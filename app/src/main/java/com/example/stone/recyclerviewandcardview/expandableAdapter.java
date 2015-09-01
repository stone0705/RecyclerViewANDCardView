package com.example.stone.recyclerviewandcardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by stone on 2015/9/1.
 */
public class expandableAdapter extends RecyclerView.Adapter {
    ArrayList<Object> dataList;
    final int TYPE_CHILD = 1;
    final int TYPE_PARENT = 0;
    LayoutInflater LI;
    public expandableAdapter(ArrayList<Object> dataList){
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_PARENT){
            return onCreateParentViewHolder(parent);
        }else{
            return onCreateChildViewHolder(parent);
        }
    }

    public parentViewHolder onCreateParentViewHolder(ViewGroup viewGroup){
        View v = LI.from(viewGroup.getContext()).inflate(R.layout.parent,viewGroup,false);
        parentViewHolder vh = new parentViewHolder(v);
        return vh;
    }

    public childViewHolder onCreateChildViewHolder(ViewGroup viewGroup){
        View v = LI.from(viewGroup.getContext()).inflate(R.layout.child,viewGroup,false);
        childViewHolder vh = new childViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(dataList.get(position) instanceof parentItem){
            onBindParentViewHolder((parentViewHolder)holder,position, dataList.get(position));
        }else{
            onBindChildViewHolder((childViewHolder)holder,position,dataList.get(position));
        }
    }

    public void onBindParentViewHolder(final parentViewHolder vh, final int position,Object parentitem){
        final parentItem item = (parentItem)parentitem;
        vh.textview1.setText(item.text1);
        vh.button.setText("展開");
        vh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(item,vh.getPosition());
            }
        });
    }

    public void onBindChildViewHolder(childViewHolder vh,int position,Object childitem){
        childItem item = (childItem)childitem;
        vh.textView1.setText(item.text1);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position) instanceof parentItem){
            return TYPE_PARENT;
        }else{
            return TYPE_CHILD;
        }
    }
    public void expand(parentItem item,int position){
        if(item.childList.isEmpty() || item.childList == null){
            return;
        }else{
            int count = item.childList.size();
            if(item.isexapand){
                item.isexapand = false;
                for(int i = count - 1;i >= 0;i--){
                    dataList.remove(position + i + 1);
                    notifyItemRemoved(position + i + 1);
                }
            }else{
                item.isexapand = true;
                for(int i = 0;i<count;i++){
                    dataList.add(position + i + 1,item.childList.get(i));
                    notifyItemInserted(position + i + 1);
                }
            }
        }
    }
    public void additem(parentItem item) {
        dataList.add(item);
        //this.notifyItemChanged(datalist.size());
        //this.notifyDataSetChanged();
        this.notifyItemInserted(dataList.size());
    }
    public void removeAll(){
        if(!dataList.isEmpty()){
            for(int i = dataList.size()-1;i>=0;i--){
                dataList.remove(i);
                //this.notifyItemChanged(i);
                //this.notifyDataSetChanged();
                this.notifyItemRemoved(i);
            }
        }
    }
    public void remove(){
        if(this.getItemCount()!=0){
            ArrayList<Integer> position = new ArrayList<Integer>();
            for(int i = 0;i<getItemCount();i++){
                if(dataList.get(i) instanceof parentItem){
                    position.add(i);
                }
            }
            double number = Math.random() * position.size();
            int i = position.get((int)number);
            //this.notifyItemChanged(datalist.size());
            //this.notifyDataSetChanged();
            parentItem item = (parentItem)dataList.get(position.get((int)number));
            if(item.isexapand){
                expand(item,i);
            }
            dataList.remove(i);
            this.notifyItemRemoved(i);
        }
    }
}
class parentViewHolder extends RecyclerView.ViewHolder{
    TextView textview1;
    Button button;
    public parentViewHolder(View itemView) {
        super(itemView);
        textview1 = (TextView)itemView.findViewById(R.id.parentText);
        button = (Button)itemView.findViewById(R.id.parentbutton);

    }
}
class childViewHolder extends RecyclerView.ViewHolder{
    TextView textView1;
    public childViewHolder(View itemView) {
        super(itemView);
        textView1 = (TextView)itemView.findViewById(R.id.chileText);
    }
}
class parentItem {
    ArrayList<childItem> childList;
    String text1;
    boolean isexapand = false;
    public parentItem(ArrayList<childItem> childList,String text1){
        this.childList = childList;
        this.text1 = text1;
    }
}
class childItem {
    String text1;
    public childItem(String text1){
        this.text1 = text1;
    }
}
