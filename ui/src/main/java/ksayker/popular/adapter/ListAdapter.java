package ksayker.popular.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ksayker.domain.entities.Article;
import ksayker.popular.R;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {
    private Context context;
    private List<Article> items;

    public ListAdapter(Context context, List<Article> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        viewHolder.textView.setText(items.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Article> articles) {
        items = articles;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_text);
        }
    }
}
