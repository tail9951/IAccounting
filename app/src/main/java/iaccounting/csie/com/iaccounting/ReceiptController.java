package iaccounting.csie.com.iaccounting;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReceiptController {
    private ReceiptDB rdb;
    private Context ourcontext;
    private SQLiteDatabase db;

    public ReceiptController(Context c) {
        ourcontext = c;
        rdb = new ReceiptDB(c);
        db = rdb.getWritableDatabase();
    }


    public void close() {
        rdb.close();
    }

    public void insert(String s, int month) {
        ContentValues values = new ContentValues();
        values.put("_num",s.toString());//寫入資料
        values.put("_date",month);
        db.insert(rdb.TB,null,values);
    }

    public void display(ListView lv){
        //ListView LV1 = (ListView)findViewById(R.id.LV); //讀取元件
        Cursor cursor = db.query(rdb.TB,new String[]{"_id","_num","_date"},null,null,null,null,null);
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("_id",cursor.getString(0));
            item.put("_num",cursor.getString(1));
            item.put("_date",cursor.getString(2));
            items.add(item);
            cursor.moveToNext();
        }

        SimpleAdapter SA = new SimpleAdapter(ourcontext,items,android.R.layout.simple_expandable_list_item_2,new String[]{"_num","_date"},new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    public void delete(long _id) {
        db.delete(rdb.TB, rdb._ID + "=" + _id, null);
    }

}
