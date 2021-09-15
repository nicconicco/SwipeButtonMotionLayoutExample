package com.nicco.motionlayoutexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import com.nicco.motionlayoutexamples.databinding.ActivityDemoComponentSwipeButtonBinding

class DemoComponentSwipeButton : AppCompatActivity() {

    private var swipeCompleted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_component_swipe_button)

        findViewById<SwipeButton>(R.id.swipeButton).setListener(object: SwipeButtonListener {
            override fun onSwipeCompleted() {
                swipeCompleted = true
            }

            override fun clicked() {
                Toast.makeText(this@DemoComponentSwipeButton, "swipeButton", Toast.LENGTH_LONG).show()
            }
        })

        findViewById<SwipeButton>(R.id.swipeButton2).setListener(object: SwipeButtonListener {
            override fun onSwipeCompleted() {
                swipeCompleted = true
            }

            override fun clicked() {
                Toast.makeText(this@DemoComponentSwipeButton, "swipeButton2", Toast.LENGTH_LONG).show()
            }
        })

        findViewById<SwipeButton>(R.id.swipeButton3).setListener(object: SwipeButtonListener {
            override fun onSwipeCompleted() {
                swipeCompleted = true
            }

            override fun clicked() {
                Toast.makeText(this@DemoComponentSwipeButton, "swipeButton3", Toast.LENGTH_LONG).show()
            }
        })
    }
}

interface SwipeButtonListener {
    fun onSwipeCompleted()
    fun clicked()
}