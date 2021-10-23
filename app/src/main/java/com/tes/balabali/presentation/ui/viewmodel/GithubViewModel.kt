package com.tes.balabali.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tes.balabali.data.ApiRepositoryImpl
import com.tes.balabali.domain.common.Result
import com.tes.balabali.domain.common.SimpleResult
import com.tes.balabali.domain.model.DataModel
import com.tes.balabali.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GithubViewModel(val api: ApiRepositoryImpl) :
    BaseViewModel() {

    private var _githubData = MutableLiveData<SimpleResult<DataModel>>(Result.State.Loading)
    val githubData = _githubData as LiveData<SimpleResult<DataModel>>

    fun searchRepo(query: String, sort: String) {
        viewModelScope.launch {
            val result = api.searchRepo(query, sort)
            delay(1200)
            _githubData.postValue(result)
        }
    }

}