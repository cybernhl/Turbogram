package turbogram.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.baranak.turbogramf.R;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import turbogram.Models.Draft;
import turbogram.Models.SideMenuItem;
import turbogram.Models.TagLink;
import turbogram.Models.UpdateModel;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "turbogramdb";
    private static int DB_VERSION = 6;
    private String DB_PATH = null;
    private SQLiteDatabase sqLite;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    public void onCreate(SQLiteDatabase sqLite) {
        version1(sqLite);
        version2(sqLite);
        version3(sqLite);
        version4(sqLite);
    }

    public void onUpgrade(SQLiteDatabase sqLite, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            version2(sqLite);
        }
        if (oldVersion < 4) {
            version4(sqLite);
        }
        if (oldVersion < 6) {
            version3(sqLite);
        }
    }

    public void open() {
        this.sqLite = getWritableDatabase();
    }

    public synchronized void close() {
        if (this.sqLite != null) {
            this.sqLite.close();
        }
        super.close();
    }

    private void version1(SQLiteDatabase sqLite) {
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_dialog_cat (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_dialog (_id INTEGER PRIMARY KEY AUTOINCREMENT, dialog_id INTEGER, category_id INTEGER)");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_fav_msg_cat (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_fav_msg (_id INTEGER PRIMARY KEY AUTOINCREMENT, dialog_id INTEGER, message_id INTEGER)");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_profile_msg (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id INTEGER, category_id INTEGER)");
    }

    private void version2(SQLiteDatabase sqLite) {
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_userchanges ( _id integer primary key autoincrement, type integer, new_value text, user_id integer, is_new integer, change_date integer)");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_drafts (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, text TEXT)");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_tags (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, tag TEXT)");
        addDraft(sqLite, LocaleController.getString("DraftCreaditNumber", R.string.DraftCreaditNumber), "6219 8610 **** ****");
        addDraft(sqLite, LocaleController.getString("DraftEmail", R.string.DraftEmail), "Example@gmail.com");
        addTag(sqLite, LocaleController.getString("TagTurbo", R.string.TagTurbo), "@TurboTel");
        addTag(sqLite, LocaleController.getString("TagTurboFree", R.string.TagTurboFree), "@TurboTel_Free");
    }

    private void version3(SQLiteDatabase sqLite) {
        sqLite.execSQL("DROP TABLE IF EXISTS tbl_sidemenu");
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_sidemenu (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, show integer, delbtn integer)");
        createSideMenu(sqLite);
    }

    private void version4(SQLiteDatabase sqLite) {
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS tbl_wallpapers (_id INTEGER PRIMARY KEY AUTOINCREMENT, did integer)");
    }

    public void addPMCategory(String catName) {
        TableProfileCategories.addCategory(catName, this.sqLite);
    }

    public void deletePMCategoryById(int id) {
        TableProfileCategories.deletePMCategoryById(id, this.sqLite);
    }

    public void editPMCategoryById(int id, String catName) {
        TableProfileCategories.editPMCategoryById(id, catName, this.sqLite);
    }

    public String getPMCategoryById(int id) {
        return TableProfileCategories.getPMCategoryNameById(id, this.sqLite);
    }

    public ArrayList<String> getAllPMCategoryNames() {
        return TableProfileCategories.getAllPMCategoryNames(this.sqLite);
    }

    public ArrayList<Integer> getAllPMCategoryIds() {
        return TableProfileCategories.getAllPMCategoryIds(this.sqLite);
    }

    public void deleteFMsByDid(long dialogId) {
        TableFavoriteMessages.deleteFMsByDid(dialogId, this.sqLite);
    }

    public void deleteFMByMid(int messageId) {
        TableFavoriteMessages.deleteFMByMid(messageId, this.sqLite);
    }

    public ArrayList<Integer> getAllFMs() {
        return TableFavoriteMessages.getAllFMs(this.sqLite);
    }

    public ArrayList<Integer> getAllFMsByDid(long dialogId) {
        return TableFavoriteMessages.getAllFMsByDid(dialogId, this.sqLite);
    }

    public void addPM(int messageId, int catId) {
        TableProfleMessages.addPM(messageId, catId, this.sqLite);
    }

    public boolean ifExistsPM(int messageId) {
        return TableProfleMessages.ifExistsPM(messageId, this.sqLite);
    }

    public void deleteAllPMs() {
        TableProfleMessages.deleteAllPMs(this.sqLite);
    }

    public void deletePMByMid(int messageId) {
        TableProfleMessages.deletePMByMid(messageId, this.sqLite);
    }

    public void deletePMsByCid(int cid) {
        TableProfleMessages.deletePMsByCid(cid, this.sqLite);
    }

    public void deletePMFromCategory(int messageId) {
        TableProfleMessages.deletePMFromCategory(messageId, this.sqLite);
    }

    public void changePMCategory(int messageId, int catId) {
        TableProfleMessages.changePMCategory(messageId, catId, this.sqLite);
    }

    public ArrayList<Integer> getAllPMs() {
        return TableProfleMessages.getAllPMs(this.sqLite);
    }

    public ArrayList<Integer> getAllPMsWithCid() {
        return TableProfleMessages.getAllPMsWithCid(this.sqLite);
    }

    public ArrayList<Integer> getAllPMsByCid(int catId) {
        return TableProfleMessages.getAllPMsByCid(catId, this.sqLite);
    }

    public Integer getCategoryPMCount(int catId) {
        return TableProfleMessages.getCategoryPMCount(catId, this.sqLite);
    }

    public void addCategory(String catName) {
        TableDialogCategories.addCategory(catName, this.sqLite);
    }

    public void deleteCategoryById(int id) {
        TableDialogCategories.deleteCategoryById(id, this.sqLite);
    }

    public void editCategoryById(int id, String catName) {
        TableDialogCategories.editCategoryById(id, catName, this.sqLite);
    }

    public String getCategoryById(int id) {
        return TableDialogCategories.getCategoryById(id, this.sqLite);
    }

    public ArrayList<Integer> getAllCategoryIds() {
        return TableDialogCategories.getAllCategoryIds(this.sqLite);
    }

    public ArrayList<String> getAllCategoryNames() {
        return TableDialogCategories.getAllCategoryNames(this.sqLite);
    }

    public void addDialog(int did, int cid) {
        TableDialogs.addDialog(did, cid, this.sqLite);
    }

    public void deleteDialogByDid(int did) {
        TableDialogs.deleteDialogByDid(did, this.sqLite);
    }

    public void deleteDialogsByCid(int cid) {
        TableDialogs.deleteDialogsByCid(cid, this.sqLite);
    }

    public void changeDialogCategory(int did, int cid) {
        TableDialogs.changeDialogCategory(did, cid, this.sqLite);
    }

    public boolean ifExistsDialog(int did) {
        return TableDialogs.ifExistsDialog(did, this.sqLite);
    }

    public ArrayList<Integer> getDialogsByCid(int catId) {
        return TableDialogs.getDialogsByCid(catId, this.sqLite);
    }

    public Integer getCategoryDCount(int catId) {
        return TableDialogs.getCategoryDCount(catId, this.sqLite);
    }

    public void addDraft(SQLiteDatabase sqLite, String title, String text) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("text", text);
        sqLite.insert("tbl_drafts", null, args);
    }

    public void addDraft(String title, String text) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("text", text);
        this.sqLite.insert("tbl_drafts", null, args);
    }

    public ArrayList<Draft> getAllDrafts() {
        Cursor cursor = this.sqLite.rawQuery("SELECT title,text FROM tbl_drafts", null);
        ArrayList<Draft> array = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    array.add(new Draft(cursor.getString(0), cursor.getString(1)));
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            return array;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void deleteAllDrafts() {
        this.sqLite.delete("tbl_drafts", null, null);
    }

    public void deleteDraft(String title) {
        this.sqLite.delete("tbl_drafts", "title='" + title + "'", null);
    }

    public void editDraft(String oldTitle, String title, String text) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("text", text);
        this.sqLite.update("tbl_drafts", args, "title='" + oldTitle + "'", null);
    }

    public boolean ifExistsDTitle(String title) {
        boolean result = false;
        Cursor cursor = this.sqLite.rawQuery("SELECT title FROM tbl_drafts WHERE title ='" + title + "'", null);
        try {
            if (cursor.moveToFirst()) {
                result = true;
            }
            if (cursor != null) {
                cursor.close();
            }
            return result;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public boolean ifExistsDText(String text) {
        boolean result = false;
        Cursor cursor = this.sqLite.rawQuery("SELECT text FROM tbl_drafts WHERE text ='" + text + "'", null);
        try {
            if (cursor.moveToFirst()) {
                result = true;
            }
            if (cursor != null) {
                cursor.close();
            }
            return result;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void addTag(SQLiteDatabase sqLite, String title, String tag) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("tag", tag);
        sqLite.insert("tbl_tags", null, args);
    }

    public void addTag(String title, String tag) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("tag", tag);
        this.sqLite.insert("tbl_tags", null, args);
    }

    public ArrayList<TagLink> getAllTags() {
        Cursor cursor = this.sqLite.rawQuery("SELECT title,tag FROM tbl_tags", null);
        ArrayList<TagLink> array = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    array.add(new TagLink(cursor.getString(0), cursor.getString(1)));
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            return array;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void deleteAllTags() {
        this.sqLite.delete("tbl_tags", null, null);
    }

    public void deleteTag(String tag) {
        this.sqLite.delete("tbl_tags", "tag='" + tag + "'", null);
    }

    public void editTag(String oldTag, String title, String tag) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("tag", tag);
        this.sqLite.update("tbl_tags", args, "tag='" + oldTag + "'", null);
    }

    public boolean ifExistsTitle(String title) {
        boolean result = false;
        Cursor cursor = this.sqLite.rawQuery("SELECT tag FROM tbl_tags WHERE title ='" + title + "'", null);
        try {
            if (cursor.moveToFirst()) {
                result = true;
            }
            if (cursor != null) {
                cursor.close();
            }
            return result;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public boolean ifExistsTag(String tag) {
        boolean result = false;
        Cursor cursor = this.sqLite.rawQuery("SELECT tag FROM tbl_tags WHERE tag ='" + tag + "'", null);
        try {
            if (cursor.moveToFirst()) {
                result = true;
            }
            if (cursor != null) {
                cursor.close();
            }
            return result;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* renamed from: a */
    public Cursor m1236a(int var1, int var2) {
        String var3;
        if (var1 != 0) {
            var3 = "type=" + var1;
        } else {
            var3 = null;
        }
        String str = var2 + "";
        return getReadableDatabase().query("tbl_userchanges", null, var3, null, null, null, "_id DESC", str);
    }

    public UpdateModel getUpdateModelByCursor(Cursor cursor) {
        boolean isNew = false;
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        int type = cursor.getInt(cursor.getColumnIndex(Param.TYPE));
        String newVal = cursor.getString(cursor.getColumnIndex("new_value"));
        int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
        if (!cursor.isNull(cursor.getColumnIndex("is_new")) && cursor.getLong(cursor.getColumnIndex("is_new")) > 0) {
            isNew = true;
        }
        return new UpdateModel(Long.valueOf(id), type, newVal, userId, isNew, cursor.getString(cursor.getColumnIndex("change_date")));
    }

    public void insertUpdateModel(UpdateModel updateModel) {
        if (updateModel.getType() == 3) {
            deleteIfItsRepeated(updateModel);
        }
        open();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Param.TYPE, Integer.valueOf(updateModel.getType()));
            contentValues.put("new_value", updateModel.getNewValue());
            contentValues.put("user_id", Integer.valueOf(updateModel.getUserId()));
            contentValues.put("is_new", Integer.valueOf(updateModel.isNew() ? 1 : 0));
            if (updateModel.getChangeDate() != null) {
                contentValues.put("change_date", updateModel.getChangeDate());
            }
            this.sqLite.insert("tbl_userchanges", null, contentValues);
        } finally {
            this.sqLite.close();
        }
    }

    public void deleteIfItsRepeated(UpdateModel updateModel) {
        int uid = Integer.valueOf(updateModel.getUserId()).intValue();
        int result = -1;
        open();
        Cursor cursor = this.sqLite.rawQuery("SELECT _id FROM tbl_userchanges WHERE user_id =" + uid + " AND type=" + 3, null);
        try {
            if (cursor.moveToFirst()) {
                result = cursor.getInt(0);
            }
            if (cursor != null) {
                cursor.close();
            }
            if (result > -1) {
                this.sqLite.delete("tbl_userchanges", "_id =" + result, null);
            }
            this.sqLite.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* renamed from: a */
    public void m1237a() {
        open();
        this.sqLite.beginTransaction();
        try {
            ContentValues var2 = new ContentValues();
            var2.putNull("is_new");
            this.sqLite.update("tbl_userchanges", var2, null, null);
            this.sqLite.setTransactionSuccessful();
        } finally {
            this.sqLite.endTransaction();
        }
    }

    public void deleteAll() {
        open();
        this.sqLite.beginTransaction();
        try {
            this.sqLite.delete("tbl_userchanges", null, null);
            this.sqLite.setTransactionSuccessful();
        } finally {
            this.sqLite.endTransaction();
        }
    }

    public int getNewUserChangesCount() {
        Cursor query = null;
        try {
            query = getReadableDatabase().query("tbl_userchanges", null, "is_new = 1", null, null, null, "_id");
            int count = query.getCount();
            if (query != null) {
                query.close();
            }
            return count;
        } catch (Throwable th) {
            if (query != null) {
                query.close();
            }
            int i = 0;
            return 0;
        }
    }

    public void addMenu(SQLiteDatabase sqLite, String title, int show, int delButton) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("show", Integer.valueOf(show));
        args.put("delbtn", Integer.valueOf(delButton));
        sqLite.insert("tbl_sidemenu", null, args);
    }

    public void addMenu(String title, int show, int delButton) {
        ContentValues args = new ContentValues();
        args.put("title", title);
        args.put("show", Integer.valueOf(show));
        args.put("delbtn", Integer.valueOf(delButton));
        this.sqLite.insert("tbl_sidemenu", null, args);
    }

    public void deleteAllMenu() {
        this.sqLite.delete("tbl_sidemenu", null, null);
    }

    public ArrayList<SideMenuItem> getAllMenu() {
        Cursor cursor = this.sqLite.rawQuery("SELECT * FROM tbl_sidemenu", null);
        ArrayList<SideMenuItem> array = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    array.add(new SideMenuItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            return array;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public ArrayList<String> getAllShowingMenu() {
        Cursor cursor = this.sqLite.rawQuery("SELECT title FROM tbl_sidemenu where show=1", null);
        ArrayList<String> array = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    array.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            return array;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void createSideMenu(SQLiteDatabase sqLite) {
        addMenu(sqLite, "newgroup", 1, 0);
        addMenu(sqLite, "newschat", 1, 0);
        addMenu(sqLite, "newchannel", 1, 0);
        addMenu(sqLite, "sep", 1, 0);
        addMenu(sqLite, "contacts", 1, 0);
        addMenu(sqLite, "smessages", 1, 0);
        addMenu(sqLite, "calls", 1, 0);
        addMenu(sqLite, "scontacts", 1, 0);
        addMenu(sqLite, "cchanges", 1, 0);
        addMenu(sqLite, "idfinder", 1, 0);
        addMenu(sqLite, "sep", 1, 0);
        addMenu(sqLite, "cloud", 0, 0);
        addMenu(sqLite, "dcategory", 0, 0);
        addMenu(sqLite, "dmanager", 1, 0);
        addMenu(sqLite, "sep", 1, 0);
        addMenu(sqLite, "invite", 1, 0);
        addMenu(sqLite, "setting", 1, 0);
        addMenu(sqLite, "tsetting", 1, 0);
        addMenu(sqLite, "theme", 0, 0);
        addMenu(sqLite, "faq", 1, 0);
        addMenu(sqLite, "sep", 0, 0);
        addMenu(sqLite, "turn", 1, 0);
    }

    public void addWallpaper(long did) {
        ContentValues args = new ContentValues();
        args.put("did", Long.valueOf(did));
        this.sqLite.insert("tbl_wallpapers", null, args);
    }

    public void deleteWallpaper(long did) {
        this.sqLite.delete("tbl_wallpapers", "did=" + did, null);
    }

    public ArrayList<Long> getAllWallpapers() {
        Cursor cursor = this.sqLite.rawQuery("SELECT did FROM tbl_wallpapers", null);
        ArrayList<Long> array = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    array.add(Long.valueOf((long) cursor.getInt(0)));
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            return array;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
