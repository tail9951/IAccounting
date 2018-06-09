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

    public void insert(String s, Integer month) {
        ContentValues values = new ContentValues();
        values.put(rdb._NUM,s.toString());//寫入資料
        values.put(rdb._DATE,month);
        db.insert(rdb.TB,null,values);
    }

    public void edit(long _id, String _num, Integer _date){
        ContentValues values = new ContentValues();
        if(_num != null){
            values.put(rdb._NUM, _num);
        }
        if(_date != null){
            values.put(rdb._DATE, _date);
        }
        db.update(rdb.TB, values,rdb._ID + "=" + _id, null);
    }
/*
    public Cursor query(String s){

    }
*/
    public void display(ListView lv){
        //ListView LV1 = (ListView)findViewById(R.id.LV); //讀取元件
        Cursor cursor = db.query(rdb.TB,new String[]{rdb._ID,rdb._NUM,rdb._DATE},null,null,null,null,null);
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put(rdb._ID,cursor.getString(0));
            item.put(rdb._NUM,cursor.getString(1));
            item.put(rdb._DATE,cursor.getString(2));
            items.add(item);
            cursor.moveToNext();
        }

        SimpleAdapter SA = new SimpleAdapter(ourcontext,items,android.R.layout.simple_expandable_list_item_2,new String[]{rdb._NUM,rdb._DATE},new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    public void delete(long _id) {
        db.delete(rdb.TB, rdb._ID + "=" + _id, null);
    }

}
