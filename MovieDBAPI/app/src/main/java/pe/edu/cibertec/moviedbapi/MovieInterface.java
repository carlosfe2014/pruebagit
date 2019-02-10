package pe.edu.cibertec.moviedbapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("movie")
    Call<MovieResponse> searchMovie(@Query("api_key") String apikey, @Query("query") String movie);
}
