package id.cahya.core.data.source.local.room

import androidx.room.*
import id.cahya.core.data.source.local.entity.MovieEntity
import id.cahya.core.data.source.local.entity.SeasonEntity
import id.cahya.core.data.source.local.entity.TvShowEntity
import id.cahya.core.data.source.local.entity.TvShowWithSeasonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TmdbDao {

    //Movie
    @Query("SELECT * FROM movies ORDER BY voteCount DESC")
    fun getDiscoverMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where favorited = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieById(movieId: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    //Tv Show
    @Query("SELECT * FROM tvShows ORDER BY voteCount DESC")
    fun getDiscoverTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows where favorited = 1")
    fun getFavoriteTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: String): Flow<TvShowEntity>

    @Transaction
    @Query("SELECT * FROM seasons LEFT JOIN tvShows ON tvShows.tvShowId = seasons.tvShowId WHERE seasons.tvShowId = :tvShowId")
    fun getSeasonByTvShowId(tvShowId: String): Flow<TvShowWithSeasonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeason(season: List<SeasonEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}