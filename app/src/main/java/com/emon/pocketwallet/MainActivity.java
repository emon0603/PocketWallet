package com.emon.pocketwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.seosh817.circularseekbar.CircularSeekBar;
import com.seosh817.circularseekbar.CircularSeekBarAnimation;
import com.seosh817.circularseekbar.callbacks.OnProgressChangedListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    CircularSeekBar circularSeekBar;
    private GridView gridView;
    private TextView totalbalance;
    private HashMap<String,String> hashMap;

    private ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circularSeekBar = findViewById(R.id.circular_seek_bar);
        gridView = findViewById(R.id.gridView);
        totalbalance = findViewById(R.id.totalbalance);

        dbhelper = new DatabaseHelper(this);
        double total = dbhelper.IncomeTotalBalance() - dbhelper.ExpenseTotalBalance();
        totalbalance.setText(""+total);




        ListHashMap();

        MyAdaper myAdaper = new MyAdaper();
        gridView.setAdapter(myAdaper);

        gridView.setEnabled(false);
        TotalBalanceMethod();



        circularSeekBar.setOnProgressChangedListener(new OnProgressChangedListener() {
            @Override
            public void onProgressChanged(float v) {
            }
        });

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        TotalBalanceMethod();

    }

    private class MyAdaper extends BaseAdapter{


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.item_dash, parent,false);
            MaterialCardView materialCardView = myview.findViewById(R.id.cardview);
            ImageView icontv = myview.findViewById(R.id.icontv);
            TextView titletv = myview.findViewById(R.id.titletv);


            hashMap= arrayList.get(position);

            String Title = hashMap.get("Title");
            String background = hashMap.get("background");
            String icon = hashMap.get("icon");


            icontv.setImageResource(Integer.parseInt(icon));
            titletv.setText(Title);
            materialCardView.setBackgroundResource(Integer.parseInt(background));

            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Title.contains("Add Incomes")){

                        startActivity(new Intent(getApplicationContext(),Add_Item.class));
                        Input_Data.appbar_title="Add Incomes";
                        Input_Data.Expense = false;

                    } if (Title.contains("Add Expenses")){

                        startActivity(new Intent(getApplicationContext(),Add_Item.class));
                        Input_Data.appbar_title="Add Expenses";
                        Input_Data.Expense = true;

                    }

                }
            });

            return myview;
        }
    }

    private void ListHashMap(){

        hashMap = new HashMap<>();
        hashMap.put("Title", "Add Incomes");
        hashMap.put("background", String.valueOf(R.drawable.card1));
        hashMap.put("icon", String.valueOf(R.drawable.income));
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("Title", "Add Expenses");
        hashMap.put("background", String.valueOf(R.drawable.card2));
        hashMap.put("icon", String.valueOf(R.drawable.costs));
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("Title", "Budget Limit");
        hashMap.put("background", String.valueOf(R.drawable.card2));
        hashMap.put("icon", String.valueOf(R.drawable.budget));
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("Title", "Accounts");
        hashMap.put("background", String.valueOf(R.drawable.card1));
        hashMap.put("icon", String.valueOf(R.drawable.account));
        arrayList.add(hashMap);

    }

    public void TotalBalanceMethod(){

        dbhelper = new DatabaseHelper(this);
        double total = dbhelper.IncomeTotalBalance() - dbhelper.ExpenseTotalBalance();
        totalbalance.setText(""+total);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}