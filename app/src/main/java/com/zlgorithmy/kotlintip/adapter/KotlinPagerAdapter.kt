package com.zlgorithmy.kotlintip.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

import com.zlgorithmy.kotlintip.R
import java.util.*

@ExperimentalUnsignedTypes
class KotlinPagerAdapter(private val mContext: Context, private var mColors: Map<String, Int>) : PagerAdapter() {
    private var itemClickListener: IKotlinItemClickListener? = null
    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(mContext, R.layout.items_view_pager, null)

        val mSpinner = view.findViewById<Spinner>(R.id.mSpinner)
        mSpinner.onItemSelectedListener = OnItemSelectedListener(view)
        mSpinner.setSelection(position % mColors.size)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @ExperimentalUnsignedTypes
    internal inner class OnItemSelectedListener(view: View) :
        AdapterView.OnItemSelectedListener {
        private var mView = view
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val color = mColors.getOrDefault((view as TextView).text.toString(), mColors.values.random())
            val mTextView = mView.findViewById<TextView>(R.id.mTextView)

            mTextView.text = color.toUInt().toString(16).toUpperCase(Locale.getDefault()).replaceFirst("FF", "#")
            mTextView.setBackgroundColor(color)
            itemClickListener?.onItemClickListener(position)
        }
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }
}
