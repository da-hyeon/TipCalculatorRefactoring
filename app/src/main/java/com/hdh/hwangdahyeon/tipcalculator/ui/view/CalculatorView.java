package com.hdh.hwangdahyeon.tipcalculator.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.hdh.hwangdahyeon.tipcalculator.Constants;

public class CalculatorView extends View implements CalculatorContract.View {

    private CalculatorContract.Presenter mPresenter;


    /**
     * 갱신 핸들러
     */
    @SuppressLint("HandlerLeak")
    Handler mInvalidateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate(); // View를 다시 그림
            mInvalidateHandler.sendEmptyMessageDelayed(0, Constants.HANDLER_INVALIDATE);
        }
    };

    /**
     * 별 생성 핸들러
     */
    @SuppressLint("HandlerLeak")
    Handler mCreateStartHandler = new Handler() {
        public void handleMessage(Message msg) {
            mPresenter.createStar();
            mCreateStartHandler.sendEmptyMessageDelayed(0, Constants.HANDLER_CREATE_STAR);
        }
    };

    /**
     * 생성자
     */
    public CalculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPresenter = new CalculatorPresenter(this, getContext());

        mInvalidateHandler.sendEmptyMessageDelayed(0, Constants.HANDLER_INVALIDATE);
        mCreateStartHandler.sendEmptyMessageDelayed(0, Constants.HANDLER_CREATE_STAR);
    }

    /**
     * 그리기
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPresenter.onDraw(canvas);
    }

    /**
     * Handler 객체 넘기기
     */
    public Handler getInvalidateHandler() {
        return mInvalidateHandler;
    }

    /**
     * Handler 객체 넘기기
     */
    public Handler getCreateStartHandler() {
        return mCreateStartHandler;
    }
}
