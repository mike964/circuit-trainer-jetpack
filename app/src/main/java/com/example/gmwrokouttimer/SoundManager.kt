package com.example.gmwrokouttimer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class SoundManager(context: Context) {
    private val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    private val soundPool = SoundPool.Builder()
        .setMaxStreams(10) // Max simultaneous streams
        .setAudioAttributes(audioAttributes)
        .build()

    // Store the loaded sound ID
    val soundClickId = soundPool.load(context, R.raw.click_3124, 1)

    fun playClickSound() {
        // play(soundID, leftVolume, rightVolume, priority, loop, rate)
        soundPool.play(soundClickId, 1f, 1f, 0, 0, 1f)
    }

    fun release() {
        soundPool.release()
    }
}