package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;

/* compiled from: UInt.kt */
@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 y2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001yB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u000fJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010F\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u001dJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0016J\u001e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bM\u0010\u000bJ\u001e\u0010N\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u000fJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bR\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u001dJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bT\u0010\u0016J\u0010\u0010U\u001a\u00020VH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020ZH\u0087\b¢\u0006\u0004\b[\u0010\\J\u0010\u0010]\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0016\u0010o\u001a\u00020\rH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bp\u0010XJ\u0016\u0010q\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005J\u0016\u0010s\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bt\u0010fJ\u0016\u0010u\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bv\u0010jJ\u001b\u0010w\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bx\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006z"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes2.dex */
public final class UInt implements Comparable<UInt> {
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    @PublishedApi
    private /* synthetic */ UInt(int i) {
        this.data = i;
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns */
    private static final int m102andWZ4Q5Ns(int i, int i2) {
        return m109constructorimpl(i & i2);
    }

    /* renamed from: box-impl */
    public static final /* synthetic */ UInt m103boximpl(int i) {
        return new UInt(i);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static final int m104compareTo7apg3OU(int i, byte b) {
        return UnsignedKt.uintCompare(i, m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static final int m105compareToVKZWuLQ(int i, long j) {
        return UnsignedKt.ulongCompare(ULong.m187constructorimpl(i & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private int m106compareToWZ4Q5Ns(int i) {
        return UnsignedKt.uintCompare(getData(), i);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static int m107compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m108compareToxj2QHRw(int i, short s) {
        return UnsignedKt.uintCompare(i, m109constructorimpl(s & UShort.MAX_VALUE));
    }

    @PublishedApi
    /* renamed from: constructor-impl */
    public static int m109constructorimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: dec-pVg5ArA */
    private static final int m110decpVg5ArA(int i) {
        return m109constructorimpl(i - 1);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final int m111div7apg3OU(int i, byte b) {
        return UnsignedKt.m362uintDivideJ1ME1BU(i, m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m112divVKZWuLQ(int i, long j) {
        return UnsignedKt.m364ulongDivideeb3DHEI(ULong.m187constructorimpl(i & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final int m113divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m362uintDivideJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final int m114divxj2QHRw(int i, short s) {
        return UnsignedKt.m362uintDivideJ1ME1BU(i, m109constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: equals-impl */
    public static boolean m115equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m116equalsimpl0(int i, int i2) {
        return i == i2;
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU */
    private static final int m117floorDiv7apg3OU(int i, byte b) {
        return UnsignedKt.m362uintDivideJ1ME1BU(i, m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m118floorDivVKZWuLQ(int i, long j) {
        return UnsignedKt.m364ulongDivideeb3DHEI(ULong.m187constructorimpl(i & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final int m119floorDivWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m362uintDivideJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw */
    private static final int m120floorDivxj2QHRw(int i, short s) {
        return UnsignedKt.m362uintDivideJ1ME1BU(i, m109constructorimpl(s & UShort.MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m121hashCodeimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: inc-pVg5ArA */
    private static final int m122incpVg5ArA(int i) {
        return m109constructorimpl(i + 1);
    }

    @InlineOnly
    /* renamed from: inv-pVg5ArA */
    private static final int m123invpVg5ArA(int i) {
        return m109constructorimpl(~i);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final int m124minus7apg3OU(int i, byte b) {
        return m109constructorimpl(i - m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m125minusVKZWuLQ(int i, long j) {
        return ULong.m187constructorimpl(ULong.m187constructorimpl(i & 4294967295L) - j);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final int m126minusWZ4Q5Ns(int i, int i2) {
        return m109constructorimpl(i - i2);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final int m127minusxj2QHRw(int i, short s) {
        return m109constructorimpl(i - m109constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU */
    private static final byte m128mod7apg3OU(int i, byte b) {
        return UByte.m33constructorimpl((byte) UnsignedKt.m363uintRemainderJ1ME1BU(i, m109constructorimpl(b & UByte.MAX_VALUE)));
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ */
    private static final long m129modVKZWuLQ(int i, long j) {
        return UnsignedKt.m365ulongRemaindereb3DHEI(ULong.m187constructorimpl(i & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns */
    private static final int m130modWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m363uintRemainderJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw */
    private static final short m131modxj2QHRw(int i, short s) {
        return UShort.m293constructorimpl((short) UnsignedKt.m363uintRemainderJ1ME1BU(i, m109constructorimpl(s & UShort.MAX_VALUE)));
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns */
    private static final int m132orWZ4Q5Ns(int i, int i2) {
        return m109constructorimpl(i | i2);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final int m133plus7apg3OU(int i, byte b) {
        return m109constructorimpl(i + m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m134plusVKZWuLQ(int i, long j) {
        return ULong.m187constructorimpl(ULong.m187constructorimpl(i & 4294967295L) + j);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final int m135plusWZ4Q5Ns(int i, int i2) {
        return m109constructorimpl(i + i2);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final int m136plusxj2QHRw(int i, short s) {
        return m109constructorimpl(i + m109constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns */
    private static final UIntRange m137rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final int m138rem7apg3OU(int i, byte b) {
        return UnsignedKt.m363uintRemainderJ1ME1BU(i, m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m139remVKZWuLQ(int i, long j) {
        return UnsignedKt.m365ulongRemaindereb3DHEI(ULong.m187constructorimpl(i & 4294967295L), j);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final int m140remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m363uintRemainderJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final int m141remxj2QHRw(int i, short s) {
        return UnsignedKt.m363uintRemainderJ1ME1BU(i, m109constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: shl-pVg5ArA */
    private static final int m142shlpVg5ArA(int i, int i2) {
        return m109constructorimpl(i << i2);
    }

    @InlineOnly
    /* renamed from: shr-pVg5ArA */
    private static final int m143shrpVg5ArA(int i, int i2) {
        return m109constructorimpl(i >>> i2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final int m144times7apg3OU(int i, byte b) {
        return m109constructorimpl(i * m109constructorimpl(b & UByte.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m145timesVKZWuLQ(int i, long j) {
        return ULong.m187constructorimpl(ULong.m187constructorimpl(i & 4294967295L) * j);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final int m146timesWZ4Q5Ns(int i, int i2) {
        return m109constructorimpl(i * i2);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final int m147timesxj2QHRw(int i, short s) {
        return m109constructorimpl(i * m109constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m148toByteimpl(int i) {
        return (byte) i;
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m149toDoubleimpl(int i) {
        return UnsignedKt.uintToDouble(i);
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m150toFloatimpl(int i) {
        return (float) UnsignedKt.uintToDouble(i);
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m151toIntimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m152toLongimpl(int i) {
        return i & 4294967295L;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m153toShortimpl(int i) {
        return (short) i;
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m154toStringimpl(int i) {
        return String.valueOf(i & 4294967295L);
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ */
    private static final byte m155toUBytew2LRezQ(int i) {
        return UByte.m33constructorimpl((byte) i);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA */
    private static final int m156toUIntpVg5ArA(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU */
    private static final long m157toULongsVKNKU(int i) {
        return ULong.m187constructorimpl(i & 4294967295L);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg */
    private static final short m158toUShortMh2AYeg(int i) {
        return UShort.m293constructorimpl((short) i);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns */
    private static final int m159xorWZ4Q5Ns(int i, int i2) {
        return m109constructorimpl(i ^ i2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(getData(), uInt.getData());
    }

    public boolean equals(Object obj) {
        return m115equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m121hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m154toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: from getter */
    public final /* synthetic */ int getData() {
        return this.data;
    }
}
