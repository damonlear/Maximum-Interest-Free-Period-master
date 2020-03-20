package com.damonleexh.ui.add;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.damonleexh.R;
import com.damonleexh.base.ViewPagerAdapter;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.ArrayList;
import java.util.List;

public class AddCardActivity extends AppCompatActivity {

    private TextView tvStepTitle;
    private AppCompatButton button;
    private ViewPager2 viewPager2;
    private String[] titleArrays = {"银行", "银行卡号", "账单日", "还款日"};
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        button = findViewById(R.id.button);
        tvStepTitle = findViewById(R.id.tv_step_title);
        viewPager2 = findViewById(R.id.view_pager2);

        mFragments.add(new BankPickerFragment());
        mFragments.add(new BankNumberFragment());
        mFragments.add(new BankPaymentFragment());
        mFragments.add(new BankStatementFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, titleArrays, mFragments);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setOffscreenPageLimit(titleArrays.length);
        //mRecyclerView.getLayoutManager().setItemPrefetchEnabled(false);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager2.getCurrentItem() == titleArrays.length - 1) {
                    Toast.makeText(AddCardActivity.this, "FINAL", Toast.LENGTH_SHORT).show();
                } else {
                    setPager(viewPager2.getCurrentItem() + 1);
                }
            }
        });
    }

    private void setPager(int position) {
        tvStepTitle.setText(titleArrays[position]);
        viewPager2.setCurrentItem(position);
    }

    public void setCreditCardBank(String name) {
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(name);
    }

    public void setCreditCardNumber(String number) {
        TextView tvNumber = findViewById(R.id.tv_number);
        if (TextUtils.isEmpty(number)) {
            tvNumber.setText("**** **** **** ****");
        } else {
            tvNumber.setText(number);
        }
    }

    public void setCreditCardPayment(String payment) {
        TextView tvPayment = findViewById(R.id.tv_payment);
        tvPayment.setText(payment);
    }

    public void setCreditCardStatement(String statement) {
        TextView tvStatement = findViewById(R.id.tv_statement);
        tvStatement.setText(statement);
    }

    public void setCreditCardBackground(String url) {
        CardView cardView = findViewById(R.id.card_view);
        ImageView ivIcon = findViewById(R.id.iv_icon);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.loading_spinner)
                .centerInside()
                .error(R.drawable.loading_error);
        Glide.with(this)
                .load(url)
                .apply(options)
                .listener(GlidePalette.with(url)
                        .use(GlidePalette.Profile.VIBRANT)
                        .intoBackground(cardView, GlidePalette.Swatch.RGB)
                        .crossfade(true)
                )
                .into(ivIcon);
    }
}
