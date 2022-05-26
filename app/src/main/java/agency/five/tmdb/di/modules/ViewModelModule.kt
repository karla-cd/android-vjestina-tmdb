package agency.five.tmdb.di.modules

import agency.five.tmdb.ui.FavoritesViewModel
import agency.five.tmdb.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        FavoritesViewModel(get())
    }
}