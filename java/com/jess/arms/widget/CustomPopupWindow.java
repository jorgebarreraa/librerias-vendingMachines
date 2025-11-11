package com.jess.arms.widget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
public class CustomPopupWindow extends PopupWindow {
    private boolean isFocus;
    private boolean isOutsideTouch;
    private boolean isWrap;
    private int mAnimationStyle;
    private Drawable mBackgroundDrawable;
    private View mContentView;
    private CustomPopupWindowListener mListener;
    private View mParentView;

    /* loaded from: classes.dex */
    public static final class Builder {
        private int animationStyle;
        private Drawable backgroundDrawable;
        private View contentView;
        private boolean isFocus;
        private boolean isOutsideTouch;
        private boolean isWrap;
        private CustomPopupWindowListener listener;
        private View parentView;

        private Builder() {
            this.isOutsideTouch = true;
            this.isFocus = true;
            this.backgroundDrawable = new ColorDrawable(0);
            this.animationStyle = -1;
        }

        public Builder animationStyle(int i) {
            this.animationStyle = i;
            return this;
        }

        public Builder backgroundDrawable(Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        public CustomPopupWindow build() {
            if (this.contentView == null) {
                throw new IllegalStateException("ContentView is required");
            }
            if (this.listener != null) {
                return new CustomPopupWindow(this);
            }
            throw new IllegalStateException("CustomPopupWindowListener is required");
        }

        public Builder contentView(View view) {
            this.contentView = view;
            return this;
        }

        public Builder customListener(CustomPopupWindowListener customPopupWindowListener) {
            this.listener = customPopupWindowListener;
            return this;
        }

        public Builder isFocus(boolean z) {
            this.isFocus = z;
            return this;
        }

        public Builder isOutsideTouch(boolean z) {
            this.isOutsideTouch = z;
            return this;
        }

        public Builder isWrap(boolean z) {
            this.isWrap = z;
            return this;
        }

        public Builder parentView(View view) {
            this.parentView = view;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface CustomPopupWindowListener {
        void initPopupView(View view);
    }

    private CustomPopupWindow(Builder builder) {
        this.mContentView = builder.contentView;
        this.mParentView = builder.parentView;
        this.mListener = builder.listener;
        this.isOutsideTouch = builder.isOutsideTouch;
        this.isFocus = builder.isFocus;
        this.mBackgroundDrawable = builder.backgroundDrawable;
        this.mAnimationStyle = builder.animationStyle;
        this.isWrap = builder.isWrap;
        initLayout();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static View inflateView(ContextThemeWrapper contextThemeWrapper, int i) {
        return LayoutInflater.from(contextThemeWrapper).inflate(i, (ViewGroup) null);
    }

    private void initLayout() {
        this.mListener.initPopupView(this.mContentView);
        setWidth(this.isWrap ? -2 : -1);
        setHeight(this.isWrap ? -2 : -1);
        setFocusable(this.isFocus);
        setOutsideTouchable(this.isOutsideTouch);
        setBackgroundDrawable(this.mBackgroundDrawable);
        int i = this.mAnimationStyle;
        if (i != -1) {
            setAnimationStyle(i);
        }
        setContentView(this.mContentView);
    }

    @Override // android.widget.PopupWindow
    public View getContentView() {
        return this.mContentView;
    }

    public void show() {
        View view = this.mParentView;
        if (view == null) {
            showAtLocation(this.mContentView, 17, 0, 0);
        } else {
            showAtLocation(view, 17, 0, 0);
        }
    }
}
