package com.nikol412.poopysquish.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.poopysquish.databinding.ItemKanbanColumnBinding

class KanbanTableAdapter : RecyclerView.Adapter<KanbanColumnViewHolder>() {
    private val itemsList = mutableListOf<KanbanColumnItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KanbanColumnViewHolder =
        KanbanColumnViewHolder(
            ItemKanbanColumnBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: KanbanColumnViewHolder, position: Int) {
        holder.onBind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size

    fun setNewItems(newItems: List<KanbanColumnItem>) {
        val diff = DiffCallback(itemsList, newItems)
        val diffResult = DiffUtil.calculateDiff(diff)
        itemsList.clear()
        itemsList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffCallback(
        private val oldList: List<KanbanColumnItem>,
        private val newList: List<KanbanColumnItem>
    ) : DiffUtil.Callback() {
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getNewListSize(): Int = newList.size

        override fun getOldListSize(): Int = oldList.size
    }
}