package linchom.com.linchomspace.shopping.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/10/20.
 */

public class GoodsOrderBean implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.goodsNum);
        dest.writeString(this.goodsImg);
        dest.writeString(this.goodsName);
        dest.writeString(this.goodsPrice);
    }

    protected GoodsOrderBean(Parcel in) {
        this.goodsNum = in.readString();
        this.goodsImg = in.readString();
        this.goodsName = in.readString();
        this.goodsPrice = in.readString();
    }

    public static final Parcelable.Creator<GoodsOrderBean> CREATOR = new Parcelable.Creator<GoodsOrderBean>() {
        @Override
        public GoodsOrderBean createFromParcel(Parcel source) {
            return new GoodsOrderBean(source);
        }

        @Override
        public GoodsOrderBean[] newArray(int size) {
            return new GoodsOrderBean[size];
        }
    };
}
