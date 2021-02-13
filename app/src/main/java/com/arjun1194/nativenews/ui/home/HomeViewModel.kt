package com.arjun1194.nativenews.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.arjun1194.nativenews.data.model.NewsResponse
import com.arjun1194.nativenews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val response = MutableLiveData<NewsResponse>()
    val showLoader = MutableLiveData<Boolean>(false)

    fun getTopHeadlines(): MutableLiveData<NewsResponse> {
        //show loader true
        showLoader.postValue(true)
        viewModelScope.launch {
            newsRepository.getTopHeadlines()
                    .onEach {
                        response.postValue(it)
                        showLoader.postValue(false)
                    }.launchIn(viewModelScope)
        }

        //show loader false
        return response
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}