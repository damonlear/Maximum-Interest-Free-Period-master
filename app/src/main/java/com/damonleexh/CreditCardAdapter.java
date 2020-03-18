package com.damonleexh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<BankViewHolder> {
    private Context mContext;
    private List<CreditCard> mList;
    private LayoutInflater mInflater;
    private RequestOptions options = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.loading_spinner)
            .centerInside()
            .error(R.drawable.loading_error);

    public CreditCardAdapter(Context mContext, List<CreditCard> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_credit_card, null);
        return new BankViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, int position) {
        try {
            CreditCard creditCard = mList.get(position);
            holder.ivIcon.setImageResource(R.drawable.loading_spinner);
            holder.tvName.setText(creditCard.getName());
            holder.tvStatement.setText(creditCard.getStatementDate());
            holder.tvPayment.setText(creditCard.getPaymentDate());
            holder.tvMax.setText(String.valueOf(creditCard.getGracePeriod()));

            Glide.with(mContext)
                    .load("https://apimg.alipay.com/combo.png?d=cashier&t=CCB")
                    .apply(options)
                    .into(holder.ivIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
