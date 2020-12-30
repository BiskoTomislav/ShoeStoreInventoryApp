package com.udacity.shoestore.converters

import androidx.databinding.InverseMethod
import timber.log.Timber

object Converter {

    @InverseMethod("toDouble")
    @JvmStatic
    fun fromDouble(value: Double): String {
        Timber.d("fromDouble $value")
        return value.toString()
    }

    @JvmStatic
    fun toDouble(value: String): Double {
        Timber.d("toDouble $value")
        return when {
            value.isEmpty() -> 0.0
            else -> value.toDouble()
        }
    }
}