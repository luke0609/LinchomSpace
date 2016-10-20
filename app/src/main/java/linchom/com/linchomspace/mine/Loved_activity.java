package linchom.com.linchomspace.mine;



import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.fragment.goods_fragment;
import linchom.com.linchomspace.mine.fragment.news_fragment;
import linchom.com.linchomspace.mine.fragment.vidio_fragment;


public class Loved_activity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG ="Loved_activity" ;

    private Button bt_goods;
    private Button bt_vidio;
    private Button bt_ziun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved_activity);

        bt_goods = ((Button) findViewById(R.id.bt_goods));
        bt_vidio = ((Button) findViewById(R.id.bt_vidio));
        bt_ziun = ((Button) findViewById(R.id.bt_ziun));

        switchFragment(new news_fragment());
    }


    @Override
    public void onClick(View v) {
        Fragment fragment=null;
        switch (v.getId()){
            case R.id.bt_goods:
                fragment=new goods_fragment();
                break;
            case R.id.bt_ziun:
            fragment=new news_fragment();
            break;
            case R.id.bt_vidio:
                fragment=new vidio_fragment();
                break;
        }
        switchFragment(fragment);
    }

    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.lv_goods,fragment).commit();
    }
}
