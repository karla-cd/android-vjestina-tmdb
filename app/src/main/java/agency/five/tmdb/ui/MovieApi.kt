package agency.five.tmdb.ui

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*

interface MovieApi {
    suspend fun getPopularMovies() : MoviesResponse
    suspend fun getStreamingMovies() : MoviesResponse
    suspend fun getTVMovies() : MoviesResponse
    suspend fun getOnRentMovies() : MoviesResponse
}

internal class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {

    override suspend fun getPopularMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/popular?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getStreamingMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getTVMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/upcoming?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

    override suspend fun getOnRentMovies(): MoviesResponse = client.get("https://api.themoviedb.org/3/movie/upcoming?api_key=8c4bf1b3b1e7d645233f7a48cd613638")

}