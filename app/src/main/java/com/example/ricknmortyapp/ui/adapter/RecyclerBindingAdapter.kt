package com.example.ricknmortyapp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.ricknmortyapp.R


class RecyclerBindingAdapter<T>(
    val holderLayout: Int,
    private val variableId: Int,
    private val items: MutableList<T>
):
    RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder>() {

    var onClick: (item: T, position: Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            onClick(item, position)
        }
        holder.getBinding().setVariable(variableId, item)
    }

    override fun getItemCount() = items.size

    class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val binding: ViewDataBinding = DataBindingUtil.bind(v)!!

        fun getBinding(): ViewDataBinding {
            return binding
        }
    }
}