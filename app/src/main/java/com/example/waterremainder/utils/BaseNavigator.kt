package com.example.waterremainder.utils

interface BaseNavigator {
    fun showLoading()
    fun hideLoading()
    fun showToast(msg: String)
    fun showSnackBar(message: String, isError: Boolean)
    fun showAlert(title: String, msg: String?)
    fun gotoLogin()
    fun finishActivity()
}