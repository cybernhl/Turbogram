package turbogram.Utilities;

import java.util.ArrayList;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC.User;
import turbogram.Database.DBHelper;

public class DialogsLoader {
    private static ArrayList<TLRPC$TL_dialog> catDialogs = new ArrayList();
    private ArrayList<TLRPC$TL_dialog> allDialogs = new ArrayList();
    private int currentAccount = UserConfig.selectedAccount;
    private int currentCat = TurboConfig$BG.currentCat;
    private boolean isTabsEnabled = TurboConfig$Tabs.tabsEnabled;
    private int selectedTab = TurboConfig$Tabs.selectedTab;
    private int turboDialogId = TurboConfig$BG.turboDialogId;

    public static void getCatDialogs(int cat) {
        catDialogs.clear();
        ArrayList<Integer> ds = new ArrayList();
        DBHelper dbHelper = new DBHelper(ApplicationLoader.applicationContext);
        dbHelper.open();
        try {
            ds.addAll(dbHelper.getDialogsByCid(cat));
            for (int i = 0; i < ds.size(); i++) {
                catDialogs.add((TLRPC$TL_dialog) MessagesController.getInstance(UserConfig.selectedAccount).dialogs_dict.get((long) ((Integer) ds.get(i)).intValue()));
            }
        } finally {
            dbHelper.close();
        }
    }

