package com.zlgorithmy.kotlintip.common

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.zlgorithmy.kotlintip.R
import kotlinx.android.synthetic.main.activity_text_views.*
import org.jetbrains.anko.toast

class TextViewsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_views)
        initView()
    }

    private fun initView() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //toast(p0.toString())
            }
        })
        editText.setOnEditorActionListener { p0, p1, p2 ->
            toast( "${p0.text} $p1 $p2")
            true
        }
    }
}
