package com.intsab.intsabshop.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.Utils.CartUtils;
import com.intsab.intsabshop.data.Models.ProductItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
    private List<ProductItem> listData;
    private AppUtils.AdapterClickListener listener;
    private AppUtils.AddToCartClickListener addToCartClickListener;
    private Context context;

    public ItemsListAdapter(List<ProductItem> listData, Context context, AppUtils.AdapterClickListener listener, AppUtils.AddToCartClickListener cartClickListener) {
        this.listData = listData;
        this.listener = listener;
        this.context = context;
        addToCartClickListener = cartClickListener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProductItem item = listData.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvPrice.setText("Price: " + item.getPrice());
        AppUtils.loadImage(context, item.getImage(), holder.ivImage);
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicked(item.getId());
            }
        });

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCartClickListener.addToCartListener(CartUtils.productItemToCart(item));
                Toast.makeText(context, context.getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show();
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
        public ImageView ivImage;
        public Button addToCart;
        public ConstraintLayout mainView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.product_image);
            this.tvTitle = (TextView) itemView.findViewById(R.id.title);
            this.tvDescription = (TextView) itemView.findViewById(R.id.description);
            this.tvPrice = (TextView) itemView.findViewById(R.id.price);
            this.addToCart = (Button) itemView.findViewById(R.id.add_to_cart);
            mainView = (ConstraintLayout) itemView.findViewById(R.id.main_item);
        }
    }
}

