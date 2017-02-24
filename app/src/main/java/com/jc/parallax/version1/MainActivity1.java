package com.jc.parallax.version1;

import com.jc.parallax.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends Activity implements View.OnClickListener {
    private TextView textShip;
    private ParallaxLayout parallaxLayout;
    private ImageView imageView;
    private ImageView rl_weibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main1);
        /**
         * 动画支持11以上sdk,11以下默认不显示动画 若需要支持11以下动画,也可导入https://github.com/JakeWharton/NineOldAndroids
         */
        if ( Build.VERSION.SDK_INT > 10 ) {
            textShip = (TextView)findViewById(R.id.textShip);
            textShip.setOnClickListener(this);
            imageView = (ImageView) findViewById( R.id.iv_man );
            parallaxLayout = (ParallaxLayout) findViewById( R.id.parallax_container );
            if ( parallaxLayout != null ) {
                imageView.setVisibility( View.VISIBLE );
                textShip.setVisibility( View.VISIBLE );
                parallaxLayout.setImage( imageView );
                parallaxLayout.setLooping( false );
                parallaxLayout.setupChildren(
                    getLayoutInflater(),
                    R.layout.view_intro_1,
                    R.layout.view_intro_2,
                    R.layout.view_intro_3,
                    R.layout.view_intro_4,
                    R.layout.view_intro_5,
                    R.layout.view_login );
            }
        }
        else {
            setContentView( R.layout.view_login );
        }
        rl_weibo = (ImageView) findViewById(R.id.rl_weibo);
        rl_weibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.textShip:
            parallaxLayout.setLogin();
            break;

        case R.id.rl_weibo:
            try {
                Uri uri = Uri.parse( "market://details?id=com.xingin.xhs" );
                Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( intent );
            } catch ( ActivityNotFoundException e ) {
                e.printStackTrace();
                Toast.makeText(this, "没有第三方市场工具下载", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
