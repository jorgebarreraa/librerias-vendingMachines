package com.google.zxing.pdf417.encoder;

import android.app.kingsun.rk3288;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.yj.coffeemachines.Listeners.MultipleClicksListeners;
import okhttp3.internal.http.StatusLine;

/* loaded from: classes.dex */
final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{rk3288.RK30_PIN7_PB5, StatusLine.HTTP_PERM_REDIRECT, 436, rk3288.RK30_PIN8_PD4, 646, 653, 428, 379}, new int[]{rk3288.RK30_PIN8_PC2, 562, rk3288.RK30_PIN7_PB0, 755, 599, 524, 801, 132, 295, 116, 442, 428, 295, 42, rk3288.RK30_PIN5_PC0, 65}, new int[]{361, 575, 922, 525, rk3288.RK30_PIN5_PC0, 586, 640, 321, 536, 742, 677, 742, 687, rk3288.RK30_PIN8_PD4, rk3288.RK30_PIN6_PA1, 517, 273, 494, rk3288.RK30_PIN8_PA7, 147, 593, 800, 571, 320, 803, 133, rk3288.RK30_PIN7_PA7, 390, 685, 330, 63, 410}, new int[]{539, 422, 6, 93, 862, 771, 453, 106, 610, rk3288.RK30_PIN8_PD7, 107, 505, 733, 877, 381, 612, 723, 476, 462, rk3288.RK30_PIN5_PB4, 430, 609, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, 400, 672, 762, rk3288.RK30_PIN8_PD3, rk3288.RK30_PIN5_PD0, 440, 35, 519, 31, 460, 594, rk3288.RK30_PIN7_PA1, 535, 517, 352, 605, 158, 651, rk3288.RK30_PIN6_PB1, 488, 502, 648, 733, 717, 83, 404, 97, rk3288.RK30_PIN8_PD0, 771, 840, 629, 4, 381, 843, 623, rk3288.RK30_PIN8_PB0, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, 415, 822, 93, rk3288.RK30_PIN6_PD1, rk3288.RK30_PIN6_PC0, PDF417Common.MAX_CODEWORDS_IN_BARCODE, rk3288.RK30_PIN7_PC4, 583, 620, rk3288.RK30_PIN7_PC6, 148, 447, 631, 292, 908, 490, 704, 516, rk3288.RK30_PIN8_PA2, 457, 907, 594, 723, 674, 292, rk3288.RK30_PIN8_PC0, 96, 684, 432, 686, 606, 860, 569, rk3288.RK30_PIN6_PA1, rk3288.RK30_PIN6_PD3, 129, rk3288.RK30_PIN5_PD2, rk3288.RK30_PIN7_PB4, rk3288.RK30_PIN8_PD7, rk3288.RK30_PIN6_PA0, 775, rk3288.RK30_PIN8_PC6, rk3288.RK30_PIN5_PB5, 40, 379, 712, 463, 646, 776, rk3288.RK30_PIN5_PB3, 491, 297, 763, 156, 732, 95, rk3288.RK30_PIN8_PB6, 447, 90, 507, 48, rk3288.RK30_PIN7_PA4, 821, 808, 898, 784, 663, 627, 378, 382, rk3288.RK30_PIN8_PA6, 380, 602, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, rk3288.RK30_PIN7_PC2, 726, 600, rk3288.RK30_PIN8_PB5, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, rk3288.RK30_PIN6_PC3, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, rk3288.RK30_PIN6_PB4, 82, 586, 708, 250, 905, 786, 138, 720, 858, rk3288.RK30_PIN6_PA2, 311, 913, rk3288.RK30_PIN8_PC3, rk3288.RK30_PIN5_PD6, 375, 850, 438, 733, rk3288.RK30_PIN6_PA2, rk3288.RK30_PIN8_PD0, rk3288.RK30_PIN6_PB1, rk3288.RK30_PIN8_PD0, 828, 757, 710, 814, 919, 89, 68, 569, 11, rk3288.RK30_PIN6_PB4, 796, 605, 540, 913, 801, HardwareConfigState.DEFAULT_MAXIMUM_FDS_FOR_HARDWARE_CONFIGS, 799, 137, 439, 418, 592, 668, 353, 859, 370, 694, 325, rk3288.RK30_PIN7_PC0, rk3288.RK30_PIN6_PD0, 257, rk3288.RK30_PIN8_PD4, 549, rk3288.RK30_PIN6_PC1, 884, 315, 70, 329, 793, 490, rk3288.RK30_PIN8_PC2, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, StatusLine.HTTP_TEMP_REDIRECT, 291, 803, 712, 19, 358, 399, 908, 103, FrameMetricsAggregator.EVERY_DURATION, 51, 8, 517, rk3288.RK30_PIN7_PA1, 289, 470, 637, 731, 66, 255, 917, rk3288.RK30_PIN8_PB5, 463, 830, 730, 433, 848, 585, 136, 538, 906, 90, 2, 290, 743, rk3288.RK30_PIN6_PA7, 655, 903, 329, 49, 802, 580, 355, 588, rk3288.RK30_PIN5_PD4, 462, 10, 134, 628, 320, 479, 130, 739, 71, rk3288.RK30_PIN8_PA7, 318, 374, 601, rk3288.RK30_PIN6_PA0, 605, 142, 673, 687, rk3288.RK30_PIN7_PB2, 722, 384, rk3288.RK30_PIN5_PC1, 752, 607, 640, 455, rk3288.RK30_PIN6_PA1, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, rk3288.RK30_PIN8_PA5, 852, 655, 309, 697, 755, 756, 60, rk3288.RK30_PIN7_PA7, 773, 434, StatusLine.HTTP_MISDIRECTED_REQUEST, 726, 528, 503, 118, 49, 795, 32, 144, MultipleClicksListeners.MIN_CLICK_DELAY_TIME, rk3288.RK30_PIN7_PB6, 836, 394, rk3288.RK30_PIN8_PD0, 566, 319, 9, 647, 550, 73, 914, 342, 126, 32, 681, 331, 792, 620, 60, 609, 441, 180, 791, 893, 754, 605, 383, rk3288.RK30_PIN7_PA4, 749, 760, rk3288.RK30_PIN6_PC5, 54, 297, 134, 54, 834, 299, 922, rk3288.RK30_PIN5_PD7, 910, 532, 609, 829, rk3288.RK30_PIN5_PD5, 20, rk3288.RK30_PIN5_PA7, 29, 872, 449, 83, 402, 41, 656, 505, 579, 481, rk3288.RK30_PIN5_PB5, 404, rk3288.RK30_PIN7_PD3, 688, 95, 497, 555, 642, 543, StatusLine.HTTP_TEMP_REDIRECT, 159, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, rk3288.RK30_PIN6_PB7, 409, 574, 118, 498, rk3288.RK30_PIN8_PD5, 380, 350, 492, rk3288.RK30_PIN6_PA5, rk3288.RK30_PIN8_PB1, 920, 155, 914, 299, rk3288.RK30_PIN7_PA5, 643, 294, 871, 306, 88, 87, rk3288.RK30_PIN6_PA1, 352, 781, 846, 75, 327, 520, 435, 543, rk3288.RK30_PIN6_PB3, 666, rk3288.RK30_PIN7_PD1, 346, 781, 621, 640, rk3288.RK30_PIN8_PB4, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, rk3288.RK30_PIN8_PC0, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, rk3288.RK30_PIN6_PB4, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, 142, 808, 684, rk3288.RK30_PIN8_PD7, 536, 561, 76, 653, 899, 729, 567, 744, 390, InputDeviceCompat.SOURCE_DPAD, rk3288.RK30_PIN6_PA0, 516, rk3288.RK30_PIN8_PA2, rk3288.RK30_PIN7_PC0, 518, 794, 395, 768, 848, 51, 610, 384, rk3288.RK30_PIN5_PB0, rk3288.RK30_PIN5_PD6, 826, 328, 596, 786, 303, 570, 381, 415, 641, 156, rk3288.RK30_PIN7_PB5, 151, 429, 531, rk3288.RK30_PIN6_PB7, 676, 710, 89, rk3288.RK30_PIN5_PB0, 304, 402, 40, 708, 575, 162, 864, rk3288.RK30_PIN7_PA5, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, rk3288.RK30_PIN7_PD0, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, 902, 452, rk3288.RK30_PIN5_PA7, 342, rk3288.RK30_PIN7_PC4, rk3288.RK30_PIN5_PB5, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, rk3288.RK30_PIN8_PD3, 711, 472, 420, rk3288.RK30_PIN7_PC5, 288, 594, 394, FrameMetricsAggregator.EVERY_DURATION, 327, 589, 777, 699, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, rk3288.RK30_PIN6_PA1, 417, 158, rk3288.RK30_PIN6_PC1, 563, 564, 343, 693, 109, 608, 563, 365, rk3288.RK30_PIN5_PC5, 772, 677, 310, rk3288.RK30_PIN7_PD0, 353, 708, 410, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, 424, 833, 77, 597, 346, rk3288.RK30_PIN8_PB5, 757, 632, 695, 751, 331, rk3288.RK30_PIN7_PC7, rk3288.RK30_PIN5_PD0, 45, 787, 680, 18, 66, 407, 369, 54, 492, rk3288.RK30_PIN7_PA4, 613, 830, 922, 437, 519, 644, 905, 789, 420, 305, 441, rk3288.RK30_PIN6_PB7, 300, 892, 827, 141, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, rk3288.RK30_PIN7_PD4, 341, rk3288.RK30_PIN7_PC2, 797, 838, 837, 720, rk3288.RK30_PIN7_PA0, StatusLine.HTTP_TEMP_REDIRECT, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, rk3288.RK30_PIN6_PD0, 548, rk3288.RK30_PIN7_PD1, 321, 881, 699, 535, 673, 782, rk3288.RK30_PIN6_PC2, 815, 905, 303, 843, 922, rk3288.RK30_PIN8_PD1, 73, 469, 791, 660, 162, 498, StatusLine.HTTP_PERM_REDIRECT, 155, 422, 907, 817, rk3288.RK30_PIN5_PD3, 62, 16, 425, 535, 336, rk3288.RK30_PIN8_PD6, 437, 375, 273, 610, 296, rk3288.RK30_PIN5_PC7, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, 424, rk3288.RK30_PIN7_PC2, 749, 321, 54, 669, 316, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, 610, 46, 656, 447, rk3288.RK30_PIN5_PB3, 616, 464, rk3288.RK30_PIN5_PD6, 531, 297, 321, 762, 752, 533, rk3288.RK30_PIN5_PB7, 134, 14, 381, 433, 717, 45, 111, 20, 596, rk3288.RK30_PIN8_PD4, 736, 138, 646, 411, 877, 669, 141, 919, 45, 780, 407, 164, 332, 899, 165, 726, 600, 325, 498, 655, 357, 752, 768, rk3288.RK30_PIN6_PD7, 849, 647, 63, 310, 863, rk3288.RK30_PIN7_PD3, 366, 304, rk3288.RK30_PIN8_PD2, 738, 675, 410, 389, rk3288.RK30_PIN7_PC4, 31, 121, 303, rk3288.RK30_PIN8_PA7}};

    private PDF417ErrorCorrection() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String generateErrorCorrection(CharSequence charSequence, int i) {
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(i);
        char[] cArr = new char[errorCorrectionCodewordCount];
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            int charAt = (charSequence.charAt(i2) + cArr[cArr.length - 1]) % PDF417Common.NUMBER_OF_CODEWORDS;
            for (int i3 = errorCorrectionCodewordCount - 1; i3 >= 1; i3--) {
                cArr[i3] = (char) ((cArr[i3 - 1] + (929 - ((EC_COEFFICIENTS[i][i3] * charAt) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
            }
            cArr[0] = (char) ((929 - ((charAt * EC_COEFFICIENTS[i][0]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(errorCorrectionCodewordCount);
        for (int i4 = errorCorrectionCodewordCount - 1; i4 >= 0; i4--) {
            if (cArr[i4] != 0) {
                cArr[i4] = (char) (929 - cArr[i4]);
            }
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getErrorCorrectionCodewordCount(int i) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
        }
        return 1 << (i + 1);
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        if (i <= 40) {
            return 2;
        }
        if (i <= 160) {
            return 3;
        }
        if (i <= 320) {
            return 4;
        }
        if (i <= 863) {
            return 5;
        }
        throw new WriterException("No recommendation possible");
    }
}
