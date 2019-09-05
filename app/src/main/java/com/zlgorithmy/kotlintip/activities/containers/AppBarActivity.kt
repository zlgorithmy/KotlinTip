package com.zlgorithmy.kotlintip.activities.containers

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.widget.NestedScrollView
import com.google.android.material.tabs.TabLayout
import com.zlgorithmy.kotlintip.R
import kotlinx.android.synthetic.main.activity_app_bar.*


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

        mToolbar.title = getString(R.string.appBarLayout)

        mTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                mNestedScrollView?.post {
                    mNestedScrollView?.scrollTo(
                        0,
                        textViews[tab.position % textViews.size].top - textViews[tab.position % textViews.size].marginTop
                    )
                }
                mTabs.setBackgroundColor(resources.getIntArray(R.array.colors)[tab.position])
            }
        })

        mNestedScrollView?.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, oy: Int ->
            if (scrollY - oy <= 10 && scrollY - oy > -10) {
                mNestedScrollView?.stopNestedScroll()
                var i = 0
                for ((idx, txt) in textViews.withIndex()) {
                    if (scrollY in txt.top - txt.marginTop..txt.bottom + txt.marginBottom) {
                        i = idx
                        break
                    }
                }
                mTabs.setBackgroundColor(resources.getIntArray(R.array.colors)[i])
                if (mTabs.selectedTabPosition != i) {
                    mTabs.post { mTabs.getTabAt(i)?.select() }
                }
            }
        }
        mTabs.selectedTabPosition
        mFloatingActionButton?.setOnClickListener {
            if (mTabs.selectedTabPosition == 0) {
                mNestedScrollView?.post { mNestedScrollView?.fullScroll(ScrollView.SCROLL_INDICATOR_TOP) }
            } else {
                mTabs.post { mTabs.getTabAt(0)?.select() }
            }
        }
        mTabs.setBackgroundColor(resources.getIntArray(R.array.colors)[0])
    }
}