package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class MaterialMessage {
    private Double capacity;
    private String createTime;
    private String createUser;
    private String deviceTypeId;
    private String deviceTypeName;
    private Double expendRate;
    private String id;
    private int isDeleted;
    private boolean ischange;
    private Long key;
    private String locaName;
    private int localFineTuning_cold;
    private int localFineTuning_hot;
    private boolean localIsSuger;
    private int localMaterialFineTuning;
    private int micRate;
    private String name;
    private String no;
    private String position;
    private String rawType;
    private int speedTime;
    private int speedTimeUnit;
    private String speedUnit;
    private int status;
    private String unit;
    private String updateTime;
    private String updateUser;
    private Double warnCapacity;

    public MaterialMessage() {
    }

    public MaterialMessage(Long l, String str, String str2, String str3, String str4, int i, int i2, String str5, String str6, String str7, String str8, String str9, Double d, String str10, String str11, Double d2, Double d3, int i3, int i4, String str12, String str13, String str14, boolean z, int i5, int i6, int i7, int i8, boolean z2) {
        this.key = l;
        this.createUser = str;
        this.createTime = str2;
        this.updateUser = str3;
        this.updateTime = str4;
        this.status = i;
        this.isDeleted = i2;
        this.id = str5;
        this.deviceTypeId = str6;
        this.no = str7;
        this.rawType = str8;
        this.name = str9;
        this.capacity = d;
        this.position = str10;
        this.unit = str11;
        this.expendRate = d2;
        this.warnCapacity = d3;
        this.speedTime = i3;
        this.speedTimeUnit = i4;
        this.speedUnit = str12;
        this.deviceTypeName = str13;
        this.locaName = str14;
        this.ischange = z;
        this.micRate = i5;
        this.localFineTuning_cold = i6;
        this.localFineTuning_hot = i7;
        this.localMaterialFineTuning = i8;
        this.localIsSuger = z2;
    }

    public Double getCapacity() {
        return this.capacity;
    }

    public String getCreateTime() {
        String str = this.createTime;
        return str == null ? "" : str;
    }

    public String getCreateUser() {
        String str = this.createUser;
        return str == null ? "" : str;
    }

    public String getDeviceTypeId() {
        String str = this.deviceTypeId;
        return str == null ? "" : str;
    }

    public String getDeviceTypeName() {
        String str = this.deviceTypeName;
        return str == null ? "" : str;
    }

    public Double getExpendRate() {
        return this.expendRate;
    }

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public int getIsDeleted() {
        return this.isDeleted;
    }

    public boolean getIschange() {
        return this.ischange;
    }

    public Long getKey() {
        return this.key;
    }

    public String getLocaName() {
        String str = this.locaName;
        return str == null ? "" : str;
    }

    public int getLocalFineTuning_cold() {
        return this.localFineTuning_cold;
    }

    public int getLocalFineTuning_hot() {
        return this.localFineTuning_hot;
    }

    public boolean getLocalIsSuger() {
        return this.localIsSuger;
    }

    public int getLocalMaterialFineTuning() {
        return this.localMaterialFineTuning;
    }

    public int getMicRate() {
        return this.micRate;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getNo() {
        String str = this.no;
        return str == null ? "" : str;
    }

    public String getPosition() {
        String str = this.position;
        return str == null ? "0" : str;
    }

    public String getRawType() {
        String str = this.rawType;
        return str == null ? "" : str;
    }

    public int getSpeedTime() {
        return this.speedTime;
    }

    public int getSpeedTimeUnit() {
        return this.speedTimeUnit;
    }

    public String getSpeedUnit() {
        String str = this.speedUnit;
        return str == null ? "" : str;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUnit() {
        String str = this.unit;
        return str == null ? "" : str;
    }

    public String getUpdateTime() {
        String str = this.updateTime;
        return str == null ? "" : str;
    }

    public String getUpdateUser() {
        String str = this.updateUser;
        return str == null ? "" : str;
    }

    public Double getWarnCapacity() {
        return this.warnCapacity;
    }

    public boolean ischange() {
        return this.ischange;
    }

    public void setCapacity(Double d) {
        this.capacity = d;
    }

    public void setCreateTime(String str) {
        if (str == null) {
            str = "";
        }
        this.createTime = str;
    }

    public void setCreateUser(String str) {
        if (str == null) {
            str = "";
        }
        this.createUser = str;
    }

    public void setDeviceTypeId(String str) {
        if (str == null) {
            str = "";
        }
        this.deviceTypeId = str;
    }

    public void setDeviceTypeName(String str) {
        if (str == null) {
            str = "";
        }
        this.deviceTypeName = str;
    }

    public void setExpendRate(Double d) {
        this.expendRate = d;
    }

    public void setId(String str) {
        if (str == null) {
            str = "";
        }
        this.id = str;
    }

    public void setIsDeleted(int i) {
        this.isDeleted = i;
    }

    public void setIschange(boolean z) {
        this.ischange = z;
    }

    public void setKey(Long l) {
        this.key = l;
    }

    public void setLocaName(String str) {
        if (str == null) {
            str = "";
        }
        this.locaName = str;
    }

    public void setLocalFineTuning_cold(int i) {
        this.localFineTuning_cold = i;
    }

    public void setLocalFineTuning_hot(int i) {
        this.localFineTuning_hot = i;
    }

    public void setLocalIsSuger(boolean z) {
        this.localIsSuger = z;
    }

    public void setLocalMaterialFineTuning(int i) {
        this.localMaterialFineTuning = i;
    }

    public void setMicRate(int i) {
        this.micRate = i;
    }

    public void setName(String str) {
        if (str == null) {
            str = "";
        }
        this.name = str;
    }

    public void setNo(String str) {
        if (str == null) {
            str = "";
        }
        this.no = str;
    }

    public void setPosition(String str) {
        if (str == null) {
            str = "";
        }
        this.position = str;
    }

    public void setRawType(String str) {
        if (str == null) {
            str = "";
        }
        this.rawType = str;
    }

    public void setSpeedTime(int i) {
        this.speedTime = i;
    }

    public void setSpeedTimeUnit(int i) {
        this.speedTimeUnit = i;
    }

    public void setSpeedUnit(String str) {
        if (str == null) {
            str = "";
        }
        this.speedUnit = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setUnit(String str) {
        if (str == null) {
            str = "";
        }
        this.unit = str;
    }

    public void setUpdateTime(String str) {
        if (str == null) {
            str = "";
        }
        this.updateTime = str;
    }

    public void setUpdateUser(String str) {
        if (str == null) {
            str = "";
        }
        this.updateUser = str;
    }

    public void setWarnCapacity(Double d) {
        this.warnCapacity = d;
    }

    public String toString() {
        return "MaterialMessage{key=" + this.key + ", createUser='" + this.createUser + "', createTime='" + this.createTime + "', updateUser='" + this.updateUser + "', updateTime='" + this.updateTime + "', status=" + this.status + ", isDeleted=" + this.isDeleted + ", id='" + this.id + "', deviceTypeId='" + this.deviceTypeId + "', no='" + this.no + "', rawType='" + this.rawType + "', name='" + this.name + "', capacity=" + this.capacity + ", position='" + this.position + "', unit='" + this.unit + "', expendRate=" + this.expendRate + ", warnCapacity=" + this.warnCapacity + ", speedTime=" + this.speedTime + ", speedTimeUnit=" + this.speedTimeUnit + ", speedUnit='" + this.speedUnit + "', deviceTypeName='" + this.deviceTypeName + "', locaName='" + this.locaName + "', ischange=" + this.ischange + ", micRate=" + this.micRate + ", localFineTuning_cold=" + this.localFineTuning_cold + ", localFineTuning_hot=" + this.localFineTuning_hot + ", localMaterialFineTuning=" + this.localMaterialFineTuning + ", localIsSuger=" + this.localIsSuger + '}';
    }
}
