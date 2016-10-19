package linchom.com.linchomspace.shopping;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.widget.GoodsScrollView;

public class GoodsActivity extends AppCompatActivity {

    private GoodsScrollView sv_goods_scrollview;
    private RelativeLayout rl_goods_head;
    private TextView tv_goods_title_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        initView();
        initData();
        initEvent();


    }

    private void initView() {

        sv_goods_scrollview = ((GoodsScrollView) findViewById(R.id.sv_goods_scrollview));
        rl_goods_head = ((RelativeLayout) findViewById(R.id.rl_goods_head));
        tv_goods_title_content = ((TextView) findViewById(R.id.tv_goods_title_content));

    }

    private void initData() {

        rl_goods_head.getBackground().setAlpha(0);
        int initColor = tv_goods_title_content.getTextColors().getDefaultColor();
        int initR = Color.red(initColor);
        int initG = Color.green(initColor);
        int initB = Color.blue(initColor);
        int newColor = Color.argb(0,initR, initG, initB);
        tv_goods_title_content.setTextColor(newColor);


    }

    private void initEvent() {
        sv_goods_scrollview.setScrollViewListener(new GoodsScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {

                titleAnima(y);

            }
        });

    }



    public void titleAnima(int y) {
        int scrollHeight = sv_goods_scrollview.getChildAt(0).getHeight()
                - sv_goods_scrollview.getHeight();
        float scrollPercent = (float) y / scrollHeight;
        rl_goods_head.getBackground().setAlpha((int) (255 * scrollPercent));

         int color = tv_goods_title_content.getTextColors().getDefaultColor();
         int r = Color.red(color);
         int g = Color.green(color);
         int b = Color.blue(color);
        int changeToColor = Color.argb((int) (255 * scrollPercent), r, g, b);

        tv_goods_title_content.setTextColor(changeToColor);
    }
}
