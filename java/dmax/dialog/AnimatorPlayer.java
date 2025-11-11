package dmax.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

/* loaded from: classes.dex */
class AnimatorPlayer extends AnimatorListenerAdapter {
    private Animator[] animators;
    private boolean interrupted = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatorPlayer(Animator[] animatorArr) {
        this.animators = animatorArr;
    }

    private void animate() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(this.animators);
        animatorSet.addListener(this);
        animatorSet.start();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.interrupted) {
            return;
        }
        animate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void play() {
        animate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        this.interrupted = true;
    }
}
