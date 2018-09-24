package org.telegram.ui.ActionBar;

import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.v4.text.HtmlCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.SpannedString;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.ads.AdSize;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.vending.licensing.Policy;
import com.googlecode.mp4parser.authoring.tracks.h265.NalUnitTypes;
import com.googlecode.mp4parser.boxes.microsoft.XtraBox;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatBigEmptyView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.GroupCreateCheckBox;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Components.TypefaceSpan;
import turbogram.Components.TabsView;

public class ThemeDescription {
    public static int FLAG_AB_AM_BACKGROUND = 1048576;
    public static int FLAG_AB_AM_ITEMSCOLOR = 512;
    public static int FLAG_AB_AM_SELECTORCOLOR = 4194304;
    public static int FLAG_AB_AM_TOPBACKGROUND = 2097152;
    public static int FLAG_AB_ITEMSCOLOR = 64;
    public static int FLAG_AB_SEARCH = 134217728;
    public static int FLAG_AB_SEARCHPLACEHOLDER = ConnectionsManager.FileTypeFile;
    public static int FLAG_AB_SELECTORCOLOR = 256;
    public static int FLAG_AB_SUBMENUBACKGROUND = Integer.MIN_VALUE;
    public static int FLAG_AB_SUBMENUITEM = 1073741824;
    public static int FLAG_AB_SUBTITLECOLOR = 1024;
    public static int FLAG_AB_TITLECOLOR = 128;
    public static int FLAG_BACKGROUND = 1;
    public static int FLAG_BACKGROUNDFILTER = 32;
    public static int FLAG_CELLBACKGROUNDCOLOR = 16;
    public static int FLAG_CHECKBOX = 8192;
    public static int FLAG_CHECKBOXCHECK = 16384;
    public static int FLAG_CHECKTAG = 262144;
    public static int FLAG_CURSORCOLOR = 16777216;
    public static int FLAG_DRAWABLESELECTEDSTATE = 65536;
    public static int FLAG_FASTSCROLL = ConnectionsManager.FileTypeVideo;
    public static int FLAG_HINTTEXTCOLOR = 8388608;
    public static int FLAG_IMAGECOLOR = 8;
    public static int FLAG_LINKCOLOR = 2;
    public static int FLAG_LISTGLOWCOLOR = 32768;
    public static int FLAG_PROGRESSBAR = 2048;
    public static int FLAG_SECTIONS = 524288;
    public static int FLAG_SELECTOR = 4096;
    public static int FLAG_SELECTORWHITE = 268435456;
    public static int FLAG_SERVICEBACKGROUND = 536870912;
    public static int FLAG_TEXTCOLOR = 4;
    public static int FLAG_USEBACKGROUNDDRAWABLE = 131072;
    private HashMap<String, Field> cachedFields;
    private int changeFlags;
    private int currentColor;
    private String currentKey;
    private int defaultColor;
    private ThemeDescriptionDelegate delegate;
    private Drawable[] drawablesToUpdate;
    private Class[] listClasses;
    private String[] listClassesFieldName;
    public boolean needDivider;
    private HashMap<String, Boolean> notFoundCachedFields;
    private Paint[] paintToUpdate;
    private int previousColor;
    private boolean[] previousIsDefault;
    private View viewToInvalidate;

    public interface ThemeDescriptionDelegate {
        void didSetColor();
    }

    public ThemeDescription(View view, int flags, Class[] classes, Paint[] paint, Drawable[] drawables, ThemeDescriptionDelegate themeDescriptionDelegate, String key, Object unused, boolean divider) {
        this.previousIsDefault = new boolean[1];
        this.needDivider = false;
        this.currentKey = key;
        this.paintToUpdate = paint;
        this.drawablesToUpdate = drawables;
        this.viewToInvalidate = view;
        this.changeFlags = flags;
        this.listClasses = classes;
        this.delegate = themeDescriptionDelegate;
        this.needDivider = divider;
    }

    public ThemeDescription(View view, int flags, Class[] classes, Paint paint, Drawable[] drawables, ThemeDescriptionDelegate themeDescriptionDelegate, String key, boolean divider) {
        this.previousIsDefault = new boolean[1];
        this.needDivider = false;
        this.currentKey = key;
        if (paint != null) {
            this.paintToUpdate = new Paint[]{paint};
        }
        this.drawablesToUpdate = drawables;
        this.viewToInvalidate = view;
        this.changeFlags = flags;
        this.listClasses = classes;
        this.delegate = themeDescriptionDelegate;
        this.needDivider = divider;
    }

    public ThemeDescription(View view, int flags, Class[] classes, String[] classesFields, Paint[] paint, Drawable[] drawables, ThemeDescriptionDelegate themeDescriptionDelegate, String key, boolean divider) {
        this.previousIsDefault = new boolean[1];
        this.needDivider = false;
        this.currentKey = key;
        this.paintToUpdate = paint;
        this.drawablesToUpdate = drawables;
        this.viewToInvalidate = view;
        this.changeFlags = flags;
        this.listClasses = classes;
        this.listClassesFieldName = classesFields;
        this.delegate = themeDescriptionDelegate;
        this.cachedFields = new HashMap();
        this.needDivider = divider;
    }

    public ThemeDescription(View view, int flags, Class[] classes, Paint[] paint, Drawable[] drawables, ThemeDescriptionDelegate themeDescriptionDelegate, String key, Object unused) {
        this.previousIsDefault = new boolean[1];
        this.needDivider = false;
        this.currentKey = key;
        this.paintToUpdate = paint;
        this.drawablesToUpdate = drawables;
        this.viewToInvalidate = view;
        this.changeFlags = flags;
        this.listClasses = classes;
        this.delegate = themeDescriptionDelegate;
    }

    public ThemeDescription(View view, int flags, Class[] classes, Paint paint, Drawable[] drawables, ThemeDescriptionDelegate themeDescriptionDelegate, String key) {
        this.previousIsDefault = new boolean[1];
        this.needDivider = false;
        this.currentKey = key;
        if (paint != null) {
            this.paintToUpdate = new Paint[]{paint};
        }
        this.drawablesToUpdate = drawables;
        this.viewToInvalidate = view;
        this.changeFlags = flags;
        this.listClasses = classes;
        this.delegate = themeDescriptionDelegate;
    }

    public ThemeDescription(View view, int flags, Class[] classes, String[] classesFields, Paint[] paint, Drawable[] drawables, ThemeDescriptionDelegate themeDescriptionDelegate, String key) {
        this.previousIsDefault = new boolean[1];
        this.needDivider = false;
        this.currentKey = key;
        this.paintToUpdate = paint;
        this.drawablesToUpdate = drawables;
        this.viewToInvalidate = view;
        this.changeFlags = flags;
        this.listClasses = classes;
        this.listClassesFieldName = classesFields;
        this.delegate = themeDescriptionDelegate;
        this.cachedFields = new HashMap();
        this.notFoundCachedFields = new HashMap();
    }

    public ThemeDescriptionDelegate setDelegateDisabled() {
        ThemeDescriptionDelegate oldDelegate = this.delegate;
        this.delegate = null;
        return oldDelegate;
    }

    public void setColor(int color, boolean useDefault) {
        setColor(color, useDefault, true);
    }

    private boolean checkTag(String key, View view) {
        if (key == null || view == null) {
            return false;
        }
        Object viewTag = view.getTag();
        if (viewTag instanceof String) {
            return ((String) viewTag).contains(key);
        }
        return false;
    }

