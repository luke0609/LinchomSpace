package linchom.com.linchomspace.homepage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.MainActivity;
import linchom.com.linchomspace.R;

public class NavigationActivity extends AppCompatActivity {

    @InjectView(R.id.iv_navigation_delete)
    ImageView ivNavigationDelete;
    @InjectView(R.id.pd_tuijian)
    Button pdTuijian;
    @InjectView(R.id.pd_hangyezixun)
    Button pdHangyezixun;
    @InjectView(R.id.pd_canpinzixun)
    Button pdCanpinzixun;
    @InjectView(R.id.pd_video)
    Button pdVideo;
    @InjectView(R.id.pd_pinpaizixun)
    Button pdPinpaizixun;
    @InjectView(R.id.pd_hangyebiaozhun)
    Button pdHangyebiaozhun;
    @InjectView(R.id.pd_hangyejishu)
    Button pdHangyejishu;
    @InjectView(R.id.pd_wulianwang)
    Button pdWulianwang;
    @InjectView(R.id.pd_zhihuichengshi)
    Button pdZhihuichengshi;
    @InjectView(R.id.pd_zhihuishequ)
    Button pdZhihuishequ;
    @InjectView(R.id.pd_wurenji)
    Button pdWurenji;
    @InjectView(R.id.pd_zhinengyanglao)
    Button pdZhinengyanglao;
    @InjectView(R.id.pd_zhinengjiankang)
    Button pdZhinengjiankang;
    @InjectView(R.id.pd_zhinengyiliao)
    Button pdZhinengyiliao;
    @InjectView(R.id.pd_jiqiren)
    Button pdJiqiren;
    @InjectView(R.id.pd_vrar)
    Button pdVrar;
    @InjectView(R.id.pd_zhanhuixinxi)
    Button pdZhanhuixinxi;
    @InjectView(R.id.pd_peixunxinxi)
    Button pdPeixunxinxi;
    @InjectView(R.id.pd_zhichaofangan)
    Button pdZhichaofangan;
    @InjectView(R.id.pd_zhichaoanli)
    Button pdZhichaoanli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.inject(this);
    }

    private void toMain() {
        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }

    @OnClick(R.id.iv_navigation_delete)
    public void onClick() {
        NavigationActivity.this.finish();

    }

    @OnClick({R.id.pd_tuijian, R.id.pd_hangyezixun, R.id.pd_canpinzixun, R.id.pd_video, R.id.pd_pinpaizixun, R.id.pd_hangyebiaozhun, R.id.pd_hangyejishu, R.id.pd_wulianwang, R.id.pd_zhihuichengshi, R.id.pd_zhihuishequ, R.id.pd_wurenji, R.id.pd_zhinengyanglao, R.id.pd_zhinengjiankang, R.id.pd_zhinengyiliao, R.id.pd_jiqiren, R.id.pd_vrar, R.id.pd_zhanhuixinxi, R.id.pd_peixunxinxi, R.id.pd_zhichaofangan, R.id.pd_zhichaoanli})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pd_tuijian:
                toMain();
                finish();
                break;
            case R.id.pd_hangyezixun:
                break;
            case R.id.pd_canpinzixun:
                break;
            case R.id.pd_video:
                break;
            case R.id.pd_pinpaizixun:
                break;
            case R.id.pd_hangyebiaozhun:
                break;
            case R.id.pd_hangyejishu:
                break;
            case R.id.pd_wulianwang:
                break;
            case R.id.pd_zhihuichengshi:
                break;
            case R.id.pd_zhihuishequ:
                break;
            case R.id.pd_wurenji:
                break;
            case R.id.pd_zhinengyanglao:
                break;
            case R.id.pd_zhinengjiankang:
                break;
            case R.id.pd_zhinengyiliao:
                break;
            case R.id.pd_jiqiren:
                break;
            case R.id.pd_vrar:
                break;
            case R.id.pd_zhanhuixinxi:
                break;
            case R.id.pd_peixunxinxi:
                break;
            case R.id.pd_zhichaofangan:
                break;
            case R.id.pd_zhichaoanli:
                break;
        }
    }
}
