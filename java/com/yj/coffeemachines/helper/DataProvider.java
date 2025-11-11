package com.yj.coffeemachines.helper;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.helper.bean.DeviceProviderBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import java.io.File;

/* loaded from: classes.dex */
public class DataProvider {
    public static void providerDevice(DeviceInfoBean deviceInfoBean) {
        DeviceProviderBean deviceProviderBean = new DeviceProviderBean();
        deviceProviderBean.setDeviceNo(deviceInfoBean.getDeviceInfo().getDeviceExtNo());
        deviceProviderBean.setDeviceType(deviceInfoBean.getDeviceType().getName());
        deviceProviderBean.setDoorVersion("未知");
        deviceProviderBean.setEndTime(deviceInfoBean.getDeviceInfo().getVipEndTime());
        deviceProviderBean.setFeatureType("未知");
        deviceProviderBean.setGroundVersion("未知");
        deviceProviderBean.setHardwareVersion(deviceInfoBean.getDeviceInfo().getHardwareVersion());
        deviceProviderBean.setIceType("未知");
        deviceProviderBean.setInstantVersion("未知");
        deviceProviderBean.setMainVersion("未知");
        deviceProviderBean.setMdbVersion("未知");
        deviceProviderBean.setPayVersion("未知");
        deviceProviderBean.setProtocol(deviceInfoBean.getDeviceType().getProtocol());
        deviceProviderBean.setServerPhone(deviceInfoBean.getDeviceInfo().getServicePhone());
        deviceProviderBean.setInstantSerialPort(Constants.SERIAPort_instant());
        if (Constants.getStatePort2() == 1) {
            deviceProviderBean.setGroundSerialPort(Constants.SERIAPort_currentgrinding());
        }
        if (Constants.getStatePort3() == 1) {
            deviceProviderBean.setPaySerialPort(Constants.SERIAPort_payment());
        }
        String json = GsonUtils.toJson(deviceProviderBean);
        File file = new File(Constants.tools_device);
        FileUtils.createFileByDeleteOldFile(file);
        FileIOUtils.writeFileFromString(file, json);
    }
}
