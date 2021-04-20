package com.example.autoshowroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<String> cars = new ArrayList();

    public void setData(ArrayList<String> data) {
        this.cars = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String data = cars.get(holder.getAdapterPosition());
        StringTokenizer sT = new StringTokenizer(data, ";");
        String carString = sT.nextToken();
        System.out.println(carString);
        String modelString = sT.nextToken();
        String yearString, colorString, seatsString, priceString;
        yearString = sT.nextToken();
        colorString = sT.nextToken();
        seatsString = sT.nextToken();
        priceString = sT.nextToken();
        TextView maker = holder.itemView.findViewById(R.id.makerv);
        TextView model = holder.itemView.findViewById(R.id.modelv);
        TextView year = holder.itemView.findViewById(R.id.yearv);
        TextView color = holder.itemView.findViewById(R.id.colorv);
        TextView seats = holder.itemView.findViewById(R.id.seatsv);
        TextView price = holder.itemView.findViewById(R.id.pricev);

        maker.setText("Maker: " + carString);
        model.setText("Model: " + modelString);
        year.setText("Year " + yearString);
        color.setText("Color " + colorString);
        seats.setText("Seats " + seatsString);
        price.setText("Price " + priceString);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Car no: " + position + " " + maker.getText().toString() + " " + model.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView carTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carTv = itemView.findViewById(R.id.makerv);
        }
    }
}
