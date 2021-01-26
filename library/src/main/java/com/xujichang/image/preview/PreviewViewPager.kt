package com.xujichang.image.preview

import android.content.Context
import android.view.MotionEvent
import androidx.viewpager2.widget.ViewPager2


class PreviewViewPager(context: Context)/* : ViewPager2(context) */{
    /*override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> isLock = true
            MotionEvent.ACTION_UP -> isLock = false
        }
        return if (isLock) {
            false // 不拦截，交给子View处理
        } else super.onInterceptTouchEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> isLock = true
            MotionEvent.ACTION_UP -> isLock = false
        }
        return super.dispatchTouchEvent(event)
    }*/
}