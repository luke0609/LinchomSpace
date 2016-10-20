package linchom.com.linchomspace.shopping.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/20.
 */

public class GoodsOrderBean implements Serializable {

    public String goodsNum;
    public String goodsImg;
    public String goodsName;
    public String goodsPrice;

    public GoodsOrderBean(String goodsNum, String goodsImg, String goodsName, String goodsPrice) {
        this.goodsNum = goodsNum;
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }



}
