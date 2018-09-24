package me.cheshmak.android.sdk.core.p022l;

import android.os.AsyncTask;

/* renamed from: me.cheshmak.android.sdk.core.l.b */
public class C0517b {
    /* renamed from: a */
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> m893a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        if (asyncTask == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramsArr);
        return asyncTask;
    }
}
