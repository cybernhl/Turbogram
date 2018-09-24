package org.telegram.ui.Components.Paint;

import android.graphics.PointF;

public class Point {
    public boolean edge;
    /* renamed from: x */
    public double f824x;
    /* renamed from: y */
    public double f825y;
    /* renamed from: z */
    public double f826z;

    public Point(double x, double y, double z) {
        this.f824x = x;
        this.f825y = y;
        this.f826z = z;
    }

    public Point(Point point) {
        this.f824x = point.f824x;
        this.f825y = point.f825y;
        this.f826z = point.f826z;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        if (!(this.f824x == other.f824x && this.f825y == other.f825y && this.f826z == other.f826z)) {
            z = false;
        }
        return z;
    }

    Point multiplySum(Point point, double scalar) {
        return new Point((this.f824x + point.f824x) * scalar, (this.f825y + point.f825y) * scalar, (this.f826z + point.f826z) * scalar);
    }

    Point multiplyAndAdd(double scalar, Point point) {
        return new Point((this.f824x * scalar) + point.f824x, (this.f825y * scalar) + point.f825y, (this.f826z * scalar) + point.f826z);
    }

    void alteringAddMultiplication(Point point, double scalar) {
        this.f824x += point.f824x * scalar;
        this.f825y += point.f825y * scalar;
        this.f826z += point.f826z * scalar;
    }

    Point add(Point point) {
        return new Point(this.f824x + point.f824x, this.f825y + point.f825y, this.f826z + point.f826z);
    }

    Point substract(Point point) {
        return new Point(this.f824x - point.f824x, this.f825y - point.f825y, this.f826z - point.f826z);
    }

    Point multiplyByScalar(double scalar) {
        return new Point(this.f824x * scalar, this.f825y * scalar, this.f826z * scalar);
    }

    Point getNormalized() {
        return multiplyByScalar(1.0d / getMagnitude());
    }

    private double getMagnitude() {
        return Math.sqrt(((this.f824x * this.f824x) + (this.f825y * this.f825y)) + (this.f826z * this.f826z));
    }

    float getDistanceTo(Point point) {
        return (float) Math.sqrt((Math.pow(this.f824x - point.f824x, 2.0d) + Math.pow(this.f825y - point.f825y, 2.0d)) + Math.pow(this.f826z - point.f826z, 2.0d));
    }

    PointF toPointF() {
        return new PointF((float) this.f824x, (float) this.f825y);
    }
}
