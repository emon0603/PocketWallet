package com.emon.pocketwallet;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Input_Data extends Fragment {

    Spinner moneyspinner, paymentspinner;

    ImageButton spinnerCatego;

    ImageView backbt, insertbt, datepicker;
    TextView Header_title;

    EditText edamount, edcate, eddate, ednotes,edpayment;

    FrameLayout categories_frame;

    private DatabaseHelper dbHelper;
    public static String appbar_title = "";
    public static boolean Expense = true;

    public static boolean categories_frame_visi = true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View inputview = layoutInflater.inflate(R.layout.fragment_input__data, container, false);
        moneyspinner = inputview.findViewById(R.id.moneyspinner);
        paymentspinner = inputview.findViewById(R.id.paymentspinner);
        backbt = inputview.findViewById(R.id.backbt);
        Header_title = inputview.findViewById(R.id.Header_title);
        insertbt = inputview.findViewById(R.id.insertbt);
        edamount = inputview.findViewById(R.id.edamount);
        edcate = inputview.findViewById(R.id.edcate);
        ednotes = inputview.findViewById(R.id.ednotes);
        spinnerCatego = inputview.findViewById(R.id.spinnerCatego);
        categories_frame = inputview.findViewById(R.id.categories_frame);



        Header_title.setText(appbar_title);

        dbHelper = new DatabaseHelper(getContext());

        edcate.setText(Categories_item.name_catagories);




        spinnerCatego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Header_title.setText("Catagories");
                insertbt.setVisibility(View.GONE);

                categories_frame.setVisibility(View.VISIBLE);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.categories_frame, new Categories_item());
                fragmentTransaction.commit();


            }
        });

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertbt.setVisibility(View.VISIBLE);

                if (Expense==true){
                    Header_title.setText("Add Expenses");
                    categories_frame.setVisibility(View.GONE);

                } if (Expense!=true){
                    Header_title.setText("Add Incomes");
                    categories_frame.setVisibility(View.GONE);
                } else {

                }


            }
        });

        insertbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String samount = edamount.getText().toString();
                String cate = edcate.getText().toString();
                String note = ednotes.getText().toString();
                Double amount = Double.parseDouble(samount);


                if (Expense==true){

                    dbHelper.AddExpense(amount,cate,note );
                    Toast.makeText(getContext(), "Expense Successfully Inserted",Toast.LENGTH_SHORT).show();

                } else {
                    dbHelper.AddInComes(amount, cate,note);
                    Toast.makeText(getContext(), "Incomes Successfully Inserted",Toast.LENGTH_SHORT).show();
                }

                edamount.setText("");
                edcate.setText("");
                ednotes.setText("");


            }
        });

        SpinnerMethod();



        return inputview;
    }

    private void DatePicker(){

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth + 1;
                        eddate.setText("" + selectedday + "-" + selectedmonth + "-" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            }
        });

    }
    private void SpinnerMethod(){

        //////============= Spinner Amonut=======/////////////////
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.monney_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moneyspinner.setAdapter(adapter);
        //////============= Spinner End=======/////////////////

        //////============= Spinner Payment=======/////////////////
        ArrayAdapter<CharSequence> adapterpayment = ArrayAdapter.createFromResource(
                getContext(),
                R.array.payment_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentspinner.setAdapter(adapterpayment);

        //////============= Spinner End=======/////////////////


    }



}
