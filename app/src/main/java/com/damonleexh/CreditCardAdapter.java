package com.damonleexh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.github.florent37.glidepalette.GlidePalette;

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
    public void onBindViewHolder(@NonNull final BankViewHolder holder, int position) {
        if (holder == null) return;
        try {
            CreditCard creditCard = mList.get(position);
            holder.ivIcon.setImageResource(R.drawable.loading_spinner);
            holder.tvName.setText(creditCard.getName());
            holder.tvStatement.setText(creditCard.getStatementDate());
            holder.tvPayment.setText(creditCard.getPaymentDate());
            holder.tvMax.setText(String.valueOf(creditCard.getGracePeriod()));

            String url = "https://apimg.alipay.com/combo.png?d=cashier&t=" + creditCard.getBank();
            if (creditCard.getBank().contentEquals("ALIPAY")){
                url = "https://bkimg.cdn.bcebos.com/pic/d788d43f8794a4c22b73bf7d00f41bd5ad6e3917?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U4MA==,xp_5,yp_5";
            }
            Glide.with(mContext)
                    .load(url)
                    .apply(options)
                    .listener(GlidePalette.with(url)
                            .use(GlidePalette.Profile.VIBRANT)
                            .intoBackground(holder.cardView, GlidePalette.Swatch.RGB)
                            .crossfade(true)
                    )
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
