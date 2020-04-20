package com.photograph.lo7.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.AppHolder;
import com.photograph.lo7.R;
import com.photograph.lo7.controller.ArticleController;
import com.photograph.lo7.databinding.ItemArticleBinding;
import com.photograph.lo7.entity.Article;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.ui.activities.ArticleActivity;
import com.rxjava.rxlife.RxLife;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;
    private Context context;

    public ArticleAdapter(Context context) {
        this.context = context;
    }

    public ArticleAdapter(Context context, List<Article> articles) {
        this.articles = articles;
        this.context = context ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        ItemArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_article, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemArticleBinding articleBinding = holder.binding;
        articleBinding.setArticle(articles.get(position));
        articleBinding.executePendingBindings();
        articleBinding.getRoot().setOnClickListener(v -> {
            Article article = articles.get(position);
            AppHolder.currentArticle = article;
            ArticleController.INSTANCE.visitArticle(article.getId())
                    .as(RxLife.asOnMain(holder.itemView.getRootView()))
                    .subscribe(success -> {
                        article.setVisitCount(article.getVisitCount() + 1);
                    }, (OnError) error -> error.show(error.getErrorMsg()));
            Intent intent = new Intent(context, ArticleActivity.class);
            context.startActivity(intent);
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemArticleBinding binding;

        public ViewHolder(ItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();

    }



    public void loadMore(List<Article> articles) {
        int end = articles.size();
        this.articles.addAll(articles);
        //notifyItemInserted(end);
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
