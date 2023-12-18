package com.example.reproductor.Buscador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancionAdapter extends RecyclerView.Adapter<CancionAdapter.ViewHolder> {

    private List<Canciones> listaCanciones;
    private Context context;
    private OnItemClickListener listener;
    private Usuarios usuarioActual;
    private int posicionCancionSeleccionada;

    // Constructor
    public CancionAdapter(Context context, List<Canciones> listaCanciones, Usuarios usuarioActual) {
        this.context = context;
        this.listaCanciones = listaCanciones;
        this.usuarioActual = usuarioActual;
    }

    // Interfaz para el escuchador de clics
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Método para establecer el escuchador
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        holder.txtSongNameBuscador.setText(currentCancion.getNombre() != null ? currentCancion.getNombre() : "<Sin Nombre>");
        holder.txtArtistNameBuscador.setText(currentCancion.getArtistaNombre() != null ? currentCancion.getArtistaNombre() : "<Sin Artista>");

        // Obtener la URL de la imagen de la canción
        String imageUrl = currentCancion.getLinkImage();

        // Cargamos la imagen de cada cancion
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.cargando_cancion_image)
                .into(holder.imgSongBuscador);

        // Configurar el clic en el ViewHolder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });

        // configuramos el escuchador para la pulsación larga
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();

                // Crear instancia de PopUpMenuLongClick
                PopUpMenuLongClick popUpMenuLongClick = new PopUpMenuLongClick(context, usuarioActual, listaCanciones, position);

                // Obtener el menú inflado
                menuInflater.inflate(R.menu.pop_up, popupMenu.getMenu());

                // Agregar dinámicamente un ítem al menú solo si hay más de dos playlist
                // ya que una de ellas va a ser la de favoritos
                Menu menu = popupMenu.getMenu();
                if (popUpMenuLongClick.getPlaylistUsuarioActual().size() > 1) {
                    menu.add(Menu.NONE, 14, Menu.NONE, "Agregar a playlist");
                }

                // Establecer el escuchador para el menú
                popupMenu.setOnMenuItemClickListener(popUpMenuLongClick);

                // Mostrar el menú emergente
                popupMenu.show();
                return true;
            }
        });


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
