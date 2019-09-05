package com.zlgorithmy.kotlintip.activities.containers

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import com.zlgorithmy.kotlintip.R
import com.zlgorithmy.kotlintip.adapter.KotlinPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*
import org.jetbrains.anko.toast
import java.util.*

@ExperimentalUnsignedTypes
class ViewPagerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        initView()
    }

    private fun initView() {
        initViewPager()
    }

    private fun initViewPager() {
        val colors = mapOf<String, Int>().toMutableMap()

        for ((idx, color) in resources.getStringArray(R.array.color_items).withIndex()) {
            colors[color] = resources.getIntArray(R.array.colors)[idx]
        }
        val adapter = KotlinPagerAdapter(this, colors.toMap())
        mViewPager?.adapter = adapter
        adapter.setOnKotlinItemClickListener(object :
            KotlinPagerAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                val colorCode = resources.getIntArray(R.array.colors)[position]
                    .toUInt().toString(16).toUpperCase(Locale.getDefault()).replaceFirst("FF", "#")
                (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).primaryClip =
                    ClipData.newPlainText(resources.getStringArray(R.array.color_items)[position], colorCode)
                toast("已复制 $colorCode")
            }
        })
    }
}
