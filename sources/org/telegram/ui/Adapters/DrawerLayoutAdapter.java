package org.telegram.ui.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DividerCell;
import org.telegram.ui.Cells.DrawerActionCell;
import org.telegram.ui.Cells.DrawerAddCell;
import org.telegram.ui.Cells.DrawerProfileCell;
import org.telegram.ui.Cells.DrawerUserCell;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Database.DBHelper;
import turbogram.Utilities.TurboConfig;

public class DrawerLayoutAdapter extends SelectionAdapter {
    private ArrayList<Integer> accountNumbers = new ArrayList();
    private boolean accountsShowed;
    private DBHelper dbHelper;
    private ArrayList<Item> items = new ArrayList();
    private Context mContext;
    private ArrayList<String> menuItems = new ArrayList();
    private DrawerProfileCell profileCell;

    private class Item {
        public int icon;
        public int id;
        public String text;

        public Item(int id, String text, int icon) {
            this.icon = icon;
            this.id = id;
            this.text = text;
        }

        public void bind(DrawerActionCell actionCell) {
            actionCell.setTextAndIcon(this.text, this.icon);
        }
    }

    public DrawerLayoutAdapter(Context context) {
        boolean z = true;
        this.mContext = context;
        if (UserConfig.getActivatedAccountsCount() <= 1 || !MessagesController.getGlobalMainSettings().getBoolean("accountsShowed", true)) {
            z = false;
        }
        this.accountsShowed = z;
        Theme.createDialogsResources(context);
        this.dbHelper = new DBHelper(context);
        resetItems();
    }

    private int getAccountRowsCount() {
        int count = this.accountNumbers.size() + 1;
        if (this.accountNumbers.size() < 3) {
            return count + 1;
        }
        return count;
    }

    public int getItemCount() {
        int count = this.items.size() + 2;
        if (this.accountsShowed) {
            return count + getAccountRowsCount();
        }
        return count;
    }

    public void setAccountsShowed(boolean value, boolean animated) {
        if (this.accountsShowed != value) {
            this.accountsShowed = value;
            if (this.profileCell != null) {
                this.profileCell.setAccountsShowed(this.accountsShowed);
            }
            MessagesController.getGlobalMainSettings().edit().putBoolean("accountsShowed", this.accountsShowed).commit();
            if (!animated) {
                notifyDataSetChanged();
            } else if (this.accountsShowed) {
                notifyItemRangeInserted(2, getAccountRowsCount());
            } else {
                notifyItemRangeRemoved(2, getAccountRowsCount());
            }
        }
    }

    public boolean isAccountsShowed() {
        return this.accountsShowed;
    }

    public void notifyDataSetChanged() {
        resetItems();
        super.notifyDataSetChanged();
    }

