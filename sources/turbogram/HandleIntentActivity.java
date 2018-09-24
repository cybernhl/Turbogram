package turbogram;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.baranak.turbogramf.R;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.LaunchActivity;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class HandleIntentActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        handleIntent(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private boolean handleIntent(Intent intent) {
        if (TurboConfig.adsBlocker) {
            int flags = intent.getFlags();
            if (UserConfig.getInstance(UserConfig.selectedAccount).isClientActivated() && (1048576 & flags) == 0 && intent != null && intent.getAction() != null && "android.intent.action.VIEW".equals(intent.getAction())) {
                Uri data = intent.getData();
                if (data != null) {
                    String scheme = data.getScheme();
                    if (scheme != null && scheme.equals("tg")) {
                        String url = data.toString();
                        if (url.startsWith("tg:resolve") || url.startsWith("tg://resolve") || url.startsWith("tg:join") || url.startsWith("tg://join")) {
                            TurboUtils.showToast(this, LocaleController.getString("AnAdvBlocked", R.string.AnAdvBlocked), 0);
                        }
                    }
                }
            }
        } else {
            intent.setClass(this, LaunchActivity.class);
            startActivity(intent);
        }
        finish();
        return false;
    }
}
