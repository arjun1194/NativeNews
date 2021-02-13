package com.arjun1194.nativenews.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    var timestamp: String
        get() {
            return sharedPreferences.getString(timeStampValue, "")!!
        }
        set(value) {
            sharedPreferences.edit()
                .putString(timeStampValue, value)
                .apply()
        }

    companion object {
        private const val timeStampValue = "timestamp"
    }
}