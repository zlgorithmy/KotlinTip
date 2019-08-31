package com.zlgorithmy.kotlintip.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zlgorithmy.kotlintip.R
import com.zlgorithmy.kotlintip.activities.buttons.ButtonActivity
import com.zlgorithmy.kotlintip.activities.texts.TextViewsActivity
import com.zlgorithmy.kotlintip.activities.widgets.WidgetsActivity
import kotlinx.android.synthetic.main.activity_control.*
import org.jetbrains.anko.startActivity

class ControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        initView()
    }

    private fun initBtn() {
        mBtn_TextView?.setOnClickListener { startActivity<TextViewsActivity>() }
        mBtn_Button?.setOnClickListener { startActivity<ButtonActivity>() }
        mBtn_Widgets?.setOnClickListener { startActivity<WidgetsActivity>() }
    }

    private fun initView() {
        initBtn()
    }
}
