package com.yj.coffeemachines.mvp.ui.widget;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import androidx.annotation.RequiresApi;
import com.yj.coffeemachines.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes.dex */
public class MyDatePickerDialog extends AlertDialog {
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private OnImgDialogListener listener;
    private final Calendar mCalendar;
    private final DatePicker mDatePicker_start;
    private boolean mTitleNeedsUpdate;
    private View view;

    /* loaded from: classes.dex */
    public interface OnImgDialogListener {
        void cancle();

        void onItemImg(int i, int i2, int i3, String str);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public MyDatePickerDialog(Context context, int i) {
        super(context, i);
        this.mTitleNeedsUpdate = true;
        this.mCalendar = Calendar.getInstance();
        this.view = LayoutInflater.from(getContext()).inflate(R.layout.dare_picker_dialog, (ViewGroup) null);
        this.view.setBackgroundColor(-1);
        this.mDatePicker_start = (DatePicker) this.view.findViewById(R.id.datePicker);
        this.mDatePicker_start.setMaxDate(this.mCalendar.getTime().getTime());
        this.mDatePicker_start.setCalendarViewShown(false);
        this.mDatePicker_start.setOnTouchListener(new View.OnTouchListener() { // from class: com.yj.coffeemachines.mvp.ui.widget.MyDatePickerDialog.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    MyDatePickerDialog.this.mDatePicker_start.setMaxDate(new SimpleDateFormat("yyyy-MM-dd").parse(MyDatePickerDialog.this.mDatePicker_start.getYear() + "-" + MyDatePickerDialog.this.mDatePicker_start.getMonth() + "-" + MyDatePickerDialog.this.mDatePicker_start.getDayOfMonth()).getTime());
                    MyDatePickerDialog.this.mDatePicker_start.invalidate();
                    return false;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        setButton();
    }

    private void setButton() {
        this.view.findViewById(R.id.date_picker_ok).setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.widget.MyDatePickerDialog.2
            @Override // android.view.View.OnClickListener
            @RequiresApi(api = 23)
            public void onClick(View view) {
                if (MyDatePickerDialog.this.listener != null) {
                    MyDatePickerDialog.this.listener.onItemImg(MyDatePickerDialog.this.mDatePicker_start.getYear(), MyDatePickerDialog.this.mDatePicker_start.getMonth(), MyDatePickerDialog.this.mDatePicker_start.getDayOfMonth(), "1");
                    MyDatePickerDialog.this.dismiss();
                }
            }
        });
        this.view.findViewById(R.id.date_picker_cancle).setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.widget.MyDatePickerDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MyDatePickerDialog.this.listener != null) {
                    MyDatePickerDialog.this.listener.cancle();
                    MyDatePickerDialog.this.dismiss();
                }
            }
        });
    }

    public DatePicker getDatePicker() {
        return this.mDatePicker_start;
    }

    public void myShow() {
        show();
        setContentView(this.view);
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public void setOnImgDialogListener(OnImgDialogListener onImgDialogListener) {
        this.listener = onImgDialogListener;
    }

    public void updateDate(int i, int i2, int i3) {
        this.mDatePicker_start.updateDate(i, i2, i3);
    }
}
