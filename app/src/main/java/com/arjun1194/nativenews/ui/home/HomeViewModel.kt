package com.arjun1194.nativenews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun1194.nativenews.data.model.NewsResponse
import com.arjun1194.nativenews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _response = MutableLiveData<NewsResponse>()
    val response: LiveData<NewsResponse> = _response
    val showLoader = MutableLiveData(false)


    fun getTopHeadlines() {
        showLoader.postValue(true)
        viewModelScope.launch {
            newsRepository.getTopHeadlines()
                .onEach {
                    _response.postValue(it)
                    showLoader.postValue(false)
                }.launchIn(viewModelScope)
        }

    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}