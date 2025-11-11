package com.yj.coffeemachines.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.yj.coffeemachines.R;
import java.io.InputStream;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class GifImageView extends AppCompatImageView implements View.OnClickListener {
    private boolean isAutoPlay;
    private boolean isPlaying;
    private int mImageHeight;
    private int mImageWidth;
    private Movie mMovie;
    private long mMovieStart;
    private Bitmap mStartPlay;

    public GifImageView(Context context) {
        super(context);
        this.mMovieStart = 0L;
        this.isPlaying = false;
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMovieStart = 0L;
        this.isPlaying = false;
        init(context, attributeSet);
    }

    private int getResourceId(TypedArray typedArray, Context context, AttributeSet attributeSet) {
        try {
            try {
                Field declaredField = TypedArray.class.getDeclaredField("mValue");
                declaredField.setAccessible(true);
                int i = ((TypedValue) declaredField.get(typedArray)).resourceId;
                if (typedArray != null) {
                    typedArray.recycle();
                }
                return i;
            } catch (Exception e) {
                e.printStackTrace();
                if (typedArray == null) {
                    return 0;
                }
                typedArray.recycle();
                return 0;
            }
        } catch (Throwable th) {
            if (typedArray != null) {
                typedArray.recycle();
            }
            throw th;
        }
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GifImageView);
        int resourceId = getResourceId(obtainStyledAttributes, context, attributeSet);
        if (resourceId != 0) {
            InputStream openRawResource = getResources().openRawResource(resourceId);
            this.mMovie = Movie.decodeStream(openRawResource);
            if (this.mMovie != null) {
                Bitmap decodeStream = BitmapFactory.decodeStream(openRawResource);
                this.mImageWidth = decodeStream.getWidth();
                this.mImageHeight = decodeStream.getHeight();
                decodeStream.recycle();
                this.isAutoPlay = obtainStyledAttributes.getBoolean(0, false);
                boolean z = this.isAutoPlay;
            }
        }
    }

    private boolean playMovie(Canvas canvas) {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.mMovieStart == 0) {
            this.mMovieStart = uptimeMillis;
        }
        int duration = this.mMovie.duration();
        if (duration == 0) {
            duration = 1000;
        }
        long j = duration;
        this.mMovie.setTime((int) ((uptimeMillis - this.mMovieStart) % j));
        this.mMovie.draw(canvas, 0.0f, 0.0f);
        if (uptimeMillis - this.mMovieStart < j) {
            return false;
        }
        this.mMovieStart = 0L;
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == getId()) {
            this.isPlaying = true;
            invalidate();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Movie movie = this.mMovie;
        if (movie == null) {
            super.onDraw(canvas);
            return;
        }
        if (this.isAutoPlay) {
            playMovie(canvas);
            invalidate();
        } else if (this.isPlaying) {
            if (playMovie(canvas)) {
                this.isPlaying = false;
            }
            invalidate();
        } else {
            movie.setTime(0);
            this.mMovie.draw(canvas, 0.0f, 0.0f);
            canvas.drawBitmap(this.mStartPlay, (this.mImageWidth - this.mStartPlay.getWidth()) / 2, (this.mImageHeight - this.mStartPlay.getHeight()) / 2, (Paint) null);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mMovie != null) {
            setMeasuredDimension(this.mImageWidth, this.mImageHeight);
        }
    }
}
