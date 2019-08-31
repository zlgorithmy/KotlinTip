package com.zlgorithmy.kotlintip.activities.buttons

import android.app.Activity
import android.os.Bundle
import android.widget.RadioButton
import com.zlgorithmy.kotlintip.R
import kotlinx.android.synthetic.main.activity_button.*
import org.jetbrains.anko.toast

class ButtonActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        initView()
    }

    private fun initView() {
        mButton?.setOnClickListener { toast("This is a Button!") }
        mImageButton?.setOnClickListener { toast("This is a ImageButton!") }
        mCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                toast("CheckBox ON!")
                compoundButton.background = getDrawable(R.drawable.king_yna)
                compoundButton.text = getString(R.string.CheckBox_ON)
            } else {
                toast("CheckBox OFF!")
                compoundButton.text = getString(R.string.CheckBox_OFF)
                compoundButton.background = getDrawable(R.drawable.ali)
            }
        }

        mRadioGroup?.setOnCheckedChangeListener { radioGroup, i ->
            toast(
                "${findViewById<RadioButton>(
                    i
                ).text}"
            )
            when (i) {
                R.id.mRadioButton1 -> radioGroup.alpha = 0.1f
                R.id.mRadioButton2 -> radioGroup.alpha = 0.5f
                R.id.mRadioButton3 -> radioGroup.alpha = 1.0f
            }
        }

        mToggleButton.setOnCheckedChangeListener { _, b ->
            toast(if (b) "ToggleButton ON!" else "ToggleButton OFF!")
        }

        mSwitch.setOnCheckedChangeListener { _, b ->
            toast(if (b) "Switch ON!" else "Switch OFF!")
        }

        mFloatingActionButton.setOnClickListener { toast("This is a FloatingActionButton!") }
    }
}
