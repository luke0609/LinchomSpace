package linchom.com.linchomspace.shopping.GoodsSqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/10/22.
 */

public class JoinCartSQLiteOpenHelper extends SQLiteOpenHelper{

    private final static  String CARTDB = "cartdb";

    private final static  int CARTVERSION = 1;

    public JoinCartSQLiteOpenHelper(Context context) {
        super(context, CARTDB, null, CARTVERSION);
    }

    public JoinCartSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("Sqlite","Sqlite------onCreate");

        db.execSQL("create table cart(cartId Integer  primary key  autoincrement,sessionId varchar)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
