package com.damonleexh.ui.add;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;

import java.util.Calendar;

public class BankStatementFragment extends LazyFragment {
    private TextView tvTitle;
    private TextView tvStatementTitle;
    private TextView tvStatement;
    private RelativeLayout rlBankPicker;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bank_calendar;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvTitle = view.findViewById(R.id.tv_calendar_title);
        tvStatementTitle = view.findViewById(R.id.tv_bank_title);
        tvStatement  = view.findViewById(R.id.tv_bank_name);
        rlBankPicker  = view.findViewById(R.id.rl_bank_picker);
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText(R.string.pick_statement);
        tvStatementTitle.setText(R.string.statement_t);
        tvStatement.setText(R.string.pick_statement);
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
            if (TextUtils.isEmpty(tvStatement.getText())) throw new NullPointerException("the statement date is null");
            Integer.parseInt(tvStatement.getText().toString().trim());
        }catch (Exception e){
            e.printStackTrace();
            showDatePickerDialog(4, Calendar.getInstance());
        }
    }

    public void showDatePickerDialog(int themeResId, Calendar calendar) {
        new DatePickerDialog(getActivity(), themeResId, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setStatement(dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void setStatement(int statement) {
        ((AddCardActivity) getActivity()).setCreditCardStatement(statement);
        tvStatement.setText(String.valueOf(statement));
    }
}
