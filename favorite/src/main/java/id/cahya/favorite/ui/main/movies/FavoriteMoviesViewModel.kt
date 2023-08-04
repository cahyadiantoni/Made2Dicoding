package id.cahya.favorite.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.domain.model.Movie
import id.cahya.core.domain.usecase.TmdbUseCase

class FavoriteMoviesViewModel
    (private val tmdbUseCase: TmdbUseCase): ViewModel(){
    fun getMovieFav(): LiveData<List<Movie>> =
        tmdbUseCase.getFavoriteMovie().asLiveData()
}