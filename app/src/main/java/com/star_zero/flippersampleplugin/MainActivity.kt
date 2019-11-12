package com.star_zero.flippersampleplugin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.star_zero.flippersampleplugin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // サンプルです。
    // プロダクションコードにはFlipperの実装が入らないようにしましょう
    private val plugin by lazy {
        (application as App).plugin
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            plugin.sendText(binding.text.text.toString())
        }

        plugin.data.observe(this) {
            // Flipperから受信
            binding.text.setText(it)
        }
    }
}
