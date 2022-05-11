package agency.five.tmdb

import agency.five.tmdb.di.modules.dataModule
import agency.five.tmdb.di.modules.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TmdbApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TmdbApplication)
            modules(
                listOf(dataModule, viewModelModule)
            )
        }
    }
}