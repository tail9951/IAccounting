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

    public void insert(String s, String month) {

        ContentValues values = new ContentValues();
        values.put(rdb._NUM,s.toString());      //寫入資料
        values.put(rdb._DATE,month.toString());
        db.insert(rdb.TB,null,values);

        //成就系統
        Achievement gv = (Achievement) ourcontext.getApplicationContext();
        gv.addReceiptNum();
    }

    public void edit(long _id, String _num, String _date){
        ContentValues values = new ContentValues();
        if(_num != null){
            values.put(rdb._NUM, _num);
        }
        if(_date != null){
            values.put(rdb._DATE, _date);
        }
        db.update(rdb.TB, values,rdb._ID + "=" + _id, null);
    }

    //輸入年月 ex:2017-05 回傳一個 2017-05的所有發票Cursor
    public Cursor query(String date){
        Cursor c = db.rawQuery("select * from TB1 where strftime('%Y-%m',_date)=?", new String[] {date});
        return c;
    }
    //使用id搜尋發票
    public Cursor query(long id){
        Cursor c = db.rawQuery("select * from TB1 where _id=?",new String[]{String.valueOf(id)});
        return c;
    }

    public void single_display(ListView lv,Cursor c){
        Cursor cursor = c;
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
