package com.tes.balabali.presentation.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.tes.balabali.R
import com.google.android.material.snackbar.Snackbar

fun TextView.changeTextColor(changesPrice: Double, context: Context) {
    if (changesPrice < 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.red))
    } else if (changesPrice > 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.green_100))
    }
}

fun String.addPrefix(): String {
    val value = this.toDouble()
    return if (value < 0) {
        "-$value"
    } else if (value > 0) {
        "+$value"
    } else this
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.showIf(condition : () -> Boolean) {
    if (condition.invoke()) setVisible()
    else setGone()
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.showSlideUp() {
    setVisible()
    val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
    startAnimation(TranslateAnimation(0f, 0f, height.toFloat() + bottomMargin.toFloat(), 0f).apply {
        duration = 300
        fillAfter = true
    })
}

fun View.hideSlideDown() {
    if (this.visibility == View.VISIBLE) {
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(
            TranslateAnimation(
                0f,
                0f,
                0f,
                height.toFloat() + bottomMargin.toFloat()
            ).apply {
                duration = 300
                fillAfter = true
            })
        setGone()
    }
}

fun View.showErrorSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.red_700))
    snackbar.show()
}


fun View.showSuccessSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.green_100))
    snackbar.show()
}

fun Double?.orZero() = this ?: 0.0