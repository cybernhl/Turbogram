package turbogram.Utilities;

import com.baranak.turbogramf.R;
import java.util.Calendar;
import org.telegram.messenger.LocaleController;

public class SolarCalendar {
    private Calendar calendar;
    private int date;
    private int month;
    private int weekDay;
    private int year;

    public SolarCalendar() {
        this.calendar = Calendar.getInstance();
        calSolarCalendar();
    }

    public SolarCalendar(Calendar calendar) {
        this.calendar = calendar;
        calSolarCalendar();
    }

    private void calSolarCalendar() {
        int georgianYear = this.calendar.get(1);
        int georgianMonth = this.calendar.get(2) + 1;
        int georgianDate = this.calendar.get(5);
        this.weekDay = this.calendar.get(7) - 1;
        int[] buf1 = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int[] buf2 = new int[]{0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        int ld;
        if (georgianYear % 4 != 0) {
            this.date = buf1[georgianMonth - 1] + georgianDate;
            if (this.date > 79) {
                this.date -= 79;
                if (this.date <= 186) {
                    switch (this.date % 31) {
                        case 0:
                            this.month = this.date / 31;
                            this.date = 31;
                            break;
                        default:
                            this.month = (this.date / 31) + 1;
                            this.date %= 31;
                            break;
                    }
                    this.year = georgianYear - 621;
                    return;
                }
                this.date -= 186;
                switch (this.date % 30) {
                    case 0:
                        this.month = (this.date / 30) + 6;
                        this.date = 30;
                        break;
                    default:
                        this.month = (this.date / 30) + 7;
                        this.date %= 30;
                        break;
                }
                this.year = georgianYear - 621;
                return;
            }
            if (georgianYear <= 1996 || georgianYear % 4 != 1) {
                ld = 10;
            } else {
                ld = 11;
            }
            this.date += ld;
            switch (this.date % 30) {
                case 0:
                    this.month = (this.date / 30) + 9;
                    this.date = 30;
                    break;
                default:
                    this.month = (this.date / 30) + 10;
                    this.date %= 30;
                    break;
            }
            this.year = georgianYear - 622;
            return;
        }
        this.date = buf2[georgianMonth - 1] + georgianDate;
        if (georgianYear >= 1996) {
            ld = 79;
        } else {
            ld = 80;
        }
        if (this.date > ld) {
            this.date -= ld;
            if (this.date <= 186) {
                switch (this.date % 31) {
                    case 0:
                        this.month = this.date / 31;
                        this.date = 31;
                        break;
                    default:
                        this.month = (this.date / 31) + 1;
                        this.date %= 31;
                        break;
                }
                this.year = georgianYear - 621;
                return;
            }
            this.date -= 186;
            switch (this.date % 30) {
                case 0:
                    this.month = (this.date / 30) + 6;
                    this.date = 30;
                    break;
                default:
                    this.month = (this.date / 30) + 7;
                    this.date %= 30;
                    break;
            }
            this.year = georgianYear - 621;
            return;
        }
        this.date += 10;
        switch (this.date % 30) {
            case 0:
                this.month = (this.date / 30) + 9;
                this.date = 30;
                break;
            default:
                this.month = (this.date / 30) + 10;
                this.date %= 30;
                break;
        }
        this.year = georgianYear - 622;
    }

    public String getMonth() {
        String strMonth = "";
        switch (this.month) {
            case 1:
                return LocaleController.getString("Farvardin", R.string.Farvardin);
            case 2:
                return LocaleController.getString("Ordibehesht", R.string.Ordibehesht);
            case 3:
                return LocaleController.getString("Khordad", R.string.Khordad);
            case 4:
                return LocaleController.getString("Tir", R.string.Tir);
            case 5:
                return LocaleController.getString("Mordad", R.string.Mordad);
            case 6:
                return LocaleController.getString("Shahrivar", R.string.Shahrivar);
            case 7:
                return LocaleController.getString("Mehr", R.string.Mehr);
            case 8:
                return LocaleController.getString("Aban", R.string.Aban);
            case 9:
                return LocaleController.getString("Azar", R.string.Azar);
            case 10:
                return LocaleController.getString("Dey", R.string.Dey);
            case 11:
                return LocaleController.getString("Bahman", R.string.Bahman);
            case 12:
                return LocaleController.getString("Esfand", R.string.Esfand);
            default:
                return strMonth;
        }
    }

    public String getDesDate() {
        StringBuilder describedDateFormat = new StringBuilder();
        describedDateFormat.append(String.valueOf(this.date)).append(" ").append(getMonth()).append(" ").append(String.valueOf(this.year)).append(" ").append(LocaleController.getString("Saat", R.string.Saat)).append(" ").append(getTime());
        return String.valueOf(describedDateFormat);
    }

    public String getShortDesDateTime() {
        StringBuilder describedDateFormat = new StringBuilder();
        describedDateFormat.append(String.valueOf(this.date)).append(" ").append(getMonth()).append(" ").append(LocaleController.getString("Saat", R.string.Saat)).append(" ").append(getTime());
        return String.valueOf(describedDateFormat);
    }

    public String getShortDesDate() {
        StringBuilder describedDateFormat = new StringBuilder();
        describedDateFormat.append(String.valueOf(this.date)).append(" ").append(getMonth());
        return String.valueOf(describedDateFormat);
    }

    public String getDesMonthYear() {
        StringBuilder describedDateFormat = new StringBuilder();
        describedDateFormat.append(getMonth()).append(" ").append(String.valueOf(this.year));
        return String.valueOf(describedDateFormat);
    }

    public String getNumDateTime() {
        StringBuilder numericDateFormat = new StringBuilder();
        numericDateFormat.append(String.valueOf(this.year)).append("/").append(String.valueOf(this.month)).append("/").append(String.valueOf(this.date)).append(" ").append(LocaleController.getString("Saat", R.string.Saat)).append(" ").append(getTime());
        return String.valueOf(numericDateFormat);
    }

    public String getNumDate() {
        StringBuilder numericDateFormat = new StringBuilder();
        numericDateFormat.append(String.valueOf(this.year)).append("/").append(String.valueOf(this.month)).append("/").append(String.valueOf(this.date)).append(" ");
        return String.valueOf(numericDateFormat);
    }

    public String getTime() {
        boolean is24HourFormat = TurboConfig.is24Hours;
        int h = this.calendar.get(11);
        int m = this.calendar.get(12);
        StringBuilder time = new StringBuilder();
        if (is24HourFormat) {
            time.append(h).append(":").append(m);
        } else {
            Object obj;
            int i = h < 12 ? h : h == 12 ? 12 : h - 12;
            StringBuilder append = time.append(i).append(":");
            if (m < 10) {
                obj = "0" + m;
            } else {
                obj = Integer.valueOf(m);
            }
            append.append(obj).append(h < 12 ? " " + LocaleController.getString("AM", R.string.AM) : " " + LocaleController.getString("PM", R.string.PM));
        }
        return String.valueOf(time);
    }

    public String toString() {
        return getDesDate();
    }
}
