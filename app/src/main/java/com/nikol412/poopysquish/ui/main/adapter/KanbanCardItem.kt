package com.nikol412.poopysquish.ui.main.adapter

import androidx.annotation.ColorRes
import androidx.annotation.IntegerRes

data class KanbanCardItem(
    val id: Int,
    val name: String?,
    val description: String?,
    val status: StatusItem?,
    val assignTo: String?
)

data class StatusItem(
    @ColorRes val color: Int,
    val text: String?
)


