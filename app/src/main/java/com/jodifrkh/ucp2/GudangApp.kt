package com.jodifrkh.ucp2

import android.app.Application
import com.jodifrkh.ucp2.dependenciesinjection.ContainerApp

class GudangApp: Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}