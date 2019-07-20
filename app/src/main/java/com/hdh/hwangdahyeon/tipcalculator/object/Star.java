package com.hdh.hwangdahyeon.tipcalculator.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hdh.hwangdahyeon.tipcalculator.MyApplication;
import com.hdh.hwangdahyeon.R;

public class Star {

    public int nW_x, nW_y; // 단어 좌표
    public int sW_x, sW_y; // 단어 크기
    public Bitmap star; // 단어 이미지

    public long lastTime_star; // 경과 시간

    public boolean dead = false; // 터질것인지 여부
    public boolean deadStart = false;

    public int alpha = 0; // 이미지의 Alpha (투명도)

    int ran[] = {R.drawable.star1 , R.drawable.star2};
    private int index = (int) (Math.random() * ran.length);          //배열개수만큼 랜덤ㄱ
    private int res = ran[index];               //res에 ran에서 랜덤으로 하나 뽑은것을 저장

    private int randomSize[] = {90, 100 , 110, 120};
    private int randomSizeIndex = (int) (Math.random() * randomSize.length);          //배열개수만큼 랜덤ㄱ
    private int randomSizeres = randomSize[index];               //res에 ran에서 랜덤으로 하나 뽑은것을 저장

    //----------------------------------
    // 생성자
    //----------------------------------
    public Star(int _x, int _y, Context context) {

        int width = MyApplication.getResolution().getWidth();

        nW_x = _x;
        nW_y = _y;

        sW_x = width / randomSizeres;          //이미지의 x축 크기
        sW_y = width / randomSizeres;          //이미지의 y축 크기

        star = BitmapFactory.decodeResource(context.getResources(), res);               //이미지생성
        star = Bitmap.createScaledBitmap(star, sW_x, sW_y, true);       //크기조정

        lastTime_star = System.currentTimeMillis(); // 현재 시각
    }

    /**
     * Alpha값 점점 낮추기
     */
    public boolean reducedStarAlphaValue() {
        alpha -= 5;
        if (alpha <= 1) {
            return true;
        }
        else
            return false;
    }

    /**
     * Alpha값 점점 높이기
     */
    public void increaseStarAlphaValue() {
        alpha += 5;
        if (alpha >= 250) {
            deadStart = true;
        }
    }
}
