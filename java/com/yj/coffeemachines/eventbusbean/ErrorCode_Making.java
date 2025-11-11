package com.yj.coffeemachines.eventbusbean;

import com.blankj.utilcode.util.StringUtils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class ErrorCode_Making {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        GETCUP(StringUtils.getString(R.string.test_cup_fail)),
        SETWATERANDMATERA(StringUtils.getString(R.string.water_discharge_time)),
        FRESHGROUNDCOFFEEFINISHED(StringUtils.getString(R.string.completion_of_grinding)),
        CUPTYPE(StringUtils.getString(R.string.method_setting)),
        GETDRINK(StringUtils.getString(R.string.dispense_beverage)),
        FINISH(StringUtils.getString(R.string.production_completed)),
        GETICE(StringUtils.getString(R.string.ice_shedding)),
        CURRENTFINISH(StringUtils.getString(R.string.freshly_ground)),
        CURRENTERROR(StringUtils.getString(R.string.production_during)),
        CURRENTFINISHING(StringUtils.getString(R.string.Currently_in_production)),
        TEST(StringUtils.getString(R.string.remove_cup)),
        ORBIT(StringUtils.getString(R.string.foreign_matter)),
        CHECKFALSE(StringUtils.getString(R.string.placement_timeout));

        private String name;

        ErrorCode(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public ErrorCode_Making(boolean z, ErrorCode errorCode, String str) {
        this.isOK = z;
        this.errorCode = errorCode;
        this.message = str;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public boolean isOK() {
        return this.isOK;
    }

    public String toString() {
        return "ErrorCode_Making{isOK=" + this.isOK + ", message='" + this.message + "', errorCode=" + this.errorCode.toString() + '}';
    }
}
