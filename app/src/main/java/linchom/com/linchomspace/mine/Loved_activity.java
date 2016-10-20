package linchom.com.linchomspace.mine;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.LovedInfoBean;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

public class Loved_activity extends AppCompatActivity {

    public static final String TAG ="Loved_activity" ;

    private String goods_id;

//    private List<LovedInfoBean.GoodsMap> goodsList =new ArrayList<LovedInfoBean.GoodsMap>();
//    private GoodsCommonAdapter<LovedInfoBean.GoodsMap> goodsCommonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved_activity);

//        initData();
//        initEvent();
    }

//    public void initEvent(){
//        goodsCommonAdapter=new GoodsCommonAdapter<LovedInfoBean.GoodsMap>(getApplicationContext(),goodsList,R.layout.my_loved_activity) {
//            @Override
//            public void convert(GoodsViewHolder viewHolder, LovedInfoBean.GoodsMap goodsMap, int position) {
//                //ImageView iv_goodsList_item_image = ((ImageView) viewHolder.getViewById(R.id.iv_goodsList_item_image));
//                TextView market_price = ((TextView) viewHolder.getViewById(R.id.market_price));
//
//                 TextView shop_price = ((TextView) viewHolder.getViewById(R.id.shop_price));
//
//                TextView is_attention = ((TextView) viewHolder.getViewById(R.id.is_attention));
//
//                market_price.setText(goodsMap.market_price);
//                shop_price.setText(goodsMap.shop_price);
//                is_attention.setText(goodsMap.is_attention);
//                //GoodsXUtilsImage.display(iv_goodsList_item_image, GoodsHttpUtils.IMGURL+goods.goods_thumb);
//            }
//        };
//    }
//
//    public void initData(){
//        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php?act=collectionlist&");
//        x.http().post(requestParams, new Callback.CommonCallback<String>() {
//
//            @Override
//            public void onSuccess(String result) {
//
//
//                Gson gson = new Gson();
//                List<LovedInfoBean.GoodsMap> newList = new ArrayList<LovedInfoBean.GoodsMap>();
//                JsonParser parser = new JsonParser();
//
//                JsonElement element = parser.parse(result);
//
//                JsonObject root = element.getAsJsonObject();
//
//
//                JsonPrimitive resultjson = root.getAsJsonPrimitive("result");
//
//                String resultBean = resultjson.getAsString();
//
////                Log.i(TAG, "resultBean====" + resultBean);
//
//                JsonObject datajson = root.getAsJsonObject("data");
//
//                String str = datajson.toString();
////                System.out.println("str"+str);
//
//
//                JsonParser parser2 = new JsonParser();
//
//                JsonElement element2 = parser2.parse(str);
//
//                JsonObject root2 = element2.getAsJsonObject();
//
//                JsonPrimitive total_pages = root2.getAsJsonPrimitive("total_pages");
//
//                //pageCount = Integer.parseInt(total_pages.getAsString());
//
//
//                JsonObject items = root2.getAsJsonObject("items");
//
//
//                Map<String, LovedInfoBean.GoodsMap> mapNew = gson.fromJson(items, new TypeToken<Map<String, LovedInfoBean.GoodsMap>>() {
//                }.getType());
//
//                newList.clear();
//
//
//                for (Map.Entry<String, LovedInfoBean.GoodsMap> m : mapNew.entrySet()) {
//
//                    LovedInfoBean.GoodsMap goodsInfo = m.getValue();
//
//                    newList.add(goodsInfo);
//
//
//                }
//
//                goodsList.addAll(newList);
////                System.out.println("newlist"+newList);
////                System.out.println("newlist"+goodsList);
//                goodsCommonAdapter.notifyDataSetChanged();
//
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
}
