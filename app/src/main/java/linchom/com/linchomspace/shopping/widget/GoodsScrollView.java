package linchom.com.linchomspace.shopping.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/10/18.
 */

public class GoodsScrollView  extends ScrollView{

    private ScrollViewListener scrollViewListener = null;


    public GoodsScrollView(Context context) {
        super(context);
    }

    public GoodsScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoodsScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        // x为当前滑动条的横坐标，y表示当前滑动条的纵坐标，oldx为前一次滑动的横坐标，oldy表示前一次滑动的纵坐标
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            // 在这里将方法暴露出去
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        // 弹性滑动关键则是maxOverScrollX， 以及maxOverScrollY，
        // 一般默认值都是0，需要弹性时，更改其值即可
        // 即就是，为零则不会发生弹性，不为零（>0,负数未测试）则会滑动到其值的位置
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, 0, 0, isTouchEvent);
    }

    public interface ScrollViewListener {

        void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy);

    }

    public void setScrollViewListener(ScrollViewListener listener) {
        scrollViewListener = listener;
    }



}
