package turbogram.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class TableDialogs {
    public static void addDialog(int did, int cid, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("dialog_id", Integer.valueOf(did));
        args.put("category_id", Integer.valueOf(cid));
        sqLite.insert("tbl_dialog", null, args);
    }

    public static void deleteDialogByDid(int did, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_dialog", "dialog_id=" + did, null);
    }

    public static void deleteDialogsByCid(int cid, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_dialog", "category_id=" + cid, null);
    }

    public static void changeDialogCategory(int did, int cid, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("category_id", Integer.valueOf(cid));
        sqLite.update("tbl_dialog", args, "dialog_id=" + did, null);
    }

    public static boolean ifExistsDialog(int did, SQLiteDatabase sqLite) {
        boolean result = false;
        Cursor cursor = sqLite.rawQuery("SELECT dialog_id FROM tbl_dialog WHERE dialog_id = " + did, null);
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

    public static ArrayList<Integer> getDialogsByCid(int cid, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT dialog_id FROM tbl_dialog WHERE category_id =" + cid, null);
        ArrayList<Integer> array = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    array.add(Integer.valueOf(cursor.getInt(0)));
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

    public static Integer getCategoryDCount(int catId, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT COUNT(category_id) FROM tbl_dialog WHERE category_id =" + catId, null);
        Integer count = Integer.valueOf(0);
        try {
            if (cursor.moveToFirst()) {
                do {
                    count = Integer.valueOf(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return count;
        } catch (Throwable th) {
            cursor.close();
        }
    }
}
