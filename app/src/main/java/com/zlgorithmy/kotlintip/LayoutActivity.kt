package com.zlgorithmy.kotlintip

import android.app.Activity
import android.os.Bundle
import com.zlgorithmy.kotlintip.layout.*
import kotlinx.android.synthetic.main.activity_layout.*
import org.jetbrains.anko.startActivity

class LayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        initView()
    }

    private fun initView() {
        Btn_LinearLayout.setOnClickListener {
            //startActivity(Intent(this, LinearLayoutActivity::class.java))
            startActivity<LinearLayoutActivity>(
                "start_time" to System.currentTimeMillis(),
                "message" to "good Morning"
            )
        }
        Btn_FrameLayout.setOnClickListener {
            startActivity<FrameLayoutActivity>()
        }
        Btn_RelativeLayout.setOnClickListener {
            startActivity<RelativeLayoutActivity>()
        }
        Btn_TableLayout.setOnClickListener {
            startActivity<TableLayoutActivity>()
        }
        Btn_AbsoluteLayout.setOnClickListener {
            startActivity<AbsoluteLayoutActivity>()
        }
        Btn_ConstraintLayout.setOnClickListener {
            startActivity<ConstraintLayoutActivity>()
        }
    }
}
