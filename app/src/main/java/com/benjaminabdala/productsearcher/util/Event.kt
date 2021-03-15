package com.benjaminabdala.productsearcher.util

open class Event<out T> (private val content: T) {

    fun getContent(): T = content
}
