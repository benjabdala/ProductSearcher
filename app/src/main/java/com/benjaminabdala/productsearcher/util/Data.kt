package com.benjaminabdala.productsearcher.util

import java.lang.Exception

data class Data<RequestData>(
    var status: Status,
    var data: RequestData? = null,
    var error: Exception? = null
)

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}
