package dmax.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

/* loaded from: classes.dex */
public class SpotsDialog extends AlertDialog {
    private static final int DELAY = 150;
    private static final int DURATION = 1500;
    private AnimatorPlayer animator;
    private CharSequence message;
    private int size;
    private AnimatedView[] spots;

    /* loaded from: classes.dex */
    public static class Builder {
        private DialogInterface.OnCancelListener cancelListener;
        private boolean cancelable = true;
        private Context context;
        private String message;
        private int messageId;
        private int themeId;

        public AlertDialog build() {
            Context context = this.context;
            int i = this.messageId;
            String string = i != 0 ? context.getString(i) : this.message;
            int i2 = this.themeId;
            if (i2 == 0) {
                i2 = R.style.SpotsDialogDefault;
            }
            return new SpotsDialog(context, string, i2, this.cancelable, this.cancelListener);
        }

        public Builder setCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.cancelListener = onCancelListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.cancelable = z;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setMessage(@StringRes int i) {
            this.messageId = i;
            return this;
        }

        public Builder setMessage(String str) {
            this.message = str;
            return this;
        }

        public Builder setTheme(@StyleRes int i) {
            this.themeId = i;
            return this;
        }
    }

    private SpotsDialog(Context context, String str, int i, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, i);
        this.message = str;
        setCancelable(z);
        if (onCancelListener != null) {
            setOnCancelListener(onCancelListener);
        }
    }

    private Animator[] createAnimations() {
        Animator[] animatorArr = new Animator[this.size];
        int i = 0;
        while (true) {
            AnimatedView[] animatedViewArr = this.spots;
            if (i >= animatedViewArr.length) {
                return animatorArr;
            }
            final AnimatedView animatedView = animatedViewArr[i];
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(animatedView, "xFactor", 0.0f, 1.0f);
            ofFloat.setDuration(1500L);
            ofFloat.setInterpolator(new HesitateInterpolator());
            ofFloat.setStartDelay(i * 150);
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: dmax.dialog.SpotsDialog.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    animatedView.setVisibility(4);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    animatedView.setVisibility(0);
                }
            });
            animatorArr[i] = ofFloat;
            i++;
        }
    }

    private void initMessage() {
        CharSequence charSequence = this.message;
        if (charSequence == null || charSequence.length() <= 0) {
            return;
        }
        ((TextView) findViewById(R.id.dmax_spots_title)).setText(this.message);
    }

    private void initProgress() {
        ProgressLayout progressLayout = (ProgressLayout) findViewById(R.id.dmax_spots_progress);
        this.size = progressLayout.getSpotsCount();
        this.spots = new AnimatedView[this.size];
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.spot_size);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.progress_width);
        for (int i = 0; i < this.spots.length; i++) {
            AnimatedView animatedView = new AnimatedView(getContext());
            animatedView.setBackgroundResource(R.drawable.dmax_spots_spot);
            animatedView.setTarget(dimensionPixelSize2);
            animatedView.setXFactor(-1.0f);
            animatedView.setVisibility(4);
            progressLayout.addView(animatedView, dimensionPixelSize, dimensionPixelSize);
            this.spots[i] = animatedView;
        }
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dmax_spots_dialog);
        setCanceledOnTouchOutside(false);
        initMessage();
        initProgress();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        for (AnimatedView animatedView : this.spots) {
            animatedView.setVisibility(0);
        }
        this.animator = new AnimatorPlayer(createAnimations());
        this.animator.play();
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.animator.stop();
    }

    @Override // android.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        this.message = charSequence;
        if (isShowing()) {
            initMessage();
        }
    }
}
