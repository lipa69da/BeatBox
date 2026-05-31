package com.lipa.beatbox

import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.Insets
import androidx.recyclerview.widget.GridLayoutManager
import com.lipa.beatbox.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lipa.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var beatBoxViewModel: BeatBoxViewModel
    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //beatBox = BeatBox(assets)
        //beatBox.loadSounds()

        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val factory = BeatBoxViewModelFactory(assets)
        beatBoxViewModel = ViewModelProvider(this, factory)[BeatBoxViewModel::class.java]

        beatBox = beatBoxViewModel.beatBox

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val seekBar: SeekBar = findViewById(R.id.speed_seek_bar)
        val textView: TextView = findViewById(R.id.speed_text_view)

        textView.text = getString(R.string.playback_speed, String.format("%.1fx", beatBox.rate))

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Вычисляем скорость: минимальная 0.5 + шаг (progress * 0.1)
                val currentRate = 0.5f + (progress * 0.1f)

                // Передаем значение в BeatBox
                beatBox.rate = currentRate

                // Форматируем число (например, 1.0) в строку "1.0x"
                //val speedString = String.format("%.1fx", currentRate)

                // Подставляем сформированную строку в наш ресурс из strings.xml
                //textView.text = getString(R.string.playback_speed, speedString)
                textView.text = getString(R.string.playback_speed, String.format("%.1fx", currentRate))

                // Обновляем текст на экране
                //textView.text = String.format("Скорость: %.1fx", currentRate)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    //override fun onDestroy() {
    //    super.onDestroy()
    //    beatBox.release()
    //}

    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }
    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                SoundHolder {
            val binding =
                DataBindingUtil.inflate<ListItemSoundBinding>(
                    layoutInflater,
                    R.layout.list_item_sound,
                    parent,
                    false
                )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder:
                                      SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount() = sounds.size
    }
}