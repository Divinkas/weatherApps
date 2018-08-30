package com.example.divinkas.weatherapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.divinkas.weatherapp.Data.ItemTempPlaces;
import com.example.divinkas.weatherapp.Model.PlacesModel;
import com.example.divinkas.weatherapp.R;
import com.example.divinkas.weatherapp.WeatherActivity;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<ItemTempPlaces> itemPlacesList;

    public PlacesAdapter(Context context, List<ItemTempPlaces> lst) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        itemPlacesList = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.places_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNamePlace.setText(itemPlacesList.get(position).getName());
        holder.dellPlace.setOnClickListener((v)->{
                PlacesModel placesModel = new PlacesModel(context);
                placesModel.dellPlaces(itemPlacesList.get(position).getLat(), itemPlacesList.get(position).getLon());
                itemPlacesList.remove(position);
                notifyDataSetChanged();
        });
        holder.open.setOnClickListener((v)->{
            Intent intent = new Intent(context, WeatherActivity.class);
            intent.putExtra("lat", String.valueOf(itemPlacesList.get(position).getLat()));
            intent.putExtra("lon", String.valueOf(itemPlacesList.get(position).getLon()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return itemPlacesList.size(); }

    @Override
    public int getItemViewType(int position) { return position; }

    @Override
    public long getItemId(int position) { return position; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamePlace;
        ImageView dellPlace;
        LinearLayout open;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamePlace = itemView.findViewById(R.id.title_places);
            dellPlace = itemView.findViewById(R.id.delete_places);
            open = itemView.findViewById(R.id.ll_open);
        }
    }
}
