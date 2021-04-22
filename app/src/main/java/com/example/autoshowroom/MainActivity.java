package com.example.autoshowroom;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.autoshowroom.provider.Car;
import com.example.autoshowroom.provider.CarViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private CarViewModel carViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_menu, R.string.close_menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(new MyNavigationViewListener());


        EditText maker = findViewById(R.id.makertext);
        EditText model = findViewById(R.id.modeltext);
        EditText year = findViewById(R.id.yeartext);
        EditText color = findViewById(R.id.colortext);
        EditText seats = findViewById(R.id.seattext);
        EditText price = findViewById(R.id.priceno);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();

        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        carViewModel.getAllCars().observe(this, newData -> {
            recyclerViewAdapter.setCars(newData);
            recyclerViewAdapter.notifyDataSetChanged();
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCar();


            }
        });


        ListView lw = findViewById(R.id.listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.clearall:
                resetFieldsAndData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void resetFieldsAndData() {
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

        SharedPreferences.Editor editor = getPreferences(0).edit();
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

    public void addCar() {
        EditText maker = findViewById(R.id.makertext);
        EditText model = findViewById(R.id.modeltext);
        EditText year = findViewById(R.id.yeartext);
        EditText color = findViewById(R.id.colortext);
        EditText seats = findViewById(R.id.seattext);
        EditText price = findViewById(R.id.priceno);

        Car car = new Car(maker.getText().toString(), model.getText().toString(),
                Integer.parseInt(year.getText().toString()), color.getText().toString(), Integer.parseInt(seats.getText().toString()), Integer.parseInt(price.getText().toString()));
        carViewModel.insert(car);
        Toast.makeText(this, "Added car: " + maker.getText().toString() + " " + model.getText().toString(), Toast.LENGTH_SHORT).show();
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
            EditText carText, modelText, yearText, colorText, seatsText, priceText;
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
            String carString = sT.nextToken();
            String modelString = sT.nextToken();
            String yearString, colorString, seatsString, priceString;
            yearString = sT.nextToken();
            colorString = sT.nextToken();
            seatsString = sT.nextToken();
            priceString = sT.nextToken();
            maker.setText(carString);
            model.setText(modelString);
            year.setText(yearString);
            color.setText(colorString);
            seats.setText(seatsString);
            price.setText(priceString);
        }
    }

    class MyNavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.addcar:
                    addCar();
                    EditText maker = findViewById(R.id.makertext);
                    EditText model = findViewById(R.id.modeltext);
                    EditText year = findViewById(R.id.yeartext);
                    EditText color = findViewById(R.id.colortext);
                    EditText seats = findViewById(R.id.seattext);
                    EditText price = findViewById(R.id.priceno);
                    Car car = new Car(maker.getText().toString(), model.getText().toString(),
                            Integer.parseInt(year.getText().toString()), color.getText().toString(), Integer.parseInt(seats.getText().toString()), Integer.parseInt(price.getText().toString()));
                    carViewModel.insert(car);

                    break;
                case R.id.removelast:


                    break;
                case R.id.removeall:
                    carViewModel.deleteAll();
                    break;

                case R.id.listall:
                    Intent myIntent = new Intent(MainActivity.this, ListCars.class);
                    MainActivity.this.startActivity(myIntent);
                    break;
            }
            DrawerLayout drawer = findViewById(R.id.drawer);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
}