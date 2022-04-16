package com.nikol412.poopysquish.ui.main.cardsAdapter.entity

import com.nikol412.poopysquish.ui.main.adapter.StatusItem

data class CardItem(
    val id: Int,
    val name: String?,
    val description: String?,
    val status: StatusItem?,
    val assignTo: List<String>,
)