package com.example.stone.recyclerviewandcardview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by stone on 2015/9/2.
 */
/**
 * An implementation of {@link ItemTouchHelper.Callback} that enables basic drag & drop and
 * swipe-to-dismiss. Drag events are automatically started by an item long-press.
 * @author Paul Burke (ipaulpro)
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final dragdropAdapter mAdapter;

    public SimpleItemTouchHelperCallback(dragdropAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager){
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = 0;
        }else{
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition() ,recyclerView);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}
