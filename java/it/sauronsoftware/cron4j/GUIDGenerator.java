package it.sauronsoftware.cron4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import kotlin.UByte;

/* loaded from: classes2.dex */
class GUIDGenerator {
    private static String MACHINE_DESCRIPTOR = getMachineDescriptor();

    GUIDGenerator() {
    }

    private static StringBuffer buildNetworkInterfaceDescriptor() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            StringBuffer stringBuffer = new StringBuffer();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                StringBuffer mACAddressDescriptor = getMACAddressDescriptor(nextElement);
                StringBuffer inetAddressDescriptor = getInetAddressDescriptor(nextElement);
                StringBuffer stringBuffer2 = new StringBuffer();
                if (mACAddressDescriptor != null) {
                    stringBuffer2.append(mACAddressDescriptor);
                }
                if (inetAddressDescriptor != null) {
                    if (stringBuffer2.length() > 0) {
                        stringBuffer2.append('=');
                    }
                    stringBuffer2.append(inetAddressDescriptor);
                }
                if (stringBuffer2.length() > 0) {
                    if (stringBuffer.length() > 0) {
                        stringBuffer.append(';');
                    }
                    stringBuffer.append(stringBuffer2);
                }
            }
            return stringBuffer;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void encode(StringBuffer stringBuffer, int i) {
        String hexString = Integer.toHexString(i);
        int length = hexString.length();
        for (int i2 = 8; i2 > length; i2--) {
            stringBuffer.append('0');
        }
        stringBuffer.append(hexString);
    }

    private static void encode(StringBuffer stringBuffer, long j) {
        String hexString = Long.toHexString(j);
        int length = hexString.length();
        for (int i = 16; i > length; i--) {
            stringBuffer.append('0');
        }
        stringBuffer.append(hexString);
    }

    private static void encode(StringBuffer stringBuffer, Object obj) {
        encode(stringBuffer, obj.hashCode());
    }

    public static String generate() {
        StringBuffer stringBuffer = new StringBuffer();
        encode(stringBuffer, MACHINE_DESCRIPTOR);
        encode(stringBuffer, Runtime.getRuntime());
        encode(stringBuffer, Thread.currentThread());
        encode(stringBuffer, System.currentTimeMillis());
        encode(stringBuffer, getRandomInt());
        return stringBuffer.toString();
    }

    private static StringBuffer getInetAddressDescriptor(NetworkInterface networkInterface) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress nextElement = inetAddresses.nextElement();
            if (stringBuffer.length() > 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append(nextElement.getHostAddress());
        }
        return stringBuffer;
    }

    private static StringBuffer getMACAddressDescriptor(NetworkInterface networkInterface) {
        byte[] bArr;
        try {
            bArr = networkInterface.getHardwareAddress();
        } catch (Throwable unused) {
            bArr = null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr != null) {
            for (byte b : bArr) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append("-");
                }
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
        }
        return stringBuffer;
    }

    private static String getMachineDescriptor() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(System.getProperty("os.name"));
        stringBuffer.append("::");
        stringBuffer.append(System.getProperty("os.arch"));
        stringBuffer.append("::");
        stringBuffer.append(System.getProperty("os.version"));
        stringBuffer.append("::");
        stringBuffer.append(System.getProperty("user.name"));
        stringBuffer.append("::");
        StringBuffer buildNetworkInterfaceDescriptor = buildNetworkInterfaceDescriptor();
        if (buildNetworkInterfaceDescriptor != null) {
            stringBuffer.append(buildNetworkInterfaceDescriptor);
        } else {
            try {
                stringBuffer.append(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException unused) {
            }
        }
        return stringBuffer.toString();
    }

    private static int getRandomInt() {
        return (int) Math.round(Math.random() * 2.147483647E9d);
    }
}
