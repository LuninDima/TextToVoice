package ru.moondi.texttovoice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ru.moondi.texttovoice.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityMainBinding
    private var tts: TextToSpeech? = null
    private var button: Button? = null
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        button = this.button
        textView = this.textView
        tts = TextToSpeech(this, this)
        button = binding.button
        textView = binding.textView
        button!!.isEnabled = false
        button!!.setOnClickListener{
            Speak()
        }
    }

    private fun Speak() {
       val text =textView?.text
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        Log.d("mylogs", "test")
        if(status == TextToSpeech.SUCCESS){
            var output = tts!!.setLanguage(Locale.getDefault())
            if(status == TextToSpeech.LANG_MISSING_DATA || output == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Not Supported", Toast.LENGTH_SHORT).show()
            } else{
                button!!.isEnabled = true
            }

        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts!= null){
            tts!!.stop()
        }
    }
}