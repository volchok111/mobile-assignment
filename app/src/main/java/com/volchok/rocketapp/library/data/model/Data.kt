package com.volchok.rocketapp.library.data.model

sealed class Data<out T> {

    object Loading : Data<Nothing>() {
        override fun toString() = "Loading"
    }

    data class Success<out T>(val value: T) : Data<T>() {
        override fun toString() = "Success($value)"
    }

    data class Error(
        val cause: Throwable,
        val previousError: Error? = null,
    ) : Data<Nothing>() {
        override fun toString() = "Error ($cause) ${previousError?.let { "-> $it" }.orEmpty()}"
    }

    fun toDataUnit(): Data<Unit> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(this.cause, this.previousError)
            is Success -> Success(Unit)
        }
    }
}