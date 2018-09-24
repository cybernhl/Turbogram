package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.internal.wallet.zzam;
import com.google.android.gms.internal.wallet.zzn;
import com.google.android.gms.internal.wallet.zzr;
import com.google.android.gms.wallet.C0391R;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class SupportWalletFragment extends Fragment {
    private final Fragment fragment = this;
    private zzb zzfd;
    private boolean zzfe = false;
    private final SupportFragmentWrapper zzff = SupportFragmentWrapper.wrap(this);
    private final zzc zzfg = new zzc();
    private zza zzfh = new zza(this);
    private WalletFragmentOptions zzfi;
    private WalletFragmentInitParams zzfj;
    private MaskedWalletRequest zzfk;
    private MaskedWallet zzfl;
    private Boolean zzfm;

    public interface OnStateChangedListener {
        void onStateChanged(SupportWalletFragment supportWalletFragment, int i, int i2, Bundle bundle);
    }

    static class zza extends zzr {
        private OnStateChangedListener zzfn;
        private final SupportWalletFragment zzfo;

        zza(SupportWalletFragment supportWalletFragment) {
            this.zzfo = supportWalletFragment;
        }

        public final void zza(int i, int i2, Bundle bundle) {
            if (this.zzfn != null) {
                this.zzfn.onStateChanged(this.zzfo, i, i2, bundle);
            }
        }

        public final void zza(OnStateChangedListener onStateChangedListener) {
            this.zzfn = onStateChangedListener;
        }
    }

    private static class zzb implements LifecycleDelegate {
        private final zzn zzfp;

        private zzb(zzn zzn) {
            this.zzfp = zzn;
        }

        private final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.zzfp.initialize(walletFragmentInitParams);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        private final void setEnabled(boolean z) {
            try {
                this.zzfp.setEnabled(z);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        private final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            try {
                this.zzfp.updateMaskedWalletRequest(maskedWalletRequest);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        private final void updateMaskedWallet(MaskedWallet maskedWallet) {
            try {
                this.zzfp.updateMaskedWallet(maskedWallet);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        private final int getState() {
            try {
                return this.zzfp.getState();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzfp.zza(ObjectWrapper.wrap(activity), (WalletFragmentOptions) bundle.getParcelable("extraWalletFragmentOptions"), bundle2);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                this.zzfp.onCreate(bundle);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) ObjectWrapper.unwrap(this.zzfp.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzfp.onStart();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzfp.onResume();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onPause() {
            try {
                this.zzfp.onPause();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzfp.onStop();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public final void onDestroyView() {
        }

        public final void onDestroy() {
        }

        public final void onLowMemory() {
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzfp.onSaveInstanceState(bundle);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        private final void onActivityResult(int i, int i2, Intent intent) {
            try {
                this.zzfp.onActivityResult(i, i2, intent);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class zzc extends DeferredLifecycleHelper<zzb> implements OnClickListener {
        private final /* synthetic */ SupportWalletFragment zzfq;

        private zzc(SupportWalletFragment supportWalletFragment) {
            this.zzfq = supportWalletFragment;
        }

        protected final void createDelegate(OnDelegateCreatedListener<zzb> onDelegateCreatedListener) {
            FragmentActivity activity = this.zzfq.fragment.getActivity();
            if (this.zzfq.zzfd == null && this.zzfq.zzfe && activity != null) {
                try {
                    this.zzfq.zzfd = new zzb(zzam.zza(activity, this.zzfq.zzff, this.zzfq.zzfi, this.zzfq.zzfh));
                    this.zzfq.zzfi = null;
                    onDelegateCreatedListener.onDelegateCreated(this.zzfq.zzfd);
                    if (this.zzfq.zzfj != null) {
                        this.zzfq.zzfd.initialize(this.zzfq.zzfj);
                        this.zzfq.zzfj = null;
                    }
                    if (this.zzfq.zzfk != null) {
                        this.zzfq.zzfd.updateMaskedWalletRequest(this.zzfq.zzfk);
                        this.zzfq.zzfk = null;
                    }
                    if (this.zzfq.zzfl != null) {
                        this.zzfq.zzfd.updateMaskedWallet(this.zzfq.zzfl);
                        this.zzfq.zzfl = null;
                    }
                    if (this.zzfq.zzfm != null) {
                        this.zzfq.zzfd.setEnabled(this.zzfq.zzfm.booleanValue());
                        this.zzfq.zzfm = null;
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        }

        protected final void handleGooglePlayUnavailable(FrameLayout frameLayout) {
            int i = -1;
            int i2 = -2;
            View button = new Button(this.zzfq.fragment.getActivity());
            button.setText(C0391R.string.wallet_buy_button_place_holder);
            if (this.zzfq.zzfi != null) {
                WalletFragmentStyle fragmentStyle = this.zzfq.zzfi.getFragmentStyle();
                if (fragmentStyle != null) {
                    DisplayMetrics displayMetrics = this.zzfq.fragment.getResources().getDisplayMetrics();
                    i = fragmentStyle.zza("buyButtonWidth", displayMetrics, -1);
                    i2 = fragmentStyle.zza("buyButtonHeight", displayMetrics, -2);
                }
            }
            button.setLayoutParams(new LayoutParams(i, i2));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        public final void onClick(View view) {
            FragmentActivity activity = this.zzfq.fragment.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(activity, 12451000), activity, -1);
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        supportWalletFragment.fragment.setArguments(bundle);
        return supportWalletFragment;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        if (this.zzfd != null) {
            this.zzfd.initialize(walletFragmentInitParams);
            this.zzfj = null;
        } else if (this.zzfj == null) {
            this.zzfj = walletFragmentInitParams;
            if (this.zzfk != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzfl != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        if (this.zzfd != null) {
            this.zzfd.updateMaskedWalletRequest(maskedWalletRequest);
            this.zzfk = null;
            return;
        }
        this.zzfk = maskedWalletRequest;
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.zzfd != null) {
            this.zzfd.updateMaskedWallet(maskedWallet);
            this.zzfl = null;
            return;
        }
        this.zzfl = maskedWallet;
    }

    public final void setEnabled(boolean z) {
        if (this.zzfd != null) {
            this.zzfd.setEnabled(z);
            this.zzfm = null;
            return;
        }
        this.zzfm = Boolean.valueOf(z);
    }

    public final void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.zzfh.zza(onStateChangedListener);
    }

    public final int getState() {
        return this.zzfd != null ? this.zzfd.getState() : 0;
    }

    public final void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        if (this.zzfi == null) {
            this.zzfi = WalletFragmentOptions.zza((Context) activity, attributeSet);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", this.zzfi);
        this.zzfg.onInflate(activity, bundle2, bundle);
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) bundle.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.zzfj != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.zzfj = walletFragmentInitParams;
            }
            if (this.zzfk == null) {
                this.zzfk = (MaskedWalletRequest) bundle.getParcelable("maskedWalletRequest");
            }
            if (this.zzfl == null) {
                this.zzfl = (MaskedWallet) bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.zzfi = (WalletFragmentOptions) bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey("enabled")) {
                this.zzfm = Boolean.valueOf(bundle.getBoolean("enabled"));
            }
        } else if (this.fragment.getArguments() != null) {
            WalletFragmentOptions walletFragmentOptions = (WalletFragmentOptions) this.fragment.getArguments().getParcelable("extraWalletFragmentOptions");
            if (walletFragmentOptions != null) {
                walletFragmentOptions.zza(this.fragment.getActivity());
                this.zzfi = walletFragmentOptions;
            }
        }
        this.zzfe = true;
        this.zzfg.onCreate(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzfg.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public final void onStart() {
        super.onStart();
        this.zzfg.onStart();
    }

    public final void onResume() {
        super.onResume();
        this.zzfg.onResume();
        FragmentManager supportFragmentManager = this.fragment.getActivity().getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(this.fragment.getActivity(), 12451000), this.fragment.getActivity(), -1);
        }
    }

    public final void onPause() {
        super.onPause();
        this.zzfg.onPause();
    }

    public final void onStop() {
        super.onStop();
        this.zzfg.onStop();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzfg.onSaveInstanceState(bundle);
        if (this.zzfj != null) {
            bundle.putParcelable("walletFragmentInitParams", this.zzfj);
            this.zzfj = null;
        }
        if (this.zzfk != null) {
            bundle.putParcelable("maskedWalletRequest", this.zzfk);
            this.zzfk = null;
        }
        if (this.zzfl != null) {
            bundle.putParcelable("maskedWallet", this.zzfl);
            this.zzfl = null;
        }
        if (this.zzfi != null) {
            bundle.putParcelable("walletFragmentOptions", this.zzfi);
            this.zzfi = null;
        }
        if (this.zzfm != null) {
            bundle.putBoolean("enabled", this.zzfm.booleanValue());
            this.zzfm = null;
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzfe = false;
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.zzfd != null) {
            this.zzfd.onActivityResult(i, i2, intent);
        }
    }
}
