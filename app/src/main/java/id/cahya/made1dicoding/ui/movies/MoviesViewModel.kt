package id.cahya.made1dicoding.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.Movie
import id.cahya.core.domain.usecase.TmdbUseCase

class MoviesViewModel
    (private val tmdbUseCase: TmdbUseCase) :
    ViewModel() {

    fun getDiscoverMovies(): LiveData<Resource<List<Movie>>> =
        tmdbUseCase.getDiscoverMovies().asLiveData()
}