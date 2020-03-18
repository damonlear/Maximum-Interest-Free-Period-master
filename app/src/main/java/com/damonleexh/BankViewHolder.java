package com.damonleexh;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BankViewHolder extends RecyclerView.ViewHolder {


    TextView tvName;
    TextView tvPayment;
    TextView tvStatement;
    TextView tvMax;
    ImageView ivIcon;

    public BankViewHolder(@NonNull View convertView) {
        super(convertView);
        ivIcon = convertView.findViewById(R.id.iv_icon);
        tvName = convertView.findViewById(R.id.tv_name);
        tvStatement = convertView.findViewById(R.id.tv_statement);
        tvPayment = convertView.findViewById(R.id.tv_payment);
        tvMax = convertView.findViewById(R.id.tv_max);
    }
}
