package com.damonleexh.ui.add;

import android.view.View;
import android.widget.TextView;

import com.damonleexh.R;
import com.damonleexh.base.LazyFragment;

public class BankPaymentFragment extends LazyFragment {
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
    }
}
