package com.google.android.gms.internal.config;

import android.content.Context;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;

public final class zzv extends GoogleApi<NoOptions> {
    public zzv(Context context) {
        super(context, zze.API, null, new ApiExceptionMapper());
    }
}
