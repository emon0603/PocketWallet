package com.emon.pocketwallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class Transactions extends Fragment {

    DatabaseHelper databaseHelper;
    TextView tvtotalexpense,TvCurrentData,nodatatv;
    ListView listView;

    private HashMap<String,String> viewHash;
    private ArrayList< HashMap<String,String> > arrayListview = new ArrayList<>();
    ViewAdapter viewAdapter = new ViewAdapter();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View tranView = layoutInflater.inflate(R.layout.fragment_transactions, container, false);
        tvtotalexpense = tranView.findViewById(R.id.totalexpensetv);
        TvCurrentData = tranView.findViewById(R.id.TvCurrentData);
        listView = tranView.findViewById(R.id.listview);
        nodatatv = tranView.findViewById(R.id.nodatatv);


        databaseHelper = new DatabaseHelper(getContext());

        if (Input_Data.Expense==true){

            Cursor cursor = databaseHelper.showExpense();

            if (cursor!=null && cursor.getCount()>0){

                while (cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    double amount = cursor.getDouble(1);
                    String catagories = cursor.getString(2);
                    String date = cursor.getString(3);
                    String notes = cursor.getString(4);

                    viewHash = new HashMap<>();
                    viewHash.put("amount", ""+amount);
                    viewHash.put("catagories", ""+catagories);
                    viewHash.put("date", ""+date);
                    viewHash.put("notes", ""+notes);
                    arrayListview.add(viewHash);

                }


            } else {
                nodatatv.setText("No Data Found");

            }
        } else {
            Cursor cursor = databaseHelper.showIncome();
            if (cursor!=null && cursor.getCount()>0){

                while (cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    double amount = cursor.getDouble(1);
                    String catagories = cursor.getString(2);
                    String date = cursor.getString(3);
                    String notes = cursor.getString(4);

                    viewHash = new HashMap<>();
                    viewHash.put("amount", ""+amount);
                    viewHash.put("catagories", ""+catagories);
                    viewHash.put("date", ""+date);
                    viewHash.put("notes", ""+notes);
                    arrayListview.add(viewHash);

                }


            } else {
                nodatatv.setText("No Data Found");

            }
        }



        BalanceMethod();
        TvCurrentData.setText(getcurrentDateAndTime());


        listView.setAdapter(viewAdapter);

        return tranView;
    }


    public static String getcurrentDateAndTime(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = simpleDateFormat.format(c);
        return formattedDate;
    }

    public void BalanceMethod(){

        if (Input_Data.Expense==true){
            tvtotalexpense.setText("Total: "+databaseHelper.ExpenseTotalBalance());
        } else {
            tvtotalexpense.setText("Total: "+databaseHelper.IncomeTotalBalance());
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        BalanceMethod();
    }


    public class ViewAdapter extends BaseAdapter{

        TextView catetv, paymenttv,datetv,amounttv;
        MaterialCardView trancardview;

        @Override
        public int getCount() {
            return arrayListview.size();
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
            View TranView = layoutInflater.inflate(R.layout.item_transa, parent, false);

            catetv = TranView.findViewById(R.id.catetv);
            paymenttv = TranView.findViewById(R.id.paymenttv);
            datetv = TranView.findViewById(R.id.datetv);
            amounttv = TranView.findViewById(R.id.amounttv);
            trancardview = TranView.findViewById(R.id.trancardview);



            viewHash = arrayListview.get(position);
            String amount = viewHash.get("amount");
            String catagories = viewHash.get("catagories");
            String date = viewHash.get("date");
            String notes = viewHash.get("notes");


                if (Input_Data.Expense==true){

                    amounttv.setText("- "+amount);
                    amounttv.setTextColor(getResources().getColor(R.color.red));
                } else {
                    amounttv.setText("+ "+amount);
                    amounttv.setTextColor(getResources().getColor(R.color.teal_700));
                }

                ///////////////========/////////////

                if (notes.isEmpty()){
                    paymenttv.setText("Null");
                } else {
                    paymenttv.setText(notes);
                }

                catetv.setText(catagories);
                datetv.setText(""+date);




            trancardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (Input_Data.Expense==true){

                        new AlertDialog.Builder(getContext())
                                .setTitle("Amount: - "+amount)
                                .setMessage("Catagories: "+catagories+"\n\n"+"Note: "+notes+"\n")
                                .show();


                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Amount: + "+amount)
                                .setMessage("Catagories: "+catagories+"\n\n"+"Note: "+notes+"\n")
                                .show();
                    }


                }
            });


            return TranView;
        }
    }



}