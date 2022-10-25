package com.example.gsnmusic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsnmusic.db.Musicas;

import java.util.ArrayList;
import java.util.List;

public class MusicasListAdapter extends RecyclerView.Adapter<MusicasListAdapter.MyViewHolder> {

    Context context;

    List<Musicas> musicasList;


    public MusicasListAdapter(List<Musicas> musicas){
        this.musicasList = musicas;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public MusicasListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclermusicas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.lbmusica.setText(musicasList.get(position).nomemusica);
        holder.lbartista.setText(musicasList.get(position).artista);
    }

    @Override
    public int getItemCount() {
        return this.musicasList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lbmusica;
        TextView lbartista;
        ImageButton playmusica;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbmusica = itemView.findViewById(R.id.lbmusica);
            lbartista = itemView.findViewById(R.id.lbartista);
        }
    }

    public Filter getMusicFilter() {
        return musicFilter;
    }

    private final Filter musicFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Musicas> filterlist = new ArrayList<>();
            Log.i("TAG", "performFiltering: " + constraint.toString());
            if (constraint.length() == 0){
                filterlist.addAll(musicasList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Musicas musica : musicasList){
                    if (musica.nomemusica.toLowerCase().contains(filterPattern)){
                        filterlist.add(musica);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filterlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            musicasList.clear();
            musicasList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}
