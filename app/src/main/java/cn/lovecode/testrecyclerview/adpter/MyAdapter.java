package cn.lovecode.testrecyclerview.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.lovecode.testrecyclerview.R;
import cn.lovecode.testrecyclerview.animators.AnimateViewHolder;
import cn.lovecode.testrecyclerview.bean.User;

/**
 * Created by Bob on 2016/7/3.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<User> users = new ArrayList<>();
    public MyAdapter(Context mCon, List<User> users) {
        this.users = users;
    }

    //点击监听事件
    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    };
    private OnRecyclerViewItemClickListener listener;
    //设置监听器
    public void setItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void remove(int position) {
        users.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String text, int position) {
        User user = new User();
        users.add(position, user);
        notifyItemInserted(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                R.layout.item_user, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = users.get(position);
        Uri uri = Uri.parse(user.getUrl());
        holder.ivUrl.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView ivUrl;
        /**
         * @Description: TODO
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            ivUrl = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view);
        }
    }static class MyViewHolder extends AnimateViewHolder {
        private SimpleDraweeView ivUrl;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivUrl = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view);
        }

        @Override
        public void animateRemoveImpl(ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void preAnimateAddImpl() {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void animateAddImpl(ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }
}
