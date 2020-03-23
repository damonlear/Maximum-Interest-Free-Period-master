package com.damonleexh.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.damonleexh.Code;
import com.damonleexh.R;
import com.damonleexh.base.SpacesItemDecoration;
import com.damonleexh.bean.CreditCard;
import com.damonleexh.ui.VideoActivity;
import com.damonleexh.ui.add.AddCardActivity;
import com.damonleexh.util.CalculateManager;
import com.damonleexh.util.FileUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CreditCard> mList = new ArrayList<>();
    private CreditCardAdapter creditCardAdapter;
    private RecyclerView mRecyclerView;
    private String mFile = "creditCard.json";

    //查询信用卡账单
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.listview);
        creditCardAdapter = new CreditCardAdapter(this, mList);
        mRecyclerView.setAdapter(creditCardAdapter);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                startActivityForResult(intent, Code.REQUESTCODE_PICKER_CARD);
            }
        });

        new Thread() {
            @Override
            public void run() {
                super.run();
                initData();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        writeFilesTemp(mList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_statement_date:
                Intent intent_statement_date = new Intent(this, VideoActivity.class);
                intent_statement_date.putExtra("uriString", VideoActivity.statement_date);
                startActivity(intent_statement_date);
                break;
            case R.id.menu_interest_free_period:
                Intent intent_interest_free_period = new Intent(this, VideoActivity.class);
                intent_interest_free_period.putExtra("uriString", VideoActivity.interest_free_period);
                startActivity(intent_interest_free_period);
                break;
            case R.id.menu_max_interest_free_period:
                new AlertDialog.Builder(this)
                        .setMessage("最大免息期 = 下月还款日 - 账单日次日")
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .create().show();
                break;
            case R.id.menu_about:
                String message = "开发者：damon_lee@aliyun.com\n" + "项目地址：https://github.com/damonlear/Maximum-Interest-Free-Period-master\n";
                new AlertDialog.Builder(this)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .create().show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == Code.REQUESTCODE_PICKER_CARD) {
                if (resultCode == Code.RESULTCODE_PICKER_CARD) {
                    CreditCard creditCard = (CreditCard) data.getSerializableExtra("creditCard");
                    boolean b = addCreditCard(creditCard);
                    if (b) {
                        refrash();
                    }
                }
            }
        }
    }

    private boolean addCreditCard(CreditCard model) {
        try {
            CreditCard creditCard = new CreditCard(model.getBank(), model.getName(), model.getStatementDate(), model.getPaymentDate());
            int maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
            creditCard.setGracePeriod(maxFreeTime);
            mList.add(creditCard);
            Snackbar.make(mRecyclerView, "添加成功", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initData() {
        List<CreditCard> creditCards = readFilesTemp();
        if (creditCards != null && creditCards.size() > 0) {
            mList.clear();
            mList.addAll(creditCards);
        } else {
            try {
                mList.clear();
                CreditCard creditCard = new CreditCard("ALIPAY", "支付宝", "01", "10");
                int maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("CITIC", "中信银行", "02", "21");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("CMB", "招商银行", "25", "13");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("SPABANK", "平安银行", "20", "08");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("SPDB", "上海浦东发展银行", "13", "02");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        refrash();
    }

    private void refrash() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                creditCardAdapter.notifyDataSetChanged();
                CreditCard max = Collections.max(mList, new Comparator<CreditCard>() {
                    @Override
                    public int compare(CreditCard o1, CreditCard o2) {
                        return o1.getGracePeriod() - o2.getGracePeriod();
                    }
                });
                setTitle(max.getName() + "：" + max.getGracePeriod() + "天");
            }
        });
    }

    private List<CreditCard> readFilesTemp() {
        String temp = FileUtil.read(this, mFile);
        return JSON.parseArray(temp, CreditCard.class);
    }

    private boolean writeFilesTemp(List<CreditCard> list) {
        String temp = JSON.toJSONString(list);
        return FileUtil.write(this, mFile, temp);
    }
}

