package agency.five.tmdb.di.modules

import agency.five.tmdb.vm.FavoritesViewModel
import agency.five.tmdb.vm.HomeViewModel
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