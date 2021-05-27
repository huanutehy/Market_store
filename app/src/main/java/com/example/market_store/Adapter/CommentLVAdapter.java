package com.example.market_store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.market_store.Object.Comment;
import com.example.market_store.R;

import java.util.List;

public class CommentLVAdapter extends ArrayAdapter<Comment> {

    public CommentLVAdapter(Context context, int res, List<Comment> item) {
        super(context, res, item);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.custom_cmt, null);
        }
        TextView tvUsername = v.findViewById(R.id.tvUsername_cust_cmt);
        TextView tvTime = v.findViewById(R.id.tvTime_cust_cmt);
        TextView tvComment = v.findViewById(R.id.tvComment);
        RatingBar ratingBar = v.findViewById(R.id.ratingBar_cust_cmt);

        Comment comment = getItem(i);
        if (comment != null) {
            tvUsername.setText(comment.getAccount());
            tvTime.setText(comment.getTime());
            tvComment.setText(comment.getComment());
            ratingBar.setRating((float) comment.getRating());
            ratingBar.setIsIndicator(true);
        }
        return v;
    }
}
