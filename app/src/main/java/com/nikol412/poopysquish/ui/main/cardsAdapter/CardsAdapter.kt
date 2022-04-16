package com.nikol412.poopysquish.ui.main.cardsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.poopysquish.databinding.ItemCardBinding
import com.nikol412.poopysquish.ui.main.cardsAdapter.entity.CardItem

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {
    private val itemsList = mutableListOf<CardItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size

    fun setNewItems(newItems: List<CardItem>) {
        val diff = DiffCallback(itemsList, newItems)
        val diffResult = DiffUtil.calculateDiff(diff)
        itemsList.clear()
        itemsList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffCallback(
        private val oldList: List<CardItem>,
        private val newList: List<CardItem>
    ) : DiffUtil.Callback() {
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getNewListSize(): Int = newList.size

        override fun getOldListSize(): Int = oldList.size
    }

    class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CardItem) {
            with(binding) {
                item.name?.let {
                    textViewName.text = it
                }
                item.description?.let {
                    textViewDescription.text = it
                } ?: kotlin.run {
                    textViewDescription.visibility = View.GONE
                }
                item.assignTo.firstOrNull()?.let {
                    cardAssignToText.text = it
                } ?: kotlin.run {
                    cardAssignToLayout.visibility = View.GONE
                }

            }
        }
    }

}