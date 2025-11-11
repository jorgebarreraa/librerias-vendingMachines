package com.innohi;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class StorageInfo implements Parcelable {
    public static final Parcelable.Creator<StorageInfo> CREATOR = new Parcelable.Creator<StorageInfo>() { // from class: com.innohi.StorageInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StorageInfo createFromParcel(Parcel parcel) {
            return new StorageInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StorageInfo[] newArray(int i) {
            return new StorageInfo[i];
        }
    };
    public static final int TYPE_LOCAL_STORAGE = 2;
    public static final int TYPE_MEMORY = 1;
    public static final int TYPE_TFCARD = 3;
    public static final int TYPE_USB_STORAGE = 4;
    private long mFreeSize;
    private String mPath;
    private long mTotalSize;
    private int mType;

    public StorageInfo(int i, long j, long j2, String str) {
        this.mType = 0;
        this.mTotalSize = 0L;
        this.mFreeSize = 0L;
        this.mPath = "";
        this.mType = i;
        this.mTotalSize = j;
        this.mFreeSize = j2;
        this.mPath = str;
    }

    public StorageInfo(Parcel parcel) {
        this.mType = 0;
        this.mTotalSize = 0L;
        this.mFreeSize = 0L;
        this.mPath = "";
        this.mType = parcel.readInt();
        this.mTotalSize = parcel.readLong();
        this.mFreeSize = parcel.readLong();
        this.mPath = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getFreeSize() {
        return this.mFreeSize;
    }

    public String getPath() {
        return this.mPath;
    }

    public long getTotalSize() {
        return this.mTotalSize;
    }

    public int getType() {
        return this.mType;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mTotalSize);
        parcel.writeLong(this.mFreeSize);
        parcel.writeString(this.mPath);
    }
}
