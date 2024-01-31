package com.example.receptkezelo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.receptkezelo.data.RendesItem
import com.example.receptkezelo.databinding.ItemRendesListBinding

class RendesAdapter(private val listener: RendesItemClickListener) :
    RecyclerView.Adapter<RendesAdapter.RendesViewHolder>() {

    private val items = mutableListOf<RendesItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RendesViewHolder(
        ItemRendesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RendesViewHolder, position: Int) {
        val rendesItem = items[position]

        holder.binding.tvName.text = rendesItem.name
        holder.binding.tvDescription.text = rendesItem.description

        holder.binding.tvOsszetevok.text = rendesItem.ingredients

        holder.binding.ibRemove.setOnClickListener {
            listener.onItemDelete(rendesItem)
            items.remove(rendesItem)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: RendesItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(rendesItems: List<RendesItem>) {
        items.clear()
        items.addAll(rendesItems)
        notifyDataSetChanged()
    }

    interface RendesItemClickListener {
        fun onItemChanged(item: RendesItem)
        fun onItemDelete(item: RendesItem)
    }

    inner class RendesViewHolder(val binding: ItemRendesListBinding) : RecyclerView.ViewHolder(binding.root)
}