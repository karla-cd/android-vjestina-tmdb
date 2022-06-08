package agency.five.tmdb.vm

import agency.five.tmdb.di.api.MovieApi
import agency.five.tmdb.di.repo.MovieRepository
import androidx.lifecycle.ViewModel

class DetailsViewModel(
    private val movieId : Int,
    private val movieRepository : MovieRepository,
    val movieApi : MovieApi
) : ViewModel()
{
    suspend fun getMovieDetails(movieId : Int) = movieRepository.getMovieDetails(movieId)
}