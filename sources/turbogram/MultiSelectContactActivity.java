package turbogram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.baranak.turbogramf.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.ItemDecoration;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Adapters.SearchAdapterHelper.HashtagObject;
import org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate;
import org.telegram.ui.Cells.GroupCreateSectionCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.GroupCreateDividerItemDecoration;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.FastScrollAdapter;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import turbogram.Utilities.TurboConfig;

public class MultiSelectContactActivity extends BaseFragment implements OnClickListener, NotificationCenter$NotificationCenterDelegate {
    private DeleteContactsAdapter adapter;
    private ArrayList<GroupCreateSpan> allSpans = new ArrayList();
    private int chatId;
    private int chatType = 0;
    private int containerHeight;
    private GroupCreateSpan currentDeletingSpan;
    private AnimatorSet currentDoneButtonAnimation;
    private View doneButton;
    private boolean doneButtonVisible;
    private EditTextBoldCursor editText;
    private EmptyTextProgressView emptyView;
    private int fieldY;
    private boolean ignoreScrollEvent;
    private boolean isAlwaysShare;
    private boolean isGroup;
    private boolean isNeverShare;
    private GroupCreateDividerItemDecoration itemDecoration;
    private RecyclerListView listView;
    private int maxCount = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS;
    private ScrollView scrollView;
    private boolean searchWas;
    private boolean searching;
    private HashMap<Integer, GroupCreateSpan> selectedContacts = new HashMap();
    ArrayList<User> selectedUsers = new ArrayList();
    private SpansContainer spansContainer;
    private int type = 1;

