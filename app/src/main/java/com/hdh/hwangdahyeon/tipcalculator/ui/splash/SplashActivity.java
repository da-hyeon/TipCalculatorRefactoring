package com.hdh.hwangdahyeon.tipcalculator.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hdh.hwangdahyeon.R;
import com.hdh.hwangdahyeon.tipcalculator.base.BaseActivity;
import com.hdh.hwangdahyeon.tipcalculator.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        finish();
    }
}
