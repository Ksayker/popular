package ksayker.popular.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

    private AddToFavoriteListener favoriteListener;
    private ArticleClickListener articleClickListener;

    public ListAdapter(Context context, List<Article> items, AddToFavoriteListener favoriteListener,
                       ArticleClickListener articleClickListener) {
        this.context = context;
        this.items = items;
        this.favoriteListener = favoriteListener;
        this.articleClickListener = articleClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        Article article = items.get(i);

        viewHolder.tvTitle.setText(article.getTitle());

        viewHolder.cbFavorite.setOnCheckedChangeListener(null);
        viewHolder.cbFavorite.setChecked(article.isFavorite());
        viewHolder.cbFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                favoriteListener.addToFavorite(article);
            } else {
                favoriteListener.removeFromFavorite(article);
            }
        });

        viewHolder.root.setOnClickListener(v -> articleClickListener.onArticleClick(article));
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
        View root;
        TextView tvTitle;
        CheckBox cbFavorite;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            tvTitle = itemView.findViewById(R.id.tv_item_text);
            cbFavorite = itemView.findViewById(R.id.cb_item_favorite);
        }
    }

    public interface AddToFavoriteListener {
        void addToFavorite(Article article);
        void removeFromFavorite(Article article);
    }

    public interface ArticleClickListener {
        void onArticleClick(Article article);
    }
}
