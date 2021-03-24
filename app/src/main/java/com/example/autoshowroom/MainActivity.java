package com.example.autoshowroom;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

    }
    class MyBroadCastReceiver extends BroadcastReceiver {

        /*
         * This method 'onReceive' will get executed every time class SMSReceive sends a broadcast
         * */
        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */
            EditText carText,modelText,yearText,colorText,seatsText,priceText;
            EditText maker = findViewById(R.id.makertext);
            EditText model = findViewById(R.id.modeltext);
            EditText year = findViewById(R.id.yeartext);
            EditText color = findViewById(R.id.colortext);
            EditText seats = findViewById(R.id.seattext);
            EditText price = findViewById(R.id.priceno);
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            /*
             * String Tokenizer is used to parse the incoming message
             * The protocol is to have the account holder name and account number separate by a semicolon
             * */
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            StringTokenizer sT = new StringTokenizer(msg, ";");
            String carString= sT.nextToken();
            String modelString= sT.nextToken();
            String yearString,colorString,seatsString,priceString;
            yearString= sT.nextToken();
            colorString= sT.nextToken();
            seatsString= sT.nextToken();
            priceString= sT.nextToken();
            maker.setText(carString);
            model.setText(modelString);
            year.setText(yearString);
            color.setText(colorString);
            seats.setText(seatsString);
            price.setText(priceString);

//            String accountName = sT.nextToken();
//            String accountNumber = sT.nextToken();
            /*
             * Now, its time to update the UI
             * */
        }
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

        maker.setText(sharedPreferences.getString("maker",""));
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

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
    }
}