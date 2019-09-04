package com.zlgorithmy.kotlintip.adapter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zlgorithmy.kotlintip.R
import java.util.*

@TargetApi(Build.VERSION_CODES.O)
class KotlinRecyclerAdapter(context: Context, private var mColors: Map<String, Int>) :
    RecyclerView.Adapter<KotlinRecyclerAdapter.ViewHolder>() {

    private var mContext: Context? = context
    private var itemClickListener: IKotlinItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.items_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mColors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
        holder.spinner.onItemSelectedListener = OnItemSelectedListener(holder)
        holder.spinner.setSelection(position)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        // !! 断言
        var text: TextView = itemView!!.findViewById(R.id.mTextView)
        var image: ImageView = itemView!!.findViewById(R.id.mImageView)
        var spinner: Spinner = itemView!!.findViewById(R.id.mSpinner)
    }

    internal inner class OnItemSelectedListener(holder: ViewHolder) :
        AdapterView.OnItemSelectedListener {
        private var mHolder = holder
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        @ExperimentalUnsignedTypes
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            //mHolder.text.text = (view as TextView).text
            val color = mColors.getOrDefault((view as TextView).text.toString(), mColors.values.random())

            mHolder.text.text = color.toULong().toString(16).replace("ffffffffff", "#").toUpperCase(Locale.getDefault())

            mHolder.image.setBackgroundColor(color)
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