package com.example.asus.kugoumusic.tmp.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by asus on 2016/10/8.
 */
public class LrcView extends TextView {

    private Paint paint;
    private  int width,height;
    private  float speed1=0,speed2=0;
    public LrcView(Context context) {
        super(context);
        paint=new Paint();
    }

    public LrcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
    }

    public LrcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         paint=new Paint();
    }

//    public LrcView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }



    @Override
    protected void onDraw(Canvas canvas) {
        speed1+=(1.0/30);
        if(speed1>1){
            speed1=0;
        }
        paint.setTextSize(60);
        Shader shader=new LinearGradient(getBrginX(),getEndX(),0,0,
                new int[]{Color.YELLOW,Color.GREEN},
                new float[]{speed1,speed2},
                Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setTextAlign(Paint.Align.CENTER);//设置位置为正中心
        canvas.drawText(getText().toString(), width / 2, height / 2, paint);
        handler.sendEmptyMessage(1);
    }
    /**
     * 渲染的x轴的起始位置
     */

    public  float getBrginX() {
        return  width/2-getCharacterWidth(getText().toString(),60);
    }

    /**
     * 结束位置
     */
    public  float getEndX(){
        return  width/2+getCharacterWidth(getText().toString(),60);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width=w;
        this.height=h;
    }

    /**
     * 测量位置（字符串大小和文本）
     * @param text
     * @param textsize
     * @return
     */
    public  float getCharacterWidth(String text,int textsize){
        if(text==null||text.equals("")){
            return 0;
        }
        Paint paint=new Paint();
        paint.setTextSize(textsize);
        return paint.measureText(text); //返回当前字符串宽度
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                invalidate();
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            },100);
        }
    };
}
