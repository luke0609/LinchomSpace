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
import linchom.com.linchomspace.shopping.GoodsListActivity;

public class NavigationActivity extends AppCompatActivity {


    @InjectView(R.id.iv_navigation_delete)
    ImageView ivNavigationDelete;
    @InjectView(R.id.pd_tuijian)
    Button pdTuijian;
    @InjectView(R.id.pd_hangyezixun)
    Button pdHangyezixun;
    @InjectView(R.id.pd_canpinpince)
    Button pdCanpinpince;
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
    @InjectView(R.id.pd_canpinzixun)
    Button pdCanpinzixun;
    @InjectView(R.id.chat_zhinengjiaju)

    Button chatZhinengjujia;
    @InjectView(R.id.chat_zhinengjiankang)
    Button chatZhinengjiankang;
    @InjectView(R.id.chat_zhinengzhaoguan)
    Button chatZhinengzhaoguan;
    @InjectView(R.id.chat_canpintuijie)
    Button chatCanpintuijie;
    @InjectView(R.id.chat_zhinengshequ)
    Button chatZhinengshequ;
    @InjectView(R.id.chat_gongchengjishu)
    Button chatGongchengjishu;
    @InjectView(R.id.chat_yingjiankaifa)
    Button chatYingjiankaifa;
    @InjectView(R.id.chat_jiqiren)
    Button chatJiqiren;
    @InjectView(R.id.chat_vrar)
    Button chatVrar;
    @InjectView(R.id.chat_xieyibiaozhun)
    Button chatXieyibiaozhun;
    @InjectView(R.id.chat_xuqiuduijie)
    Button chatXuqiuduijie;
    @InjectView(R.id.chat_pinpaijiaoliu)
    Button chatPinpaijiaoliu;
    @InjectView(R.id.chat_tiwenhuida)
    Button chatTiwenhuida;

