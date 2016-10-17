package com.example.asus.kugoumusic.tmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.asus.kugoumusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Main7PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.photoView)
    PhotoView photoView;

    PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7_photo_view);
        ButterKnife.bind(this);
        photoView.setImageResource(R.drawable.bg_album_head);

        photoView.setScaleType(ImageView.ScaleType.FIT_XY);

        attacher = new PhotoViewAttacher(photoView);
    }
}
