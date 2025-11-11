package io.rx_cache2.internal.cache;

import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
abstract class Action {
    private static final String PREFIX_DYNAMIC_KEY = "$d$d$d$";
    private static final String PREFIX_DYNAMIC_KEY_GROUP = "$g$g$g$";
    protected final Memory memory;
    protected final Persistence persistence;

    public Action(Memory memory, Persistence persistence) {
        this.memory = memory;
        this.persistence = persistence;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String composeKey(String str, String str2, String str3) {
        return str + PREFIX_DYNAMIC_KEY + str2 + PREFIX_DYNAMIC_KEY_GROUP + str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getKeyOnMemoryMatchingDynamicKeyGroup(String str, String str2, String str3) {
        return composeKey(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> getKeysOnMemoryMatchingDynamicKey(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        String str3 = str + PREFIX_DYNAMIC_KEY + str2;
        for (String str4 : this.memory.keySet()) {
            if (str3.equals(str4.substring(0, str4.lastIndexOf(PREFIX_DYNAMIC_KEY_GROUP)))) {
                arrayList.add(str4);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> getKeysOnMemoryMatchingProviderKey(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : this.memory.keySet()) {
            if (str.equals(str2.substring(0, str2.lastIndexOf(PREFIX_DYNAMIC_KEY)))) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }
}
