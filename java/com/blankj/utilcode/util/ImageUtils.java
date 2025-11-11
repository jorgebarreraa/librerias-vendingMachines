package com.blankj.utilcode.util;

import android.app.kingsun.rk3288;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.os.EnvironmentCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class ImageUtils {

    /* loaded from: classes.dex */
    public enum ImageType {
        TYPE_JPG("jpg"),
        TYPE_PNG("png"),
        TYPE_GIF("gif"),
        TYPE_TIFF("tiff"),
        TYPE_BMP("bmp"),
        TYPE_WEBP("webp"),
        TYPE_ICO("ico"),
        TYPE_UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

        String value;

        ImageType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    private ImageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Bitmap addBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, boolean z, float f2, boolean z2) {
        return addBorder(bitmap, f, i, z, new float[]{f2, f2, f2, f2, f2, f2, f2, f2}, z2);
    }

    private static Bitmap addBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, boolean z, float[] fArr, boolean z2) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (!z2) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(1);
        paint.setColor(i);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(f);
        if (z) {
            canvas.drawCircle(width / 2.0f, height / 2.0f, (Math.min(width, height) / 2.0f) - (f / 2.0f), paint);
        } else {
            RectF rectF = new RectF(0.0f, 0.0f, width, height);
            float f2 = f / 2.0f;
            rectF.inset(f2, f2);
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
        return bitmap;
    }

    public static Bitmap addCircleBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i) {
        return addBorder(bitmap, f, i, true, 0.0f, false);
    }

    public static Bitmap addCircleBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, boolean z) {
        return addBorder(bitmap, f, i, true, 0.0f, z);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, @FloatRange(from = 0.0d) float f2) {
        return addBorder(bitmap, f, i, false, f2, false);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, @FloatRange(from = 0.0d) float f2, boolean z) {
        return addBorder(bitmap, f, i, false, f2, z);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, float[] fArr) {
        return addBorder(bitmap, f, i, false, fArr, false);
    }

    public static Bitmap addCornerBorder(Bitmap bitmap, @FloatRange(from = 1.0d) float f, @ColorInt int i, float[] fArr, boolean z) {
        return addBorder(bitmap, f, i, false, fArr, z);
    }

    public static Bitmap addImageWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3) {
        return addImageWatermark(bitmap, bitmap2, i, i2, i3, false);
    }

    public static Bitmap addImageWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (!isEmptyBitmap(bitmap2)) {
            Paint paint = new Paint(1);
            Canvas canvas = new Canvas(copy);
            paint.setAlpha(i3);
            canvas.drawBitmap(bitmap2, i, i2, paint);
        }
        if (z && !bitmap.isRecycled() && copy != bitmap) {
            bitmap.recycle();
        }
        return copy;
    }

    public static Bitmap addReflection(Bitmap bitmap, int i) {
        return addReflection(bitmap, i, false);
    }

    public static Bitmap addReflection(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, height - i, width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height + i, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        float f = height + 0;
        canvas.drawBitmap(createBitmap, 0.0f, f, (Paint) null);
        Paint paint = new Paint(1);
        paint.setShader(new LinearGradient(0.0f, height, 0.0f, createBitmap2.getHeight() + 0, 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, width, createBitmap2.getHeight(), paint);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        if (z && !bitmap.isRecycled() && createBitmap2 != bitmap) {
            bitmap.recycle();
        }
        return createBitmap2;
    }

    public static Bitmap addTextWatermark(Bitmap bitmap, String str, float f, @ColorInt int i, float f2, float f3, boolean z) {
        if (isEmptyBitmap(bitmap) || str == null) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        Paint paint = new Paint(1);
        Canvas canvas = new Canvas(copy);
        paint.setColor(i);
        paint.setTextSize(f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, f2, f3 + f, paint);
        if (z && !bitmap.isRecycled() && copy != bitmap) {
            bitmap.recycle();
        }
        return copy;
    }

    public static Bitmap addTextWatermark(Bitmap bitmap, String str, int i, @ColorInt int i2, float f, float f2) {
        return addTextWatermark(bitmap, str, i, i2, f, f2, false);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        return bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG, 100);
    }

    public static byte[] bitmap2Bytes(@Nullable Bitmap bitmap, @NonNull Bitmap.CompressFormat compressFormat, int i) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Drawable bitmap2Drawable(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.getApp().getResources(), bitmap);
    }

    public static Bitmap bytes2Bitmap(@Nullable byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Drawable bytes2Drawable(byte[] bArr) {
        return bitmap2Drawable(bytes2Bitmap(bArr));
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        while (true) {
            if (i3 <= i2 && i4 <= i) {
                return i5;
            }
            i3 >>= 1;
            i4 >>= 1;
            i5 <<= 1;
        }
    }

    public static Bitmap clip(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return clip(bitmap, i, i2, i3, i4, false);
    }

    public static Bitmap clip(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static byte[] compressByQuality(Bitmap bitmap, @IntRange(from = 0, to = 100) int i) {
        return compressByQuality(bitmap, i, false);
    }

    public static byte[] compressByQuality(Bitmap bitmap, @IntRange(from = 0, to = 100) int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return byteArray;
    }

    public static byte[] compressByQuality(Bitmap bitmap, long j) {
        return compressByQuality(bitmap, j, false);
    }

    public static byte[] compressByQuality(Bitmap bitmap, long j, boolean z) {
        byte[] byteArray;
        int i = 0;
        if (isEmptyBitmap(bitmap) || j <= 0) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.size() <= j) {
            byteArray = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (byteArrayOutputStream.size() >= j) {
                byteArray = byteArrayOutputStream.toByteArray();
            } else {
                int i3 = 0;
                while (i < i2) {
                    i3 = (i + i2) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                    long size = byteArrayOutputStream.size();
                    if (size == j) {
                        break;
                    }
                    if (size > j) {
                        i2 = i3 - 1;
                    } else {
                        i = i3 + 1;
                    }
                }
                if (i2 == i3 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                }
                byteArray = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return byteArray;
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i) {
        return compressBySampleSize(bitmap, i, false);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, int i2) {
        return compressBySampleSize(bitmap, i, i2, false);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static Bitmap compressBySampleSize(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static Bitmap compressByScale(Bitmap bitmap, float f, float f2) {
        return scale(bitmap, f, f2, false);
    }

    public static Bitmap compressByScale(Bitmap bitmap, float f, float f2, boolean z) {
        return scale(bitmap, f, f2, z);
    }

    public static Bitmap compressByScale(Bitmap bitmap, int i, int i2) {
        return scale(bitmap, i, i2, false);
    }

    public static Bitmap compressByScale(Bitmap bitmap, int i, int i2, boolean z) {
        return scale(bitmap, i, i2, z);
    }

    public static Bitmap drawColor(@NonNull Bitmap bitmap, @ColorInt int i) {
        return drawColor(bitmap, i, false);
    }

    public static Bitmap drawColor(@NonNull Bitmap bitmap, @ColorInt int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (!z) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        new Canvas(bitmap).drawColor(i, PorterDuff.Mode.DARKEN);
        return bitmap;
    }

    public static Bitmap drawable2Bitmap(@Nullable Drawable drawable) {
        Bitmap createBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            createBitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static byte[] drawable2Bytes(@Nullable Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable));
    }

    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat compressFormat, int i) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable), compressFormat, i);
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2) {
        return fastBlur(bitmap, f, f2, false, false);
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2, boolean z) {
        return fastBlur(bitmap, f, f2, z, false);
    }

    public static Bitmap fastBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2, boolean z, boolean z2) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Paint paint = new Paint(3);
        Canvas canvas = new Canvas();
        paint.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
        canvas.scale(f, f);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        Bitmap renderScriptBlur = Build.VERSION.SDK_INT >= 17 ? renderScriptBlur(createBitmap, f2, z) : stackBlur(createBitmap, (int) f2, z);
        if (f == 1.0f || z2) {
            if (z && !bitmap.isRecycled() && renderScriptBlur != bitmap) {
                bitmap.recycle();
            }
            return renderScriptBlur;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(renderScriptBlur, width, height, true);
        if (!renderScriptBlur.isRecycled()) {
            renderScriptBlur.recycle();
        }
        if (z && !bitmap.isRecycled() && createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public static Bitmap getBitmap(@DrawableRes int i) {
        Drawable drawable = ContextCompat.getDrawable(Utils.getApp(), i);
        if (drawable == null) {
            return null;
        }
        Canvas canvas = new Canvas();
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getBitmap(@DrawableRes int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Resources resources = Utils.getApp().getResources();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap getBitmap(File file) {
        if (file == null) {
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static Bitmap getBitmap(File file, int i, int i2) {
        if (file == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap getBitmap(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            return null;
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    public static Bitmap getBitmap(FileDescriptor fileDescriptor, int i, int i2) {
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public static Bitmap getBitmap(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(inputStream);
    }

    public static Bitmap getBitmap(InputStream inputStream, int i, int i2) {
        if (inputStream == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap getBitmap(String str) {
        if (UtilsBridge.isSpace(str)) {
            return null;
        }
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap getBitmap(String str, int i, int i2) {
        if (UtilsBridge.isSpace(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getBitmap(byte[] bArr, int i) {
        if (bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length);
    }

    public static Bitmap getBitmap(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length == 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x002c -> B:12:0x002f). Please report as a decompilation issue!!! */
    public static ImageType getImageType(File file) {
        FileInputStream fileInputStream;
        ImageType imageType;
        FileInputStream fileInputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                imageType = getImageType(fileInputStream);
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        if (imageType == null) {
            fileInputStream.close();
            return null;
        }
        try {
            fileInputStream.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return imageType;
    }

    private static ImageType getImageType(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[12];
            if (inputStream.read(bArr) != -1) {
                return getImageType(bArr);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageType getImageType(String str) {
        return getImageType(UtilsBridge.getFileByPath(str));
    }

    private static ImageType getImageType(byte[] bArr) {
        String upperCase = UtilsBridge.bytes2HexString(bArr).toUpperCase();
        return upperCase.contains("FFD8FF") ? ImageType.TYPE_JPG : upperCase.contains("89504E47") ? ImageType.TYPE_PNG : upperCase.contains("47494638") ? ImageType.TYPE_GIF : (upperCase.contains("49492A00") || upperCase.contains("4D4D002A")) ? ImageType.TYPE_TIFF : upperCase.contains("424D") ? ImageType.TYPE_BMP : (upperCase.startsWith("52494646") && upperCase.endsWith("57454250")) ? ImageType.TYPE_WEBP : (upperCase.contains("00000100") || upperCase.contains("00000200")) ? ImageType.TYPE_ICO : ImageType.TYPE_UNKNOWN;
    }

    public static int getRotateDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return rk3288.RK30_PIN8_PB6;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int[] getSize(File file) {
        if (file == null) {
            return new int[]{0, 0};
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static int[] getSize(String str) {
        return getSize(UtilsBridge.getFileByPath(str));
    }

    private static boolean isBMP(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == 66 && bArr[1] == 77;
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    private static boolean isGIF(byte[] bArr) {
        return bArr.length >= 6 && bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 56 && (bArr[4] == 55 || bArr[4] == 57) && bArr[5] == 97;
    }

    public static boolean isImage(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return isImage(file.getPath());
    }

    public static boolean isImage(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            if (options.outWidth > 0) {
                return options.outHeight > 0;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean isJPEG(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40;
    }

    private static boolean isPNG(byte[] bArr) {
        return bArr.length >= 8 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71 && bArr[4] == 13 && bArr[5] == 10 && bArr[6] == 26 && bArr[7] == 10;
    }

    @RequiresApi(17)
    public static Bitmap renderScriptBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f) {
        return renderScriptBlur(bitmap, f, false);
    }

    @RequiresApi(17)
    public static Bitmap renderScriptBlur(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f, boolean z) {
        if (!z) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        RenderScript renderScript = null;
        try {
            renderScript = RenderScript.create(Utils.getApp());
            renderScript.setMessageHandler(new RenderScript.RSMessageHandler());
            Allocation createFromBitmap = Allocation.createFromBitmap(renderScript, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
            Allocation createTyped = Allocation.createTyped(renderScript, createFromBitmap.getType());
            ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            create.setInput(createFromBitmap);
            create.setRadius(f);
            create.forEach(createTyped);
            createTyped.copyTo(bitmap);
            return bitmap;
        } finally {
            if (renderScript != null) {
                renderScript.destroy();
            }
        }
    }

    public static Bitmap rotate(Bitmap bitmap, int i, float f, float f2) {
        return rotate(bitmap, i, f, f2, false);
    }

    public static Bitmap rotate(Bitmap bitmap, int i, float f, float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i, f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat) {
        return save(bitmap, file, compressFormat, 100, false);
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int i) {
        return save(bitmap, file, compressFormat, i, false);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x005a -> B:27:0x006f). Please report as a decompilation issue!!! */
    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int i, boolean z) {
        BufferedOutputStream bufferedOutputStream;
        boolean z2 = false;
        if (isEmptyBitmap(bitmap)) {
            Log.e("ImageUtils", "bitmap is empty.");
            return false;
        }
        if (bitmap.isRecycled()) {
            Log.e("ImageUtils", "bitmap is recycled.");
            return false;
        }
        if (!UtilsBridge.createFileByDeleteOldFile(file)) {
            Log.e("ImageUtils", "create or delete file <" + file + "> failed.");
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            z2 = bitmap.compress(compressFormat, i, bufferedOutputStream);
            if (z && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            bufferedOutputStream.close();
        } catch (IOException e3) {
            e = e3;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            return z2;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return z2;
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, boolean z) {
        return save(bitmap, file, compressFormat, 100, z);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return save(bitmap, str, compressFormat, 100, false);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i) {
        return save(bitmap, UtilsBridge.getFileByPath(str), compressFormat, i, false);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i, boolean z) {
        return save(bitmap, UtilsBridge.getFileByPath(str), compressFormat, i, z);
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, boolean z) {
        return save(bitmap, str, compressFormat, 100, z);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        return save2Album(bitmap, "", compressFormat, 100, false);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        return save2Album(bitmap, "", compressFormat, i, false);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, boolean z) {
        return save2Album(bitmap, "", compressFormat, i, z);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z) {
        return save2Album(bitmap, "", compressFormat, 100, z);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return save2Album(bitmap, str, compressFormat, 100, false);
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, int i) {
        return save2Album(bitmap, str, compressFormat, i, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File save2Album(android.graphics.Bitmap r6, java.lang.String r7, android.graphics.Bitmap.CompressFormat r8, int r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ImageUtils.save2Album(android.graphics.Bitmap, java.lang.String, android.graphics.Bitmap$CompressFormat, int, boolean):java.io.File");
    }

    @Nullable
    public static File save2Album(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, boolean z) {
        return save2Album(bitmap, str, compressFormat, 100, z);
    }

    public static Bitmap scale(Bitmap bitmap, float f, float f2) {
        return scale(bitmap, f, f2, false);
    }

    public static Bitmap scale(Bitmap bitmap, float f, float f2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        return scale(bitmap, i, i2, false);
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        if (z && !bitmap.isRecycled() && createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2) {
        return skew(bitmap, f, f2, 0.0f, 0.0f, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, float f3, float f4) {
        return skew(bitmap, f, f2, f3, f4, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, float f3, float f4, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setSkew(f, f2, f3, f4);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap skew(Bitmap bitmap, float f, float f2, boolean z) {
        return skew(bitmap, f, f2, 0.0f, 0.0f, z);
    }

    public static Bitmap stackBlur(Bitmap bitmap, int i) {
        return stackBlur(bitmap, i, false);
    }

    public static Bitmap stackBlur(Bitmap bitmap, int i, boolean z) {
        int i2;
        int[] iArr;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        int i3 = i < 1 ? 1 : i;
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i4 = width * height;
        int[] iArr2 = new int[i4];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = i3 + i3 + 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[Math.max(width, height)];
        int i8 = (i7 + 1) >> 1;
        int i9 = i8 * i8;
        int i10 = i9 * 256;
        int[] iArr7 = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr7[i11] = i11 / i9;
        }
        int[][] iArr8 = (int[][]) Array.newInstance((Class<?>) int.class, i7, 3);
        int i12 = i3 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            Bitmap bitmap2 = copy;
            int i16 = -i3;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            while (i16 <= i3) {
                int i26 = i6;
                int i27 = height;
                int i28 = iArr2[i14 + Math.min(i5, Math.max(i16, 0))];
                int[] iArr9 = iArr8[i16 + i3];
                iArr9[0] = (i28 & 16711680) >> 16;
                iArr9[1] = (i28 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i28 & 255;
                int abs = i12 - Math.abs(i16);
                i17 += iArr9[0] * abs;
                i18 += iArr9[1] * abs;
                i19 += iArr9[2] * abs;
                if (i16 > 0) {
                    i23 += iArr9[0];
                    i24 += iArr9[1];
                    i25 += iArr9[2];
                } else {
                    i20 += iArr9[0];
                    i21 += iArr9[1];
                    i22 += iArr9[2];
                }
                i16++;
                height = i27;
                i6 = i26;
            }
            int i29 = i6;
            int i30 = height;
            int i31 = i3;
            int i32 = 0;
            while (i32 < width) {
                iArr3[i14] = iArr7[i17];
                iArr4[i14] = iArr7[i18];
                iArr5[i14] = iArr7[i19];
                int i33 = i17 - i20;
                int i34 = i18 - i21;
                int i35 = i19 - i22;
                int[] iArr10 = iArr8[((i31 - i3) + i7) % i7];
                int i36 = i20 - iArr10[0];
                int i37 = i21 - iArr10[1];
                int i38 = i22 - iArr10[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr6[i32] = Math.min(i32 + i3 + 1, i5);
                } else {
                    iArr = iArr7;
                }
                int i39 = iArr2[i15 + iArr6[i32]];
                iArr10[0] = (i39 & 16711680) >> 16;
                iArr10[1] = (i39 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i39 & 255;
                int i40 = i23 + iArr10[0];
                int i41 = i24 + iArr10[1];
                int i42 = i25 + iArr10[2];
                i17 = i33 + i40;
                i18 = i34 + i41;
                i19 = i35 + i42;
                i31 = (i31 + 1) % i7;
                int[] iArr11 = iArr8[i31 % i7];
                i20 = i36 + iArr11[0];
                i21 = i37 + iArr11[1];
                i22 = i38 + iArr11[2];
                i23 = i40 - iArr11[0];
                i24 = i41 - iArr11[1];
                i25 = i42 - iArr11[2];
                i14++;
                i32++;
                iArr7 = iArr;
            }
            i15 += width;
            i13++;
            height = i30;
            copy = bitmap2;
            i6 = i29;
        }
        int i43 = i6;
        Bitmap bitmap3 = copy;
        int i44 = height;
        int[] iArr12 = iArr7;
        int i45 = 0;
        while (i45 < width) {
            int i46 = -i3;
            int i47 = i46 * width;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = 0;
            while (i46 <= i3) {
                int[] iArr13 = iArr6;
                int max = Math.max(0, i47) + i45;
                int[] iArr14 = iArr8[i46 + i3];
                iArr14[0] = iArr3[max];
                iArr14[1] = iArr4[max];
                iArr14[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i46);
                i48 += iArr3[max] * abs2;
                i49 += iArr4[max] * abs2;
                i50 += iArr5[max] * abs2;
                if (i46 > 0) {
                    i54 += iArr14[0];
                    i55 += iArr14[1];
                    i56 += iArr14[2];
                } else {
                    i51 += iArr14[0];
                    i52 += iArr14[1];
                    i53 += iArr14[2];
                }
                int i57 = i43;
                if (i46 < i57) {
                    i47 += width;
                }
                i46++;
                i43 = i57;
                iArr6 = iArr13;
            }
            int[] iArr15 = iArr6;
            int i58 = i43;
            int i59 = i44;
            int i60 = i55;
            int i61 = i56;
            int i62 = 0;
            int i63 = i3;
            int i64 = i54;
            int i65 = i53;
            int i66 = i52;
            int i67 = i51;
            int i68 = i50;
            int i69 = i49;
            int i70 = i48;
            int i71 = i45;
            while (i62 < i59) {
                iArr2[i71] = (iArr2[i71] & ViewCompat.MEASURED_STATE_MASK) | (iArr12[i70] << 16) | (iArr12[i69] << 8) | iArr12[i68];
                int i72 = i70 - i67;
                int i73 = i69 - i66;
                int i74 = i68 - i65;
                int[] iArr16 = iArr8[((i63 - i3) + i7) % i7];
                int i75 = i67 - iArr16[0];
                int i76 = i66 - iArr16[1];
                int i77 = i65 - iArr16[2];
                if (i45 == 0) {
                    i2 = i3;
                    iArr15[i62] = Math.min(i62 + i12, i58) * width;
                } else {
                    i2 = i3;
                }
                int i78 = iArr15[i62] + i45;
                iArr16[0] = iArr3[i78];
                iArr16[1] = iArr4[i78];
                iArr16[2] = iArr5[i78];
                int i79 = i64 + iArr16[0];
                int i80 = i60 + iArr16[1];
                int i81 = i61 + iArr16[2];
                i70 = i72 + i79;
                i69 = i73 + i80;
                i68 = i74 + i81;
                i63 = (i63 + 1) % i7;
                int[] iArr17 = iArr8[i63];
                i67 = i75 + iArr17[0];
                i66 = i76 + iArr17[1];
                i65 = i77 + iArr17[2];
                i64 = i79 - iArr17[0];
                i60 = i80 - iArr17[1];
                i61 = i81 - iArr17[2];
                i71 += width;
                i62++;
                i3 = i2;
            }
            i45++;
            i43 = i58;
            i44 = i59;
            iArr6 = iArr15;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i44);
        return bitmap3;
    }

    public static Bitmap toAlpha(Bitmap bitmap) {
        return toAlpha(bitmap, false);
    }

    public static Bitmap toAlpha(Bitmap bitmap, Boolean bool) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap extractAlpha = bitmap.extractAlpha();
        if (bool.booleanValue() && !bitmap.isRecycled() && extractAlpha != bitmap) {
            bitmap.recycle();
        }
        return extractAlpha;
    }

    public static Bitmap toGray(Bitmap bitmap) {
        return toGray(bitmap, false);
    }

    public static Bitmap toGray(Bitmap bitmap, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap toRound(Bitmap bitmap) {
        return toRound(bitmap, 0, 0, false);
    }

    public static Bitmap toRound(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2) {
        return toRound(bitmap, i, i2, false);
    }

    public static Bitmap toRound(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        float f = min;
        float f2 = f / 2.0f;
        float f3 = width;
        float f4 = height;
        RectF rectF = new RectF(0.0f, 0.0f, f3, f4);
        rectF.inset((width - min) / 2.0f, (height - min) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(rectF.left, rectF.top);
        if (width != height) {
            matrix.preScale(f / f3, f / f4);
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (i > 0) {
            paint.setShader(null);
            paint.setColor(i2);
            paint.setStyle(Paint.Style.STROKE);
            float f5 = i;
            paint.setStrokeWidth(f5);
            canvas.drawCircle(f3 / 2.0f, f4 / 2.0f, f2 - (f5 / 2.0f), paint);
        }
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap toRound(Bitmap bitmap, boolean z) {
        return toRound(bitmap, 0, 0, z);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f) {
        return toRoundCorner(bitmap, f, 0.0f, 0, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, @FloatRange(from = 0.0d) float f2, @ColorInt int i) {
        return toRoundCorner(bitmap, f, f2, i, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, @FloatRange(from = 0.0d) float f2, @ColorInt int i, boolean z) {
        return toRoundCorner(bitmap, new float[]{f, f, f, f, f, f, f, f}, f2, i, z);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float f, boolean z) {
        return toRoundCorner(bitmap, f, 0.0f, 0, z);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float[] fArr, @FloatRange(from = 0.0d) float f, @ColorInt int i) {
        return toRoundCorner(bitmap, fArr, f, i, false);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float[] fArr, @FloatRange(from = 0.0d) float f, @ColorInt int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        float f2 = f / 2.0f;
        rectF.inset(f2, f2);
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        if (f > 0.0f) {
            paint.setShader(null);
            paint.setColor(i);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawPath(path, paint);
        }
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap view2Bitmap(View view) {
        Bitmap createBitmap;
        if (view == null) {
            return null;
        }
        boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setDrawingCacheEnabled(true);
        view.setWillNotCacheDrawing(false);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null || drawingCache.isRecycled()) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache();
            Bitmap drawingCache2 = view.getDrawingCache();
            if (drawingCache2 == null || drawingCache2.isRecycled()) {
                createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.RGB_565);
                view.draw(new Canvas(createBitmap));
            } else {
                createBitmap = Bitmap.createBitmap(drawingCache2);
            }
        } else {
            createBitmap = Bitmap.createBitmap(drawingCache);
        }
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        return createBitmap;
    }
}
