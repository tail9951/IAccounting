package iaccounting.csie.com.iaccounting;
import static iaccounting.csie.com.iaccounting.DBHelper.ID;
import static iaccounting.csie.com.iaccounting.DBHelper.NAME;
import static iaccounting.csie.com.iaccounting.DBHelper.TIME;
import static iaccounting.csie.com.iaccounting.DBHelper.AMOUNT;
import static iaccounting.csie.com.iaccounting.DBHelper.DESCRIPTION;
import static iaccounting.csie.com.iaccounting.DBHelper.CATEGORY;
import static iaccounting.csie.com.iaccounting.DBHelper.ACC_ID;
import static iaccounting.csie.com.iaccounting.DBHelper.IN_OR_OUT;
import static iaccounting.csie.com.iaccounting.DBHelper.ACC_NAME;
import static iaccounting.csie.com.iaccounting.DBHelper.CURRENCY;
import static iaccounting.csie.com.iaccounting.DBHelper.ACC_AMOUNT;
import static iaccounting.csie.com.iaccounting.DBHelper.TABLE_NAME;
import static iaccounting.csie.com.iaccounting.DBHelper.TABLE_NAME2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AccountController {
    private DBHelper adb;
    private Context mycontext;
    private SQLiteDatabase db;

    public AccountController(Context c) {
        mycontext = c;
        adb = new DBHelper(c);
        db = adb.getWritableDatabase();
    }

    public void close() {
        adb.close();
    }

    public void insert_acc(String acc_name,Integer currency,Integer acc_amount) {
        ContentValues values = new ContentValues();
        values.put(ACC_NAME, acc_name);
        values.put(CURRENCY, currency);
        values.put(ACC_AMOUNT, acc_amount);
        db.insert(TABLE_NAME2, null, values);
    }

    public void insert(String name, String time, Integer amount, String description,
                       Integer category, Integer acc_id, Integer in_or_out) {
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(TIME, time);
        values.put(AMOUNT, amount);
        values.put(DESCRIPTION, description);
        values.put(CATEGORY, category);
        values.put(ACC_ID, acc_id);
        values.put(IN_OR_OUT, in_or_out);
        db.insert(TABLE_NAME, null, values);

        //成就系統
        Achievement gv = (Achievement) mycontext.getApplicationContext();
        gv.addAccountNum();
    }
    public void delete_acc(long _id) {
        db.delete(TABLE_NAME2, ACC_ID + "=" + _id, null);
    }
    public void delete(long _id) {
        db.delete(TABLE_NAME, ID + "=" + _id, null);
    }

    public void display(ListView lv){
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,
                null,null);
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("ID",cursor.getString(0));
            item.put("NAME",cursor.getString(1));
            item.put("TIME",cursor.getString(2));
            item.put("AMOUNT",cursor.getString(3));
            item.put("DESCRIPTION",cursor.getString(4));
            item.put("CATEGORY",cursor.getString(5));
            item.put("ACC_ID",cursor.getString(6));
            item.put("IN_OR_OUT",cursor.getString(7));
            items.add(item);
            cursor.moveToNext();
        }
        SimpleAdapter SA = new SimpleAdapter(mycontext,items,
                android.R.layout.simple_expandable_list_item_2,new String[]{"CATEGORY","TIME"},
                new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    public void display_q(ListView lv,Cursor c){
        Cursor cursor = c;
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("ID",cursor.getString(0));
            item.put("NAME",cursor.getString(1));
            item.put("TIME",cursor.getString(2));
            item.put("AMOUNT",cursor.getString(3));
            item.put("DESCRIPTION",cursor.getString(4));
            item.put("CATEGORY",cursor.getString(5));
            item.put("ACC_ID",cursor.getString(6));
            item.put("IN_OR_OUT",cursor.getString(7));
            items.add(item);
            cursor.moveToNext();
        }
        SimpleAdapter SA = new SimpleAdapter(mycontext,items,
                android.R.layout.simple_expandable_list_item_2,new String[]{"CATEGORY","TIME"},
                new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }
    //修改帳戶明細
    public void edit(long _id,String name, String time, Integer amount, String description,
                     Integer category, Integer acc_id, Integer in_or_out) {
        ContentValues values = new ContentValues();
        if (name != null) {
            values.put(NAME, name);
        }
        if (time != null) {
            values.put(TIME, time);
        }
        if (amount != null) {
            values.put(AMOUNT, amount);
        }
        if (description != null) {
            values.put(DESCRIPTION, description);
        }
        if (category != null) {
            values.put(CATEGORY, category);
        }
        if (acc_id != null) {
            values.put(ACC_ID, acc_id);
        }
        if (in_or_out != null) {
            values.put(IN_OR_OUT, in_or_out);
        }
        db.update(TABLE_NAME, values, ID + "=" + _id, null);
    }

    public Cursor query_acc(long _id) {
        Cursor c=db.rawQuery("SELECT * FROM " + TABLE_NAME2 +" WHERE "+ ACC_ID + "=" + _id,null);
        return c;
    }
    //顯示所有帳戶資訊
    public void display_acc(ListView lv){
        Cursor cursor =  db.query(TABLE_NAME2,null,null,null,null,
                null,null);;
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("ACC_ID",cursor.getString(0));
            item.put("ACC_NAME",cursor.getString(1));
            item.put("CURRENCY",cursor.getString(2));
            item.put("ACC_AMOUNT",cursor.getString(3));
            items.add(item);
            cursor.moveToNext();
        }
        SimpleAdapter SA = new SimpleAdapter(mycontext,items,
                android.R.layout.simple_expandable_list_item_2,new String[]{"ACC_ID","ACC_NAME"},
                new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }
    //顯示所有帳戶查詢
    public void display_acc_q(ListView lv,Cursor c){
        Cursor cursor = c;
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("ACC_ID",cursor.getString(0));
            item.put("ACC_NAME",cursor.getString(1));
            item.put("CURRENCY",cursor.getString(2));
            item.put("ACC_AMOUNT",cursor.getString(3));
            items.add(item);
            cursor.moveToNext();
        }
        SimpleAdapter SA = new SimpleAdapter(mycontext,items,
                android.R.layout.simple_expandable_list_item_2,new String[]{"ACC_AMOUNT","ACC_ID"},
                new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    //查詢明細(年)+分類
    public Cursor query_y(String year,Integer category){
        if(category == null) {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where strftime('%Y',time) = ?", new String[]{year});
            return c;
        }
        else {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where strftime('%Y',time) = ? AND category =" + category, new String[]{year});
            return c;
        }
    }
    //查詢明細(月)+分類
    public Cursor query_m(String month,Integer category){
        if(category == null) {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where strftime('%Y-%m',time) = ?", new String[]{month});
            return c;
        }
        else {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where strftime('%Y-%m',time) = ? AND category =" + category, new String[]{month});
            return c;
        }
    }
    //查詢明細(日)+分類
    public Cursor query_d(String date,Integer category) {
        if (category == null) {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where strftime('%Y-%m-%d',time) = ?", new String[]{date});
            return c;
        } else {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where strftime('%Y-%m-%d',time) = ? AND category =" + category, new String[]{date});
            return c;
        }
    }
    
       public Cursor query_m_sum(String month,Integer category){
        Cursor c = db.rawQuery("select SUM(amount) from " + TABLE_NAME + " where strftime('%Y-%m',time) = ? AND category =" + category, new String[]{month});
        return c;
    }
}
