package com.zamorano.mybooks.model.api

import java.lang.Error

class ApiResultEntity<T> {
    var resultData: T? = null
    var errorData: Error? = null
    var responseCode: Int = 0
}
