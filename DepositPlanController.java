package iaccounting.csie.com.iaccounting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositPlanController {
    private DepositPlanDB DPdb;
    private Context ourcontext;
    private SQLiteDatabase db;

    public DepositPlanController(Context c){
        ourcontext = c;
        DPdb = new DepositPlanDB(c);
        db = DPdb.getWritableDatabase();
    }

    public void close(){DPdb.close();}

    public void insertPlan(String _name, String _description, Integer _goal){
        ContentValues values = new ContentValues();
        values.put(DPdb._PNAME, _name);
        values.put(DPdb._DESCRIPTION, _description);
        values.put(DPdb._GOAL, _goal);
        db.insert(DPdb.PlanTB,null,values);
    }

    //預設 新增Deposit時只用" 當下時間 "為準
    public void insertDeposit(String _name, Integer _amount, Integer _PId){
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt=new Date();
        String dts=sdf.format(dt);
        values.put(DPdb._DNAME, _name);
        values.put(DPdb._AMOUNT, _amount);
        values.put(DPdb._TIME, dts);
        values.put(DPdb._PID,_PId);
        db.insert(DPdb.DepositTB,null,values);
    }

    public void deletePlan(long _id){
        db.delete(DPdb.PlanTB,DPdb._PID + "=" + _id,null);
    }


    public void deleteDeposit(long _id){
        db.delete(DPdb.DepositTB,DPdb._DID + "=" + _id,null);
    }

    public void displayPlan(ListView lv, Cursor c){
        Cursor cursor = c;
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put(DPdb._PID,cursor.getString(0));
            item.put(DPdb._PNAME,cursor.getString(1));
            item.put(DPdb._DESCRIPTION,cursor.getString(2));
            item.put(DPdb._GOAL,cursor.getString(3));
            items.add(item);
            cursor.moveToNext();
        }
        SimpleAdapter SA = new SimpleAdapter(ourcontext,items,android.R.layout.simple_expandable_list_item_2,new String[]{DPdb._PNAME,DPdb._DESCRIPTION},new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    public void displayDeposit(ListView lv, Cursor c){
        Cursor cursor = c;
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        cursor.moveToFirst();

        for(int i = 0;i < cursor.getCount();i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put(DPdb._DID,cursor.getString(0));
            item.put(DPdb._DNAME,cursor.getString(1));
            item.put(DPdb._AMOUNT,cursor.getString(2));
            item.put(DPdb._TIME,cursor.getString(3));
            items.add(item);
            cursor.moveToNext();
        }
        SimpleAdapter SA = new SimpleAdapter(ourcontext,items,android.R.layout.simple_expandable_list_item_2,new String[]{DPdb._DNAME,DPdb._TIME},new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(SA);
    }

    //搜尋出所有的Plan
    public Cursor queryAllPlan(){
        Cursor c = db.rawQuery("select * from " + DPdb.PlanTB,null);
        return c;
    }
    //搜尋出某一個Plan
    public Cursor queryPlan(long _pid){
        Cursor c = db.rawQuery("select * from " + DPdb.PlanTB + " where " + DPdb._PID + "=" + _pid,null);
        return c;
    }
    //搜尋出某個Plan中所有的deposit
    public Cursor queryAllDeposit(long _pid){
        Cursor c = db.rawQuery("select * from " + DPdb.DepositTB + " where " + DPdb._PID + "=" + _pid,null);
        return c;
    }
    //搜尋出其中一個deposit
    public Cursor queryDeposit(long _did){
        Cursor c = db.rawQuery("select * from " + DPdb.DepositTB + " where " + DPdb._DID + "=" + _did,null);
        return c;
    }

    public void editPlan(long _id, String _name, String _description, Integer _goal){
        ContentValues values = new ContentValues();
        if(_name != null){
            values.put(DPdb._PNAME, _name);
        }
        if(_description != null){
            values.put(DPdb._DESCRIPTION, _description);
        }
        if(_description != null){
            values.put(DPdb._GOAL, _goal);
        }
        db.update(DPdb.PlanTB, values,DPdb._PID + "=" + _id, null);
    }

    public void editDeposit(long _id, String _name, Integer _amount){
        ContentValues values = new ContentValues();
        if(_name != null){
            values.put(DPdb._PNAME, _name);
        }
        if(_amount != null){
            values.put(DPdb._AMOUNT, _amount);
        }
        db.update(DPdb.DepositTB, values,DPdb._DID + "=" + _id, null);
    }
}
