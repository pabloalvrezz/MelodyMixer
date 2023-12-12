package com.example.reproductor.Buscador;

import android.content.Context;
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

import com.example.reproductor.R;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listas_horizontal, parent, false);
        return new ViewHolder(view);
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
