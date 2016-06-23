package com.example.deerhunter.kotlindagger2mvpexample.adapters.dividers

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

open class DividerItemDecoration @JvmOverloads constructor(context: Context, orientation: Int, @DrawableRes dividerRes: Int, val startMargin: Int = 0, val endMargin: Int = 0) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable
    private var mOrientation: Int = 0

    init {
        mDivider = context.resources.getDrawable(dividerRes)
        setOrientation(orientation)
    }

    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + startMargin
        val right = parent.width - parent.paddingRight - endMargin
        val childCount = parent.childCount
        for (i in 0..childCount - 1) {
            val child = parent.getChildAt(i)
            if(isDecorated(child, parent)) {
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin +
                        Math.round(ViewCompat.getTranslationY(child))
                val bottom = top + mDivider.intrinsicHeight
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }

    open fun isDecorated(view: View, parent: RecyclerView): Boolean = true

    fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop + startMargin
        val bottom = parent.height - parent.paddingBottom - endMargin
        val childCount = parent.childCount
        for (i in 0..childCount - 1) {
            val child = parent.getChildAt(i)
            if(isDecorated(child, parent)) {
                val params = child.layoutParams as RecyclerView.LayoutParams
                val left = child.right + params.rightMargin +
                        Math.round(ViewCompat.getTranslationX(child))
                val right = left + mDivider.intrinsicHeight
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (isDecorated(view, parent)) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.intrinsicHeight)
            } else {
                outRect.set(0, 0, mDivider.intrinsicWidth, 0)
            }
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
        val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }
}