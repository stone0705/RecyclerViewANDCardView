package com.example.stone.recyclerviewandcardview;

import android.app.Activity;
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


public class bigActivity extends Activity {
    RecyclerView recyclerView;
    bigAdapter mAdapter;
    ArrayList<bigItem> datalist;
    Button add, remove, removeAll, change;
    int imageid;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big);
        recyclerView = (RecyclerView)findViewById(R.id.linear_recycler_view);
        datalist = new ArrayList<bigItem>();
        mAdapter = new bigAdapter(datalist,this,this);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
        setListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_big, menu);
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
                mAdapter.additem(new bigItem(imageid,"編號:"+mAdapter.getItemCount(),msg,"隱藏"+mAdapter.getItemCount()));
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
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(count){
                    case(1):{
                        for(int i = datalist.size()-1;i>=0;i--){
                            mAdapter.notifyItemRemoved(i);
                        }
                        GridLayoutManager mGrid = new GridLayoutManager(bigActivity.this,3);
                        recyclerView.setLayoutManager(mGrid);
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
                        StaggeredGridLayoutManager mSta = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(mSta);
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
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(bigActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
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
