package com.example.stone.recyclerviewandcardview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by stone on 2015/8/26.
 */
public class ItemAnimator extends RecyclerView.ItemAnimator {
    @Override
    public void runPendingAnimations() {

    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {

        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
