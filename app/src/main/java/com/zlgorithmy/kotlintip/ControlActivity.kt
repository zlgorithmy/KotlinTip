package com.zlgorithmy.kotlintip

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zlgorithmy.kotlintip.adapter.KotlinRecycleAdapter
import com.zlgorithmy.kotlintip.setting.SettingsActivity
import kotlinx.android.synthetic.main.activity_control.*
import org.jetbrains.anko.startActivity

class ControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        initView()
    }

    private fun initBtn() {
        button?.setOnClickListener { startActivity<SettingsActivity>() }
    }

    private fun initRecyclerView() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        mRecyclerView.layoutManager = layoutManager

        // itemDecoration
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.king_yna,null))
        mRecyclerView.addItemDecoration(itemDecoration)

        // animation
        mRecyclerView.itemAnimator = DefaultItemAnimator()

        // setAdapter
        val adapter = KotlinRecycleAdapter(
            this,
            arrayOf(
                resources.getString(R.string.one),
                resources.getString(R.string.two),
                resources.getString(R.string.three)
            )
        )
        mRecyclerView.adapter = adapter
        // itemClick
        adapter.setOnKotlinItemClickListener(object :
            KotlinRecycleAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: String) {
                Toast.makeText(applicationContext, position, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initWeb() {
        web?.loadUrl("https://www.jianshu.com/")
    }

    private fun initVideo() {
        //videoView?.setMediaController(MediaController(this))
        videoView?.setVideoURI(Uri.parse("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"))
        videoView?.requestFocus()
        videoView?.start()
        videoView?.setOnCompletionListener { videoView?.start() }
    }

    private fun initView() {
        initBtn()
        initRecyclerView()
        initWeb()
        initVideo()
    }
}
