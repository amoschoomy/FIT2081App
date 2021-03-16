package com.example.autoshowroom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void resetFieldsAndData(View view) {
        EditText maker = findViewById(R.id.makertext);
        EditText model = findViewById(R.id.modeltext);
        EditText year = findViewById(R.id.yeartext);
        EditText color = findViewById(R.id.colortext);
        EditText seats = findViewById(R.id.seattext);
        EditText price = findViewById(R.id.priceno);

        maker.setText("");
        model.setText("");
        year.setText("");
        color.setText("");
        seats.setText("");
        price.setText("");

        SharedPreferences.Editor editor=getPreferences(0).edit();
        editor.clear();
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferences(0);

        EditText maker = findViewById(R.id.makertext);
        EditText model = findViewById(R.id.modeltext);
        EditText year = findViewById(R.id.yeartext);
        EditText color = findViewById(R.id.colortext);
        EditText seats = findViewById(R.id.seattext);
        EditText price = findViewById(R.id.priceno);

        maker.setText(sharedPreferences.getString("maker", ""));
        model.setText(sharedPreferences.getString("model", ""));
        year.setText(sharedPreferences.getString("year", ""));
        color.setText(sharedPreferences.getString("color", ""));
        seats.setText(sharedPreferences.getString("seats", ""));
        price.setText(sharedPreferences.getString("price", ""));


    }

    public void addCar(View view) {
        EditText maker = findViewById(R.id.makertext);
        EditText model = findViewById(R.id.modeltext);
        EditText year = findViewById(R.id.yeartext);
        EditText color = findViewById(R.id.colortext);
        EditText seats = findViewById(R.id.seattext);
        EditText price = findViewById(R.id.priceno);

        SharedPreferences.Editor editor = getPreferences(0).edit();
        editor.putString("maker", maker.getText().toString());
        editor.putString("model", model.getText().toString());
        editor.putString("year", year.getText().toString());
        editor.putString("color", color.getText().toString());
        editor.putString("seats", seats.getText().toString());
        editor.putString("price", price.getText().toString());
        editor.apply();

        Toast.makeText(this, "Car maker: " + maker.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}