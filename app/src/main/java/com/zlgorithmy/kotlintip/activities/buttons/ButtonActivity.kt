package com.zlgorithmy.kotlintip.activities.buttons

import android.app.Activity
import android.os.Bundle
import android.widget.RadioButton
import com.google.android.material.chip.Chip
import com.zlgorithmy.kotlintip.R
import kotlinx.android.synthetic.main.activity_button.*
import org.jetbrains.anko.find
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
            mSwitch.text = getString(if (b) R.string.SwitchON else R.string.SwitchOFF)
        }

        mFloatingActionButton.setOnClickListener { toast("This is a FloatingActionButton!") }

        mChipGroup?.setOnCheckedChangeListener { group, i ->
            if (i in listOf(
                    R.id.mChip1,
                    R.id.mChip2,
                    R.id.mChip3,
                    R.id.mChip4
                )
            ) toast("${find<Chip>(i).text}")
            when (i) {
                -1 -> group.background = getDrawable(R.drawable.emerald_water)
                R.id.mChip1 -> group.background = getDrawable(R.drawable.lemon_twist)
                R.id.mChip2 -> group.background = getDrawable(R.drawable.evening_sunshine)
                R.id.mChip3 -> group.background = getDrawable(R.drawable.ali)
                R.id.mChip4 -> group.background = getDrawable(R.drawable.j_shine)
                else -> group.background = getDrawable(R.drawable.king_yna)
            }
        }
    }
}
