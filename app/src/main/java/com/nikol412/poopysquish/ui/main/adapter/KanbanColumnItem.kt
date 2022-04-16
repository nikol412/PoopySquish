package com.nikol412.poopysquish.ui.main.adapter

import com.nikol412.poopysquish.ui.main.cardsAdapter.entity.CardItem

data class KanbanColumnItem(
    val id: Int,
    val name: String,
    var cards: List<CardItem>
)