    /* renamed from: turbogram.MultiSelectContactActivity$1 */
    class C23711 extends ActionBarMenuOnItemClick {
        C23711() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                MultiSelectContactActivity.this.finishFragment();
            } else if (id == 1) {
                MultiSelectContactActivity.this.onDonePressed();
            }
        }
    }

    /* renamed from: turbogram.MultiSelectContactActivity$5 */
    class C23755 implements Callback {
        C23755() {
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }
    }

    /* renamed from: turbogram.MultiSelectContactActivity$6 */
    class C23766 implements OnEditorActionListener {
        C23766() {
        }

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            return actionId == 6 && MultiSelectContactActivity.this.onDonePressed();
        }
    }

    /* renamed from: turbogram.MultiSelectContactActivity$7 */
    class C23777 implements OnKeyListener {
        C23777() {
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode != 67 || event.getAction() != 1 || MultiSelectContactActivity.this.editText.length() != 0 || MultiSelectContactActivity.this.allSpans.isEmpty()) {
                return false;
            }
            MultiSelectContactActivity.this.spansContainer.removeSpan((GroupCreateSpan) MultiSelectContactActivity.this.allSpans.get(MultiSelectContactActivity.this.allSpans.size() - 1));
            MultiSelectContactActivity.this.updateHint();
            MultiSelectContactActivity.this.checkVisibleRows();
            return true;
        }
    }

    /* renamed from: turbogram.MultiSelectContactActivity$8 */
    class C23788 implements TextWatcher {
        C23788() {
        }

        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (MultiSelectContactActivity.this.editText.length() != 0) {
                MultiSelectContactActivity.this.searching = true;
                MultiSelectContactActivity.this.searchWas = true;
                MultiSelectContactActivity.this.adapter.setSearching(true);
                MultiSelectContactActivity.this.itemDecoration.setSearching(true);
                MultiSelectContactActivity.this.adapter.searchDialogs(MultiSelectContactActivity.this.editText.getText().toString());
                MultiSelectContactActivity.this.listView.setFastScrollVisible(false);
                MultiSelectContactActivity.this.listView.setVerticalScrollBarEnabled(true);
                MultiSelectContactActivity.this.emptyView.setText(LocaleController.getString("NoResult", R.string.NoResult));
                return;
            }
            MultiSelectContactActivity.this.closeSearch();
        }
    }

    /* renamed from: turbogram.MultiSelectContactActivity$9 */
    class C23799 implements OnItemClickListener {
        C23799() {
        }

        public void onItemClick(View view, int position) {
            boolean z = false;
            if (view instanceof GroupCreateUserCell) {
                GroupCreateUserCell cell = (GroupCreateUserCell) view;
                User user = cell.getUser();
                if (user != null) {
                    boolean exists = MultiSelectContactActivity.this.selectedContacts.containsKey(Integer.valueOf(user.id));
                    if (exists) {
                        MultiSelectContactActivity.this.spansContainer.removeSpan((GroupCreateSpan) MultiSelectContactActivity.this.selectedContacts.get(Integer.valueOf(user.id)));
                        MultiSelectContactActivity.this.selectedUsers.remove(user);
                    } else if (MultiSelectContactActivity.this.maxCount != 0 && MultiSelectContactActivity.this.selectedContacts.size() == MultiSelectContactActivity.this.maxCount) {
                        return;
                    } else {
                        if (MultiSelectContactActivity.this.chatType == 0 && MultiSelectContactActivity.this.selectedContacts.size() == MessagesController.getInstance(MultiSelectContactActivity.this.currentAccount).maxGroupCount) {
                            Builder builder = new Builder(MultiSelectContactActivity.this.getParentActivity());
                            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                            builder.setMessage(LocaleController.getString("SoftUserLimitAlert", R.string.SoftUserLimitAlert));
                            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                            MultiSelectContactActivity.this.showDialog(builder.create());
                            return;
                        }
                        boolean z2;
                        MessagesController instance = MessagesController.getInstance(MultiSelectContactActivity.this.currentAccount);
                        if (MultiSelectContactActivity.this.searching) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        instance.putUser(user, z2);
                        MultiSelectContactActivity.this.selectedUsers.add(user);
                        GroupCreateSpan span = new GroupCreateSpan(MultiSelectContactActivity.this.editText.getContext(), user);
                        MultiSelectContactActivity.this.spansContainer.addSpan(span);
                        span.setOnClickListener(MultiSelectContactActivity.this);
                    }
                    MultiSelectContactActivity.this.updateHint();
                    if (MultiSelectContactActivity.this.searching || MultiSelectContactActivity.this.searchWas) {
                        AndroidUtilities.showKeyboard(MultiSelectContactActivity.this.editText);
                    } else {
                        if (!exists) {
                            z = true;
                        }
                        cell.setChecked(z, true);
                    }
                    if (MultiSelectContactActivity.this.editText.length() > 0) {
                        MultiSelectContactActivity.this.editText.setText(null);
                    }
                }
            }
        }
    }

    public class DeleteContactsAdapter extends FastScrollAdapter {
        private ArrayList<User> contacts = new ArrayList();
        private Context context;
        private SearchAdapterHelper searchAdapterHelper;
        private ArrayList<User> searchResult = new ArrayList();
        private ArrayList<CharSequence> searchResultNames = new ArrayList();
        private Timer searchTimer;
        private boolean searching;

        public DeleteContactsAdapter(Context ctx) {
            this.context = ctx;
            ArrayList<TLRPC$TL_contact> arrayList = ContactsController.getInstance(MultiSelectContactActivity.this.currentAccount).contacts;
            for (int a = 0; a < arrayList.size(); a++) {
                User user = MessagesController.getInstance(MultiSelectContactActivity.this.currentAccount).getUser(Integer.valueOf(((TLRPC$TL_contact) arrayList.get(a)).user_id));
                if (!(user == null || user.self || user.deleted)) {
                    this.contacts.add(user);
                }
            }
            this.searchAdapterHelper = new SearchAdapterHelper(true);
            this.searchAdapterHelper.setDelegate(new SearchAdapterHelperDelegate(MultiSelectContactActivity.this) {
                public void onDataSetChanged() {
                    DeleteContactsAdapter.this.notifyDataSetChanged();
                }

                public void onSetHashtags(ArrayList<HashtagObject> arrayList, HashMap<String, HashtagObject> hashMap) {
                }
            });
        }

        public void setSearching(boolean value) {
            if (this.searching != value) {
                this.searching = value;
                notifyDataSetChanged();
            }
        }

        public String getLetter(int position) {
            if (position < 0 || position >= this.contacts.size()) {
                return null;
            }
            User user = (User) this.contacts.get(position);
            if (user == null) {
                return null;
            }
            if (LocaleController.nameDisplayOrder == 1) {
                if (!TextUtils.isEmpty(user.first_name)) {
                    return user.first_name.substring(0, 1).toUpperCase();
                }
                if (!TextUtils.isEmpty(user.last_name)) {
                    return user.last_name.substring(0, 1).toUpperCase();
                }
            } else if (!TextUtils.isEmpty(user.last_name)) {
                return user.last_name.substring(0, 1).toUpperCase();
            } else {
                if (!TextUtils.isEmpty(user.first_name)) {
                    return user.first_name.substring(0, 1).toUpperCase();
                }
            }
            return "";
        }

        public int getItemCount() {
            if (!this.searching) {
                return this.contacts.size();
            }
            int count = this.searchResult.size();
            int globalCount = this.searchAdapterHelper.getGlobalSearch().size();
            if (globalCount != 0) {
                return count + (globalCount + 1);
            }
            return count;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            switch (viewType) {
                case 0:
                    view = new GroupCreateSectionCell(this.context);
                    break;
                default:
                    view = new GroupCreateUserCell(this.context, true);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            User user;
            switch (holder.getItemViewType()) {
                case 0:
                    GroupCreateSectionCell cell = (GroupCreateSectionCell) holder.itemView;
                    if (this.searching) {
                        cell.setText(LocaleController.getString("GlobalSearch", R.string.GlobalSearch));
                        return;
                    }
                    return;
                default:
                    GroupCreateUserCell cell2 = holder.itemView;
                    CharSequence username = null;
                    CharSequence name = null;
                    if (this.searching) {
                        int localCount = this.searchResult.size();
                        int globalCount = this.searchAdapterHelper.getGlobalSearch().size();
                        if (position >= 0 && position < localCount) {
                            user = (User) this.searchResult.get(position);
                        } else if (position <= localCount || position > globalCount + localCount) {
                            user = null;
                        } else {
                            user = (User) this.searchAdapterHelper.getGlobalSearch().get((position - localCount) - 1);
                        }
                        if (user != null) {
                            if (position < localCount) {
                                name = (CharSequence) this.searchResultNames.get(position);
                                if (!(name == null || TextUtils.isEmpty(user.username) || !name.toString().startsWith("@" + user.username))) {
                                    username = name;
                                    name = null;
                                }
                            } else if (position > localCount && !TextUtils.isEmpty(user.username)) {
                                String foundUserName = this.searchAdapterHelper.getLastFoundUsername();
                                if (foundUserName.startsWith("@")) {
                                    foundUserName = foundUserName.substring(1);
                                }
                                try {
                                    CharSequence username2 = new SpannableStringBuilder(null);
                                    try {
                                        ((SpannableStringBuilder) username2).setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)), 0, foundUserName.length(), 33);
                                        username = username2;
                                    } catch (Exception e) {
                                        username = username2;
                                        username = user.username;
                                        cell2.setUser(user, name, username);
                                        cell2.setChecked(MultiSelectContactActivity.this.selectedContacts.containsKey(Integer.valueOf(user.id)), false);
                                        return;
                                    }
                                } catch (Exception e2) {
                                    username = user.username;
                                    cell2.setUser(user, name, username);
                                    cell2.setChecked(MultiSelectContactActivity.this.selectedContacts.containsKey(Integer.valueOf(user.id)), false);
                                    return;
                                }
                            }
                        }
                    }
                    user = (User) this.contacts.get(position);
                    cell2.setUser(user, name, username);
                    cell2.setChecked(MultiSelectContactActivity.this.selectedContacts.containsKey(Integer.valueOf(user.id)), false);
                    return;
            }
        }

        public int getItemViewType(int position) {
            if (this.searching && position == this.searchResult.size()) {
                return 0;
            }
            return 1;
        }

        public int getPositionForScrollProgress(float progress) {
            return (int) (((float) getItemCount()) * progress);
        }

        public void onViewRecycled(ViewHolder holder) {
            if (holder.getItemViewType() == 1) {
                ((GroupCreateUserCell) holder.itemView).recycle();
            }
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }

        public void searchDialogs(final String query) {
            try {
                if (this.searchTimer != null) {
                    this.searchTimer.cancel();
                }
            } catch (Exception e) {
                FileLog.e(e);
            }
            if (query == null) {
                this.searchResult.clear();
                this.searchResultNames.clear();
                this.searchAdapterHelper.queryServerSearch(null, false, false, false, false, 0, false);
                notifyDataSetChanged();
                return;
            }
            this.searchTimer = new Timer();
            this.searchTimer.schedule(new TimerTask() {

                /* renamed from: turbogram.MultiSelectContactActivity$DeleteContactsAdapter$2$1 */
                class C23821 implements Runnable {

                    /* renamed from: turbogram.MultiSelectContactActivity$DeleteContactsAdapter$2$1$1 */
                    class C23811 implements Runnable {
                        C23811() {
                        }

                        public void run() {
                            String search1 = query.trim().toLowerCase();
                            if (search1.length() == 0) {
                                DeleteContactsAdapter.this.updateSearchResults(new ArrayList(), new ArrayList());
                                return;
                            }
                            String search2 = LocaleController.getInstance().getTranslitString(search1);
                            if (search1.equals(search2) || search2.length() == 0) {
                                search2 = null;
                            }
                            String[] search = new String[((search2 != null ? 1 : 0) + 1)];
                            search[0] = search1;
                            if (search2 != null) {
                                search[1] = search2;
                            }
                            ArrayList<User> resultArray = new ArrayList();
                            ArrayList<CharSequence> resultArrayNames = new ArrayList();
                            for (int a = 0; a < DeleteContactsAdapter.this.contacts.size(); a++) {
                                User user = (User) DeleteContactsAdapter.this.contacts.get(a);
                                String name = ContactsController.formatName(user.first_name, user.last_name).toLowerCase();
                                String tName = LocaleController.getInstance().getTranslitString(name);
                                if (name.equals(tName)) {
                                    tName = null;
                                }
                                int found = 0;
                                int length = search.length;
                                int i = 0;
                                while (i < length) {
                                    String q = search[i];
                                    if (name.startsWith(q) || name.contains(" " + q) || (tName != null && (tName.startsWith(q) || tName.contains(" " + q)))) {
                                        found = 1;
                                    } else if (user.username != null && user.username.startsWith(q)) {
                                        found = 2;
                                    }
                                    if (found != 0) {
                                        if (found == 1) {
                                            resultArrayNames.add(AndroidUtilities.generateSearchName(user.first_name, user.last_name, q));
                                        } else {
                                            resultArrayNames.add(AndroidUtilities.generateSearchName("@" + user.username, null, "@" + q));
                                        }
                                        resultArray.add(user);
                                    } else {
                                        i++;
                                    }
                                }
                            }
                            DeleteContactsAdapter.this.updateSearchResults(resultArray, resultArrayNames);
                        }
                    }

                    C23821() {
                    }

                    public void run() {
                        DeleteContactsAdapter.this.searchAdapterHelper.queryServerSearch(query, false, false, false, false, 0, false);
                        Utilities.searchQueue.postRunnable(new C23811());
                    }
                }

                public void run() {
                    try {
                        DeleteContactsAdapter.this.searchTimer.cancel();
                        DeleteContactsAdapter.this.searchTimer = null;
                    } catch (Exception e) {
                        FileLog.e(e);
                    }
                    AndroidUtilities.runOnUIThread(new C23821());
                }
            }, 200, 300);
        }

        private void updateSearchResults(final ArrayList<User> users, final ArrayList<CharSequence> names) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    DeleteContactsAdapter.this.searchResult = users;
                    DeleteContactsAdapter.this.searchResultNames = names;
                    DeleteContactsAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    private class SpansContainer extends ViewGroup {
        private View addingSpan;
        private boolean animationStarted;
        private ArrayList<Animator> animators = new ArrayList();
        private AnimatorSet currentAnimation;
        private View removingSpan;

        /* renamed from: turbogram.MultiSelectContactActivity$SpansContainer$1 */
        class C23851 extends AnimatorListenerAdapter {
            C23851() {
            }

            public void onAnimationEnd(Animator animator) {
                SpansContainer.this.addingSpan = null;
                SpansContainer.this.currentAnimation = null;
                SpansContainer.this.animationStarted = false;
                MultiSelectContactActivity.this.editText.setAllowDrawCursor(true);
            }
        }

        public SpansContainer(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int minWidth;
            int count = getChildCount();
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int maxWidth = width - AndroidUtilities.dp(32.0f);
            int currentLineWidth = 0;
            int y = AndroidUtilities.dp(12.0f);
            int allCurrentLineWidth = 0;
            int allY = AndroidUtilities.dp(12.0f);
            for (int a = 0; a < count; a++) {
                View child = getChildAt(a);
                if (child instanceof GroupCreateSpan) {
                    child.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), 1073741824));
                    if (child != this.removingSpan && child.getMeasuredWidth() + currentLineWidth > maxWidth) {
                        y += child.getMeasuredHeight() + AndroidUtilities.dp(12.0f);
                        currentLineWidth = 0;
                    }
                    if (child.getMeasuredWidth() + allCurrentLineWidth > maxWidth) {
                        allY += child.getMeasuredHeight() + AndroidUtilities.dp(12.0f);
                        allCurrentLineWidth = 0;
                    }
                    int x = AndroidUtilities.dp(16.0f) + currentLineWidth;
                    if (!this.animationStarted) {
                        if (child == this.removingSpan) {
                            child.setTranslationX((float) (AndroidUtilities.dp(16.0f) + allCurrentLineWidth));
                            child.setTranslationY((float) allY);
                        } else if (this.removingSpan != null) {
                            if (child.getTranslationX() != ((float) x)) {
                                this.animators.add(ObjectAnimator.ofFloat(child, "translationX", new float[]{(float) x}));
                            }
                            if (child.getTranslationY() != ((float) y)) {
                                this.animators.add(ObjectAnimator.ofFloat(child, "translationY", new float[]{(float) y}));
                            }
                        } else {
                            child.setTranslationX((float) x);
                            child.setTranslationY((float) y);
                        }
                    }
                    if (child != this.removingSpan) {
                        currentLineWidth += child.getMeasuredWidth() + AndroidUtilities.dp(9.0f);
                    }
                    allCurrentLineWidth += child.getMeasuredWidth() + AndroidUtilities.dp(9.0f);
                }
            }
            if (AndroidUtilities.isTablet()) {
                minWidth = AndroidUtilities.dp(366.0f) / 3;
            } else {
                minWidth = (Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y) - AndroidUtilities.dp(164.0f)) / 3;
            }
            if (maxWidth - currentLineWidth < minWidth) {
                currentLineWidth = 0;
                y += AndroidUtilities.dp(44.0f);
            }
            if (maxWidth - allCurrentLineWidth < minWidth) {
                allY += AndroidUtilities.dp(44.0f);
            }
            MultiSelectContactActivity.this.editText.measure(MeasureSpec.makeMeasureSpec(maxWidth - currentLineWidth, 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), 1073741824));
            if (!this.animationStarted) {
                int currentHeight = allY + AndroidUtilities.dp(44.0f);
                int fieldX = currentLineWidth + AndroidUtilities.dp(16.0f);
                MultiSelectContactActivity.this.fieldY = y;
                if (this.currentAnimation != null) {
                    if (MultiSelectContactActivity.this.containerHeight != y + AndroidUtilities.dp(44.0f)) {
                        this.animators.add(ObjectAnimator.ofInt(MultiSelectContactActivity.this, "containerHeight", new int[]{resultHeight}));
                    }
                    if (MultiSelectContactActivity.this.editText.getTranslationX() != ((float) fieldX)) {
                        this.animators.add(ObjectAnimator.ofFloat(MultiSelectContactActivity.this.editText, "translationX", new float[]{(float) fieldX}));
                    }
                    if (MultiSelectContactActivity.this.editText.getTranslationY() != ((float) MultiSelectContactActivity.this.fieldY)) {
                        this.animators.add(ObjectAnimator.ofFloat(MultiSelectContactActivity.this.editText, "translationY", new float[]{(float) MultiSelectContactActivity.this.fieldY}));
                    }
                    MultiSelectContactActivity.this.editText.setAllowDrawCursor(false);
                    this.currentAnimation.playTogether(this.animators);
                    this.currentAnimation.start();
                    this.animationStarted = true;
                } else {
                    MultiSelectContactActivity.this.containerHeight = currentHeight;
                    MultiSelectContactActivity.this.editText.setTranslationX((float) fieldX);
                    MultiSelectContactActivity.this.editText.setTranslationY((float) MultiSelectContactActivity.this.fieldY);
                }
            } else if (!(this.currentAnimation == null || MultiSelectContactActivity.this.ignoreScrollEvent || this.removingSpan != null)) {
                MultiSelectContactActivity.this.editText.bringPointIntoView(MultiSelectContactActivity.this.editText.getSelectionStart());
            }
            setMeasuredDimension(width, MultiSelectContactActivity.this.containerHeight);
        }

        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            int count = getChildCount();
            for (int a = 0; a < count; a++) {
                View child = getChildAt(a);
                child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
            }
        }

        public void addSpan(GroupCreateSpan span) {
            MultiSelectContactActivity.this.allSpans.add(span);
            MultiSelectContactActivity.this.selectedContacts.put(Integer.valueOf(span.getUid()), span);
            MultiSelectContactActivity.this.editText.setHintVisible(false);
            if (this.currentAnimation != null) {
                this.currentAnimation.setupEndValues();
                this.currentAnimation.cancel();
            }
            this.animationStarted = false;
            this.currentAnimation = new AnimatorSet();
            this.currentAnimation.addListener(new C23851());
            this.currentAnimation.setDuration(150);
            this.addingSpan = span;
            this.animators.clear();
            this.animators.add(ObjectAnimator.ofFloat(this.addingSpan, "scaleX", new float[]{0.01f, 1.0f}));
            this.animators.add(ObjectAnimator.ofFloat(this.addingSpan, "scaleY", new float[]{0.01f, 1.0f}));
            this.animators.add(ObjectAnimator.ofFloat(this.addingSpan, "alpha", new float[]{0.0f, 1.0f}));
            addView(span);
        }

        public void removeSpan(final GroupCreateSpan span) {
            MultiSelectContactActivity.this.ignoreScrollEvent = true;
            MultiSelectContactActivity.this.selectedContacts.remove(Integer.valueOf(span.getUid()));
            MultiSelectContactActivity.this.allSpans.remove(span);
            span.setOnClickListener(null);
            if (this.currentAnimation != null) {
                this.currentAnimation.setupEndValues();
                this.currentAnimation.cancel();
            }
            this.animationStarted = false;
            this.currentAnimation = new AnimatorSet();
            this.currentAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    SpansContainer.this.removeView(span);
                    SpansContainer.this.removingSpan = null;
                    SpansContainer.this.currentAnimation = null;
                    SpansContainer.this.animationStarted = false;
                    MultiSelectContactActivity.this.editText.setAllowDrawCursor(true);
                    if (MultiSelectContactActivity.this.allSpans.isEmpty()) {
                        MultiSelectContactActivity.this.editText.setHintVisible(true);
                    }
                }
            });
            this.currentAnimation.setDuration(150);
            this.removingSpan = span;
            this.animators.clear();
            this.animators.add(ObjectAnimator.ofFloat(this.removingSpan, "scaleX", new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(this.removingSpan, "scaleY", new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(this.removingSpan, "alpha", new float[]{1.0f, 0.0f}));
            requestLayout();
        }
    }

    public MultiSelectContactActivity(Bundle args) {
        super(args);
        this.type = args.getInt(Param.TYPE, 1);
        this.chatType = args.getInt("chatType", 0);
        this.isAlwaysShare = args.getBoolean("isAlwaysShare", false);
        this.isNeverShare = args.getBoolean("isNeverShare", false);
        this.isGroup = args.getBoolean("isGroup", false);
        this.chatId = args.getInt("chatId");
        this.maxCount = this.chatType == 0 ? MessagesController.getInstance(this.currentAccount).maxMegagroupCount : MessagesController.getInstance(this.currentAccount).maxBroadcastCount;
    }

    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatDidCreated);
        return super.onFragmentCreate();
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatDidCreated);
    }

    public void onClick(View v) {
        GroupCreateSpan span = (GroupCreateSpan) v;
        if (span.isDeleting()) {
            this.currentDeletingSpan = null;
            this.spansContainer.removeSpan(span);
            updateHint();
            checkVisibleRows();
            return;
        }
        if (this.currentDeletingSpan != null) {
            this.currentDeletingSpan.cancelDeleteAnimation();
        }
        this.currentDeletingSpan = span;
        span.startDeleteAnimation();
    }

    public View createView(Context context) {
        boolean z;
        int i = 1;
        this.searching = false;
        this.searchWas = false;
        this.allSpans.clear();
        this.selectedContacts.clear();
        this.currentDeletingSpan = null;
        if (this.chatType == 2) {
            z = true;
        } else {
            z = false;
        }
        this.doneButtonVisible = z;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        if (this.type == 1) {
            this.actionBar.setTitle(LocaleController.getString("DeleteContacts", R.string.DeleteContacts));
        } else {
            this.actionBar.setTitle(LocaleController.getString("SpecialContacts", R.string.SpecialContacts));
        }
        this.actionBar.setActionBarMenuOnItemClick(new C23711());
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_done, AndroidUtilities.dp(56.0f));
        if (this.chatType != 2) {
            this.doneButton.setScaleX(0.0f);
            this.doneButton.setScaleY(0.0f);
            this.doneButton.setAlpha(0.0f);
        }
        this.fragmentView = new ViewGroup(context) {
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int maxSize;
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = MeasureSpec.getSize(heightMeasureSpec);
                setMeasuredDimension(width, height);
                if (AndroidUtilities.isTablet() || height > width) {
                    maxSize = AndroidUtilities.dp(144.0f);
                } else {
                    maxSize = AndroidUtilities.dp(56.0f);
                }
                MultiSelectContactActivity.this.scrollView.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(maxSize, Integer.MIN_VALUE));
                MultiSelectContactActivity.this.listView.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(height - MultiSelectContactActivity.this.scrollView.getMeasuredHeight(), 1073741824));
                MultiSelectContactActivity.this.emptyView.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(height - MultiSelectContactActivity.this.scrollView.getMeasuredHeight(), 1073741824));
            }

            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                MultiSelectContactActivity.this.scrollView.layout(0, 0, MultiSelectContactActivity.this.scrollView.getMeasuredWidth(), MultiSelectContactActivity.this.scrollView.getMeasuredHeight());
                MultiSelectContactActivity.this.listView.layout(0, MultiSelectContactActivity.this.scrollView.getMeasuredHeight(), MultiSelectContactActivity.this.listView.getMeasuredWidth(), MultiSelectContactActivity.this.scrollView.getMeasuredHeight() + MultiSelectContactActivity.this.listView.getMeasuredHeight());
                MultiSelectContactActivity.this.emptyView.layout(0, MultiSelectContactActivity.this.scrollView.getMeasuredHeight(), MultiSelectContactActivity.this.emptyView.getMeasuredWidth(), MultiSelectContactActivity.this.scrollView.getMeasuredHeight() + MultiSelectContactActivity.this.emptyView.getMeasuredHeight());
            }

            protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
                boolean result = super.drawChild(canvas, child, drawingTime);
                if (child == MultiSelectContactActivity.this.listView || child == MultiSelectContactActivity.this.emptyView) {
                    MultiSelectContactActivity.this.parentLayout.drawHeaderShadow(canvas, MultiSelectContactActivity.this.scrollView.getMeasuredHeight());
                }
                return result;
            }
        };
        ViewGroup frameLayout = this.fragmentView;
        this.scrollView = new ScrollView(context) {
            public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
                if (MultiSelectContactActivity.this.ignoreScrollEvent) {
                    MultiSelectContactActivity.this.ignoreScrollEvent = false;
                    return false;
                }
                rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
                rectangle.top += MultiSelectContactActivity.this.fieldY + AndroidUtilities.dp(20.0f);
                rectangle.bottom += MultiSelectContactActivity.this.fieldY + AndroidUtilities.dp(50.0f);
                return super.requestChildRectangleOnScreen(child, rectangle, immediate);
            }
        };
        this.scrollView.setVerticalScrollBarEnabled(false);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, Theme.getColor(Theme.key_windowBackgroundWhite));
        frameLayout.addView(this.scrollView);
        this.spansContainer = new SpansContainer(context);
        this.scrollView.addView(this.spansContainer, LayoutHelper.createFrame(-1, -2.0f));
        this.editText = new EditTextBoldCursor(context) {
            public boolean onTouchEvent(MotionEvent event) {
                if (MultiSelectContactActivity.this.currentDeletingSpan != null) {
                    MultiSelectContactActivity.this.currentDeletingSpan.cancelDeleteAnimation();
                    MultiSelectContactActivity.this.currentDeletingSpan = null;
                }
                return super.onTouchEvent(event);
            }
        };
        this.editText.setTextSize(1, 18.0f);
        this.editText.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText));
        this.editText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.editText.setCursorColor(Theme.getColor(Theme.key_groupcreate_cursor));
        this.editText.setInputType(655536);
        this.editText.setSingleLine(true);
        this.editText.setBackgroundDrawable(null);
        this.editText.setVerticalScrollBarEnabled(false);
        this.editText.setHorizontalScrollBarEnabled(false);
        this.editText.setTextIsSelectable(false);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setImeOptions(268435462);
        this.editText.setGravity((LocaleController.isRTL ? 5 : 3) | 16);
        this.spansContainer.addView(this.editText);
        if (this.chatType == 2) {
            this.editText.setHintText(LocaleController.getString("AddMutual", R.string.AddMutual));
        } else if (this.isAlwaysShare) {
            if (this.isGroup) {
                this.editText.setHintText(LocaleController.getString("AlwaysAllowPlaceholder", R.string.AlwaysAllowPlaceholder));
            } else {
                this.editText.setHintText(LocaleController.getString("AlwaysShareWithPlaceholder", R.string.AlwaysShareWithPlaceholder));
            }
        } else if (!this.isNeverShare) {
            this.editText.setHintText(LocaleController.getString("SendMessageTo", R.string.SendMessageTo));
        } else if (this.isGroup) {
            this.editText.setHintText(LocaleController.getString("NeverAllowPlaceholder", R.string.NeverAllowPlaceholder));
        } else {
            this.editText.setHintText(LocaleController.getString("NeverShareWithPlaceholder", R.string.NeverShareWithPlaceholder));
        }
        this.editText.setCustomSelectionActionModeCallback(new C23755());
        this.editText.setOnEditorActionListener(new C23766());
        this.editText.setOnKeyListener(new C23777());
        this.editText.addTextChangedListener(new C23788());
        this.emptyView = new EmptyTextProgressView(context);
        if (ContactsController.getInstance(this.currentAccount).isLoadingContacts()) {
            this.emptyView.showProgress();
        } else {
            this.emptyView.showTextView();
        }
        this.emptyView.setShowAtCenter(true);
        this.emptyView.setText(LocaleController.getString("NoContacts", R.string.NoContacts));
        frameLayout.addView(this.emptyView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 1, false);
        this.listView = new RecyclerListView(context);
        this.listView.setFastScrollEnabled();
        this.listView.setEmptyView(this.emptyView);
        RecyclerListView recyclerListView = this.listView;
        Adapter deleteContactsAdapter = new DeleteContactsAdapter(context);
        this.adapter = deleteContactsAdapter;
        recyclerListView.setAdapter(deleteContactsAdapter);
        this.listView.setLayoutManager(linearLayoutManager);
        this.listView.setVerticalScrollBarEnabled(false);
        recyclerListView = this.listView;
        if (!LocaleController.isRTL) {
            i = 2;
        }
        recyclerListView.setVerticalScrollbarPosition(i);
        recyclerListView = this.listView;
        ItemDecoration groupCreateDividerItemDecoration = new GroupCreateDividerItemDecoration();
        this.itemDecoration = groupCreateDividerItemDecoration;
        recyclerListView.addItemDecoration(groupCreateDividerItemDecoration);
        frameLayout.addView(this.listView);
        this.listView.setOnItemClickListener(new C23799());
        this.listView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    AndroidUtilities.hideKeyboard(MultiSelectContactActivity.this.editText);
                }
            }
        });
        updateHint();
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.editText != null) {
            this.editText.requestFocus();
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.contactsDidLoaded) {
            if (this.emptyView != null) {
                this.emptyView.showTextView();
            }
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        } else if (id == NotificationCenter.updateInterfaces) {
            if (this.listView != null) {
                int mask = ((Integer) args[0]).intValue();
                int count = this.listView.getChildCount();
                if ((mask & 2) != 0 || (mask & 1) != 0 || (mask & 4) != 0) {
                    for (int a = 0; a < count; a++) {
                        View child = this.listView.getChildAt(a);
                        if (child instanceof GroupCreateUserCell) {
                            ((GroupCreateUserCell) child).update(mask);
                        }
                    }
                }
            }
        } else if (id == NotificationCenter.chatDidCreated) {
            removeSelfFromStack();
        }
    }

    private void checkVisibleRows() {
        int count = this.listView.getChildCount();
        for (int a = 0; a < count; a++) {
            View child = this.listView.getChildAt(a);
            if (child instanceof GroupCreateUserCell) {
                GroupCreateUserCell cell = (GroupCreateUserCell) child;
                User user = cell.getUser();
                if (user != null) {
                    cell.setChecked(this.selectedContacts.containsKey(Integer.valueOf(user.id)), true);
                }
            }
        }
    }

    private boolean onDonePressed() {
        if (this.selectedUsers.isEmpty()) {
            return false;
        }
        Builder builder;
        if (this.type == 1) {
            builder = new Builder(getParentActivity());
            builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ContactsController.getInstance(MultiSelectContactActivity.this.currentAccount).deleteContact(MultiSelectContactActivity.this.selectedUsers);
                    MultiSelectContactActivity.this.finishFragment();
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
            return true;
        } else if (this.type != 2) {
            return true;
        } else {
            builder = new Builder(getParentActivity());
            builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (int a = 0; a < MultiSelectContactActivity.this.selectedUsers.size(); a++) {
                        int uid = ((User) MultiSelectContactActivity.this.selectedUsers.get(a)).id;
                        if (!TurboConfig.containValue("specific_c" + uid)) {
                            TurboConfig.setIntValue("specific_c" + uid, uid);
                        }
                    }
                    MultiSelectContactActivity.this.finishFragment();
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
            return true;
        }
    }

    private void closeSearch() {
        this.searching = false;
        this.searchWas = false;
        this.itemDecoration.setSearching(false);
        this.adapter.setSearching(false);
        this.adapter.searchDialogs(null);
        this.listView.setFastScrollVisible(true);
        this.listView.setVerticalScrollBarEnabled(false);
        this.emptyView.setText(LocaleController.getString("NoContacts", R.string.NoContacts));
    }

    private void updateHint() {
        if (this.selectedContacts.isEmpty() || this.selectedContacts.size() == 1) {
            this.actionBar.setSubtitle(LocaleController.formatString("ContactSelectedCount1", R.string.ContactSelectedCount1, new Object[]{Integer.valueOf(this.selectedContacts.size())}));
        } else {
            this.actionBar.setSubtitle(LocaleController.formatString("ContactSelectedCount2", R.string.ContactSelectedCount2, new Object[]{Integer.valueOf(this.selectedContacts.size())}));
        }
        if (this.chatType == 2) {
            return;
        }
        AnimatorSet animatorSet;
        Animator[] animatorArr;
        if (this.doneButtonVisible && this.allSpans.isEmpty()) {
            if (this.currentDoneButtonAnimation != null) {
                this.currentDoneButtonAnimation.cancel();
            }
            this.currentDoneButtonAnimation = new AnimatorSet();
            animatorSet = this.currentDoneButtonAnimation;
            animatorArr = new Animator[3];
            animatorArr[0] = ObjectAnimator.ofFloat(this.doneButton, "scaleX", new float[]{0.0f});
            animatorArr[1] = ObjectAnimator.ofFloat(this.doneButton, "scaleY", new float[]{0.0f});
            animatorArr[2] = ObjectAnimator.ofFloat(this.doneButton, "alpha", new float[]{0.0f});
            animatorSet.playTogether(animatorArr);
            this.currentDoneButtonAnimation.setDuration(180);
            this.currentDoneButtonAnimation.start();
            this.doneButtonVisible = false;
        } else if (!this.doneButtonVisible && !this.allSpans.isEmpty()) {
            if (this.currentDoneButtonAnimation != null) {
                this.currentDoneButtonAnimation.cancel();
            }
            this.currentDoneButtonAnimation = new AnimatorSet();
            animatorSet = this.currentDoneButtonAnimation;
            animatorArr = new Animator[3];
            animatorArr[0] = ObjectAnimator.ofFloat(this.doneButton, "scaleX", new float[]{1.0f});
            animatorArr[1] = ObjectAnimator.ofFloat(this.doneButton, "scaleY", new float[]{1.0f});
            animatorArr[2] = ObjectAnimator.ofFloat(this.doneButton, "alpha", new float[]{1.0f});
            animatorSet.playTogether(animatorArr);
            this.currentDoneButtonAnimation.setDuration(180);
            this.currentDoneButtonAnimation.start();
            this.doneButtonVisible = true;
        }
    }

    public ThemeDescription[] getThemeDescriptions() {
        ThemeDescriptionDelegate сellDelegate = new ThemeDescriptionDelegate() {
            public void didSetColor() {
                int count = MultiSelectContactActivity.this.listView.getChildCount();
                for (int a = 0; a < count; a++) {
                    View child = MultiSelectContactActivity.this.listView.getChildAt(a);
                    if (child instanceof GroupCreateUserCell) {
                        ((GroupCreateUserCell) child).update(0);
                    }
                }
            }
        };
        r10 = new ThemeDescription[37];
        r10[11] = new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider);
        r10[12] = new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder);
        r10[13] = new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle);
        r10[14] = new ThemeDescription(this.editText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        r10[15] = new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_groupcreate_hintText);
        r10[16] = new ThemeDescription(this.editText, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_groupcreate_cursor);
        r10[17] = new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GroupCreateSectionCell.class}, null, null, null, Theme.key_graySection);
        r10[18] = new ThemeDescription(this.listView, 0, new Class[]{GroupCreateSectionCell.class}, new String[]{"drawable"}, null, null, null, Theme.key_groupcreate_sectionShadow);
        r10[19] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateSectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_groupcreate_sectionText);
        r10[20] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"textView"}, null, null, null, Theme.key_groupcreate_sectionText);
        r10[21] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_groupcreate_checkbox);
        r10[22] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_groupcreate_checkboxCheck);
        r10[23] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_groupcreate_onlineText);
        r10[24] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_groupcreate_offlineText);
        r10[25] = new ThemeDescription(this.listView, 0, new Class[]{GroupCreateUserCell.class}, null, new Drawable[]{Theme.avatar_photoDrawable, Theme.avatar_broadcastDrawable}, null, Theme.key_avatar_text);
        r10[26] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundRed);
        r10[27] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundOrange);
        r10[28] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundViolet);
        r10[29] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundGreen);
        r10[30] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundCyan);
        r10[31] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundBlue);
        r10[32] = new ThemeDescription(null, 0, null, null, null, сellDelegate, Theme.key_avatar_backgroundPink);
        r10[33] = new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_avatar_backgroundGroupCreateSpanBlue);
        r10[34] = new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanBackground);
        r10[35] = new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanText);
        r10[36] = new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_avatar_backgroundBlue);
        return r10;
    }
}
