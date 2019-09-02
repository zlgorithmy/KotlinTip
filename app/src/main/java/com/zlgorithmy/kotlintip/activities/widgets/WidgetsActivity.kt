package com.zlgorithmy.kotlintip.activities.widgets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zlgorithmy.kotlintip.R
import com.zlgorithmy.kotlintip.adapter.KotlinRecycleAdapter
import kotlinx.android.synthetic.main.activity_text_views.view.*
import kotlinx.android.synthetic.main.activity_widgets.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class WidgetsActivity : Activity() {
    private lateinit var mItems: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets)
        mItems = listOf(
            resources.getString(R.string.one),
            resources.getString(R.string.two),
            resources.getString(R.string.three)
        )
        initView()
    }

    private fun initView() {
        initVideo()
        initWeb()
        initRecyclerView()
        initSpinner()
        initCardView()
        initCalendarView()
        initProcessBar()
        initSeekBar()
        initRatingBar()
        initSearchView()
    }

    private fun initVideo() {
        //videoView?.setMediaController(MediaController(this))
        mVideoView?.setVideoURI(Uri.parse("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"))
        mVideoView?.requestFocus()
        mVideoView?.start()
        mVideoView?.setOnCompletionListener { mVideoView?.start() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWeb() {
        mWebView?.settings?.javaScriptEnabled = true
        mWebView?.settings?.blockNetworkImage = false
        mWebView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        mWebView?.loadUrl("http://www.doyoudo.com/resources")
    }

    private fun initRecyclerView() {
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        (mRecyclerView?.layoutManager as LinearLayoutManager).orientation =
            LinearLayoutManager.VERTICAL

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.king_yna, null))
        mRecyclerView?.addItemDecoration(itemDecoration)

        mRecyclerView?.itemAnimator = DefaultItemAnimator()

        val adapter = KotlinRecycleAdapter(
            this,
            mItems.toTypedArray()
        )
        mRecyclerView?.adapter = adapter

        adapter.setOnKotlinItemClickListener(object :
            KotlinRecycleAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: String) {
                toast(position)
            }
        })
    }

    private fun initSpinner() {
        val startAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item, mItems
        )
        startAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        mSpinner?.prompt = "请选择"
        mSpinner?.adapter = startAdapter

        mSpinner?.onItemSelectedListener = OnItemSelectedListener()

        mSpinnerText?.text = resources.getString(R.string.TextView)
        mSpinnerText?.setOnClickListener {
            selector("请选择", mItems) { _: DialogInterface, i: Int ->
                toast("你的选择是：${mItems[i]}")
            }
        }
    }

    private fun initCalendarView() {
        mCalendarView.setOnDateChangeListener { _, i, i2, i3 ->
            toast("$i-${i2 + 1}-$i3")
        }
    }

    private fun initCardView() {
        mCardView?.setOnClickListener {
            toast("CardView")
        }
    }

    private fun initProcessBar() {
        Thread(
            Runnable {
                for (i in 1..10) {
                    if (mProgressBar1.progress < 100) {
                        mProgressBar1.setProgress(mProgressBar1.progress + 10, true)
                    }
                    mProgressBar2.setProgress(i * 10, true)
                    Thread.sleep(1000)
                }
            }).start()
    }

    private fun initSeekBar() {
        mSeekBar1.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                toast("$p1 $p2")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                toast("onStartTrackingTouch")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                toast("onStopTrackingTouch")
            }
        })
        mSeekBar2.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                toast("$p1 $p2")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                toast("onStartTrackingTouch")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                toast("onStopTrackingTouch")
            }
        })
    }

    private fun initRatingBar() {
        mRatingBar.numStars = 10
        mRatingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
                toast("${p0.numStars} $p1 $p2")
            }
    }

    private fun initSearchView() {
        mSearchView.setIconifiedByDefault(false)
        mSearchView.setOnQueryTextFocusChangeListener { view, b ->
            toast("${view.text.text} $b")
        }
    }

    internal inner class OnItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toast("你的选择是：${mItems[position]}")
        }
    }
}
