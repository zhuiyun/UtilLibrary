package com.zhuiyun.library.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

/**

 * @author: yun

 * @date: 2022/9/24 0024 17

 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "PlayAndroidDataStore")


suspend fun DataStore<Preferences>.saveBooleanData( key: String, value: Boolean) {
    this.edit { mutablePreferences ->
        mutablePreferences[booleanPreferencesKey(key)] = value
    }
}

suspend fun DataStore<Preferences>.saveIntData( key: String, value: Int) {
    this.edit { mutablePreferences ->
        mutablePreferences[intPreferencesKey(key)] = value
    }
}

suspend fun DataStore<Preferences>.saveStringData( key: String, value: String) {
    this.edit { mutablePreferences ->
        mutablePreferences[stringPreferencesKey(key)] = value
    }
}

suspend fun DataStore<Preferences>.saveFloatData( key: String, value: Float) {
    this.edit { mutablePreferences ->
        mutablePreferences[floatPreferencesKey(key)] = value
    }
}

suspend fun DataStore<Preferences>.saveLongData( key: String, value: Long) {
    this.edit { mutablePreferences ->
        mutablePreferences[longPreferencesKey(key)] = value
    }
}