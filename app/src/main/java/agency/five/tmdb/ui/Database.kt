package agency.five.tmdb.ui

class Database() {

    var popularMovies : List<MovieItem> = listOf(
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

    var favoriteMovies : MutableList<MovieItem> = mutableListOf()

    var streamingMovies : List<MovieItem> = listOf(
        MovieItem(
            id = 1,
            title = "Goodfellas",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDgtN2RjYy00YzM1LWI4ZmQtMjIwYjFjNmI3ZGEwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,3,128,176_AL_.jpg"
        ),
        MovieItem(
            id = 2,
            title = "Seven Samurai",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BOWE4ZDdhNmMtNzE5ZC00NzExLTlhNGMtY2ZhYjYzODEzODA1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UX128_CR0,1,128,176_AL_.jpg"
        ),
        MovieItem(
            id = 3,
            title = "Se7en",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX128_CR0,3,128,176_AL_.jpg"
        )
    )

    var tVMovies : List<MovieItem> = listOf(
        MovieItem(
            id = 1,
            title = "Toy Story",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX128_CR0,3,128,176_AL_.jpg"
        ),
        MovieItem(
            id = 2,
            title = "City Lights",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BY2I4MmM1N2EtM2YzOS00OWUzLTkzYzctNDc5NDg2N2IyODJmXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,3,128,176_AL_.jpg"
        ),
        MovieItem(
            id = 3,
            title = "Hamilton",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BY2I4MmM1N2EtM2YzOS00OWUzLTkzYzctNDc5NDg2N2IyODJmXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,3,128,176_AL_.jpg"
        )
    )

    var onRentMovies : List<MovieItem> = listOf(
        MovieItem(
            id = 1,
            title = "WALL·E",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BMjExMTg5OTU0NF5BMl5BanBnXkFtZTcwMjMxMzMzMw@@._V1_UX128_CR0,3,128,176_AL_.jpg"
        ),
        MovieItem(
            id = 2,
            title = "Spider-Man: Into the Spider-Verse",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_UX128_CR0,3,128,176_AL_.jpg"
        ),
        MovieItem(
            id = 3,
            title = "Coco",
            overview = "Overview",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_UX128_CR0,1,128,176_AL_.jpg"
        )
    )

    fun addFavoriteMovie(movie : MovieItem) : List<MovieItem> {
        favoriteMovies.add(movie)
        return favoriteMovies
    }

    fun removeFromFavorites(movie : MovieItem) : List<MovieItem> {
        favoriteMovies.remove(movie)
        var i : Int = 1
        for (item in favoriteMovies) {
            item.id = i
            i += 1
        }
        return favoriteMovies
    }

}