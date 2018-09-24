package turbogram;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import turbogram.Utilities.TurboConfig;

public class AMSettingsActivity extends BaseFragment {
    private EditText msgTextView;
    private ScrollView scrollView;

    /* renamed from: turbogram.AMSettingsActivity$1 */
    class C22181 extends ActionBarMenuOnItemClick {
        C22181() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                AMSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.AMSettingsActivity$2 */
    class C22192 implements OnClickListener {
        C22192() {
        }

        public void onClick(View v) {
            boolean z;
            boolean z2 = true;
            boolean answeringMachine = TurboConfig.answeringMachine;
            String str = "answering_machine";
            if (answeringMachine) {
                z = false;
            } else {
                z = true;
            }
            TurboConfig.setBooleanValue(str, z);
            if (v instanceof TextCheckCell) {
                TextCheckCell textCheckCell = (TextCheckCell) v;
                if (answeringMachine) {
                    z2 = false;
                }
                textCheckCell.setChecked(z2);
            }
        }
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        TurboConfig.setStringValue("answering_machine_msg", this.msgTextView.getText().toString());
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString("AnsweringMachine", R.string.AnsweringMachine));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setActionBarMenuOnItemClick(new C22181());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.scrollView = new ScrollView(context);
        this.scrollView.setFillViewport(true);
        frameLayout.addView(this.scrollView);
        LayoutParams scrollLayoutParams = (LayoutParams) this.scrollView.getLayoutParams();
        scrollLayoutParams.width = -1;
        scrollLayoutParams.height = -1;
        this.scrollView.setLayoutParams(scrollLayoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.scrollView.addView(linearLayout);
        LayoutParams params1 = (LayoutParams) linearLayout.getLayoutParams();
        params1.width = -1;
        params1.height = -1;
        linearLayout.setLayoutParams(params1);
        TextCheckCell enableCheck = new TextCheckCell(context);
        enableCheck.setBackgroundColor(-1);
        enableCheck.setTextAndCheck(LocaleController.getString("EnableAnsweringMachine", R.string.EnableAnsweringMachine), TurboConfig.answeringMachine, true);
        enableCheck.setOnClickListener(new C22192());
        enableCheck.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        linearLayout.addView(enableCheck);
        this.msgTextView = new EditText(context);
        this.msgTextView.setHint(LocaleController.getString("AnsweringMachineHint", R.string.AnsweringMachineHint));
        this.msgTextView.setText(TurboConfig.answeringMachineMsg);
        this.msgTextView.setSelection(this.msgTextView.getText().length());
        this.msgTextView.setTextSize(1, 16.0f);
        this.msgTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.msgTextView.setPadding(0, AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f));
        linearLayout.addView(this.msgTextView);
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) this.msgTextView.getLayoutParams();
        params2.width = -1;
        params2.height = -2;
        params2.gravity = 1;
        params2.topMargin = AndroidUtilities.dp(15.0f);
        params2.leftMargin = AndroidUtilities.dp(15.0f);
        params2.rightMargin = AndroidUtilities.dp(15.0f);
        this.msgTextView.setLayoutParams(params2);
        this.msgTextView.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.msgTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        return this.fragmentView;
    }
}
