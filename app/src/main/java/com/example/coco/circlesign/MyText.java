package com.example.coco.circlesign;

import android.animation.AnimatorSet;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by coco on 2017/12/7.
 */

public class MyText {
    private TextView mTv;
    private ImageView mImg;

    private Integer moveX,moveY;
    private Integer randomX,randomY;
    private AnimatorSet animatorSet;
    private boolean clickable =false;

    public TextView getmTv() {
        return mTv;
    }

    public void setmTv(TextView mTv) {
        this.mTv = mTv;
    }

    public ImageView getmImg() {
        return mImg;
    }

    public void setmImg(ImageView mImg) {
        this.mImg = mImg;
    }

    public Integer getMoveX() {
        return moveX;
    }

    public void setMoveX(Integer moveX) {
        this.moveX = moveX;
    }

    public Integer getMoveY() {
        return moveY;
    }

    public void setMoveY(Integer moveY) {
        this.moveY = moveY;
    }

    public Integer getRandomX() {
        return randomX;
    }

    public void setRandomX(Integer randomX) {
        this.randomX = randomX;
    }

    public Integer getRandomY() {
        return randomY;
    }

    public void setRandomY(Integer randomY) {
        this.randomY = randomY;
    }

    public AnimatorSet getAnimatorSet() {
        return animatorSet;
    }

    public void setAnimatorSet(AnimatorSet animatorSet) {
        this.animatorSet = animatorSet;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public Integer getColorID() {
        return colorID;
    }

    public void setColorID(Integer colorID) {
        this.colorID = colorID;
    }

    private Integer colorID;

    public MyText(TextView mTv, ImageView mImg, Integer colorID) {
        this.mTv = mTv;
        this.mImg = mImg;
        this.colorID = colorID;
    }
}
