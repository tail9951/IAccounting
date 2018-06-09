package iaccounting.csie.com.iaccounting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReceiptDB extends SQLiteOpenHelper{
    public final static String DB="Receipt.db";  //資料庫
    public final static String TB="TB1";       //資料表
    private final static int VS=2;              //版本
    public static final String _ID = "_id";
    public static final String _NUM = "_num";
    public static final String _DATE = "_date";


    public ReceiptDB(Context context) {
        //super(context, name, factory, version);
        super(context, DB, null, VS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL = "CREATE TABLE IF NOT EXISTS " + TB + "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,_num VARCHAR(15) ,_date INTEGER)";
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL = "DROP TABLE " + TB;
        sqLiteDatabase.execSQL(SQL);
    }
}