    public void setColor(int color, boolean useDefault, boolean save) {
        int a;
        Drawable drawable;
        RecyclerListView recyclerListView;
        if (save) {
            Theme.setColor(this.currentKey, color, useDefault);
        }
        if (this.paintToUpdate != null) {
            a = 0;
            while (a < this.paintToUpdate.length) {
                if ((this.changeFlags & FLAG_LINKCOLOR) == 0 || !(this.paintToUpdate[a] instanceof TextPaint)) {
                    this.paintToUpdate[a].setColor(color);
                } else {
                    ((TextPaint) this.paintToUpdate[a]).linkColor = color;
                }
                a++;
            }
        }
        if (this.drawablesToUpdate != null) {
            for (a = 0; a < this.drawablesToUpdate.length; a++) {
                if (this.drawablesToUpdate[a] != null) {
                    if (this.drawablesToUpdate[a] instanceof CombinedDrawable) {
                        if ((this.changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
                            ((CombinedDrawable) this.drawablesToUpdate[a]).getBackground().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                        } else {
                            ((CombinedDrawable) this.drawablesToUpdate[a]).getIcon().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                        }
                    } else if (this.drawablesToUpdate[a] instanceof AvatarDrawable) {
                        ((AvatarDrawable) this.drawablesToUpdate[a]).setColor(color);
                    } else {
                        this.drawablesToUpdate[a].setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                    }
                }
            }
        }
        if (this.viewToInvalidate != null && this.listClasses == null && this.listClassesFieldName == null && ((this.changeFlags & FLAG_CHECKTAG) == 0 || checkTag(this.currentKey, this.viewToInvalidate))) {
            if ((this.changeFlags & FLAG_BACKGROUND) != 0) {
                this.viewToInvalidate.setBackgroundColor(color);
            }
            if ((this.changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
                if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) {
                    drawable = this.viewToInvalidate.getBackground();
                    if (drawable instanceof CombinedDrawable) {
                        if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0) {
                            drawable = ((CombinedDrawable) drawable).getBackground();
                        } else {
                            drawable = ((CombinedDrawable) drawable).getIcon();
                        }
                    }
                    if (drawable != null) {
                        if ((drawable instanceof StateListDrawable) || (VERSION.SDK_INT >= 21 && (drawable instanceof RippleDrawable))) {
                            boolean z;
                            if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            Theme.setSelectorDrawableColor(drawable, color, z);
                        } else if (drawable instanceof ShapeDrawable) {
                            ((ShapeDrawable) drawable).getPaint().setColor(color);
                        } else {
                            drawable.setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                        }
                    }
                } else if (this.viewToInvalidate instanceof EditTextBoldCursor) {
                    ((EditTextBoldCursor) this.viewToInvalidate).setErrorLineColor(color);
                }
            }
        }
        if (this.viewToInvalidate instanceof TabsView) {
            ((TabsView) this.viewToInvalidate).updateCounters();
        }
        if (this.viewToInvalidate instanceof ActionBar) {
            if ((this.changeFlags & FLAG_AB_ITEMSCOLOR) != 0) {
                ((ActionBar) this.viewToInvalidate).setItemsColor(color, false);
            }
            if ((this.changeFlags & FLAG_AB_TITLECOLOR) != 0) {
                ((ActionBar) this.viewToInvalidate).setTitleColor(color);
            }
            if ((this.changeFlags & FLAG_AB_SELECTORCOLOR) != 0) {
                ((ActionBar) this.viewToInvalidate).setItemsBackgroundColor(color, false);
            }
            if ((this.changeFlags & FLAG_AB_AM_SELECTORCOLOR) != 0) {
                ((ActionBar) this.viewToInvalidate).setItemsBackgroundColor(color, true);
            }
            if ((this.changeFlags & FLAG_AB_AM_ITEMSCOLOR) != 0) {
                ((ActionBar) this.viewToInvalidate).setItemsColor(color, true);
            }
            if ((this.changeFlags & FLAG_AB_SUBTITLECOLOR) != 0) {
                ((ActionBar) this.viewToInvalidate).setSubtitleColor(color);
            }
            if ((this.changeFlags & FLAG_AB_AM_BACKGROUND) != 0) {
                ((ActionBar) this.viewToInvalidate).setActionModeColor(color);
            }
            if ((this.changeFlags & FLAG_AB_AM_TOPBACKGROUND) != 0) {
                ((ActionBar) this.viewToInvalidate).setActionModeTopColor(color);
            }
            if ((this.changeFlags & FLAG_AB_SEARCHPLACEHOLDER) != 0) {
                ((ActionBar) this.viewToInvalidate).setSearchTextColor(color, true);
            }
            if ((this.changeFlags & FLAG_AB_SEARCH) != 0) {
                ((ActionBar) this.viewToInvalidate).setSearchTextColor(color, false);
            }
            if ((this.changeFlags & FLAG_AB_SUBMENUITEM) != 0) {
                ((ActionBar) this.viewToInvalidate).setPopupItemsColor(color);
            }
            if ((this.changeFlags & FLAG_AB_SUBMENUBACKGROUND) != 0) {
                ((ActionBar) this.viewToInvalidate).setPopupBackgroundColor(color);
            }
        }
        if (this.viewToInvalidate instanceof EmptyTextProgressView) {
            if ((this.changeFlags & FLAG_TEXTCOLOR) != 0) {
                ((EmptyTextProgressView) this.viewToInvalidate).setTextColor(color);
            } else if ((this.changeFlags & FLAG_PROGRESSBAR) != 0) {
                ((EmptyTextProgressView) this.viewToInvalidate).setProgressBarColor(color);
            }
        }
        if (this.viewToInvalidate instanceof RadialProgressView) {
            ((RadialProgressView) this.viewToInvalidate).setProgressColor(color);
        } else if (this.viewToInvalidate instanceof LineProgressView) {
            if ((this.changeFlags & FLAG_PROGRESSBAR) != 0) {
                ((LineProgressView) this.viewToInvalidate).setProgressColor(color);
            } else {
                ((LineProgressView) this.viewToInvalidate).setBackColor(color);
            }
        } else if (this.viewToInvalidate instanceof ContextProgressView) {
            ((ContextProgressView) this.viewToInvalidate).updateColors();
        }
        if ((this.changeFlags & FLAG_TEXTCOLOR) != 0 && ((this.changeFlags & FLAG_CHECKTAG) == 0 || checkTag(this.currentKey, this.viewToInvalidate))) {
            if (this.viewToInvalidate instanceof TextView) {
                ((TextView) this.viewToInvalidate).setTextColor(color);
            } else if (this.viewToInvalidate instanceof NumberTextView) {
                ((NumberTextView) this.viewToInvalidate).setTextColor(color);
            } else if (this.viewToInvalidate instanceof SimpleTextView) {
                ((SimpleTextView) this.viewToInvalidate).setTextColor(color);
            } else if (this.viewToInvalidate instanceof ChatBigEmptyView) {
                ((ChatBigEmptyView) this.viewToInvalidate).setTextColor(color);
            }
        }
        if ((this.changeFlags & FLAG_CURSORCOLOR) != 0 && (this.viewToInvalidate instanceof EditTextBoldCursor)) {
            ((EditTextBoldCursor) this.viewToInvalidate).setCursorColor(color);
        }
        if ((this.changeFlags & FLAG_HINTTEXTCOLOR) != 0) {
            if (this.viewToInvalidate instanceof EditTextBoldCursor) {
                if ((this.changeFlags & FLAG_PROGRESSBAR) != 0) {
                    ((EditTextBoldCursor) this.viewToInvalidate).setHeaderHintColor(color);
                } else {
                    ((EditTextBoldCursor) this.viewToInvalidate).setHintColor(color);
                }
            } else if (this.viewToInvalidate instanceof EditText) {
                ((EditText) this.viewToInvalidate).setHintTextColor(color);
            }
        }
        if (!(this.viewToInvalidate == null || (this.changeFlags & FLAG_SERVICEBACKGROUND) == 0)) {
            Drawable background = this.viewToInvalidate.getBackground();
            if (background != null) {
                background.setColorFilter(Theme.colorFilter);
            }
        }
        if ((this.changeFlags & FLAG_IMAGECOLOR) != 0 && ((this.changeFlags & FLAG_CHECKTAG) == 0 || checkTag(this.currentKey, this.viewToInvalidate))) {
            if (this.viewToInvalidate instanceof ImageView) {
                if ((this.changeFlags & FLAG_USEBACKGROUNDDRAWABLE) != 0) {
                    drawable = ((ImageView) this.viewToInvalidate).getDrawable();
                    if ((drawable instanceof StateListDrawable) || (VERSION.SDK_INT >= 21 && (drawable instanceof RippleDrawable))) {
                        Theme.setSelectorDrawableColor(drawable, color, (this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0);
                    }
                } else {
                    ((ImageView) this.viewToInvalidate).setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                }
            } else if (this.viewToInvalidate instanceof BackupImageView) {
            }
        }
        if ((this.viewToInvalidate instanceof ScrollView) && (this.changeFlags & FLAG_LISTGLOWCOLOR) != 0) {
            AndroidUtilities.setScrollViewEdgeEffectColor((ScrollView) this.viewToInvalidate, color);
        }
        if (this.viewToInvalidate instanceof RecyclerListView) {
            recyclerListView = this.viewToInvalidate;
            if ((this.changeFlags & FLAG_SELECTOR) != 0 && this.currentKey.equals(Theme.key_listSelector)) {
                recyclerListView.setListSelectorColor(color);
            }
            if ((this.changeFlags & FLAG_FASTSCROLL) != 0) {
                recyclerListView.updateFastScrollColors();
            }
            if ((this.changeFlags & FLAG_LISTGLOWCOLOR) != 0) {
                recyclerListView.setGlowColor(color);
            }
            if ((this.changeFlags & FLAG_SECTIONS) != 0) {
                ArrayList<View> headers = recyclerListView.getHeaders();
                if (headers != null) {
                    for (a = 0; a < headers.size(); a++) {
                        processViewColor((View) headers.get(a), color);
                    }
                }
                headers = recyclerListView.getHeadersCache();
                if (headers != null) {
                    for (a = 0; a < headers.size(); a++) {
                        processViewColor((View) headers.get(a), color);
                    }
                }
                View header = recyclerListView.getPinnedHeader();
                if (header != null) {
                    processViewColor(header, color);
                }
            }
        } else if (this.viewToInvalidate != null && (this.listClasses == null || this.listClasses.length == 0)) {
            if ((this.changeFlags & FLAG_SELECTOR) != 0) {
                this.viewToInvalidate.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            } else if ((this.changeFlags & FLAG_SELECTORWHITE) != 0) {
                this.viewToInvalidate.setBackgroundDrawable(Theme.getSelectorDrawable(true));
            }
        }
        if (this.listClasses != null) {
            int count;
            if (this.viewToInvalidate instanceof RecyclerListView) {
                recyclerListView = (RecyclerListView) this.viewToInvalidate;
                recyclerListView.getRecycledViewPool().clear();
                count = recyclerListView.getHiddenChildCount();
                for (a = 0; a < count; a++) {
                    processViewColor(recyclerListView.getHiddenChildAt(a), color);
                }
                count = recyclerListView.getCachedChildCount();
                for (a = 0; a < count; a++) {
                    processViewColor(recyclerListView.getCachedChildAt(a), color);
                }
                count = recyclerListView.getAttachedScrapChildCount();
                for (a = 0; a < count; a++) {
                    processViewColor(recyclerListView.getAttachedScrapChildAt(a), color);
                }
            }
            if (this.viewToInvalidate instanceof ViewGroup) {
                ViewGroup viewGroup = this.viewToInvalidate;
                count = viewGroup.getChildCount();
                for (a = 0; a < count; a++) {
                    processViewColor(viewGroup.getChildAt(a), color);
                }
            }
            processViewColor(this.viewToInvalidate, color);
        }
        this.currentColor = color;
        if (this.delegate != null) {
            this.delegate.didSetColor();
        }
        if (this.viewToInvalidate != null) {
            this.viewToInvalidate.invalidate();
        }
    }

    private void processViewColor(View child, int color) {
        for (int b = 0; b < this.listClasses.length; b++) {
            if (this.listClasses[b].isInstance(child)) {
                boolean passedCheck;
                String key;
                Field field;
                Object obj;
                TextView textView;
                Drawable[] drawables;
                int a;
                CharSequence text;
                TypefaceSpan[] spans;
                Drawable drawable;
                child.invalidate();
                if ((this.changeFlags & FLAG_CHECKTAG) != 0) {
                    if (!checkTag(this.currentKey, child)) {
                        passedCheck = false;
                        if (this.listClassesFieldName != null) {
                            key = this.listClasses[b] + "_" + this.listClassesFieldName[b];
                            if (this.notFoundCachedFields != null || !this.notFoundCachedFields.containsKey(key)) {
                                field = (Field) this.cachedFields.get(key);
                                if (field == null) {
                                    field = this.listClasses[b].getDeclaredField(this.listClassesFieldName[b]);
                                    if (field != null) {
                                        field.setAccessible(true);
                                        this.cachedFields.put(key, field);
                                    }
                                }
                                if (field != null) {
                                    obj = field.get(child);
                                    if (obj == null) {
                                        if (!passedCheck && (obj instanceof View)) {
                                            if (!checkTag(this.currentKey, (View) obj)) {
                                            }
                                        }
                                        if (obj instanceof View) {
                                            ((View) obj).invalidate();
                                        }
                                        if ((this.changeFlags & FLAG_USEBACKGROUNDDRAWABLE) != 0 && (obj instanceof View)) {
                                            obj = ((View) obj).getBackground();
                                        }
                                        if ((this.changeFlags & FLAG_BACKGROUND) == 0 && (obj instanceof View)) {
                                            ((View) obj).setBackgroundColor(color);
                                        } else {
                                            try {
                                                if (obj instanceof Switch) {
                                                    ((Switch) obj).checkColorFilters();
                                                } else if (obj instanceof EditTextCaption) {
                                                    if (obj instanceof SimpleTextView) {
                                                        if (obj instanceof TextView) {
                                                            textView = (TextView) obj;
                                                            if ((this.changeFlags & FLAG_IMAGECOLOR) != 0) {
                                                                drawables = textView.getCompoundDrawables();
                                                                if (drawables != null) {
                                                                    for (a = 0; a < drawables.length; a++) {
                                                                        if (drawables[a] != null) {
                                                                            drawables[a].setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                                        }
                                                                    }
                                                                }
                                                            } else if ((this.changeFlags & FLAG_LINKCOLOR) != 0) {
                                                                textView.getPaint().linkColor = color;
                                                                textView.invalidate();
                                                            } else if ((this.changeFlags & FLAG_FASTSCROLL) == 0) {
                                                                text = textView.getText();
                                                                if (text instanceof SpannedString) {
                                                                    spans = (TypefaceSpan[]) ((SpannedString) text).getSpans(0, text.length(), TypefaceSpan.class);
                                                                    if (spans != null && spans.length > 0) {
                                                                        for (TypefaceSpan color2 : spans) {
                                                                            color2.setColor(color);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                textView.setTextColor(color);
                                                            }
                                                        } else if (obj instanceof ImageView) {
                                                            ((ImageView) obj).setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                        } else if (obj instanceof BackupImageView) {
                                                            drawable = ((BackupImageView) obj).getImageReceiver().getStaticThumb();
                                                            if (drawable instanceof CombinedDrawable) {
                                                                if (drawable != null) {
                                                                    drawable.setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                                }
                                                            } else if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) {
                                                                ((CombinedDrawable) drawable).getBackground().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                            } else {
                                                                ((CombinedDrawable) drawable).getIcon().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                            }
                                                        } else if (obj instanceof Drawable) {
                                                            if (obj instanceof CheckBox) {
                                                                if (obj instanceof GroupCreateCheckBox) {
                                                                    ((GroupCreateCheckBox) obj).updateColors();
                                                                } else if (obj instanceof Integer) {
                                                                    field.set(child, Integer.valueOf(color));
                                                                } else if (obj instanceof RadioButton) {
                                                                    if (obj instanceof TextPaint) {
                                                                        if (obj instanceof LineProgressView) {
                                                                            if (obj instanceof Paint) {
                                                                                ((Paint) obj).setColor(color);
                                                                            } else if (!(obj instanceof SeekBarView)) {
                                                                                if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) {
                                                                                    ((SeekBarView) obj).setOuterColor(color);
                                                                                } else {
                                                                                    ((SeekBarView) obj).setInnerColor(color);
                                                                                }
                                                                            }
                                                                        } else if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) {
                                                                            ((LineProgressView) obj).setProgressColor(color);
                                                                        } else {
                                                                            ((LineProgressView) obj).setBackColor(color);
                                                                        }
                                                                    } else if ((this.changeFlags & FLAG_LINKCOLOR) == 0) {
                                                                        ((TextPaint) obj).linkColor = color;
                                                                    } else {
                                                                        ((TextPaint) obj).setColor(color);
                                                                    }
                                                                } else if ((this.changeFlags & FLAG_CHECKBOX) != 0) {
                                                                    ((RadioButton) obj).setBackgroundColor(color);
                                                                    ((RadioButton) obj).invalidate();
                                                                } else if ((this.changeFlags & FLAG_CHECKBOXCHECK) != 0) {
                                                                    ((RadioButton) obj).setCheckedColor(color);
                                                                    ((RadioButton) obj).invalidate();
                                                                }
                                                            } else if ((this.changeFlags & FLAG_CHECKBOX) != 0) {
                                                                ((CheckBox) obj).setBackgroundColor(color);
                                                            } else if ((this.changeFlags & FLAG_CHECKBOXCHECK) != 0) {
                                                                ((CheckBox) obj).setCheckColor(color);
                                                            }
                                                        } else if (obj instanceof LetterDrawable) {
                                                            if (obj instanceof CombinedDrawable) {
                                                                if (!(obj instanceof StateListDrawable) || (VERSION.SDK_INT >= 21 && (obj instanceof RippleDrawable))) {
                                                                    Theme.setSelectorDrawableColor((Drawable) obj, color, (this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0);
                                                                } else {
                                                                    ((Drawable) obj).setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                                }
                                                            } else if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) {
                                                                ((CombinedDrawable) obj).getBackground().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                            } else {
                                                                ((CombinedDrawable) obj).getIcon().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                            }
                                                        } else if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) {
                                                            ((LetterDrawable) obj).setBackgroundColor(color);
                                                        } else {
                                                            ((LetterDrawable) obj).setColor(color);
                                                        }
                                                    } else if ((this.changeFlags & FLAG_LINKCOLOR) == 0) {
                                                        ((SimpleTextView) obj).setLinkTextColor(color);
                                                    } else {
                                                        ((SimpleTextView) obj).setTextColor(color);
                                                    }
                                                } else if ((this.changeFlags & FLAG_HINTTEXTCOLOR) == 0) {
                                                    ((EditTextCaption) obj).setHintColor(color);
                                                    ((EditTextCaption) obj).setHintTextColor(color);
                                                } else {
                                                    ((EditTextCaption) obj).setTextColor(color);
                                                }
                                            } catch (Throwable e) {
                                                FileLog.e(e);
                                                this.notFoundCachedFields.put(key, Boolean.valueOf(true));
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (child instanceof GroupCreateSpan) {
                            ((GroupCreateSpan) child).updateColors();
                        }
                    }
                }
                passedCheck = true;
                child.invalidate();
                if ((this.changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
                    drawable = child.getBackground();
                    if (drawable != null) {
                        if ((this.changeFlags & FLAG_CELLBACKGROUNDCOLOR) == 0) {
                            if (drawable instanceof CombinedDrawable) {
                                drawable = ((CombinedDrawable) drawable).getIcon();
                            } else if ((drawable instanceof StateListDrawable) || (VERSION.SDK_INT >= 21 && (drawable instanceof RippleDrawable))) {
                                Theme.setSelectorDrawableColor(drawable, color, (this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0);
                            }
                            drawable.setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                        } else if (drawable instanceof CombinedDrawable) {
                            Drawable back = ((CombinedDrawable) drawable).getBackground();
                            if (back instanceof ColorDrawable) {
                                ((ColorDrawable) back).setColor(color);
                            }
                        }
                    }
                } else if ((this.changeFlags & FLAG_CELLBACKGROUNDCOLOR) != 0) {
                    child.setBackgroundColor(color);
                } else if ((this.changeFlags & FLAG_TEXTCOLOR) != 0) {
                    if (child instanceof TextView) {
                        ((TextView) child).setTextColor(color);
                    }
                } else if ((this.changeFlags & FLAG_SERVICEBACKGROUND) != 0) {
                    Drawable background = child.getBackground();
                    if (background != null) {
                        background.setColorFilter(Theme.colorFilter);
                    }
                } else if ((this.changeFlags & FLAG_SELECTOR) != 0) {
                    child.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                } else if ((this.changeFlags & FLAG_SELECTORWHITE) != 0) {
                    child.setBackgroundDrawable(Theme.getSelectorDrawable(true));
                }
                if (this.listClassesFieldName != null) {
                    key = this.listClasses[b] + "_" + this.listClassesFieldName[b];
                    if (this.notFoundCachedFields != null) {
                    }
                    field = (Field) this.cachedFields.get(key);
                    if (field == null) {
                        field = this.listClasses[b].getDeclaredField(this.listClassesFieldName[b]);
                        if (field != null) {
                            field.setAccessible(true);
                            this.cachedFields.put(key, field);
                        }
                    }
                    if (field != null) {
                        obj = field.get(child);
                        if (obj == null) {
                            if (checkTag(this.currentKey, (View) obj)) {
                            }
                            if (obj instanceof View) {
                                ((View) obj).invalidate();
                            }
                            obj = ((View) obj).getBackground();
                            if ((this.changeFlags & FLAG_BACKGROUND) == 0) {
                            }
                            if (obj instanceof Switch) {
                                ((Switch) obj).checkColorFilters();
                            } else if (obj instanceof EditTextCaption) {
                                if (obj instanceof SimpleTextView) {
                                    if (obj instanceof TextView) {
                                        textView = (TextView) obj;
                                        if ((this.changeFlags & FLAG_IMAGECOLOR) != 0) {
                                            drawables = textView.getCompoundDrawables();
                                            if (drawables != null) {
                                                for (a = 0; a < drawables.length; a++) {
                                                    if (drawables[a] != null) {
                                                        drawables[a].setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                                    }
                                                }
                                            }
                                        } else if ((this.changeFlags & FLAG_LINKCOLOR) != 0) {
                                            textView.getPaint().linkColor = color;
                                            textView.invalidate();
                                        } else if ((this.changeFlags & FLAG_FASTSCROLL) == 0) {
                                            textView.setTextColor(color);
                                        } else {
                                            text = textView.getText();
                                            if (text instanceof SpannedString) {
                                                spans = (TypefaceSpan[]) ((SpannedString) text).getSpans(0, text.length(), TypefaceSpan.class);
                                                while (i < spans.length) {
                                                    color2.setColor(color);
                                                }
                                            }
                                        }
                                    } else if (obj instanceof ImageView) {
                                        ((ImageView) obj).setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                    } else if (obj instanceof BackupImageView) {
                                        drawable = ((BackupImageView) obj).getImageReceiver().getStaticThumb();
                                        if (drawable instanceof CombinedDrawable) {
                                            if (drawable != null) {
                                                drawable.setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                            }
                                        } else if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) {
                                            ((CombinedDrawable) drawable).getIcon().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                        } else {
                                            ((CombinedDrawable) drawable).getBackground().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                        }
                                    } else if (obj instanceof Drawable) {
                                        if (obj instanceof CheckBox) {
                                            if (obj instanceof GroupCreateCheckBox) {
                                                ((GroupCreateCheckBox) obj).updateColors();
                                            } else if (obj instanceof Integer) {
                                                field.set(child, Integer.valueOf(color));
                                            } else if (obj instanceof RadioButton) {
                                                if (obj instanceof TextPaint) {
                                                    if (obj instanceof LineProgressView) {
                                                        if (obj instanceof Paint) {
                                                            ((Paint) obj).setColor(color);
                                                        } else if (!(obj instanceof SeekBarView)) {
                                                            if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) {
                                                                ((SeekBarView) obj).setInnerColor(color);
                                                            } else {
                                                                ((SeekBarView) obj).setOuterColor(color);
                                                            }
                                                        }
                                                    } else if ((this.changeFlags & FLAG_PROGRESSBAR) == 0) {
                                                        ((LineProgressView) obj).setBackColor(color);
                                                    } else {
                                                        ((LineProgressView) obj).setProgressColor(color);
                                                    }
                                                } else if ((this.changeFlags & FLAG_LINKCOLOR) == 0) {
                                                    ((TextPaint) obj).setColor(color);
                                                } else {
                                                    ((TextPaint) obj).linkColor = color;
                                                }
                                            } else if ((this.changeFlags & FLAG_CHECKBOX) != 0) {
                                                ((RadioButton) obj).setBackgroundColor(color);
                                                ((RadioButton) obj).invalidate();
                                            } else if ((this.changeFlags & FLAG_CHECKBOXCHECK) != 0) {
                                                ((RadioButton) obj).setCheckedColor(color);
                                                ((RadioButton) obj).invalidate();
                                            }
                                        } else if ((this.changeFlags & FLAG_CHECKBOX) != 0) {
                                            ((CheckBox) obj).setBackgroundColor(color);
                                        } else if ((this.changeFlags & FLAG_CHECKBOXCHECK) != 0) {
                                            ((CheckBox) obj).setCheckColor(color);
                                        }
                                    } else if (obj instanceof LetterDrawable) {
                                        if (obj instanceof CombinedDrawable) {
                                            if (obj instanceof StateListDrawable) {
                                            }
                                            if ((this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0) {
                                            }
                                            Theme.setSelectorDrawableColor((Drawable) obj, color, (this.changeFlags & FLAG_DRAWABLESELECTEDSTATE) == 0);
                                        } else if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) {
                                            ((CombinedDrawable) obj).getIcon().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                        } else {
                                            ((CombinedDrawable) obj).getBackground().setColorFilter(new PorterDuffColorFilter(color, Mode.MULTIPLY));
                                        }
                                    } else if ((this.changeFlags & FLAG_BACKGROUNDFILTER) == 0) {
                                        ((LetterDrawable) obj).setColor(color);
                                    } else {
                                        ((LetterDrawable) obj).setBackgroundColor(color);
                                    }
                                } else if ((this.changeFlags & FLAG_LINKCOLOR) == 0) {
                                    ((SimpleTextView) obj).setTextColor(color);
                                } else {
                                    ((SimpleTextView) obj).setLinkTextColor(color);
                                }
                            } else if ((this.changeFlags & FLAG_HINTTEXTCOLOR) == 0) {
                                ((EditTextCaption) obj).setTextColor(color);
                            } else {
                                ((EditTextCaption) obj).setHintColor(color);
                                ((EditTextCaption) obj).setHintTextColor(color);
                            }
                        }
                    }
                } else if (child instanceof GroupCreateSpan) {
                    ((GroupCreateSpan) child).updateColors();
                }
            }
        }
    }

    public String getCurrentKey() {
        return this.currentKey;
    }

    public void startEditing() {
        int color = Theme.getColor(this.currentKey, this.previousIsDefault);
        this.previousColor = color;
        this.currentColor = color;
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    public int getSetColor() {
        return Theme.getColor(this.currentKey);
    }

    public void setDefaultColor() {
        setColor(Theme.getDefaultColor(this.currentKey), true);
    }

    public void setPreviousColor() {
        setColor(this.previousColor, this.previousIsDefault[0]);
    }

    public String getTitle() {
        if (LocaleController.isPersian) {
            String str = this.currentKey;
            Object obj = -1;
            switch (str.hashCode()) {
                case -2147269658:
                    if (str.equals(Theme.key_chat_outMenu)) {
                        obj = 256;
                        break;
                    }
                    break;
                case -2139469579:
                    if (str.equals(Theme.key_chat_emojiPanelEmptyText)) {
                        obj = 313;
                        break;
                    }
                    break;
                case -2132427577:
                    if (str.equals(Theme.key_chat_outViews)) {
                        obj = 241;
                        break;
                    }
                    break;
                case -2103805301:
                    if (str.equals(Theme.key_actionBarActionModeDefault)) {
                        obj = 10;
                        break;
                    }
                    break;
                case -2102232027:
                    if (str.equals(Theme.key_profile_actionIcon)) {
                        obj = 51;
                        break;
                    }
                    break;
                case -1998676357:
                    if (str.equals(Theme.key_chat_recordedVoicePlayPausePressed)) {
                        obj = 302;
                        break;
                    }
                    break;
                case -1996746125:
                    if (str.equals(Theme.key_chats_actionIcon)) {
                        obj = 137;
                        break;
                    }
                    break;
                case -1992864503:
                    if (str.equals(Theme.key_actionBarDefaultSubmenuBackground)) {
                        obj = 19;
                        break;
                    }
                    break;
                case -1992639563:
                    if (str.equals(Theme.key_avatar_actionBarSelectorViolet)) {
                        obj = 36;
                        break;
                    }
                    break;
                case -1974166005:
                    if (str.equals(Theme.key_chat_outFileProgressSelected)) {
                        obj = 271;
                        break;
                    }
                    break;
                case -1961633574:
                    if (str.equals(Theme.key_chat_outLoader)) {
                        obj = 277;
                        break;
                    }
                    break;
                case -1954084622:
                    if (str.equals(Theme.key_chats_menuCloud)) {
                        obj = 132;
                        break;
                    }
                    break;
                case -1942198229:
                    if (str.equals(Theme.key_chats_menuPhone)) {
                        obj = TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
                        break;
                    }
                    break;
                case -1927175348:
                    if (str.equals(Theme.key_chat_outFileBackgroundSelected)) {
                        obj = 276;
                        break;
                    }
                    break;
                case -1926854985:
                    if (str.equals(Theme.key_windowBackgroundWhiteGrayText2)) {
                        obj = 367;
                        break;
                    }
                    break;
                case -1926854984:
                    if (str.equals(Theme.key_windowBackgroundWhiteGrayText3)) {
                        obj = TsExtractor.TS_STREAM_TYPE_E_AC3;
                        break;
                    }
                    break;
                case -1926854983:
                    if (str.equals(Theme.key_windowBackgroundWhiteGrayText4)) {
                        obj = 3;
                        break;
                    }
                    break;
                case -1926854982:
                    if (str.equals(Theme.key_windowBackgroundWhiteGrayText5)) {
                        obj = 372;
                        break;
                    }
                    break;
                case -1924841028:
                    if (str.equals(Theme.key_actionBarDefaultSubtitle)) {
                        obj = 15;
                        break;
                    }
                    break;
                case -1908320176:
                    if (str.equals(Theme.key_chat_inVoiceSeekbarSelected)) {
                        obj = 219;
                        break;
                    }
                    break;
                case -1898872646:
                    if (str.equals(Theme.key_chats_verifiedBackground)) {
                        obj = 124;
                        break;
                    }
                    break;
                case -1891930735:
                    if (str.equals(Theme.key_chat_outFileBackground)) {
                        obj = 275;
                        break;
                    }
                    break;
                case -1878988531:
                    if (str.equals(Theme.key_avatar_actionBarSelectorGreen)) {
                        obj = 40;
                        break;
                    }
                    break;
                case -1871598549:
                    if (str.equals(Theme.key_actionBarWhiteSelector)) {
                        obj = 8;
                        break;
                    }
                    break;
                case -1863494935:
                    if (str.equals(Theme.key_dialogRoundCheckBox)) {
                        obj = 159;
                        break;
                    }
                    break;
                case -1853661732:
                    if (str.equals(Theme.key_chat_outTimeSelectedText)) {
                        obj = 255;
                        break;
                    }
                    break;
                case -1850167367:
                    if (str.equals(Theme.key_chat_emojiPanelShadowLine)) {
                        obj = 315;
                        break;
                    }
                    break;
                case -1849805674:
                    if (str.equals(Theme.key_dialogBackground)) {
                        obj = 148;
                        break;
                    }
                    break;
                case -1846883115:
                    if (str.equals(Theme.key_chat_inInstant)) {
                        obj = 230;
                        break;
                    }
                    break;
                case -1782810708:
                    if (str.equals(Theme.key_groupcreate_offlineText)) {
                        obj = 357;
                        break;
                    }
                    break;
                case -1779173263:
                    if (str.equals(Theme.key_chat_topPanelMessage)) {
                        obj = 329;
                        break;
                    }
                    break;
                case -1777297962:
                    if (str.equals(Theme.key_chats_muteIcon)) {
                        obj = 125;
                        break;
                    }
                    break;
                case -1770645101:
                    if (str.equals(Theme.key_avatar_backgroundInProfileGreen)) {
                        obj = 59;
                        break;
                    }
                    break;
                case -1767675171:
                    if (str.equals(Theme.key_chat_inViaBotNameText)) {
                        obj = 195;
                        break;
                    }
                    break;
                case -1758608141:
                    if (str.equals(Theme.key_windowBackgroundWhiteValueText)) {
                        obj = 366;
                        break;
                    }
                    break;
                case -1733632792:
                    if (str.equals(Theme.key_emptyListPlaceholder)) {
                        obj = 63;
                        break;
                    }
                    break;
                case -1731366708:
                    if (str.equals(Theme.key_chat_editDoneIcon)) {
                        obj = 289;
                        break;
                    }
                    break;
                case -1724033454:
                    if (str.equals(Theme.key_chat_inPreviewInstantText)) {
                        obj = 232;
                        break;
                    }
                    break;
                case -1719903102:
                    if (str.equals(Theme.key_chat_outViewsSelected)) {
                        obj = 242;
                        break;
                    }
                    break;
                case -1719839798:
                    if (str.equals(Theme.key_avatar_backgroundInProfileBlue)) {
                        obj = 55;
                        break;
                    }
                    break;
                case -1719798125:
                    if (str.equals(Theme.key_avatar_backgroundInProfileCyan)) {
                        obj = 60;
                        break;
                    }
                    break;
                case -1719425818:
                    if (str.equals(Theme.key_avatar_backgroundInProfilePink)) {
                        obj = 61;
                        break;
                    }
                    break;
                case -1683744660:
                    if (str.equals(Theme.key_profile_verifiedBackground)) {
                        obj = 22;
                        break;
                    }
                    break;
                case -1680030754:
                    if (str.equals(Theme.key_radioBackgroundChecked)) {
                        obj = 73;
                        break;
                    }
                    break;
                case -1666483987:
                    if (str.equals(Theme.key_muteTabCounterText)) {
                        obj = 103;
                        break;
                    }
                    break;
                case -1654302575:
                    if (str.equals(Theme.key_chats_menuBackground)) {
                        obj = 126;
                        break;
                    }
                    break;
                case -1641108702:
                    if (str.equals(Theme.key_avatar_backgroundOrange)) {
                        obj = 91;
                        break;
                    }
                    break;
                case -1633591792:
                    if (str.equals(Theme.key_chat_emojiPanelStickerPackSelector)) {
                        obj = 320;
                        break;
                    }
                    break;
                case -1627686284:
                    if (str.equals(Theme.key_tabCounterText)) {
                        obj = 101;
                        break;
                    }
                    break;
                case -1625862693:
                    if (str.equals(Theme.key_chat_wallpaper)) {
                        obj = 164;
                        break;
                    }
                    break;
                case -1623818608:
                    if (str.equals(Theme.key_chat_inForwardedNameText)) {
                        obj = 194;
                        break;
                    }
                    break;
                case -1604008580:
                    if (str.equals(Theme.key_chat_outAudioProgress)) {
                        obj = 258;
                        break;
                    }
                    break;
                case -1582272256:
                    if (str.equals(Theme.key_chat_emojiPanelNewTrending)) {
                        obj = 312;
                        break;
                    }
                    break;
                case -1570912375:
                    if (str.equals(Theme.key_radioBackground)) {
                        obj = 72;
                        break;
                    }
                    break;
                case -1543133775:
                    if (str.equals(Theme.key_chat_outContactNameText)) {
                        obj = 252;
                        break;
                    }
                    break;
                case -1542353776:
                    if (str.equals(Theme.key_chat_outVoiceSeekbar)) {
                        obj = 267;
                        break;
                    }
                    break;
                case -1533503664:
                    if (str.equals(Theme.key_chat_outFileProgress)) {
                        obj = 270;
                        break;
                    }
                    break;
                case -1530345450:
                    if (str.equals(Theme.key_chat_inReplyMessageText)) {
                        obj = 198;
                        break;
                    }
                    break;
                case -1496224782:
                    if (str.equals(Theme.key_chat_inReplyLine)) {
                        obj = 196;
                        break;
                    }
                    break;
                case -1461837066:
                    if (str.equals(Theme.key_chats_unreadCounterText)) {
                        obj = 106;
                        break;
                    }
                    break;
                case -1448601229:
                    if (str.equals(Theme.key_avatar_backgroundViolet)) {
                        obj = 92;
                        break;
                    }
                    break;
                case -1426358165:
                    if (str.equals(Theme.key_avatar_nameInMessageBlue)) {
                        obj = 310;
                        break;
                    }
                    break;
                case -1426316492:
                    if (str.equals(Theme.key_avatar_nameInMessageCyan)) {
                        obj = 309;
                        break;
                    }
                    break;
                case -1425944185:
                    if (str.equals(Theme.key_avatar_nameInMessagePink)) {
                        obj = 311;
                        break;
                    }
                    break;
                case -1425226166:
                    if (str.equals(Theme.key_chat_fieldOverlayText)) {
                        obj = 290;
                        break;
                    }
                    break;
                case -1407570354:
                    if (str.equals(Theme.key_chat_inReplyMediaMessageText)) {
                        obj = 199;
                        break;
                    }
                    break;
                case -1397026623:
                    if (str.equals(Theme.key_windowBackgroundGray)) {
                        obj = 2;
                        break;
                    }
                    break;
                case -1385379359:
                    if (str.equals(Theme.key_dialogIcon)) {
                        obj = 151;
                        break;
                    }
                    break;
                case -1384474442:
                    if (str.equals(Theme.key_chat_mediaLoaderPhotoIconSelected)) {
                        obj = 186;
                        break;
                    }
                    break;
                case -1316415606:
                    if (str.equals(Theme.key_actionBarActionModeDefaultSelector)) {
                        obj = 13;
                        break;
                    }
                    break;
                case -1310183623:
                    if (str.equals(Theme.key_chat_muteIcon)) {
                        obj = 165;
                        break;
                    }
                    break;
                case -1294066294:
                    if (str.equals(Theme.key_chat_mediaProgress)) {
                        obj = 176;
                        break;
                    }
                    break;
                case -1281993001:
                    if (str.equals(Theme.key_returnToCallBackground)) {
                        obj = 146;
                        break;
                    }
                    break;
                case -1270046900:
                    if (str.equals(Theme.key_tabsBackground)) {
                        obj = 97;
                        break;
                    }
                    break;
                case -1264241339:
                    if (str.equals(Theme.key_chat_searchPanelIcons)) {
                        obj = 339;
                        break;
                    }
                    break;
                case -1262649070:
                    if (str.equals(Theme.key_avatar_nameInMessageGreen)) {
                        obj = 308;
                        break;
                    }
                    break;
                case -1243368121:
                    if (str.equals(Theme.key_chat_stickerViaBotNameText)) {
                        obj = 172;
                        break;
                    }
                    break;
                case -1241257069:
                    if (str.equals(Theme.key_calls_callReceivedGreenIcon)) {
                        obj = 179;
                        break;
                    }
                    break;
                case -1240647597:
                    if (str.equals(Theme.key_chat_outBubbleShadow)) {
                        obj = 235;
                        break;
                    }
                    break;
                case -1229478359:
                    if (str.equals(Theme.key_chats_unreadCounter)) {
                        obj = 104;
                        break;
                    }
                    break;
                case -1213387098:
                    if (str.equals(Theme.key_chat_inMenuSelected)) {
                        obj = 208;
                        break;
                    }
                    break;
                case -1184394761:
                    if (str.equals(Theme.key_chat_replyPanelClose)) {
                        obj = 338;
                        break;
                    }
                    break;
                case -1179121895:
                    if (str.equals(Theme.key_chat_replyPanelIcons)) {
                        obj = 337;
                        break;
                    }
                    break;
                case -1178138213:
                    if (str.equals(Theme.key_chat_botKeyboardButtonBackgroundPressed)) {
                        obj = 325;
                        break;
                    }
                    break;
                case -1167182553:
                    if (str.equals(Theme.key_stickers_menuSelector)) {
                        obj = 85;
                        break;
                    }
                    break;
                case -1154375072:
                    if (str.equals(Theme.key_avatar_nameInMessageRed)) {
                        obj = 305;
                        break;
                    }
                    break;
                case -1147596450:
                    if (str.equals(Theme.key_chat_inFileInfoSelectedText)) {
                        obj = 225;
                        break;
                    }
                    break;
                case -1106544443:
                    if (str.equals(Theme.key_chats_sentErrorIcon)) {
                        obj = 122;
                        break;
                    }
                    break;
                case -1106471792:
                    if (str.equals(Theme.key_chat_outAudioPerfomerText)) {
                        obj = 261;
                        break;
                    }
                    break;
                case -1078554766:
                    if (str.equals(Theme.key_windowBackgroundWhiteBlueHeader)) {
                        obj = 62;
                        break;
                    }
                    break;
                case -1074293766:
                    if (str.equals(Theme.key_avatar_backgroundActionBarGreen)) {
                        obj = 39;
                        break;
                    }
                    break;
                case -1063762099:
                    if (str.equals(Theme.key_windowBackgroundWhiteGreenText2)) {
                        obj = 369;
                        break;
                    }
                    break;
                case -1062379852:
                    if (str.equals(Theme.key_chat_messageLinkOut)) {
                        obj = 238;
                        break;
                    }
                    break;
                case -1046600742:
                    if (str.equals(Theme.key_profile_actionBackground)) {
                        obj = 52;
                        break;
                    }
                    break;
                case -1019316079:
                    if (str.equals(Theme.key_chat_outReplyMessageText)) {
                        obj = 247;
                        break;
                    }
                    break;
                case -1016198330:
                    if (str.equals(Theme.key_chat_botKeyboardButtonText)) {
                        obj = 326;
                        break;
                    }
                    break;
                case -1012016554:
                    if (str.equals(Theme.key_chat_inFileBackground)) {
                        obj = 226;
                        break;
                    }
                    break;
                case -1006953508:
                    if (str.equals(Theme.key_chat_secretTimerBackground)) {
                        obj = 349;
                        break;
                    }
                    break;
                case -1005376655:
                    if (str.equals(Theme.key_chat_inAudioSeekbar)) {
                        obj = 215;
                        break;
                    }
                    break;
                case -1005120019:
                    if (str.equals(Theme.key_chats_secretIcon)) {
                        obj = 110;
                        break;
                    }
                    break;
                case -1004973057:
                    if (str.equals(Theme.key_chats_secretName)) {
                        obj = 108;
                        break;
                    }
                    break;
                case -1000652091:
                    if (str.equals(Theme.key_inappPlayerPlayPause)) {
                        obj = 142;
                        break;
                    }
                    break;
                case -986225898:
                    if (str.equals(Theme.key_fastScrollText)) {
                        obj = 88;
                        break;
                    }
                    break;
                case -969849940:
                    if (str.equals(Theme.key_chat_stickerReplyNameText)) {
                        obj = 174;
                        break;
                    }
                    break;
                case -960321732:
                    if (str.equals(Theme.key_chat_mediaMenu)) {
                        obj = 169;
                        break;
                    }
                    break;
                case -955211830:
                    if (str.equals(Theme.key_chat_topPanelLine)) {
                        obj = 327;
                        break;
                    }
                    break;
                case -947564153:
                    if (str.equals(Theme.key_actionBarDefaultSearchPlaceholder)) {
                        obj = 17;
                        break;
                    }
                    break;
                case -927786875:
                    if (str.equals(Theme.key_tabIcons)) {
                        obj = 98;
                        break;
                    }
                    break;
                case -901363160:
                    if (str.equals(Theme.key_chats_menuPhoneCats)) {
                        obj = 129;
                        break;
                    }
                    break;
                case -893100539:
                    if (str.equals(Theme.key_chat_recordTime)) {
                        obj = 296;
                        break;
                    }
                    break;
                case -857030041:
                    if (str.equals(Theme.key_chat_serviceIcon)) {
                        obj = 354;
                        break;
                    }
                    break;
                case -856934936:
                    if (str.equals(Theme.key_chat_serviceLink)) {
                        obj = 355;
                        break;
                    }
                    break;
                case -856700133:
                    if (str.equals(Theme.key_chat_serviceText)) {
                        obj = 353;
                        break;
                    }
                    break;
                case -850714303:
                    if (str.equals(Theme.key_windowBackgroundGrayShadow)) {
                        obj = 4;
                        break;
                    }
                    break;
                case -818305664:
                    if (str.equals(Theme.key_chat_inFileNameText)) {
                        obj = 223;
                        break;
                    }
                    break;
                case -810517465:
                    if (str.equals(Theme.key_chat_outAudioSeekbarSelected)) {
                        obj = 265;
                        break;
                    }
                    break;
                case -805096120:
                    if (str.equals(Theme.key_chats_nameIcon)) {
                        obj = 109;
                        break;
                    }
                    break;
                case -792942846:
                    if (str.equals(Theme.key_graySection)) {
                        obj = 89;
                        break;
                    }
                    break;
                case -789001418:
                    if (str.equals(Theme.key_chats_actionPressedBackground)) {
                        obj = 139;
                        break;
                    }
                    break;
                case -779362418:
                    if (str.equals(Theme.key_chat_emojiPanelTrendingTitle)) {
                        obj = 321;
                        break;
                    }
                    break;
                case -771457608:
                    if (str.equals(Theme.key_chat_inVoiceSeekbarFill)) {
                        obj = 220;
                        break;
                    }
                    break;
                case -763385518:
                    if (str.equals(Theme.key_chats_date)) {
                        obj = 117;
                        break;
                    }
                    break;
                case -763087825:
                    if (str.equals(Theme.key_chats_name)) {
                        obj = 107;
                        break;
                    }
                    break;
                case -756337980:
                    if (str.equals(Theme.key_profile_actionPressedBackground)) {
                        obj = 53;
                        break;
                    }
                    break;
                case -738385572:
                    if (str.equals(Theme.key_checkboxSquareDisabled)) {
                        obj = 70;
                        break;
                    }
                    break;
                case -713987006:
                    if (str.equals(Theme.key_profile_title)) {
                        obj = 20;
                        break;
                    }
                    break;
                case -712338357:
                    if (str.equals(Theme.key_chat_inSiteNameText)) {
                        obj = 202;
                        break;
                    }
                    break;
                case -654429213:
                    if (str.equals(Theme.key_chats_message)) {
                        obj = 112;
                        break;
                    }
                    break;
                case -632224754:
                    if (str.equals(Theme.key_checkboxSquareBackground)) {
                        obj = 68;
                        break;
                    }
                    break;
                case -629209323:
                    if (str.equals(Theme.key_chats_pinnedIcon)) {
                        obj = 111;
                        break;
                    }
                    break;
                case -608456434:
                    if (str.equals(Theme.key_chat_outBubbleSelected)) {
                        obj = 236;
                        break;
                    }
                    break;
                case -570274322:
                    if (str.equals(Theme.key_chat_outReplyMediaMessageSelectedText)) {
                        obj = 249;
                        break;
                    }
                    break;
                case -565473788:
                    if (str.equals(Theme.key_chat_messagePanelShadow)) {
                        obj = 284;
                        break;
                    }
                    break;
                case -564899147:
                    if (str.equals(Theme.key_chat_outInstantSelected)) {
                        obj = 280;
                        break;
                    }
                    break;
                case -560721948:
                    if (str.equals(Theme.key_chat_outSentCheckSelected)) {
                        obj = PsExtractor.VIDEO_STREAM_MASK;
                        break;
                    }
                    break;
                case -552118908:
                    if (str.equals(Theme.key_actionBarDefault)) {
                        obj = 6;
                        break;
                    }
                    break;
                case -493564645:
                    if (str.equals(Theme.key_avatar_actionBarSelectorRed)) {
                        obj = 28;
                        break;
                    }
                    break;
                case -450514995:
                    if (str.equals(Theme.key_chats_actionMessage)) {
                        obj = 114;
                        break;
                    }
                    break;
                case -427186938:
                    if (str.equals(Theme.key_chat_inAudioDurationSelectedText)) {
                        obj = 214;
                        break;
                    }
                    break;
                case -427016862:
                    if (str.equals(Theme.key_chat_mediaLoaderPhoto)) {
                        obj = 183;
                        break;
                    }
                    break;
                case -421563244:
                    if (str.equals(Theme.key_chat_inAudioSeekbarFill)) {
                        obj = 217;
                        break;
                    }
                    break;
                case -391617936:
                    if (str.equals(Theme.key_chat_selectedBackground)) {
                        obj = 171;
                        break;
                    }
                    break;
                case -381243596:
                    if (str.equals(Theme.key_actionBarDefaultTitle)) {
                        obj = 14;
                        break;
                    }
                    break;
                case -363666503:
                    if (str.equals(Theme.key_dialogsBackgroundWhite)) {
                        obj = 1;
                        break;
                    }
                    break;
                case -354489314:
                    if (str.equals(Theme.key_chat_outFileInfoText)) {
                        obj = 273;
                        break;
                    }
                    break;
                case -343666293:
                    if (str.equals(Theme.key_windowBackgroundWhite)) {
                        obj = null;
                        break;
                    }
                    break;
                case -332978597:
                    if (str.equals(Theme.key_chat_secretTimerText)) {
                        obj = 350;
                        break;
                    }
                    break;
                case -314340441:
                    if (str.equals(Theme.key_chat_recordedVoicePlayPause)) {
                        obj = 301;
                        break;
                    }
                    break;
                case -294026410:
                    if (str.equals(Theme.key_chat_inReplyNameText)) {
                        obj = 197;
                        break;
                    }
                    break;
                case -269301944:
                    if (str.equals(Theme.key_chat_stickerReplyLine)) {
                        obj = 173;
                        break;
                    }
                    break;
                case -269235893:
                    if (str.equals(Theme.key_chat_lockIcon)) {
                        obj = 166;
                        break;
                    }
                    break;
                case -264184037:
                    if (str.equals(Theme.key_inappPlayerClose)) {
                        obj = 145;
                        break;
                    }
                    break;
                case -260428237:
                    if (str.equals(Theme.key_chat_outVoiceSeekbarFill)) {
                        obj = 269;
                        break;
                    }
                    break;
                case -258492929:
                    if (str.equals(Theme.key_avatar_nameInMessageOrange)) {
                        obj = 306;
                        break;
                    }
                    break;
                case -257300036:
                    if (str.equals(Theme.key_dialogTextRed)) {
                        obj = 155;
                        break;
                    }
                    break;
                case -254202015:
                    if (str.equals(Theme.key_groupcreate_sectionShadow)) {
                        obj = 362;
                        break;
                    }
                    break;
                case -251079667:
                    if (str.equals(Theme.key_chat_outPreviewInstantText)) {
                        obj = 281;
                        break;
                    }
                    break;
                case -249481380:
                    if (str.equals(Theme.key_listSelector)) {
                        obj = 5;
                        break;
                    }
                    break;
                case -248568965:
                    if (str.equals(Theme.key_inappPlayerTitle)) {
                        obj = 143;
                        break;
                    }
                    break;
                case -224769986:
                    if (str.equals(Theme.key_featuredStickers_addedIcon)) {
                        obj = 77;
                        break;
                    }
                    break;
                case -197165287:
                    if (str.equals(Theme.key_chat_messagePanelVoiceDelete)) {
                        obj = 293;
                        break;
                    }
                    break;
                case -185786131:
                    if (str.equals(Theme.key_chat_unreadMessagesStartText)) {
                        obj = 343;
                        break;
                    }
                    break;
                case -182444032:
                    if (str.equals(Theme.key_chat_stickerReplyMessageText)) {
                        obj = 175;
                        break;
                    }
                    break;
                case -176488427:
                    if (str.equals(Theme.key_chat_replyPanelLine)) {
                        obj = 334;
                        break;
                    }
                    break;
                case -176436564:
                    if (str.equals(Theme.key_chat_replyPanelName)) {
                        obj = 335;
                        break;
                    }
                    break;
                case -166587875:
                    if (str.equals(Theme.key_chat_mediaLoaderPhotoSelected)) {
                        obj = 185;
                        break;
                    }
                    break;
                case -147756504:
                    if (str.equals(Theme.key_checkboxSquareCheck)) {
                        obj = 69;
                        break;
                    }
                    break;
                case -143547632:
                    if (str.equals(Theme.key_chat_inFileProgressSelected)) {
                        obj = 222;
                        break;
                    }
                    break;
                case -136351367:
                    if (str.equals(Theme.key_chat_goDownButtonCounter)) {
                        obj = 347;
                        break;
                    }
                    break;
                case -115051944:
                    if (str.equals(Theme.key_chat_mediaInfoText)) {
                        obj = 170;
                        break;
                    }
                    break;
                case -108292334:
                    if (str.equals(Theme.key_chats_menuTopShadow)) {
                        obj = 127;
                        break;
                    }
                    break;
                case -93324646:
                    if (str.equals(Theme.key_dialogButton)) {
                        obj = 153;
                        break;
                    }
                    break;
                case -71280336:
                    if (str.equals(Theme.key_switchTrackChecked)) {
                        obj = 67;
                        break;
                    }
                    break;
                case -65985456:
                    if (str.equals(Theme.key_avatar_nameInMessageViolet)) {
                        obj = 307;
                        break;
                    }
                    break;
                case -65607089:
                    if (str.equals(Theme.key_chats_menuItemIcon)) {
                        obj = 133;
                        break;
                    }
                    break;
                case -65277181:
                    if (str.equals(Theme.key_chats_menuItemText)) {
                        obj = TsExtractor.TS_STREAM_TYPE_SPLICE_INFO;
                        break;
                    }
                    break;
                case -35597940:
                    if (str.equals(Theme.key_chat_inContactNameText)) {
                        obj = 203;
                        break;
                    }
                    break;
                case -34130797:
                    if (str.equals(Theme.key_chat_mediaSentCheck)) {
                        obj = 167;
                        break;
                    }
                    break;
                case -18073397:
                    if (str.equals(Theme.key_chats_tabletSelectedOverlay)) {
                        obj = 140;
                        break;
                    }
                    break;
                case -12871922:
                    if (str.equals(Theme.key_chat_secretChatStatusText)) {
                        obj = Policy.RETRY;
                        break;
                    }
                    break;
                case 27337780:
                    if (str.equals(Theme.key_chats_pinnedOverlay)) {
                        obj = 118;
                        break;
                    }
                    break;
                case 35753358:
                    if (str.equals(Theme.key_chat_recordedVoiceDot)) {
                        obj = 300;
                        break;
                    }
                    break;
                case 49148112:
                    if (str.equals(Theme.key_chat_inPreviewLine)) {
                        obj = 201;
                        break;
                    }
                    break;
                case 51359814:
                    if (str.equals(Theme.key_chat_replyPanelMessage)) {
                        obj = 336;
                        break;
                    }
                    break;
                case 56289180:
                    if (str.equals(Theme.key_chat_messagePanelVoiceBackground)) {
                        obj = 294;
                        break;
                    }
                    break;
                case 57332012:
                    if (str.equals(Theme.key_chats_sentCheck)) {
                        obj = 119;
                        break;
                    }
                    break;
                case 57460786:
                    if (str.equals(Theme.key_chats_sentClock)) {
                        obj = 120;
                        break;
                    }
                    break;
                case 58088916:
                    if (str.equals(Theme.key_fastScrollInactive)) {
                        obj = 87;
                        break;
                    }
                    break;
                case 59489836:
                    if (str.equals(Theme.key_chats_sentError)) {
                        obj = 121;
                        break;
                    }
                    break;
                case 89466127:
                    if (str.equals(Theme.key_chat_outAudioSeekbarFill)) {
                        obj = 266;
                        break;
                    }
                    break;
                case 117743477:
                    if (str.equals(Theme.key_chat_outPreviewLine)) {
                        obj = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
                        break;
                    }
                    break;
                case 141076636:
                    if (str.equals(Theme.key_groupcreate_spanBackground)) {
                        obj = 360;
                        break;
                    }
                    break;
                case 141894978:
                    if (str.equals(Theme.key_windowBackgroundWhiteRedText5)) {
                        obj = 370;
                        break;
                    }
                    break;
                case 144855009:
                    if (str.equals(Theme.key_calls_callReceivedRedIcon)) {
                        obj = 178;
                        break;
                    }
                    break;
                case 177646899:
                    if (str.equals(Theme.key_avatar_actionBarIconGreen)) {
                        obj = 41;
                        break;
                    }
                    break;
                case 185438775:
                    if (str.equals(Theme.key_chat_outAudioSelectedProgress)) {
                        obj = 259;
                        break;
                    }
                    break;
                case 216441603:
                    if (str.equals(Theme.key_chat_goDownButton)) {
                        obj = 344;
                        break;
                    }
                    break;
                case 231486891:
                    if (str.equals(Theme.key_chat_inAudioPerfomerText)) {
                        obj = 212;
                        break;
                    }
                    break;
                case 234713742:
                    if (str.equals(Theme.key_chat_messagePanelVoiceShadow)) {
                        obj = 295;
                        break;
                    }
                    break;
                case 243668262:
                    if (str.equals(Theme.key_chat_inTimeText)) {
                        obj = 205;
                        break;
                    }
                    break;
                case 250130940:
                    if (str.equals(Theme.key_chats_verifiedCheck)) {
                        obj = 123;
                        break;
                    }
                    break;
                case 257089712:
                    if (str.equals(Theme.key_chat_outAudioDurationText)) {
                        obj = 262;
                        break;
                    }
                    break;
                case 271457747:
                    if (str.equals(Theme.key_chat_inBubbleSelected)) {
                        obj = PsExtractor.PRIVATE_STREAM_1;
                        break;
                    }
                    break;
                case 281000709:
                    if (str.equals(Theme.key_checkboxCheck)) {
                        obj = 75;
                        break;
                    }
                    break;
                case 303219761:
                    if (str.equals(Theme.key_chat_mediaViews)) {
                        obj = 168;
                        break;
                    }
                    break;
                case 303350244:
                    if (str.equals(Theme.key_chat_reportSpam)) {
                        obj = 333;
                        break;
                    }
                    break;
                case 308050435:
                    if (str.equals(Theme.key_chat_goDownButtonShadow)) {
                        obj = 345;
                        break;
                    }
                    break;
                case 316847509:
                    if (str.equals(Theme.key_chat_outLoaderSelected)) {
                        obj = 278;
                        break;
                    }
                    break;
                case 328230217:
                    if (str.equals(Theme.key_chat_recordedVoiceBackground)) {
                        obj = 299;
                        break;
                    }
                    break;
                case 329054614:
                    if (str.equals(Theme.key_returnToCallText)) {
                        obj = 147;
                        break;
                    }
                    break;
                case 339397761:
                    if (str.equals(Theme.key_windowBackgroundWhiteBlackText)) {
                        obj = 365;
                        break;
                    }
                    break;
                case 371859081:
                    if (str.equals(Theme.key_chat_inReplyMediaMessageSelectedText)) {
                        obj = Callback.DEFAULT_DRAG_ANIMATION_DURATION;
                        break;
                    }
                    break;
                case 375189922:
                    if (str.equals(Theme.key_chat_searchPanelText)) {
                        obj = 340;
                        break;
                    }
                    break;
                case 397290579:
                    if (str.equals(Theme.key_avatar_text)) {
                        obj = 54;
                        break;
                    }
                    break;
                case 415452907:
                    if (str.equals(Theme.key_chat_outAudioDurationSelectedText)) {
                        obj = 263;
                        break;
                    }
                    break;
                case 421601145:
                    if (str.equals(Theme.key_chat_emojiPanelIconSelected)) {
                        obj = 317;
                        break;
                    }
                    break;
                case 421601469:
                    if (str.equals(Theme.key_chat_emojiPanelIconSelector)) {
                        obj = 318;
                        break;
                    }
                    break;
                case 426061980:
                    if (str.equals(Theme.key_chat_serviceBackground)) {
                        obj = 351;
                        break;
                    }
                    break;
                case 429680544:
                    if (str.equals(Theme.key_avatar_subtitleInProfileBlue)) {
                        obj = 26;
                        break;
                    }
                    break;
                case 429722217:
                    if (str.equals(Theme.key_avatar_subtitleInProfileCyan)) {
                        obj = 46;
                        break;
                    }
                    break;
                case 430094524:
                    if (str.equals(Theme.key_avatar_subtitleInProfilePink)) {
                        obj = 50;
                        break;
                    }
                    break;
                case 435303214:
                    if (str.equals(Theme.key_actionBarDefaultSubmenuItem)) {
                        obj = 18;
                        break;
                    }
                    break;
                case 439976061:
                    if (str.equals(Theme.key_avatar_subtitleInProfileGreen)) {
                        obj = 42;
                        break;
                    }
                    break;
                case 444983522:
                    if (str.equals(Theme.key_chat_topPanelClose)) {
                        obj = 330;
                        break;
                    }
                    break;
                case 446162770:
                    if (str.equals(Theme.key_windowBackgroundWhiteBlueText)) {
                        obj = 368;
                        break;
                    }
                    break;
                case 460598594:
                    if (str.equals(Theme.key_chat_topPanelTitle)) {
                        obj = 328;
                        break;
                    }
                    break;
                case 477463716:
                    if (str.equals(Theme.key_groupcreate_onlineText)) {
                        obj = 356;
                        break;
                    }
                    break;
                case 513052576:
                    if (str.equals(Theme.key_muteTabCounter)) {
                        obj = 102;
                        break;
                    }
                    break;
                case 527405547:
                    if (str.equals(Theme.key_inappPlayerBackground)) {
                        obj = 141;
                        break;
                    }
                    break;
                case 536753172:
                    if (str.equals(Theme.key_chat_recordVoiceCancel)) {
                        obj = 297;
                        break;
                    }
                    break;
                case 556028747:
                    if (str.equals(Theme.key_chat_outVoiceSeekbarSelected)) {
                        obj = 268;
                        break;
                    }
                    break;
                case 565013755:
                    if (str.equals(Theme.key_chat_mediaLoaderPhotoIcon)) {
                        obj = 184;
                        break;
                    }
                    break;
                case 585851719:
                    if (str.equals(Theme.key_groupcreate_checkbox)) {
                        obj = 358;
                        break;
                    }
                    break;
                case 589961756:
                    if (str.equals(Theme.key_chat_goDownButtonIcon)) {
                        obj = 346;
                        break;
                    }
                    break;
                case 603546305:
                    if (str.equals(Theme.key_avatar_actionBarIconRed)) {
                        obj = 29;
                        break;
                    }
                    break;
                case 609083508:
                    if (str.equals(Theme.key_chat_messagePanelVoicePressed)) {
                        obj = 292;
                        break;
                    }
                    break;
                case 613339836:
                    if (str.equals(Theme.key_dialogTextHint)) {
                        obj = 163;
                        break;
                    }
                    break;
                case 613458991:
                    if (str.equals(Theme.key_dialogTextLink)) {
                        obj = 150;
                        break;
                    }
                    break;
                case 626157205:
                    if (str.equals(Theme.key_chat_inVoiceSeekbar)) {
                        obj = 218;
                        break;
                    }
                    break;
                case 632413071:
                    if (str.equals(Theme.key_avatar_backgroundGreen)) {
                        obj = 93;
                        break;
                    }
                    break;
                case 634019162:
                    if (str.equals(Theme.key_chat_emojiPanelBackspace)) {
                        obj = 319;
                        break;
                    }
                    break;
                case 635007317:
                    if (str.equals(Theme.key_chat_inFileProgress)) {
                        obj = 221;
                        break;
                    }
                    break;
                case 647654362:
                    if (str.equals(Theme.key_groupcreate_cursor)) {
                        obj = 364;
                        break;
                    }
                    break;
                case 648238646:
                    if (str.equals(Theme.key_chat_outAudioTitleText)) {
                        obj = 260;
                        break;
                    }
                    break;
                case 655457041:
                    if (str.equals(Theme.key_chat_inFileBackgroundSelected)) {
                        obj = 227;
                        break;
                    }
                    break;
                case 710565640:
                    if (str.equals(Theme.key_dialogBadgeText)) {
                        obj = 162;
                        break;
                    }
                    break;
                case 719283335:
                    if (str.equals(Theme.key_chat_botKeyboardButtonBackground)) {
                        obj = 324;
                        break;
                    }
                    break;
                case 725268818:
                    if (str.equals(Theme.key_profile_adminIcon)) {
                        obj = 81;
                        break;
                    }
                    break;
                case 732262561:
                    if (str.equals(Theme.key_chat_outTimeText)) {
                        obj = 254;
                        break;
                    }
                    break;
                case 765296599:
                    if (str.equals(Theme.key_chat_outReplyLine)) {
                        obj = 245;
                        break;
                    }
                    break;
                case 788219202:
                    if (str.equals(Theme.key_chat_sentErrorIcon)) {
                        obj = 181;
                        break;
                    }
                    break;
                case 803672502:
                    if (str.equals(Theme.key_chat_messagePanelIcons)) {
                        obj = 287;
                        break;
                    }
                    break;
                case 818652189:
                    if (str.equals(Theme.key_actionBarDefaultIcon)) {
                        obj = 9;
                        break;
                    }
                    break;
                case 826015922:
                    if (str.equals(Theme.key_chat_emojiPanelTrendingDescription)) {
                        obj = 322;
                        break;
                    }
                    break;
                case 839206824:
                    if (str.equals(Theme.key_chats_actionBackground)) {
                        obj = TsExtractor.TS_STREAM_TYPE_DTS;
                        break;
                    }
                    break;
                case 850854541:
                    if (str.equals(Theme.key_chat_inPreviewInstantSelectedText)) {
                        obj = 233;
                        break;
                    }
                    break;
                case 850926856:
                    if (str.equals(Theme.key_chat_recordedVoiceProgress)) {
                        obj = 303;
                        break;
                    }
                    break;
                case 878757551:
                    if (str.equals(Theme.key_fastScrollActive)) {
                        obj = 86;
                        break;
                    }
                    break;
                case 890367586:
                    if (str.equals(Theme.key_chat_inViews)) {
                        obj = PsExtractor.AUDIO_STREAM;
                        break;
                    }
                    break;
                case 913069217:
                    if (str.equals(Theme.key_chat_outMenuSelected)) {
                        obj = InputDeviceCompat.SOURCE_KEYBOARD;
                        break;
                    }
                    break;
                case 927863384:
                    if (str.equals(Theme.key_chat_inBubbleShadow)) {
                        obj = TsExtractor.TS_PACKET_SIZE;
                        break;
                    }
                    break;
                case 939137799:
                    if (str.equals(Theme.key_chat_inContactPhoneText)) {
                        obj = 204;
                        break;
                    }
                    break;
                case 939824634:
                    if (str.equals(Theme.key_chat_outInstant)) {
                        obj = ModuleDescriptor.MODULE_VERSION;
                        break;
                    }
                    break;
                case 942636688:
                    if (str.equals(Theme.key_chat_inInstantSelected)) {
                        obj = 231;
                        break;
                    }
                    break;
                case 946144033:
                    if (str.equals(Theme.key_windowBackgroundWhiteBlueText3)) {
                        obj = 136;
                        break;
                    }
                    break;
                case 962085693:
                    if (str.equals(Theme.key_chats_menuCloudBackgroundCats)) {
                        obj = 131;
                        break;
                    }
                    break;
                case 983278580:
                    if (str.equals(Theme.key_avatar_subtitleInProfileOrange)) {
                        obj = 34;
                        break;
                    }
                    break;
                case 985965442:
                    if (str.equals(Theme.key_chat_messagePanelVoiceDuration)) {
                        obj = 298;
                        break;
                    }
                    break;
                case 990077518:
                    if (str.equals(Theme.key_avatar_backgroundBlue)) {
                        obj = 95;
                        break;
                    }
                    break;
                case 990119191:
                    if (str.equals(Theme.key_avatar_backgroundCyan)) {
                        obj = 94;
                        break;
                    }
                    break;
                case 990491498:
                    if (str.equals(Theme.key_avatar_backgroundPink)) {
                        obj = 96;
                        break;
                    }
                    break;
                case 1003567593:
                    if (str.equals(Theme.key_tabSelectedIcon)) {
                        obj = 99;
                        break;
                    }
                    break;
                case 1008947016:
                    if (str.equals(Theme.key_avatar_backgroundActionBarRed)) {
                        obj = 27;
                        break;
                    }
                    break;
                case 1020100908:
                    if (str.equals(Theme.key_chat_inAudioSeekbarSelected)) {
                        obj = 216;
                        break;
                    }
                    break;
                case 1033466956:
                    if (str.equals(Theme.key_actionBarDefaultSearch)) {
                        obj = 16;
                        break;
                    }
                    break;
                case 1045660718:
                    if (str.equals(Theme.key_chats_menuName)) {
                        obj = 128;
                        break;
                    }
                    break;
                case 1045892135:
                    if (str.equals(Theme.key_windowBackgroundWhiteGrayIcon)) {
                        obj = 79;
                        break;
                    }
                    break;
                case 1046222043:
                    if (str.equals(Theme.key_windowBackgroundWhiteGrayText)) {
                        obj = 371;
                        break;
                    }
                    break;
                case 1069051047:
                    if (str.equals(Theme.key_tabCounter)) {
                        obj = 100;
                        break;
                    }
                    break;
                case 1079427869:
                    if (str.equals(Theme.key_chat_inViewsSelected)) {
                        obj = 193;
                        break;
                    }
                    break;
                case 1100033490:
                    if (str.equals(Theme.key_chat_inAudioSelectedProgress)) {
                        obj = 210;
                        break;
                    }
                    break;
                case 1106068251:
                    if (str.equals(Theme.key_groupcreate_spanText)) {
                        obj = 361;
                        break;
                    }
                    break;
                case 1121079660:
                    if (str.equals(Theme.key_chat_outAudioSeekbar)) {
                        obj = 264;
                        break;
                    }
                    break;
                case 1173499294:
                    if (str.equals(Theme.key_avatar_backgroundInProfileOrange)) {
                        obj = 57;
                        break;
                    }
                    break;
                case 1175786053:
                    if (str.equals(Theme.key_avatar_subtitleInProfileViolet)) {
                        obj = 38;
                        break;
                    }
                    break;
                case 1195322391:
                    if (str.equals(Theme.key_chat_inAudioProgress)) {
                        obj = 209;
                        break;
                    }
                    break;
                case 1196761690:
                    if (str.equals(Theme.key_chat_inLoaderSelected)) {
                        obj = 229;
                        break;
                    }
                    break;
                case 1199344772:
                    if (str.equals(Theme.key_chat_topPanelBackground)) {
                        obj = 331;
                        break;
                    }
                    break;
                case 1201609915:
                    if (str.equals(Theme.key_chat_outReplyNameText)) {
                        obj = 246;
                        break;
                    }
                    break;
                case 1202885960:
                    if (str.equals(Theme.key_chat_outPreviewInstantSelectedText)) {
                        obj = 282;
                        break;
                    }
                    break;
                case 1212117123:
                    if (str.equals(Theme.key_avatar_backgroundActionBarBlue)) {
                        obj = 23;
                        break;
                    }
                    break;
                case 1212158796:
                    if (str.equals(Theme.key_avatar_backgroundActionBarCyan)) {
                        obj = 43;
                        break;
                    }
                    break;
                case 1212531103:
                    if (str.equals(Theme.key_avatar_backgroundActionBarPink)) {
                        obj = 47;
                        break;
                    }
                    break;
                case 1229768718:
                    if (str.equals(Theme.key_checkboxSquareUnchecked)) {
                        obj = 71;
                        break;
                    }
                    break;
                case 1231763334:
                    if (str.equals(Theme.key_chat_addContact)) {
                        obj = 332;
                        break;
                    }
                    break;
                case 1248714848:
                    if (str.equals(Theme.key_chat_stickersHintPanel)) {
                        obj = 323;
                        break;
                    }
                    break;
                case 1269980952:
                    if (str.equals(Theme.key_chat_inBubble)) {
                        obj = 187;
                        break;
                    }
                    break;
                case 1285554199:
                    if (str.equals(Theme.key_avatar_backgroundActionBarOrange)) {
                        obj = 31;
                        break;
                    }
                    break;
                case 1288729698:
                    if (str.equals(Theme.key_chat_unreadMessagesStartArrowIcon)) {
                        obj = 342;
                        break;
                    }
                    break;
                case 1308150651:
                    if (str.equals(Theme.key_chat_outFileNameText)) {
                        obj = 272;
                        break;
                    }
                    break;
                case 1316752473:
                    if (str.equals(Theme.key_chat_outFileInfoSelectedText)) {
                        obj = 274;
                        break;
                    }
                    break;
                case 1320232494:
                    if (str.equals(Theme.key_chat_recordedVoiceProgressInner)) {
                        obj = 304;
                        break;
                    }
                    break;
                case 1327229315:
                    if (str.equals(Theme.key_actionBarDefaultSelector)) {
                        obj = 7;
                        break;
                    }
                    break;
                case 1333190005:
                    if (str.equals(Theme.key_chat_outForwardedNameText)) {
                        obj = 243;
                        break;
                    }
                    break;
                case 1366006767:
                    if (str.equals(Theme.key_avatar_backgroundInProfileViolet)) {
                        obj = 58;
                        break;
                    }
                    break;
                case 1372411761:
                    if (str.equals(Theme.key_inappPlayerPerformer)) {
                        obj = 144;
                        break;
                    }
                    break;
                case 1411374187:
                    if (str.equals(Theme.key_chat_messagePanelHint)) {
                        obj = 286;
                        break;
                    }
                    break;
                case 1411698028:
                    if (str.equals(Theme.key_chat_messagePanelSend)) {
                        obj = 288;
                        break;
                    }
                    break;
                case 1411728145:
                    if (str.equals(Theme.key_chat_messagePanelText)) {
                        obj = 285;
                        break;
                    }
                    break;
                case 1414117958:
                    if (str.equals(Theme.key_chat_outSiteNameText)) {
                        obj = 251;
                        break;
                    }
                    break;
                case 1441009150:
                    if (str.equals(Theme.key_avatar_actionBarIconOrange)) {
                        obj = 33;
                        break;
                    }
                    break;
                case 1450167170:
                    if (str.equals("chat_outContactPhoneText")) {
                        obj = 253;
                        break;
                    }
                    break;
                case 1468557089:
                    if (str.equals(Theme.key_avatar_backgroundInProfileRed)) {
                        obj = 56;
                        break;
                    }
                    break;
                case 1478061672:
                    if (str.equals(Theme.key_avatar_backgroundActionBarViolet)) {
                        obj = 35;
                        break;
                    }
                    break;
                case 1528152827:
                    if (str.equals(Theme.key_chat_inAudioTitleText)) {
                        obj = 211;
                        break;
                    }
                    break;
                case 1529596970:
                    if (str.equals(Theme.key_avatar_actionBarIconBlue)) {
                        obj = 25;
                        break;
                    }
                    break;
                case 1529638643:
                    if (str.equals(Theme.key_avatar_actionBarIconCyan)) {
                        obj = 45;
                        break;
                    }
                    break;
                case 1530010950:
                    if (str.equals(Theme.key_avatar_actionBarIconPink)) {
                        obj = 49;
                        break;
                    }
                    break;
                case 1536891843:
                    if (str.equals(Theme.key_checkbox)) {
                        obj = 74;
                        break;
                    }
                    break;
                case 1550703263:
                    if (str.equals(Theme.key_chat_inLoader)) {
                        obj = 228;
                        break;
                    }
                    break;
                case 1573464919:
                    if (str.equals(Theme.key_chat_serviceBackgroundSelected)) {
                        obj = 352;
                        break;
                    }
                    break;
                case 1595048395:
                    if (str.equals(Theme.key_chat_inAudioDurationText)) {
                        obj = 213;
                        break;
                    }
                    break;
                case 1628297471:
                    if (str.equals(Theme.key_chat_messageLinkIn)) {
                        obj = 191;
                        break;
                    }
                    break;
                case 1633516623:
                    if (str.equals(Theme.key_avatar_actionBarIconViolet)) {
                        obj = 37;
                        break;
                    }
                    break;
                case 1635685130:
                    if (str.equals(Theme.key_profile_verifiedCheck)) {
                        obj = 21;
                        break;
                    }
                    break;
                case 1637669025:
                    if (str.equals(Theme.key_chat_messageTextOut)) {
                        obj = 237;
                        break;
                    }
                    break;
                case 1647377944:
                    if (str.equals(Theme.key_chat_outViaBotNameText)) {
                        obj = 244;
                        break;
                    }
                    break;
                case 1655132457:
                    if (str.equals(Theme.key_chat_sentError)) {
                        obj = 180;
                        break;
                    }
                    break;
                case 1657795113:
                    if (str.equals(Theme.key_chat_outSentCheck)) {
                        obj = 239;
                        break;
                    }
                    break;
                case 1663688926:
                    if (str.equals(Theme.key_chats_attachMessage)) {
                        obj = 116;
                        break;
                    }
                    break;
                case 1674318617:
                    if (str.equals(Theme.key_divider)) {
                        obj = 76;
                        break;
                    }
                    break;
                case 1676443787:
                    if (str.equals(Theme.key_avatar_subtitleInProfileRed)) {
                        obj = 30;
                        break;
                    }
                    break;
                case 1682961989:
                    if (str.equals(Theme.key_switchThumbChecked)) {
                        obj = 66;
                        break;
                    }
                    break;
                case 1687612836:
                    if (str.equals(Theme.key_actionBarActionModeDefaultIcon)) {
                        obj = 12;
                        break;
                    }
                    break;
                case 1690535425:
                    if (str.equals(Theme.key_groupcreate_checkboxCheck)) {
                        obj = 359;
                        break;
                    }
                    break;
                case 1706062567:
                    if (str.equals(Theme.key_windowBackgroundWhiteLinkSelection)) {
                        obj = 83;
                        break;
                    }
                    break;
                case 1714118894:
                    if (str.equals(Theme.key_chat_unreadMessagesStartBackground)) {
                        obj = 341;
                        break;
                    }
                    break;
                case 1737023367:
                    if (str.equals(Theme.key_chat_goDownButtonCounterBackground)) {
                        obj = 348;
                        break;
                    }
                    break;
                case 1796336943:
                    if (str.equals(Theme.key_profile_creatorIcon)) {
                        obj = 80;
                        break;
                    }
                    break;
                case 1809914009:
                    if (str.equals(Theme.key_dialogButtonSelector)) {
                        obj = 154;
                        break;
                    }
                    break;
                case 1814021667:
                    if (str.equals(Theme.key_chat_inFileInfoText)) {
                        obj = 224;
                        break;
                    }
                    break;
                case 1819958985:
                    if (str.equals(Theme.key_dialogBadgeBackground)) {
                        obj = 161;
                        break;
                    }
                    break;
                case 1828201066:
                    if (str.equals(Theme.key_dialogTextBlack)) {
                        obj = 149;
                        break;
                    }
                    break;
                case 1828220291:
                    if (str.equals(Theme.key_dialogTextBlue2)) {
                        obj = 156;
                        break;
                    }
                    break;
                case 1828220292:
                    if (str.equals(Theme.key_dialogTextBlue3)) {
                        obj = 157;
                        break;
                    }
                    break;
                case 1829565163:
                    if (str.equals(Theme.key_chat_inMenu)) {
                        obj = 207;
                        break;
                    }
                    break;
                case 1832998042:
                    if (str.equals(Theme.key_dialogTextGray2)) {
                        obj = 152;
                        break;
                    }
                    break;
                case 1832998044:
                    if (str.equals(Theme.key_dialogTextGray4)) {
                        obj = 158;
                        break;
                    }
                    break;
                case 1853943154:
                    if (str.equals(Theme.key_chat_messageTextIn)) {
                        obj = 190;
                        break;
                    }
                    break;
                case 1878895888:
                    if (str.equals(Theme.key_avatar_actionBarSelectorBlue)) {
                        obj = 24;
                        break;
                    }
                    break;
                case 1878937561:
                    if (str.equals(Theme.key_avatar_actionBarSelectorCyan)) {
                        obj = 44;
                        break;
                    }
                    break;
                case 1879309868:
                    if (str.equals(Theme.key_avatar_actionBarSelectorPink)) {
                        obj = 48;
                        break;
                    }
                    break;
                case 1919725567:
                    if (str.equals(Theme.key_chat_botButtonText)) {
                        obj = 182;
                        break;
                    }
                    break;
                case 1921699010:
                    if (str.equals(Theme.key_chats_unreadCounterMuted)) {
                        obj = 105;
                        break;
                    }
                    break;
                case 1924610295:
                    if (str.equals(Theme.key_chat_mediaTimeText)) {
                        obj = 177;
                        break;
                    }
                    break;
                case 1929729373:
                    if (str.equals(Theme.key_progressCircle)) {
                        obj = 84;
                        break;
                    }
                    break;
                case 1930276193:
                    if (str.equals(Theme.key_chat_inTimeSelectedText)) {
                        obj = 206;
                        break;
                    }
                    break;
                case 1971615773:
                    if (str.equals(Theme.key_avatar_backgroundRed)) {
                        obj = 90;
                        break;
                    }
                    break;
                case 1972802227:
                    if (str.equals(Theme.key_chat_outReplyMediaMessageText)) {
                        obj = 248;
                        break;
                    }
                    break;
                case 1994112714:
                    if (str.equals(Theme.key_actionBarActionModeDefaultTop)) {
                        obj = 11;
                        break;
                    }
                    break;
                case 2016511272:
                    if (str.equals(Theme.key_stickers_menu)) {
                        obj = 78;
                        break;
                    }
                    break;
                case 2037749678:
                    if (str.equals(Theme.key_groupcreate_sectionText)) {
                        obj = 363;
                        break;
                    }
                    break;
                case 2047871903:
                    if (str.equals(Theme.key_dialogRoundCheckBoxCheck)) {
                        obj = 160;
                        break;
                    }
                    break;
                case 2052611411:
                    if (str.equals(Theme.key_chat_outBubble)) {
                        obj = 234;
                        break;
                    }
                    break;
                case 2067556030:
                    if (str.equals(Theme.key_chat_emojiPanelIcon)) {
                        obj = 316;
                        break;
                    }
                    break;
                case 2090082520:
                    if (str.equals(Theme.key_chats_nameMessage)) {
                        obj = 113;
                        break;
                    }
                    break;
                case 2105341053:
                    if (str.equals(Theme.key_chats_draft)) {
                        obj = 115;
                        break;
                    }
                    break;
                case 2109820260:
                    if (str.equals(Theme.key_avatar_actionBarSelectorOrange)) {
                        obj = 32;
                        break;
                    }
                    break;
                case 2118871810:
                    if (str.equals(Theme.key_switchThumb)) {
                        obj = 64;
                        break;
                    }
                    break;
                case 2119150199:
                    if (str.equals(Theme.key_switchTrack)) {
                        obj = 65;
                        break;
                    }
                    break;
                case 2131990258:
                    if (str.equals(Theme.key_windowBackgroundWhiteLinkText)) {
                        obj = 82;
                        break;
                    }
                    break;
                case 2133456819:
                    if (str.equals(Theme.key_chat_emojiPanelBackground)) {
                        obj = 314;
                        break;
                    }
                    break;
                case 2141345810:
                    if (str.equals(Theme.key_chat_messagePanelBackground)) {
                        obj = 283;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return "زمینه صفحه";
                case 1:
                    return "زمینه صفحه";
                case 2:
                    return "بخش خاکستری";
                case 3:
                    return "متن در بخش خاکستری";
                case 4:
                    return "سایه بالای بخش خاکستری";
                case 5:
                    return "گزیننده سطر در فهرست";
                case 6:
                    return "زمینه سربرگ";
                case 7:
                    return "گزیننده در سربرگ";
                case 8:
                    return "گزیننده در سربرگ عملیاتی";
                case 9:
                    return "آیکون در سربرگ";
                case 10:
                    return "زمینه سربرگ عملیاتی";
                case 11:
                    return "نوار وضعیت در سربرگ عملیاتی";
                case 12:
                    return "آیکون در سربرگ عملیاتی";
                case 13:
                    return "گزیننده در سربرگ عملیاتی";
                case 14:
                    return "عنوان در سربرگ";
                case 15:
                    return "زیر عنوان در سربرگ";
                case 16:
                    return "متن جستجو در سربرگ";
                case 17:
                    return "متن پیشفرض جستجو در سربرگ";
                case 18:
                    return "گزینه های منوی سربرگ";
                case 19:
                    return "زمینه منوی سربرگ";
                case 20:
                    return "عنوان در سربرگ گسترده";
                case 21:
                    return "تیک آیکون تایید";
                case 22:
                    return "زمینه آیکون تایید";
                case 23:
                    return "سربرگ گسترده (آبی)";
                case 24:
                    return "گزیننده در سربرگ گسترده (آبی)";
                case 25:
                    return "آیکون در سربرگ گسترده (آبی)";
                case 26:
                    return "زیر عنوان در سربرگ گسترده (آبی)";
                case 27:
                    return "سربرگ گسترده (قرمز)";
                case 28:
                    return "گزیننده در سربرگ گسترده (قرمز)";
                case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                    return "آیکون در سربرگ گسترده (قرمز)";
                case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                    return "زیر عنوان در سربرگ گسترده (قرمز)";
                case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                    return "سربرگ گسترده (نارنجی)";
                case 32:
                    return "گزیننده در سربرگ گسترده (نارنجی)";
                case 33:
                    return "آیکون در سربرگ گسترده (نارنجی)";
                case 34:
                    return "زیر عنوان در سربرگ گسترده (نارنجی)";
                case 35:
                    return "سربرگ گسترده (بنفش)";
                case 36:
                    return "گزیننده در سربرگ گسترده (بنفش)";
                case 37:
                    return "آیکون در سربرگ گسترده (بنفش)";
                case 38:
                    return "زیر عنوان در سربرگ گسترده (بنفش)";
                case 39:
                    return "سربرگ گسترده (سبز)";
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    return "گزیننده در سربرگ گسترده (سبز)";
                case 41:
                    return "آیکون در سربرگ گسترده (سبز)";
                case 42:
                    return "زیر عنوان در سربرگ گسترده (سبز)";
                case 43:
                    return "سربرگ گسترده (کبودی)";
                case 44:
                    return "گزیننده در سربرگ گسترده (کبودی)";
                case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                    return "آیکون در سربرگ گسترده (کبودی)";
                case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                    return "زیر عنوان در سربرگ گسترده (کبودی)";
                case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                    return "سربرگ گسترده (صورتی)";
                case 48:
                    return "گزیننده در سربرگ گسترده (صورتی)";
                case 49:
                    return "آیکون در سربرگ گسترده (صورتی)";
                case 50:
                    return "زیر عنوان در سربرگ گسترده (صورتی)";
                case 51:
                    return "آیکون دکمه دایره ای روی سربرگ";
                case 52:
                    return "زمینه دکمه دایره ای روی سربرگ";
                case 53:
                    return "گزیننده دکمه دایره ای روی سربرگ";
                case 54:
                    return "متن آواتار الفبایی";
                case 55:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (آبی)";
                case 56:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (قرمز)";
                case 57:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (نارنجی)";
                case 58:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (بنفش)";
                case 59:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (سبز)";
                case 60:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (کبودی)";
                case 61:
                    return "زمینه آواتار الفبایی در سربرگ گسترده (صورتی)";
                case 62:
                    return "عنوان بخشهای تنظیمات";
                case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                    return "متن لیست خالی";
                case 64:
                    return "شستی سوییچ";
                case VoIPService.CALL_MIN_LAYER /*65*/:
                    return "شیار سوییچ";
                case 66:
                    return "شستی سوییچ (روشن)";
                case 67:
                    return "شیار سوییچ (روشن)";
                case 68:
                    return "زمینه چک باکس";
                case 69:
                    return "تیک چک باکس";
                case 70:
                    return "زمینه چک باکس غیرفعال";
                case TsExtractor.TS_SYNC_BYTE /*71*/:
                    return "کادر چک باکس انتخاب نشده";
                case XtraBox.MP4_XTRA_BT_GUID /*72*/:
                    return "دکمه رادیویی";
                case SecretChatHelper.CURRENT_SECRET_CHAT_LAYER /*73*/:
                    return "دکمه رادیویی انتخاب شده";
                case VoIPService.CALL_MAX_LAYER /*74*/:
                    return "چک باکس";
                case 75:
                    return "چک باکس انتخاب شده";
                case 76:
                    return "خط جدا کننده";
                case 77:
                    return "تیک قالب فعال";
                case 78:
                    return "آیکون سه نقطه مقابل سطر";
                case 79:
                    return "آیکونها";
                case 80:
                    return "آیکون سازنده";
                case 81:
                    return "آیکون ادمینها";
                case 82:
                    return "لینک";
                case 83:
                    return "زمینه لینک کلیک شده";
                case 84:
                    return "آیکون بارگزاری (دایره چرخان)";
                case TLRPC.LAYER /*85*/:
                    return "گزیننده منوی استیکر";
                case 86:
                    return "نوار مرور سریع (فعال)";
                case 87:
                    return "نوار مرور سریع (غیرفعال)";
                case 88:
                    return "متن نوار مرور";
                case TsExtractor.TS_STREAM_TYPE_DVBSUBS /*89*/:
                    return "بخش خاکستری";
                case AdSize.LARGE_AD_HEIGHT /*90*/:
                    return "زمینه آواتار الفبایی در فهرست (قرمز)";
                case 91:
                    return "زمینه آواتار الفبایی در فهرست (نارنجی)";
                case 92:
                    return "زمینه آواتار الفبایی در فهرست (بنفش)";
                case 93:
                    return "زمینه آواتار الفبایی در فهرست (سبز)";
                case 94:
                    return "زمینه آواتار الفبایی در فهرست (کبودی)";
                case 95:
                    return "زمینه آواتار الفبایی در فهرست (آبی)";
                case 96:
                    return "زمینه آواتار الفبایی در فهرست (صورتی)";
                case 97:
                    return "زمینه زبانه ها";
                case 98:
                    return "آیکون زبانه ها";
                case ConnectionResult.UNFINISHED /*99*/:
                    return "آیکون زبانه انتخاب شده";
                case 100:
                    return "زمینه شمارنده روی زبانه ها";
                case 101:
                    return "متن شمارنده روی زبانه ها";
                case 102:
                    return "زمینه شمارنده روی زبانه ها (بی صدا)";
                case 103:
                    return "متن شمارنده روی زبانه ها (بی صدا)";
                case 104:
                    return "زمینه شمارنده پیامهای خوانده نشده";
                case 105:
                    return "زمینه شمارنده پیامهای خوانده نشده (بی صدا)";
                case 106:
                    return "متن شمارنده پیامهای خوانده نشده";
                case 107:
                    return "نام گفتگو";
                case 108:
                    return "نام گفتگوی محرمانه";
                case 109:
                    return "آیکون گفتگو";
                case 110:
                    return "آیکون گفتگوی محرمانه";
                case 111:
                    return "آیکون سنجاق کردن";
                case 112:
                    return "متن پیام";
                case 113:
                    return "نام پیام دهنده در گروه";
                case 114:
                    return "عبارت (در حال نوشتن)";
                case 115:
                    return "پیشنویس";
                case 116:
                    return "ضمیمه (عکس، ویدئو، استیکر)";
                case 117:
                    return "تاریخ";
                case 118:
                    return "زمینه گفتگوی سنجاق شده";
                case 119:
                    return "تیک پیام";
                case 120:
                    return "آیکون ساعت";
                case 121:
                    return "زمینه آیکون خطای ارسال";
                case 122:
                    return "متن آیکون خطای ارسال";
                case 123:
                    return "تیک آیکون تایید";
                case 124:
                    return "زمینه آیکون تایید";
                case 125:
                    return "آیکون گفتگوی بی صدا";
                case 126:
                    return "زمینه منوی کناری";
                case 127:
                    return "سایه زیر نام و تلفن در منوی کناری";
                case 128:
                    return "نام در منوی کناری";
                case 129:
                    return "شماره تلفن در منوی کناری (بدون عکس زمینه)";
                case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /*130*/:
                    return "شماره تلفن در منوی کناری (با عکس زمینه)";
                case 131:
                    return "زمینه آیکون ابر در منوی کناری";
                case 132:
                    return "آیکون ابر در منوی کناری";
                case 133:
                    return "آیکون گزینه ها در منوی کناری";
                case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /*134*/:
                    return "متن گزینه ها در منوی کناری";
                case TsExtractor.TS_STREAM_TYPE_E_AC3 /*135*/:
                    return "آخرین بازدید مخاطبین در نتیجه جستجو (آفلاین)";
                case 136:
                    return "آخرین بازدید مخاطبین در نتیجه جستجو (آنلاین)";
                case 137:
                    return "آیکون دکمه شناور";
                case TsExtractor.TS_STREAM_TYPE_DTS /*138*/:
                    return "زمینه دکمه شناور";
                case 139:
                    return "زمینه دکمه شناور کلیک شده";
                case 140:
                    return "گفتگوی انتخاب شده در تبلت";
                case 141:
                    return "زمینه پخش کننده موزیک";
                case 142:
                    return "دکمه پخش و توقف پخش کننده موزیک";
                case 143:
                    return "عنوان  در پخش کننده موزیک";
                case 144:
                    return "خواننده در پخش کننده موزیک";
                case 145:
                    return "دکمه بستن پخش کننده موزیک";
                case 146:
                    return "زمینه دکمه بازگشت به تماس";
                case 147:
                    return "متن دکمه بازگشت به تماس";
                case 148:
                    return "زمینه دیالوگ";
                case 149:
                    return "متن در دیالوگ";
                case 150:
                    return "لینک در دیالوگ";
                case 151:
                    return "آیکونها در دیالوگ";
                case 152:
                    return "عنوان در دیالوگ کشویی";
                case 153:
                    return "دکمه های دیالوگ پاپ آپ";
                case 154:
                    return "گزیننده دکمه های دیالوگ پاپ آپ";
                case 155:
                    return "دکمه حذف در پنجره نمایش پک استیکر";
                case 156:
                    return "دکمه های دیالوگ کشویی";
                case 157:
                    return "دکمه فعال ارسال در پنجره فوروارد چندگانه";
                case 158:
                    return "دکمه غیرفعال ارسال در پنجره فوروارد چندگانه";
                case 159:
                    return "چک باکس در پنجره فوروارد چندگانه";
                case 160:
                    return "تیک چک باکس در پنجره فوروارد چندگانه";
                case 161:
                    return "زمینه تعداد تیکها در پنجره فوروارد چندگانه";
                case 162:
                    return "متن تعداد تیکها در پنجره فوروارد چندگانه";
                case 163:
                    return "عبارت 'ارسال به...' در پنجره فوروارد چندگانه";
                case 164:
                    return "زمینه گفتگو";
                case 165:
                    return "آیکون گفتگوی بی صدا در سربرگ صفحه";
                case 166:
                    return "آیکون گفتگوی محرمانه درسربرگ صفحه";
                case 167:
                    return "تیک پیام در عکس و ویدئو";
                case 168:
                    return "آیکون تعداد بازدید در عکس و ویدئو";
                case 169:
                    return "آیکون سه نقطه در عکس و ویدئو";
                case 170:
                    return "متن اطلاعات در عکس و ویدئو";
                case 171:
                    return "پس زمینه پیامهای انتخاب شده";
                case 172:
                    return "نام ربات برای استیکر";
                case 173:
                    return "خط کنار پاسخ برای استیکر";
                case 174:
                    return "نام در پاسخ برای استیکر";
                case 175:
                    return "متن پیام در پاسخ برای استیکر";
                case 176:
                    return "نوار پیشرفت برای عکس و ویدئو";
                case 177:
                    return "ساعت ارسال برای عکس و ویدئو";
                case 178:
                    return "آیکون تماس ناموفق (فلش قرمز)";
                case 179:
                    return "آیکون تماس موفق (فلش سبز)";
                case 180:
                    return "زمینه آیکون خطای ارسال";
                case 181:
                    return "متن آیکون خطای ارسال";
                case 182:
                    return "متن دکمه های شیشه ای رباتها";
                case 183:
                    return "بارکننده در عکس و ویدئو";
                case 184:
                    return "آیکون بارکننده در عکس و ویدئو";
                case 185:
                    return "بارکننده انتخاب شده در عکس و ویدئو";
                case 186:
                    return "آیکون بارکننده انتخاب شده در عکس و ویدئو";
                case 187:
                    return "زمینه پیام (دریافتی)";
                case TsExtractor.TS_PACKET_SIZE /*188*/:
                    return "سایه زمینه پیام (دریافتی)";
                case PsExtractor.PRIVATE_STREAM_1 /*189*/:
                    return "زمینه پیام انتخاب شده (دریافتی)";
                case 190:
                    return "متن پیام (دریافتی)";
                case 191:
                    return "لینک در پیام (دریافتی)";
                case PsExtractor.AUDIO_STREAM /*192*/:
                    return "آیکون تعداد بازدید (دریافتی)";
                case 193:
                    return "آیکون تعداد بازدید انتخاب شده (دریافتی)";
                case 194:
                    return "نام فوروارد شونده (دریافتی)";
                case 195:
                    return "نام ربات (دریافتی)";
                case 196:
                    return "خط کنار پاسخ (دریافتی)";
                case 197:
                    return "نام در پاسخ (دریافتی)";
                case 198:
                    return "متن پیام در پاسخ (دریافتی)";
                case 199:
                    return "متن پیام در پاسخ برای عکسها (دریافتی)";
                case Callback.DEFAULT_DRAG_ANIMATION_DURATION /*200*/:
                    return "متن پیام انتخاب شده در پاسخ برای عکسها (دریافتی)";
                case 201:
                    return "خط کنار پیش نمایش سایت (دریافتی)";
                case 202:
                    return "نام سایت (دریافتی)";
                case 203:
                    return "نام مخاطب به اشتراک گذاشته شده (دریافتی)";
                case 204:
                    return "تلفن مخاطب به اشتراک گذاشته شده (دریافتی)";
                case 205:
                    return "ساعت ارسال (دریافتی)";
                case 206:
                    return "ساعت در پیام انتخاب شده (دریافتی)";
                case 207:
                    return "آیکون سه نقطه روی پیام (دریافتی)";
                case 208:
                    return "آیکون سه نقطه روی پیام انتخاب شده (دریافتی)";
                case 209:
                    return "دایره بارکننده موزیک و صدا (دریافتی)";
                case 210:
                    return "دایره بارکننده موزیک و صدا انتخاب شده (دریافتی)";
                case 211:
                    return "عنوان موزیک (دریافتی)";
                case 212:
                    return "خواننده موزیک (دریافتی)";
                case 213:
                    return "اطلاعات موزیک (دریافتی)";
                case 214:
                    return "اطلاعات موزیک انتخاب شده (دریافتی)";
                case 215:
                    return "شیار پیمایش موزیک (دریافتی)";
                case 216:
                    return "شیار پیمایش موزیک انتخاب شده (دریافتی)";
                case 217:
                    return "شیار پیمایش پر شده موزیک (دریافتی)";
                case 218:
                    return "شیار پیمایش پیام صوتی (دریافتی)";
                case 219:
                    return "شیار پیمایش پیام صوتی انتخاب شده (دریافتی)";
                case 220:
                    return "شیار پیمایش پیام صوتی پر شده (دریافتی)";
                case 221:
                    return "دایره بارکننده فایل (دریافتی)";
                case 222:
                    return "دایره بارکننده فایل انتخاب شده (دریافتی)";
                case 223:
                    return "نام فایل (دریافتی)";
                case 224:
                    return "اطلاعات فایل (دریافتی)";
                case 225:
                    return "اطلاعات فایل انتخاب شده (دریافتی)";
                case 226:
                    return "زمینه فایل عکس (دریافتی)";
                case 227:
                    return "زمینه فایل عکس انتخاب شده (دریافتی)";
                case 228:
                    return "بارکننده (دریافتی)";
                case 229:
                    return "بارکننده انتخاب شده (دریافتی)";
                case 230:
                    return "آیکون تلفن در تماس ورودی";
                case 231:
                    return "آیکون تلفن در تماس ورودی انتخاب شده";
                case 232:
                    return "دکمه نمایش سریع سایت (دریافتی)";
                case 233:
                    return "دکمه نمایش سریع سایت انتخاب شده (دریافتی)";
                case 234:
                    return "زمینه پیام (ارسالی)";
                case 235:
                    return "سایه زمینه پیام (ارسالی)";
                case 236:
                    return "زمینه پیام انتخاب شده (ارسالی)";
                case 237:
                    return "متن پیام (ارسالی)";
                case 238:
                    return "لینک در پیام (ارسالی)";
                case 239:
                    return "تیک پیام";
                case PsExtractor.VIDEO_STREAM_MASK /*240*/:
                    return "تیک پیام انتخاب شده";
                case 241:
                    return "آیکون تعداد بازدید (ارسالی)";
                case 242:
                    return "آیکون تعداد بازدید انتخاب شده (ارسالی)";
                case 243:
                    return "نام فوروارد شونده (ارسالی)";
                case 244:
                    return "نام ربات (ارسالی)";
                case 245:
                    return "خط کنار پاسخ (ارسالی)";
                case 246:
                    return "نام در پاسخ (ارسالی)";
                case 247:
                    return "متن پیام در پاسخ (ارسالی)";
                case 248:
                    return "متن پیام در پاسخ برای عکسها (ارسالی)";
                case 249:
                    return "متن پیام انتخاب شده در پاسخ برای عکسها (ارسالی)";
                case Callback.DEFAULT_SWIPE_ANIMATION_DURATION /*250*/:
                    return "خط کنار پیش نمایش سایت (ارسالی)";
                case 251:
                    return "نام سایت (ارسالی)";
                case 252:
                    return "نام مخاطب به اشتراک گذاشته شده (ارسالی)";
                case 253:
                    return "تلفن مخاطب به اشتراک گذاشته شده (ارسالی)";
                case 254:
                    return "ساعت ارسال (ارسالی)";
                case 255:
                    return "ساعت در پیام انتخاب شده (ارسالی)";
                case 256:
                    return "آیکون سه نقطه روی پیام (ارسالی)";
                case InputDeviceCompat.SOURCE_KEYBOARD /*257*/:
                    return "آیکون سه نقطه روی پیام انتخاب شده (ارسالی)";
                case 258:
                    return "دایره بارکننده موزیک و صدا (ارسالی)";
                case 259:
                    return "دایره بارکننده موزیک و صدا انتخاب شده (ارسالی)";
                case 260:
                    return "عنوان موزیک (ارسالی)";
                case 261:
                    return "خواننده موزیک (ارسالی)";
                case 262:
                    return "اطلاعات موزیک (ارسالی)";
                case 263:
                    return "اطلاعات موزیک انتخاب شده (ارسالی)";
                case 264:
                    return "شیار پیمایش موزیک (ارسالی)";
                case 265:
                    return "شیار پیمایش موزیک انتخاب شده (ارسالی)";
                case 266:
                    return "شیار پیمایش پر شده موزیک (ارسالی)";
                case 267:
                    return "شیار پیمایش پیام صوتی (ارسالی)";
                case 268:
                    return "شیار پیمایش پیام صوتی انتخاب شده (ارسالی)";
                case 269:
                    return "شیار پیمایش پیام صوتی پر شده (ارسالی)";
                case 270:
                    return "دایره بارکننده فایل (ارسالی)";
                case 271:
                    return "دایره بارکننده فایل انتخاب شده (ارسالی)";
                case 272:
                    return "نام فایل (ارسالی)";
                case 273:
                    return "اطلاعات فایل (ارسالی)";
                case 274:
                    return "اطلاعات فایل انتخاب شده (ارسالی)";
                case 275:
                    return "زمینه فایل عکس (ارسالی)";
                case 276:
                    return "زمینه فایل عکس انتخاب شده (دریافتی)";
                case 277:
                    return "بارکننده (ارسالی)";
                case 278:
                    return "بارکننده انتخاب شده (ارسالی)";
                case ModuleDescriptor.MODULE_VERSION /*279*/:
                    return "آیکون تلفن در تماس خروجی";
                case 280:
                    return "آیکون تلفن در تماس خروجی انتخاب شده";
                case 281:
                    return "دکمه نمایش سریع سایت (ارسالی)";
                case 282:
                    return "دکمه نمایش سریع سایت انتخاب شده (ارسالی)";
                case 283:
                    return "زمینه ویرایشگر پیام";
                case 284:
                    return "سایه ویرایشگر پیام";
                case 285:
                    return "متن ویرایشگر پیام";
                case 286:
                    return "متن پیشفرض ویرایشگر پیام";
                case 287:
                    return "آیکونهای ویرایشگر پیام";
                case 288:
                    return "آیکون ارسال ویرایشگر پیام";
                case 289:
                    return "آیکون ویرایش پیام";
                case 290:
                    return "متن دکمه عضویت در کانال";
                case Policy.RETRY /*291*/:
                    return "متن نمایش وضعیت گفتگوی محرمانه";
                case 292:
                    return "آیکون ضبط صدا در زمان فشار دادن";
                case 293:
                    return "آیکون حذف صدای ضبط شده در پیش نمایش";
                case 294:
                    return "زمینه شاستی پنل ضبط صدا";
                case 295:
                    return "سایه شاستی پنل ضبط صدا";
                case 296:
                    return "متن زمان در پنل ضبط صدا";
                case 297:
                    return "متن لغو ضبط در پنل ضبط صدا";
                case 298:
                    return "زمان صدای ضبط شده در پیش نمایش";
                case 299:
                    return "زمینه صدای ضبط شده در پیش نمایش";
                case 300:
                    return "چراغ ضبط صدا";
                case 301:
                    return "دکمه پخش و توقف صدا در پیش نمایش";
                case 302:
                    return "دکمه پخش و توقف صدا در پیش نمایش (کلیک شده)";
                case 303:
                    return "شیار پیمایش صدا در پیش نمایش";
                case 304:
                    return "شیار پیمایش پر شده صدا در پیش نمایش";
                case 305:
                    return "نام فرستنده پیام در گروه ها (قرمز)";
                case 306:
                    return "نام فرستنده پیام در گروه ها (نارنجی)";
                case 307:
                    return "نام فرستنده پیام در گروه ها (بنفش)";
                case 308:
                    return "نام فرستنده پیام در گروه ها (سبز)";
                case 309:
                    return "نام فرستنده پیام در گروه ها (کبودی)";
                case 310:
                    return "نام فرستنده پیام در گروه ها (آبی)";
                case 311:
                    return "نام فرستنده پیام در گروه ها (صورتی)";
                case 312:
                    return "چراغ پک استیکر جدید روی آیکون شکلک در ویرایشگر";
                case 313:
                    return "متن پنل خالی شکلکها";
                case 314:
                    return "زمینه پنل شکلکها";
                case 315:
                    return "خط زیر زبانه ها در پنل شکلکها";
                case 316:
                    return "آیکون زبانها در پنل شکلکها";
                case 317:
                    return "آیکون زبانه انتخاب شده در پنل شکلکها";
                case 318:
                    return "گزیننده آیکون زبانها در پنل شکلکها";
                case 319:
                    return "آیکون حذف شکلک در پنل شکلکها";
                case 320:
                    return "زمینه زبانه استیکر انتخاب شده";
                case 321:
                    return "عنوان پک استیکر در بخش استیکرهای داغ";
                case 322:
                    return "توضیحات پک استیکر در بخش استیکرهای داغ";
                case 323:
                    return "پنل پاپ آپ اسکتیر";
                case 324:
                    return "زمینه دکمه های صفحه کلید ربات";
                case 325:
                    return "زمینه دکمه انتخاب شده صفحه کلید ربات";
                case 326:
                    return "متن دکمه های صفحه کلید ربات";
                case 327:
                    return "خط کنار در پنل پیام سنجاق شده";
                case 328:
                    return "عنوان در پنل پیام سنجاق شده";
                case 329:
                    return "پیام در پنل پیام سنجاق شده";
                case 330:
                    return "آیکون بستن پنل پیام سنجاق شده";
                case 331:
                    return "زمینه پنل پیام سنجاق شده";
                case 332:
                    return "دکمه افزودن مخاطبت در پنل سنجاق شده";
                case 333:
                    return "دکمه گزارش اسپم مخاطبت در پنل سنجاق شده";
                case 334:
                    return "خط زیر در پنل پاسخ";
                case 335:
                    return "نام در پنل پاسخ";
                case 336:
                    return "پیام در پنل پاسخ";
                case 337:
                    return "آیکونها در پنل پاسخ";
                case 338:
                    return "دکمه بستن پنل پاسخ";
                case 339:
                    return "آیکونهای پنل جستجو";
                case 340:
                    return "متن در پنل جستجو";
                case 341:
                    return "زمینه خط شروع پیامهای خوانده نشده";
                case 342:
                    return "آیکون خط شروع پیامهای خوانده نشده";
                case 343:
                    return "متن خط شروع پیامهای خوانده نشده";
                case 344:
                    return "زمینه دکمه شناور رفتن به پیام آخر";
                case 345:
                    return "سایه دکمه شناور رفتن به پیام آخر";
                case 346:
                    return "آیکون دکمه شناور رفتن به پیام آخر";
                case 347:
                    return "متن شمارنده روی دکمه شناور";
                case 348:
                    return "زمینه شمارنده روی دکمه شناور";
                case 349:
                    return "زمینه تایمر گفتگوی محرمانه در سربرگ";
                case 350:
                    return "متن تایمر گفتگوی محرمانه در سربرگ";
                case 351:
                    return "زمینه دکمه فوروارد مستقیم";
                case 352:
                    return "زمینه دکمه فوروارد مستقیم انتخاب شده";
                case 353:
                    return "متن تاریخ و تاریخ شناور";
                case 354:
                    return "آیکون دکمه فوروارد مستقیم";
                case 355:
                    return "نام افراد در پیام پیوستن آنها به گروه";
                case 356:
                    return "آخرین بازدید مخاطبین (آفلاین)";
                case 357:
                    return "آخرین بازدید مخاطبین (آنلاین)";
                case 358:
                    return "زمینه چک باکس";
                case 359:
                    return "تیک چک باکس";
                case 360:
                    return "زمینه مخاطب انتخاب شده";
                case 361:
                    return "متن مخاطب انتخاب شده";
                case 362:
                    return "سایه هر بخش";
                case 363:
                    return "متن هر بخش";
                case 364:
                    return "مکان نمای متن";
                case 365:
                    return "متن اصلی";
                case 366:
                    return "مقدار روبروی گزینه ها";
                case 367:
                    return "متن زیر شماره و نام کاربری";
                case 368:
                    return "آخرین بازدید مخاطبین (آنلاین)";
                case 369:
                    return "دکمه شروع گفتگوی محرمانه";
                case 370:
                    return "دکمه ترک کانال";
                case 371:
                    return "آخرین بازدید مخاطبین (آفلاین)";
                case 372:
                    return "متن اطلاعاتی آخر صفحه";
            }
        }
        return this.currentKey;
    }
}
