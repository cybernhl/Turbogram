package org.telegram.ui.Components;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class CubicBezierInterpolator implements Interpolator {
    public static final CubicBezierInterpolator DEFAULT = new CubicBezierInterpolator(0.25d, 0.1d, 0.25d, 1.0d);
    public static final CubicBezierInterpolator EASE_BOTH = new CubicBezierInterpolator(0.42d, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 0.58d, 1.0d);
    public static final CubicBezierInterpolator EASE_IN = new CubicBezierInterpolator(0.42d, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, 1.0d);
    public static final CubicBezierInterpolator EASE_OUT = new CubicBezierInterpolator((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 0.58d, 1.0d);
    public static final CubicBezierInterpolator EASE_OUT_QUINT = new CubicBezierInterpolator(0.23d, 1.0d, 0.32d, 1.0d);
    /* renamed from: a */
    protected PointF f819a;
    /* renamed from: b */
    protected PointF f820b;
    /* renamed from: c */
    protected PointF f821c;
    protected PointF end;
    protected PointF start;

    public CubicBezierInterpolator(PointF start, PointF end) throws IllegalArgumentException {
        this.f819a = new PointF();
        this.f820b = new PointF();
        this.f821c = new PointF();
        if (start.x < 0.0f || start.x > 1.0f) {
            throw new IllegalArgumentException("startX value must be in the range [0, 1]");
        } else if (end.x < 0.0f || end.x > 1.0f) {
            throw new IllegalArgumentException("endX value must be in the range [0, 1]");
        } else {
            this.start = start;
            this.end = end;
        }
    }

    public CubicBezierInterpolator(float startX, float startY, float endX, float endY) {
        this(new PointF(startX, startY), new PointF(endX, endY));
    }

    public CubicBezierInterpolator(double startX, double startY, double endX, double endY) {
        this((float) startX, (float) startY, (float) endX, (float) endY);
    }

    public float getInterpolation(float time) {
        return getBezierCoordinateY(getXForTime(time));
    }

    protected float getBezierCoordinateY(float time) {
        this.f821c.y = this.start.y * 3.0f;
        this.f820b.y = ((this.end.y - this.start.y) * 3.0f) - this.f821c.y;
        this.f819a.y = (1.0f - this.f821c.y) - this.f820b.y;
        return (this.f821c.y + ((this.f820b.y + (this.f819a.y * time)) * time)) * time;
    }

    protected float getXForTime(float time) {
        float x = time;
        for (int i = 1; i < 14; i++) {
            float z = getBezierCoordinateX(x) - time;
            if (((double) Math.abs(z)) < 0.001d) {
                break;
            }
            x -= z / getXDerivate(x);
        }
        return x;
    }

    private float getXDerivate(float t) {
        return this.f821c.x + (((2.0f * this.f820b.x) + ((3.0f * this.f819a.x) * t)) * t);
    }

    private float getBezierCoordinateX(float time) {
        this.f821c.x = this.start.x * 3.0f;
        this.f820b.x = ((this.end.x - this.start.x) * 3.0f) - this.f821c.x;
        this.f819a.x = (1.0f - this.f821c.x) - this.f820b.x;
        return (this.f821c.x + ((this.f820b.x + (this.f819a.x * time)) * time)) * time;
    }
}
