package com.example.reproductor.Buscador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.Entities.Usuarios;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.R;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {
    private List<PlayList> listaPlatlist;
    private Context context;
    private PlayListAdapter.OnItemClickListener listener;
    private Usuarios usuarioActual;
    private int posicionCancionSeleccionada;

    // Constructor
    public PlayListAdapter(Context context, List<PlayList> listaPlaylist, Usuarios usuarioActual) {
        this.context = context;
        this.listaPlatlist = listaPlaylist;
        this.usuarioActual = usuarioActual;
    }

    // Interfaz para el escuchador de clics
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Método para establecer el escuchador
    public void setOnItemClickListener(PlayListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false);
        return new PlayListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayListAdapter.ViewHolder holder, int position) {
        PlayList currentPlaylist = listaPlatlist.get(position);
        // Configurar las vistas con datos desde la lista de playList
        holder.txtPLNameBuscador.setText(currentPlaylist.getNombre() != null ? currentPlaylist.getNombre() : "<Sin Nombre>");

        // Obtener la URL de la imagen de la canción
        String imageUrl = currentPlaylist.getImgURLPlaylist();

        // Cargamos la imagen de cada cancion
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.cargando_cancion_image)
                .into(holder.imgPLBuscador);

        // obtenemos la posicion de la cancion seleccionada
        this.posicionCancionSeleccionada = position;

        // Configurar el clic en el ViewHolder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listaPlatlist != null)
            return listaPlatlist.size();
        else
            return 0;
    }

    public void setListaCanciones(List<PlayList> listaPlayList) {
        this.listaPlatlist = listaPlayList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPLBuscador;
        TextView txtPLNameBuscador;

        ViewHolder(View itemView) {
            super(itemView);
            imgPLBuscador = itemView.findViewById(R.id.imgListaHorizontal);
            txtPLNameBuscador = itemView.findViewById(R.id.txtNombreCancionHorizontal);
        }
    }
}