    @InjectView(R.id.zc_kongzhi)
    Button zcKongzhi;
    @InjectView(R.id.zc_kaiguan)
    Button zcKaiguan;
    @InjectView(R.id.zc_chazuo)
    Button zcChazuo;
    @InjectView(R.id.zc_zhaoming)
    Button zcZhaoming;
    @InjectView(R.id.zc_menchuang)
    Button zcMenchuang;
    @InjectView(R.id.zc_anfang)
    Button zcAnfang;
    @InjectView(R.id.zc_shexiangtou)
    Button zcShexiangtou;
    @InjectView(R.id.zc_chuangganqi)
    Button zcChuangganqi;
    @InjectView(R.id.znjj_binxiang)
    Button znjjBinxiang;
    @InjectView(R.id.znjj_kongtiao)
    Button znjjKongtiao;
    @InjectView(R.id.znjj_xiyiji)
    Button znjjXiyiji;
    @InjectView(R.id.znjj_reshuiqi)
    Button znjjReshuiqi;
    @InjectView(R.id.znjj_dianshi)
    Button znjjDianshi;
    @InjectView(R.id.znjj_yinxiang)
    Button znjjYinxiang;
    @InjectView(R.id.znjj_jinshui)
    Button znjjJinshui;
    @InjectView(R.id.znjj_youyanzhaoju)
    Button znjjYouyanzhaoju;
    @InjectView(R.id.znjj_fancaiguoju)
    Button znjjFancaiguoju;
    @InjectView(R.id.znjj_xinfenji)
    Button znjjXinfenji;
    @InjectView(R.id.znjj_kongqijinhuaqi)
    Button znjjKongqijinhuaqi;
    @InjectView(R.id.znjj_qingjieweisheng)
    Button znjjQingjieweisheng;
    @InjectView(R.id.znjj_shenghuoxiaojiadian)
    Button znjjShenghuoxiaojiadian;
    @InjectView(R.id.znjj_matong)
    Button znjjMatong;
    @InjectView(R.id.znzg_laoren)
    Button znzgLaoren;
    @InjectView(R.id.znzg_yunfu)
    Button znzgYunfu;
    @InjectView(R.id.znzg_yiner)
    Button znzgYiner;
    @InjectView(R.id.znzg_ertong)
    Button znzgErtong;
    @InjectView(R.id.znzg_chongwu)
    Button znzgChongwu;
    @InjectView(R.id.znjk_tizhong)
    Button znjkTizhong;
    @InjectView(R.id.znjk_tiwen)
    Button znjkTiwen;
    @InjectView(R.id.znjk_xueya)
    Button znjkXueya;
    @InjectView(R.id.znjk_xuetang)
    Button znjkXuetang;
    @InjectView(R.id.znjk_xindian)
    Button znjkXindian;
    @InjectView(R.id.znjk_kouqiang)
    Button znjkKouqiang;
    @InjectView(R.id.znjk_shuimian)
    Button znjkShuimian;
    @InjectView(R.id.zncd_shouhuan)
    Button zncdShouhuan;
    @InjectView(R.id.zncd_shoubiao)
    Button zncdShoubiao;
    @InjectView(R.id.zncd_fushi)
    Button zncdFushi;
    @InjectView(R.id.zncd_yanjing)
    Button zncdYanjing;
    @InjectView(R.id.zncd_vrar)
    Button zncdVrar;
    @InjectView(R.id.zncd_erji)
    Button zncdErji;
    @InjectView(R.id.zncd_fangdiu)
    Button zncdFangdiu;
    @InjectView(R.id.zn_sheying)
    Button znSheying;
    @InjectView(R.id.zn_qiche)
    Button znQiche;
    @InjectView(R.id.zn_qixing)
    Button znQixing;
    @InjectView(R.id.zn_wurenji)
    Button znWurenji;
    @InjectView(R.id.zn_jiqiren)
    Button znJiqiren;
    @InjectView(R.id.zn_yundong)
    Button znYundong;
    @InjectView(R.id.zn_huwai)
    Button znHuwai;
    @InjectView(R.id.zn_yule)
    Button znYule;
    @InjectView(R.id.zn_qingqu)
    Button znQingqu;
    @InjectView(R.id.chat_qita)
    Button chatQita;
    @InjectView(R.id.znjj_qita)
    Button znjjQita;
    @InjectView(R.id.znzg_qita)
    Button znzgQita;
    @InjectView(R.id.znjk_qita)
    Button znjkQita;
    @InjectView(R.id.zncd_qita)
    Button zncdQita;
    @InjectView(R.id.zc_qita)
    Button zcQita;
    @InjectView(R.id.zn_qita)
    Button znQita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.inject(this);
    }

    private void toMain(String pdId) {
        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
        intent.putExtra("id", 1);
        intent.putExtra("pdId",pdId);
        startActivity(intent);
    }

    private void toChat(String chatId) {
        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
        intent.putExtra("idchat", 1);
        intent.putExtra("chatId",chatId);
        startActivity(intent);
    }


    private void sendShop(String cateId) {
        Intent intent = new Intent(NavigationActivity.this, GoodsListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("keyword", "");
        bundle.putString("cateId", cateId);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }


    @OnClick({R.id.chat_qita,R.id.zc_qita,R.id.znjj_qita,R.id.znzg_qita,R.id.znjk_qita,R.id.zncd_qita,R.id.zn_qita,R.id.iv_navigation_delete, R.id.pd_tuijian, R.id.pd_hangyezixun, R.id.pd_canpinpince, R.id.pd_video, R.id.pd_pinpaizixun, R.id.pd_hangyebiaozhun, R.id.pd_hangyejishu, R.id.pd_wulianwang, R.id.pd_zhihuichengshi, R.id.pd_zhihuishequ, R.id.pd_wurenji, R.id.pd_zhinengyanglao, R.id.pd_zhinengjiankang, R.id.pd_zhinengyiliao, R.id.pd_jiqiren, R.id.pd_vrar, R.id.pd_zhanhuixinxi, R.id.pd_peixunxinxi, R.id.pd_zhichaofangan, R.id.pd_zhichaoanli, R.id.pd_canpinzixun, R.id.chat_zhinengjiaju, R.id.chat_zhinengjiankang, R.id.chat_zhinengzhaoguan, R.id.chat_canpintuijie, R.id.chat_zhinengshequ, R.id.chat_gongchengjishu, R.id.chat_yingjiankaifa, R.id.chat_jiqiren, R.id.chat_vrar, R.id.chat_xieyibiaozhun, R.id.chat_xuqiuduijie, R.id.chat_pinpaijiaoliu, R.id.chat_tiwenhuida,  R.id.zc_kongzhi, R.id.zc_kaiguan, R.id.zc_chazuo, R.id.zc_zhaoming, R.id.zc_menchuang, R.id.zc_anfang, R.id.zc_shexiangtou, R.id.zc_chuangganqi, R.id.znjj_binxiang, R.id.znjj_kongtiao, R.id.znjj_xiyiji, R.id.znjj_reshuiqi, R.id.znjj_dianshi, R.id.znjj_yinxiang, R.id.znjj_jinshui, R.id.znjj_youyanzhaoju, R.id.znjj_fancaiguoju, R.id.znjj_xinfenji, R.id.znjj_kongqijinhuaqi, R.id.znjj_qingjieweisheng, R.id.znjj_shenghuoxiaojiadian, R.id.znjj_matong, R.id.znzg_laoren, R.id.znzg_yunfu, R.id.znzg_yiner, R.id.znzg_ertong, R.id.znzg_chongwu, R.id.znjk_tizhong, R.id.znjk_tiwen, R.id.znjk_xueya, R.id.znjk_xuetang, R.id.znjk_xindian, R.id.znjk_kouqiang, R.id.znjk_shuimian, R.id.zncd_shouhuan, R.id.zncd_shoubiao, R.id.zncd_fushi, R.id.zncd_yanjing, R.id.zncd_vrar, R.id.zncd_erji, R.id.zncd_fangdiu, R.id.zn_sheying, R.id.zn_qiche, R.id.zn_qixing, R.id.zn_wurenji, R.id.zn_jiqiren, R.id.zn_yundong, R.id.zn_huwai, R.id.zn_yule, R.id.zn_qingqu})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.zc_qita:
                sendShop("87");
                finish();
            break;
            case R.id.znjj_qita :
                sendShop("161");
                finish();

            break;
            case R.id.znzg_qita :
                sendShop("89");
                finish();
            break;
            case R.id.znjk_qita:
                sendShop("90");
                finish();
            break;
            case R.id.zncd_qita :
                sendShop("88");
                finish();
            break;
            case R.id.zn_qita:
                sendShop("44");
                finish();
            break;

            case R.id.pd_tuijian:
                toMain("0");
                finish();
                break;
            case R.id.pd_hangyezixun:
                toMain("1");
                finish();
                break;
            case R.id.pd_canpinzixun:
                toMain("2");
                finish();
                break;
            case R.id.pd_canpinpince:
                toMain("3");
                finish();
                break;
            case R.id.pd_video:
                toMain("4");
                finish();
                break;
            case R.id.pd_pinpaizixun:
                toMain("5");
                finish();
                break;

            case R.id.pd_hangyebiaozhun:
                toMain("6");
                finish();
                break;
            case R.id.pd_hangyejishu:
                toMain("7");
                finish();
                break;
            case R.id.pd_wulianwang:
                toMain("8");
                finish();
                break;
            case R.id.pd_zhihuichengshi:
                toMain("9");
                finish();
                break;
            case R.id.pd_zhihuishequ:
                toMain("10");
                finish();
                break;
            case R.id.pd_wurenji:
                toMain("11");
                finish();
                break;
            case R.id.pd_zhinengyanglao:
                toMain("12");
                finish();
                break;
            case R.id.pd_zhinengjiankang:
                toMain("13");
                finish();
                break;
            case R.id.pd_zhinengyiliao:
                toMain("14");
                finish();
                break;
            case R.id.pd_jiqiren:
                toMain("15");
                finish();
                break;
            case R.id.pd_vrar:
                toMain("16");
                finish();
                break;
            case R.id.pd_zhanhuixinxi:
                toMain("17");
                finish();
                break;
            case R.id.pd_peixunxinxi:
                toMain("18");
                finish();
                break;
            case R.id.pd_zhichaofangan:
                toMain("19");
                finish();
                break;
            case R.id.pd_zhichaoanli:
                toMain("20");
                finish();
                break;
            case R.id.iv_navigation_delete:
                NavigationActivity.this.finish();
                break;
            case R.id.chat_zhinengjiaju:
                toChat("0");
                finish();
                break;

            case R.id.chat_zhinengjiankang:
                toChat("1");
                finish();
                break;
            case R.id.chat_zhinengzhaoguan:
                toChat("2");
                finish();
                break;
            case R.id.chat_canpintuijie:
                toChat("3");
                finish();
                break;
            case R.id.chat_zhinengshequ:
                toChat("4");
                finish();
                break;
            case R.id.chat_gongchengjishu:
                toChat("5");
                finish();
                break;
            case R.id.chat_yingjiankaifa:
                toChat("6");
                finish();
                break;
            case R.id.chat_jiqiren:
                toChat("7");
                finish();
                break;
            case R.id.chat_vrar:
                toChat("8");
                finish();
                break;
            case R.id.chat_xieyibiaozhun:
                toChat("9");
                finish();
                break;
            case R.id.chat_xuqiuduijie:
                toChat("10");
                finish();
                break;
            case R.id.chat_pinpaijiaoliu:
                toChat("11");
                finish();
                break;
            case R.id.chat_tiwenhuida:
                toChat("12");
                finish();
                break;
            case R.id.chat_qita:
                toChat("13");
                finish();
                break;

            case R.id.zc_kongzhi:
                sendShop("54");
                break;
            case R.id.zc_kaiguan:
                sendShop("56");
                break;
            case R.id.zc_chazuo:
                sendShop("55");
                break;
            case R.id.zc_zhaoming:
                sendShop("57");
                break;
            case R.id.zc_menchuang:
                sendShop("60");
                break;
            case R.id.zc_anfang:
                sendShop("61");
                break;
            case R.id.zc_shexiangtou:
                sendShop("85");
                break;
            case R.id.zc_chuangganqi:
                sendShop("82");
                break;
            case R.id.znjj_binxiang:
                sendShop("150");
                break;
            case R.id.znjj_kongtiao:
                sendShop("152");
                break;
            case R.id.znjj_xiyiji:
                sendShop("151");
                break;
            case R.id.znjj_reshuiqi:
                sendShop("86");
                break;
            case R.id.znjj_dianshi:
                sendShop("153");
                break;
            case R.id.znjj_yinxiang:
                sendShop("58");
                break;
            case R.id.znjj_jinshui:
                sendShop("170");
                break;
            case R.id.znjj_youyanzhaoju:
                sendShop("157");
                break;
            case R.id.znjj_fancaiguoju:
                sendShop("156");
                break;
            case R.id.znjj_xinfenji:
                sendShop("154");
                break;
            case R.id.znjj_kongqijinhuaqi:
                sendShop("155");
                break;
            case R.id.znjj_qingjieweisheng:
                sendShop("158");
                break;
            case R.id.znjj_shenghuoxiaojiadian:
                sendShop("159");
                break;
            case R.id.znjj_matong:
                sendShop("160");
                break;
            case R.id.znzg_laoren:
                sendShop("76");
                break;
            case R.id.znzg_yunfu:
                sendShop("75");
                break;
            case R.id.znzg_yiner:
                sendShop("71");
                break;
            case R.id.znzg_ertong:
                sendShop("67");
                break;
            case R.id.znzg_chongwu:
                sendShop("93");
                break;
            case R.id.znjk_tizhong:
                sendShop("162");
                break;
            case R.id.znjk_tiwen:
                sendShop("77");
                break;
            case R.id.znjk_xueya:
                sendShop("64");
                break;
            case R.id.znjk_xuetang:
                sendShop("163");
                break;
            case R.id.znjk_xindian:
                sendShop("63");
                break;
            case R.id.znjk_kouqiang:
                sendShop("68");
                break;
            case R.id.znjk_shuimian:
                sendShop("72");
                break;
            case R.id.zncd_shouhuan:
                sendShop("51");
                break;
            case R.id.zncd_shoubiao:
                sendShop("52");
                break;
            case R.id.zncd_fushi:
                sendShop("78");
                break;
            case R.id.zncd_yanjing:
                sendShop("92");
                break;
            case R.id.zncd_vrar:
                sendShop("164");
                break;
            case R.id.zncd_erji:
                sendShop("79");
                break;
            case R.id.zncd_fangdiu:
                sendShop("91");
                break;
            case R.id.zn_sheying:
                sendShop("70");
                break;
            case R.id.zn_qiche:
                sendShop("69");
                break;
            case R.id.zn_qixing:
                sendShop("165");
                break;
            case R.id.zn_wurenji:
                sendShop("166");
                break;
            case R.id.zn_jiqiren:
                sendShop("167");
                break;
            case R.id.zn_yundong:
                sendShop("73");
                break;
            case R.id.zn_huwai:
                sendShop("168");
                break;
            case R.id.zn_yule:
                sendShop("84");
                break;
            case R.id.zn_qingqu:
                sendShop("169");
                break;
        }
    }


}
