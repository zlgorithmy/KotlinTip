package com.zlgorithmy.kotlintip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zlgorithmy.kotlintip.R

class KotlinRecycleAdapter(mContext: Context, private var list: Array<String>) :
    RecyclerView.Adapter<KotlinRecycleAdapter.MyHolder>() {
    private var context: Context? = mContext
    private var itemClickListener: IKotlinItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_multi_column_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.text.text = list[position]

        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(list[position])
        }

    }

    class MyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        // !! 断言
        var text: TextView = itemView!!.findViewById(R.id.item_text)
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: String)
    }

}