package com.hdh.hwangdahyeon.tipcalculator.base;

import android.support.v7.app.AppCompatActivity;

import com.hdh.hwangdahyeon.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
