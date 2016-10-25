package linchom.com.linchomspace.mine.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import linchom.com.linchomspace.mine.pojo.LovedInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.R;

/**
 * Created by lenovo on 2016/10/20.
 */
public class goods_fragment extends Fragment {

    private List<LovedInfoBean.GoodsMap> goodsList =new ArrayList<LovedInfoBean.GoodsMap>();
    private GoodsCommonAdapter<LovedInfoBean.GoodsMap> goodsCommonAdapter;
    private ListView lv_goods;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_goodsfragment, null);
        //拿到控件
        lv_goods = ((ListView) view.findViewById(R.id.lv_goods));
        initData();
        initEvent();
        return  view;


    }
    public void initEvent(){
        //数据放进去
        goodsCommonAdapter=new GoodsCommonAdapter<LovedInfoBean.GoodsMap>(getActivity(),goodsList,R.layout.my_loved_activity) {
            @Override
            public void convert(GoodsViewHolder viewHolder, LovedInfoBean.GoodsMap goodsMap, int position) {
                //ImageView iv_goodsList_item_image = ((ImageView) viewHolder.getViewById(R.id.iv_goodsList_item_image));
                TextView goods_name = ( viewHolder.getViewById(R.id.goods_name));

                TextView market_price = (viewHolder.getViewById(R.id.market_price));

                TextView promote_price = (viewHolder.getViewById(R.id.promote_price));

                goods_name.setText(goodsMap.goods_name);
                market_price.setText(goodsMap.market_price);
//                Toast.makeText(getActivity(),"hh",Toast.LENGTH_SHORT).show();
                promote_price.setText(goodsMap.promote_price);
                //GoodsXUtilsImage.display(iv_goodsList_item_image, GoodsHttpUtils.IMGURL+goods.goods_thumb);
            }
        };
        initData();
        //放到适配器中在页面显示
        lv_goods.setAdapter( goodsCommonAdapter);

    }
    // 从服务器拿到数据，解析
    public void initData(){
        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php?act=collectionlist&");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {


                Gson gson = new Gson();
                List<LovedInfoBean.GoodsMap> newList = new ArrayList<LovedInfoBean.GoodsMap>();
                JsonParser parser = new JsonParser();

                JsonElement element = parser.parse(result);

                JsonObject root = element.getAsJsonObject();


                JsonPrimitive resultjson = root.getAsJsonPrimitive("result");

                String resultBean = resultjson.getAsString();

//                Log.i(TAG, "resultBean====" + resultBean);

                JsonObject datajson = root.getAsJsonObject("data");

                String str = datajson.toString();
                System.out.println("str"+str);


                JsonParser parser2 = new JsonParser();

                JsonElement element2 = parser2.parse(str);

                JsonObject root2 = element2.getAsJsonObject();

                JsonPrimitive total_pages = root2.getAsJsonPrimitive("total_pages");

                //pageCount = Integer.parseInt(total_pages.getAsString());


                JsonObject items = root2.getAsJsonObject("items");


                Map<String, LovedInfoBean.GoodsMap> mapNew = gson.fromJson(items, new TypeToken<Map<String, LovedInfoBean.GoodsMap>>() {
                }.getType());

                newList.clear();

//解析过的数据放到对象里
                for (Map.Entry<String, LovedInfoBean.GoodsMap> m : mapNew.entrySet()) {

                    LovedInfoBean.GoodsMap goodsInfo = m.getValue();
// 将对象数据放到list
                    newList.add(goodsInfo);


                }

                goodsList.addAll(newList);
                System.out.println("newlist"+newList);
                System.out.println("newlist"+goodsList);
//数据更新
                goodsCommonAdapter.notifyDataSetChanged();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
