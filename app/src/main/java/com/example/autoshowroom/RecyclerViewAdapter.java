package com.example.autoshowroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.provider.Car;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<Car> cars;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car data = cars.get(holder.getAdapterPosition());
        TextView maker = holder.itemView.findViewById(R.id.makerv);
        TextView model = holder.itemView.findViewById(R.id.modelv);
        TextView year = holder.itemView.findViewById(R.id.yearv);
        TextView color = holder.itemView.findViewById(R.id.colorv);
        TextView seats = holder.itemView.findViewById(R.id.seatsv);
        TextView price = holder.itemView.findViewById(R.id.pricev);
        maker.setText("Maker: " + data.getCarMaker());
        model.setText("Model: " + data.getCarModel());
        year.setText("Year " + data.getYear());
        color.setText("Color: " + data.getColour());
        seats.setText("Seats " + data.getSeats());
        price.setText("Price " + data.getPrice());
    }

    @Override
    public int getItemCount() {
        if (cars == null) {
            return 0;
        }
        return cars.size();
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView carTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carTv = itemView.findViewById(R.id.makerv);
        }
    }
}
