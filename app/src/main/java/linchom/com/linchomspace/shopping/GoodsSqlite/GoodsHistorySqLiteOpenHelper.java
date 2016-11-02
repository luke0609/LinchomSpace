package linchom.com.linchomspace.shopping.GoodsSqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/11/1.
 */

public class GoodsHistorySqLiteOpenHelper extends SQLiteOpenHelper{

    private final static  String GOODSDBNAME="goods";
    private final static int  GOODSVERSION=1;
    private static final String TAG = "GoodsHistorySqLiteOpenHelper";


    public GoodsHistorySqLiteOpenHelper(Context context) {
        super(context, GOODSDBNAME, null, GOODSVERSION);
    }

    public GoodsHistorySqLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String goodsStr = "CREATE TABLE IF NOT EXISTS goodshistory (_id INTEGER PRIMARY KEY autoincrement, goodsname varchar(20))";


        db.execSQL(goodsStr);

        Log.i(TAG,"===============oncreate");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
