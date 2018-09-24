package turbogram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig$Storage;
import turbogram.Utilities.TurboUtils;

public class StorageSelectActivity extends BaseFragment {
    private EmptyTextProgressView emptyView;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private BroadcastReceiver receiver = new C24361();
    private boolean receiverRegistered = false;
    private ArrayList<StorageItem> storageItems = new ArrayList();

    /* renamed from: turbogram.StorageSelectActivity$1 */
    class C24361 extends BroadcastReceiver {

        /* renamed from: turbogram.StorageSelectActivity$1$1 */
        class C24351 implements Runnable {
            C24351() {
            }

            public void run() {
                try {
                    StorageSelectActivity.this.getStorageList();
                } catch (Throwable e) {
                    FileLog.e(e);
                }
            }
        }

        C24361() {
        }

        public void onReceive(Context context, Intent intent) {
            Runnable r = new C24351();
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(intent.getAction())) {
                StorageSelectActivity.this.listView.postDelayed(r, 1000);
            } else {
                r.run();
            }
        }
    }

    /* renamed from: turbogram.StorageSelectActivity$2 */
    class C24372 extends ActionBarMenuOnItemClick {
        C24372() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                StorageSelectActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.StorageSelectActivity$3 */
    class C24423 implements OnItemClickListener {
        C24423() {
        }

        public void onItemClick(View view, int position) {
            final File selected = ((StorageItem) StorageSelectActivity.this.storageItems.get(position)).file;
            final SparseArray<File> paths;
            if (Environment.getExternalStorageDirectory().getAbsolutePath().startsWith(selected.getPath())) {
                TurboConfig$Storage.removeValue("storage_device");
                paths = ImageLoader.getInstance().createMediaPaths();
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        FileLoader.getInstance(StorageSelectActivity.this.currentAccount);
                        FileLoader.setMediaDirs(paths);
                    }
                });
                StorageSelectActivity.this.finishFragment();
            } else if (TurboUtils.testWrite(selected.getPath())) {
                TurboConfig$Storage.setStringValue("storage_device", selected.getPath());
                paths = ImageLoader.getInstance().createMediaPaths();
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        FileLoader.getInstance(StorageSelectActivity.this.currentAccount);
                        FileLoader.setMediaDirs(paths);
                    }
                });
                StorageSelectActivity.this.finishFragment();
            } else if (VERSION.SDK_INT >= 21) {
                Builder builder = new Builder(StorageSelectActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                File files = new File(selected, "Android/data/" + ApplicationLoader.applicationContext.getPackageName() + "/files");
                builder.setMessage(LocaleController.formatString("StorageDeviceAlert", R.string.StorageDeviceAlert, new Object[]{files.getPath()}));
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TurboConfig$Storage.setStringValue("storage_device", selected.getPath());
                        final SparseArray<File> paths = ImageLoader.getInstance().createMediaPaths();
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                FileLoader.getInstance(StorageSelectActivity.this.currentAccount);
                                FileLoader.setMediaDirs(paths);
                            }
                        });
                        StorageSelectActivity.this.finishFragment();
                    }
                });
                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                StorageSelectActivity.this.showDialog(builder.create());
            } else {
                TurboUtils.showToast(StorageSelectActivity.this.getParentActivity(), LocaleController.getString("StorageDeviceCantGrantWrite", R.string.StorageDeviceCantGrantWrite), 1);
            }
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private ListAdapter() {
        }

        public int getItemCount() {
            return StorageSelectActivity.this.storageItems.size();
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            SharedDocumentCell sharedDocumentCell = new SharedDocumentCell(StorageSelectActivity.this.getParentActivity());
            sharedDocumentCell.setLayoutParams(new LayoutParams(-1, -2));
            return new Holder(sharedDocumentCell);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            SharedDocumentCell sharedDocumentCell = holder.itemView;
            StorageItem storageItem = (StorageItem) StorageSelectActivity.this.storageItems.get(position);
            sharedDocumentCell.setTextAndValueAndTypeAndThumb(storageItem.title, storageItem.subtitle, null, null, storageItem.icon);
            if (TurboUtils.getExternalStorageDirectory().getPath().startsWith(storageItem.file.getPath())) {
                sharedDocumentCell.setChecked(true, false);
            } else {
                sharedDocumentCell.setChecked(false, false);
            }
        }

        public int getItemViewType(int pos) {
            return 0;
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }
    }

    private class StorageItem {
        File file;
        int icon;
        String subtitle;
        String title;

        private StorageItem() {
            this.subtitle = "";
        }
    }

    public void onFragmentDestroy() {
        try {
            if (this.receiverRegistered) {
                ApplicationLoader.applicationContext.unregisterReceiver(this.receiver);
            }
        } catch (Throwable e) {
            FileLog.e(e);
        }
        super.onFragmentDestroy();
    }

    public View createView(Context context) {
        if (!this.receiverRegistered) {
            this.receiverRegistered = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
            filter.addAction("android.intent.action.MEDIA_CHECKING");
            filter.addAction("android.intent.action.MEDIA_EJECT");
            filter.addAction("android.intent.action.MEDIA_MOUNTED");
            filter.addAction("android.intent.action.MEDIA_NOFS");
            filter.addAction("android.intent.action.MEDIA_REMOVED");
            filter.addAction("android.intent.action.MEDIA_SHARED");
            filter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
            filter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            filter.addDataScheme("file");
            ApplicationLoader.applicationContext.registerReceiver(this.receiver, filter);
        }
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("StorageDevice", R.string.StorageDevice));
        this.actionBar.setActionBarMenuOnItemClick(new C24372());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.emptyView = new EmptyTextProgressView(context);
        this.emptyView.setShowAtCenter(true);
        this.emptyView.showTextView();
        this.emptyView.setText(LocaleController.getString("NoResult", R.string.NoResult));
        frameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listAdapter = new ListAdapter();
        this.listView = new RecyclerListView(context);
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.listView.setEmptyView(this.emptyView);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24423());
        getStorageList();
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    private String getRootSubtitle(String path) {
        try {
            StatFs stat = new StatFs(path);
            long free = ((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize());
            if (((long) stat.getBlockCount()) * ((long) stat.getBlockSize()) == 0) {
                return "";
            }
            return LocaleController.formatString("FreeOfTotal", R.string.FreeOfTotal, new Object[]{AndroidUtilities.formatFileSize(free), AndroidUtilities.formatFileSize(((long) stat.getBlockCount()) * ((long) stat.getBlockSize()))});
        } catch (Throwable e) {
            FileLog.e(e);
            return path;
        }
    }

    private void getStorageList() {
        StorageItem storageItem;
        FileNotFoundException ex;
        Throwable th;
        String defaultPath = Environment.getExternalStorageDirectory().getPath();
        boolean defaultPath_removable = Environment.isExternalStorageRemovable();
        boolean defaultPath_available = Environment.getExternalStorageState().equals("mounted");
        HashSet<String> paths = new HashSet();
        if (defaultPath_available) {
            storageItem = new StorageItem();
            if (VERSION.SDK_INT < 9 || defaultPath_removable) {
                storageItem.title = LocaleController.getString("SdCard", R.string.SdCard);
                storageItem.icon = R.drawable.ic_external_storage;
            } else {
                storageItem.title = LocaleController.getString("InternalStorage", R.string.InternalStorage);
                storageItem.icon = R.drawable.ic_storage;
            }
            storageItem.subtitle = getRootSubtitle(defaultPath);
            storageItem.file = Environment.getExternalStorageDirectory();
            this.storageItems.add(storageItem);
            paths.add(defaultPath);
        }
        BufferedReader buf_reader = null;
        try {
            BufferedReader buf_reader2 = new BufferedReader(new FileReader("/proc/mounts"));
            while (true) {
                try {
                    String line = buf_reader2.readLine();
                    if (line == null) {
                        break;
                    } else if (line.contains("vfat") || line.contains("/mnt")) {
                        StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
                        String unused = stringTokenizer.nextToken();
                        String path = stringTokenizer.nextToken();
                        if (paths.contains(path)) {
                            continue;
                        } else {
                            unused = stringTokenizer.nextToken();
                            if (!(!line.contains("/dev/block/vold") || line.contains("/mnt/secure") || line.contains("/mnt/asec") || line.contains("/mnt/obb") || line.contains("/dev/mapper") || line.contains("tmpfs"))) {
                                if (!new File(path).isDirectory()) {
                                    int index = path.lastIndexOf(47);
                                    if (index != -1) {
                                        String newPath = "/storage/" + path.substring(index + 1);
                                        if (new File(newPath).isDirectory()) {
                                            path = newPath;
                                        }
                                    }
                                }
                                paths.add(path);
                                try {
                                    storageItem = new StorageItem();
                                    storageItem.title = LocaleController.getString("SdCard", R.string.SdCard);
                                    storageItem.icon = R.drawable.ic_external_storage;
                                    storageItem.subtitle = getRootSubtitle(path);
                                    storageItem.file = new File(path);
                                    this.storageItems.add(storageItem);
                                } catch (Exception e) {
                                    FileLog.e(e);
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e2) {
                    ex = e2;
                    buf_reader = buf_reader2;
                } catch (IOException e3) {
                    ex = e3;
                    buf_reader = buf_reader2;
                } catch (Throwable th2) {
                    th = th2;
                    buf_reader = buf_reader2;
                }
            }
            if (buf_reader2 != null) {
                try {
                    buf_reader2.close();
                    buf_reader = buf_reader2;
                    return;
                } catch (IOException e4) {
                    buf_reader = buf_reader2;
                    return;
                }
            }
        } catch (FileNotFoundException e5) {
            ex = e5;
            try {
                ThrowableExtension.printStackTrace(ex);
                if (buf_reader != null) {
                    try {
                        buf_reader.close();
                    } catch (IOException e6) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                if (buf_reader != null) {
                    try {
                        buf_reader.close();
                    } catch (IOException e7) {
                    }
                }
                throw th;
            }
        } catch (IOException e8) {
            ex = e8;
            ThrowableExtension.printStackTrace(ex);
            if (buf_reader != null) {
                try {
                    buf_reader.close();
                } catch (IOException e9) {
                }
            }
        }
    }
}
