package com.zlgorithmy.kotlintip.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import com.zlgorithmy.kotlintip.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //supportActionBar?.hide()
        initView()
    }

    private fun initView() {
        Btn_layout.setOnClickListener {
            startActivity(Intent(this, LayoutActivity::class.java))
        }
        Btn_control.setOnClickListener {
            startActivity(Intent(this, ControlActivity::class.java))
        }
    }
}
