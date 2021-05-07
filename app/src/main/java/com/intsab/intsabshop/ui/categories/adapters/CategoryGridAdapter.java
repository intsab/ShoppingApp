package com.intsab.intsabshop.ui.categories.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class CategoryGridAdapter extends RecyclerView.Adapter<CategoryGridAdapter.ViewHolder> {
    private List<String> listData;
    private AppUtils.AdapterClickListener listener;

    public CategoryGridAdapter(List<String> listData, AppUtils.AdapterClickListener listener) {
        this.listData = listData;
        this.listener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.category_grid_item_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.mainView.setBackgroundColor(color);

        final String title = listData.get(position);
        holder.textView.setText(title);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicked(title);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ConstraintLayout mainView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.tv_cat_name);
            mainView = (ConstraintLayout) itemView.findViewById(R.id.bg_category);
        }
    }
}

