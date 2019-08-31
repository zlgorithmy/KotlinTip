package com.zlgorithmy.kotlintip

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zlgorithmy.kotlintip.adapter.KotlinRecycleAdapter
import com.zlgorithmy.kotlintip.texts.TextViewsActivity
import com.zlgorithmy.kotlintip.setting.SettingsActivity
import kotlinx.android.synthetic.main.activity_control.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ControlActivity : AppCompatActivity() {
    private lateinit var mItems: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        mItems = arrayOf(
            resources.getString(R.string.one),
            resources.getString(R.string.two),
            resources.getString(R.string.three)
        ).toList()
        initView()
    }

    private fun initBtn() {
        button?.setOnClickListener { startActivity<SettingsActivity>() }
        mBtn_TextView?.setOnClickListener { startActivity<TextViewsActivity>() }
    }

    private fun initRecyclerView() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        mRecyclerView.layoutManager = layoutManager

        // itemDecoration
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.king_yna, null))
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

    private fun initSpinner() {
        //val sp = findViewById<View>(R.id.spinner) as Spinner
        val startAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mItems)
        startAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner?.prompt = "请选择"
        spinner?.adapter = startAdapter
        //sp.setSelection(0)
        spinner?.onItemSelectedListener = myItemClickListener()

        spinnerText?.text = resources.getString(R.string.TextView)
        spinnerText?.setOnClickListener {
            selector("请选择", mItems) { _: DialogInterface, i: Int ->
                toast("你的选择是：${mItems[i]}")
            }
        }
    }

    private fun initCardView() {
        mCardView?.setOnClickListener {
            toast("CardView")
        }
    }

    private fun initView() {
        initBtn()
        initRecyclerView()
        initWeb()
        initVideo()
        initSpinner()
        initCardView()
    }

    internal inner class myItemClickListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toast("你的选择是：${mItems[position]}")
        }
    }
}
