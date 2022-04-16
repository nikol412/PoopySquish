package com.nikol412.poopysquish.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val leftSpace: Int = 0,
    private val topSpace: Int = 0,
    private val rightSpace: Int = 0,
    private val bottomSpace: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = leftSpace
        outRect.right = rightSpace
        outRect.bottom = bottomSpace

        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = topSpace
        }
    }
}