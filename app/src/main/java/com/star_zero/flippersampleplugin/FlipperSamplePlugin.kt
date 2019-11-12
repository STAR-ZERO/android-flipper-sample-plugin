package com.star_zero.flippersampleplugin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.flipper.core.FlipperConnection
import com.facebook.flipper.core.FlipperObject
import com.facebook.flipper.core.FlipperPlugin

class FlipperSamplePlugin : FlipperPlugin {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    private var connection: FlipperConnection? = null

    override fun getId(): String = "sample"

    override fun onConnect(connection: FlipperConnection?) {
        this.connection = connection

        connection?.receive("send") { params, _ ->
            Log.d("FlipperSamplePlugin", "$params")
            _data.postValue(params.getString("text"))
        }
    }

    override fun onDisconnect() {
        connection = null
    }

    override fun runInBackground(): Boolean = false

    fun sendText(text: String) {
        val params = FlipperObject.Builder()
            .put("text", text)
            .build()
        connection?.send("send", params)
    }
}