package com.hdh.hwangdahyeon.tipcalculator.ui.view;

import android.graphics.Canvas;

public interface CalculatorContract {
    interface View{

    }
    interface Presenter{
        void onDraw(Canvas canvas);
        void createStar();
    }
}