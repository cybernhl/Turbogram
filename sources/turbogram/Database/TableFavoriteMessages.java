package turbogram.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class TableFavoriteMessages {
    public static void deleteFMsByDid(long did, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_fav_msg", "dialog_id=" + did, null);
    }

    public static void deleteFMByMid(int messageId, SQLiteDatabase sqLite) {
        sqLite.delete("tbl_fav_msg", "message_id=" + messageId, null);
    }

    public static ArrayList<Integer> getAllFMs(SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT message_id FROM tbl_fav_msg", null);
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

    public static ArrayList<Integer> getAllFMsByDid(long dialogId, SQLiteDatabase sqLite) {
        Cursor cursor = sqLite.rawQuery("SELECT message_id FROM tbl_fav_msg WHERE dialog_id = " + dialogId, null);
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
}
