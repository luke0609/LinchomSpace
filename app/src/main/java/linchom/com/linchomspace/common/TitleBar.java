package linchom.com.linchomspace.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import linchom.com.linchomspace.R;

/**
 * Created by Administrator on 2016/10/17.
 */

public class TitleBar extends RelativeLayout{




    private ImageView titlebarRight;
    private TextView titlebar_title;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_titlebar,this,true);

        titlebarRight = ((ImageView) view.findViewById(R.id.titlebar_button));

        titlebar_title = ((TextView) view.findViewById(R.id.titlebar_title));

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.titlebar);

        String textContent =array.getString(R.styleable.titlebar_titleText);


        titlebar_title.setText(textContent);

        boolean titleRightVis = array.getBoolean(R.styleable.titlebar_titleRightVis,true);

        if(titleRightVis==true){

            titlebarRight.setVisibility(VISIBLE);

        }else {

            titlebarRight.setVisibility(INVISIBLE);
        }




        Drawable titleRightRes =array.getDrawable(R.styleable.titlebar_titleRightRes);
        titlebarRight.setImageDrawable(titleRightRes);




        array.recycle();


    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setTitlebarRightOnClickListener(@Nullable OnClickListener l){
        titlebarRight.setOnClickListener(l);
    }






}
