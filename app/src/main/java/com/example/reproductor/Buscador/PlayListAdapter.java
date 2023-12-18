package com.example.reproductor.Buscador;

import android.content.Context;
<<<<<<< HEAD
=======
import android.content.Intent;
import android.os.Parcelable;
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.Entities.Usuarios;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.example.reproductor.R;

=======
import com.example.reproductor.PlayList.CancionesPlayList;
import com.example.reproductor.R;

import java.io.Serializable;
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    private Context context;
    private List<PlayList> playlists;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listas_horizontal, parent, false);
        return new ViewHolder(view);
    }

    public PlayListAdapter(Context context, List<PlayList> playList){
        this.context = context;
        this.playlists = playList;
    }

    // Interfaz para el escuchador de clics
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Método para establecer el escuchador
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList currentPlaylist = playlists.get(position);

        // configuramos la foto y el nombre de la playlist
        holder.txtNombreCancionHorizontal.setText(currentPlaylist.getNombre());
        Glide.with(this.context)
                .load(currentPlaylist.getImgURLPlaylist())
                .placeholder(R.drawable.cargando_cancion_image)
                .into(holder.imgListasHorizontal);

        //Configurar el clic en el ViewHolder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
<<<<<<< HEAD
=======
                    Intent intent = new Intent(context, CancionesPlayList.class);
                    intent.putExtra("playlist", currentPlaylist);
                    context.startActivity(intent);
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (playlists != null)
            return playlists.size();

        return 0;
    }

    public void setPlaylists(List<PlayList> playlists){
        this.playlists = playlists;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgListasHorizontal;
        TextView txtNombreCancionHorizontal;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgListasHorizontal = itemView.findViewById(R.id.imgListaHorizontal);
            this.txtNombreCancionHorizontal = itemView.findViewById(R.id.txtNombreCancionHorizontal);

        }
    }
}
