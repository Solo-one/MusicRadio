package com.example.asus.kugoumusic.tmp.flowLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gq on 2016/10/9.
 */
public class FlowLatout extends ViewGroup {

    //先改写构造函数 让最终的调用构造函数时产生的实例一致
    public FlowLatout(Context context) {
//        super(context);
        this(context, null);//改写一下让它可以调用两个参数的构造方法
    }

    //上下文 和 属性集 改写一下让它可以调用三个参数的构造方法
    public FlowLatout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //第三个参数是自定义的属性  然后所有的逻辑写在三个参数的构造方法中
    public FlowLatout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 设置子View的位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    //定义一个变量存储所有的VIew  以一行一行存储
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    //每一行的高度
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeight.clear();
        int width = getWidth();//当前ViewGroup的宽度
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            //如果需要换行
//            if (childWidth + lineWidth+ lp.leftMargin+lp.rightMargin>width){
            //////////////////////改写解决 为flowerLayout设置padding时有部分无法正常显示的问题
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                mLineHeight.add(lineHeight);//记录每一行的lineHeight
                mAllViews.add(lineViews);//记录所有的Views
                //重置行宽行高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置View 集合
                lineViews = new ArrayList<View>();
            }
            //不需要换行时
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }//for end
        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        //设置子VIEW的位置
//        int left = 0;
//        int top = 0 ;
        //////////////////改写解决 为flowerLayout设置padding时有部分无法正常显示的问题
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int lineNum = mAllViews.size();
        for (int i = 0; i < lineNum; i++) {
            //当前行的所有的View
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            //遍历当前行所有的view
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                //判断child的状态
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int rb = tc + child.getMeasuredHeight();
                //为子VIEW布局
                child.layout(lc, tc, rc, rb);
                //同一行的第二个view的位置
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            //第二行···第n行
//            left = 0;
            /////////////// 改写解决 为flowerLayout设置padding时有部分无法正常显示的问题
            left = getPaddingLeft();
            top += lineHeight;
        }

    }

    /**
     * 测量值
     * 测量模式 有三种：AT_MOST对应wrap_content ,EXACTLY对应具体值或者match_parent UNSPCIFIED：想要多大就多大
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //当布局中写的是match_parent时 可以直接得到容器的宽和高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);//宽度值 容器的宽度
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);//宽度的测量模式 宽度值
        boolean j = modeWidth == MeasureSpec.EXACTLY;
        Log.i("打印宽度", j + "");
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //但是当布局文件中floelayout写的是 wrap_content时(模式为AT_MOST)要自己设置值  需要写下面的代码
        int width = 0;
        int height = 0;
        //计算wrap_content时的宽高  记录每一行的宽度和高度
        int linWidth = 0;
        int linHeight = 0;
        //得到内部元素的个数
        int cCount = getChildCount();
        //遍历子VIew
        for (int i = 0; i < cCount; i++) {
            //拿到子view
            View child = getChildAt(i);
            //测量子View的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);//参数：要测量的元素  宽度 高度
            //得到子View LayoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //子View占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //子View占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //判断当前已有的宽度加上子View的宽度大于容器宽度(sizeWidth)  换行
//            if (linWidth+childWidth >sizeWidth){
//////////////////////解决问题 为FloweLayout设置padding时会有一部分无法显示
            if (linWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                width = Math.max(width, linWidth);//当前的宽度与最大宽度对比 对比得到最大的宽度
                //重置lineWidth
                linWidth = childWidth;
                //换行 行高叠加 记录行高
                height += linHeight;
                //重置行高
                linHeight = childHeight;
            } else {//未换行的情况
                linWidth += childWidth;//叠加行宽
                linHeight = Math.max(linHeight, childHeight);//得到当前行最大的高度
            }
            //最后一个控件 必须特殊处理
            if (i == cCount - 1) {//如果i到达最后一个控件 必须特殊处理
                width = Math.max(linWidth, width);
                height += linHeight;
            }

        }
        Log.e("TAG", "sizeWidth" + sizeWidth);
        Log.e("TAG", "sizeHeight" + sizeHeight);
        //布局wrap_content时
        setMeasuredDimension(
//               modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
// ///////////改写解决 为flowerLayout设置padding时有部分无法正常显示的问题
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
//               modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingBottom() + getPaddingTop());
        //如果当前的宽度是EXACTLY模式 使用父控件传进来的之 否则使用自己测量的值
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
