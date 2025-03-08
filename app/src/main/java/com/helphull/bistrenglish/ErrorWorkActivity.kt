package com.helphull.bistrenglish

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.helphull.bistrenglish.databinding.ActivityErrorWorkBinding
import com.helphull.bistrenglish.text.englishWords
import com.helphull.bistrenglish.text.errorEnWords
import com.helphull.bistrenglish.text.errorRuWords
import com.helphull.bistrenglish.text.russianVerbs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ErrorWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityErrorWorkBinding

    fun unclickable() {
        binding.btAnswer1Error.isClickable = false
        binding.btAnswer2Error.isClickable = false
        binding.btAnswer3Error.isClickable = false
        binding.btAnswer4Error.isClickable = false
        binding.btAnswer5Error.isClickable = false
    }

    var wordNumber = -1

    fun removeWord() {
        enWordsToRemove.add(errorEnWords[wordNumber])
        ruWordsToRemove.add(errorRuWords[wordNumber])
    }
    val enWordsToRemove = mutableListOf<String>()
    val ruWordsToRemove = mutableListOf<String>()

    fun nextWord() {
        wordNumber += 1
        if (wordNumber >= com.helphull.bistrenglish.text.errorRuWords.size) {
            // Показываем кнопку "В начало"
            binding.btIdkError.visibility = View.GONE
            binding.btRestartError.visibility = View.VISIBLE
            return // Прекращаем обновление слов
        }

        // Обновляем actualWords каждый раз при вызове nextWord()
        val actualWords = mutableListOf(
            com.helphull.bistrenglish.text.errorRuWords[wordNumber],
            com.helphull.bistrenglish.text.rundomRussianVrerbs[Random.nextInt(101)],
            com.helphull.bistrenglish.text.rundomRussianVrerbs[Random.nextInt(101)],
            com.helphull.bistrenglish.text.rundomRussianVrerbs[Random.nextInt(101)],
            com.helphull.bistrenglish.text.rundomRussianVrerbs[Random.nextInt(101)]
        )

        actualWords.shuffle() // Раскомментируйте, если нужно перемешать слова

        // Обновляем текст кнопок
        binding.tvCurrentWordError.text = com.helphull.bistrenglish.text.errorEnWords[wordNumber]
        binding.btAnswer1Error.text = actualWords[0]
        binding.btAnswer2Error.text = actualWords[1]
        binding.btAnswer3Error.text = actualWords[2]
        binding.btAnswer4Error.text = actualWords[3]
        binding.btAnswer5Error.text = actualWords[4]

        // Делаем кнопки кликабельными
        binding.btAnswer1Error.isClickable = true
        binding.btAnswer2Error.isClickable = true
        binding.btAnswer3Error.isClickable = true
        binding.btAnswer4Error.isClickable = true
        binding.btAnswer5Error.isClickable = true

        // Сбрасываем цвет кнопок
        binding.btAnswer1Error.setBackgroundColor(Color.GRAY)
        binding.btAnswer2Error.setBackgroundColor(Color.GRAY)
        binding.btAnswer3Error.setBackgroundColor(Color.GRAY)
        binding.btAnswer4Error.setBackgroundColor(Color.GRAY)
        binding.btAnswer5Error.setBackgroundColor(Color.GRAY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityErrorWorkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nextWord()
        binding.btRestartError.setOnClickListener {
            errorEnWords.removeAll(enWordsToRemove)
            errorRuWords.removeAll(ruWordsToRemove)
            recreate() // Закрываем текущую Activity
        }
        binding.btIdkError.setOnClickListener {
            nextWord()
        }
        binding.btAnswer1Error.setOnClickListener {
            if (binding.btAnswer1Error.text == com.helphull.bistrenglish.text.errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer1Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {

                unclickable()
                binding.btAnswer1Error.setBackgroundColor(Color.RED)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer2Error.setOnClickListener {
            if (binding.btAnswer2Error.text == com.helphull.bistrenglish.text.errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer2Error.setBackgroundColor(Color.GREEN)

                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {

                unclickable()
                binding.btAnswer2Error.setBackgroundColor(Color.RED)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer3Error.setOnClickListener {

            if (binding.btAnswer3Error.text == com.helphull.bistrenglish.text.errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer3Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer3Error.setBackgroundColor(Color.RED)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer4Error.setOnClickListener {


            if (binding.btAnswer4Error.text == com.helphull.bistrenglish.text.errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer4Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer4Error.setBackgroundColor(Color.RED)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer5Error.setOnClickListener {
            if (binding.btAnswer5Error.text == com.helphull.bistrenglish.text.errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer5Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer5Error.setBackgroundColor(Color.RED)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1200) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
    }
}