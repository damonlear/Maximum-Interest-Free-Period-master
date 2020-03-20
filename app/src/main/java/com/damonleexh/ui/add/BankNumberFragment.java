package com.damonleexh.ui.add;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;
import com.damonleexh.bean.BankModel;
import com.damonleexh.widget.BankCardNumEditText;

public class BankNumberFragment extends LazyFragment {

    private BankCardNumEditText edtNumber;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bank_number;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        edtNumber = view.findViewById(R.id.edt_number);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setCardNumber(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        //空的话，才弹出输入框
        if (TextUtils.isEmpty(edtNumber.getText())) {
            edtNumber.setFocusable(true);
            edtNumber.setFocusableInTouchMode(true);
            edtNumber.requestFocus();
            showSoftInputFromWindow(edtNumber);
        }
    }

    private void showSoftInputFromWindow(EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    private void setCardNumber(String number) {
        ((AddCardActivity) getActivity()).setCreditCardNumber(number);
    }

    private void checkCardNumber(){
        String patton = "/^[1-9]\\d{9,29}$/";
    }
}
