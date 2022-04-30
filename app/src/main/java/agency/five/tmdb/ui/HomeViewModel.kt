package agency.five.tmdb.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.dsl.module

class HomeViewModel(val movieRepository : MovieRepository) : ViewModel() {
    private val _moviesPopular : MutableStateFlow<List<MovieItem>> = MutableStateFlow(emptyList())
    public val moviesPopular : Flow<List<MovieItem>> = _moviesPopular

    private val _moviesStreaming : MutableStateFlow<List<MovieItem>> = MutableStateFlow(emptyList())
    public val moviesStreaming : Flow<List<MovieItem>> = _moviesStreaming

    private val _moviesTV : MutableStateFlow<List<MovieItem>> = MutableStateFlow(emptyList())
    public val moviesTV : Flow<List<MovieItem>> = _moviesTV

    private val _moviesOnRent : MutableStateFlow<List<MovieItem>> = MutableStateFlow(emptyList())
    public val moviesOnRent : Flow<List<MovieItem>> = _moviesOnRent

    init {
        viewModelScope.launch {
            val moviesPopularList = movieRepository.getPopularMovies()
            _moviesPopular.emit(moviesPopularList)
            val moviesStreamingList = movieRepository.getStreamingMovies()
            _moviesStreaming.emit(moviesStreamingList)
            val moviesTVList = movieRepository.getTVMovies()
            _moviesTV.emit(moviesTVList)
            val moviesOnRentList = movieRepository.getOnRentMovies()
            _moviesOnRent.emit(moviesOnRentList)
        }
    }
}