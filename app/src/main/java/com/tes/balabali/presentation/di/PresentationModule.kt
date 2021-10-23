package com.tes.balabali.presentation.di

import com.tes.balabali.presentation.ui.viewmodel.GithubViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        GithubViewModel(get())
    }
}