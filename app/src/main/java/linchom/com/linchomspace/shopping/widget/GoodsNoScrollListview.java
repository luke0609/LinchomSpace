package linchom.com.linchomspace.shopping.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class GoodsNoScrollListview extends ListView{

        public GoodsNoScrollListview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public GoodsNoScrollListview(Context context) {
		super(context);

	}

		public GoodsNoScrollListview(Context context, AttributeSet attrs) {
                super(context, attrs);  
        }  
          
        /** 
         * 设置不滚动 
         */  
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
        {  
                int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                                MeasureSpec.AT_MOST);  
                super.onMeasure(widthMeasureSpec, expandSpec);  
  
        }  
  
} 