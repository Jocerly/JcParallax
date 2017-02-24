package com.jc.parallax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jc.parallax.version1.MainActivity1;
import com.jc.parallax.version2.MainActivity2;

/**
 * Created by asus on 2017/2/23.
 */

public class WelComeActivity extends Activity implements View.OnClickListener {
    private Button btnVersion1;
    private Button btnVersion2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );

        btnVersion1 = (Button)findViewById(R.id.btnVersion1);
        btnVersion1.setOnClickListener(this);
        btnVersion2 = (Button)findViewById(R.id.btnVersion2);
        btnVersion2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVersion1:
                startActivity(new Intent(this, MainActivity1.class));
                break;
            case R.id.btnVersion2:
                startActivity(new Intent(this, MainActivity2.class));
                break;
        }
    }

    /**
     * 点击返回键退出程序
     */
    private static Boolean isExit = false;
    private Handler mHandler = new Handler();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
