package com.example.stone.recyclerviewandcardview;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stone on 2015/8/26.
 */
public class bigAdapter extends RecyclerView.Adapter<bigHolder> {
    ArrayList<bigItem> datalist = new ArrayList<bigItem>();
    Context mContext;
    Activity mActivity;
    public bigAdapter(ArrayList<bigItem> datalist, Context mContext, Activity mActivity){
        this.datalist = datalist;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }
    @Override
    public bigHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview2, viewGroup, false);
        bigHolder vh = new bigHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final bigHolder viewHolder, int i) {
        final bigItem item = datalist.get(i);
        viewHolder.image.setImageResource(item.imageId);
        viewHolder.text1.setText(item.text1);
        viewHolder.text2.setText(item.text2);
        viewHolder.text3.setText(item.text3);
        CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,20,20,20);
        viewHolder.card.setContentPadding(100,100,100,100);
        viewHolder.card.setLayoutParams(params);
        if(item.isclick){
            viewHolder.text3.setVisibility(View.VISIBLE);
        }else{
            viewHolder.text3.setVisibility(View.GONE);
        }
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the originalHeight is 0 then find the height of the View being used
                // This would be the height of the cardview
                if (item.originalHeight == 0) {
                    item.originalHeight = viewHolder.card.getHeight();
                    item.textviewHeight = item.originalHeight/2;
                    System.out.println(viewHolder.card.getHeight()+"    "+viewHolder.text3.getHeight());
                }

                // Declare a ValueAnimator object
                ValueAnimator valueAnimator;
                if (!item.isclick) {

                    item.isclick= true;
                    valueAnimator = ValueAnimator.ofInt(item.originalHeight, item.originalHeight + item.textviewHeight); // These values in this method can be changed to expand however much you like
                    Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                    a.setDuration(200);
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            viewHolder.text3.setVisibility(View.VISIBLE);
                            viewHolder.text3.setEnabled(true);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    // Set the animation on the custom view
                    viewHolder.text3.startAnimation(a);
                } else {
                    viewHolder.text3.setVisibility(View.GONE);
                    viewHolder.text3.setEnabled(false);
                    item.isclick = false;
                    valueAnimator = ValueAnimator.ofInt(item.originalHeight + item.textviewHeight, item.originalHeight);
                }
                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        viewHolder.card.getLayoutParams().height = value.intValue();
                        viewHolder.card.requestLayout();
                    }
                });


                valueAnimator.start();

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void additem(bigItem item) {
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

class bigHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView text1;
    TextView text2;
    TextView text3;
    CardView card;
    LinearLayout linear;

    public bigHolder(View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.imageView);
        text1 = (TextView)itemView.findViewById(R.id.textView);
        text2 = (TextView)itemView.findViewById(R.id.textView2);
        text3 = (TextView)itemView.findViewById(R.id.hidden);
        card = (CardView)itemView.findViewById(R.id.Card);
        linear = (LinearLayout)itemView.findViewById(R.id.linear);
    }
}

class bigItem implements Serializable {
    int imageId;
    String text1;
    String text2;
    String text3;
    int originalHeight = 0;
    int textviewHeight = 0;
    boolean isclick = false;
    public bigItem(int imageId, String text1, String text2, String text3){
        this.imageId = imageId;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }
}
