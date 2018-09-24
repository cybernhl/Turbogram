package net.hockeyapp.android.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;

public final class JSONDateUtils {
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new C06451();

    /* renamed from: net.hockeyapp.android.utils.JSONDateUtils$1 */
    static class C06451 extends ThreadLocal<DateFormat> {
        C06451() {
        }

        protected DateFormat initialValue() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat;
        }
    }

    private static void checkNull(Object date) throws JSONException {
        if (date == null) {
            throw new JSONException("date cannot be null");
        }
    }

    public static String toString(Date date) throws JSONException {
        checkNull(date);
        return ((DateFormat) DATE_FORMAT.get()).format(date);
    }

    public static Date toDate(String date) throws JSONException {
        checkNull(date);
        try {
            return ((DateFormat) DATE_FORMAT.get()).parse(date);
        } catch (ParseException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
