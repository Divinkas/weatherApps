package com.example.divinkas.weatherapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divinkas.weatherapp.Data.ItemPlaces;
import com.example.divinkas.weatherapp.R;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<ItemPlaces> itemPlacesList;

    public PlacesAdapter(Context context, List<ItemPlaces> lst) {
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
                itemPlacesList.remove(position);
                notifyDataSetChanged();
        });
        holder.open.setOnClickListener((v)->{
            Toast.makeText(context, "open by id " + position, Toast.LENGTH_SHORT).show();
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
