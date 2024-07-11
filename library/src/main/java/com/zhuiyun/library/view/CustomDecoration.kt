package com.inkbird.inkbirdapp.device.ibsm2sbt.widget

import android.R
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView


/**
 * 消除最后一条分割线
 * @author zengx
 */
class CustomDecoration(context: Context, orientation: Int, private val mIsShowBottomDivider: Boolean) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable?

    /**
     * Current orientation. Either [.HORIZONTAL] or [.VERTICAL].
     */
    private var mOrientation = 0
    private val mBounds = Rect()

    /**
     * Creates a divider [RecyclerView.ItemDecoration] that can be used with a
     *
     *
     * @param context             Current context, it will be used to access resources.
     * @param orientation         Divider orientation. Should be [.HORIZONTAL] or
     * [.VERTICAL].
     * @param isShowBottomDivider true show bottom divider false not show bottom divider
     */
    init {
        val a: TypedArray = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        if (mDivider == null) {
        }
        a.recycle()
        setOrientation(orientation)
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * [RecyclerView.LayoutManager] changes orientation.
     *
     * @param orientation [.HORIZONTAL] or [.VERTICAL]
     */
    fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL && orientation != VERTICAL)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        mOrientation = orientation
    }

    /**
     * Sets the [Drawable] for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    fun setDrawable(drawable: Drawable?) {
        requireNotNull(drawable) { "Drawable cannot be null." }
        mDivider = drawable
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || mDivider == null) {
            return
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent, state)
        } else {
            drawHorizontal(c, parent, state)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right,
                parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount
        val lastPosition = state.itemCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childRealPosition = parent.getChildAdapterPosition(child)
            //mIsShowBottomDivider false的时候不绘制最后一个view的divider
            if (mIsShowBottomDivider || childRealPosition < lastPosition) {
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val bottom = mBounds.bottom + Math.round(child.translationY)
                val top = bottom - mDivider!!.intrinsicHeight
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top,
                parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }

        val childCount = parent.childCount
        val lastPosition = state.itemCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childRealPosition = parent.getChildAdapterPosition(child)
            //mIsShowBottomDivider false的时候不绘制最后一个view的divider
            if (mIsShowBottomDivider || childRealPosition < lastPosition) {
                parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
                val right = mBounds.right + Math.round(child.translationX)
                val left = right - mDivider!!.intrinsicWidth
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (mDivider == null) {
            outRect[0, 0, 0] = 0
            return
        }
        if (mOrientation == VERTICAL) {
            val lastPosition = state.itemCount - 1
            val position = parent.getChildAdapterPosition(view)
            if (mIsShowBottomDivider || position < lastPosition) {
                outRect[0, 0, 0] = mDivider!!.intrinsicHeight
            } else {
                outRect[0, 0, 0] = 0
            }
        } else {
            val lastPosition = state.itemCount - 1
            val position = parent.getChildAdapterPosition(view)
            if (mIsShowBottomDivider || position < lastPosition) {
                outRect[0, 0, mDivider!!.intrinsicWidth] = 0
            } else {
                outRect[0, 0, 0] = 0
            }
        }
    }

    companion object {
        const val HORIZONTAL: Int = LinearLayout.HORIZONTAL
        const val VERTICAL: Int = LinearLayout.VERTICAL
        private const val TAG = "DividerItem"
        private val ATTRS = intArrayOf(R.attr.listDivider)
    }
}