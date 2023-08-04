package id.cahya.made1dicoding.di

import id.cahya.core.domain.usecase.TmdbInteractor
import id.cahya.core.domain.usecase.TmdbUseCase
import id.cahya.made1dicoding.ui.movies.MoviesViewModel
import id.cahya.made1dicoding.ui.tvshows.TvShowsViewModel
import id.cahya.made1dicoding.ui.moviedetail.MovieDetailViewModel
import id.cahya.made1dicoding.ui.search.SearchViewModel
import id.cahya.made1dicoding.ui.tvshowdetail.TvShowDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TmdbUseCase> { TmdbInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}