package com.damonleexh.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.damonleexh.R;

public class BankViewHolder extends RecyclerView.ViewHolder {


    TextView tvName;
    TextView tvPayment;
    TextView tvStatement;
    TextView tvMax;
    ImageView ivIcon;
    CardView cardView;

    public BankViewHolder(@NonNull View convertView) {
        super(convertView);
        cardView = convertView.findViewById(R.id.card_view);
        ivIcon = convertView.findViewById(R.id.iv_icon);
        tvName = convertView.findViewById(R.id.tv_name);
        tvStatement = convertView.findViewById(R.id.tv_statement);
        tvPayment = convertView.findViewById(R.id.tv_payment);
        tvMax = convertView.findViewById(R.id.tv_max);
    }
}
