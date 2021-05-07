package com.intsab.intsabshop.ui.cart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.data.Models.CartItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartItem> listData;
    private AppUtils.CartQuantityClickListener cartButtonsListeners;
    private Context context;

    public CartAdapter(List<CartItem> listData, Context context, AppUtils.CartQuantityClickListener cartClickListener) {
        this.listData = listData;
        this.context = context;
        cartButtonsListeners = cartClickListener;
    }

    @NotNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cart_list_item, parent, false);
        return new CartAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {

        final CartItem item = listData.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvPrice.setText("Price: " + item.getPrice());
        holder.tvQty.setText(" " + item.getQuantity());
        AppUtils.loadImage(context, item.getImage(), holder.ivImage);

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = item.getQuantity();
                qty = qty - 1;
                if (qty < 1) {
                    qty = 1;
                }
                item.setQuantity(qty);
                cartButtonsListeners.minusClicked(item);
                notifyItemChanged(position);
            }
        });
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = item.getQuantity();
                qty = qty + 1;
                item.setQuantity(qty);
                cartButtonsListeners.plusClicked(item);
                notifyItemChanged(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDescription;
        public TextView tvPrice;
        public TextView tvQty;
        public ImageView ivImage;
        public TextView minusButton;
        public TextView plusButton;
        public ConstraintLayout mainView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.product_image);
            this.tvTitle = (TextView) itemView.findViewById(R.id.title);
            this.tvDescription = (TextView) itemView.findViewById(R.id.description);
            this.tvPrice = (TextView) itemView.findViewById(R.id.price);
            this.tvQty = (TextView) itemView.findViewById(R.id.tv_qty);
            this.minusButton = (TextView) itemView.findViewById(R.id.minusButton);
            this.plusButton = (TextView) itemView.findViewById(R.id.plusButton);
            mainView = (ConstraintLayout) itemView.findViewById(R.id.main_item);
        }
    }
}

