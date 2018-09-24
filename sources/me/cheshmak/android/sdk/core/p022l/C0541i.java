package me.cheshmak.android.sdk.core.p022l;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import me.cheshmak.android.sdk.core.p022l.C0541i;
import me.cheshmak.android.sdk.core.p022l.C0541i.C0482d;
import me.cheshmak.android.sdk.core.p022l.C0541i.C0524e;
import me.cheshmak.android.sdk.core.p022l.C0541i.C0536a;

/* renamed from: me.cheshmak.android.sdk.core.l.i */
public class C0541i {
    /* renamed from: a */
    public static final BigDecimal f645a = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    /* renamed from: b */
    public static final BigDecimal f646b = new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663");
    /* renamed from: l */
    private static final C0482d f647l = new C05271();
    /* renamed from: c */
    private MathContext f648c;
    /* renamed from: d */
    private String f649d;
    /* renamed from: e */
    private String f650e;
    /* renamed from: f */
    private final String f651f;
    /* renamed from: g */
    private String f652g;
    /* renamed from: h */
    private List<C0538f> f653h;
    /* renamed from: i */
    private Map<String, C0524e> f654i;
    /* renamed from: j */
    private Map<String, C0485c> f655j;
    /* renamed from: k */
    private Map<String, BigDecimal> f656k;

    /* renamed from: me.cheshmak.android.sdk.core.l.i$d */
    public interface C0482d {
        /* renamed from: a */
        BigDecimal mo4395a();

        /* renamed from: b */
        String mo4396b();
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$c */
    public abstract class C0485c {
        /* renamed from: a */
        private String f464a;
        /* renamed from: b */
        private int f465b;
        /* renamed from: c */
        protected boolean f466c = false;
        /* renamed from: d */
        final /* synthetic */ C0541i f467d;

        public C0485c(C0541i c0541i, String str, int i) {
            this.f467d = c0541i;
            this.f464a = str.toUpperCase(Locale.ROOT);
            this.f465b = i;
        }

        public C0485c(C0541i c0541i, String str, int i, boolean z) {
            this.f467d = c0541i;
            this.f464a = str.toUpperCase(Locale.ROOT);
            this.f465b = i;
            this.f466c = z;
        }

        /* renamed from: a */
        public String m761a() {
            return this.f464a;
        }

        /* renamed from: a */
        public abstract C0482d mo4397a(List<C0482d> list);

        /* renamed from: b */
        public int m763b() {
            return this.f465b;
        }

