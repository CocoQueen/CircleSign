package com.example.coco.circlesign;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mTv_Switcher)
    TextSwitcher mTvSw;
    @BindView(R.id.mImg_sw)
    ImageSwitcher mImgSw;
    @BindView(R.id.iv_sign_one)
    ImageView ivSignOne;
    @BindView(R.id.tv_integral_one)
    TextView tvIntegralOne;
    @BindView(R.id.iv_sign_two)
    ImageView ivSignTwo;
    @BindView(R.id.tv_integral_two)
    TextView tvIntegralTwo;
    @BindView(R.id.tv_two_image_center_one)
    TextView tvTwoImageCenterOne;
    @BindView(R.id.iv_sign_three)
    ImageView ivSignThree;
    @BindView(R.id.tv_integral_three)
    TextView tvIntegralThree;
    @BindView(R.id.tv_two_image_center_two)
    TextView tvTwoImageCenterTwo;
    @BindView(R.id.iv_sign_four)
    ImageView ivSignFour;
    @BindView(R.id.tv_integral_four)
    TextView tvIntegralFour;
    @BindView(R.id.tv_two_image_center_three)
    TextView tvTwoImageCenterThree;
    @BindView(R.id.iv_sign_five)
    ImageView ivSignFive;
    @BindView(R.id.tv_integral_five)
    TextView tvIntegralFive;
    @BindView(R.id.tv_two_image_center_four)
    TextView tvTwoImageCenterFour;
    @BindView(R.id.iv_sign_six)
    ImageView ivSignSix;
    @BindView(R.id.tv_integral_six)
    TextView tvIntegralSix;
    @BindView(R.id.tv_two_image_center_five)
    TextView tvTwoImageCenterFive;
    @BindView(R.id.iv_sign_seven)
    ImageView ivSignSeven;
    @BindView(R.id.tv_integral_seven)
    TextView tvIntegralSeven;
    @BindView(R.id.tv_two_image_center_six)
    TextView tvTwoImageCenterSix;
    @BindView(R.id.tv_sign_below_hint)
    TextView tvSignBelowHint;
    private Context context = MainActivity.this;


    private List<MyText> textImageList;//签到和积分
    private List<TextView> textList;//图片和图片中间的内容
    private Animation back_anim;
    private Animation front_anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvent();
        initDate();
        initAnim();

    }

    private void initAnim() {
        //左上角提示动画
        Animation in = AnimationUtils.loadAnimation(this, R.anim.in_animation);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.out_animation);

        mImgSw.setInAnimation(in);
        mImgSw.setOutAnimation(out);

        mTvSw.setInAnimation(in);
        mTvSw.setOutAnimation(out);
        //左上角提示初始化
        mTvSw.setText(getResources().getString(R.string.today_sign));
        mImgSw.setImageResource(textImageList.get(0).getColorID());

        //翻转动画
        back_anim = AnimationUtils.loadAnimation(this, R.anim.back_scale);
        front_anim = AnimationUtils.loadAnimation(this, R.anim.front_scale);
        //第一个图片
        doAnimateZoom(textImageList.get(0));
        int imageView = 1;
        int textView = 0;
        //周围比较大的动画(不包括第一一天的)
        for (int i = 1; i < textImageList.size(); i++) {
            doAnimateOpen(textImageList.get(i), i + (imageView++), 13, 260);
        }
        for (int i = 0; i < textList.size(); i++) {
            doAnimateOpen(textList.get(i), i + (++textView), 13, 260);
        }
        randomAnim();
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(360) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1)
        );
        //动画周期为500ms
        set.setDuration(1000).start();
    }

    private void doAnimateOpen(MyText view, int index, int total, int radius) {
        if (view.getmImg().getVisibility() != View.VISIBLE) {
            view.getmImg().setVisibility(View.VISIBLE);//ImageView显示
        }
        double degree = Math.toRadians(360) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        view.setMoveX(translationX);
        view.setMoveY(translationY);
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view.getmImg(), "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view.getmImg(), "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view.getmImg(), "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view.getmImg(), "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view.getmImg(), "alpha", 0f, 1)
        );
        //动画周期为500ms
        set.setDuration(500).start();
    }

    private void doAnimateZoom(MyText image) {
        AnimatorSet set = new AnimatorSet();
        //包含缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(image.getmImg(), "scaleX", 0f, 2f),
                ObjectAnimator.ofFloat(image.getmImg(), "scaleY", 0f, 2f),
                ObjectAnimator.ofFloat(image.getmImg(), "alpha", 0f, 1)
        );
        //动画周期为500ms
        set.setDuration(1000).start();
    }

    private void randomAnim() {
        //随机值范围
        int min = 10;
        int max = 20;
        Random random = new Random();
        for (int i = 1; i < textImageList.size(); i++) {

            ImageView roleImageView = textImageList.get(i).getmImg();
            //取随机值
            int x = random.nextInt(max) % (max - min + 1) + min;
            int y = random.nextInt(max) % (max - min + 1) + min;

            int originalX = textImageList.get(i).getMoveX();
            int originalY = textImageList.get(i).getMoveY();


            textImageList.get(i).setRandomX(x);
            textImageList.get(i).setRandomY(y);

            AnimatorSet set = new AnimatorSet();
            //设置图片的动画
            ObjectAnimator moveX = ObjectAnimator.ofFloat(roleImageView, "translationX",
                    originalX, originalX - x, originalX,//X坐标的运动轨迹为:原点，左，原点，右，原点
                    originalX + x, originalX);
            ObjectAnimator moveY = ObjectAnimator.ofFloat(roleImageView, "translationY",
                    originalY, originalY - y, originalY,//Y坐标的运动轨迹为:原点，上，原点，下，原点
                    originalY + y, originalY);

            //设置动画循环
            moveX.setRepeatCount(-1);
            moveY.setRepeatCount(-1);
            set.playTogether(moveX, moveY);
            set.setDuration(4000);
            textImageList.get(i).setAnimatorSet(set);
        }
        //利用线程制造时间差，实现周围圆的不同步
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i < textImageList.size(); i++) {
                        Thread.sleep(500 / 7);
                        final int finalI = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //更新UI
                                AnimatorSet set = textImageList.get(finalI).getAnimatorSet();
                                set.start();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initDate() {
        textImageList = new ArrayList<>();
        textList = new ArrayList<>();
        //添加
        textImageList.add(new MyText(tvIntegralOne, ivSignOne, R.mipmap.icon_test_2_checked));
        textImageList.add(new MyText(tvIntegralTwo, ivSignTwo, R.mipmap.icon_test_1_checked));
        textImageList.add(new MyText(tvIntegralThree, ivSignThree, R.mipmap.icon_test_5_checked));
        textImageList.add(new MyText(tvIntegralFour, ivSignFour, R.mipmap.icon_test_4_checked));
        textImageList.add(new MyText(tvIntegralFive, ivSignFive, R.mipmap.icon_test_7_checked));
        textImageList.add(new MyText(tvIntegralSix, ivSignSix, R.mipmap.icon_test_8_checked));
        textImageList.add(new MyText(tvIntegralSeven, ivSignSeven, R.mipmap.icon_test_3_checked));
        //第一个是可点击的
        textImageList.get(0).setClickable(true);
        //添加
        textList.add(tvTwoImageCenterOne);
        textList.add(tvTwoImageCenterTwo);
        textList.add(tvTwoImageCenterThree);
        textList.add(tvTwoImageCenterFour);
        textList.add(tvTwoImageCenterFive);
        textList.add(tvTwoImageCenterSix);
    }

    private void initEvent() {
        mTvSw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(context);
                tv.setTextSize(16);
                tv.setTextColor(Color.WHITE);
                return tv;
            }
        });
        mImgSw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(context);
                iv.setMaxWidth(20);
                iv.setMaxHeight(20);
                return iv;
            }
        });
        ivSignOne.setOnClickListener(this);
        ivSignTwo.setOnClickListener(this);
        ivSignThree.setOnClickListener(this);
        ivSignFour.setOnClickListener(this);
        ivSignFive.setOnClickListener(this);
        ivSignSix.setOnClickListener(this);
        ivSignSeven.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sign_one:
                if (textImageList.get(0).isClickable()) {
                    textImageList.get(1).getAnimatorSet().cancel();
                    imageViewFlip(textImageList.get(0), textImageList.get(1), textList.get(1));
                    mImgSw.setImageResource(textImageList.get(1).getColorID());
                    mTvSw.setText(getResources().getString(R.string.tomorrow_sign));
                    textImageList.get(0).setClickable(false);
                    textImageList.get(1).setClickable(true);

                }
                break;
            case R.id.iv_sign_two:
                if (textImageList.get(1).isClickable()) {
                    textImageList.get(2).getAnimatorSet().cancel();
                    imageViewFlip(textImageList.get(1), textImageList.get(2), textList.get(2));
                    mImgSw.setImageResource(textImageList.get(2).getColorID());
                    mTvSw.setText(getResources().getString(R.string.tomorrow_sign));
                    textImageList.get(1).setClickable(false);
                    textImageList.get(2).setClickable(true);

                }
                break;
            case R.id.iv_sign_three:
                if (textImageList.get(2).isClickable()) {
                    textImageList.get(3).getAnimatorSet().cancel();
                    imageViewFlip(textImageList.get(2), textImageList.get(3), textList.get(3));
                    mImgSw.setImageResource(textImageList.get(3).getColorID());
                    mTvSw.setText(getResources().getString(R.string.tomorrow_sign));
                    textImageList.get(2).setClickable(false);
                    textImageList.get(3).setClickable(true);

                }
                break;
            case R.id.iv_sign_four:
                if (textImageList.get(3).isClickable()) {
                    textImageList.get(4).getAnimatorSet().cancel();
                    imageViewFlip(textImageList.get(3), textImageList.get(4), textList.get(4));
                    mImgSw.setImageResource(textImageList.get(4).getColorID());
                    mTvSw.setText(getResources().getString(R.string.tomorrow_sign));
                    textImageList.get(3).setClickable(false);
                    textImageList.get(4).setClickable(true);

                }
                break;
            case R.id.iv_sign_five:
                if (textImageList.get(4).isClickable()) {
                    textImageList.get(5).getAnimatorSet().cancel();
                    imageViewFlip(textImageList.get(4), textImageList.get(5), textList.get(5));
                    mImgSw.setImageResource(textImageList.get(5).getColorID());
                    mTvSw.setText(getResources().getString(R.string.tomorrow_sign));
                    textImageList.get(4).setClickable(false);
                    textImageList.get(5).setClickable(true);

                }
                break;
            case R.id.iv_sign_six:
                if (textImageList.get(5).isClickable()) {
                    textImageList.get(6).getAnimatorSet().cancel();
                    imageViewFlip(textImageList.get(5), textImageList.get(6), textList.get(0));
                    mImgSw.setImageResource(textImageList.get(6).getColorID());
                    mTvSw.setText(getResources().getString(R.string.tomorrow_sign));
                    textImageList.get(5).setClickable(false);
                    textImageList.get(6).setClickable(true);

                }
                break;
            case R.id.iv_sign_seven:
                if (textImageList.get(6).isClickable()) {
                    imageViewFlip(textImageList.get(6),null, null);
                    textImageList.get(6).setClickable(false);

                }
                break;
            default:
                break;

        }

    }

    private void imageViewFlip(final MyText mainTextImageMap, final MyText minorTextImageMap,
                               final TextView centerTextView) {
        final ImageView MainImage = mainTextImageMap.getmImg();
        //动画绑定
        MainImage.startAnimation(back_anim);
        //动画监听
        back_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //翻转动画开始，签到下面的提示词，渐渐消失
                AnimatorSet set = new AnimatorSet();
                //包含缩放和透明度动画
                set.playTogether(
                        ObjectAnimator.ofFloat(tvSignBelowHint, "scaleX", 1f, 0f),
                        ObjectAnimator.ofFloat(tvSignBelowHint, "scaleY", 1f, 0f),
                        ObjectAnimator.ofFloat(tvSignBelowHint, "alpha", 1f, 0)
                );
                //动画周期为500ms
                set.setDuration(500).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (MainImage.getId() != ivSignSeven.getId()) {
                    MainImage.setBackgroundResource(R.drawable.shape_circle_transparence);
                    MainImage.setImageResource(R.drawable.shape_circle_transparence);
                } else {
                    MainImage.setBackgroundResource(R.drawable.shape_circle_gray);
                    MainImage.setImageResource(R.drawable.shape_circle_gray);
                }
                MainImage.startAnimation(front_anim);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void doAnimateMoveSwapPlaces(MyText mainTextImageMap,
                                         MyText minorTextImageMap,
                                         TextView centerView) {
        final ImageView MainImage = mainTextImageMap.getmImg();
        final ImageView MinorImage = minorTextImageMap.getmImg();

        final TextView MainText = mainTextImageMap.getmTv();

        final int moveX = minorTextImageMap.getMoveX();
        final int moveY = minorTextImageMap.getMoveY();

        final int RandomX = minorTextImageMap.getRandomX();
        final int RandomY = minorTextImageMap.getRandomY();

        final AnimatorSet set = new AnimatorSet();

        //缩小，平移到周围
        ObjectAnimator anim_roundeX = ObjectAnimator.ofFloat(MainImage, "translationX", 0, moveX);
        ObjectAnimator anim_roundeY = ObjectAnimator.ofFloat(MainImage, "translationY", 0, moveY);
        ObjectAnimator anim_scale_rounde_X = ObjectAnimator.ofFloat(MainImage, "scaleX", 2f, 1f);
        ObjectAnimator anim_scale_rounde_Y = ObjectAnimator.ofFloat(MainImage, "scaleY", 2f, 1f);
        //平移TextView
        ObjectAnimator anim_move_textview_X = ObjectAnimator.ofFloat(MainText, "translationX", 0, moveX);
        ObjectAnimator anim_move_textview_Y = ObjectAnimator.ofFloat(MainText, "translationY", 0, moveY);
        //放大，平移到中间
        ObjectAnimator anim_centerX = ObjectAnimator.ofFloat(MinorImage, "translationX", moveX, 0);
        ObjectAnimator anim_centerY = ObjectAnimator.ofFloat(MinorImage, "translationY", moveY, 0);
        ObjectAnimator anim_scale_center_X = ObjectAnimator.ofFloat(MinorImage, "scaleX", 1f, 2f);
        ObjectAnimator anim_scale_center_Y = ObjectAnimator.ofFloat(MinorImage, "scaleY", 1f, 2f);
        //签到下方的提示词，显示
        tvSignBelowHint.setText(getResources().getString(R.string.tomorrow_sign));
        ObjectAnimator anim_sign_hint_tv_scale_X = ObjectAnimator.ofFloat(tvSignBelowHint, "scaleX", 0f, 1f);
        ObjectAnimator anim_sign_hint_tv_scale_Y = ObjectAnimator.ofFloat(tvSignBelowHint, "scaleY", 0f, 1f);
        ObjectAnimator anim_sign_hint_tv_alpha = ObjectAnimator.ofFloat(tvSignBelowHint, "alpha", 0f, 1f);
        //两个图片中间的TextView
        centerView.setText("100分");
        ObjectAnimator anim_tv_center_scale_X = ObjectAnimator.ofFloat(centerView, "scaleX", 0f, 1f);
        ObjectAnimator anim_tv_center_scale_Y = ObjectAnimator.ofFloat(centerView, "scaleY", 0f, 1f);
        ObjectAnimator anim_tv_center_scale_alpha = ObjectAnimator.ofFloat(centerView, "alpha", 0f, 1f);

        //动画同时启动
        set.playTogether(
                anim_roundeX, anim_roundeY, anim_scale_rounde_X, anim_scale_rounde_Y,//中间的大圆
                anim_move_textview_X, anim_move_textview_Y,//要移动的TextView
                anim_centerX, anim_centerY, anim_scale_center_X, anim_scale_center_Y,//周围的小圆
                anim_sign_hint_tv_scale_X, anim_sign_hint_tv_scale_Y, anim_sign_hint_tv_alpha,//签到下方提示词，跟随交换动画，一起启动
                anim_tv_center_scale_X, anim_tv_center_scale_Y, anim_tv_center_scale_alpha//两个图片中间，显示
        );
        //设置动画完成的时间
        set.setDuration(2000);
        //启动动画
        set.start();

        anim_centerX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //更换显示的图片和从中间到周围去的大圆的背景
                MinorImage.setImageResource(R.mipmap.zhiwen);
                MainImage.setBackgroundResource(R.drawable.shape_circle_gray);
                AnimatorSet set = new AnimatorSet();
                //ImageView
                ObjectAnimator Image_moveX = ObjectAnimator.ofFloat(MainImage, "translationX",
                        moveX - RandomX, moveX, moveX + RandomX, moveX, moveX - RandomX);
                ObjectAnimator Image_moveY = ObjectAnimator.ofFloat(MainImage, "translationY",
                        moveY - RandomY, moveY, moveY + RandomY, moveY, moveY - RandomY);
                //TextView
                ObjectAnimator text_moveX = ObjectAnimator.ofFloat(MainText, "translationX",
                        moveX - RandomX, moveX, moveX + RandomX, moveX, moveX - RandomX);
                ObjectAnimator text_moveY = ObjectAnimator.ofFloat(MainText, "translationY",
                        moveY - RandomY, moveY, moveY + RandomY, moveY, moveY - RandomY);
                //设置动画循环
                Image_moveX.setRepeatCount(-1);
                Image_moveY.setRepeatCount(-1);
                text_moveX.setRepeatCount(-1);
                text_moveY.setRepeatCount(-1);
                set.playTogether(
                        Image_moveX, Image_moveY,
                        text_moveX, text_moveY
                );
                set.setDuration(4000).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