    public DialogsLoader(int dialogType) {
        ArrayList<TLRPC$TL_dialog> availableDialogs = new ArrayList();
        if (dialogType == 0) {
            availableDialogs.addAll(MessagesController.getInstance(this.currentAccount).dialogs);
        } else if (dialogType == 3) {
            availableDialogs.addAll(MessagesController.getInstance(this.currentAccount).dialogsForward);
        }
        this.allDialogs.clear();
        int i;
        TLRPC$TL_dialog dialog;
        if (this.currentCat == -4) {
            for (i = 0; i < availableDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) availableDialogs.get(i);
                TLRPC$Chat chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-((int) dialog.id)));
                if (chat != null && (chat.creator || ChatObject.canPost(chat))) {
                    this.allDialogs.add(dialog);
                }
            }
        } else if (this.currentCat == -3) {
            this.allDialogs.addAll(availableDialogs);
        } else if (this.currentCat == -2) {
            for (i = 0; i < availableDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) availableDialogs.get(i);
                if (dialog.unread_count > 0) {
                    this.allDialogs.add(dialog);
                }
            }
        } else if (this.currentCat == -1) {
            for (i = 0; i < availableDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) availableDialogs.get(i);
                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                    this.allDialogs.add(dialog);
                }
            }
        } else if (this.currentCat == 0) {
            for (i = 0; i < availableDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) availableDialogs.get(i);
                if (MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                    this.allDialogs.add(dialog);
                }
            }
        } else {
            for (i = 0; i < catDialogs.size(); i++) {
                this.allDialogs.add(catDialogs.get(i));
            }
        }
    }

    public ArrayList<TLRPC$TL_dialog> getDialogsArray() {
        if (this.isTabsEnabled) {
            if (this.selectedTab == 7) {
                return unreadDialogs();
            }
            if (this.selectedTab == 6) {
                return allDialogs();
            }
            if (this.selectedTab == 5) {
                return favoriteDialogs();
            }
            if (this.selectedTab == 4) {
                return contactDialogs();
            }
            if (this.selectedTab == 3) {
                return groupDialogs();
            }
            if (this.selectedTab == 2) {
                return superGroupDialogs();
            }
            if (this.selectedTab == 1) {
                return channelDialogs();
            }
            if (this.selectedTab == 0) {
                return botDialogs();
            }
        }
        return allDialogs();
    }

    public ArrayList<TLRPC$TL_dialog> getBarDialogsArray(int type) {
        if (type == 0) {
            return allDialogs();
        }
        if (type == 8) {
            return favoriteDialogs();
        }
        if (type == 3) {
            return contactDialogs();
        }
        if (type == 4) {
            return groupDialogs();
        }
        if (type == 7) {
            return superGroupDialogs();
        }
        if (type == 5) {
            return channelDialogs();
        }
        if (type == 6) {
            return botDialogs();
        }
        return allDialogs();
    }

    private void addDialog(ArrayList<TLRPC$TL_dialog> dialogs, TLRPC$TL_dialog dialog, boolean hasChannel) {
        if (hasChannel) {
            if (dialog.id == ((long) (-this.turboDialogId))) {
                if (!TurboConfig.hideTurboChannel) {
                    dialogs.add(dialog);
                } else if (dialog.unread_count > 0) {
                    dialogs.add(dialog);
                }
            } else if (TurboConfig.hiddenChatsUnlocked) {
                if (TurboConfig.containValue("hide_" + dialog.id)) {
                    dialogs.add(dialog);
                }
            } else if (!TurboConfig.containValue("hide_" + dialog.id)) {
                dialogs.add(dialog);
            }
        } else if (TurboConfig.hiddenChatsUnlocked) {
            if (TurboConfig.containValue("hide_" + dialog.id)) {
                dialogs.add(dialog);
            }
        } else if (!TurboConfig.containValue("hide_" + dialog.id)) {
            dialogs.add(dialog);
        }
    }

    private ArrayList<TLRPC$TL_dialog> allDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            addDialog(dialogs, (TLRPC$TL_dialog) this.allDialogs.get(i), true);
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> unreadDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            if (dialog.unread_count > 0) {
                addDialog(dialogs, dialog, true);
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> favoriteDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            if (TurboConfig.containValue("fav_" + dialog.id)) {
                addDialog(dialogs, dialog, true);
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> contactDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            int lower_id = (int) dialog.id;
            int high_id = (int) (dialog.id >> 32);
            boolean isChat;
            if (lower_id >= 0 || high_id == 1) {
                isChat = false;
            } else {
                isChat = true;
            }
            if (!(DialogObject.isChannel(dialog) || (isChat && MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(high_id)) == null))) {
                boolean isBot;
                User user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                if (user == null || !user.bot) {
                    isBot = false;
                } else {
                    isBot = true;
                }
                if (!isBot) {
                    addDialog(dialogs, dialog, false);
                }
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> groupDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        if (TurboConfig$Tabs.mergeGroups) {
            dialogs.addAll(mergeGroupDialogs());
        } else {
            for (int i = 0; i < this.allDialogs.size(); i++) {
                TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
                int high_id = (int) (dialog.id >> 32);
                boolean isChat;
                if (((int) dialog.id) >= 0 || high_id == 1) {
                    isChat = false;
                } else {
                    isChat = true;
                }
                if (!DialogObject.isChannel(dialog) && isChat) {
                    addDialog(dialogs, dialog, false);
                }
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> superGroupDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            int lower_id = (int) dialog.id;
            if (DialogObject.isChannel(dialog) && MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id)).megagroup) {
                addDialog(dialogs, dialog, false);
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> mergeGroupDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            int lower_id = (int) dialog.id;
            int high_id = (int) (dialog.id >> 32);
            boolean isChat;
            if (lower_id >= 0 || high_id == 1) {
                isChat = false;
            } else {
                isChat = true;
            }
            TLRPC$Chat chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
            if ((!DialogObject.isChannel(dialog) && isChat) || (DialogObject.isChannel(dialog) && chat.megagroup)) {
                addDialog(dialogs, dialog, false);
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> channelDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            int lower_id = (int) dialog.id;
            if (DialogObject.isChannel(dialog) && !MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id)).megagroup) {
                addDialog(dialogs, dialog, true);
            }
        }
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> botDialogs() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        for (int i = 0; i < this.allDialogs.size(); i++) {
            TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) this.allDialogs.get(i);
            int lower_id = (int) dialog.id;
            int high_id = (int) (dialog.id >> 32);
            if (!DialogObject.isChannel(dialog)) {
                boolean isChat;
                boolean isBot;
                if (lower_id >= 0 || high_id == 1) {
                    isChat = false;
                } else {
                    isChat = true;
                }
                User user = null;
                if (!(isChat || lower_id <= 0 || high_id == 1)) {
                    user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                }
                if (user == null || !user.bot) {
                    isBot = false;
                } else {
                    isBot = true;
                }
                if (isBot) {
                    addDialog(dialogs, dialog, false);
                }
            }
        }
        return dialogs;
    }
}
