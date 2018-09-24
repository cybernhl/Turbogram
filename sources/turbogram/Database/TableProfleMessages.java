package turbogram.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class TableProfleMessages {
    public static void addPM(int messageId, int categoryId, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("message_id", Integer.valueOf(messageId));
        args.put("category_id", Integer.valueOf(categoryId));
        sqLite.insert("tbl_profile_msg", null, args);
    }

    public static void deleteAllPMs(SQLiteDatabase sqLite) {
        sqLite.delete("tbl_profile_msg", null, null);
    }

    public static void deletePMByMid(int messageId, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_profile_msg", "message_id=" + messageId, null);
    }

    public static void deletePMsByCid(int cid, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_profile_msg", "category_id=" + cid, null);
    }

    public static void deletePMFromCategory(int messageId, SQLiteDatabase sqLite) {
        changePMCategory(messageId, 0, sqLite);
    }

    public static void changePMCategory(int messageId, int catId, SQLiteDatabase sqLite) {
        ContentValues args = new ContentValues();
        args.put("category_id", Integer.valueOf(catId));
        sqLite.update("tbl_profile_msg", args, "message_id=" + messageId, null);
    }

    public static boolean ifExistsPM(int messageId, SQLiteDatabase sqLite) {
        boolean result = false;
        Cursor cursor = sqLite.rawQuery("SELECT message_id FROM tbl_profile_msg WHERE message_id = " + messageId, null);
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

    public static ArrayList<Integer> getAllPMs(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT message_id FROM tbl_profile_msg", null);
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

    public static ArrayList<Integer> getAllPMsWithCid(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT message_id FROM tbl_profile_msg WHERE category_id > 0", null);
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

    public static ArrayList<Integer> getAllPMsByCid(int catId, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT message_id FROM tbl_profile_msg WHERE category_id =" + catId, null);
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

    public static Integer getCategoryPMCount(int catId, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT COUNT(category_id) FROM tbl_profile_msg WHERE category_id =" + catId, null);
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
