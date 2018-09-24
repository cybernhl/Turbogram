package turbogram.Models;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$TL_updateUserName;
import org.telegram.tgnet.TLRPC$TL_updateUserPhone;
import org.telegram.tgnet.TLRPC$TL_updateUserPhoto;
import org.telegram.tgnet.TLRPC.User;
import turbogram.Database.DBHelper;

public class UpdateBiz {
    private DBHelper dbHelper = new DBHelper(ApplicationLoader.applicationContext);

    public void insertUserNameUpdate(User user, TLRPC$TL_updateUserName update) {
        if (user != null && update.user_id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) {
            try {
                UpdateModel updateModel = new UpdateModel();
                updateModel.setUserId(user.id);
                updateModel.setNew(true);
                if (user.username.equals(update.username)) {
                    updateModel.setNewValue(ContactsController.formatName(update.first_name, update.last_name));
                    updateModel.setType(2);
                } else {
                    updateModel.setNewValue(update.username);
                    updateModel.setType(1);
                }
                updateModel.setChangeDate(String.valueOf(System.currentTimeMillis()));
                this.dbHelper.insertUpdateModel(updateModel);
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            } catch (Exception e) {
            }
        }
    }

    public void insertPhotoUpdate(User user, TLRPC$TL_updateUserPhoto update) {
        if (user != null && update.user_id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) {
            try {
                UpdateModel updateModel = new UpdateModel();
                updateModel.setUserId(user.id);
                updateModel.setNew(true);
                updateModel.setType(3);
                updateModel.setChangeDate(String.valueOf(System.currentTimeMillis()));
                this.dbHelper.insertUpdateModel(updateModel);
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            } catch (Exception e) {
            }
        }
    }

    public void insertPhoneUpdate(User user, TLRPC$TL_updateUserPhone update) {
        if (user != null && update.user_id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) {
            try {
                UpdateModel updateModel = new UpdateModel();
                updateModel.setUserId(user.id);
                updateModel.setNew(true);
                updateModel.setNewValue(update.phone);
                updateModel.setType(4);
                updateModel.setChangeDate(String.valueOf(System.currentTimeMillis()));
                this.dbHelper.insertUpdateModel(updateModel);
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            } catch (Exception e) {
            }
        }
    }
}
