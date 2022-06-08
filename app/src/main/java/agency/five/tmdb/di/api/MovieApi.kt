package agency.five.tmdb.di.api

import agency.five.tmdb.json.CastResponse
import agency.five.tmdb.json.MovieDetailsResponse
import agency.five.tmdb.json.MoviesResponse
import io.ktor.client.*
import io.ktor.client.request.*

interface MovieApi {
    suspend fun getPopularMovies() : MoviesResponse
    suspend fun getNowPlayingMovies() : MoviesResponse
    suspend fun getUpcomingMovies() : MoviesResponse
    suspend fun getTopRatedMovies() : MoviesResponse
    suspend fun getMovieDetails(movieId : Int) : MovieDetailsResponse
    suspend fun getMovieCast(movieId : Int) : CastResponse
    suspend fun searchMovie(query: String) : MoviesResponse
}

internal class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {

    override suspend fun getPopularMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/popular?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getNowPlayingMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getUpcomingMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/upcoming?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getTopRatedMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/top_rated?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getMovieDetails(movieId : Int): MovieDetailsResponse = client.get("https://api.themoviedb.org/3/movie/${movieId}?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getMovieCast(movieId: Int): CastResponse = client.get("https://api.themoviedb.org/3/movie/$movieId/credits?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun searchMovie(query: String): MoviesResponse = client.get("https://api.themoviedb.org/3/search/movie?query=$query?api_key=8c4bf1b3b1e7d645233f7a48cd613638")
}