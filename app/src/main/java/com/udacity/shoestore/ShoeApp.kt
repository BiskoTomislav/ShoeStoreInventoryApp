package com.udacity.shoestore

import android.app.Application
import com.udacity.shoestore.models.NoUser
import com.udacity.shoestore.models.User
import timber.log.Timber

class ShoeApp : Application() {
    companion object {
        lateinit var user: User // Dummy global state for user logged in status
        lateinit var instance: ShoeApp
            private set

        fun logout() {
            Timber.d("logout")
            user = NoUser()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
        instance = this
        user = NoUser()
    }
}