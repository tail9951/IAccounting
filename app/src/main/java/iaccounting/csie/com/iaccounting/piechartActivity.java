package iaccounting.csie.com.iaccounting;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.List;
import java.util.ArrayList;

public class piechartActivity extends AppCompatActivity {

    public static final int FOOD= 1;
    public static final int CLOTHING = 2;
    public static final int HOUSING = 3;
    public static final int TRANSPORTATION = 4;
    public static final int ENTERTAINMENT = 5;
    public static final int EDUCATION = 6;
    public static final int MEDICAL = 7;
    public static final int OTHER = 8;
    private static String TAG = "piechartActivity";
    //int [] myarray = new int[num_elts];
    private String xData[] = {"FOOD","CLOTHING","HOUSING","TRANSPORTATION","ENTERTAINMENT","EDUCATION","MEDICAL","OTHER"};
    ArrayList<Integer> yData = new ArrayList<Integer>();

    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountController ac= new AccountController(this);
        /*ac.insert("omel","2018-03-06",100,"dddddd",6,2,1);
        ac.insert("omel","2018-03-07",100,"dddddd",1,2,1);
        ac.insert("omel","2018-03-08",100,"dddddd",3,2,1);
        ac.insert("omel","2018-03-09",100,"dddddd",4,2,1);
        ac.insert("omel","2018-03-10",100,"dddddd",8,2,1);
        ac.insert("omel","2018-03-11",100,"dddddd",6,2,1);*/
        for(Integer i = 0 ; i < 8;i++) {
            Cursor c=ac.query_m_sum("2018-03",i+1);
            if(c.moveToFirst())
            {
                yData.add(c.getInt(0));
            }
        }
        setContentView(R.layout.activity_piechart);
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        //pieChart.setDescription("Sales by employee (In Thousands $) ");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("收入");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());
                int pos1 = e.toString().indexOf("y: ");
                String sales = e.toString().substring(pos1 + 3);

                for(int i = 0; i < yData.size(); i++){
                    if(yData.get(i) == Float.parseFloat(sales)){
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1];
                Toast.makeText(piechartActivity.this, "分類: " + employee + "\n" + "金額: $" + sales, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.size(); i++){
            yEntrys.add(new PieEntry(yData.get(i) , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"收入");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.LTGRAY);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
