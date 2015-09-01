package com.example.stone.recyclerviewandcardview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class LinearActivity extends Activity {
    RecyclerView recyclerView;
    LinearAdapter mAdapter;
    ArrayList<LinearItem> datalist;
    Button add, remove, removeAll, change,X;
    static boolean ishorizon = false;
    int imageid;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        recyclerView = (RecyclerView)findViewById(R.id.linear_recycler_view);
        datalist = new ArrayList<LinearItem>();
        mAdapter = new LinearAdapter(datalist,this,this);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ishorizon = false;
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemAnimator ia = new DefaultItemAnimator();
        ia.setAddDuration(200);
        ia.setRemoveDuration(200);
        recyclerView.setItemAnimator(ia);
        imageid = R.drawable.ie;
        add = (Button)findViewById(R.id.add);
        remove = (Button)findViewById(R.id.remove);
        removeAll = (Button)findViewById(R.id.removeAll);
        change = (Button)findViewById(R.id.change);
        X = (Button)findViewById(R.id.X);
        setListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_linear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setListener(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                int i = (int)(Math.random()*5);
                switch(i){
                    case(0):{
                        msg = "dasdasdsada";
                        break;
                    }
                    case(1):{
                        msg = "asdawdnqownwoiefnwiuofen";
                        break;
                    }
                    case(2):{
                        msg = "ekjfnwjfn wpoficwoiefnwoif3oiurhn2oiefwfwe";
                        break;
                    }
                    case(3):{
                        msg = "ekjfnwjfasdasfmwgn wpoficnwoiefnwoiepfcnwoiefnwoif3oiurhn2oiefwfwe";
                        break;
                    }
                    case(4):{
                        msg = "ekjfnsdf wkjefbnwiuewenoifnweiofnwrgioewnrgioengn wpoficnwoiefnwoiepfcnwoiefnwoif3oiurhn2oiefwfwe";
                        break;
                    }
                    case(5):{
                        msg = "ekjfnsasldmnqwoiefnbweiubfg3784gbf83474bf7834frf234f3df wkjefbnwiuefbweuifv bwouibviwfvwerfvwjfasdasfmwenoifnweiofnwrgioewnrgioengn wpoficnwoiefnwoiepfcnwoiefnwoif3oiurhn2oiefwfwe";
                        break;
                    }
                }
                mAdapter.additem(new LinearItem(imageid,"編號:"+mAdapter.getItemCount(),msg));
                recyclerView.smoothScrollToPosition(mAdapter.getItemCount());
            }
        });
        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeAll();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.remove();
            }
        });
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count){
                    case(1):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        if(ishorizon){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LinearActivity.this,LinearLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        }else{
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LinearActivity.this,LinearLayoutManager.HORIZONTAL,false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        }
                        ishorizon = !ishorizon;
                        for(int i = 0;i<=datalist.size()-1;i++){
                            mAdapter.notifyItemInserted(i);
                        }
                        break;
                    }
                    case(2):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        if(ishorizon){
                            GridLayoutManager mGrid = new GridLayoutManager(LinearActivity.this,3);
                            mGrid.setOrientation(GridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(mGrid);
                        }else{
                            GridLayoutManager mGrid = new GridLayoutManager(LinearActivity.this,3);
                            mGrid.setOrientation(GridLayoutManager.HORIZONTAL);
                            recyclerView.setLayoutManager(mGrid);
                        }
                        ishorizon = !ishorizon;
                        for(int i = 0;i<=datalist.size()-1;i++){
                            mAdapter.notifyItemInserted(i);
                        }
                        break;
                    }
                    case(3):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        if(ishorizon){
                            StaggeredGridLayoutManager mSta = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(mSta);
                        }else{
                            StaggeredGridLayoutManager mSta = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
                            recyclerView.setLayoutManager(mSta);
                        }
                        ishorizon = !ishorizon;
                        for(int i = 0;i<=datalist.size()-1;i++){
                            mAdapter.notifyItemInserted(i);
                        }
                        break;
                    }
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(count){
                    case(1):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        if(!ishorizon){
                            GridLayoutManager mGrid = new GridLayoutManager(LinearActivity.this,3);
                            mGrid.setOrientation(GridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(mGrid);
                        }else{
                            GridLayoutManager mGrid = new GridLayoutManager(LinearActivity.this,3);
                            mGrid.setOrientation(GridLayoutManager.HORIZONTAL);
                            recyclerView.setLayoutManager(mGrid);
                        }
                        for(int i = 0;i<=datalist.size()-1;i++){
                            mAdapter.notifyItemInserted(i);
                        }
                        count++;
                        break;
                    }
                    case(2):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        if(!ishorizon){
                            StaggeredGridLayoutManager mSta = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(mSta);
                        }else{
                            StaggeredGridLayoutManager mSta = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
                            recyclerView.setLayoutManager(mSta);
                        }
                        for(int i = 0;i<=datalist.size()-1;i++){
                            mAdapter.notifyItemInserted(i);
                        }
                        count++;
                        break;
                    }
                    case(3):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        if(!ishorizon){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LinearActivity.this,LinearLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        }else{
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LinearActivity.this,LinearLayoutManager.HORIZONTAL,false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        }
                        count = 1;
                        for(int i = 0;i<=datalist.size()-1;i++){
                            mAdapter.notifyItemInserted(i);
                        }
                        break;
                    }
                }
            }
        });
    }
}
