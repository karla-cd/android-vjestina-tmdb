package agency.five.tmdb.ui

class Database() {

    var favoriteMovies : MutableList<Movie> = mutableListOf()

    fun addFavoriteMovie(movie : Movie) : List<Movie> {
        favoriteMovies.add(movie)
        return favoriteMovies
    }

    fun removeFromFavorites(movie : Movie) : List<Movie> {
        favoriteMovies.remove(movie)
        var i : Int = 1
        for (item in favoriteMovies) {
            item.id = i
            i += 1
        }
        return favoriteMovies
    }

}