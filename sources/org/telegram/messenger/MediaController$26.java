package org.telegram.messenger;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import de.jurihock.voicesmith.DataHelper;
import de.jurihock.voicesmith.dsp.processors.DetuneProcessor;
import de.jurihock.voicesmith.dsp.processors.HoarsenessProcessor;
import de.jurihock.voicesmith.dsp.processors.RobotizeProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import turbogram.Utilities.TurboConfig;

class MediaController$26 implements Runnable {
    final /* synthetic */ MediaController this$0;

    MediaController$26(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (MediaController.access$000(this.this$0) != null) {
            DataHelper dataHelper = new DataHelper(MediaController.access$000(this.this$0));
            short[] sArr = null;
            while (sArr == null) {
                dataHelper.f396b = false;
                if (TurboConfig.voiceChangerType == 2) {
                    MediaController.access$7500(this.this$0).processFrame(MediaController.access$7400(this.this$0), dataHelper);
                    RobotizeProcessor.processFrame(MediaController.access$7400(this.this$0));
                    sArr = MediaController.access$7600(this.this$0).processFrame(MediaController.access$7400(this.this$0));
                } else if (TurboConfig.voiceChangerType == 3) {
                    MediaController.access$7500(this.this$0).processFrame(MediaController.access$7400(this.this$0), dataHelper);
                    DetuneProcessor.processFrame(MediaController.access$7400(this.this$0));
                    sArr = MediaController.access$7600(this.this$0).processFrame(MediaController.access$7400(this.this$0));
                } else if (TurboConfig.voiceChangerType == 4) {
                    MediaController.access$7500(this.this$0).processFrame(MediaController.access$7400(this.this$0), dataHelper);
                    HoarsenessProcessor.processFrame(MediaController.access$7400(this.this$0));
                    sArr = MediaController.access$7600(this.this$0).processFrame(MediaController.access$7400(this.this$0));
                } else if (TurboConfig.voiceChangerType == 5) {
                    MediaController.access$7500(this.this$0).processFrame(MediaController.access$7700(this.this$0), dataHelper);
                    MediaController.access$7800(this.this$0).processFrame(MediaController.access$7700(this.this$0));
                    MediaController.access$7900(this.this$0).ifft(MediaController.access$7700(this.this$0));
                    MediaController.access$8100(this.this$0).processFrame(MediaController.access$7700(this.this$0), MediaController.access$8000(this.this$0));
                    sArr = MediaController.access$7600(this.this$0).processFrame(MediaController.access$8000(this.this$0));
                }
                if (sArr == null && dataHelper.f395a < 0 && dataHelper.f396b) {
                    sArr = new short[0];
                    break;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(sArr.length * 2);
            buffer.order(ByteOrder.nativeOrder());
            buffer.rewind();
            buffer.limit(sArr.length * 2);
            for (short putShort : sArr) {
                buffer.putShort(putShort);
            }
            buffer.position(0);
            int b = dataHelper.f395a * 2;
            if (dataHelper.f395a == -1) {
                b = sArr.length * 2;
            }
            int len = Math.min(b, sArr.length * 2);
            if (len > 0) {
                buffer.limit(len);
                double sum = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                try {
                    float sampleStep;
                    long newSamplesCount = MediaController.access$300(this.this$0) + ((long) (len / 2));
                    int currentPart = (int) ((((double) MediaController.access$300(this.this$0)) / ((double) newSamplesCount)) * ((double) MediaController.access$400(this.this$0).length));
                    int newPart = MediaController.access$400(this.this$0).length - currentPart;
                    if (currentPart != 0) {
                        sampleStep = ((float) MediaController.access$400(this.this$0).length) / ((float) currentPart);
                        float currentNum = 0.0f;
                        for (int a = 0; a < currentPart; a++) {
                            MediaController.access$400(this.this$0)[a] = MediaController.access$400(this.this$0)[(int) currentNum];
                            currentNum += sampleStep;
                        }
                    }
                    int currentNum2 = currentPart;
                    float nextNum = 0.0f;
                    sampleStep = (((float) len) / 2.0f) / ((float) newPart);
                    for (int i = 0; i < len / 2; i++) {
                        short peak = buffer.getShort();
                        if (peak > (short) 2500) {
                            sum += (double) (peak * peak);
                        }
                        if (i == ((int) nextNum) && currentNum2 < MediaController.access$400(this.this$0).length) {
                            MediaController.access$400(this.this$0)[currentNum2] = peak;
                            nextNum += sampleStep;
                            currentNum2++;
                        }
                    }
                    MediaController.access$302(this.this$0, newSamplesCount);
                } catch (Exception e) {
                    sum = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    FileLog.e("tmessages", e);
                }
                buffer.position(0);
                final double amplitude = Math.sqrt((sum / ((double) len)) / 2.0d);
                final ByteBuffer finalBuffer = buffer;
                final boolean flush = len != buffer.capacity();
                if (len != 0) {
                    MediaController.access$900(this.this$0).postRunnable(new Runnable() {
                        public void run() {
                            while (finalBuffer.hasRemaining()) {
                                int oldLimit = -1;
                                if (finalBuffer.remaining() > MediaController.access$500(MediaController$26.this.this$0).remaining()) {
                                    oldLimit = finalBuffer.limit();
                                    finalBuffer.limit(MediaController.access$500(MediaController$26.this.this$0).remaining() + finalBuffer.position());
                                }
                                MediaController.access$500(MediaController$26.this.this$0).put(finalBuffer);
                                if (MediaController.access$500(MediaController$26.this.this$0).position() == MediaController.access$500(MediaController$26.this.this$0).limit() || flush) {
                                    if (MediaController.access$600(MediaController$26.this.this$0, MediaController.access$500(MediaController$26.this.this$0), !flush ? MediaController.access$500(MediaController$26.this.this$0).limit() : finalBuffer.position()) != 0) {
                                        MediaController.access$500(MediaController$26.this.this$0).rewind();
                                        MediaController.access$702(MediaController$26.this.this$0, MediaController.access$700(MediaController$26.this.this$0) + ((long) ((MediaController.access$500(MediaController$26.this.this$0).limit() / 2) / 16)));
                                    }
                                }
                                if (oldLimit != -1) {
                                    finalBuffer.limit(oldLimit);
                                }
                            }
                        }
                    });
                }
                MediaController.access$800(this.this$0).postRunnable(MediaController.access$6100(this.this$0));
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        NotificationCenter.getInstance(MediaController.access$1200(MediaController$26.this.this$0)).postNotificationName(NotificationCenter.recordProgressChanged, Long.valueOf(System.currentTimeMillis() - MediaController.access$1100(MediaController$26.this.this$0)), Double.valueOf(amplitude));
                    }
                });
                return;
            }
            MediaController.access$1400(this.this$0, MediaController.access$1300(this.this$0));
        }
    }
}
