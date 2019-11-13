package com.hgwxr.kotlintest.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hgwxr.gameplane.GameHomeActivity
import com.hgwxr.gametiantianshuqianlibrary.tiantianshuqian.TianTianShuQianActivity
import com.hgwxr.kotlintest.R
import com.hgwxr.navigationlibrary.NavigationActivity
import com.hgwxr.retrofitlibrary.RetrofitActivity
import kotlinx.android.synthetic.main.data_item.view.*

class DataAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = mutableListOf<DataItem>()

    init {
        list.add(
            DataItem(
                "",
                "retrofit-demo",
                View.OnClickListener { RetrofitActivity.start(context as Activity) })
        )
        list.add(
            DataItem(
                "",
                "Navigation-demo",
                View.OnClickListener { NavigationActivity.start(context as Activity) })
        )
        list.add(
            DataItem(
                "",
                "Game-demo",
                View.OnClickListener { GameHomeActivity.start(context as Activity) }
            ))
        list.add(
            DataItem(
                "",
                "TianTianShuQian-demo",
                View.OnClickListener { TianTianShuQianActivity.start(context as Activity) }
            ))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.bindData(list[position])
            }
            else -> {
            }
        }
    }
}


class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(item: DataItem) {
        itemView.item_data_tv.text = item.title
        itemView.item_root.setOnClickListener {
            item.jumpAction.onClick(it)
        }
    }
}

data class DataItem(val icon: String, val title: String, val jumpAction: View.OnClickListener) {}