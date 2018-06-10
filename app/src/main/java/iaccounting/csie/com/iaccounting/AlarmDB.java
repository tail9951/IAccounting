package iaccounting.csie.com.iaccounting;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDB extends SQLiteOpenHelper{
    public final static String DB="Alarm.db";  //資料庫
    public final static String TB="notify";       //資料表
    private final static int VS=2;              //版本
    public static final String _ID = "_id";
    public static final String _CONTENT = "_num";
    public static final String _DATE = "_date";


    public AlarmDB(Context context) {
        //super(context, name, factory, version);
        super(context, DB, null, VS);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL = "CREATE TABLE " + TB +
                " (" +_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                _DATE + " String, " + _CONTENT + " String);";
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL = "DROP TABLE " + TB;
        sqLiteDatabase.execSQL(SQL);
    }
}
