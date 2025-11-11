package com.yj.coffeemachines.mvp.model.beans;

import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceInfoBean {
    private InfoBean deviceInfo;
    private List<PowerPlanBean> devicePowerPlanVos;
    private TypeInfoBean deviceType;

    /* loaded from: classes.dex */
    public static class InfoBean {
        private String address;
        private double coldHight;
        private double coldLog;
        private String createTime;
        private String deviceExtNo;
        private String deviceNo;
        private String devicePwd;
        private int deviceRunStatus;
        private String deviceTypeId;
        private String divideSettingUpdateTime;
        private String exceptionRemark;
        private String hardwareVersion;
        private String heartTime;
        private double hotHight;
        private double hotLow;
        private String id;
        private int isDeleted;
        private String latestRenewRecordId;
        private double latitude;
        private double longitude;
        private String merchantId;
        private String name;
        private String regionCode;
        private String servicePhone;
        private String softwareVersion;
        private int status;
        private String updateTime;
        private String vipEndTime;
        private String vipStartTime;
        private int vipStatus;

        public String getAddress() {
            return this.address;
        }

        public double getColdHight() {
            return this.coldHight;
        }

        public double getColdLog() {
            return this.coldLog;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public String getDeviceExtNo() {
            return this.deviceExtNo;
        }

        public String getDeviceNo() {
            return this.deviceNo;
        }

        public String getDevicePwd() {
            return this.devicePwd;
        }

        public int getDeviceRunStatus() {
            return this.deviceRunStatus;
        }

        public String getDeviceTypeId() {
            return this.deviceTypeId;
        }

        public String getDivideSettingUpdateTime() {
            return this.divideSettingUpdateTime;
        }

        public String getExceptionRemark() {
            return this.exceptionRemark;
        }

        public String getHardwareVersion() {
            return this.hardwareVersion;
        }

        public String getHeartTime() {
            return this.heartTime;
        }

        public double getHotHight() {
            return this.hotHight;
        }

        public double getHotLow() {
            return this.hotLow;
        }

        public String getId() {
            return this.id;
        }

        public int getIsDeleted() {
            return this.isDeleted;
        }

        public String getLatestRenewRecordId() {
            return this.latestRenewRecordId;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public String getMerchantId() {
            return this.merchantId;
        }

        public String getName() {
            return this.name;
        }

        public String getRegionCode() {
            return this.regionCode;
        }

        public String getServicePhone() {
            return this.servicePhone;
        }

        public String getSoftwareVersion() {
            return this.softwareVersion;
        }

        public int getStatus() {
            return this.status;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public String getVipEndTime() {
            return this.vipEndTime;
        }

        public String getVipStartTime() {
            return this.vipStartTime;
        }

        public int getVipStatus() {
            return this.vipStatus;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setColdHight(double d) {
            this.coldHight = d;
        }

        public void setColdLog(double d) {
            this.coldLog = d;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setDeviceExtNo(String str) {
            this.deviceExtNo = str;
        }

        public void setDeviceNo(String str) {
            this.deviceNo = str;
        }

        public void setDevicePwd(String str) {
            this.devicePwd = str;
        }

        public void setDeviceRunStatus(int i) {
            this.deviceRunStatus = i;
        }

        public void setDeviceTypeId(String str) {
            this.deviceTypeId = str;
        }

        public void setDivideSettingUpdateTime(String str) {
            this.divideSettingUpdateTime = str;
        }

        public void setExceptionRemark(String str) {
            this.exceptionRemark = str;
        }

        public void setHardwareVersion(String str) {
            this.hardwareVersion = str;
        }

        public void setHeartTime(String str) {
            this.heartTime = str;
        }

        public void setHotHight(double d) {
            this.hotHight = d;
        }

        public void setHotLow(double d) {
            this.hotLow = d;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setIsDeleted(int i) {
            this.isDeleted = i;
        }

        public void setLatestRenewRecordId(String str) {
            this.latestRenewRecordId = str;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }

        public void setMerchantId(String str) {
            this.merchantId = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setRegionCode(String str) {
            this.regionCode = str;
        }

        public void setServicePhone(String str) {
            this.servicePhone = str;
        }

        public void setSoftwareVersion(String str) {
            this.softwareVersion = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setVipEndTime(String str) {
            this.vipEndTime = str;
        }

        public void setVipStartTime(String str) {
            this.vipStartTime = str;
        }

        public void setVipStatus(int i) {
            this.vipStatus = i;
        }

        public String toMyString() {
            ArmsUtils.getResources(MyAppLocation.myAppLocation).getString(R.string.equipment_information);
            return "";
        }
    }

    /* loaded from: classes.dex */
    public static class PowerPlanBean {
        private String cycle;
        private String deviceId;
        private String deviceNo;
        private String id;
        private String powerOffCycleCron;
        private String powerOffTimeStr;
        private String powerOnCycleCron;
        private String powerOnTimeStr;
        private String timeZoneId;

        public String getCycle() {
            return this.cycle;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public String getDeviceNo() {
            return this.deviceNo;
        }

        public String getId() {
            return this.id;
        }

        public String getPowerOffCycleCron() {
            return this.powerOffCycleCron;
        }

        public String getPowerOffTimeStr() {
            return this.powerOffTimeStr;
        }

        public String getPowerOnCycleCron() {
            return this.powerOnCycleCron;
        }

        public String getPowerOnTimeStr() {
            return this.powerOnTimeStr;
        }

        public String getTimeZoneId() {
            return this.timeZoneId;
        }

        public void setCycle(String str) {
            this.cycle = str;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public void setDeviceNo(String str) {
            this.deviceNo = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setPowerOffCycleCron(String str) {
            this.powerOffCycleCron = str;
        }

        public void setPowerOffTimeStr(String str) {
            this.powerOffTimeStr = str;
        }

        public void setPowerOnCycleCron(String str) {
            this.powerOnCycleCron = str;
        }

        public void setPowerOnTimeStr(String str) {
            this.powerOnTimeStr = str;
        }

        public void setTimeZoneId(String str) {
            this.timeZoneId = str;
        }
    }

    /* loaded from: classes.dex */
    public static class TypeInfoBean {
        private String createTime;
        private String hardwareVersion;
        private String id;
        private int isDeleted;
        private String name;
        private String protocol;
        private String series;
        private String softwareVersion;
        private int status;
        private String updateTime;

        public String getCreateTime() {
            return this.createTime;
        }

        public String getHardwareVersion() {
            return this.hardwareVersion;
        }

        public String getId() {
            return this.id;
        }

        public int getIsDeleted() {
            return this.isDeleted;
        }

        public String getName() {
            return this.name;
        }

        public String getProtocol() {
            return this.protocol;
        }

        public String getSeries() {
            return this.series;
        }

        public String getSoftwareVersion() {
            return this.softwareVersion;
        }

        public int getStatus() {
            return this.status;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setHardwareVersion(String str) {
            this.hardwareVersion = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setIsDeleted(int i) {
            this.isDeleted = i;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setProtocol(String str) {
            this.protocol = str;
        }

        public void setSeries(String str) {
            this.series = str;
        }

        public void setSoftwareVersion(String str) {
            this.softwareVersion = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public String toMyString() {
            return "";
        }
    }

    public InfoBean getDeviceInfo() {
        return this.deviceInfo;
    }

    public List<PowerPlanBean> getDevicePowerPlan() {
        return this.devicePowerPlanVos;
    }

    public TypeInfoBean getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceInfo(InfoBean infoBean) {
        this.deviceInfo = infoBean;
    }

    public void setDevicePowerPlanVos(List<PowerPlanBean> list) {
        this.devicePowerPlanVos = list;
    }

    public void setDeviceType(TypeInfoBean typeInfoBean) {
        this.deviceType = typeInfoBean;
    }
}
