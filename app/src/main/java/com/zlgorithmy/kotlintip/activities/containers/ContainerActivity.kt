package com.zlgorithmy.kotlintip.activities.containers

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zlgorithmy.kotlintip.R
import com.zlgorithmy.kotlintip.adapter.KotlinRecyclerAdapter
import kotlinx.android.synthetic.main.activity_container.*
import org.jetbrains.anko.toast

class ContainerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        initView()
    }

    private fun initView() {
        initSpinner()
        initRecyclerView()
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
                toast("$position")
            }
        })
    }


    internal inner class OnItemSelectedListener : AdapterView.OnItemSelectedListener {
        private var mIsFirstSelect = true
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
