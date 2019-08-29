package com.zlgorithmy.kotlintip

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
