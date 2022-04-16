package com.nikol412.poopysquish.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.nikol412.poopysquish.databinding.ItemKanbanColumnBinding
import com.nikol412.poopysquish.ui.main.cardsAdapter.CardsAdapter
import com.nikol412.poopysquish.ui.utils.SpacesItemDecoration

class KanbanColumnViewHolder(private val binding: ItemKanbanColumnBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val adapter by lazy {
        val cardsAdapter = CardsAdapter()
        binding.recyclerViewCards.adapter = cardsAdapter
        binding.recyclerViewCards.addItemDecoration(SpacesItemDecoration(topSpace = 10))
        cardsAdapter
    }

    fun onBind(item: KanbanColumnItem) {
        with(binding) {
            textViewCardName.text = item.name
            adapter.setNewItems(item.cards)
        }
    }
}