package com.example.reproductor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CancionAdapter extends RecyclerView.Adapter<CancionAdapter.ViewHolder> {

    private List<String> listaCanciones;

    public CancionAdapter(List<String> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombreCancion = listaCanciones.get(position);
        holder.textViewNombreCancion.setText(nombreCancion);
    }

    @Override
    public int getItemCount() {
        return listaCanciones.size();
    }

    // Método para actualizar la lista de canciones
    public void setListaCanciones(List<String> nuevaLista) {
        listaCanciones.clear();
        listaCanciones.addAll(nuevaLista);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreCancion;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreCancion = itemView.findViewById(R.id.txvNombreCancion);
        }
    }
}
