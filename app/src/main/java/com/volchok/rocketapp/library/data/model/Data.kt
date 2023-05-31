package com.volchok.rocketapp.library.data.model

sealed class Data<out T> {

    data class Success<out T>(val value: T) : Data<T>() {
        override fun toString() = "Success($value)"
    }

    data class Error(
        val cause: Throwable,
        val previousError: Error? = null,
    ) : Data<Nothing>() {
        override fun toString() = "Error ($cause) ${previousError?.let { "-> $it" }.orEmpty()}"
    }

    fun getSuccessValueOrThrow(): T {
        return when (this) {
            is Error -> throw this.cause
            is Success -> this.value
        }
    }
}