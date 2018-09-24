package com.google.devtools.build.android.desugar.runtime;

import java.io.Closeable;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class ThrowableExtension {
    private static final String ANDROID_OS_BUILD_VERSION = "android.os.Build$VERSION";
    static final int API_LEVEL;
    static final AbstractDesugaringStrategy STRATEGY;
    public static final String SYSTEM_PROPERTY_TWR_DISABLE_MIMIC = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic";

    static abstract class AbstractDesugaringStrategy {
        protected static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];

        public abstract void addSuppressed(Throwable th, Throwable th2);

        public abstract Throwable[] getSuppressed(Throwable th);

        public abstract void printStackTrace(Throwable th);

        public abstract void printStackTrace(Throwable th, PrintStream printStream);

        public abstract void printStackTrace(Throwable th, PrintWriter printWriter);

        AbstractDesugaringStrategy() {
        }
    }

    static final class ConcurrentWeakIdentityHashMap {
        private final ConcurrentHashMap<WeakKey, List<Throwable>> map = new ConcurrentHashMap(16, 0.75f, 10);
        private final ReferenceQueue<Throwable> referenceQueue = new ReferenceQueue();

        private static final class WeakKey extends WeakReference<Throwable> {
            private final int hash;

            public WeakKey(Throwable referent, ReferenceQueue<Throwable> q) {
                super(referent, q);
                if (referent == null) {
                    throw new NullPointerException("The referent cannot be null");
                }
                this.hash = System.identityHashCode(referent);
            }

            public int hashCode() {
                return this.hash;
            }

            public boolean equals(Object obj) {
                if (obj == null || obj.getClass() != getClass()) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                WeakKey other = (WeakKey) obj;
                if (this.hash == other.hash && get() == other.get()) {
                    return true;
                }
                return false;
            }
        }

        ConcurrentWeakIdentityHashMap() {
        }

        public List<Throwable> get(Throwable throwable, boolean createOnAbsence) {
            deleteEmptyKeys();
            List<Throwable> list = (List) this.map.get(new WeakKey(throwable, null));
            if (!createOnAbsence) {
                return list;
            } else if (list != null) {
                r2 = list;
                return list;
            } else {
                List<Throwable> newValue = new Vector(2);
                list = (List) this.map.putIfAbsent(new WeakKey(throwable, this.referenceQueue), newValue);
                if (list != null) {
                    newValue = list;
                }
                r2 = list;
                return newValue;
            }
        }

        int size() {
            return this.map.size();
        }

        void deleteEmptyKeys() {
            Reference<?> key = this.referenceQueue.poll();
            while (key != null) {
                this.map.remove(key);
                key = this.referenceQueue.poll();
            }
        }
    }

    static final class MimicDesugaringStrategy extends AbstractDesugaringStrategy {
        static final String SUPPRESSED_PREFIX = "Suppressed: ";
        private final ConcurrentWeakIdentityHashMap map = new ConcurrentWeakIdentityHashMap();

        MimicDesugaringStrategy() {
        }

        public void addSuppressed(Throwable receiver, Throwable suppressed) {
            if (suppressed == receiver) {
                throw new IllegalArgumentException("Self suppression is not allowed.", suppressed);
            } else if (suppressed == null) {
                throw new NullPointerException("The suppressed exception cannot be null.");
            } else {
                this.map.get(receiver, true).add(suppressed);
            }
        }

        public Throwable[] getSuppressed(Throwable receiver) {
            List<Throwable> list = this.map.get(receiver, false);
            if (list == null || list.isEmpty()) {
                return EMPTY_THROWABLE_ARRAY;
            }
            return (Throwable[]) list.toArray(EMPTY_THROWABLE_ARRAY);
        }

        public void printStackTrace(Throwable receiver) {
            receiver.printStackTrace();
            List<Throwable> suppressedList = this.map.get(receiver, false);
            if (suppressedList != null) {
                synchronized (suppressedList) {
                    for (Throwable suppressed : suppressedList) {
                        System.err.print(SUPPRESSED_PREFIX);
                        suppressed.printStackTrace();
                    }
                }
            }
        }

        public void printStackTrace(Throwable receiver, PrintStream stream) {
            receiver.printStackTrace(stream);
            List<Throwable> suppressedList = this.map.get(receiver, false);
            if (suppressedList != null) {
                synchronized (suppressedList) {
                    for (Throwable suppressed : suppressedList) {
                        stream.print(SUPPRESSED_PREFIX);
                        suppressed.printStackTrace(stream);
                    }
                }
            }
        }

        public void printStackTrace(Throwable receiver, PrintWriter writer) {
            receiver.printStackTrace(writer);
            List<Throwable> suppressedList = this.map.get(receiver, false);
            if (suppressedList != null) {
                synchronized (suppressedList) {
                    for (Throwable suppressed : suppressedList) {
                        writer.print(SUPPRESSED_PREFIX);
                        suppressed.printStackTrace(writer);
                    }
                }
            }
        }
    }

    static final class NullDesugaringStrategy extends AbstractDesugaringStrategy {
        NullDesugaringStrategy() {
        }

        public void addSuppressed(Throwable receiver, Throwable suppressed) {
        }

        public Throwable[] getSuppressed(Throwable receiver) {
            return EMPTY_THROWABLE_ARRAY;
        }

        public void printStackTrace(Throwable receiver) {
            receiver.printStackTrace();
        }

        public void printStackTrace(Throwable receiver, PrintStream stream) {
            receiver.printStackTrace(stream);
        }

        public void printStackTrace(Throwable receiver, PrintWriter writer) {
            receiver.printStackTrace(writer);
        }
    }

    static final class ReuseDesugaringStrategy extends AbstractDesugaringStrategy {
        ReuseDesugaringStrategy() {
        }

        public void addSuppressed(Throwable receiver, Throwable suppressed) {
            receiver.addSuppressed(suppressed);
        }

        public Throwable[] getSuppressed(Throwable receiver) {
            return receiver.getSuppressed();
        }

        public void printStackTrace(Throwable receiver) {
            receiver.printStackTrace();
        }

        public void printStackTrace(Throwable receiver, PrintStream stream) {
            receiver.printStackTrace(stream);
        }

        public void printStackTrace(Throwable receiver, PrintWriter writer) {
            receiver.printStackTrace(writer);
        }
    }

    static {
        Integer num = null;
        AbstractDesugaringStrategy strategy;
        try {
            num = readApiLevelFromBuildVersion();
            if (num == null || num.intValue() < 19) {
                if (useMimicStrategy()) {
                    strategy = new NullDesugaringStrategy();
                } else {
                    strategy = new NullDesugaringStrategy();
                }
                STRATEGY = strategy;
                API_LEVEL = num != null ? 1 : num.intValue();
            }
            strategy = new ReuseDesugaringStrategy();
            STRATEGY = strategy;
            if (num != null) {
            }
            API_LEVEL = num != null ? 1 : num.intValue();
        } catch (Throwable e) {
            System.err.println("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy " + NullDesugaringStrategy.class.getName() + "will be used. The error is: ");
            e.printStackTrace(System.err);
            strategy = new NullDesugaringStrategy();
        }
    }

    public static AbstractDesugaringStrategy getStrategy() {
        return STRATEGY;
    }

    public static void addSuppressed(Throwable receiver, Throwable suppressed) {
        STRATEGY.addSuppressed(receiver, suppressed);
    }

    public static Throwable[] getSuppressed(Throwable receiver) {
        return STRATEGY.getSuppressed(receiver);
    }

    public static void printStackTrace(Throwable receiver) {
        STRATEGY.printStackTrace(receiver);
    }

    public static void printStackTrace(Throwable receiver, PrintWriter writer) {
        STRATEGY.printStackTrace(receiver, writer);
    }

    public static void printStackTrace(Throwable receiver, PrintStream stream) {
        STRATEGY.printStackTrace(receiver, stream);
    }

    public static void closeResource(Throwable throwable, Object resource) throws Throwable {
        Exception e;
        Throwable e2;
        if (resource != null) {
            try {
                if (API_LEVEL >= 19) {
                    ((AutoCloseable) resource).close();
                } else if (resource instanceof Closeable) {
                    ((Closeable) resource).close();
                } else {
                    resource.getClass().getMethod("close", new Class[0]).invoke(resource, new Object[0]);
                }
            } catch (Exception e3) {
                e = e3;
                throw new AssertionError(resource.getClass() + " does not have a close() method.", e);
            } catch (Exception e32) {
                e = e32;
                throw new AssertionError(resource.getClass() + " does not have a close() method.", e);
            } catch (Throwable e4) {
                e2 = e4;
                throw new AssertionError("Fail to call close() on " + resource.getClass(), e2);
            } catch (Throwable e42) {
                e2 = e42;
                throw new AssertionError("Fail to call close() on " + resource.getClass(), e2);
            } catch (Throwable e422) {
                e2 = e422;
                throw new AssertionError("Fail to call close() on " + resource.getClass(), e2);
            } catch (InvocationTargetException e5) {
                throw e5.getCause();
            } catch (Throwable e22) {
                if (throwable != null) {
                    addSuppressed(throwable, e22);
                }
            }
        }
    }

    private static boolean useMimicStrategy() {
        return !Boolean.getBoolean(SYSTEM_PROPERTY_TWR_DISABLE_MIMIC);
    }

    private static Integer readApiLevelFromBuildVersion() {
        try {
            return (Integer) Class.forName(ANDROID_OS_BUILD_VERSION).getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
