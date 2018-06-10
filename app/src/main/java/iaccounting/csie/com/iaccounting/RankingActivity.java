/*
 * Copyright (C) 2017 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package iaccounting.csie.com.iaccounting;
//package iaccounting.csie.com.iaccounting;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//import com.skydoves.medal.MedalAnimation;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankling);

        ListView listView = (ListView)findViewById(R.id.listView);

        Achievement gv = (Achievement) getApplicationContext();
        int receipt_num=gv.getReceiptNum();
        int diary_num=gv.getDiaryNum();
        int account_num=gv.getAccountNum();
        ArrayList<Listviewitem> items = new ArrayList<>();
        ListviewAdapter adapter = new ListviewAdapter(this, R.layout.item_rank, items);

        if(receipt_num>=5)
        {
            Listviewitem item0 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal10), ContextCompat.getDrawable(this, R.drawable.face1), "發票達人", "發票達成5張");
            items.add(item0);
        }
        if(receipt_num>=10)
        {
            Listviewitem item1 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal9), ContextCompat.getDrawable(this, R.drawable.face2), "發票王", "發票達成10張");
            items.add(item1);
        }
        if(receipt_num>=15)
        {
            Listviewitem item2 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal2), ContextCompat.getDrawable(this, R.drawable.face3), "發票神", "發票達成15張");
            items.add(item2);
        }

        if(account_num>=5)
        {
            Listviewitem item3 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal10), ContextCompat.getDrawable(this, R.drawable.face1), "記帳達人", "記帳累計5筆");
            items.add(item3);
        }
        if(account_num>=10)
        {
            Listviewitem item4 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal9), ContextCompat.getDrawable(this, R.drawable.face2), "記帳王", "記帳累計10筆");
            items.add(item4);
        }
        if(account_num>=15)
        {
            Listviewitem item5 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal4), ContextCompat.getDrawable(this, R.drawable.face3), "記帳神", "記帳累計15筆");
            items.add(item5);
        }
        if(diary_num>=1)
        {
            Listviewitem item6 = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal5), ContextCompat.getDrawable(this, R.drawable.face3), "日記初心者", "完成一篇日記!");
            items.add(item6);
        }
        if(receipt_num<5)
        {
            Listviewitem item = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal7), ContextCompat.getDrawable(this, R.drawable.profile), "隱藏成就" , "發票成就尚未達成");
            items.add(item);
        }
        if(account_num<5)
        {
            Listviewitem item = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal7), ContextCompat.getDrawable(this, R.drawable.profile), "隱藏成就" , "記帳成就尚未達成");
            items.add(item);
        }
        if(diary_num==0)
        {
            Listviewitem item = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal7), ContextCompat.getDrawable(this, R.drawable.profile), "隱藏成就" , "日記成就尚未達成");
            items.add(item);
        }


        /*for(int i=0; i<5; i++) {
            Listviewitem item = new Listviewitem(
                    ContextCompat.getDrawable(this, R.drawable.medal7), ContextCompat.getDrawable(this, R.drawable.profile), "Person" + i, "This is sample listView Item.");
            items.add(item);
        }*/

        listView.setAdapter(adapter);
    }

    /*public void fab(View v) {
        startActivity(new Intent(this, ExampleActivity.class));
    }*/

    private class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Listviewitem> data;
        private int layout;

        public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
            this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data=data;
            this.layout=layout;
        }

        @Override
        public int getCount(){return data.size();}

        @Override
        public String getItem(int position){return data.get(position).getContent();}

        @Override
        public long getItemId(int position){return position;}

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            if(convertView==null)
                convertView=inflater.inflate(layout,parent,false);

            Listviewitem listviewitem = data.get(position);

            /*MedalAnimation medalAnimation = new MedalAnimation.Builder()
                    .setSpeed(4200)
                    .setTurn(4)
                    .build();*/

            ImageView imageView_medal = (ImageView)convertView.findViewById(R.id.medal);
            imageView_medal.setImageDrawable(listviewitem.getMedal());
            //imageView_medal.startAnimation(medalAnimation);

            ImageView imageView_profile = (ImageView)convertView.findViewById(R.id.profile);
            imageView_profile.setImageDrawable(listviewitem.getProfile());

            TextView textView_name = (TextView)convertView.findViewById(R.id.name);
            textView_name.setText(listviewitem.getName());

            TextView textView_content = (TextView)convertView.findViewById(R.id.content);
            textView_content.setText(listviewitem.getContent());

            return convertView;
        }
    }

    private class Listviewitem {
        private Drawable medal;
        private Drawable profile;
        private String name;
        private String content;

        public Listviewitem(Drawable medal, Drawable profile, String name, String content){
            this.medal = medal;
            this.profile = profile;
            this.name = name;
            this.content = content;
        }

        public Drawable getMedal() {
            return medal;
        }

        private Drawable getProfile() {
            return profile;
        }

        private String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }
    }
}
