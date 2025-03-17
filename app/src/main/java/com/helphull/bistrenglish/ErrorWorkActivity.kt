package com.helphull.bistrenglish

import TextToSpeechManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.helphull.bistrenglish.databinding.ActivityErrorWorkBinding
import com.helphull.bistrenglish.progress.readJsonFile
import com.helphull.bistrenglish.progress.updateJsonFile
import com.helphull.bistrenglish.text.enAdjectivesA1
import com.helphull.bistrenglish.text.enAdjectivesA2
import com.helphull.bistrenglish.text.enAdverbsA1
import com.helphull.bistrenglish.text.enAdverbsA2
import com.helphull.bistrenglish.text.enNounsFamilyA1
import com.helphull.bistrenglish.text.enNounsJobA2
import com.helphull.bistrenglish.text.enNounsNatureA2
import com.helphull.bistrenglish.text.enNounsRestA2
import com.helphull.bistrenglish.text.enNounsSocialA1
import com.helphull.bistrenglish.text.enNounsTouristA1
import com.helphull.bistrenglish.text.enVerbsA2
import com.helphull.bistrenglish.text.englishNounsWordAroundA1
import com.helphull.bistrenglish.text.englishVerbsA1
import com.helphull.bistrenglish.text.errorEnWords
import com.helphull.bistrenglish.text.errorRuWords
import com.helphull.bistrenglish.text.randomRuAdjectivesA1
import com.helphull.bistrenglish.text.randomRuAdjectivesA2
import com.helphull.bistrenglish.text.randomRuAdverbsA1
import com.helphull.bistrenglish.text.randomRuAdverbsA2
import com.helphull.bistrenglish.text.randomRuNounsFamilyA1
import com.helphull.bistrenglish.text.randomRuNounsJobA2
import com.helphull.bistrenglish.text.randomRuNounsNatureA2
import com.helphull.bistrenglish.text.randomRuNounsRestA2
import com.helphull.bistrenglish.text.randomRuNounsSocialA1
import com.helphull.bistrenglish.text.randomRuNounsTouristA1
import com.helphull.bistrenglish.text.randomRuVerbsA2
import com.helphull.bistrenglish.text.randomRussianNounsWorldA1
import com.helphull.bistrenglish.text.randomRussianVerbsA1
import com.helphull.bistrenglish.text.ruAdjectivesA1
import com.helphull.bistrenglish.text.ruAdjectivesA2
import com.helphull.bistrenglish.text.ruAdverbsA1
import com.helphull.bistrenglish.text.ruAdverbsA2
import com.helphull.bistrenglish.text.ruNounsFamilyA1
import com.helphull.bistrenglish.text.ruNounsJobA2
import com.helphull.bistrenglish.text.ruNounsNatureA2
import com.helphull.bistrenglish.text.ruNounsRestA2
import com.helphull.bistrenglish.text.ruNounsSocialA1
import com.helphull.bistrenglish.text.ruNounsTouristA1
import com.helphull.bistrenglish.text.ruVerbsA2
import com.helphull.bistrenglish.text.russianNounsWordAroundA1
import com.helphull.bistrenglish.text.russianVerbsA1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ErrorWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityErrorWorkBinding
    private lateinit var ttsManager: TextToSpeechManager
    private fun unclickable() {
        binding.btAnswer1Error.isClickable = false
        binding.btAnswer2Error.isClickable = false
        binding.btAnswer3Error.isClickable = false
        binding.btAnswer4Error.isClickable = false
        binding.btAnswer5Error.isClickable = false
    }

    private var wordNumber = -1

    private fun paintGreen() {
        if (binding.btAnswer1Error.text == errorRuWords[wordNumber]) {
            binding.btAnswer1Error.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer2Error.text == errorRuWords[wordNumber]) {
            binding.btAnswer2Error.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer3Error.text == errorRuWords[wordNumber]) {
            binding.btAnswer3Error.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer4Error.text == errorRuWords[wordNumber]) {
            binding.btAnswer4Error.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer5Error.text == errorRuWords[wordNumber]) {
            binding.btAnswer5Error.setBackgroundColor(Color.GREEN)
        }
    }
    
    private fun removeWord() {
        enWordsToRemove.add(errorEnWords[wordNumber])
        ruWordsToRemove.add(errorRuWords[wordNumber])
        indexOfSolvedErrors.add(wordNumber)

    }
    private val enWordsToRemove = mutableListOf<String>()
    private val ruWordsToRemove = mutableListOf<String>()
    private var randomRuErrorWords = listOf<String>()
    private val indexOfSolvedErrors = mutableListOf<Int>()


    private fun nextWord() {
        wordNumber += 1
        if (wordNumber == errorRuWords.size) {
            // Показываем кнопку "В начало"
            binding.btIdkError.visibility = View.GONE
            binding.btRestartError.visibility = View.VISIBLE
            return // Прекращаем обновление слов
        }

        // Обновляем actualWords каждый раз при вызове nextWord()
        val actualWords = mutableListOf(
            //TODO: Заменить слова из первой темы, на рандомные из каждой
            errorRuWords[wordNumber],
            randomRuErrorWords[Random.nextInt(randomRuErrorWords.size)],
            randomRuErrorWords[Random.nextInt(randomRuErrorWords.size)],
            randomRuErrorWords[Random.nextInt(randomRuErrorWords.size)],
            randomRuErrorWords[Random.nextInt(randomRuErrorWords.size)]
        )

        actualWords.shuffle() // Раскомментируйте, если нужно перемешать слова

        // Обновляем текст кнопок
        binding.tvCurrentWordError.text = errorEnWords[wordNumber]
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
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityErrorWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val progress = readJsonFile(this)!!
        when (choosenLvl) { //TODO: Доделать для остальных тем загрузку ошибок
            1 -> when (choosenTheme) {
                1 -> {
                    errorEnWords =
                        progress.a1T1errorArray.map { englishVerbsA1[it] }.toMutableList()
                    errorRuWords =
                        progress.a1T1errorArray.map { russianVerbsA1[it] }.toMutableList()
                    randomRuErrorWords = randomRussianVerbsA1

                }

                2 -> {
                    errorEnWords =
                        progress.a1T2errorArray.map { englishNounsWordAroundA1[it] }.toMutableList()
                    errorRuWords =
                        progress.a1T2errorArray.map { russianNounsWordAroundA1[it] }.toMutableList()
                    randomRuErrorWords = randomRussianNounsWorldA1
                }

                3 -> {
                    errorEnWords =
                        progress.a1T3errorArray.map { enNounsFamilyA1[it] }.toMutableList()
                    errorRuWords =
                        progress.a1T3errorArray.map { ruNounsFamilyA1[it] }.toMutableList()
                    randomRuErrorWords = randomRuNounsFamilyA1
                }

                4 -> {
                    errorEnWords =
                        progress.a1T4errorArray.map { enNounsSocialA1[it] }.toMutableList()
                    errorRuWords =
                        progress.a1T4errorArray.map { ruNounsSocialA1[it] }.toMutableList()
                    randomRuErrorWords = randomRuNounsSocialA1
                }

                5 -> {
                    errorEnWords =
                        progress.a1T5errorArray.map { enNounsTouristA1[it] }.toMutableList()
                    errorRuWords =
                        progress.a1T5errorArray.map { ruNounsTouristA1[it] }.toMutableList()
                    randomRuErrorWords = randomRuNounsTouristA1
                }

                6 -> {
                    errorEnWords =
                        progress.a1T6errorArray.map { enAdjectivesA1[it] }.toMutableList()
                    errorRuWords =
                        progress.a1T6errorArray.map { ruAdjectivesA1[it] }.toMutableList()
                    randomRuErrorWords = randomRuAdjectivesA1
                }

                7 -> {
                    errorEnWords = progress.a1T7errorArray.map { enAdverbsA1[it] }.toMutableList()
                    errorRuWords = progress.a1T7errorArray.map { ruAdverbsA1[it] }.toMutableList()
                    randomRuErrorWords = randomRuAdverbsA1
                }
            }
            2 -> when (choosenTheme){
                1 -> {
                    errorEnWords =
                        progress.a2T1errorArray.map { enVerbsA2[it] }.toMutableList()
                    errorRuWords =
                        progress.a2T1errorArray.map { ruVerbsA2[it] }.toMutableList()
                    randomRuErrorWords = randomRuVerbsA2

                }

                2 -> {
                    errorEnWords =
                        progress.a2T2errorArray.map { enNounsJobA2[it] }.toMutableList()
                    errorRuWords =
                        progress.a2T2errorArray.map { ruNounsJobA2[it] }.toMutableList()
                    randomRuErrorWords = randomRuNounsJobA2
                }

                3 -> {
                    errorEnWords =
                        progress.a2T3errorArray.map { enNounsRestA2[it] }.toMutableList()
                    errorRuWords =
                        progress.a2T3errorArray.map { ruNounsRestA2[it] }.toMutableList()
                    randomRuErrorWords = randomRuNounsRestA2
                }

                4 -> {
                    errorEnWords =
                        progress.a2T4errorArray.map { enNounsNatureA2[it] }.toMutableList()
                    errorRuWords =
                        progress.a2T4errorArray.map { ruNounsNatureA2[it] }.toMutableList()
                    randomRuErrorWords = randomRuNounsNatureA2
                }

                5 -> {
                    errorEnWords =
                        progress.a2T5errorArray.map { enAdjectivesA2[it] }.toMutableList()
                    errorRuWords =
                        progress.a2T5errorArray.map { ruAdjectivesA2[it] }.toMutableList()
                    randomRuErrorWords = randomRuAdjectivesA2
                }

                6 -> {
                    errorEnWords =
                        progress.a2T6errorArray.map { enAdverbsA2[it] }.toMutableList()
                    errorRuWords =
                        progress.a2T6errorArray.map { ruAdverbsA2[it] }.toMutableList()
                    randomRuErrorWords = randomRuAdverbsA2
                }
            }
        }
        nextWord()
        ttsManager = TextToSpeechManager(this)
        binding.btPlayTextError.setOnClickListener { ttsManager.speak(errorEnWords[wordNumber]) }
        binding.btRestartError.setOnClickListener {
            errorEnWords.removeAll(enWordsToRemove)
            errorRuWords.removeAll(ruWordsToRemove)

            when(choosenLvl){ //TODO: Решиить вопрос с удалением прорешенных слов
                1 -> {when(choosenTheme){
                    1 -> {
                        indexOfSolvedErrors.sortedDescending().forEach{ index ->
                        progress.a1T1errorArray.removeAt(index)}
                        updateJsonFile(this, progress)
                    }}
                }}

            if (errorEnWords.size == 0){
                when(choosenLvl){
                    1 -> when(choosenTheme){
                        1->{progress.a1T1condition = 0
                        progress.a1T1errorArray.clear()
                        progress.a1T1 = 0
                        updateJsonFile(this,progress)
                        }
                    }
                }
                Toast.makeText(this, "Ошибки прорешаны!", Toast.LENGTH_SHORT).show()
                Thread.sleep(delayInApp)
                val intentChooseThemeActivity = Intent(this, ChooseThemeActivity::class.java)
                startActivity(intentChooseThemeActivity)
                finish()
            }
            recreate() // Закрываем текущую Activity
        }
        binding.btIdkError.setOnClickListener {
            nextWord()
        }
        binding.btAnswer1Error.setOnClickListener {
            if (binding.btAnswer1Error.text == errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer1Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer1Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer2Error.setOnClickListener {
            if (binding.btAnswer2Error.text == errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer2Error.setBackgroundColor(Color.GREEN)

                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer2Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer3Error.setOnClickListener {

            if (binding.btAnswer3Error.text == errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer3Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer3Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer4Error.setOnClickListener {


            if (binding.btAnswer4Error.text == errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer4Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer4Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer5Error.setOnClickListener {
            if (binding.btAnswer5Error.text == errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer5Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer5Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        ttsManager.shutdown()
    }
}