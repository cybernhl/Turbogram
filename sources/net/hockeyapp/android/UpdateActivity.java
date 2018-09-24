package net.hockeyapp.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class UpdateActivity extends Activity {
    public static final String FRAGMENT_CLASS = "fragmentClass";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            } else {
                getFragmentManager().beginTransaction().add(16908290, Fragment.instantiate(this, extras.getString(FRAGMENT_CLASS, UpdateFragment.class.getName()), extras), UpdateFragment.FRAGMENT_TAG).commit();
            }
        }
        setTitle(C0625R.string.hockeyapp_update_title);
    }
}
