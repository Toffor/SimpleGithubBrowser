package com.coskun.simplegithubbrowser.ui.common.model

import androidx.annotation.StringRes

data class ErrorStatus(
    @StringRes val errorMessage: Int,
    @StringRes val actionMessage: Int,
    val errorCode: Int
)