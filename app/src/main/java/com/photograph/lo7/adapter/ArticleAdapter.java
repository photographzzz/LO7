package com.photograph.lo7.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.ItemArticleBinding;
import com.photograph.lo7.entity.Article;
import com.photograph.lo7.ui.activities.ArticleActivity;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;
    private Context context;
    public ArticleAdapter(Context context,List<Article> articles) {
        this.articles = articles;
        this.context = context ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_article, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemArticleBinding binding = holder.binding;
        binding.setArticle(articles.get(position));
        binding.executePendingBindings();
        binding.getRoot().setOnClickListener(v -> {
            Article article = articles.get(position);
            Intent intent = new Intent(context, ArticleActivity.class);
            intent.putExtra("id", article.getId());
            intent.putExtra("title", article.getTitle());
            intent.putExtra("preview", article.getPreview());
            intent.putExtra("likes", article.getLikes());
            intent.putExtra("stars", article.getStars());
            intent.putExtra("authorId", article.getAuthorId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemArticleBinding binding;

        public ViewHolder(ItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
