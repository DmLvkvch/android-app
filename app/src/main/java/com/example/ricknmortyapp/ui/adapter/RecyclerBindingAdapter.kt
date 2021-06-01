package com.example.ricknmortyapp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.ricknmortyapp.R


class RecyclerBindingAdapter<T>(private val variableId: Int) :
    RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder>() {

    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: (item: T, position: Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            onClick(item, position)
        }
        holder.binding?.setVariable(variableId, item)
    }

    override fun getItemCount() = items.size

    class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(v)
    }
}