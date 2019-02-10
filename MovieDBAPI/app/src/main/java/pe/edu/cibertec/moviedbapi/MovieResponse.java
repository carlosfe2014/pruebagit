package pe.edu.cibertec.moviedbapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class MovieResponse {

    @SerializedName("results")
    private ArrayList<Movie> peliculas;

    public ArrayList<Movie> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Movie> peliculas) {
        this.peliculas = peliculas;
    }
}
