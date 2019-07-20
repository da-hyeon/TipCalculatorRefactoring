package com.hdh.hwangdahyeon.tipcalculator.ui.main;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.hdh.hwangdahyeon.tipcalculator.MyApplication;
import com.hdh.hwangdahyeon.R;
import com.hdh.hwangdahyeon.databinding.ActivityMainBinding;
import com.hdh.hwangdahyeon.tipcalculator.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private ActivityMainBinding mBinding;
    private Button[] mPercentButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().setActivity(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActivity(this);
        mPresenter = new MainPresenter(this, this, this , mBinding.clView);



        //버튼 클릭
        // 0 -> 12%
        // 1 -> 15%
        // 2 -> 18%
        // 3 -> 20%
        for (int i = 0; i < mPercentButtons.length; i++) {
            int finalI = i;
            mPercentButtons[i].setOnClickListener(v ->
                    mPresenter.clickPercentButton(finalI)
            );
        }

        //금액 입력 변동
        mBinding.etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mBinding.etAmount.getText().toString().matches("^0"))
                {
                    // Not allowed
                    mBinding.etAmount.setText(null);
                } else {
                    mPresenter.inputAmount(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //퍼센트 입력 변동
        mBinding.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.inputPercent(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 뷰 생성 및 세팅
     */
    @Override
    public void initView() {
        mPercentButtons = new Button[]{
                mBinding.btPercent12,
                mBinding.btPercent15,
                mBinding.btPercent18,
                mBinding.btPercent20
        };

        //EditText 및 TextView Size 변환
        mBinding.etAmount.post(() -> {
            float getSize = mPresenter.ScreenArea() / 18;

            //HOW MUCH?
            mBinding.tvTitle.setTextSize(getSize / 5);
            //금액 입력란
            mBinding.etAmount.setTextSize(getSize);
            //금액 뒤 $ 표시
            mBinding.tvDollar.setTextSize(getSize);

            //THE PERCENTAGE
            mBinding.tvPercentage.setTextSize(getSize / 5);

            //버튼 글씨 Size
            for (Button button : mPercentButtons) {
                button.setTextSize((int) (getSize / 3.5));
            }

            //or YOU CAN HAVE OWN
            mBinding.tvInputText.setTextSize(getSize / 5);
            //퍼센트 입력란
            mBinding.etInput.setTextSize((int) (getSize / 1.5));
            //퍼센트 수치 뒤 %표시
            mBinding.tvInputPercent.setTextSize((int) (getSize / 1.5));

            //Tip 표시
            mBinding.tvTipText.setTextSize(getSize / 5);
            mBinding.tvTip.setTextSize(getSize / 3);

            //Total 표시
            mBinding.tvTotalText.setTextSize(getSize / 5);
            mBinding.tvTotal.setTextSize(getSize / 3);
        });
    }

    @Override
    public String getPercentText() {
        return mBinding.etInput.getText().toString();
    }

    @Override
    public String getAmountText() {
        return mBinding.etAmount.getText().toString();
    }

    /**
     * TIP Text 변경하기
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void changeTipText(double tip) {

        mBinding.tvTip.setText((Math.round(tip*100)/100.0) + "$");
    }

    /**
     * Total Text 변경하기
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void changeTotalText(double total) {
        mBinding.tvTotal.setText((Math.round(total*100)/100.0) + "$");
    }

    /**
     * Percent Text 변경하기
     */
    @Override
    public void changePercentText(String percent) {
        if (!mBinding.etInput.getText().toString().equals(""))
            mBinding.etInput.setText(percent);
    }

    /**
     * 버튼 Alpha 변경하기
     */
    @Override
    public void changePercentButton(int index, boolean state) {
        float getAlpha = mPercentButtons[index].getAlpha();
        if (state) {
            if (getAlpha == 0.6f)
                return;

            mPercentButtons[index].setAlpha(0.6f);
        } else {
            if (getAlpha == 0.4f)
                return;

            mPercentButtons[index].setAlpha(0.4f);
        }
    }

    /**
     * PercentAlpha 변경하기
     */
    @Override
    public void changePercentAlpha(boolean state) {
        float getAlpha = mBinding.etInput.getAlpha();

        if (state) {
            if (getAlpha == 0.8f)
                return;

            mBinding.etInput.setAlpha(0.8f);
            mBinding.tvInputPercent.setAlpha(0.8f);

        } else {
            if (getAlpha == 0.4f)
                return;

            mBinding.etInput.setAlpha(0.4f);
            mBinding.tvInputPercent.setAlpha(0.4f);
        }
    }

    /**
     * 재진입
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    /**
     * 앱 정지
     */
    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }
}
