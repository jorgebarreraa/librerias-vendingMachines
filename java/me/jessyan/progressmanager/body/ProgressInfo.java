package me.jessyan.progressmanager.body;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ProgressInfo implements Parcelable {
    public static final Parcelable.Creator<ProgressInfo> CREATOR = new Parcelable.Creator<ProgressInfo>() { // from class: me.jessyan.progressmanager.body.ProgressInfo.1
        @Override // android.os.Parcelable.Creator
        public ProgressInfo createFromParcel(Parcel parcel) {
            return new ProgressInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProgressInfo[] newArray(int i) {
            return new ProgressInfo[i];
        }
    };
    private long contentLength;
    private long currentBytes;
    private long eachBytes;
    private boolean finish;
    private long id;
    private long intervalTime;

    public ProgressInfo(long j) {
        this.id = j;
    }

    protected ProgressInfo(Parcel parcel) {
        this.currentBytes = parcel.readLong();
        this.contentLength = parcel.readLong();
        this.intervalTime = parcel.readLong();
        this.eachBytes = parcel.readLong();
        this.id = parcel.readLong();
        this.finish = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public long getCurrentbytes() {
        return this.currentBytes;
    }

    public long getEachBytes() {
        return this.eachBytes;
    }

    public long getId() {
        return this.id;
    }

    public long getIntervalTime() {
        return this.intervalTime;
    }

    public int getPercent() {
        if (getCurrentbytes() <= 0 || getContentLength() <= 0) {
            return 0;
        }
        return (int) ((getCurrentbytes() * 100) / getContentLength());
    }

    public long getSpeed() {
        if (getEachBytes() <= 0 || getIntervalTime() <= 0) {
            return 0L;
        }
        return (getEachBytes() * 1000) / getIntervalTime();
    }

    public boolean isFinish() {
        return this.finish;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContentLength(long j) {
        this.contentLength = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCurrentbytes(long j) {
        this.currentBytes = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEachBytes(long j) {
        this.eachBytes = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFinish(boolean z) {
        this.finish = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIntervalTime(long j) {
        this.intervalTime = j;
    }

    public String toString() {
        return "ProgressInfo{id=" + this.id + ", currentBytes=" + this.currentBytes + ", contentLength=" + this.contentLength + ", eachBytes=" + this.eachBytes + ", intervalTime=" + this.intervalTime + ", finish=" + this.finish + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.currentBytes);
        parcel.writeLong(this.contentLength);
        parcel.writeLong(this.intervalTime);
        parcel.writeLong(this.eachBytes);
        parcel.writeLong(this.id);
        parcel.writeByte(this.finish ? (byte) 1 : (byte) 0);
    }
}
