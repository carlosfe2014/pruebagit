package pe.edu.cibertec.moviedbapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> items;

    PeliculaAdapter peliculaAdapter;
    EditText etMovie;
    Button btnMovie;
    RecyclerView rvMovie;

    void searchMovies() {
        String apikey = "3cae426b920b29ed2fb1c0749f258325";
        String movie = etMovie.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/search/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        Call<MovieResponse> searchMethod = movieInterface.searchMovie(apikey, movie);

        searchMethod.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if(response.isSuccessful()){
                    MovieResponse movieResponse = response.body();
                    items = movieResponse.getPeliculas();
                    peliculaAdapter = new PeliculaAdapter(items);
                    rvMovie.setAdapter(peliculaAdapter);
                    rvMovie.setLayoutManager( new LinearLayoutManager(MainActivity.this));
                    Log.d("Main", String.valueOf(items.size()));
                } else {
                    Log.d("Main", "Error");
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Main activity", t.toString());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovie = findViewById(R.id.etMovie);
        btnMovie = findViewById(R.id.btnMovie);
        rvMovie = findViewById(R.id.rvMovie);

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, etMovie.getText().toString(), Toast.LENGTH_SHORT).show();
                searchMovies();
            }
        });
    }
}
