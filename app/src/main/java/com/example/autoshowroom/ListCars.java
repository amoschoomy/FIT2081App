package com.example.autoshowroom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.provider.CarViewModel;

public class ListCars extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    CarViewModel carViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cars);
        recyclerView = findViewById(R.id.recycleview);
        recyclerViewAdapter = new RecyclerViewAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        carViewModel.getAllCars().observe(this, newData -> {
            recyclerViewAdapter.setCars(newData);
            recyclerViewAdapter.notifyDataSetChanged();
        });


    }
}