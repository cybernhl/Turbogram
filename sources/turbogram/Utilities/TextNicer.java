package turbogram.Utilities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ChatActivity;

public class TextNicer {
    private static ArrayList<String> list = new ArrayList();
    private static ArrayList<String> lista = new ArrayList();
    private static String other_ki;
    private static String other_km;

    /* renamed from: turbogram.Utilities.TextNicer$1 */
    static class C25011 implements OnClickListener {
        C25011() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == 0) {
                TurboConfig.setIntValue("nicewrite", 0);
            } else if (i == 1) {
                TurboConfig.setIntValue("nicewrite", 1);
            } else if (i == 2) {
                TurboConfig.setIntValue("nicewrite", 2);
            } else if (i == 3) {
                TurboConfig.setIntValue("nicewrite", 3);
            } else if (i == 4) {
                TurboConfig.setIntValue("nicewrite", 4);
            } else if (i == 5) {
                TurboConfig.setIntValue("nicewrite", 5);
            } else if (i == 6) {
                TurboConfig.setIntValue("nicewrite", 6);
            } else if (i == 7) {
                TurboConfig.setIntValue("nicewrite", 7);
            }
        }
    }

    public static void clean() {
        list.clear();
        lista.clear();
    }

    public static ArrayList<String> getList() {
        return list;
    }

    public static ArrayList<String> getLista() {
        return lista;
    }

    public static void showDialog(Activity parentActivity, ChatActivity parentFragment) {
        if (parentActivity != null && parentFragment != null) {
            Builder builder = new Builder(parentActivity);
            builder.setApplyTopPadding(true);
            builder.setTitle(LocaleController.getString("TextNicer", R.string.TextNicer));
            builder.setItems(new CharSequence[]{LocaleController.getString("Normal", R.string.Normal), LocaleController.getString("nicer1", R.string.nicer1), LocaleController.getString("nicer2", R.string.nicer2), LocaleController.getString("nicer3", R.string.nicer3), LocaleController.getString("nicer4", R.string.nicer4), LocaleController.getString("nicer5", R.string.nicer5), LocaleController.getString("nicer6", R.string.nicer6), LocaleController.getString("nicer7", R.string.nicer7)}, new C25011());
            parentFragment.showDialog(builder.create());
        }
    }

    public static void Splator(String splator) {
        for (String str : splator.split(" ")) {
            other_km = str;
            lista.add(other_km);
        }
    }

    public static ArrayList<String> arraysplator(ArrayList list) {
        int nicerMode = TurboConfig.textNicer;
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            if (s.contains("؟") || s.contains("،") || s.contains("?") || s.contains(",") || s.contains(":") || s.contains("(") || s.contains(")") || s.contains("!") || s.contains(";") || s.contains("[") || s.contains("]") || s.contains("}") || s.contains("{") || s.contains(".")) {
                if (nicerMode == 1) {
                    Change_name1(s);
                } else if (nicerMode == 2) {
                    Change_name2(s);
                } else if (nicerMode == 3) {
                    Change_name3(s);
                } else if (nicerMode == 4) {
                    Change_name4(s);
                } else if (nicerMode == 5) {
                    Change_name5(s);
                } else if (nicerMode == 6) {
                    Change_name6(s);
                } else if (nicerMode == 7) {
                    Change_name7(s);
                } else if (nicerMode == 8) {
                    Change_name8(s);
                }
            } else if (nicerMode == 1) {
                Change_name1(s + " ");
            } else if (nicerMode == 2) {
                Change_name2(s + " ");
            } else if (nicerMode == 3) {
                Change_name3(s + " ");
            } else if (nicerMode == 4) {
                Change_name4(s + " ");
            } else if (nicerMode == 5) {
                Change_name5(s + " ");
            } else if (nicerMode == 6) {
                Change_name6(s + " ");
            } else if (nicerMode == 7) {
                Change_name7(s + " ");
            } else if (nicerMode == 8) {
                Change_name8(s + " ");
            }
        }
        return list;
    }

    public static String Change_name1(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("ے");
                } else if (other_ki.equals("ک")) {
                    list.add("ڪ");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                list.add(index[i]);
            }
        }
        return change_en;
    }

    public static String Change_name2(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                list.add(index[i]);
            } else if (i <= index.length) {
                if (other_ki.equals("ی")) {
                    list.add("یــ");
                } else if (other_ki.equals("ل")) {
                    list.add("لــ");
                } else if (other_ki.equals("ض")) {
                    list.add("ضــ");
                } else if (other_ki.equals("ث")) {
                    list.add("ثــ");
                } else if (other_ki.equals("ق")) {
                    list.add("قــ");
                } else if (other_ki.equals("ف")) {
                    list.add("فــ");
                } else if (other_ki.equals("غ")) {
                    list.add("غــ");
                } else if (other_ki.equals("ع")) {
                    list.add("عــ");
                } else if (other_ki.equals("خ")) {
                    list.add("خــ");
                } else if (other_ki.equals("ج")) {
                    list.add("جــ");
                } else if (other_ki.equals("چ")) {
                    list.add("چــ");
                } else if (other_ki.equals("ب")) {
                    list.add("بــ");
                } else if (other_ki.equals("س")) {
                    list.add("ســ");
                } else if (other_ki.equals("ک")) {
                    list.add("ڪــ");
                } else if (other_ki.equals("گ")) {
                    list.add("گــ");
                } else if (other_ki.equals("پ")) {
                    list.add("پــ");
                } else if (other_ki.equals("م")) {
                    list.add("مــ");
                } else if (other_ki.equals("ت")) {
                    list.add("تــ");
                } else if (other_ki.equals("ش")) {
                    list.add("شــ");
                } else if (other_ki.equals("ن")) {
                    list.add("نــ");
                } else if (other_ki.equals("ط")) {
                    list.add("طــ");
                } else if (other_ki.equals("ح")) {
                    list.add("حــ");
                } else if (other_ki.equals("ظ")) {
                    list.add("ظــ");
                } else if (other_ki.equals("ص")) {
                    list.add("صــ");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public static String Change_name3(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("ے");
                } else if (other_ki.equals("ک")) {
                    list.add("ڪ");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("ی")) {
                    list.add("یــ");
                } else if (other_ki.equals("ل")) {
                    list.add("لــ");
                } else if (other_ki.equals("ض")) {
                    list.add("ضــ");
                } else if (other_ki.equals("ث")) {
                    list.add("ثــ");
                } else if (other_ki.equals("ق")) {
                    list.add("قــ");
                } else if (other_ki.equals("ف")) {
                    list.add("فــ");
                } else if (other_ki.equals("غ")) {
                    list.add("غــ");
                } else if (other_ki.equals("ع")) {
                    list.add("عــ");
                } else if (other_ki.equals("خ")) {
                    list.add("خــ");
                } else if (other_ki.equals("ج")) {
                    list.add("جــ");
                } else if (other_ki.equals("چ")) {
                    list.add("چــ");
                } else if (other_ki.equals("ب")) {
                    list.add("بــ");
                } else if (other_ki.equals("س")) {
                    list.add("ســ");
                } else if (other_ki.equals("ک")) {
                    list.add("ڪــ");
                } else if (other_ki.equals("گ")) {
                    list.add("گــ");
                } else if (other_ki.equals("پ")) {
                    list.add("پــ");
                } else if (other_ki.equals("م")) {
                    list.add("مــ");
                } else if (other_ki.equals("ت")) {
                    list.add("تــ");
                } else if (other_ki.equals("ش")) {
                    list.add("شــ");
                } else if (other_ki.equals("ن")) {
                    list.add("نــ");
                } else if (other_ki.equals("ط")) {
                    list.add("طــ");
                } else if (other_ki.equals("ح")) {
                    list.add("حــ");
                } else if (other_ki.equals("ظ")) {
                    list.add("ظــ");
                } else if (other_ki.equals("ص")) {
                    list.add("صــ");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public static String Change_name4(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("ے");
                } else if (other_ki.equals("ک")) {
                    list.add("̈́ڪ̈́");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("ی")) {
                    list.add("ی̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ل")) {
                    list.add("̈́ل̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ض")) {
                    list.add("̈́ض̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ث")) {
                    list.add("̈́ث̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ق")) {
                    list.add("̈́ق̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ف")) {
                    list.add("̈́ف̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("غ")) {
                    list.add("̈́غ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ع")) {
                    list.add("̈́ع̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("خ")) {
                    list.add("̈́خ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ج")) {
                    list.add("̈́ج̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("چ")) {
                    list.add("̈́چ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ب")) {
                    list.add("̈́ب̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("س")) {
                    list.add("̈́س̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ک")) {
                    list.add("̈́ڪ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("گ")) {
                    list.add("̈́گ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("پ")) {
                    list.add("̈́پ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("م")) {
                    list.add("̈́م̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ت")) {
                    list.add("̈́ت̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ش")) {
                    list.add("̈́ش̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ن")) {
                    list.add("̈́ن̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ط")) {
                    list.add("̈́ط̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ح")) {
                    list.add("̈́ح̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ظ")) {
                    list.add("̈́ظ̈́ـ̈́ـ̈́");
                } else if (other_ki.equals("ص")) {
                    list.add("̈́ص̈́ـ̈́ـ̈́");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public static String Change_name5(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("ے");
                } else if (other_ki.equals("ک")) {
                    list.add("ڪ");
                } else if (other_ki.equals("ض")) {
                    list.add("ض");
                } else if (other_ki.equals("ث")) {
                    list.add("ث");
                } else if (other_ki.equals("ق")) {
                    list.add("ق");
                } else if (other_ki.equals("ف")) {
                    list.add("ف");
                } else if (other_ki.equals("غ")) {
                    list.add("غ");
                } else if (other_ki.equals("ع")) {
                    list.add("ع");
                } else if (other_ki.equals("خ")) {
                    list.add("خ");
                } else if (other_ki.equals("ج")) {
                    list.add("ج");
                } else if (other_ki.equals("چ")) {
                    list.add("چ");
                } else if (other_ki.equals("ب")) {
                    list.add("ب");
                } else if (other_ki.equals("س")) {
                    list.add("س");
                } else if (other_ki.equals("ک")) {
                    list.add("ک");
                } else if (other_ki.equals("گ")) {
                    list.add("گ");
                } else if (other_ki.equals("پ")) {
                    list.add("پ");
                } else if (other_ki.equals("م")) {
                    list.add("م");
                } else if (other_ki.equals("ت")) {
                    list.add("ت");
                } else if (other_ki.equals("ش")) {
                    list.add("ش");
                } else if (other_ki.equals("ن")) {
                    list.add("ن");
                } else if (other_ki.equals("ط")) {
                    list.add("ط");
                } else if (other_ki.equals("ح")) {
                    list.add("ح");
                } else if (other_ki.equals("ظ")) {
                    list.add("ظ");
                } else if (other_ki.equals("ص")) {
                    list.add("ص");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("ی")) {
                    list.add("̶̶ی̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ل")) {
                    list.add("̶̶ل̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ض")) {
                    list.add("̶̶ض̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ث")) {
                    list.add("̶̶ث̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ق")) {
                    list.add("̶̶ق̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ف")) {
                    list.add("̶̶ف̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("غ")) {
                    list.add("̶̶غ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ع")) {
                    list.add("̶̶ع̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("خ")) {
                    list.add("̶̶خ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ج")) {
                    list.add("̶̶ج̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("چ")) {
                    list.add("̶̶چ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ب")) {
                    list.add("̶̶ب̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("س")) {
                    list.add("̶̶س̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ک")) {
                    list.add("̶̶ڪ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("گ")) {
                    list.add("̶̶گ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("پ")) {
                    list.add("̶̶پ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("م")) {
                    list.add("̶̶م̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ت")) {
                    list.add("̶̶ت̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ش")) {
                    list.add("̶̶ش̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ن")) {
                    list.add("̶̶ن̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ط")) {
                    list.add("̶̶ط̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ح")) {
                    list.add("̶̶ح̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ظ")) {
                    list.add("̶̶ظ̶̶ـ̶̶ـ̶̶");
                } else if (other_ki.equals("ص")) {
                    list.add("̶̶ص̶̶ـ̶̶ـ̶̶");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public static String Change_name6(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("ے");
                } else if (other_ki.equals("ک")) {
                    list.add("̅̅ڪ̅̅");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("ی")) {
                    list.add("̅̅ی̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ل")) {
                    list.add("̅̅ل̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ض")) {
                    list.add("̅̅ض̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ث")) {
                    list.add("̅̅ث̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ق")) {
                    list.add("̅̅ق̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ف")) {
                    list.add("̅̅ف̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("غ")) {
                    list.add("̅̅غ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ع")) {
                    list.add("̅̅ع̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("خ")) {
                    list.add("̅̅خ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ج")) {
                    list.add("̅̅ج̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("چ")) {
                    list.add("̅̅چ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ب")) {
                    list.add("̅̅ب̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("س")) {
                    list.add("̅̅س̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ک")) {
                    list.add("̅̅ڪ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("گ")) {
                    list.add("̅̅گ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("پ")) {
                    list.add("̅̅پ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("م")) {
                    list.add("̅̅م̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ت")) {
                    list.add("̅̅ت̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ش")) {
                    list.add("̅̅ش̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ن")) {
                    list.add("̅̅ن̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ط")) {
                    list.add("̅̅ط̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ح")) {
                    list.add("̅̅ح̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ظ")) {
                    list.add("̅̅ظ̅̅ـ̅̅ـ̅̅");
                } else if (other_ki.equals("ص")) {
                    list.add("̅̅ص̅̅ـ̅̅ـ̅̅");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public static String Change_name7(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("̑ےِ̑");
                } else if (other_ki.equals("ک")) {
                    list.add("̑ڪ̑");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("ی")) {
                    list.add("̑ی̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ل")) {
                    list.add("̑ل̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ض")) {
                    list.add("̑ض̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ث")) {
                    list.add("̑ث̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ق")) {
                    list.add("̑ق̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ف")) {
                    list.add("̑ف̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("غ")) {
                    list.add("̑غ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ع")) {
                    list.add("̑ع̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("خ")) {
                    list.add("̑خ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ج")) {
                    list.add("̑ج̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("چ")) {
                    list.add("̑چ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ب")) {
                    list.add("̑ب̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("س")) {
                    list.add("̑س̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ک")) {
                    list.add("̑ڪ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("گ")) {
                    list.add("̑گ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("پ")) {
                    list.add("̑پ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("م")) {
                    list.add("̑م̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ت")) {
                    list.add("̑ت̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ش")) {
                    list.add("̑ش̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ن")) {
                    list.add("̑ن̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ط")) {
                    list.add("̑ط̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ح")) {
                    list.add("̑ح̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ظ")) {
                    list.add("̑ظ̑̑ـ̑̑ـ̑");
                } else if (other_ki.equals("ص")) {
                    list.add("̑ص̑̑ـ̑̑ـ̑");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public static String Change_name8(String change_en) {
        String[] index = change_en.split("");
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("ی")) {
                    list.add("ــےِ");
                } else if (other_ki.equals("ک")) {
                    list.add("ڪ");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("آ")) {
                    list.add("آ");
                } else if (other_ki.equals("ا")) {
                    list.add("ا");
                } else if (other_ki.equals("أ")) {
                    list.add("أ");
                } else if (other_ki.equals("إ")) {
                    list.add("إ");
                } else if (other_ki.equals("ض")) {
                    list.add("ۻ");
                } else if (other_ki.equals("ذ")) {
                    list.add("ذ");
                } else if (other_ki.equals("ز")) {
                    list.add("ز");
                } else if (other_ki.equals("ژ")) {
                    list.add("ژ");
                } else if (other_ki.equals("ق")) {
                    list.add("ق");
                } else if (other_ki.equals("ف")) {
                    list.add("ڣ");
                } else if (other_ki.equals("غ")) {
                    list.add("غ");
                } else if (other_ki.equals("ع")) {
                    list.add("ع");
                } else if (other_ki.equals("خ")) {
                    list.add("خ");
                } else if (other_ki.equals("ج")) {
                    list.add("ڄ");
                } else if (other_ki.equals("چ")) {
                    list.add("چ");
                } else if (other_ki.equals("د")) {
                    list.add("د");
                } else if (other_ki.equals("ب")) {
                    list.add("ب");
                } else if (other_ki.equals("ل")) {
                    list.add("ݪ");
                } else if (other_ki.equals("ی")) {
                    list.add("ی");
                } else if (other_ki.equals("س")) {
                    list.add("س");
                } else if (other_ki.equals("ک")) {
                    list.add("ک");
                } else if (other_ki.equals("گ")) {
                    list.add("ڳ");
                } else if (other_ki.equals("پ")) {
                    list.add("ڀ");
                } else if (other_ki.equals("ر")) {
                    list.add("ڔ");
                } else if (other_ki.equals("و")) {
                    list.add("ۅ");
                } else if (other_ki.equals("م")) {
                    list.add("ݦ");
                } else if (other_ki.equals("ت")) {
                    list.add("ټ");
                } else if (other_ki.equals("ه")) {
                    list.add("ه");
                } else if (other_ki.equals("ش")) {
                    list.add("ۺ");
                } else if (other_ki.equals("ن")) {
                    list.add("ݧ");
                } else if (other_ki.equals("ط")) {
                    list.add("ط");
                } else if (other_ki.equals("ح")) {
                    list.add("ځ");
                } else if (other_ki.equals("ث")) {
                    list.add("ث");
                } else if (other_ki.equals("ظ")) {
                    list.add("ظ");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }
}
