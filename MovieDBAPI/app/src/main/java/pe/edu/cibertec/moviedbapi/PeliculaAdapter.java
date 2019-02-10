package pe.edu.cibertec.moviedbapi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.LayoutPelicula> {


    ArrayList<Movie> peliculas;

    public PeliculaAdapter(ArrayList<Movie> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public LayoutPelicula onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.pelicula_layout, viewGroup, false);

        LayoutPelicula layoutPelicula = new LayoutPelicula(view);
        return layoutPelicula;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutPelicula layoutPelicula, int position) {

        layoutPelicula.tvNombre.setText(peliculas.get(position).getTitle());
        layoutPelicula.tvOverview.setText(peliculas.get(position).getOverview());
        layoutPelicula.tvDate.setText(peliculas.get(position).getReleaseDate());

        String url = "https://image.tmdb.org/t/p/w185/" + peliculas.get(position).getPosterPath();
        Glide.with(layoutPelicula.itemView).load(url).into(layoutPelicula.ivMovie);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class LayoutPelicula extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvOverview;
        TextView tvDate;
        ImageView ivMovie;


        public LayoutPelicula(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivMovie = itemView.findViewById(R.id.ivMovie);
        }
    }
}
