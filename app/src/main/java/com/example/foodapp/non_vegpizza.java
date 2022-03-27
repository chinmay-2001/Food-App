package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class non_vegpizza extends AppCompatActivity {

    String[] maintilte = {"CHICKEN SAUSAGE", "Chicken Golden Delight", "Chicken Dominator"};
    String[] subtitle = {"₹199.99", "₹214.99", "₹259.99"};
    double[] price={199.99,214.99,259.99};
    Integer[] imgid = {R.drawable.chicken_sausage, R.drawable.chicken_golden_delight, R.drawable.dominator};
    static non_vegpizza Instance;
    int postiton_item;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Instance=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_vegpizza);
        MyListview adapter = new MyListview(this, maintilte, subtitle, imgid);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter);
        list.setOnCreateContextMenuListener(this);
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
                Intent intent=new Intent(non_vegpizza.this,Kahuna_Burger.class);
                startActivity(intent);
                Toast.makeText(this,"Burger Selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.i3:
                Toast.makeText(this,"Add to Cart",Toast.LENGTH_LONG).show();
                Intent i=new Intent(non_vegpizza.this,Cart.class);
                startActivity(i);
                break;
            case R.id.i4:
                Toast.makeText(this,"Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(non_vegpizza.this,veg_pizza.class);
                startActivity(intent1);
                break;
            case  R.id.i5:
                Toast.makeText(this,"Non Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(non_vegpizza.this,non_vegpizza.class);
                startActivity(intent2);
                break;
            default:
                return false;
        }
        return true;
    }


    static non_vegpizza getInstance(){
        return Instance;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info=new AdapterView.AdapterContextMenuInfo(item,)
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        postiton_item=info.position;
        Intent intent=new Intent(this,customize.class);

        Bundle b=new Bundle();
        b.putString("position",maintilte[postiton_item]);
        b.putString("subtitle",subtitle[postiton_item]);
        b.putInt("imgid",imgid[postiton_item]);
        b.putDouble("price",price[postiton_item]);
        intent.putExtras(b);
        startActivity(intent);
        System.out.println(postiton_item);
        return true;
    }


//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }

    String getdatatitle(){
        return this.maintilte[postiton_item];
    }
    double price(){
        return this.price[postiton_item];
    }

    class MyListview  extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] maintitle;
        private final String[] subtitle;
        private final Integer[] imgid;
        public MyListview Instance=this;

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
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu=new PopupMenu(context,imageview);
                    popupMenu.getMenuInflater().inflate(R.menu.add_to_cart,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            postiton_item=position;
                            MainActivity.getinstance().item.add((String) titletext.getText());
                            MainActivity.getinstance().sub.add((String)sub.getText());
                            MainActivity.getinstance().image.add(imgid[position]);
                            try {

                                MainActivity.getinstance().total += price[position];
                            }
                            catch (Exception e){
                                MainActivity.getinstance().total += 0;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
            return rowView;
        }
    }
}