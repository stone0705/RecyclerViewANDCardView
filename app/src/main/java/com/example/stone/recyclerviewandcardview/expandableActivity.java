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

public class expandableActivity extends Activity {
    RecyclerView recyclerView;
    expandableAdapter mAdapter;
    ArrayList<Object> datalist;
    int count = 0;
    Button add, remove, removeAll, change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        recyclerView = (RecyclerView)findViewById(R.id.linear_recycler_view);
        datalist = new ArrayList<Object>();
        mAdapter = new expandableAdapter(datalist);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemAnimator ia = new DefaultItemAnimator();
        ia.setAddDuration(200);
        ia.setRemoveDuration(200);
        recyclerView.setItemAnimator(ia);
        add = (Button)findViewById(R.id.add);
        remove = (Button)findViewById(R.id.remove);
        removeAll = (Button)findViewById(R.id.removeAll);
        change = (Button)findViewById(R.id.change);
        setListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expandable, menu);
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
                ArrayList<childItem> child = new ArrayList<childItem>();
                int i = (int)(Math.random()*5);
                switch(i){
                    case(0):{
                        msg = "eee4";
                        child.add(new childItem("qqqq"));
                        child.add(new childItem("qwwqqq"));
                        child.add(new childItem("qqqweqqq"));
                        child.add(new childItem("ccq"));
                        break;
                    }
                    case(1):{
                        msg = "eesdfsfewe2";
                        child.add(new childItem("qqqq"));
                        child.add(new childItem("qwwawrqwrqqq"));
                        break;
                    }
                    case(2):{
                        msg = "eee2";
                        child.add(new childItem("qqqq"));
                        child.add(new childItem("qwwqqq"));
                        break;
                    }
                    case(3):{
                        msg = "eee4";
                        child.add(new childItem("qqqq"));
                        child.add(new childItem("qwwqqq"));
                        child.add(new childItem("qwwqqq"));
                        child.add(new childItem("qwwqqq"));
                        break;
                    }
                    case(4):{
                        msg = "no0";
                        break;
                    }
                    case(5):{
                        msg = "eeasde1";
                        child.add(new childItem("qaaaqqq"));
                        break;
                    }
                }
                mAdapter.additem(new parentItem(child,msg));
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
                        GridLayoutManager mGrid = new GridLayoutManager(expandableActivity.this,3);
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
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(expandableActivity.this);
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
