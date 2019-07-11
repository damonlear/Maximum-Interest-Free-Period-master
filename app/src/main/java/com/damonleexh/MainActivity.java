package com.damonleexh;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.damonleexh.util.CalculateManager;
import com.damonleexh.util.FileUtil;
import com.damonleexh.widget.CreditCardInputDialog;
import com.damonleexh.widget.ICredieCardListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CreditCard> mList = new ArrayList<>();
    private CreditCardAdapter creditCardAdapter;
    private ListView listview;

    //查询信用卡账单
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);
        creditCardAdapter = new CreditCardAdapter(this, mList);
        listview.setAdapter(creditCardAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreditCardInputDialog dialog = new CreditCardInputDialog();
                dialog.setOnInputListener(new ICredieCardListener() {
                    @Override
                    public void input(String name, String statement, String payment) {
                        addCreditCard(name, statement, payment);
                        refrash();
                    }
                });
                dialog.show(MainActivity.this.getFragmentManager(), "ADD");
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

    private boolean addCreditCard(String bankName, String statement, String paymeny) {
        try {
            CreditCard creditCard = new CreditCard(bankName, statement, paymeny);
            int maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
            creditCard.setGracePeriod(maxFreeTime);
            mList.add(creditCard);
            Snackbar.make(listview, "添加成功", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initData() {
        String read = FileUtil.read(this, getApplication().getPackageName());
        CreditCards creditCards = JSON.parseObject(read, CreditCards.class);
        if (creditCards != null && creditCards.list != null && creditCards.list.size() > 0) {
            mList.clear();
            mList.addAll(creditCards.list);
        } else {
            try {
                mList.clear();
                CreditCard creditCard = new CreditCard("支付宝", "01", "10");
                int maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("中信银行", "02", "21");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("招商银行", "25", "13");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("平安银行", "20", "08");
                maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(creditCard.getStatementDate(), creditCard.getPaymentDate());
                creditCard.setGracePeriod(maxFreeTime);
                mList.add(creditCard);
                creditCard = new CreditCard("浦发银行", "13", "02");
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

    private class CreditCards implements Serializable {
        List<CreditCard> list;
    }
}

