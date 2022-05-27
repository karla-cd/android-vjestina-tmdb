package agency.five.tmdb.ui

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*

interface MovieApi {
    suspend fun getPopularMovies() : MoviesResponse
    suspend fun getNowPlayingMovies() : MoviesResponse
    suspend fun getUpcomingMovies() : MoviesResponse
    suspend fun getTopRatedMovies() : MoviesResponse
    suspend fun getMovieDetails(movieId : Int) : MovieDetailsResponse
}

internal class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {

    override suspend fun getPopularMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/popular?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getNowPlayingMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getUpcomingMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/upcoming?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getTopRatedMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/top_rated?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getMovieDetails(movieId : Int): MovieDetailsResponse = client.get("https://api.themoviedb.org/3/movie/${movieId}?api_key=8c4bf1b3b1e7d645233f7a48cd613638")
}