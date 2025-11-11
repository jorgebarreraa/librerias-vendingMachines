package it.sauronsoftware.cron4j;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes2.dex */
class StaticMethodTask extends Task {
    static /* synthetic */ Class array$Ljava$lang$String;
    private String[] args;
    private String className;
    private String methodName;

    public StaticMethodTask(String str, String str2, String[] strArr) {
        this.className = str;
        this.methodName = str2;
        this.args = strArr;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    @Override // it.sauronsoftware.cron4j.Task
    public void execute(TaskExecutionContext taskExecutionContext) throws RuntimeException {
        Class<?> cls;
        try {
            Class<?> cls2 = Class.forName(this.className);
            try {
                Class<?>[] clsArr = new Class[1];
                if (array$Ljava$lang$String == null) {
                    cls = class$("[Ljava.lang.String;");
                    array$Ljava$lang$String = cls;
                } else {
                    cls = array$Ljava$lang$String;
                }
                clsArr[0] = cls;
                Method method = cls2.getMethod(this.methodName, clsArr);
                if (!Modifier.isStatic(method.getModifiers())) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("The method ");
                    stringBuffer.append(this.methodName);
                    stringBuffer.append("(String[]) of the class ");
                    stringBuffer.append(this.className);
                    stringBuffer.append(" is not static");
                    throw new RuntimeException(stringBuffer.toString());
                }
                try {
                    method.invoke(null, this.args);
                } catch (Exception unused) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Failed to invoke the static method ");
                    stringBuffer2.append(this.methodName);
                    stringBuffer2.append("(String[]) of the class ");
                    stringBuffer2.append(this.className);
                    throw new RuntimeException(stringBuffer2.toString());
                }
            } catch (NoSuchMethodException e) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Cannot find a ");
                stringBuffer3.append(this.methodName);
                stringBuffer3.append("(String[]) method in class ");
                stringBuffer3.append(this.className);
                throw new RuntimeException(stringBuffer3.toString(), e);
            }
        } catch (ClassNotFoundException e2) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Cannot load class ");
            stringBuffer4.append(this.className);
            throw new RuntimeException(stringBuffer4.toString(), e2);
        }
    }
}
