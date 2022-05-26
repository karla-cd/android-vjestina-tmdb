package agency.five.tmdb.di.modules

import agency.five.tmdb.di.ktor.KtorClient
import agency.five.tmdb.ui.*
import agency.five.tmdb.ui.MovieApiImpl
import agency.five.tmdb.ui.MovieRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(MovieApiImpl(KtorClient().httpClient), Database())
    }
    single<MovieApi> {
        MovieApiImpl(KtorClient().httpClient)
    }
    single {
        KtorClient().httpClient
    }
}