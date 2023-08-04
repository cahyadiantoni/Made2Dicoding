package id.cahya.made1dicoding.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.cahya.core.data.Resource
import id.cahya.core.domain.model.SearchItem
import id.cahya.core.domain.usecase.TmdbUseCase

class SearchViewModel
    (private val tmdbUseCase: TmdbUseCase) :
    ViewModel() {

    fun getTrendings(): LiveData<Resource<List<SearchItem>>> = tmdbUseCase.getTrendings().asLiveData()

    fun getSearchResult(title: String): LiveData<Resource<List<SearchItem>>> =
        tmdbUseCase.getSearchResult(title).asLiveData()
}