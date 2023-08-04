package id.cahya.core.domain.usecase

import id.cahya.core.data.Resource
import id.cahya.core.domain.model.Movie
import id.cahya.core.domain.model.SearchItem
import id.cahya.core.domain.model.TvShow
import id.cahya.core.domain.model.TvShowWithSeason
import kotlinx.coroutines.flow.Flow

interface TmdbUseCase {
    fun getDiscoverMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(movieId: String): Flow<Resource<Movie>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, newState: Boolean)

    fun getDiscoverTvShow(): Flow<Resource<List<TvShow>>>
    fun getTvShowDetail(showId: String): Flow<Resource<TvShow>>
    fun getTvShowWithSeason(showId: String): Flow<Resource<TvShowWithSeason>>
    fun getFavoriteTvShow(): Flow<List<TvShow>>
    fun setFavoriteTvShow(tvShow: TvShow, newState: Boolean)

    fun getSearchResult(title: String): Flow<Resource<List<SearchItem>>>
    fun getTrendings(): Flow<Resource<List<SearchItem>>>
}