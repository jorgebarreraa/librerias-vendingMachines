package com.jess.arms.integration;

import com.jess.arms.base.Platform;
import java.lang.reflect.Method;
import org.greenrobot.eventbus.Subscribe;
// import org.simple.eventbus.EventBus; // Eliminado: dependencia no disponible

/* loaded from: classes.dex */
public final class EventBusManager {
    private static volatile EventBusManager sInstance;

    private EventBusManager() {
    }

    public static EventBusManager getInstance() {
        if (sInstance == null) {
            synchronized (EventBusManager.class) {
                if (sInstance == null) {
                    sInstance = new EventBusManager();
                }
            }
        }
        return sInstance;
    }

    private boolean haveAnnotation(Object obj) {
        Method[] methods;
        Class<?> cls = obj.getClass();
        boolean z = false;
        while (cls != null && !isSystemCalss(cls.getName()) && !z) {
            try {
                try {
                    methods = cls.getDeclaredMethods();
                } catch (Throwable unused) {
                    methods = cls.getMethods();
                    z = true;
                }
                for (Method method : methods) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (method.isAnnotationPresent(Subscribe.class) && parameterTypes.length == 1) {
                        return true;
                    }
                }
                cls = cls.getSuperclass();
            } catch (Throwable th) {
                throw th;
            }
        }
        return false;
    }

    private boolean isSystemCalss(String str) {
        return str.startsWith("java.") || str.startsWith("javax.") || str.startsWith("android.");
    }

    public void clear() {
        if (Platform.DEPENDENCY_ANDROID_EVENTBUS) {
            EventBus.getDefault().clear();
        } else if (Platform.DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.clearCaches();
        }
    }

    public void post(Object obj) {
        if (Platform.DEPENDENCY_ANDROID_EVENTBUS) {
            EventBus.getDefault().post(obj);
        } else if (Platform.DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.getDefault().post(obj);
        }
    }

    public void postSticky(Object obj) {
        if (Platform.DEPENDENCY_ANDROID_EVENTBUS) {
            EventBus.getDefault().postSticky(obj);
        } else if (Platform.DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.getDefault().postSticky(obj);
        }
    }

    public void register(Object obj) {
        if (Platform.DEPENDENCY_ANDROID_EVENTBUS) {
            EventBus.getDefault().register(obj);
        }
        if (Platform.DEPENDENCY_EVENTBUS && haveAnnotation(obj)) {
            org.greenrobot.eventbus.EventBus.getDefault().register(obj);
        }
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        if (Platform.DEPENDENCY_ANDROID_EVENTBUS) {
            EventBus.getDefault().removeStickyEvent(cls);
            return null;
        }
        if (Platform.DEPENDENCY_EVENTBUS) {
            return (T) org.greenrobot.eventbus.EventBus.getDefault().removeStickyEvent((Class) cls);
        }
        return null;
    }

    public void unregister(Object obj) {
        if (Platform.DEPENDENCY_ANDROID_EVENTBUS) {
            EventBus.getDefault().unregister(obj);
        }
        if (Platform.DEPENDENCY_EVENTBUS && haveAnnotation(obj)) {
            org.greenrobot.eventbus.EventBus.getDefault().unregister(obj);
        }
    }
}
