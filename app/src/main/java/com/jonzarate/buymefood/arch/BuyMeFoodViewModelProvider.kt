package com.jonzarate.buymefood.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonzarate.buymefood.data.repo.UserSource
import com.jonzarate.buymefood.itemlist.ItemListViewModel
import com.jonzarate.buymefood.login.LoginViewModel

class BuyMeFoodViewModelProvider(private val source : UserSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(source) as T
        } else if (modelClass.isAssignableFrom(ItemListViewModel::class.java)) {
            return ItemListViewModel(source) as T
        }

        throw IllegalArgumentException("ViewModel not registered")
    }
}