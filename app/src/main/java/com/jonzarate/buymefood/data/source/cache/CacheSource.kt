package com.jonzarate.buymefood.data.source.cache

import com.jonzarate.buymefood.data.model.Group
import com.jonzarate.buymefood.data.model.User

interface CacheSource {

    var user : User?

    var group : Group?

}