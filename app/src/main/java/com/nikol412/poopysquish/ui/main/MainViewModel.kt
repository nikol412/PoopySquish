package com.nikol412.poopysquish.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol412.poopysquish.R
import com.nikol412.poopysquish.ui.main.adapter.KanbanColumnItem
import com.nikol412.poopysquish.ui.main.adapter.StatusItem
import com.nikol412.poopysquish.ui.main.cardsAdapter.entity.CardItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val boardColumnsList = MutableLiveData<List<KanbanColumnItem>>()

    init {
        viewModelScope.launch {
            delay(1000)
            boardColumnsList.value = getKanbanColumnsList()
        }
    }

    private fun getKanbanColumnsList() =
        listOf(
            KanbanColumnItem(1, "TO-DO", getRandomCardsForColumn()),
            KanbanColumnItem(1, "IN PROGRESS", getRandomCardsForColumn()),
            KanbanColumnItem(1, "DONE", getRandomCardsForColumn()),
        )


    private fun getRandomCardsForColumn(): List<CardItem> {
        val cardsCount = Random.nextInt(2, 15)
        val cardsList = MutableList<CardItem>(cardsCount) {
            getRandomCard(it)
        }
        return cardsList
    }

    private fun getRandomCard(id: Int) =
        CardItem(
            id = id,
            name = if (Random.nextBoolean()) "card $id" else "Critical bug",
            description = if (Random.nextBoolean()) "desc $id" else "some description text 123",
            status = if (Random.nextBoolean()) StatusItem(
                color = getRandomColor(),
                text = "to discuss"
            ) else null,
            assignTo = if (Random.nextBoolean()) listOf("Tima") else emptyList()
        )

    private fun getRandomColor(): Int {
        val colorsListRes = listOf(
            R.color.gray_light,
            R.color.neutral_light,
            R.color.neutral_dark
        )

        return colorsListRes[Random.nextInt(colorsListRes.size - 1)]
    }
}