package com.lipa.beatbox

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

// Наследуемся от архитектурного androidx.lifecycle.ViewModel
class BeatBoxViewModel(assets: AssetManager) : ViewModel() {

    // BeatBox теперь надежно спрятан здесь
    val beatBox = BeatBox(assets)

    override fun onCleared() {
        super.onCleared()
        // Сюда переносим очистку ресурсов
        beatBox.release()
    }
}
