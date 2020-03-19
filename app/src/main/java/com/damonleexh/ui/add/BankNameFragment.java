package com.damonleexh.ui.add;

import android.view.View;
import android.widget.TextView;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;
import com.damonleexh.bean.Bank;
import com.damonleexh.bean.CreditCard;
import com.damonleexh.util.BankJsonPaser;

import java.io.IOException;
import java.util.List;

public class BankNameFragment extends LazyFragment {
    TextView textView;

    @Override
    protected int getContentViewId() {
        return R.layout.item_page_textview;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        textView = view.findViewById(R.id.tvTitle);
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("当前时间：" + System.currentTimeMillis());

        try {
            List<Bank> banks = BankJsonPaser.paser(getActivity());
            textView.setText(banks.get(0).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
