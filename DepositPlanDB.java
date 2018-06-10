package iaccounting.csie.com.iaccounting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DepositPlanDB extends SQLiteOpenHelper{
    public static String DB="DepositPlan.db";     //資料庫
    public static String PlanTB="Planning";           //資料表 : 計畫
    public static String DepositTB="Deposit";     //資料表 : 儲蓄
    private final static int VS=2;              //版本
    public static String _PID = "_PId";
    public static String _GOAL = "_goal";
    public static String _PNAME = "_Pname";
    public static String _DESCRIPTION = "_description";
    public static String _DID = "_DId";
    public static String _AMOUNT = "_amount";
    public static String _TIME = "_time";
    public static String _DNAME = "_Dname";

    public DepositPlanDB(Context context) {
        //super(context, name, factory, version);
        super(context,DB,null,VS);
    }

    @Override                                                                                                                                   // FOREIGN KEY("category_id") REFERENCES note_category("category_id")
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_Plan = "CREATE TABLE IF NOT EXISTS " + PlanTB + "(_PId INTEGER PRIMARY KEY AUTOINCREMENT ,_Pname VARCHAR(15) ,_description VARCHAR(50) ,_goal INTEGER)";
        String SQL_Deposit = "CREATE TABLE IF NOT EXISTS " + DepositTB + "(_DId INTEGER PRIMARY KEY AUTOINCREMENT , _Dname VARCHAR(15)  ,_time DATETIME , _amount INTEGER , _PId INTEGER , FOREIGN KEY ("+_PID+") REFERENCES Planning("+_PID+") ON DELETE CASCADE)";


        sqLiteDatabase.execSQL(SQL_Plan);
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        sqLiteDatabase.execSQL(SQL_Deposit);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String SQL_Plan = "DROP TABLE IF EXISTS " + PlanTB;
        final String SQL_Deposit = "DROP TABLE IF EXISTS " + DepositTB;
        sqLiteDatabase.execSQL(SQL_Plan);
        sqLiteDatabase.execSQL(SQL_Deposit);
    }

}
