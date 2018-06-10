package iaccounting.csie.com.iaccounting;

import static android.provider.BaseColumns._ID;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "Account";
    public static final String TABLE_NAME2 = "Accounting";
    public final static String DATABASE_NAME = "AccountDB.db";
    public final static int DATABASE_VERSION = 2;  //資料庫版本
    public final static String ID = "_id";
    public final static String NAME = "name";
    public final static String TIME = "time";
    public final static String AMOUNT = "amount";
    public final static String DESCRIPTION = "description";
    public final static String CATEGORY = "category";
    public final static String ACC_ID = "acc_id";
    public final static String IN_OR_OUT = "in_or_out";
    public final static String ACC_NAME = "acc_name";
    public final static String CURRENCY = "currency";
    public final static String ACC_AMOUNT = "acc_amount";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            final String INIT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " CHAR, " + TIME + " DATETIME, " + AMOUNT + " INT, " + DESCRIPTION + " CHAR, " + CATEGORY + " INT, " + ACC_ID + " INT, " + IN_OR_OUT + " INT);";
            final String INIT_TABLE2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + " (" +ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACC_NAME + " CHAR, " + CURRENCY + " INT, " + ACC_AMOUNT + " INT);";
            db.execSQL(INIT_TABLE);
            db.execSQL(INIT_TABLE2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        final String DROP_TABLE2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_TABLE2);
        onCreate(db);
    }
}