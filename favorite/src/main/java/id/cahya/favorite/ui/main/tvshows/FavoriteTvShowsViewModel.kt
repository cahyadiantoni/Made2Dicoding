package id.cahya.favorite.ui.main.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.domain.model.TvShow
import id.cahya.core.domain.usecase.TmdbUseCase

class FavoriteTvShowsViewModel
    (private val tmdbUseCase: TmdbUseCase): ViewModel() {
    fun getTvShowFav(): LiveData<List<TvShow>> =
        tmdbUseCase.getFavoriteTvShow().asLiveData()
}