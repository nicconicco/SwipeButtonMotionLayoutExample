package com.nicco.motionlayoutexamples

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import android.util.DisplayMetrics


class SwipeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : MotionLayout(context, attrs, defStyleAttr) {

    private lateinit var listener: SwipeButtonListener
    private lateinit var textviewContinue: TextView
    private lateinit var background: View
    private lateinit var backgroundWhite: View

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
        textviewContinue = findViewById(R.id.textview_continue)
        background = findViewById(R.id.background)
        backgroundWhite = findViewById(R.id.background_white)

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
                findViewById<ImageView>(R.id.img).setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_baseline_arrow_forward_ios_24
                    )
                )
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (background.width == backgroundWhite.width) {
                    textviewContinue.setOnClickListener {
                        listener.clicked()
                    }

                    findViewById<ImageView>(R.id.img).setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_baseline_done_24
                        )
                    )

                    findViewById<MotionLayout>(R.id.rootLayout).isInteractionEnabled = false
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
            R.styleable.design_system
        )

        val size = typeArray.indexCount

        (0 until size).asSequence()
            .map { typeArray.getIndex(it) }
            .forEach {
                when (it) {
                    R.styleable.design_system_primary_text ->
                        primaryTextResource = typeArray.getResourceId(it, 0)
                    R.styleable.design_system_secondary_text ->
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
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()