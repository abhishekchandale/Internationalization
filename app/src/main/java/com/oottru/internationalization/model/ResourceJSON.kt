package com.oottru.internationalization.model

class ResourceJSON() {

    lateinit var resource_key: String;
    lateinit var value: String
    var id: Int = 0
    lateinit var locale: String

    override fun toString(): String {
        return "ResourceJSON(resource_key='$resource_key', value='$value', id=$id, locale='$locale')"
    }
}
