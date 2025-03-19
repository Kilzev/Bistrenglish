import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class TextToSpeechManager(private val context: Context) {

    private var tts: TextToSpeech? = null
    private var ttsEnabled = false

    init {
        initTTS()
    }

    private fun initTTS() {
        tts = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status == TextToSpeech.SUCCESS) {
                    val locale = Locale.getDefault()
                    if (tts?.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
                        tts?.setLanguage(locale)
                    } else {
                        tts?.setLanguage(Locale.US)
                    }
                    tts?.setPitch(1.0f) // Настройка тона
                    tts?.setSpeechRate(0.5f) // Настройка скорости речи
                    ttsEnabled = true
                } else {
                    Log.e("TTS", "Ошибка инициализации")
                    ttsEnabled = false
                }
            }
        })
    }

    fun speak(text: String) {
        if (!ttsEnabled) return

        speakGreater21(text)
    }

    @Suppress("deprecation")
    private fun speakUnder20(text: String) {
        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "MessageId"
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, map)
    }

    @TargetApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private fun speakGreater21(text: String) {
        val utteranceId = this.hashCode().toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}