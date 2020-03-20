package com.damonleexh.ui.add;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;
import com.damonleexh.bean.BankModel;
import com.damonleexh.ui.add.bank.BankPickerView;
import com.damonleexh.ui.add.bank.BankPopupWindow;
import com.damonleexh.util.BankJsonPaser;

import java.io.IOException;
import java.util.List;

public class BankNameFragment extends LazyFragment {
    private TextView tvBank;
    private TextView tvName;
    private RelativeLayout rlBankPicker;

    List<BankModel> mBankModels;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bank_picker;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvBank = view.findViewById(R.id.tv_bank_title);
        tvName = view.findViewById(R.id.tv_bank_name);
        rlBankPicker = view.findViewById(R.id.rl_bank_picker);
    }

    @Override
    protected void initData() {
        super.initData();
        try {
            mBankModels = BankJsonPaser.paser(getActivity());
            initPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPopupWindow() {
        final BankPopupWindow popupWindow = new BankPopupWindow(getActivity());
        popupWindow.setData(mBankModels);
        popupWindow.setOnBankSelectListener(new BankPopupWindow.OnBankSelectListener() {
            @Override
            public void onBankSelect(BankModel model) {
                if (model == null) return;
                tvBank.setText(model.getBank());
                tvName.setText(model.getName());
            }
        });
        rlBankPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.show();
            }
        });
    }
}
