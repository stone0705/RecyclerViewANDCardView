package com.example.stone.recyclerviewandcardview;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stone on 2015/8/26.
 */
public class LinearAdapter extends RecyclerView.Adapter<LinearViewHolder> {
    ArrayList<LinearItem> datalist = new ArrayList<LinearItem>();
    Context mContext;
    Activity mActivity;
    public LinearAdapter(ArrayList<LinearItem> datalist,Context mContext,Activity mActivity){
        this.datalist = datalist;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }
    @Override
    public LinearViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview1, viewGroup, false);
        LinearViewHolder vh = new LinearViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final LinearViewHolder viewHolder, int i) {
        final LinearItem item = datalist.get(i);
        viewHolder.image.setImageResource(item.imageId);
        viewHolder.text1.setText(item.text1);
        viewHolder.text2.setText(item.text2);
        if(LinearActivity.ishorizon){
            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT,CardView.LayoutParams.MATCH_PARENT);
            params.setMargins(20,20,20,20);
            viewHolder.card.setLayoutParams(params);
        }else{
            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,CardView.LayoutParams.WRAP_CONTENT);
            params.setMargins(20,20,20,20);
            viewHolder.card.setLayoutParams(params);
        }
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("item",item);
                View shareView = viewHolder.card;
                intent.setClass(mContext,CardViewActivity.class);
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView , "card");
                mContext.startActivity(intent, transitionActivityOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void additem(LinearItem item) {
        datalist.add(item);
        //this.notifyItemChanged(datalist.size());
        //this.notifyDataSetChanged();
        this.notifyItemInserted(datalist.size());
    }
    public void removeAll(){
        if(!datalist.isEmpty()){
            for(int i = datalist.size()-1;i>=0;i--){
                datalist.remove(i);
                //this.notifyItemChanged(i);
                //this.notifyDataSetChanged();
                this.notifyItemRemoved(i);
            }
        }
    }
    public void remove(){
        if(this.getItemCount()!=0){
            int p = this.getItemCount()-1;
            double number = Math.random()*p;
            datalist.remove((int)number);
            //this.notifyItemChanged(datalist.size());
            //this.notifyDataSetChanged();
            this.notifyItemRemoved((int)number);
        }
    }
}
class LinearViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView text1;
    TextView text2;
    CardView card;
    public LinearViewHolder(View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.imageView);
        text1 = (TextView)itemView.findViewById(R.id.textView);
        text2 = (TextView)itemView.findViewById(R.id.textView2);
        card = (CardView)itemView.findViewById(R.id.Card);
    }
}
class LinearItem implements Serializable {
    int imageId;
    String text1;
    String text2;
    public LinearItem(int imageId, String text1, String text2){
        this.imageId = imageId;
        this.text1 = text1;
        this.text2 = text2;
    }
}
