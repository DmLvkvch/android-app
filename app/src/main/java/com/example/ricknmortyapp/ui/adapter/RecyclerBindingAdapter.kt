package com.example.ricknmortyapp.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.annotations.NonNull


class RecyclerBindingAdapter<T : Any>(private val layout: Int, private val variableId: Int) :
    PagingDataAdapter<T, RecyclerView.ViewHolder>(DiffUtilComparator<T>()) {

    private val LOADING = 0
    private val ITEM = 1

    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: (item: T, position: Int) -> Unit = { _, _ -> }
    var onLoadingData: () -> Unit = { -> }
    var onLoadingDataEnd: () -> Unit = { -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BindingHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (getItemViewType(position) == ITEM) {
            onLoadingDataEnd()
            holder.itemView.setOnClickListener {
                onClick(item, position)
            }
            holder as BindingHolder
            holder.binding?.setVariable(variableId, item)
        } else {
            onLoadingData()
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size - 1) LOADING else ITEM
    }

    class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(v)
    }

    class DiffUtilComparator<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(@NonNull oldItem: T, @NonNull newItem: T): Boolean {
            return oldItem!! == newItem
        }

    }
}