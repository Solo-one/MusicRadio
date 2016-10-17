package com.example.asus.kugoumusic.lazyFragment;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by asus on 2016/9/26.
 */
public abstract class LazyFragment extends Fragment {

    protected boolean isVisiable = false;//Fragment 是否可见

    /**
     * Fragment将要可见时调用  该方法执行很早，在onCreate()方法之前
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisiable = true;
            lazyInitData();
        }else {
            isVisiable = false;
        }
    }

    //懒加载抽象方法
    public abstract void lazyInitData();
}
