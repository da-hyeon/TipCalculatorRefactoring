package com.hdh.hwangdahyeon.tipcalculator.ui.main;

public interface MainContract {
    interface View{
        void initView();
        String getPercentText();
        String getAmountText();

        void changeTipText(double tip);
        void changeTotalText(double total);
        void changePercentText(String percent);

        void changePercentButton(int index , boolean state);
        void changePercentAlpha(boolean state);
    }
    interface Presenter{
        void onResume();
        void onPause();

        float ScreenArea();

        void clickPercentButton(int index);

        void inputAmount(String amount);
        void inputPercent(String percent);
    }
}
