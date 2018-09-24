package me.cheshmak.android.sdk.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import me.cheshmak.android.sdk.C0451R;

public class DualSelectorView extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    TextView f774a;
    /* renamed from: b */
    TextView f775b;
    /* renamed from: c */
    private int f776c = 0;
    /* renamed from: d */
    private C0599a f777d;

    /* renamed from: me.cheshmak.android.sdk.core.view.DualSelectorView$a */
    public interface C0599a {
        /* renamed from: a */
        void mo4415a();

        /* renamed from: b */
        void mo4416b();
    }

    public DualSelectorView(Context context) {
        super(context);
        m1218a();
    }

    public DualSelectorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1218a();
    }

    /* renamed from: a */
    private void m1218a() {
        inflate(getContext(), C0451R.layout.ches_view_dual_selector, this);
        this.f774a = (TextView) findViewById(C0451R.id.first_option);
        this.f775b = (TextView) findViewById(C0451R.id.second_option);
    }

    /* renamed from: a */
    public DualSelectorView m1219a(C0599a c0599a) {
        this.f777d = c0599a;
        return this;
    }

    /* renamed from: a */
    public void m1220a(int i, int i2) {
        this.f774a.setText(i);
        this.f775b.setText(i2);
    }

    public int getOptionSelected() {
        return this.f776c;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == C0451R.id.first_option) {
            this.f776c = 0;
            this.f774a.setSelected(true);
            this.f775b.setSelected(false);
            if (this.f777d != null) {
                this.f777d.mo4415a();
            }
        } else if (id == C0451R.id.second_option) {
            this.f776c = 1;
            this.f774a.setSelected(false);
            this.f775b.setSelected(true);
            if (this.f777d != null) {
                this.f777d.mo4416b();
            }
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f776c = 0;
        this.f774a.setSelected(true);
        this.f775b.setSelected(false);
        this.f774a.setOnClickListener(this);
        this.f775b.setOnClickListener(this);
    }

    public void setDefaultSelected(int i) {
        switch (i) {
            case 0:
                this.f776c = 0;
                this.f774a.setSelected(true);
                this.f775b.setSelected(false);
                return;
            case 1:
                this.f776c = 1;
                this.f774a.setSelected(false);
                this.f775b.setSelected(true);
                return;
            default:
                return;
        }
    }
}
