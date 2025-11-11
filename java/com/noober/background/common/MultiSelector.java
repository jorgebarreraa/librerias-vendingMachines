package com.noober.background.common;

import android.R;

/* loaded from: classes.dex */
public enum MultiSelector {
    State_Checkable("state_checkable", R.attr.state_checkable),
    State_Checked("state_checked", R.attr.state_checked),
    State_Enabled("state_enabled", R.attr.state_enabled),
    State_Selected("state_selected", R.attr.state_selected),
    State_Pressed("state_pressed", R.attr.state_pressed),
    State_Focused("state_focused", R.attr.state_focused),
    State_Hovered("state_hovered", R.attr.state_hovered),
    State_Activated("state_activated", R.attr.state_activated);

    public int id;
    public String value;

    MultiSelector(String str, int i) {
        this.value = str;
        this.id = i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static MultiSelector getMultiAttr(String str) {
        char c;
        switch (str.hashCode()) {
            case -1722420599:
                if (str.equals("state_selected")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1616325175:
                if (str.equals("state_focused")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1243548044:
                if (str.equals("state_pressed")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -182969863:
                if (str.equals("state_checked")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 64931747:
                if (str.equals("state_activated")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 175751469:
                if (str.equals("state_hovered")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 259503156:
                if (str.equals("state_checkable")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1760089491:
                if (str.equals("state_enabled")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return State_Checkable;
            case 1:
                return State_Checked;
            case 2:
                return State_Enabled;
            case 3:
                return State_Selected;
            case 4:
                return State_Pressed;
            case 5:
                return State_Focused;
            case 6:
                return State_Hovered;
            case 7:
                return State_Activated;
            default:
                return null;
        }
    }
}
