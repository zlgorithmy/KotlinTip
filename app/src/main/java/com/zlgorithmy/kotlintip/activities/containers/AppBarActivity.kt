package com.zlgorithmy.kotlintip.activities.containers

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.core.widget.NestedScrollView
import com.google.android.material.tabs.TabLayout
import com.zlgorithmy.kotlintip.R
import kotlinx.android.synthetic.main.activity_app_bar.*
import java.util.*


@ExperimentalUnsignedTypes
class AppBarActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_bar)
        initView()
    }

    private fun initView() {
        val textViews = listOf<TextView>().toMutableList()
        for ((idx, str) in resources.getStringArray(R.array.color_items).withIndex()) {

            val textView = LayoutInflater.from(this).inflate(R.layout.items_text_view, mLinearLayout, false) as TextView
            textView.text = str
            textView.setBackgroundColor(resources.getIntArray(R.array.colors)[idx])

            mLinearLayout?.addView(textView)
            textViews.add(textView)

            mTabs.addTab(mTabs.newTab().setText(resources.getStringArray(R.array.color_items)[idx]))
        }
        for (i in 0..6) {
            mLinearLayout?.addView(LayoutInflater.from(this).inflate(R.layout.items_text_view, mLinearLayout, false) as TextView)
        }
        mToolbar.title = getString(R.string.appBarLayout)

        mTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                println("${mNestedScrollView?.nextFocusDownId}")
                mNestedScrollView?.post {
                    mNestedScrollView?.scrollTo(
                        0,
                        textViews[tab.position % textViews.size].top
                    )
                }
            }
        })

        mNestedScrollView?.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            for ((idx, txt) in textViews.withIndex()) {
                if (scrollY in txt.top - txt.marginTop..txt.bottom) {
                    mTabs.setBackgroundColor(resources.getIntArray(R.array.colors)[idx])
                    mBottomAppBar?.setBackgroundColor(resources.getIntArray(R.array.colors)[idx])
                    val i = resources.getIntArray(R.array.colors).size - 1 - idx
                    mBottomAppBar?.setBackgroundColor(resources.getIntArray(R.array.colors)[i])

                    val text = findViewById<TextView>(resources.getIntArray(R.array.ids)[0])
                    text.setBackgroundColor(resources.getIntArray(R.array.colors)[idx])
                    text.text = resources.getIntArray(R.array.colors)[idx].toUInt().toString(16).toUpperCase(Locale.getDefault()).replaceFirst(
                        "FF",
                        "#"
                    )
                    break
                }
            }
        }

        mFloatingActionButton?.setOnClickListener {
            if (mTabs.selectedTabPosition == 0) {
                mNestedScrollView?.post { mNestedScrollView?.fullScroll(ScrollView.SCROLL_INDICATOR_TOP) }
            } else {
                mTabs.post { mTabs.getTabAt(0)?.select() }
            }
        }
        mTabs.setBackgroundColor(resources.getIntArray(R.array.colors)[0])

        mBottomAppBar?.title = getString(R.string.bottomAppBar)
        val textView = LayoutInflater.from(this).inflate(R.layout.items_text_view_bottom_bar, mLinearLayout, false) as TextView
        textView.text = resources.getIntArray(R.array.colors)[0].toUInt().toString(16).toUpperCase(Locale.getDefault()).replaceFirst(
            "FF",
            "#"
        )
        textView.setBackgroundColor(resources.getIntArray(R.array.colors)[0])
        textView.id = resources.getIntArray(R.array.ids)[0]
        mBottomAppBar?.addView(textView, 0)
    }
}