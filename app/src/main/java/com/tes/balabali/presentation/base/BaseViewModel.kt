package com.tes.balabali.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val successSnackbar = MutableLiveData<String>()
    fun successSnackbar() = successSnackbar as LiveData<String>

    protected val dialog = MutableLiveData<String>()
    fun showLoading() = dialog as LiveData<String>

    protected fun postSuccessSnackbar(message: String) {
        successSnackbar.postValue(message)
    }

    protected fun showLoadingDialog(message: String) {
        dialog.postValue(message)
    }
}