        /* renamed from: c */
        public boolean m764c() {
            return this.f465b < 0;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$e */
    public abstract class C0524e {
        /* renamed from: a */
        private String f548a;
        /* renamed from: b */
        protected boolean f549b = false;
        /* renamed from: c */
        final /* synthetic */ C0541i f550c;
        /* renamed from: d */
        private int f551d;
        /* renamed from: e */
        private boolean f552e;

        public C0524e(C0541i c0541i, String str, int i, boolean z) {
            this.f550c = c0541i;
            this.f548a = str;
            this.f551d = i;
            this.f552e = z;
        }

        public C0524e(C0541i c0541i, String str, int i, boolean z, boolean z2) {
            this.f550c = c0541i;
            this.f548a = str;
            this.f551d = i;
            this.f552e = z;
            this.f549b = z2;
        }

        /* renamed from: a */
        public String m929a() {
            return this.f548a;
        }

        /* renamed from: a */
        public abstract BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2);

        /* renamed from: b */
        public int m931b() {
            return this.f551d;
        }

        /* renamed from: c */
        public boolean m932c() {
            return this.f552e;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$i */
    public abstract class C0525i extends C0524e {
        /* renamed from: d */
        final /* synthetic */ C0541i f553d;

        public C0525i(C0541i c0541i, String str, int i, boolean z) {
            this.f553d = c0541i;
            super(c0541i, str, i, z);
        }

        /* renamed from: a */
        public abstract BigDecimal mo4406a(BigDecimal bigDecimal);

        /* renamed from: a */
        public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            if (bigDecimal2 == null) {
                return mo4406a(bigDecimal);
            }
            throw new C0536a("Did not expect a second parameter for unary operator");
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$b */
    public abstract class C0526b extends C0485c {
        /* renamed from: b */
        final /* synthetic */ C0541i f557b;

        public C0526b(C0541i c0541i, String str, int i) {
            this.f557b = c0541i;
            super(c0541i, str, i);
        }

        public C0526b(C0541i c0541i, String str, int i, boolean z) {
            this.f557b = c0541i;
            super(c0541i, str, i, z);
        }

        /* renamed from: a */
        public C0482d mo4397a(final List<C0482d> list) {
            return new C0482d(this) {
                /* renamed from: b */
                final /* synthetic */ C0526b f624b;
                /* renamed from: c */
                private List<BigDecimal> f625c;

                /* renamed from: c */
                private List<BigDecimal> m1003c() {
                    if (this.f625c == null) {
                        this.f625c = new ArrayList();
                        for (C0482d a : list) {
                            this.f625c.add(a.mo4395a());
                        }
                    }
                    return this.f625c;
                }

                /* renamed from: a */
                public BigDecimal mo4395a() {
                    return this.f624b.mo4407b(m1003c());
                }

                /* renamed from: b */
                public String mo4396b() {
                    return String.valueOf(this.f624b.mo4407b(m1003c()));
                }
            };
        }

        /* renamed from: b */
        public abstract BigDecimal mo4407b(List<BigDecimal> list);
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$1 */
    static class C05271 implements C0482d {
        C05271() {
        }

        /* renamed from: a */
        public BigDecimal mo4395a() {
            return null;
        }

        /* renamed from: b */
        public String mo4396b() {
            return null;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$a */
    public static class C0536a extends RuntimeException {
        public C0536a(String str) {
            super(str);
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$f */
    class C0538f {
        /* renamed from: a */
        public String f626a = "";
        /* renamed from: b */
        public C0539g f627b;
        /* renamed from: c */
        public int f628c;
        /* renamed from: d */
        final /* synthetic */ C0541i f629d;

        C0538f(C0541i c0541i) {
            this.f629d = c0541i;
        }

        /* renamed from: a */
        public char m1006a(int i) {
            return this.f626a.charAt(i);
        }

        /* renamed from: a */
        public int m1007a() {
            return this.f626a.length();
        }

        /* renamed from: a */
        public void m1008a(char c) {
            this.f626a += c;
        }

        /* renamed from: a */
        public void m1009a(String str) {
            this.f626a += str;
        }

        public String toString() {
            return this.f626a;
        }
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$g */
    enum C0539g {
        VARIABLE,
        FUNCTION,
        LITERAL,
        OPERATOR,
        UNARY_OPERATOR,
        OPEN_PAREN,
        COMMA,
        CLOSE_PAREN,
        HEX_LITERAL,
        STRINGPARAM
    }

    /* renamed from: me.cheshmak.android.sdk.core.l.i$h */
    private class C0540h implements Iterator<C0538f> {
        /* renamed from: a */
        final /* synthetic */ C0541i f641a;
        /* renamed from: b */
        private int f642b = 0;
        /* renamed from: c */
        private String f643c;
        /* renamed from: d */
        private C0538f f644d;

        public C0540h(C0541i c0541i, String str) {
            this.f641a = c0541i;
            this.f643c = str.trim();
        }

        /* renamed from: a */
        private boolean m1010a(char c) {
            return c == 'x' || c == 'X' || ((c >= '0' && c <= '9') || ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')));
        }

        /* renamed from: b */
        private char m1011b() {
            return this.f642b < this.f643c.length() + -1 ? this.f643c.charAt(this.f642b + 1) : '\u0000';
        }

        /* renamed from: a */
        public C0538f m1012a() {
            C0538f c0538f = new C0538f(this.f641a);
            if (this.f642b >= this.f643c.length()) {
                this.f644d = null;
                return null;
            }
            char charAt = this.f643c.charAt(this.f642b);
            while (Character.isWhitespace(charAt) && this.f642b < this.f643c.length()) {
                String str = this.f643c;
                int i = this.f642b + 1;
                this.f642b = i;
                charAt = str.charAt(i);
            }
            c0538f.f628c = this.f642b;
            if (Character.isDigit(charAt) || (charAt == '.' && Character.isDigit(m1011b()))) {
                Object obj = (charAt == '0' && (m1011b() == 'x' || m1011b() == 'X')) ? 1 : null;
                while (true) {
                    if ((obj != null && m1010a(r0)) || ((Character.isDigit(r0) || r0 == '.' || r0 == 'e' || r0 == 'E' || ((r0 == '-' && c0538f.m1007a() > 0 && ('e' == c0538f.m1006a(c0538f.m1007a() - 1) || 'E' == c0538f.m1006a(c0538f.m1007a() - 1))) || (r0 == '+' && c0538f.m1007a() > 0 && ('e' == c0538f.m1006a(c0538f.m1007a() - 1) || 'E' == c0538f.m1006a(c0538f.m1007a() - 1))))) && this.f642b < this.f643c.length())) {
                        str = this.f643c;
                        int i2 = this.f642b;
                        this.f642b = i2 + 1;
                        c0538f.m1008a(str.charAt(i2));
                        charAt = this.f642b == this.f643c.length() ? '\u0000' : this.f643c.charAt(this.f642b);
                    }
                }
                if (obj == null) {
                }
                c0538f.f627b = obj == null ? C0539g.HEX_LITERAL : C0539g.LITERAL;
            } else if (charAt == '\"') {
                this.f642b++;
                if (this.f644d.f627b == C0539g.STRINGPARAM) {
                    return m1012a();
                }
                charAt = this.f643c.charAt(this.f642b);
                while (charAt != '\"') {
                    str = this.f643c;
                    i = this.f642b;
                    this.f642b = i + 1;
                    c0538f.m1008a(str.charAt(i));
                    charAt = this.f642b == this.f643c.length() ? '\u0000' : this.f643c.charAt(this.f642b);
                }
                c0538f.f627b = C0539g.STRINGPARAM;
            } else if (Character.isLetter(charAt) || this.f641a.f649d.indexOf(charAt) >= 0) {
                while (true) {
                    if ((Character.isLetter(charAt) || Character.isDigit(charAt) || this.f641a.f650e.indexOf(charAt) >= 0 || (c0538f.m1007a() == 0 && this.f641a.f649d.indexOf(charAt) >= 0)) && this.f642b < this.f643c.length()) {
                        str = this.f643c;
                        i = this.f642b;
                        this.f642b = i + 1;
                        c0538f.m1008a(str.charAt(i));
                        charAt = this.f642b == this.f643c.length() ? '\u0000' : this.f643c.charAt(this.f642b);
                    }
                }
                if (charAt == ' ') {
                    while (charAt == ' ' && this.f642b < this.f643c.length()) {
                        str = this.f643c;
                        int i3 = this.f642b;
                        this.f642b = i3 + 1;
                        charAt = str.charAt(i3);
                    }
                    this.f642b--;
                }
                c0538f.f627b = charAt == '(' ? C0539g.FUNCTION : C0539g.VARIABLE;
            } else if (charAt == '(' || charAt == ')' || charAt == ',') {
                if (charAt == '(') {
                    c0538f.f627b = C0539g.OPEN_PAREN;
                } else if (charAt == ')') {
                    c0538f.f627b = C0539g.CLOSE_PAREN;
                } else {
                    c0538f.f627b = C0539g.COMMA;
                }
                c0538f.m1008a(charAt);
                this.f642b++;
            } else {
                int i4 = this.f642b;
                char charAt2 = this.f643c.charAt(this.f642b);
                int i5 = -1;
                String str2 = "";
                while (!Character.isLetter(charAt2) && !Character.isDigit(charAt2) && this.f641a.f649d.indexOf(charAt2) < 0 && !Character.isWhitespace(charAt2) && charAt2 != '(' && charAt2 != ')' && charAt2 != ',' && this.f642b < this.f643c.length()) {
                    str2 = str2 + charAt2;
                    this.f642b++;
                    if (this.f641a.f654i.containsKey(str2)) {
                        i5 = this.f642b;
                    }
                    charAt2 = this.f642b == this.f643c.length() ? '\u0000' : this.f643c.charAt(this.f642b);
                }
                if (i5 != -1) {
                    c0538f.m1009a(this.f643c.substring(i4, i5));
                    this.f642b = i5;
                } else {
                    c0538f.m1009a(str2);
                }
                if (this.f644d == null || this.f644d.f627b == C0539g.OPERATOR || this.f644d.f627b == C0539g.OPEN_PAREN || this.f644d.f627b == C0539g.COMMA) {
                    c0538f.f626a += "u";
                    c0538f.f627b = C0539g.UNARY_OPERATOR;
                } else {
                    c0538f.f627b = C0539g.OPERATOR;
                }
            }
            this.f644d = c0538f;
            return c0538f;
        }

        public boolean hasNext() {
            return this.f642b < this.f643c.length();
        }

        public /* synthetic */ Object next() {
            return m1012a();
        }

        public void remove() {
            throw new C0536a("remove() not supported");
        }
    }

    public C0541i(String str) {
        this(str, MathContext.DECIMAL32);
    }

    public C0541i(String str, MathContext mathContext) {
        this.f648c = null;
        this.f649d = "_";
        this.f650e = "_";
        this.f652g = null;
        this.f653h = null;
        this.f654i = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.f655j = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.f656k = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.f648c = mathContext;
        this.f652g = str;
        this.f651f = str;
        m1030a(new C0524e(this, "+", 20, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f556a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f556a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.add(bigDecimal2, this.f556a.f648c);
            }
        });
        m1030a(new C0524e(this, "-", 20, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f568a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f568a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.subtract(bigDecimal2, this.f568a.f648c);
            }
        });
        m1030a(new C0524e(this, "*", 30, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f580a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f580a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.multiply(bigDecimal2, this.f580a.f648c);
            }
        });
        m1030a(new C0524e(this, "/", 30, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f592a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f592a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.divide(bigDecimal2, this.f592a.f648c);
            }
        });
        m1030a(new C0524e(this, "%", 30, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f613a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f613a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.remainder(bigDecimal2, this.f613a.f648c);
            }
        });
        m1030a(new C0524e(this, "^", 40, false) {
            /* renamed from: a */
            final /* synthetic */ C0541i f615a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f615a.m1016a(bigDecimal, bigDecimal2);
                int signum = bigDecimal2.signum();
                double doubleValue = bigDecimal.doubleValue();
                BigDecimal multiply = bigDecimal2.multiply(new BigDecimal(signum));
                BigDecimal remainder = multiply.remainder(BigDecimal.ONE);
                multiply = bigDecimal.pow(multiply.subtract(remainder).intValueExact(), this.f615a.f648c).multiply(new BigDecimal(Math.pow(doubleValue, remainder.doubleValue())), this.f615a.f648c);
                return signum == -1 ? BigDecimal.ONE.divide(multiply, this.f615a.f648c.getPrecision(), RoundingMode.HALF_UP) : multiply;
            }
        });
        m1030a(new C0524e(this, "&&", 4, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f616a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                Object obj = 1;
                this.f616a.m1016a(bigDecimal, bigDecimal2);
                Object obj2 = bigDecimal.compareTo(BigDecimal.ZERO) != 0 ? 1 : null;
                if (bigDecimal2.compareTo(BigDecimal.ZERO) == 0) {
                    obj = null;
                }
                return (obj2 == null || obj == null) ? BigDecimal.ZERO : BigDecimal.ONE;
            }
        });
        m1030a(new C0524e(this, "||", 2, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f618a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                Object obj = 1;
                this.f618a.m1016a(bigDecimal, bigDecimal2);
                Object obj2 = bigDecimal.compareTo(BigDecimal.ZERO) != 0 ? 1 : null;
                if (bigDecimal2.compareTo(BigDecimal.ZERO) == 0) {
                    obj = null;
                }
                return (obj2 == null && obj == null) ? BigDecimal.ZERO : BigDecimal.ONE;
            }
        });
        m1030a(new C0524e(this, ">", 10, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f575a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f575a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.compareTo(bigDecimal2) == 1 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1030a(new C0524e(this, ">=", 10, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f586a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f586a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.compareTo(bigDecimal2) >= 0 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1030a(new C0524e(this, "<", 10, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f597a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f597a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.compareTo(bigDecimal2) == -1 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1030a(new C0524e(this, "<=", 10, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f617a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f617a.m1016a(bigDecimal, bigDecimal2);
                return bigDecimal.compareTo(bigDecimal2) <= 0 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1030a(new C0524e(this, "=", 7, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f619a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                return bigDecimal == bigDecimal2 ? BigDecimal.ONE : (bigDecimal == null || bigDecimal2 == null) ? BigDecimal.ZERO : bigDecimal.compareTo(bigDecimal2) == 0 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1030a(new C0524e(this, "==", 7, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f620a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                return ((C0524e) this.f620a.f654i.get("=")).mo4405a(bigDecimal, bigDecimal2);
            }
        });
        m1030a(new C0524e(this, "!=", 7, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f621a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                return bigDecimal == bigDecimal2 ? BigDecimal.ZERO : (bigDecimal == null || bigDecimal2 == null) ? BigDecimal.ONE : bigDecimal.compareTo(bigDecimal2) != 0 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1030a(new C0524e(this, "<>", 7, false, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f622a;

            /* renamed from: a */
            public BigDecimal mo4405a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
                this.f622a.m1016a(bigDecimal, bigDecimal2);
                return ((C0524e) this.f622a.f654i.get("!=")).mo4405a(bigDecimal, bigDecimal2);
            }
        });
        m1030a(new C0525i(this, "-", 60, false) {
            /* renamed from: a */
            final /* synthetic */ C0541i f554a;

            /* renamed from: a */
            public BigDecimal mo4406a(BigDecimal bigDecimal) {
                return bigDecimal.multiply(new BigDecimal(-1));
            }
        });
        m1030a(new C0525i(this, "+", 60, false) {
            /* renamed from: a */
            final /* synthetic */ C0541i f555a;

            /* renamed from: a */
            public BigDecimal mo4406a(BigDecimal bigDecimal) {
                return bigDecimal.multiply(BigDecimal.ONE);
            }
        });
        m1028a(new C0526b(this, "NOT", 1, true) {
            /* renamed from: a */
            final /* synthetic */ C0541i f558a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f558a.m1015a((BigDecimal) list.get(0));
                return (((BigDecimal) list.get(0)).compareTo(BigDecimal.ZERO) == 0 ? 1 : 0) != 0 ? BigDecimal.ONE : BigDecimal.ZERO;
            }
        });
        m1029a(new C0485c(this, "IF", 3) {
            /* renamed from: a */
            final /* synthetic */ C0541i f559a;

            /* renamed from: a */
            public C0482d mo4397a(List<C0482d> list) {
                BigDecimal a = ((C0482d) list.get(0)).mo4395a();
                this.f559a.m1015a(a);
                return (a.compareTo(BigDecimal.ZERO) != 0 ? 1 : 0) != 0 ? (C0482d) list.get(1) : (C0482d) list.get(2);
            }
        });
        m1028a(new C0526b(this, "RANDOM", 0) {
            /* renamed from: a */
            final /* synthetic */ C0541i f560a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                return new BigDecimal(Math.random(), this.f560a.f648c);
            }
        });
        m1028a(new C0526b(this, "SIN", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f561a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f561a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.sin(Math.toRadians(((BigDecimal) list.get(0)).doubleValue())), this.f561a.f648c);
            }
        });
        m1028a(new C0526b(this, "COS", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f562a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f562a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.cos(Math.toRadians(((BigDecimal) list.get(0)).doubleValue())), this.f562a.f648c);
            }
        });
        m1028a(new C0526b(this, "TAN", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f563a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f563a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.tan(Math.toRadians(((BigDecimal) list.get(0)).doubleValue())), this.f563a.f648c);
            }
        });
        m1028a(new C0526b(this, "ASIN", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f564a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f564a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.toDegrees(Math.asin(((BigDecimal) list.get(0)).doubleValue())), this.f564a.f648c);
            }
        });
        m1028a(new C0526b(this, "ACOS", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f565a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f565a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.toDegrees(Math.acos(((BigDecimal) list.get(0)).doubleValue())), this.f565a.f648c);
            }
        });
        m1028a(new C0526b(this, "ATAN", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f566a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f566a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.toDegrees(Math.atan(((BigDecimal) list.get(0)).doubleValue())), this.f566a.f648c);
            }
        });
        m1028a(new C0526b(this, "ATAN2", 2) {
            /* renamed from: a */
            final /* synthetic */ C0541i f567a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f567a.m1016a((BigDecimal) list.get(0), (BigDecimal) list.get(1));
                return new BigDecimal(Math.toDegrees(Math.atan2(((BigDecimal) list.get(0)).doubleValue(), ((BigDecimal) list.get(1)).doubleValue())), this.f567a.f648c);
            }
        });
        m1028a(new C0526b(this, "SINH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f569a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f569a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.sinh(((BigDecimal) list.get(0)).doubleValue()), this.f569a.f648c);
            }
        });
        m1028a(new C0526b(this, "COSH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f570a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f570a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.cosh(((BigDecimal) list.get(0)).doubleValue()), this.f570a.f648c);
            }
        });
        m1028a(new C0526b(this, "TANH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f571a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f571a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.tanh(((BigDecimal) list.get(0)).doubleValue()), this.f571a.f648c);
            }
        });
        m1028a(new C0526b(this, "SEC", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f572a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f572a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(1.0d / Math.cos(Math.toRadians(((BigDecimal) list.get(0)).doubleValue())), this.f572a.f648c);
            }
        });
        m1028a(new C0526b(this, "CSC", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f573a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f573a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(1.0d / Math.sin(Math.toRadians(((BigDecimal) list.get(0)).doubleValue())), this.f573a.f648c);
            }
        });
        m1028a(new C0526b(this, "SECH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f574a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f574a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(1.0d / Math.cosh(((BigDecimal) list.get(0)).doubleValue()), this.f574a.f648c);
            }
        });
        m1028a(new C0526b(this, "CSCH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f576a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f576a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(1.0d / Math.sinh(((BigDecimal) list.get(0)).doubleValue()), this.f576a.f648c);
            }
        });
        m1028a(new C0526b(this, "COT", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f577a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f577a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(1.0d / Math.tan(Math.toRadians(((BigDecimal) list.get(0)).doubleValue())), this.f577a.f648c);
            }
        });
        m1028a(new C0526b(this, "ACOT", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f578a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f578a.m1015a((BigDecimal) list.get(0));
                if (((BigDecimal) list.get(0)).doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return new BigDecimal(Math.toDegrees(Math.atan(1.0d / ((BigDecimal) list.get(0)).doubleValue())), this.f578a.f648c);
                }
                throw new C0536a("Number must not be 0");
            }
        });
        m1028a(new C0526b(this, "COTH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f579a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f579a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(1.0d / Math.tanh(((BigDecimal) list.get(0)).doubleValue()), this.f579a.f648c);
            }
        });
        m1028a(new C0526b(this, "ASINH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f581a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f581a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.log(Math.sqrt(Math.pow(((BigDecimal) list.get(0)).doubleValue(), 2.0d) + 1.0d) + ((BigDecimal) list.get(0)).doubleValue()), this.f581a.f648c);
            }
        });
        m1028a(new C0526b(this, "ACOSH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f582a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f582a.m1015a((BigDecimal) list.get(0));
                if (Double.compare(((BigDecimal) list.get(0)).doubleValue(), 1.0d) < 0) {
                    throw new C0536a("Number must be x >= 1");
                }
                return new BigDecimal(Math.log(Math.sqrt(Math.pow(((BigDecimal) list.get(0)).doubleValue(), 2.0d) - 1.0d) + ((BigDecimal) list.get(0)).doubleValue()), this.f582a.f648c);
            }
        });
        m1028a(new C0526b(this, "ATANH", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f583a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f583a.m1015a((BigDecimal) list.get(0));
                if (Math.abs(((BigDecimal) list.get(0)).doubleValue()) <= 1.0d && Math.abs(((BigDecimal) list.get(0)).doubleValue()) != 1.0d) {
                    return new BigDecimal(Math.log((1.0d + ((BigDecimal) list.get(0)).doubleValue()) / (1.0d - ((BigDecimal) list.get(0)).doubleValue())) * 0.5d, this.f583a.f648c);
                }
                throw new C0536a("Number must be |x| < 1");
            }
        });
        m1028a(new C0526b(this, "RAD", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f584a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f584a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.toRadians(((BigDecimal) list.get(0)).doubleValue()), this.f584a.f648c);
            }
        });
        m1028a(new C0526b(this, "DEG", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f585a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f585a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.toDegrees(((BigDecimal) list.get(0)).doubleValue()), this.f585a.f648c);
            }
        });
        m1028a(new C0526b(this, "MAX", -1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f587a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                if (list.size() == 0) {
                    throw new C0536a("MAX requires at least one parameter");
                }
                BigDecimal bigDecimal = null;
                for (BigDecimal bigDecimal2 : list) {
                    BigDecimal bigDecimal22;
                    this.f587a.m1015a(bigDecimal22);
                    if (bigDecimal != null && bigDecimal22.compareTo(bigDecimal) <= 0) {
                        bigDecimal22 = bigDecimal;
                    }
                    bigDecimal = bigDecimal22;
                }
                return bigDecimal;
            }
        });
        m1028a(new C0526b(this, "MIN", -1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f588a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                if (list.size() == 0) {
                    throw new C0536a("MIN requires at least one parameter");
                }
                BigDecimal bigDecimal = null;
                for (BigDecimal bigDecimal2 : list) {
                    BigDecimal bigDecimal22;
                    this.f588a.m1015a(bigDecimal22);
                    if (bigDecimal != null && bigDecimal22.compareTo(bigDecimal) >= 0) {
                        bigDecimal22 = bigDecimal;
                    }
                    bigDecimal = bigDecimal22;
                }
                return bigDecimal;
            }
        });
        m1028a(new C0526b(this, "ABS", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f589a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f589a.m1015a((BigDecimal) list.get(0));
                return ((BigDecimal) list.get(0)).abs(this.f589a.f648c);
            }
        });
        m1028a(new C0526b(this, "LOG", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f590a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f590a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.log(((BigDecimal) list.get(0)).doubleValue()), this.f590a.f648c);
            }
        });
        m1028a(new C0526b(this, "LOG10", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f591a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f591a.m1015a((BigDecimal) list.get(0));
                return new BigDecimal(Math.log10(((BigDecimal) list.get(0)).doubleValue()), this.f591a.f648c);
            }
        });
        m1028a(new C0526b(this, "ROUND", 2) {
            /* renamed from: a */
            final /* synthetic */ C0541i f593a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f593a.m1016a((BigDecimal) list.get(0), (BigDecimal) list.get(1));
                return ((BigDecimal) list.get(0)).setScale(((BigDecimal) list.get(1)).intValue(), this.f593a.f648c.getRoundingMode());
            }
        });
        m1028a(new C0526b(this, "FLOOR", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f594a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f594a.m1015a((BigDecimal) list.get(0));
                return ((BigDecimal) list.get(0)).setScale(0, RoundingMode.FLOOR);
            }
        });
        m1028a(new C0526b(this, "CEILING", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f595a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f595a.m1015a((BigDecimal) list.get(0));
                return ((BigDecimal) list.get(0)).setScale(0, RoundingMode.CEILING);
            }
        });
        m1028a(new C0526b(this, "SQRT", 1) {
            /* renamed from: a */
            final /* synthetic */ C0541i f596a;

            /* renamed from: b */
            public BigDecimal mo4407b(List<BigDecimal> list) {
                this.f596a.m1015a((BigDecimal) list.get(0));
                BigDecimal bigDecimal = (BigDecimal) list.get(0);
                if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                    return new BigDecimal(0);
                }
                if (bigDecimal.signum() < 0) {
                    throw new C0536a("Argument to SQRT() function must not be negative");
                }
                BigInteger toBigInteger = bigDecimal.movePointRight(this.f596a.f648c.getPrecision() << 1).toBigInteger();
                BigInteger shiftRight = toBigInteger.shiftRight((toBigInteger.bitLength() + 1) >> 1);
                while (true) {
                    BigInteger shiftRight2 = shiftRight.add(toBigInteger.divide(shiftRight)).shiftRight(1);
                    Thread.yield();
                    if (shiftRight2.compareTo(shiftRight) == 0) {
                        return new BigDecimal(shiftRight2, this.f596a.f648c.getPrecision());
                    }
                    shiftRight = shiftRight2;
                }
            }
        });
        this.f656k.put("e", f646b);
        this.f656k.put("PI", f645a);
        this.f656k.put("NULL", null);
        this.f656k.put("TRUE", BigDecimal.ONE);
        this.f656k.put("FALSE", BigDecimal.ZERO);
    }

    /* renamed from: a */
    private List<C0538f> m1014a(String str) {
        List arrayList = new ArrayList();
        Stack stack = new Stack();
        C0540h c0540h = new C0540h(this, str);
        C0538f c0538f = null;
        Object obj = null;
        while (c0540h.hasNext()) {
            C0538f a = c0540h.m1012a();
            C0524e c0524e;
            switch (a.f627b) {
                case STRINGPARAM:
                    stack.push(a);
                    break;
                case LITERAL:
                case HEX_LITERAL:
                    arrayList.add(a);
                    break;
                case VARIABLE:
                    arrayList.add(a);
                    break;
                case FUNCTION:
                    stack.push(a);
                    C0538f c0538f2 = a;
                    break;
                case COMMA:
                    if (c0538f == null || c0538f.f627b != C0539g.OPERATOR) {
                        while (!stack.isEmpty() && ((C0538f) stack.peek()).f627b != C0539g.OPEN_PAREN) {
                            arrayList.add(stack.pop());
                        }
                        if (!stack.isEmpty()) {
                            break;
                        }
                        throw new C0536a("Parse error for function '" + obj + "'");
                    }
                    throw new C0536a("Missing parameter(s) for operator " + c0538f + " at character position " + c0538f.f628c);
                case OPERATOR:
                    if (c0538f == null || !(c0538f.f627b == C0539g.COMMA || c0538f.f627b == C0539g.OPEN_PAREN)) {
                        c0524e = (C0524e) this.f654i.get(a.f626a);
                        if (c0524e != null) {
                            m1018a(arrayList, stack, c0524e);
                            stack.push(a);
                            break;
                        }
                        throw new C0536a("Unknown operator '" + a + "' at position " + (a.f628c + 1));
                    }
                    throw new C0536a("Missing parameter(s) for operator " + a + " at character position " + a.f628c);
                case UNARY_OPERATOR:
                    if (c0538f == null || c0538f.f627b == C0539g.OPERATOR || c0538f.f627b == C0539g.COMMA || c0538f.f627b == C0539g.OPEN_PAREN) {
                        c0524e = (C0524e) this.f654i.get(a.f626a);
                        if (c0524e != null) {
                            m1018a(arrayList, stack, c0524e);
                            stack.push(a);
                            break;
                        }
                        throw new C0536a("Unknown unary operator '" + a.f626a.substring(0, a.f626a.length() - 1) + "' at position " + (a.f628c + 1));
                    }
                    throw new C0536a("Invalid position for unary operator " + a + " at character position " + a.f628c);
                case OPEN_PAREN:
                    if (c0538f != null) {
                        if (c0538f.f627b == C0539g.LITERAL || c0538f.f627b == C0539g.CLOSE_PAREN || c0538f.f627b == C0539g.VARIABLE || c0538f.f627b == C0539g.HEX_LITERAL) {
                            C0538f c0538f3 = new C0538f(this);
                            c0538f3.m1009a("*");
                            c0538f3.f627b = C0539g.OPERATOR;
                            stack.push(c0538f3);
                        }
                        if (c0538f.f627b == C0539g.FUNCTION) {
                            arrayList.add(a);
                        }
                    }
                    stack.push(a);
                    break;
                case CLOSE_PAREN:
                    if (c0538f == null || c0538f.f627b != C0539g.OPERATOR) {
                        while (!stack.isEmpty() && ((C0538f) stack.peek()).f627b != C0539g.OPEN_PAREN) {
                            arrayList.add(stack.pop());
                        }
                        if (!stack.isEmpty()) {
                            stack.pop();
                            if (!stack.isEmpty() && ((C0538f) stack.peek()).f627b == C0539g.FUNCTION) {
                                arrayList.add(stack.pop());
                                break;
                            }
                        }
                        throw new C0536a("Mismatched parentheses");
                    }
                    throw new C0536a("Missing parameter(s) for operator " + c0538f + " at character position " + c0538f.f628c);
                    break;
                default:
                    break;
            }
            c0538f = a;
        }
        while (!stack.isEmpty()) {
            c0538f = (C0538f) stack.pop();
            if (c0538f.f627b == C0539g.OPEN_PAREN || c0538f.f627b == C0539g.CLOSE_PAREN) {
                throw new C0536a("Mismatched parentheses");
            }
            arrayList.add(c0538f);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m1015a(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            throw new ArithmeticException("Operand may not be null");
        }
    }

    /* renamed from: a */
    private void m1016a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        if (bigDecimal == null) {
            throw new ArithmeticException("First operand may not be null");
        } else if (bigDecimal2 == null) {
            throw new ArithmeticException("Second operand may not be null");
        }
    }

    /* renamed from: a */
    private void m1017a(List<C0538f> list) {
        Stack stack = new Stack();
        stack.push(Integer.valueOf(0));
        for (C0538f c0538f : list) {
            switch (c0538f.f627b) {
                case FUNCTION:
                    C0485c c0485c = (C0485c) this.f655j.get(c0538f.f626a.toUpperCase(Locale.ROOT));
                    if (c0485c == null) {
                        throw new C0536a("Unknown function '" + c0538f + "' at position " + (c0538f.f628c + 1));
                    }
                    int intValue = ((Integer) stack.pop()).intValue();
                    if (c0485c.m764c() || intValue == c0485c.m763b()) {
                        if (stack.size() > 0) {
                            stack.set(stack.size() - 1, Integer.valueOf(((Integer) stack.peek()).intValue() + 1));
                            break;
                        }
                        throw new C0536a("Too many function calls, maximum scope exceeded");
                    }
                    throw new C0536a("Function " + c0538f + " expected " + c0485c.m763b() + " parameters, got " + intValue);
                case OPERATOR:
                    if (((Integer) stack.peek()).intValue() >= 2) {
                        stack.set(stack.size() - 1, Integer.valueOf((((Integer) stack.peek()).intValue() - 2) + 1));
                        break;
                    }
                    throw new C0536a("Missing parameter(s) for operator " + c0538f);
                case UNARY_OPERATOR:
                    if (((Integer) stack.peek()).intValue() >= 1) {
                        break;
                    }
                    throw new C0536a("Missing parameter(s) for operator " + c0538f);
                case OPEN_PAREN:
                    stack.push(Integer.valueOf(0));
                    break;
                default:
                    stack.set(stack.size() - 1, Integer.valueOf(((Integer) stack.peek()).intValue() + 1));
                    break;
            }
        }
        if (stack.size() > 1) {
            throw new C0536a("Too many unhandled function parameter lists");
        } else if (((Integer) stack.peek()).intValue() > 1) {
            throw new C0536a("Too many numbers or variables");
        } else if (((Integer) stack.peek()).intValue() < 1) {
            throw new C0536a("Empty expression");
        }
    }

    /* renamed from: a */
    private void m1018a(List<C0538f> list, Stack<C0538f> stack, C0524e c0524e) {
        C0538f c0538f = stack.isEmpty() ? null : (C0538f) stack.peek();
        while (c0538f != null) {
            if (c0538f.f627b != C0539g.OPERATOR && c0538f.f627b != C0539g.UNARY_OPERATOR) {
                return;
            }
            if ((c0524e.m932c() && c0524e.m931b() <= ((C0524e) this.f654i.get(c0538f.f626a)).m931b()) || c0524e.m931b() < ((C0524e) this.f654i.get(c0538f.f626a)).m931b()) {
                list.add(stack.pop());
                c0538f = stack.isEmpty() ? null : (C0538f) stack.peek();
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    private List<C0538f> m1022b() {
        if (this.f653h == null) {
            this.f653h = m1014a(this.f652g);
            m1017a(this.f653h);
        }
        return this.f653h;
    }

    /* renamed from: a */
    public BigDecimal m1026a() {
        return m1027a(true);
    }

    /* renamed from: a */
    public BigDecimal m1027a(boolean z) {
        Stack stack = new Stack();
        for (final C0538f c0538f : m1022b()) {
            final C0482d c0482d;
            switch (c0538f.f627b) {
                case STRINGPARAM:
                    stack.push(new C0482d(this) {
                        /* renamed from: b */
                        final /* synthetic */ C0541i f610b;

                        /* renamed from: a */
                        public BigDecimal mo4395a() {
                            return null;
                        }

                        /* renamed from: b */
                        public String mo4396b() {
                            return c0538f.f626a;
                        }
                    });
                    break;
                case LITERAL:
                    stack.push(new C0482d(this) {
                        /* renamed from: b */
                        final /* synthetic */ C0541i f608b;

                        /* renamed from: a */
                        public BigDecimal mo4395a() {
                            return c0538f.f626a.equalsIgnoreCase("NULL") ? null : new BigDecimal(c0538f.f626a, this.f608b.f648c);
                        }

                        /* renamed from: b */
                        public String mo4396b() {
                            return String.valueOf(new BigDecimal(c0538f.f626a, this.f608b.f648c));
                        }
                    });
                    break;
                case HEX_LITERAL:
                    stack.push(new C0482d(this) {
                        /* renamed from: b */
                        final /* synthetic */ C0541i f612b;

                        /* renamed from: a */
                        public BigDecimal mo4395a() {
                            return new BigDecimal(new BigInteger(c0538f.f626a.substring(2), 16), this.f612b.f648c);
                        }

                        /* renamed from: b */
                        public String mo4396b() {
                            return new BigInteger(c0538f.f626a.substring(2), 16).toString();
                        }
                    });
                    break;
                case VARIABLE:
                    if (this.f656k.containsKey(c0538f.f626a)) {
                        stack.push(new C0482d(this) {
                            /* renamed from: b */
                            final /* synthetic */ C0541i f606b;

                            /* renamed from: a */
                            public BigDecimal mo4395a() {
                                BigDecimal bigDecimal = (BigDecimal) this.f606b.f656k.get(c0538f.f626a);
                                return bigDecimal == null ? null : bigDecimal.round(this.f606b.f648c);
                            }

                            /* renamed from: b */
                            public String mo4396b() {
                                return c0538f.f626a;
                            }
                        });
                        break;
                    }
                    throw new C0536a("Unknown operator or function: " + c0538f);
                case FUNCTION:
                    C0485c c0485c = (C0485c) this.f655j.get(c0538f.f626a.toUpperCase(Locale.ROOT));
                    List arrayList = new ArrayList(!c0485c.m764c() ? c0485c.m763b() : 0);
                    while (!stack.isEmpty() && stack.peek() != f647l) {
                        arrayList.add(0, stack.pop());
                    }
                    if (stack.peek() == f647l) {
                        stack.pop();
                    }
                    stack.push(c0485c.mo4397a(arrayList));
                    break;
                case OPERATOR:
                    c0482d = (C0482d) stack.pop();
                    final C0482d c0482d2 = (C0482d) stack.pop();
                    stack.push(new C0482d(this) {
                        /* renamed from: d */
                        final /* synthetic */ C0541i f604d;

                        /* renamed from: a */
                        public BigDecimal mo4395a() {
                            return ((C0524e) this.f604d.f654i.get(c0538f.f626a)).mo4405a(c0482d2.mo4395a(), c0482d.mo4395a());
                        }

                        /* renamed from: b */
                        public String mo4396b() {
                            return String.valueOf(((C0524e) this.f604d.f654i.get(c0538f.f626a)).mo4405a(c0482d2.mo4395a(), c0482d.mo4395a()));
                        }
                    });
                    break;
                case UNARY_OPERATOR:
                    c0482d = (C0482d) stack.pop();
                    stack.push(new C0482d(this) {
                        /* renamed from: c */
                        final /* synthetic */ C0541i f600c;

                        /* renamed from: a */
                        public BigDecimal mo4395a() {
                            return ((C0524e) this.f600c.f654i.get(c0538f.f626a)).mo4405a(c0482d.mo4395a(), null);
                        }

                        /* renamed from: b */
                        public String mo4396b() {
                            return String.valueOf(((C0524e) this.f600c.f654i.get(c0538f.f626a)).mo4405a(c0482d.mo4395a(), null));
                        }
                    });
                    break;
                case OPEN_PAREN:
                    stack.push(f647l);
                    break;
                default:
                    break;
            }
        }
        BigDecimal a = ((C0482d) stack.pop()).mo4395a();
        return a == null ? null : z ? a.stripTrailingZeros() : a;
    }

    /* renamed from: a */
    public C0526b m1028a(C0526b c0526b) {
        return (C0526b) this.f655j.put(c0526b.m761a(), c0526b);
    }

    /* renamed from: a */
    public C0485c m1029a(C0485c c0485c) {
        return (C0485c) this.f655j.put(c0485c.m761a(), c0485c);
    }

    /* renamed from: a */
    public C0524e m1030a(C0524e c0524e) {
        Object a = c0524e.m929a();
        if (c0524e instanceof C0525i) {
            a = a + "u";
        }
        return (C0524e) this.f654i.put(a, c0524e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0541i c0541i = (C0541i) obj;
        return this.f652g == null ? c0541i.f652g == null : this.f652g.equals(c0541i.f652g);
    }

    public int hashCode() {
        return this.f652g == null ? 0 : this.f652g.hashCode();
    }

    public String toString() {
        return this.f652g;
    }
}
