package com.google.android.gms.internal.config;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;

public final class zze {
    public static final Api<NoOptions> API = new Api("Config.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<zzw, NoOptions> CLIENT_BUILDER = new zzf();
    private static final ClientKey<zzw> CLIENT_KEY = new ClientKey();
    public static final zzg zze = new zzo();
}
