package id.cahya.core.util

import id.cahya.core.domain.model.Movie
import id.cahya.core.domain.model.SearchItem

object DataDummy {

    fun generateDummyMovie(): List<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                872585,
                "Oppenheimer",
                "The story of J. Robert Oppenheimer’s role in the development of the atomic bomb during World War II.",
                "/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
                "/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
                "2023-07-19",
                1440,
                8.3
            )
        )

        movies.add(
            Movie(
                346698,
                "Barbie",
                "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
                "/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg",
                "/nHf61UzkfFno5X1ofIhugCPus2R.jpg",
                "2023-07-19",
                2358,
                7.5,
            )
        )

        return movies
    }

    fun generateDummyMovieDetail(): List<Movie> {
        val detailMovies = ArrayList<Movie>()

        detailMovies.add(
            Movie(
                872585,
                "Oppenheimer",
                "The story of J. Robert Oppenheimer’s role in the development of the atomic bomb during World War II.",
                "/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
                "/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
                "2023-07-19",
                1440,
                8.3,
                110,
                arrayListOf(
                    "Fantasy",
                    "Action",
                    "Adventure",
                    "Science Fiction",
                    "Thriller"
                ).toString(),
            )
        )

        detailMovies.add(
            Movie(
                346698,
                "Barbie",
                "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
                "/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg",
                "/nHf61UzkfFno5X1ofIhugCPus2R.jpg",
                "2023-07-19",
                2358,
                7.5,
                107,
                arrayListOf(
                    "Animation",
                    "Adventure",
                    "Fantasy",
                    "Family",
                    "Action"
                ).toString(),
            )
        )

        return detailMovies
    }

    fun generateDummySearch(): List<SearchItem> {
        val movies = ArrayList<SearchItem>()

        movies.add(
            SearchItem(
                346698,
                "Barbie",
                "/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg",
                "/nHf61UzkfFno5X1ofIhugCPus2R.jpg",
                "movie",
                "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
                2358,
                7.5,
                "2023-07-19"
                )
        )

        movies.add(
            SearchItem(
                346698,
                "Barbie",
                "/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg",
                "/nHf61UzkfFno5X1ofIhugCPus2R.jpg",
                "movie",
                "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
                2358,
                7.5,
                "2023-07-19",
                )
        )

        return movies
    }
}