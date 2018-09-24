package turbogram.Utilities;

import java.util.ArrayList;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC.User;

public class DialogsLoaderShareAlert {
    ArrayList<TLRPC$TL_dialog> allDialogs = new ArrayList();
    private int currentAccount = UserConfig.selectedAccount;
    boolean showInList = TurboConfig$Tabs.showHiddenInShareAlert;

    public ArrayList<TLRPC$TL_dialog> shareAlertGetDialogsArray(int selectedTab) {
        this.allDialogs.addAll(MessagesController.getInstance(this.currentAccount).dialogsServerOnly);
        if (selectedTab == 7) {
            return getAll();
        }
        if (selectedTab == 6) {
            return getFavorites();
        }
        if (selectedTab == 5) {
            return getContacts();
        }
        if (selectedTab == 4) {
            return getGroups();
        }
        if (selectedTab == 3) {
            return getSuperGroups();
        }
        if (selectedTab == 2) {
            return getChannels();
        }
        if (selectedTab == 1) {
            return getBots();
        }
        if (selectedTab == 0) {
            return getPhonebook();
        }
        return getAll();
    }

    private ArrayList<TLRPC$TL_dialog> getAll() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        TLRPC$Chat chat;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    if (lower_id > 0) {
                        dialogs.add(dialog);
                    } else {
                        chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                        if (!(chat == null || ChatObject.isNotInChat(chat) || (ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup))) {
                            dialogs.add(dialog);
                        }
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    if (lower_id > 0) {
                        dialogs.add(dialog);
                    } else {
                        chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                        if (!(chat == null || ChatObject.isNotInChat(chat) || (ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup))) {
                            dialogs.add(dialog);
                        }
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getFavorites() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        TLRPC$Chat chat;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(!TurboConfig.containValue("fav_" + String.valueOf(dialog.id)) || lower_id == 0 || high_id == 1)) {
                    if (lower_id > 0) {
                        dialogs.add(dialog);
                    } else {
                        chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                        if (!(chat == null || ChatObject.isNotInChat(chat) || (ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup))) {
                            dialogs.add(dialog);
                        }
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(!TurboConfig.containValue("fav_" + String.valueOf(dialog.id)) || lower_id == 0 || high_id == 1)) {
                    if (lower_id > 0) {
                        dialogs.add(dialog);
                    } else {
                        chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                        if (!(chat == null || ChatObject.isNotInChat(chat) || (ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup))) {
                            dialogs.add(dialog);
                        }
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getContacts() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        User user;
        boolean isBot;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1 || lower_id <= 0)) {
                    user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                    if (user == null || !user.bot) {
                        isBot = false;
                    } else {
                        isBot = true;
                    }
                    if (!isBot) {
                        dialogs.add(dialog);
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1 || lower_id <= 0)) {
                    user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                    if (user == null || !user.bot) {
                        isBot = false;
                    } else {
                        isBot = true;
                    }
                    if (!isBot) {
                        dialogs.add(dialog);
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getGroups() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        TLRPC$Chat chat;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (!(chat == null || ChatObject.isNotInChat(chat) || ((ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup) || DialogObject.isChannel(dialog)))) {
                        dialogs.add(dialog);
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (!(chat == null || ChatObject.isNotInChat(chat) || ((ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup) || DialogObject.isChannel(dialog)))) {
                        dialogs.add(dialog);
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getSuperGroups() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        TLRPC$Chat chat;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (!(chat == null || ChatObject.isNotInChat(chat) || ((ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup) || !DialogObject.isChannel(dialog) || !chat.megagroup))) {
                        dialogs.add(dialog);
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (!ChatObject.isNotInChat(chat) && ((!ChatObject.isChannel(chat) || chat.creator || ChatObject.canPost(chat) || chat.megagroup) && DialogObject.isChannel(dialog) && chat.megagroup)) {
                        dialogs.add(dialog);
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getChannels() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        TLRPC$Chat chat;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (!(chat == null || ChatObject.isNotInChat(chat) || ((ChatObject.isChannel(chat) && !chat.creator && !ChatObject.canPost(chat) && !chat.megagroup) || !DialogObject.isChannel(dialog) || chat.megagroup))) {
                        dialogs.add(dialog);
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (!ChatObject.isNotInChat(chat) && ((!ChatObject.isChannel(chat) || chat.creator || ChatObject.canPost(chat) || chat.megagroup) && DialogObject.isChannel(dialog) && !chat.megagroup)) {
                        dialogs.add(dialog);
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getBots() {
        ArrayList<TLRPC$TL_dialog> dialogs;
        int i;
        TLRPC$TL_dialog dialog;
        int lower_id;
        User user;
        boolean isBot;
        if (this.showInList) {
            dialogs = new ArrayList();
            for (i = 0; i < this.allDialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
                lower_id = (int) dialog.id;
                int high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1 || lower_id <= 0)) {
                    user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                    if (user == null || !user.bot) {
                        isBot = false;
                    } else {
                        isBot = true;
                    }
                    if (isBot) {
                        dialogs.add(dialog);
                    }
                }
            }
            return dialogs;
        }
        dialogs = new ArrayList();
        for (i = 0; i < this.allDialogs.size(); i++) {
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogsServerOnly.get(i);
            if (!TurboConfig.containValue("hide_" + String.valueOf(dialog.id))) {
                lower_id = (int) dialog.id;
                high_id = (int) (dialog.id >> 32);
                if (!(lower_id == 0 || high_id == 1 || lower_id <= 0)) {
                    user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                    if (user == null || !user.bot) {
                        isBot = false;
                    } else {
                        isBot = true;
                    }
                    if (isBot) {
                        dialogs.add(dialog);
                    }
                }
            }
        }
        ArrayList<TLRPC$TL_dialog> arrayList = dialogs;
        return dialogs;
    }

    private ArrayList<TLRPC$TL_dialog> getPhonebook() {
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        ArrayList<TLRPC$TL_contact> contacts = ContactsController.getInstance(this.currentAccount).contacts;
        for (int i = 0; i < contacts.size(); i++) {
            TLRPC$TL_contact contact = (TLRPC$TL_contact) contacts.get(i);
            TLRPC$TL_dialog dialog = new TLRPC$TL_dialog();
            dialog.id = (long) contact.user_id;
            dialogs.add(dialog);
        }
        return dialogs;
    }
}
