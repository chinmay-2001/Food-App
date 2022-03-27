package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.number.Precision;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Cart extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        try {
            if(MainActivity.getinstance().item!=null) {
        //                listView = findViewById(R.id.listview);
        //                ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, MainActivity.getinstance().item);
        //                listView.setAdapter(arrayAdapter);
                Integer[] arr=new Integer[MainActivity.getinstance().image.size()];
                String[] arr1=new String[MainActivity.getinstance().sub.size()];
                String[] arr2=new String[MainActivity.getinstance().item.size()];
                MyListview adapter = new MyListview(this, MainActivity.getinstance().item.toArray(arr2), MainActivity.getinstance().sub.toArray(arr1), MainActivity.getinstance().image.toArray(arr));
                ListView list = (ListView) findViewById(R.id.listview);
                list.setAdapter(adapter);
            }
        } catch (Exception e) {
            listView=findViewById(R.id.listview);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, Collections.singletonList("no data"));
            listView.setAdapter(arrayAdapter);
        }
        EditText editText=findViewById(R.id.editText2);
        DecimalFormat df=new DecimalFormat("###.##");
        editText.setText(df.format(MainActivity.getinstance().total));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.i1:
                Toast.makeText(this,"Pizza selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.i2:
                Intent intent=new Intent(Cart.this,Kahuna_Burger.class);
                startActivity(intent);
                Toast.makeText(this,"Burger Selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.i3:
                Toast.makeText(this,"Add to Cart",Toast.LENGTH_LONG).show();
                Intent i=new Intent(Cart.this,Cart.class);
                startActivity(i);
                break;
            case R.id.i4:
                Toast.makeText(this,"Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(Cart.this,veg_pizza.class);
                startActivity(intent1);
                break;
            case  R.id.i5:
                Toast.makeText(this,"Non Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(Cart.this,non_vegpizza.class);
                startActivity(intent2);
                break;
            default:
                return false;
        }
        return true;
    }

    class MyListview  extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] maintitle;
        private final String[] subtitle;
        private final Integer[] imgid;

        public MyListview(Activity context, String[] maintitle, String[] subtitle,Integer[] imgid1) {
            super(context,R.layout.mylist,maintitle);
            this.context=context;
            this.maintitle=maintitle;
            this.subtitle=subtitle;
            this.imgid = imgid1;
        }




        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            View rowView= layoutInflater.inflate(R.layout.mylist,null,true);
            TextView titletext=rowView.findViewById(R.id.title);
            TextView sub=(TextView) rowView.findViewById(R.id.subtitle);
            ImageView imageview=rowView.findViewById(R.id.imageView6);
            titletext.setText(maintitle[position]);
            imageview.setImageResource(imgid[position]);
            sub.setText(subtitle[position]);

            return rowView;
        }
    }
}