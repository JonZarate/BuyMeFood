package com.jonzarate.buymefood.itemlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonzarate.buymefood.data.model.Group
import com.jonzarate.buymefood.data.repo.UserSource
import com.jonzarate.buymefood.usecase.GetGroupInteractor
import com.jonzarate.buymefood.usecase.RefreshGroupInteractor

class ItemListViewModel constructor(private var userSource : UserSource) : ViewModel(), GetGroupInteractor.Callback, RefreshGroupInteractor.Callback {

    init {
        GetGroupInteractor(this, userSource).execute()
    }

    var group : MutableLiveData<Group> = MutableLiveData()


    fun refresh () {
        RefreshGroupInteractor(this, userSource, group.value?.id).execute()
    }

    fun create() {

    }

    override fun onGetGroupSuccess(group: Group?) {
        this.group.value = group
    }

    override fun onGetGroupFail() {

    }

}