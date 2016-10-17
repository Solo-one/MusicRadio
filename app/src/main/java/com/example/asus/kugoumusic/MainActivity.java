package com.example.asus.kugoumusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.util.VolleyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLatast();
    }


    //下拉加载最新数据
    private void loadLatast() {
        VolleyUtil.Get("http://apis.baidu.com/txapi/keji/keji?num=10&page=1")
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }


    /**
     * 网络监听回调方法
     */
    private class MyCallBack implements CallBack {

        @Override
        public void onSuccess(String response) {
            Log.i("TAG",response);
        }

        @Override
        public void onErrer(VolleyError error) {

        }

    }

  /*  //加载更多数据
    private void loadMore() {
        page++;
        VolleyUtil.get("http://apis.baidu.com/tngou/hospital/list?id="+cityID+"&page="+page+"&rows=10")
                .setCallBack(new MyCallBack1())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }*/

    /**
     * 网络监听回调方法
     */
   /* private class MyCallBack1 implements CallBack {

        @Override
        public void onSuccess(String response) {
            mData.addAll(parserJSON(response));
            adapter1.notifyDataSetChanged();
            setListViewHeight(lv);//解决ScrollView中嵌套ListView滚动效果冲突问题

            dao.removeHospital();//删除数据库元素
            dao.addHospital(mData);//重新写入数据
        }

        @Override
        public void onErrer(VolleyError error) {
            MedicalApplication.toast.toastShow("请求失败，请检查网络");
        }

    }
*/
    /**
     * JSON 格式化数据
     * @param s
     */
   /* private List<Hospital> parserJSON(String s) {

        List<Hospital> data = new ArrayList<>();

        try {
            JSONObject result = new JSONObject(s);
            boolean status = result.getBoolean("status");
            int total = result.getInt("total");

            JSONArray jsonArray = result.getJSONArray("tngou");
            //遍历json数组
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);

                Hospital nt = new Hospital(
                        jo.getString("name"),
                        jo.getString("level"),
                        jo.getString("url"),
                        "http://tnfs.tngou.net/image"+jo.getString("img"),
                        jo.getString("address"),
                        jo.getString("gobus")
                );

                data.add(nt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }*/
}
