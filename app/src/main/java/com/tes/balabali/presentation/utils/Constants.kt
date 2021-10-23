package com.tes.balabali.presentation.utils

object Constants {
    const val IS_USER_LOGIN = "IS_USER_LOGIN"
    const val SHARED_PREFERENCE_NAME = "UserPref"
    const val EMAIL = "email"
    const val TOKEN = "token"

    sealed class LoginFlow {
        object LoginPage : LoginFlow()
        object HomePage : LoginFlow()
    }
}