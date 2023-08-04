package id.cahya.made1dicoding.ui.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.TvShow
import id.cahya.core.domain.model.TvShowWithSeason
import id.cahya.core.domain.usecase.TmdbUseCase

class TvShowDetailViewModel
    (private val tmdbUseCase: TmdbUseCase) :
    ViewModel() {

    private val tvShow = MutableLiveData<TvShow>()

    fun setSelectedTvShow(tvShow: TvShow) {
        this.tvShow.value = tvShow
    }

    fun getTvShowDetail(showId: Int): LiveData<Resource<TvShow>> =
        tmdbUseCase.getTvShowDetail(showId.toString()).asLiveData()

    fun getTvShowSeasons(showId: Int): LiveData<Resource<TvShowWithSeason>> =
        tmdbUseCase.getTvShowWithSeason(showId.toString()).asLiveData()

    fun setFavorite(): Boolean {
        val tvShow = tvShow.value
        if (tvShow != null) {
            val newState = !tvShow.favorited
            tmdbUseCase.setFavoriteTvShow(tvShow, newState)
            return newState
        }
        return false
    }
}