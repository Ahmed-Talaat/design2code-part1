package com.goodpatch.design2code_part1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val STATE_SMALL = intArrayOf(
            R.attr.state_small, -R.attr.state_medium, -R.attr.state_large
    )

    private val STATE_MEDIUM = intArrayOf(
            -R.attr.state_small, R.attr.state_medium, -R.attr.state_large
    )

    private val STATE_LARGE = intArrayOf(
            -R.attr.state_small, -R.attr.state_medium, R.attr.state_large
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSeekBar()
    }

    private fun setUpSeekBar() {
        with(seekBar) {
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    handleCupAnimationState(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
        }
    }

    /**
     * divide the SeekBar to 3 sections and change the states based on progress
     * @param progress seekbar progress
     */
    private fun handleCupAnimationState(progress: Int) {
        val max = seekBar.max

        val cupType = when (progress) {
            in 0..max / 3 -> STATE_SMALL
            in max / 3..max / 2 -> STATE_MEDIUM
            in max / 2..max -> STATE_LARGE
            else -> throw IllegalStateException()
        }

        imageView.setImageState(cupType, true)
    }
}
