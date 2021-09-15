package com.nicco.motionlayoutexamples

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.motion.widget.MotionLayout

class SwipeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : MotionLayout(context, attrs, defStyleAttr) {

    private lateinit var listener: SwipeButtonListener

    @StringRes
    private var primaryTextResource: Int = 0

    @StringRes
    private var secondaryTextResource: Int = 0

    init {
        View.inflate(getContext(), R.layout.design_system_swipe_button, this)
        initAttrs(attrs)
        init()
    }

    private fun init() {
        findViewById<TextView>(R.id.textview_intro).setTextResourceIfNotZero(primaryTextResource)
        findViewById<TextView>(R.id.textview_continue).setTextResourceIfNotZero(
            secondaryTextResource
        )
        initSwipeListener()
    }

    private fun initSwipeListener() {
        findViewById<MotionLayout>(R.id.rootLayout).setTransitionListener(object :
            TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                listener.onSwipeCompleted()
                findViewById<TextView>(R.id.textview_continue).setOnClickListener {
                    listener.clicked()
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })
    }

    private fun initAttrs(attributSet: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(
            attributSet,
            R.styleable.design_system_swipe_button_component
        )

        val size = typeArray.indexCount

        (0 until size).asSequence()
            .map { typeArray.getIndex(it) }
            .forEach {
                when (it) {
                    R.styleable.design_system_swipe_button_component_primary_text ->
                        primaryTextResource = typeArray.getResourceId(it, 0)
                    R.styleable.design_system_swipe_button_component_secondary_text ->
                        secondaryTextResource = typeArray.getResourceId(it, 0)
                }
            }
    }

    fun setListener(swipeButtonListener: SwipeButtonListener) {
        this.listener = swipeButtonListener
    }
}

private fun TextView.setTextResourceIfNotZero(@StringRes text: Int) {
    if (text != 0) setText(text)
}
