package com.zjf.finder.biz.home.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.zjf.finder.R;
import com.zjf.finder.base.http.HttpCallback2;
import com.zjf.finder.base.http.Result2;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.home.model.News;
import com.zjf.finder.biz.home.service.NewsService;
import com.zjf.finder.constant.Constant;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textview);
        mTextView.setText("Ready...");
        getData();
    }

    private void getData(){
        RetrofitHttpClient.getInstance().forRetrofit(Constant.BASE_URL, NewsService.class)
                .getNewsData("social", Constant.APIKEY, "10", 1).enqueue(new HttpCallback2<Result2>() {
            @Override
            public void onResponse(Result2 result2) {
                if(result2 != null && result2.getNewslist() != null){
                    List<News> newsList = result2.getNewslist();
                    mTextView.setText(newsList.toString());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                Toast.makeText(getApplicationContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
