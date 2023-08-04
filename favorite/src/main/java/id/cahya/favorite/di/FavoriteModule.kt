package id.cahya.favorite.di

import id.cahya.favorite.ui.main.movies.FavoriteMoviesViewModel
import id.cahya.favorite.ui.main.tvshows.FavoriteTvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteTvShowsViewModel(get()) }
}