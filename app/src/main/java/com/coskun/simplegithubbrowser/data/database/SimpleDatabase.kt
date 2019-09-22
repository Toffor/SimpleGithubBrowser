package com.coskun.simplegithubbrowser.data.database

import android.content.SharedPreferences
import androidx.core.content.edit
import com.coskun.simplegithubbrowser.di.AppScope
import com.coskun.simplegithubbrowser.util.logError
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import javax.inject.Inject

@AppScope
class SimpleDatabase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    moshi: Moshi
) {

    private val longSetAdapter: JsonAdapter<Set<Long>>

    init {
        val longSetType = Types.newParameterizedType(Set::class.java, Long::class.javaObjectType)
        longSetAdapter = moshi.adapter<Set<Long>>(longSetType)
    }

    fun putLongSet(key: String, value: Set<Long>) {
        val serializedValue = longSetAdapter.toJson(value)
        sharedPreferences.edit {
            putString(key, serializedValue)
        }
    }

    fun getLongSet(key: String): Set<Long> {
        val json = sharedPreferences.getString(key, null) ?: return emptySet()
        return try {
            longSetAdapter.fromJson(json) ?: emptySet()
        } catch (e: IOException) {
            logError(e)
            emptySet()
        }
    }

}