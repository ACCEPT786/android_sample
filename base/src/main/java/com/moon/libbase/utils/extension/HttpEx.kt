package com.moon.libbase.utils.extension

import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * @author ry
 * @date 2019-12-23
 */
@JvmField
val TYPE_PROTO = MediaType.parse("application/x-protobuf")

@JvmField
val TYPE_JSON = MediaType.parse("application/json")

fun String.toJsonRequestBody():RequestBody{
    return RequestBody.create(TYPE_JSON, this)
}