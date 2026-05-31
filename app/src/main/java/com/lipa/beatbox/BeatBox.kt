package com.lipa.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {
    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()
    var rate: Float = 1.0f
    init {
        sounds = loadSounds()
    }

    fun play(sound: Sound) {
        val id = sound.soundId ?: return
        soundPool.play(id, 1.0f, 1.0f, 1, 0, rate)
    }

    fun release() {
        soundPool.release()
    }

    private fun loadSounds(): List<Sound> {

        val soundNames: Array<String>

        try {
            soundNames =
                assets.list(SOUNDS_FOLDER)!!
            //Log.d(TAG, "Found ${soundNames.size} sounds")
            //return soundNames.asList()
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath =
                "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            //sounds.add(sound)
            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
        return sounds
    }
    private fun load(sound: Sound) {
        val afd: AssetFileDescriptor =
            assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }
}
