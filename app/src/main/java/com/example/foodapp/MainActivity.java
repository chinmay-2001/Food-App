package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String > item=new ArrayList<>();
    ArrayList<Integer> image=new ArrayList<>();
    ArrayList<String> sub=new ArrayList<>();
    static  MainActivity instanece;
    double total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instanece=this;

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
                Intent intent=new Intent(MainActivity.this,Kahuna_Burger.class);
                startActivity(intent);
                Toast.makeText(this,"Burger Selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.i3:
                Toast.makeText(this,"Add to Cart",Toast.LENGTH_LONG).show();
                Intent i=new Intent(MainActivity.this,Cart.class);
                startActivity(i);
               break;
            case R.id.i4:
                Toast.makeText(this,"Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(MainActivity.this,veg_pizza.class);
                startActivity(intent1);
                break;
            case  R.id.i5:
                Toast.makeText(this,"Non Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(MainActivity.this,non_vegpizza.class);
                startActivity(intent2);
                break;
            default:
                return false;
        }
        return true;
    }
    static MainActivity getinstance(){
        return instanece;
    }

}