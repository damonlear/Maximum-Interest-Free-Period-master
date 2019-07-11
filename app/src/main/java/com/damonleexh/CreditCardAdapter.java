package com.damonleexh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class CreditCardAdapter extends BaseAdapter {
    private Context mContext;
    private List<CreditCard> mList;

    public CreditCardAdapter(Context mContext, List<CreditCard> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BankViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_credit_card, null);
            holder = new BankViewHolder();
            holder.ivIcon = convertView.findViewById(R.id.iv_icon);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvStatement = convertView.findViewById(R.id.tv_statement);
            holder.tvPayment = convertView.findViewById(R.id.tv_payment);
            holder.tvMax = convertView.findViewById(R.id.tv_max);
            convertView.setTag(holder);
        } else {
            holder = (BankViewHolder) convertView.getTag();
        }
        try {
            holder.ivIcon.setImageResource(R.mipmap.ic_launcher);
            holder.tvName.setText(mList.get(position).getName());
            holder.tvStatement.setText(mList.get(position).getStatementDate());
            holder.tvPayment.setText(mList.get(position).getPaymentDate());
            holder.tvMax.setText(String.valueOf(mList.get(position).getGracePeriod()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
