package agency.five.tmdb.di.modules

import agency.five.tmdb.di.api.MovieApi
import agency.five.tmdb.di.ktor.KtorClient
import agency.five.tmdb.di.repo.MovieRepository
import agency.five.tmdb.di.api.MovieApiImpl
import agency.five.tmdb.di.db.Database
import agency.five.tmdb.di.repo.MovieRepositoryImpl
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