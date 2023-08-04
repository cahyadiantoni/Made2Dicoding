package id.cahya.made1dicoding.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.TvShow
import id.cahya.core.domain.usecase.TmdbUseCase

class TvShowsViewModel
    (private val tmdbUseCase: TmdbUseCase) :
    ViewModel() {

    fun getDiscoverTvShow(): LiveData<Resource<List<TvShow>>> =
        tmdbUseCase.getDiscoverTvShow().asLiveData()
}