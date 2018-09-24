package turbogram.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class TableProfileCategories {
    public static void addCategory(String catName, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("name", catName);
        sqLite.insert("tbl_fav_msg_cat", null, args);
    }

    public static void deletePMCategoryById(int id, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_fav_msg_cat", "_id=" + id, null);
    }

    public static void editPMCategoryById(int id, String catName, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("name", catName);
        sqLite.update("tbl_fav_msg_cat", args, "_id=" + id, null);
    }

    public static String getPMCategoryNameById(int id, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT name FROM tbl_fav_msg_cat WHERE _id=" + id, null);
        String name = null;
        try {
            if (cursor.moveToFirst()) {
                name = cursor.getString(0);
            }
            cursor.close();
            return name;
        } catch (Throwable th) {
            cursor.close();
        }
    }

    public static ArrayList<String> getAllPMCategoryNames(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT name FROM tbl_fav_msg_cat", null);
        ArrayList<String> categoryArray = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    categoryArray.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return categoryArray;
        } catch (Throwable th) {
            cursor.close();
        }
    }

    public static ArrayList<Integer> getAllPMCategoryIds(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT _id FROM tbl_fav_msg_cat", null);
        ArrayList<Integer> categoryArray = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    categoryArray.add(Integer.valueOf(cursor.getInt(0)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return categoryArray;
        } catch (Throwable th) {
            cursor.close();
        }
    }
}
