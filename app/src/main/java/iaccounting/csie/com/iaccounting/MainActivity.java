package iaccounting.csie.com.iaccounting;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Drawer{
    private ReceiptController rdbcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        rdbcon = new ReceiptController(this);
        rdbcon.insert("DB62608552","2016-05-06");
        rdbcon.insert("DB65621203","2016-04-16");
        rdbcon.insert("DB15234564","2016-03-26");
        rdbcon.insert("DB19995744","2017-05-12");
        rdbcon.insert("DB52342156","2016-04-13");
        rdbcon.insert("DB95620415","2016-06-06");
        rdbcon.insert("DB65156842","2017-05-30");
        rdbcon.insert("CB65212032","2016-04-16");
        rdbcon.insert("AB68432135","2016-04-16");
        rdbcon.insert("RB98512303","2016-04-16");
        ListView LV1 = (ListView)findViewById(R.id.LV); //讀取元件
        rdbcon.edit(3,"KF65621203", "2017-05-01");
        //rdbcon.delete(2);
        rdbcon.display(LV1);
        //rdbcon.single_display(LV1, rdbcon.query("2017-05"));
        Achievement gv = (Achievement) getApplicationContext();
        gv.checkReceiptNum(this);

    }



    /*private void add(String s,int month) {
        SQLiteDatabase db = rdb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_num",s.toString());//寫入資料
        values.put("_date",month);
        db.insert("TB1",null,values);

    }*/

    /*private void display() {
        SQLiteDatabase db = rdb.getWritableDatabase();
        ListView LV1 = (ListView)findViewById(R.id.LV); //讀取元件
        Cursor cursor = db.query("TB1",new String[]{"_id","_num","_date"},null,null,null,null,null);
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

        SimpleAdapter SA = new SimpleAdapter(this,items,android.R.layout.simple_expandable_list_item_2,new String[]{"_num","_date"},new int[]{android.R.id.text1,android.R.id.text2});
        LV1.setAdapter(SA);
    }*/

}
