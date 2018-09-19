package com.mercari.sundararaghavan.myapplication.products.view;

import android.arch.lifecycle.LifecycleOwner;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mercari.sundararaghavan.myapplication.R;
import com.mercari.sundararaghavan.myapplication.products.model.Repo;
import com.mercari.sundararaghavan.myapplication.products.viewmodel.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {

    private final List<Repo> data = new ArrayList<>();

    RepoListAdapter(ProductsViewModel viewModel, String category, LifecycleOwner lifecycleOwner) {
        viewModel.getRepos(category).observe(lifecycleOwner, repos -> {
            if(repos==null){
                data.clear();
                notifyDataSetChanged();
                return;
            }
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RepoDiffCallback(data, repos));
            data.clear();
            data.addAll(repos);
            diffResult.dispatchUpdatesTo(this);
        });
        setHasStableIds(true);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        private final Drawable drawable;
        private final RequestOptions options;
        @BindView(R.id.name_tv)
        TextView prodNameTextView;
        @BindView(R.id.like_cnt_tv)
        TextView likesTextView;
        @BindView(R.id.comment_cnt_tv)
        TextView commentsTextView;
        @BindView(R.id.price_tv)
        TextView priceTextView;
        @BindView(R.id.product_img)
        ImageView productImage;
        @BindView(R.id.sold_out_img)
        ImageView soldOutImage;


        private Repo repo;

        RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_image_24dp);
            options = new RequestOptions();
            options.centerCrop();
            options.placeholder(drawable);

        }

        void bind(Repo repo) {
            this.repo = repo;
            prodNameTextView.setText(repo.name());
            likesTextView.setText(String.valueOf(repo.num_likes()));
            commentsTextView.setText(String.valueOf(repo.num_comments()));
            priceTextView.setText(String.valueOf(repo.price()));
            if(repo.status().trim().equals("sold_out")) {
                soldOutImage.setVisibility(View.VISIBLE);
            }
            else{
                soldOutImage.setVisibility(View.GONE);
            }
            Glide.with(productImage.getContext()).load(repo.photo()).
                    apply(options).into(productImage);

        }
    }
}
