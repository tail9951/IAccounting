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


public class AlarmController {
    private AlarmDB adb;
    private Context ourcontext;
    private SQLiteDatabase db;

    public AlarmController(Context c) {
        ourcontext = c;
        adb = new AlarmDB(c);
        db = adb.getWritableDatabase();

    }


    public void close() {
        adb.close();
    }

    public void insert(String date, String content) {
        ContentValues values = new ContentValues();
        values.put(adb._DATE,date);      //寫入資料
        values.put(adb._CONTENT,content);
        db.insert(adb.TB,null,values);
    }

    public void edit(long _id, String date, String content){
        ContentValues values = new ContentValues();
        if(date != null){
            values.put(adb._DATE, date);
        }
        if(content != null){
            values.put(adb._CONTENT, content);
        }
        db.update(adb.TB, values,adb._ID + "=" + _id, null);
    }

    //輸入日期和時間
    public Cursor query(String date){
        Cursor c = db.rawQuery("select * from TB where TB._DATE = ?", new String[] {date});
        return c;
    }

    public void single_display(ListView lv,Cursor c){
        Cursor cursor = c;
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put(adb._ID,cursor.getString(0));
            item.put(adb._DATE,cursor.getString(1));
            item.put(adb._CONTENT,cursor.getString(2));
            items.add(item);
            cursor.moveToNext();
        }

        SimpleAdapter SA = new SimpleAdapter(ourcontext,items,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{adb._DATE,adb._CONTENT},
                new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    public void display(ListView lv){
        //ListView LV1 = (ListView)findViewById(R.id.LV); //讀取元件
        Cursor cursor = db.query(adb.TB,new String[]{adb._ID,adb._DATE,adb._CONTENT},null,null,null,null,null);
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put(adb._ID,cursor.getString(0));
            item.put(adb._DATE,cursor.getString(1));
            item.put(adb._CONTENT,cursor.getString(2));
            items.add(item);
            cursor.moveToNext();
        }

        SimpleAdapter SA = new SimpleAdapter(ourcontext,items,android.R.layout.simple_expandable_list_item_2,new String[]{adb._DATE,adb._CONTENT},new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    public void delete(long _id) {
        db.delete(adb.TB, adb._ID + "=" + _id, null);
    }

}
