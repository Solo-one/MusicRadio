package com.example.asus.kugoumusic.tmp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by asus on 2016/9/22.
 */
public class IconFontLayoutFactory  implements LayoutInflaterFactory {
    private static Typeface mTypeface;
    private AppCompatDelegate mAppCompatDelegate;
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view= mAppCompatDelegate.createView(parent,name,context,attrs);
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(mTypeface);
        }

        return view;
    }

    public IconFontLayoutFactory(AppCompatDelegate mAppCompatDelegate,Context context) {
        if (mTypeface==null) {
            mTypeface=Typeface.createFromAsset(context.getAssets(),"fonts/iconfont.ttf");
        }
        this.mAppCompatDelegate=mAppCompatDelegate;
    }

}

