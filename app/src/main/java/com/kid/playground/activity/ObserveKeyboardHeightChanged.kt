package com.kid.playground.activity

import android.R
import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout

interface KeyboardHeightChangedListener {
    fun onChange(keyboardHeight: Int, screenHeight: Int)
}

//https://github.com/madebycm/AndroidBug5497Workaround/blob/master/AndroidBug5497Workaround.java
class ObserveKeyboardHeightChanged private constructor(
    activity: Activity,
    private var listener: KeyboardHeightChangedListener
) : OnGlobalLayoutListener {
    private val mChildOfContent: View
    private var usableBottomPrevious = 0
    private val frameLayoutParams: FrameLayout.LayoutParams

    companion object {
        // For more information, see https://issuetracker.google.com/issues/36911528
        // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.
        fun assistActivity(activity: Activity, listener: KeyboardHeightChangedListener) {
            ObserveKeyboardHeightChanged(activity, listener)
        }
    }

    init {
        val content = activity.findViewById<View>(R.id.content) as FrameLayout
        mChildOfContent = content.getChildAt(0)
        mChildOfContent.viewTreeObserver
            .addOnGlobalLayoutListener(this)
        frameLayoutParams = mChildOfContent.layoutParams as FrameLayout.LayoutParams
    }

    private fun unregister() {
        mChildOfContent.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        val usableBottomNow = computeUsableBottom()
        if (usableBottomNow != usableBottomPrevious) {
            val screenHeight = mChildOfContent.rootView.height
            val heightDifference = screenHeight - usableBottomNow
            if (heightDifference > screenHeight / 4) {
                // keyboard probably just became visible
                listener.onChange(heightDifference, screenHeight)
            } else {
                listener.onChange(0, screenHeight)
            }
            usableBottomPrevious = usableBottomNow
        }
    }

    private fun computeUsableBottom(): Int {
        val r = Rect()
        mChildOfContent.getWindowVisibleDisplayFrame(r)
        return r.bottom
    }
}