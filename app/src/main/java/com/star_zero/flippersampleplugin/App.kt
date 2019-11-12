package com.star_zero.flippersampleplugin

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.soloader.SoLoader

class App : Application() {

    val plugin by lazy {
        FlipperSamplePlugin()
    }

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(plugin)
            client.start()
        }
    }
}