package com.example.lenovopc.test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data = new ArrayList<>();
    int pageCount = 0 , totalPage = 1  , perpagecount = 3 , remainingcount = 0  ;
    TextView pagetx ;
    LinearLayout ll_parent;
    public static String prevoiusOne = "test" ;

    ArrayList<String> hs_page = new ArrayList<>();
    ArrayList<String> hs_orderpage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("commit changes ","on github test");


        pagetx = (TextView)findViewById(R.id.pagetxt);
        ll_parent = (LinearLayout)findViewById(R.id.add_parent);
        data.clear();
        for (int i = 1 ; i < 21 ; i++ ){

            data.add("Hiii"+i);

        }

        firstTimeLoadData(ll_parent , data.size() , perpagecount , pageCount , hs_page , hs_orderpage , pagetx , data);

        findViewById(R.id.nxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prevoiusOne = "2" ;


         nextClick(ll_parent , data.size() , perpagecount  , hs_page , hs_orderpage , pagetx ,data);

            }
        });
        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           PreviousClick(ll_parent , data.size() , perpagecount , hs_page , hs_orderpage , pagetx ,data);


            }
        });


    }




    public void nextClick(LinearLayout ll_parent , int totaldata , int perpagecount ,
                          ArrayList<String> hs_page , ArrayList<String> hs_orderpage , TextView pagetx , ArrayList<String> data){


        if (pageCount+1 == hs_page.size()) {
            Toast.makeText(MainActivity.this, "That' it22", Toast.LENGTH_SHORT).show();
        }else {

            if (pageCount < hs_page.size()) {

                pageCount = pageCount + 1 ;

                ll_parent.removeAllViews();

                int max = Integer.parseInt(hs_orderpage.get(pageCount));
                int min = Integer.parseInt(hs_orderpage.get(pageCount - 1));

                Log.e("**max", "" + max);
                Log.e("**min", "" + min);

                if (max < perpagecount) {

                    int max2 = min + max;
                    pagetx.setText(min + "-" + max2 + "/" + totaldata);
                    Log.e("max2", "for last" + max2);
                    for (int i = min; i < max2; i++) {

                        ll_parent.addView(ADDview(data.get(i)));
                    }
                } else {
                    pagetx.setText(min + "-" + max + "/" + totaldata);
                    for (int i = min; i < max; i++) {

                        ll_parent.addView(ADDview(data.get(i)));
                    }
                }


            } else {
                //  Toast.makeText(MainActivity.this, "That' it", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void PreviousClick(LinearLayout ll_parent , int totaldata , int perpagecount ,
                              ArrayList<String> hs_page , ArrayList<String> hs_orderpage , TextView pagetx , ArrayList<String> data){

        if (prevoiusOne.equals("1")){

            Toast.makeText(MainActivity.this, "That' it previous", Toast.LENGTH_SHORT).show();

        }else {

            pageCount = pageCount - 1 ;
            if (pageCount == 0){

                prevoiusOne = "1" ;

                ll_parent.removeAllViews();

                Log.e("order page",""+hs_orderpage);
                Log.e(" page",""+hs_page);
                int max = Integer.parseInt(hs_orderpage.get(pageCount));
                pagetx.setText("1"+"-"+max+"/"+data.size());
                for (int i= 0 ; i < max ; i++){
                    ll_parent.addView(ADDview(data.get(i)));
                }


            }else if (pageCount < hs_page.size()){

                prevoiusOne = "2" ;

                ll_parent.removeAllViews();

                Log.e("order page",""+hs_orderpage);
                Log.e(" page",""+hs_page);
                int max = Integer.parseInt(hs_orderpage.get(pageCount));
                int min = Integer.parseInt(hs_orderpage.get(pageCount-1));

                Log.e("**max",""+max);
                Log.e("**min",""+min);

                if (max < perpagecount){

                    int max2 = min + max ;
                    pagetx.setText(min+"-"+max2+"/"+totaldata);
                    Log.e("max2","for last"+max2);
                    for (int i = min ; i < max2 ; i++){

                        ll_parent.addView(ADDview(data.get(i)));
                    }
                }else {
                    pagetx.setText(min+"-"+max+"/"+data.size());
                    for (int i = min ; i < max ; i++){

                        ll_parent.addView(ADDview(data.get(i)));
                    }
                }


            }
            else {
                //  Toast.makeText(MainActivity.this, "That' it", Toast.LENGTH_SHORT).show();
            }

        }



    }


    public void firstTimeLoadData(LinearLayout ll_parent , int totaldata , int perpagecount , int pagecount ,
                                  ArrayList<String> hs_page , ArrayList<String> hs_orderpage , TextView pagetx , ArrayList<String> data){

        if (data.size()>perpagecount){

            int totalPage = totaldata / perpagecount ;
            int remainingcount = totaldata % perpagecount ;

            for (int i = 1 ; i <= totalPage ; i++){
                hs_page.add(""+i);
                hs_orderpage.add(String.valueOf(i*perpagecount));
            }

            if (remainingcount == 0){
                Toast.makeText(this, "No remain", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "remains", Toast.LENGTH_SHORT).show();
                hs_page.add(String.valueOf(totalPage+1));
                hs_orderpage.add(String.valueOf(remainingcount));
            }
            int siz = Integer.parseInt(hs_orderpage.get(pagecount));
            for (int i = 0 ; i < siz ; i++){
                ll_parent.addView(ADDview(data.get(i)));
            }

            pagetx.setText("1"+"-"+siz+"/"+totaldata);


        }else {

            for (int i = 0 ; i < totaldata ; i++){

                ll_parent.addView(ADDview(data.get(i)));

            }

        }

    }


    public View ADDview(String s){

        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.add_layout, null);

        TextView textView = (TextView) v.findViewById(R.id.add_txt);
        textView.setText(s);

        return v ;

    }



}
