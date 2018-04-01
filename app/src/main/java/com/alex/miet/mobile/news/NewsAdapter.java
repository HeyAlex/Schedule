package com.alex.miet.mobile.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.miet.mobile.entities.NewsItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.alex.miet.mobile.R;

import static com.alex.miet.mobile.util.Preconditions.checkNotNull;

/**
 * Adapter that used in {@link NewsFragment}
 */
/*package*/ class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<NewsItem> items = new ArrayList<>();
    private Context context;
    private OnNewsClickedListener onNewsClickedListener;

    /*package*/ NewsAdapter(OnNewsClickedListener onNewsClickedListener) {
        this.onNewsClickedListener = checkNotNull(onNewsClickedListener);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.context = recyclerView.getContext();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        context = null;
    }

    /*package*/ void setItems(List<NewsItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    /*package*/ interface OnNewsClickedListener {
        void onNewsClicked(NewsItem newsModel);
    }

    /*package*/ class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsDate)
        TextView date;

        @BindView(R.id.newsDescription)
        TextView desciption;

        @BindView(R.id.newsImage)
        ImageView image;

        @BindView(R.id.newsTitle)
        TextView title;

        /*package*/ NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final NewsItem newsModel) {
            Glide.with(context).load(newsModel.getImageUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(image);
            title.setText(newsModel.getTitle());
            desciption.setText(newsModel.getDescription());
            date.setText(newsModel.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNewsClickedListener.onNewsClicked(newsModel);
                }
            });
        }
    }
}
