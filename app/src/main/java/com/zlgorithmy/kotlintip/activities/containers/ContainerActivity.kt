package com.zlgorithmy.kotlintip.activities.containers

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zlgorithmy.kotlintip.R
import com.zlgorithmy.kotlintip.adapter.KotlinRecyclerAdapter
import kotlinx.android.synthetic.main.activity_container.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.set

@ExperimentalUnsignedTypes
class ContainerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        initView()
    }

    private fun initView() {
        initSpinner()
        initRecyclerView()
        initScrollView()
        initViewPager()
        initAppBar()
    }

    private fun initSpinner() {
        mSpinner?.onItemSelectedListener = OnItemSelectedListener()
    }

    private fun initRecyclerView() {
        val colors = mapOf<String, Int>().toMutableMap()

        for ((idx, color) in resources.getStringArray(R.array.color_items).withIndex()) {
            colors[color] = resources.getIntArray(R.array.colors)[idx]
        }

        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        (mRecyclerView?.layoutManager as LinearLayoutManager).orientation =
            LinearLayoutManager.VERTICAL

        val itemDecoration = DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.ali_divider)!!)
        mRecyclerView?.addItemDecoration(itemDecoration)

        mRecyclerView?.itemAnimator = DefaultItemAnimator()

        val adapter = KotlinRecyclerAdapter(
            baseContext,
            colors.toMap()
        )
        mRecyclerView?.adapter = adapter

        adapter.setOnKotlinItemClickListener(object :
            KotlinRecyclerAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                val colorCode = resources.getIntArray(R.array.colors)[position]
                    .toUInt().toString(16).toUpperCase(Locale.getDefault()).replaceFirst("FF", "#")
                (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).primaryClip =
                    ClipData.newPlainText(resources.getStringArray(R.array.color_items)[position], colorCode)
                toast("已复制 $colorCode")
            }
        })
    }

    private fun initScrollView() {
        mButton_toBottom?.setOnClickListener {
            mScrollViewText.post { mScrollViewText.fullScroll(ScrollView.FOCUS_DOWN) }
        }
        mButton_toSpinner?.setOnClickListener {
            mScrollViewText.post { mScrollViewText.scrollTo(0, mSpinner.top) }
        }
        mButton_toRecyclerView?.setOnClickListener {
            mScrollViewText.post { mScrollViewText.scrollTo(0, mRecyclerView.top) }
        }
        mFloatingActionButton?.setOnClickListener {
            mScrollViewText.post { mScrollViewText.fullScroll(ScrollView.FOCUS_UP) }
            mHorizontalScrollView.post { mHorizontalScrollView.fullScroll(ScrollView.FOCUS_LEFT) }
        }

        mButton_toRight?.setOnClickListener {
            mHorizontalScrollView.post { mHorizontalScrollView.fullScroll(ScrollView.FOCUS_RIGHT) }
        }

        mButton_toStart?.setOnClickListener {
            mHorizontalScrollView.post { mHorizontalScrollView.smoothScrollTo(0, 0) }
        }

        mButton_toCenter?.setOnClickListener {
            toast("${mHorizontalScrollView.width}")
            mHorizontalScrollView.post { mHorizontalScrollView.smoothScrollTo(mHorizontalScrollView.width / 2, 0) }
        }

        var i = 0
        val btnItems = listOf(mBtn0!!, mBtn1!!, mBtn2!!, mBtn3!!, mBtn4!!, mBtn5!!)
        val rect = Rect()
        mHorizontalScrollView1?.getHitRect(rect)

        mFloatingActionButton1?.setOnClickListener {
            i = if (btnItems[btnItems.lastIndex].getLocalVisibleRect(rect)) 0 else i + 1
            mHorizontalScrollView1?.post {
                mHorizontalScrollView1?.smoothScrollTo(btnItems[i].left - btnItems[i].marginStart, 0)
            }
        }
    }

    private fun initViewPager() {
        mBtn_ViewPager?.setOnClickListener { startActivity<ViewPagerActivity>() }
    }

    private fun initAppBar() {
        mBtn_AppBar?.setOnClickListener { startActivity<AppBarActivity>() }
    }


    internal inner class OnItemSelectedListener(private var mIsFirstSelect: Boolean = true) : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            toast("onNothingSelected")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (mIsFirstSelect) {
                mIsFirstSelect = false
            } else {
                toast("onItemSelected：${(view as TextView).text}")
            }
        }
    }
}
