package agency.five.tmdb.di.modules

import agency.five.tmdb.ui.Database
import agency.five.tmdb.ui.MovieApiImpl
import agency.five.tmdb.ui.MovieRepository
import agency.five.tmdb.ui.MovieRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(MovieApiImpl())
    }
    single { Database() }
}