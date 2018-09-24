package org.telegram.ui.ActionBar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MediaController;

class Theme$7 implements SensorEventListener {
    Theme$7() {
    }

    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        if (lux <= 0.0f) {
            lux = 0.1f;
        }
        if (!ApplicationLoader.mainInterfacePaused && ApplicationLoader.isScreenOn) {
            if (lux > 500.0f) {
                Theme.access$502(1.0f);
            } else {
                Theme.access$502(((float) Math.ceil((9.932299613952637d * Math.log((double) lux)) + 27.05900001525879d)) / 100.0f);
            }
            if (Theme.access$500() > Theme.autoNightBrighnessThreshold) {
                if (Theme.access$200()) {
                    Theme.access$202(false);
                    AndroidUtilities.cancelRunOnUIThread(Theme.access$700());
                }
                if (!Theme.access$000()) {
                    Theme.access$002(true);
                    AndroidUtilities.runOnUIThread(Theme.access$600(), Theme.access$800());
                }
            } else if (!MediaController.getInstance().isRecordingOrListeningByProximity()) {
                if (Theme.access$000()) {
                    Theme.access$002(false);
                    AndroidUtilities.cancelRunOnUIThread(Theme.access$600());
                }
                if (!Theme.access$200()) {
                    Theme.access$202(true);
                    AndroidUtilities.runOnUIThread(Theme.access$700(), Theme.access$800());
                }
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
