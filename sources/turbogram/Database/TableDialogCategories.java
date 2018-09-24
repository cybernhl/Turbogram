package turbogram.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class TableDialogCategories {
    public static void addCategory(String catName, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("name", catName);
        sqLite.insert("tbl_dialog_cat", null, args);
    }

    public static void deleteCategoryById(int id, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_dialog_cat", "_id=" + id, null);
    }

    public static void editCategoryById(int id, String catName, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("name", catName);
        sqLite.update("tbl_dialog_cat", args, "_id=" + id, null);
    }

    public static ArrayList<Integer> getAllCategoryIds(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT _id FROM tbl_dialog_cat", null);
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

    public static ArrayList<String> getAllCategoryNames(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT name FROM tbl_dialog_cat", null);
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

    public static String getCategoryById(int id, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT name FROM tbl_dialog_cat WHERE _id =" + id, null);
        String name = null;
        try {
            if (cursor.moveToFirst()) {
                name = cursor.getString(0);
            }
            if (cursor != null) {
                cursor.close();
            }
            return name;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
