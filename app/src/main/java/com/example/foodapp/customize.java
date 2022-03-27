package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class customize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        Bundle bundle=getIntent().getExtras();
        final String[] maintitle = {bundle.getString("position")};

        final String[] subtitle = {bundle.getString("subtitle")};

        int imgid=bundle.getInt("imgid");

        EditText editText=findViewById(R.id.editText);
        editText.setText(maintitle[0]);
        CheckBox coke=findViewById(R.id.coke);
        CheckBox fries=findViewById(R.id.fires);
        Button addtocart=findViewById(R.id.button);
        Toast.makeText(this,"Add to Cart",Toast.LENGTH_LONG).show();
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coke.isChecked()){
                    MainActivity.getinstance().total+=85;
                    subtitle[0] +="+ ₹85";
                }
                if(fries.isChecked()){
                    MainActivity.getinstance().total+=40;
                    subtitle[0]+="+ ₹40";
                }
                MainActivity.getinstance().item.add(maintitle[0]);
                MainActivity.getinstance().sub.add(subtitle[0]);
                MainActivity.getinstance().image.add(imgid);
                MainActivity.getinstance().total+=bundle.getDouble("price");
                Toast.makeText(customize.this,"Added to Cart",Toast.LENGTH_LONG).show();
            }
        });

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
                Intent intent=new Intent(customize.this,Kahuna_Burger.class);
                startActivity(intent);
                Toast.makeText(this,"Burger Selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.i3:
                Toast.makeText(this,"Add to Cart",Toast.LENGTH_LONG).show();
                Intent i=new Intent(customize.this,Cart.class);
                startActivity(i);
                break;
            case R.id.i4:
                Toast.makeText(this,"Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(customize.this,veg_pizza.class);
                startActivity(intent1);
                break;
            case  R.id.i5:
                Toast.makeText(this,"Non Veg Pizza Selected",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(customize.this,non_vegpizza.class);
                startActivity(intent2);
                break;
            default:
                return false;
        }
        return true;
    }

}