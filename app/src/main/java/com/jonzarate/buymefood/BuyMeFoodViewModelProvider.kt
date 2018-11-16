package com.jonzarate.buymefood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonzarate.buymefood.data.source.UserSource
import com.jonzarate.buymefood.login.LoginViewModel

class BuyMeFoodViewModelProvider(private val source : UserSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(source) as T
        }

        throw IllegalArgumentException("ViewModel not registered")
    }

}