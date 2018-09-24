package org.telegram.ui.ActionBar;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.baranak.turbogramf.R;
import java.io.File;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;

class Theme$8 implements Runnable {
    final /* synthetic */ ArrayList val$wallpapers;

    /* renamed from: org.telegram.ui.ActionBar.Theme$8$1 */
    class C10071 implements Runnable {
        C10071() {
        }

        public void run() {
            NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
        }
    }

    Theme$8(ArrayList arrayList) {
        this.val$wallpapers = arrayList;
    }

    public void run() {
        synchronized (Theme.access$900()) {
            for (int i = 0; i < this.val$wallpapers.size(); i++) {
                long dialog_id = ((Long) this.val$wallpapers.get(i)).longValue();
                try {
                    File toFile = new File(ApplicationLoader.getFilesDirFixed(), "wallpaper" + dialog_id + ".jpg");
                    if (toFile.exists()) {
                        Theme.access$1000().put(Long.valueOf(dialog_id), Drawable.createFromPath(toFile.getAbsolutePath()));
                    } else {
                        Theme.access$1000().put(Long.valueOf(dialog_id), ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.background_hd));
                    }
                } catch (Throwable th) {
                }
                if (Theme.access$1000().get(Long.valueOf(dialog_id)) == null) {
                    Theme.access$1000().put(Long.valueOf(dialog_id), new ColorDrawable(Theme.access$1100()));
                }
            }
            AndroidUtilities.runOnUIThread(new C10071());
        }
    }
}
