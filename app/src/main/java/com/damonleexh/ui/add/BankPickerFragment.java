package com.damonleexh.ui.add;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;
import com.damonleexh.bean.BankModel;
import com.damonleexh.ui.add.bank.BankPopupWindow;
import com.damonleexh.url.BaseUrl;
import com.damonleexh.util.BankJsonPaser;

import java.io.IOException;
import java.util.List;

public class BankPickerFragment extends LazyFragment {
    List<BankModel> mBankModels;
    private TextView tvBank;
    private TextView tvName;
    private RelativeLayout rlBankPicker;

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
                setPickCard(model);
            }
        });
        rlBankPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.show();
            }
        });
    }

    private void setPickCard(BankModel model) {
        tvBank.setText(model.getBank());
        tvName.setText(model.getName());

        ((AddCardActivity) getActivity()).setCreditCardBank(model.getName());
        ((AddCardActivity) getActivity()).setCreditCardBackground(BaseUrl.getBankIconUrl(model.getBank()));
    }
}
