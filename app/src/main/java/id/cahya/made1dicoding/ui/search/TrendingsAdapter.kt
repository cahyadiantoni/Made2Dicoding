package id.cahya.made1dicoding.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.cahya.made1dicoding.R
import id.cahya.core.domain.model.SearchItem
import id.cahya.made1dicoding.databinding.ItemMoviesTvshowsBinding
import id.cahya.made1dicoding.ui.moviedetail.MovieDetailActivity
import id.cahya.made1dicoding.ui.tvshowdetail.TvShowDetailActivity
import id.cahya.core.util.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TrendingsAdapter :
    ListAdapter<SearchItem, TrendingsAdapter.TrendingViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val itemsTrendingDetailBinding =
            ItemMoviesTvshowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(itemsTrendingDetailBinding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trending = getItem(position)
        holder.bind(trending)
    }

    class TrendingViewHolder(private val binding: ItemMoviesTvshowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trending: SearchItem) {
            with(binding) {
                tvItemTitle.text = trending.name
                tvItemDate.text = Utils.changeStringToDateFormat(trending.releaseOrAirDate)
                tvItemRating.rating = trending.voteAverage.toFloat() / 2
                tvItemSynopsis.text = trending.overview
                itemView.setOnClickListener {
                    //show detail page
                    if (trending.mediaType == "tv") {
                        val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                        intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, trending.id)
                        itemView.context.startActivity(intent)
                    } else if (trending.mediaType == "movie") {
                        val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, trending.id)
                        itemView.context.startActivity(intent)
                    }
                }
                Glide.with(itemView.context)
                    .load(trending.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}