package com.hdh.hwangdahyeon.tipcalculator.data;

import android.graphics.Point;
import android.view.Display;

import com.hdh.hwangdahyeon.tipcalculator.MyApplication;

public class Resolution {
    private int width;
    private int height;

    public Resolution() {
        Display display = MyApplication.getInstance().getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
