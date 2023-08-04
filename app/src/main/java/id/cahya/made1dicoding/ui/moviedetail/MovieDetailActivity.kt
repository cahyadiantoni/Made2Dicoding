package id.cahya.made1dicoding.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.cahya.made1dicoding.R
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.Movie
import id.cahya.core.util.Utils.changeMinuteToDurationFormat
import id.cahya.core.util.Utils.changeStringToDateFormat
import id.cahya.made1dicoding.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            if (movieId != 0) {
                val movieDetails = movieDetailViewModel.getMovieDetail(movieId)
                movieDetails.observe(this) { movie ->
                    when (movie) {
                        is Resource.Loading -> binding.contentMovieDetail.progressCircular.visibility =
                            View.VISIBLE

                        is Resource.Success -> {
                            Log.i("result", movie.data.toString())
                            binding.contentMovieDetail.progressCircular.visibility = View.GONE
                            movie.data?.let {
                                movieDetailViewModel.setMovie(it)
                                showDetailMovie(it)
                            }
                        }

                        is Resource.Error -> {
                            binding.contentMovieDetail.progressCircular.visibility = View.GONE
                            Toast.makeText(
                                this,
                                getString(R.string.error_while_getting_data),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.fabFavorite.setOnClickListener {
            val newState = movieDetailViewModel.setFavorite()
            if (newState) {
                Toast.makeText(this, R.string.addedToFavorite, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.removedFromFavorite, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDetailMovie(movieDetails: Movie) {
        with(binding) {
            setFabIcon(movieDetails.favorited)
            toolbarLayout.title = movieDetails.title
            movieBackdrop.alpha = 0.75F
            contentMovieDetail.movieTitle.text = movieDetails.title
            contentMovieDetail.movieSinopsis.text = movieDetails.overview
            contentMovieDetail.movieReleaseDate.text =
                changeStringToDateFormat(movieDetails.releaseDate)
            contentMovieDetail.movieRating.rating =
                movieDetails.voteAverage.toFloat() / 2
            contentMovieDetail.movieGenres.text = movieDetails.genres
        }

        Glide.with(this)
            .load(movieDetails.posterPath)
            .transform(RoundedCorners(16))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.contentMovieDetail.moviePoster)

        Glide.with(this)
            .load(movieDetails.backdropPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.movieBackdrop)
    }

    private fun setFabIcon(isFavorited: Boolean) {
        if (isFavorited) {
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}