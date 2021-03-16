package com.example.autoshowroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addCar(View view){
        EditText tv=findViewById(R.id.makertext);
        Toast.makeText(this, "Car maker: "+tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    public void resetField(View view){
        EditText maker,model,year,color,seats,address,price;
        maker=findViewById(R.id.makertext);model=findViewById(R.id.modeltext);
        year=findViewById(R.id.yeartext);color=findViewById(R.id.colortext);
        seats=findViewById(R.id.seattext); address=findViewById(R.id.addresstext);
        price=findViewById(R.id.priceno);
        maker.setText("");model.setText("");year.setText("");color.setText("");
        seats.setText("");address.setText("");price.setText("");
    }
}