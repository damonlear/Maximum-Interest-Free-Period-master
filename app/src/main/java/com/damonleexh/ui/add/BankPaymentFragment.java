package com.damonleexh.ui.add;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;

import java.util.Calendar;

public class BankPaymentFragment extends LazyFragment {
    private TextView tvTitle;
    private TextView tvPaymentTitle;
    private TextView tvPayment;
    private View rlBankPicker;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bank_calendar;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvTitle = view.findViewById(R.id.tv_calendar_title);
        tvPaymentTitle = view.findViewById(R.id.tv_bank_title);
        tvPayment  = view.findViewById(R.id.tv_bank_name);
        rlBankPicker = view.findViewById(R.id.rl_bank_picker);
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText(R.string.pick_payment);
        tvPaymentTitle.setText(R.string.payment_t);
        tvPayment.setText(R.string.pick_payment);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        rlBankPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(4, Calendar.getInstance());
            }
        });
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        try {
            if (TextUtils.isEmpty(tvPayment.getText())) throw new NullPointerException("the payment date is null");
            Integer.parseInt(tvPayment.getText().toString().trim());
        }catch (Exception e){
            e.printStackTrace();
            showDatePickerDialog(4, Calendar.getInstance());
        }
    }

    public void showDatePickerDialog(int themeResId, Calendar calendar) {
        new DatePickerDialog(getActivity(), themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setPayment(dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void setPayment(int payment) {
        ((AddCardActivity) getActivity()).setCreditCardPayment(payment);
        tvPayment.setText(String.valueOf(payment));
    }
}
