package com.google.android.gms.internal.vision;

final class zzfb {
    static String zzd(zzbo zzbo) {
        zzfd zzfc = new zzfc(zzbo);
        StringBuilder stringBuilder = new StringBuilder(zzfc.size());
        for (int i = 0; i < zzfc.size(); i++) {
            byte zzl = zzfc.zzl(i);
            switch (zzl) {
                case (byte) 7:
                    stringBuilder.append("\\a");
                    break;
                case (byte) 8:
                    stringBuilder.append("\\b");
                    break;
                case (byte) 9:
                    stringBuilder.append("\\t");
                    break;
                case (byte) 10:
                    stringBuilder.append("\\n");
                    break;
                case (byte) 11:
                    stringBuilder.append("\\v");
                    break;
                case (byte) 12:
                    stringBuilder.append("\\f");
                    break;
                case (byte) 13:
                    stringBuilder.append("\\r");
                    break;
                case (byte) 34:
                    stringBuilder.append("\\\"");
                    break;
                case (byte) 39:
                    stringBuilder.append("\\'");
                    break;
                case (byte) 92:
                    stringBuilder.append("\\\\");
                    break;
                default:
                    if (zzl >= (byte) 32 && zzl <= (byte) 126) {
                        stringBuilder.append((char) zzl);
                        break;
                    }
                    stringBuilder.append('\\');
                    stringBuilder.append((char) (((zzl >>> 6) & 3) + 48));
                    stringBuilder.append((char) (((zzl >>> 3) & 7) + 48));
                    stringBuilder.append((char) ((zzl & 7) + 48));
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
