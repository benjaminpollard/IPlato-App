package com.idea.group.iplato

import android.app.Application
import io.realm.Realm

class IPlatoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}