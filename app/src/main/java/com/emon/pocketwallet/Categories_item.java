package com.emon.pocketwallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class Categories_item extends Fragment {

    GridView gridView;
    RelativeLayout categories_mainlay,cataitembt;
    HashMap<String,String> hascate;
    ArrayList < HashMap <String,String> > arrayListcata = new ArrayList<>();

    public static String name_catagories = "";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View cataview = layoutInflater.inflate(R.layout.fragment_categories_item, container, false);
        gridView = cataview.findViewById(R.id.catego_gridview);
        categories_mainlay = cataview.findViewById(R.id.categories_mainlay);


        CatagoHashMap();


        CateAdapter cateAdapter = new CateAdapter();
        gridView.setAdapter(cateAdapter);




        return cataview;

    }

    public class CateAdapter extends BaseAdapter{

        ImageView imageview,circle_image;
        TextView type_name;



        @Override
        public int getCount() {
            return arrayListcata.size();
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

            LayoutInflater cainfla = getLayoutInflater();
            View CaView = cainfla.inflate(R.layout.item_catagories, parent, false);
            imageview = CaView.findViewById(R.id.imageview);
            type_name = CaView.findViewById(R.id.type_name);
            circle_image = CaView.findViewById(R.id.circle_image);
            cataitembt = CaView.findViewById(R.id.cataitembt);


            hascate = arrayListcata.get(position);
            String name_type = hascate.get("type");
            String pic = hascate.get("image");




            type_name.setText(name_type);
            imageview.setImageResource(Integer.parseInt(pic));

            if (name_type.contains("Add Your Own")){
                imageview.setVisibility(View.GONE);
                circle_image.setImageResource(R.drawable.baseline_control_point_24);
            }

            cataitembt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories_mainlay.setVisibility(View.GONE);

                }
            });





            return CaView;
        }
    }

    private void CatagoHashMap() {

        hascate = new HashMap<>();
        hascate.put("type", "Food");
        hascate.put("image", String.valueOf(R.drawable.baseline_fastfood_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Shopping");
        hascate.put("image", String.valueOf(R.drawable.baseline_add_shopping_cart_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Transport");
        hascate.put("image", String.valueOf(R.drawable.baseline_emoji_transportation_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Gifts");
        hascate.put("image", String.valueOf(R.drawable.baseline_card_giftcard_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Family");
        hascate.put("image", String.valueOf(R.drawable.baseline_family_restroom_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Groceries");
        hascate.put("image", String.valueOf(R.drawable.baseline_add_shopping_cart_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Health");
        hascate.put("image", String.valueOf(R.drawable.round_boy_24));
        arrayListcata.add(hascate);


        hascate = new HashMap<>();
        hascate.put("type", "Savings");
        hascate.put("image", String.valueOf(R.drawable.baseline_account_balance_24));
        arrayListcata.add(hascate);






    }

    private void Replace(Fragment fragment){
        androidx.fragment.app.FragmentManager fragmentManager = getFragmentManager();
        androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelay, fragment);
        fragmentTransaction.commit();
    }





}