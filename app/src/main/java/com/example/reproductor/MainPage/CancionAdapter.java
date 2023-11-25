package com.example.reproductor.MainPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CancionAdapter extends RecyclerView.Adapter<CancionAdapter.ViewHolder> {

    private List<Canciones> listaCanciones;
    private Context context;

    // Constructor
    public CancionAdapter(Context context, List<Canciones> listaCanciones) {
        this.context = context;
        this.listaCanciones = listaCanciones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Canciones currentCancion = listaCanciones.get(position);

        // Configurar las vistas con datos desde la lista de canciones
        holder.txtSongNameBuscador.setText(currentCancion.getNombre());
        holder.txtArtistNameBuscador.setText(currentCancion.getGenero());

        // Obtener la URL de la imagen de la canción
        String imageUrl = currentCancion.getLinkImage();

        // Usar Glide para cargar la imagen en la ImageView
        Glide.with(context)
                .load(imageUrl)
                .into(holder.imgSongBuscador);
    }

    @Override
    public int getItemCount() {
        if (listaCanciones != null)
            return listaCanciones.size();
        else
            return 0;
    }

    public void setListaCanciones(List<Canciones> listaCanciones) {
        this.listaCanciones = listaCanciones;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSongBuscador;
        TextView txtSongNameBuscador;
        TextView txtArtistNameBuscador;

        ViewHolder(View itemView) {
            super(itemView);
            imgSongBuscador = itemView.findViewById(R.id.imgSongBuscador);
            txtSongNameBuscador = itemView.findViewById(R.id.txtSongNameBuscador);
            txtArtistNameBuscador = itemView.findViewById(R.id.txtSongGenre);
        }
    }
}
