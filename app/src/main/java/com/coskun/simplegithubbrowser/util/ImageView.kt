package com.coskun.simplegithubbrowser.util

import android.animation.ValueAnimator
import android.widget.ImageView
import androidx.annotation.DrawableRes

fun ImageView.changeImageWithAnimation(@DrawableRes resourceId: Int) {
    var imageChanged = false
    val valueAnimator = ValueAnimator.ofFloat(1f, 0.7f, 1.3f, 1f)
    valueAnimator.duration = 300
    valueAnimator.addUpdateListener { animator ->
        val animatedValue = animator.animatedValue as Float
        scaleX = animatedValue
        scaleY = animatedValue

        if (animatedValue > 1.2 && imageChanged.not()) {
            imageChanged = true
            setImageResource(resourceId)
        }
    }
    valueAnimator.start()
}