    public boolean isEnabled(ViewHolder holder) {
        int itemType = holder.getItemViewType();
        return itemType == 3 || itemType == 4 || itemType == 5;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                this.profileCell = new DrawerProfileCell(this.mContext);
                this.profileCell.setOnArrowClickListener(new DrawerLayoutAdapter$$Lambda$0(this));
                view = this.profileCell;
                break;
            case 2:
                view = new DividerCell(this.mContext);
                break;
            case 3:
                view = new DrawerActionCell(this.mContext);
                break;
            case 4:
                view = new DrawerUserCell(this.mContext);
                break;
            case 5:
                view = new DrawerAddCell(this.mContext);
                break;
            default:
                view = new EmptyCell(this.mContext, AndroidUtilities.dp(8.0f));
                break;
        }
        view.setLayoutParams(new LayoutParams(-1, -2));
        return new Holder(view);
    }

    final /* synthetic */ void lambda$onCreateViewHolder$0$DrawerLayoutAdapter(View v) {
        setAccountsShowed(((DrawerProfileCell) v).isAccountsShowed(), true);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ((DrawerProfileCell) holder.itemView).setUser(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId())), this.accountsShowed);
                holder.itemView.setBackgroundColor(Theme.getColor(Theme.key_avatar_backgroundActionBarBlue));
                return;
            case 3:
                position -= 2;
                if (this.accountsShowed) {
                    position -= getAccountRowsCount();
                }
                DrawerActionCell drawerActionCell = holder.itemView;
                ((Item) this.items.get(position)).bind(drawerActionCell);
                drawerActionCell.setPadding(0, 0, 0, 0);
                return;
            case 4:
                holder.itemView.setAccount(((Integer) this.accountNumbers.get(position - 2)).intValue());
                return;
            default:
                return;
        }
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        i -= 2;
        if (this.accountsShowed) {
            if (i < this.accountNumbers.size()) {
                return 4;
            }
            if (this.accountNumbers.size() < 3) {
                if (i == this.accountNumbers.size()) {
                    return 5;
                }
                if (i == this.accountNumbers.size() + 1) {
                    return 2;
                }
            } else if (i == this.accountNumbers.size()) {
                return 2;
            }
            i -= getAccountRowsCount();
        }
        if (((String) this.menuItems.get(i)).toString().equals("sep")) {
            return 2;
        }
        return 3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resetItems() {
        /*
        r13 = this;
        r8 = 7;
        r7 = 6;
        r6 = 4;
        r4 = 2;
        r5 = 3;
        r13.getAllMenu();
        r2 = r13.accountNumbers;
        r2.clear();
        r0 = 0;
    L_0x000e:
        if (r0 >= r5) goto L_0x0026;
    L_0x0010:
        r2 = org.telegram.messenger.UserConfig.getInstance(r0);
        r2 = r2.isClientActivated();
        if (r2 == 0) goto L_0x0023;
    L_0x001a:
        r2 = r13.accountNumbers;
        r3 = java.lang.Integer.valueOf(r0);
        r2.add(r3);
    L_0x0023:
        r0 = r0 + 1;
        goto L_0x000e;
    L_0x0026:
        r2 = r13.accountNumbers;
        r3 = org.telegram.ui.Adapters.DrawerLayoutAdapter$$Lambda$1.$instance;
        java.util.Collections.sort(r2, r3);
        r2 = r13.items;
        r2.clear();
        r2 = org.telegram.messenger.UserConfig.selectedAccount;
        r2 = org.telegram.messenger.UserConfig.getInstance(r2);
        r2 = r2.isClientActivated();
        if (r2 != 0) goto L_0x003f;
    L_0x003e:
        return;
    L_0x003f:
        r1 = 0;
    L_0x0040:
        r2 = r13.menuItems;
        r2 = r2.size();
        if (r1 >= r2) goto L_0x003e;
    L_0x0048:
        r2 = r13.menuItems;
        r2 = r2.get(r1);
        r2 = (java.lang.String) r2;
        r3 = -1;
        r9 = r2.hashCode();
        switch(r9) {
            case -2006490276: goto L_0x0103;
            case -1183699191: goto L_0x0096;
            case -977365303: goto L_0x00f6;
            case -567451565: goto L_0x008b;
            case -330973341: goto L_0x0080;
            case -306308766: goto L_0x012a;
            case -111934207: goto L_0x00e9;
            case 101142: goto L_0x00ac;
            case 113758: goto L_0x005f;
            case 3571837: goto L_0x0110;
            case 94425557: goto L_0x00b7;
            case 94756405: goto L_0x011d;
            case 110327241: goto L_0x0137;
            case 1384665311: goto L_0x006a;
            case 1395293355: goto L_0x0075;
            case 1478475008: goto L_0x00dc;
            case 1985941072: goto L_0x00a1;
            case 2028682694: goto L_0x00cf;
            case 2134040255: goto L_0x00c3;
            default: goto L_0x0058;
        };
    L_0x0058:
        r2 = r3;
    L_0x0059:
        switch(r2) {
            case 0: goto L_0x0144;
            case 1: goto L_0x014c;
            case 2: goto L_0x0165;
            case 3: goto L_0x017e;
            case 4: goto L_0x0197;
            case 5: goto L_0x01b0;
            case 6: goto L_0x01c9;
            case 7: goto L_0x01e4;
            case 8: goto L_0x01ff;
            case 9: goto L_0x021a;
            case 10: goto L_0x0235;
            case 11: goto L_0x0250;
            case 12: goto L_0x02a4;
            case 13: goto L_0x02bf;
            case 14: goto L_0x02da;
            case 15: goto L_0x02f5;
            case 16: goto L_0x031f;
            case 17: goto L_0x033a;
            case 18: goto L_0x0355;
            default: goto L_0x005c;
        };
    L_0x005c:
        r1 = r1 + 1;
        goto L_0x0040;
    L_0x005f:
        r9 = "sep";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0068:
        r2 = 0;
        goto L_0x0059;
    L_0x006a:
        r9 = "newgroup";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0073:
        r2 = 1;
        goto L_0x0059;
    L_0x0075:
        r9 = "newschat";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x007e:
        r2 = r4;
        goto L_0x0059;
    L_0x0080:
        r9 = "newchannel";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0089:
        r2 = r5;
        goto L_0x0059;
    L_0x008b:
        r9 = "contacts";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0094:
        r2 = r6;
        goto L_0x0059;
    L_0x0096:
        r9 = "invite";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x009f:
        r2 = 5;
        goto L_0x0059;
    L_0x00a1:
        r9 = "setting";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00aa:
        r2 = r7;
        goto L_0x0059;
    L_0x00ac:
        r9 = "faq";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00b5:
        r2 = r8;
        goto L_0x0059;
    L_0x00b7:
        r9 = "calls";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00c0:
        r2 = 8;
        goto L_0x0059;
    L_0x00c3:
        r9 = "smessages";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00cc:
        r2 = 9;
        goto L_0x0059;
    L_0x00cf:
        r9 = "scontacts";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00d8:
        r2 = 10;
        goto L_0x0059;
    L_0x00dc:
        r9 = "cchanges";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00e5:
        r2 = 11;
        goto L_0x0059;
    L_0x00e9:
        r9 = "idfinder";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00f2:
        r2 = 12;
        goto L_0x0059;
    L_0x00f6:
        r9 = "dmanager";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x00ff:
        r2 = 13;
        goto L_0x0059;
    L_0x0103:
        r9 = "tsetting";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x010c:
        r2 = 14;
        goto L_0x0059;
    L_0x0110:
        r9 = "turn";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0119:
        r2 = 15;
        goto L_0x0059;
    L_0x011d:
        r9 = "cloud";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0126:
        r2 = 16;
        goto L_0x0059;
    L_0x012a:
        r9 = "dcategory";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0133:
        r2 = 17;
        goto L_0x0059;
    L_0x0137:
        r9 = "theme";
        r2 = r2.equals(r9);
        if (r2 == 0) goto L_0x0058;
    L_0x0140:
        r2 = 18;
        goto L_0x0059;
    L_0x0144:
        r2 = r13.items;
        r3 = 0;
        r2.add(r3);
        goto L_0x005c;
    L_0x014c:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = "NewGroup";
        r10 = 2131494162; // 0x7f0c0512 float:1.8611825E38 double:1.05309804E-314;
        r9 = org.telegram.messenger.LocaleController.getString(r9, r10);
        r10 = 2131165617; // 0x7f0701b1 float:1.7945456E38 double:1.052935717E-314;
        r3.<init>(r4, r9, r10);
        r2.add(r3);
        goto L_0x005c;
    L_0x0165:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = "NewSecretChat";
        r10 = 2131494170; // 0x7f0c051a float:1.861184E38 double:1.0530980437E-314;
        r9 = org.telegram.messenger.LocaleController.getString(r9, r10);
        r10 = 2131165620; // 0x7f0701b4 float:1.7945462E38 double:1.0529357184E-314;
        r3.<init>(r5, r9, r10);
        r2.add(r3);
        goto L_0x005c;
    L_0x017e:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = "NewChannel";
        r10 = 2131494160; // 0x7f0c0510 float:1.861182E38 double:1.053098039E-314;
        r9 = org.telegram.messenger.LocaleController.getString(r9, r10);
        r10 = 2131165611; // 0x7f0701ab float:1.7945444E38 double:1.052935714E-314;
        r3.<init>(r6, r9, r10);
        r2.add(r3);
        goto L_0x005c;
    L_0x0197:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = "Contacts";
        r10 = 2131493405; // 0x7f0c021d float:1.861029E38 double:1.0530976657E-314;
        r9 = org.telegram.messenger.LocaleController.getString(r9, r10);
        r10 = 2131165613; // 0x7f0701ad float:1.7945448E38 double:1.052935715E-314;
        r3.<init>(r7, r9, r10);
        r2.add(r3);
        goto L_0x005c;
    L_0x01b0:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = "InviteFriends";
        r10 = 2131493936; // 0x7f0c0430 float:1.8611366E38 double:1.053097928E-314;
        r9 = org.telegram.messenger.LocaleController.getString(r9, r10);
        r10 = 2131165616; // 0x7f0701b0 float:1.7945454E38 double:1.0529357165E-314;
        r3.<init>(r8, r9, r10);
        r2.add(r3);
        goto L_0x005c;
    L_0x01c9:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 8;
        r10 = "Settings";
        r11 = 2131494914; // 0x7f0c0802 float:1.861335E38 double:1.0530984113E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165621; // 0x7f0701b5 float:1.7945464E38 double:1.052935719E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x01e4:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 9;
        r10 = "TelegramFaq";
        r11 = 2131495097; // 0x7f0c08b9 float:1.861372E38 double:1.0530985017E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165615; // 0x7f0701af float:1.7945452E38 double:1.052935716E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x01ff:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 10;
        r10 = "Calls";
        r11 = 2131493197; // 0x7f0c014d float:1.8609867E38 double:1.053097563E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165612; // 0x7f0701ac float:1.7945446E38 double:1.0529357145E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x021a:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 11;
        r10 = "SavedMessages";
        r11 = 2131494821; // 0x7f0c07a5 float:1.8613161E38 double:1.0530983653E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165619; // 0x7f0701b3 float:1.794546E38 double:1.052935718E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x0235:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 13;
        r10 = "SpecialContacts";
        r11 = 2131495005; // 0x7f0c085d float:1.8613534E38 double:1.0530984563E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165894; // 0x7f0702c6 float:1.7946018E38 double:1.052935854E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x0250:
        r3 = r13.items;
        r9 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r10 = 14;
        r2 = org.telegram.messenger.LocaleController.isRTL;
        if (r2 == 0) goto L_0x0284;
    L_0x025a:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r11 = r13.getStringCount();
        r2 = r2.append(r11);
        r11 = "UserChanges";
        r12 = 2131495359; // 0x7f0c09bf float:1.8614252E38 double:1.053098631E-314;
        r11 = org.telegram.messenger.LocaleController.getString(r11, r12);
        r2 = r2.append(r11);
        r2 = r2.toString();
    L_0x0279:
        r11 = 2131165886; // 0x7f0702be float:1.7946002E38 double:1.05293585E-314;
        r9.<init>(r10, r2, r11);
        r3.add(r9);
        goto L_0x005c;
    L_0x0284:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r11 = "UserChanges";
        r12 = 2131495359; // 0x7f0c09bf float:1.8614252E38 double:1.053098631E-314;
        r11 = org.telegram.messenger.LocaleController.getString(r11, r12);
        r2 = r2.append(r11);
        r11 = r13.getStringCount();
        r2 = r2.append(r11);
        r2 = r2.toString();
        goto L_0x0279;
    L_0x02a4:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 15;
        r10 = "IdFinder";
        r11 = 2131493912; // 0x7f0c0418 float:1.8611318E38 double:1.053097916E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165891; // 0x7f0702c3 float:1.7946012E38 double:1.0529358523E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x02bf:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 17;
        r10 = "DownloadManager";
        r11 = 2131493551; // 0x7f0c02af float:1.8610585E38 double:1.053097738E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165888; // 0x7f0702c0 float:1.7946006E38 double:1.052935851E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x02da:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 18;
        r10 = "TurboSettings";
        r11 = 2131495269; // 0x7f0c0965 float:1.861407E38 double:1.0530985867E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165621; // 0x7f0701b5 float:1.7945464E38 double:1.052935719E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x02f5:
        r3 = r13.items;
        r9 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r10 = 19;
        r2 = turbogram.Utilities.TurboConfig.isSilent;
        if (r2 == 0) goto L_0x0314;
    L_0x02ff:
        r2 = "TurboTurnOn";
        r11 = 2131495276; // 0x7f0c096c float:1.8614084E38 double:1.05309859E-314;
        r2 = org.telegram.messenger.LocaleController.getString(r2, r11);
    L_0x0309:
        r11 = 2131165892; // 0x7f0702c4 float:1.7946014E38 double:1.052935853E-314;
        r9.<init>(r10, r2, r11);
        r3.add(r9);
        goto L_0x005c;
    L_0x0314:
        r2 = "TurboTurnOff";
        r11 = 2131495275; // 0x7f0c096b float:1.8614082E38 double:1.0530985897E-314;
        r2 = org.telegram.messenger.LocaleController.getString(r2, r11);
        goto L_0x0309;
    L_0x031f:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 21;
        r10 = "CategorizeProfile";
        r11 = 2131493210; // 0x7f0c015a float:1.8609894E38 double:1.0530975694E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165893; // 0x7f0702c5 float:1.7946016E38 double:1.0529358533E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x033a:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 22;
        r10 = "CategorizeDialogs";
        r11 = 2131493209; // 0x7f0c0159 float:1.8609892E38 double:1.053097569E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165887; // 0x7f0702bf float:1.7946004E38 double:1.0529358504E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
    L_0x0355:
        r2 = r13.items;
        r3 = new org.telegram.ui.Adapters.DrawerLayoutAdapter$Item;
        r9 = 23;
        r10 = "Theme";
        r11 = 2131495122; // 0x7f0c08d2 float:1.8613772E38 double:1.053098514E-314;
        r10 = org.telegram.messenger.LocaleController.getString(r10, r11);
        r11 = 2131165623; // 0x7f0701b7 float:1.7945468E38 double:1.05293572E-314;
        r3.<init>(r9, r10, r11);
        r2.add(r3);
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.Adapters.DrawerLayoutAdapter.resetItems():void");
    }

    static final /* synthetic */ int lambda$resetItems$1$DrawerLayoutAdapter(Integer o1, Integer o2) {
        long l1 = (long) UserConfig.getInstance(o1.intValue()).loginTime;
        long l2 = (long) UserConfig.getInstance(o2.intValue()).loginTime;
        if (l1 > l2) {
            return 1;
        }
        if (l1 < l2) {
            return -1;
        }
        return 0;
    }

    public int getId(int position) {
        position -= 2;
        if (this.accountsShowed) {
            position -= getAccountRowsCount();
        }
        if (position < 0 || position >= this.items.size()) {
            return -1;
        }
        Item item = (Item) this.items.get(position);
        if (item != null) {
            return item.id;
        }
        return -1;
    }

    private String getStringCount() {
        int count = this.dbHelper.getNewUserChangesCount();
        String stringCount = "";
        if (count <= 0) {
            return stringCount;
        }
        return (LocaleController.isRTL ? "(" : " (") + String.valueOf(count) + (LocaleController.isRTL ? ") " : ")");
    }

    private void getAllMenu() {
        this.dbHelper.open();
        try {
            this.menuItems.clear();
            ArrayList<String> mi = new ArrayList();
            mi.addAll(this.dbHelper.getAllShowingMenu());
            for (int i = 0; i < mi.size(); i++) {
                String menuString = (String) mi.get(i);
                if (!menuString.equals("calls")) {
                    this.menuItems.add(menuString);
                } else if (TurboConfig.callsEnabled) {
                    this.menuItems.add(menuString);
                }
            }
        } finally {
            this.dbHelper.close();
        }
    }
}
