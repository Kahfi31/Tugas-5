package com.example.myapplication;
import android.view.animation.Interpolator;

public class DecelerateAccelerateInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return (float) (Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
