package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlin.time.ExperimentalTime;

/* compiled from: DurationConversions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"toJavaDuration", "Ljava/time/Duration;", "Lkotlin/time/Duration;", "toJavaDuration-LRDsOJo", "(D)Ljava/time/Duration;", "toKotlinDuration", "(Ljava/time/Duration;)D", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 4, 0}, pn = "kotlin.time")
@JvmName(name = "DurationConversionsJDK8Kt")
/* loaded from: classes2.dex */
public final class DurationConversionsJDK8Kt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    @ExperimentalTime
    /* renamed from: toJavaDuration-LRDsOJo, reason: not valid java name */
    private static final Duration m1484toJavaDurationLRDsOJo(double d) {
        Duration ofSeconds = Duration.ofSeconds((long) kotlin.time.Duration.getInSeconds-impl(d), kotlin.time.Duration.getNanosecondsComponent-impl(d));
        Intrinsics.checkNotNullExpressionValue(ofSeconds, "toComponents { seconds, …, nanoseconds.toLong()) }");
        return ofSeconds;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    @ExperimentalTime
    private static final double toKotlinDuration(Duration duration) {
        return kotlin.time.Duration.plus-LRDsOJo(DurationKt.getSeconds(duration.getSeconds()), DurationKt.getNanoseconds(duration.getNano()));
    }
}
