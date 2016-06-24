package com.djonce.android;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.djonce.banner.JBanner;
import com.djonce.banner.JBannerModel;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JBanner jBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        initViews();
    }

    private void initViews() {
        jBanner = (JBanner) findViewById(R.id.banner);

        jBanner.setImageHandleListener(new JBanner.OnImageHandleListener() {
            @Override
            public View onLoadImage(JBannerModel entity) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.getInstance().displayImage(entity.getImgUrl(), imageView, defaultImageOptions);
                return imageView;
            }

        });

        jBanner.setViewPagerViews(getViewPagerViews());
    }

    private List<JBannerModel> getViewPagerViews() {

        List<JBannerModel> jBannerModels = new ArrayList<>();

        BannerEntity model = new BannerEntity();
        model.setId(0);
        model.setDesc("第一张图啊~");
        model.setImgUrl("http://img2.peiyinxiu.com/2016061414286e1472bbad42b6c7.jpg");
        model.setOtherUrl("jfsodfjosafjosdjfoj");
        jBannerModels.add(model);

        model = new BannerEntity();
        model.setId(109);
        model.setDesc("第二张图啊~");
        model.setImgUrl("http://img2.peiyinxiu.com/2016060817405c247aea27144c29.jpg");
        model.setOtherUrl("的说法色弱");
        jBannerModels.add(model);

        return jBannerModels;
    }


    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                //.writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }

    private DisplayImageOptions defaultImageOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
            .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
            .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
            .build();//构建完成;
}
