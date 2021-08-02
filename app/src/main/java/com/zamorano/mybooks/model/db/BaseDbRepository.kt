package com.zamorano.mybooks.model.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

abstract class BaseDbRepository {
    companion object {
        fun executeQuery(block: () -> Unit) {
            runBlocking {
                withContext(Dispatchers.Default) {
                    block()
                }
            }
        }
    }
}