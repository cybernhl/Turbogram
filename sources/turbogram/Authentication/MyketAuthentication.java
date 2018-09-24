package turbogram.Authentication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.provider.Settings.Secure;
import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.MyketServerManagedPolicy;
import turbogram.Utilities.TurboConfig;

public class MyketAuthentication {
    private static final byte[] SALT = new byte[]{(byte) -24, (byte) 53, (byte) 32, (byte) -78, (byte) -103, (byte) -75, (byte) 62, (byte) -49, (byte) 46, (byte) 38, (byte) -19, (byte) -98, (byte) 19, (byte) -107, (byte) -100, (byte) -41, (byte) -55, (byte) 57, (byte) -96, (byte) 12};
    private static Activity activity;
    private static boolean hideDialog = false;
    private static LicenseChecker mChecker;
    private static Handler mHandler;
    private static LicenseCheckerCallback mLicenseCheckerCallback;

    /* renamed from: turbogram.Authentication.MyketAuthentication$1 */
    static class C22201 implements OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            MyketAuthentication.doCheck();
        }
    }

    /* renamed from: turbogram.Authentication.MyketAuthentication$7 */
    static class C22217 implements Runnable {
        C22217() {
        }

        public void run() {
            MyketAuthentication.activity.setProgressBarIndeterminateVisibility(false);
        }
    }

    private static class MyLicenseCheckerCallback implements LicenseCheckerCallback {
        private MyLicenseCheckerCallback() {
        }

        public void allow(int policyReason) {
            if (!MyketAuthentication.activity.isFinishing()) {
                MyketAuthentication.hideProgress();
                TurboConfig.setBooleanValue("is_premium", true);
            }
        }

        public void dontAllow(int policyReason) {
            if (!MyketAuthentication.activity.isFinishing()) {
                MyketAuthentication.hideProgress();
                TurboConfig.setBooleanValue("is_premium", false);
            }
        }

        public void applicationError(int errorCode) {
            if (!MyketAuthentication.activity.isFinishing()) {
                MyketAuthentication.hideProgress();
            }
        }
    }

    public static void initServices(Activity _activity) {
        if (!TurboConfig.isPremium) {
            activity = _activity;
            initLicenseCheckerService();
        }
    }

    public static void releaseServices() {
        if (mChecker != null) {
            mChecker.onDestroy();
        }
    }

    private static void initLicenseCheckerService() {
        mHandler = new Handler();
        String deviceId = Secure.getString(activity.getContentResolver(), "android_id");
        mLicenseCheckerCallback = new MyLicenseCheckerCallback();
        mChecker = new LicenseChecker(activity, new MyketServerManagedPolicy(activity, new AESObfuscator(SALT, activity.getPackageName(), deviceId)), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCz+GrRT0VjFukiLtZxKH3Reg4z8BYkCD3dTfdVS6VK+3ai5uO4BTT9FwETLS16uwW+KDpmAkvlU74/J1aaF8cHk95aA9E5NphA1PUDGjNFM+27nm3RFhFJssvfU4x+KaGIPxHGtdray9s7T77zLgDRXCbsnGf1d5KkcrZTOYd1HQIDAQAB");
        doCheck();
    }

    private static void doCheck() {
        activity.setProgressBarIndeterminateVisibility(true);
        mChecker.checkAccess(mLicenseCheckerCallback);
    }

    private static void hideProgress() {
        mHandler.post(new C22217());
    }
}
