package agency.five.tmdb.ui

interface MovieApi {
    suspend fun getPopularMovies() : List<MovieItem>
}

internal class MovieApiImpl : MovieApi {
    override suspend fun getPopularMovies(): List<MovieItem> {
        return listOf(
            MovieItem(
                id = 1,
                title = "The Shawshank Redemption",
                overview = "Overview",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg"
            ),
            MovieItem(
                id = 2,
                title = "The Godfather",
                overview = "Overview",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,1,128,176_AL_.jpg"
            ),
            MovieItem(
                id = 3,
                title = "The Dark Knight",
                overview = "Overview",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX128_CR0,3,128,176_AL_.jpg"
            )
        )
    }
}