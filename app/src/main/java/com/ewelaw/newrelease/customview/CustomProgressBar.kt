package com.ewelaw.newrelease.customview

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.ewelaw.newrelease.R

class CustomProgressBar(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private var currentPercentage = 0

    private val backgroundBarSpace = RectF()

    private val backgroundColor = context.resources.getColor(R.color.purple_200, null)
    private val progressBarColor =context.resources.getColor(R.color.purple_500, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, 7000)
    }

    private var backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 60f
        strokeCap = Paint.Cap.ROUND
        color = backgroundColor
    }

    private val progressBarPaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        color = progressBarColor
        strokeWidth = 40f
    }

    override fun onDraw(canvas: Canvas?) {
        setSpace()
        canvas?.let {
            drawBackground(it)
            drawDot(it)
        }
    }

    private fun setSpace() {
        backgroundBarSpace.set(
            0f,
            ((height) / 2f),
            width + 0f,
            ((height) / 2f)
        )
    }

    private fun drawDot(canvas: Canvas) {
        val percentageToFill = getCurrentPercentageToFill()
        canvas.drawCircle(
            percentageToFill,
            height / 2f, 3f,
            progressBarPaint
        )
    }

    private fun drawBackground(canvas: Canvas) {
        val radius = (height / 2).toFloat()
        canvas.drawRoundRect(backgroundBarSpace,
            radius, radius,
            backgroundPaint)
    }

    private fun getCurrentPercentageToFill() =
        (width * (currentPercentage / 100.0)).toFloat()

    fun animateProgress() {
        val valuesHolder = PropertyValuesHolder.ofFloat("percentage", 0f, 100f)
        val animator = ValueAnimator().apply {
            setValues(valuesHolder)
            duration = 5000
            addUpdateListener {
                val percentage = it.getAnimatedValue("percentage") as Float
                currentPercentage = percentage.toInt()
                invalidate()
            }
        }
        animator.start()
    }
}
