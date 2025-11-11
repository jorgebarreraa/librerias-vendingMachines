package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.model.WorkTypeConverters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public final class RawQueries {
    private RawQueries() {
    }

    private static void bindings(@NonNull StringBuilder sb, int i) {
        if (i <= 0) {
            return;
        }
        sb.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(",");
            sb.append("?");
        }
    }

    @NonNull
    public static SupportSQLiteQuery workQueryToRawQuery(@NonNull WorkQuery workQuery) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder("SELECT * FROM workspec");
        List<WorkInfo.State> states = workQuery.getStates();
        String str = " AND";
        String str2 = " WHERE";
        if (!states.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(states.size());
            Iterator<WorkInfo.State> it2 = states.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Integer.valueOf(WorkTypeConverters.stateToInt(it2.next())));
            }
            sb.append(" WHERE");
            sb.append(" state IN (");
            bindings(sb, arrayList2.size());
            sb.append(")");
            arrayList.addAll(arrayList2);
            str2 = " AND";
        }
        List<UUID> ids = workQuery.getIds();
        if (!ids.isEmpty()) {
            ArrayList arrayList3 = new ArrayList(ids.size());
            Iterator<UUID> it3 = ids.iterator();
            while (it3.hasNext()) {
                arrayList3.add(it3.next().toString());
            }
            sb.append(str2);
            sb.append(" id IN (");
            bindings(sb, ids.size());
            sb.append(")");
            arrayList.addAll(arrayList3);
            str2 = " AND";
        }
        List<String> tags = workQuery.getTags();
        if (tags.isEmpty()) {
            str = str2;
        } else {
            sb.append(str2);
            sb.append(" id IN (SELECT work_spec_id FROM worktag WHERE tag IN (");
            bindings(sb, tags.size());
            sb.append("))");
            arrayList.addAll(tags);
        }
        List<String> uniqueWorkNames = workQuery.getUniqueWorkNames();
        if (!uniqueWorkNames.isEmpty()) {
            sb.append(str);
            sb.append(" id IN (SELECT work_spec_id FROM workname WHERE name IN (");
            bindings(sb, uniqueWorkNames.size());
            sb.append("))");
            arrayList.addAll(uniqueWorkNames);
        }
        sb.append(";");
        return new SimpleSQLiteQuery(sb.toString(), arrayList.toArray());
    }
}
