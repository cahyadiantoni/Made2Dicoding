package id.cahya.made1dicoding.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.Movie
import id.cahya.core.domain.usecase.TmdbUseCase
import id.cahya.core.util.DataDummy
import id.cahya.made1dicoding.util.MainDispatcherRule
import id.cahya.made1dicoding.util.getOrAwaitValue
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    private val movies = DataDummy.generateDummyMovie()

    @Mock
    private lateinit var tmdbUseCase: TmdbUseCase

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(tmdbUseCase)
    }

    @Test
    fun getMovies() {
        val dummyMovie= Resource.Success(movies)
        val movie = flowOf(dummyMovie)
        `when`(tmdbUseCase.getDiscoverMovies()).thenReturn(movie)

        val movieEntities = viewModel.getDiscoverMovies().getOrAwaitValue().data
        verify(tmdbUseCase).getDiscoverMovies()
        assertNotNull(movieEntities)
        assertEquals(2, movieEntities?.size)

        viewModel.getDiscoverMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}