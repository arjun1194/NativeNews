package com.arjun1194.nativenews.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun1194.nativenews.data.model.DataResponse
import com.arjun1194.nativenews.data.model.Source
import com.arjun1194.nativenews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val sources = MutableLiveData<DataResponse<List<Source>>>();

    fun getSource() {
        viewModelScope.launch {
            newsRepository.getSources().onEach {
                sources.postValue(it)
            }.launchIn(viewModelScope)
        }
    }
}