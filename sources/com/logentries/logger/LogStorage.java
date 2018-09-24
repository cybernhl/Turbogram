package com.logentries.logger;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class LogStorage {
    private static final long MAX_QUEUE_FILE_SIZE = 10485760;
    private static final String STORAGE_FILE_NAME = "LogentriesLogStorage.log";
    private static final String TAG = "LogentriesAndroidLogger";
    private Context context;
    private File storageFilePtr = null;

    public LogStorage(Context context) {
        this.context = context;
        this.storageFilePtr = create();
    }

    private File create() {
        return new File(this.context.getFilesDir(), STORAGE_FILE_NAME);
    }

    private long getCurrentStorageFileSize() {
        if (this.storageFilePtr == null) {
            this.storageFilePtr = create();
        }
        return this.storageFilePtr.length();
    }

    public Queue<String> getAllLogsFromStorage(boolean z) {
        Queue<String> arrayDeque = new ArrayDeque();
        FileInputStream fileInputStream = null;
        try {
            InputStream openFileInput = this.context.openFileInput(STORAGE_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DataInputStream(openFileInput)));
            for (Object readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                arrayDeque.offer(readLine);
            }
            if (z) {
                removeStorageFile();
            }
            if (openFileInput != null) {
                try {
                    openFileInput.close();
                } catch (IOException e) {
                    Log.e(TAG, "Cannot close the local storage file: " + e.getMessage());
                }
            }
        } catch (IOException e2) {
            Log.e(TAG, "Cannot load logs from the local storage: " + e2.getMessage());
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e22) {
                    Log.e(TAG, "Cannot close the local storage file: " + e22.getMessage());
                }
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    Log.e(TAG, "Cannot close the local storage file: " + e3.getMessage());
                }
            }
        }
        return arrayDeque;
    }

    public void putLogToStorage(String str) {
        if (!str.endsWith("\n")) {
            str = str + "\n";
        }
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bytes = str.getBytes();
            long currentStorageFileSize = getCurrentStorageFileSize() + ((long) bytes.length);
            Log.d(TAG, "Current size: " + Long.toString(currentStorageFileSize));
            if (currentStorageFileSize >= MAX_QUEUE_FILE_SIZE) {
                Log.d(TAG, "Log storage will be cleared because threshold of 10485760 bytes has been reached");
                reCreateStorageFile();
            }
            fileOutputStream = this.context.openFileOutput(STORAGE_FILE_NAME, 32768);
            fileOutputStream.write(bytes);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public void reCreateStorageFile() {
        Log.d(TAG, "Log storage has been re-created.");
        if (this.storageFilePtr == null) {
            this.storageFilePtr = create();
        } else {
            removeStorageFile();
        }
        this.storageFilePtr = create();
    }

    public void removeStorageFile() {
        if (!this.storageFilePtr.delete()) {
            throw new IOException("Cannot delete LogentriesLogStorage.log");
        }
    }
}
