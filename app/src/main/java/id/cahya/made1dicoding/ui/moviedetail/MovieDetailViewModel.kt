package id.cahya.made1dicoding.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.Movie
import id.cahya.core.domain.usecase.TmdbUseCase

class MovieDetailViewModel
    (private val tmdbUseCase: TmdbUseCase) :
    ViewModel() {

    private val movie = MutableLiveData<Movie>()

    fun setMovie(movie: Movie) {
        this.movie.value = movie
    }

    fun getMovieDetail(movieId: Int): LiveData<Resource<Movie>> =
        tmdbUseCase.getMovieDetail(movieId.toString()).asLiveData()

    fun setFavorite(): Boolean {
        val movie = movie.value
        if (movie != null) {
            val newState = !movie.favorited
            tmdbUseCase.setFavoriteMovie(movie, newState)
            return newState
        }
        return false
    }
}