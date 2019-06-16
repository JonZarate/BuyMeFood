package com.jonzarate.buymefood.data.source.cache

import com.jonzarate.buymefood.data.model.Group
import com.jonzarate.buymefood.data.model.User


class Cache() : CacheSource {

    override var user: User? = null

    override var group: Group? = null

}