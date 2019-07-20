package com.hdh.hwangdahyeon.tipcalculator.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.hdh.hwangdahyeon.tipcalculator.MyApplication;
import com.hdh.hwangdahyeon.tipcalculator.data.Location;
import com.hdh.hwangdahyeon.tipcalculator.object.Star;

import java.util.ArrayList;

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private CalculatorContract.View mView;
    private Context mContext;
    private ArrayList<Star> mStar;              //별 ArrayList 선언
    private Paint pStar;

    private int mWidth;
    private int mHeight;

    private Location mLocation;

    CalculatorPresenter(CalculatorContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mStar = new ArrayList<>(); // 별 ArrayList 생성
        pStar = new Paint();
        mLocation = MyApplication.getLocation();

        mWidth = MyApplication.getResolution().getWidth();
        mHeight = MyApplication.getResolution().getHeight();
    }


    @Override
    public void onDraw(Canvas canvas) {
        MeltStar();

        for (Star tStar : mStar) {                //별 그리기
            pStar.setAlpha(tStar.alpha);
            canvas.drawBitmap(tStar.star, tStar.nW_x, tStar.nW_y, pStar);
        }
    }

    /**
     * 별 생성
     */
    @Override
    public void createStar() {
        mLocation.setX((int) (Math.random() * mWidth));
        mLocation.setY((int) (Math.random() * ((mHeight / 3) * 2)));


        while (mLocation.getX() > mWidth / 5 &&
                mLocation.getY() < mHeight / 3 &&
                mLocation.getX() < (mWidth / 5) * 4) {
            mLocation.setX((int) (Math.random() * mWidth));
            mLocation.setY((int) (Math.random() * ((mHeight / 3) * 2)));
        }

        mStar.add(new Star(mLocation.getX(), mLocation.getY(), mContext));
    }

    /**
     * 별 지우기
     */
    private void DeleteStar() {
        for (int i = mStar.size() - 1; i >= 0; i--) {
            if (mStar.get(i).dead) {
                mStar.get(i).star = null;
                mStar.remove(i);
            }
        }
    }

    /**
     * 별 녹이기
     */
    private void MeltStar() {
        DeleteStar();
        for (int i = mStar.size() - 1; i >= 0; i--) {
            if (mStar.get(i).deadStart) {
                mStar.get(i).dead = mStar.get(i).reducedStarAlphaValue();
            } else {
                mStar.get(i).increaseStarAlphaValue();
            }
        }
    }
}
