package com.hdh.hwangdahyeon.tipcalculator.ui.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

import com.hdh.hwangdahyeon.tipcalculator.Constants;
import com.hdh.hwangdahyeon.tipcalculator.ui.view.CalculatorView;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private CalculatorView calculatorView;

    private boolean mIsPercentCheck[];


    /**
     * 생성자
     */
    MainPresenter(MainContract.View mView, Context mContext, Activity mActivity, CalculatorView calculatorView) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.calculatorView = calculatorView;

        mIsPercentCheck = new boolean[4];
        mView.initView();
    }

    /**
     * 재진입
     */
    @Override
    public void onResume() {
        calculatorView.getInvalidateHandler().sendEmptyMessageDelayed(0, Constants.HANDLER_INVALIDATE);
        calculatorView.getCreateStartHandler().sendEmptyMessageDelayed(0, Constants.HANDLER_CREATE_STAR);
    }

    /**
     * 앱 멈춤
     */
    @Override
    public void onPause() {
        calculatorView.getInvalidateHandler().removeMessages(0);
        calculatorView.getCreateStartHandler().removeMessages(0);
    }

    /**
     * 화면 비율 구하기
     */
    @Override
    public float ScreenArea() {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    /**
     * 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickPercentButton(int index) {
        //입력한 값이 Double형이면
        String amount = mView.getAmountText();

        mIsPercentCheck[0] = false;
        mIsPercentCheck[1] = false;
        mIsPercentCheck[2] = false;
        mIsPercentCheck[3] = false;

        switch (index) {
            case 0:
                mIsPercentCheck[0] = true;
                calculator(0.12, amount);
                break;
            case 1:
                mIsPercentCheck[1] = true;
                calculator(0.15, amount);
                break;
            case 2:
                mIsPercentCheck[2] = true;
                calculator(0.18, amount);
                break;
            case 3:
                mIsPercentCheck[3] = true;
                calculator(0.20, amount);
                break;
        }
        mView.changePercentText(null);
        setButton();
    }



    /**
     * 금액 입력 이벤트 처리
     */
    @Override
    public void inputAmount(String amount) {

        //입력한 값이 Double형이면
        if (isStringDouble(amount)) {
            double amountToDouble = Double.parseDouble(amount);

            if (mIsPercentCheck[0]) {
                calculator(0.12, amountToDouble);
            } else if (mIsPercentCheck[1]) {
                calculator(0.15, amountToDouble);
            } else if (mIsPercentCheck[2]) {
                calculator(0.18, amountToDouble);
            } else if (mIsPercentCheck[3]) {
                calculator(0.20, amountToDouble);
            } else {
                if (isStringDouble(mView.getPercentText())) {
                    calculator(Double.parseDouble(mView.getPercentText()) / 100, amountToDouble);
                } else {
                    mView.changeTipText(0);
                    mView.changeTotalText(0);
                }
            }
        } else {
            mView.changeTipText(0);
            mView.changeTotalText(0);
        }
    }

    /**
     * 퍼센트 입력 이벤트 처리
     */
    @Override
    public void inputPercent(String percent) {
        if (percent.equals("")) {
            mIsPercentCheck[0] = true;
            mIsPercentCheck[1] = false;
            mIsPercentCheck[2] = false;
            mIsPercentCheck[3] = false;
            setButton();
            clickPercentButton(0);
            mView.changePercentAlpha(false);
        } else {
            mIsPercentCheck[0] = false;
            mIsPercentCheck[1] = false;
            mIsPercentCheck[2] = false;
            mIsPercentCheck[3] = false;
            setButton();
            if (isStringDouble(percent) && isStringDouble(mView.getAmountText())) {
                calculator(Double.parseDouble(percent) / 100, Double.parseDouble(mView.getAmountText()));
                mView.changePercentAlpha(true);
            } else {
                mView.changeTipText(0);
                mView.changeTotalText(0);
            }
        }
    }

    /**
     * Double , String 구별
     * true = Double , false = String
     */
    private static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 버튼 세팅
     */
    private void setButton() {
        for (int i = 0; i < 4; i++) {
            mView.changePercentButton(i, mIsPercentCheck[i]);
        }
    }

    /**
     * 계산하기
     */
    private void calculator(double percent, double amount) {
        double result = amount * percent;
        double total = amount + result;
        mView.changeTipText(result);
        mView.changeTotalText(total);
    }

    /**
     * 계산하기
     */
    private void calculator(double percent, String amount) {
        if (isStringDouble(amount)) {
            double result = Double.parseDouble(amount) * percent;
            double total = Double.parseDouble(amount) + result;
            mView.changeTipText(result);
            mView.changeTotalText(total);
        } else {
            mView.changeTipText(0);
            mView.changeTotalText(0);
        }
    }
}
