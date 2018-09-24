package org.telegram.messenger;

import android.hardware.SensorManager;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import org.telegram.ui.Components.EmbedBottomSheet;

class MediaController$4 implements Runnable {
    final /* synthetic */ MediaController this$0;

    /* renamed from: org.telegram.messenger.MediaController$4$1 */
    class C08491 extends PhoneStateListener {
        C08491() {
        }

        public void onCallStateChanged(final int state, String incomingNumber) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    EmbedBottomSheet embedBottomSheet;
                    if (state == 1) {
                        if (MediaController$4.this.this$0.isPlayingMessage(MediaController.access$2900(MediaController$4.this.this$0)) && !MediaController$4.this.this$0.isMessagePaused()) {
                            MediaController$4.this.this$0.pauseMessage(MediaController.access$2900(MediaController$4.this.this$0));
                        } else if (!(MediaController.access$3000(MediaController$4.this.this$0) == null && MediaController.access$3100(MediaController$4.this.this$0) == null)) {
                            MediaController$4.this.this$0.stopRecording(2);
                        }
                        embedBottomSheet = EmbedBottomSheet.getInstance();
                        if (embedBottomSheet != null) {
                            embedBottomSheet.pause();
                        }
                        MediaController.access$3202(MediaController$4.this.this$0, true);
                    } else if (state == 0) {
                        MediaController.access$3202(MediaController$4.this.this$0, false);
                    } else if (state == 2) {
                        embedBottomSheet = EmbedBottomSheet.getInstance();
                        if (embedBottomSheet != null) {
                            embedBottomSheet.pause();
                        }
                        MediaController.access$3202(MediaController$4.this.this$0, true);
                    }
                }
            });
        }
    }

    MediaController$4(MediaController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        try {
            MediaController.access$2202(this.this$0, MessagesController.getGlobalMainSettings().getFloat("playbackSpeed", 1.0f));
            MediaController.access$2302(this.this$0, (SensorManager) ApplicationLoader.applicationContext.getSystemService("sensor"));
            MediaController.access$2402(this.this$0, MediaController.access$2300(this.this$0).getDefaultSensor(10));
            MediaController.access$2502(this.this$0, MediaController.access$2300(this.this$0).getDefaultSensor(9));
            if (MediaController.access$2400(this.this$0) == null || MediaController.access$2500(this.this$0) == null) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("gravity or linear sensor not found");
                }
                MediaController.access$2602(this.this$0, MediaController.access$2300(this.this$0).getDefaultSensor(1));
                MediaController.access$2402(this.this$0, null);
                MediaController.access$2502(this.this$0, null);
            }
            MediaController.access$2702(this.this$0, MediaController.access$2300(this.this$0).getDefaultSensor(8));
            MediaController.access$2802(this.this$0, ((PowerManager) ApplicationLoader.applicationContext.getSystemService("power")).newWakeLock(32, "proximity"));
        } catch (Exception e) {
            FileLog.e(e);
        }
        try {
            PhoneStateListener phoneStateListener = new C08491();
            TelephonyManager mgr = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");
            if (mgr != null) {
                mgr.listen(phoneStateListener, 32);
            }
        } catch (Exception e2) {
            FileLog.e(e2);
        }
    }
}
