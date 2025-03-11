package com.helphull.bistrenglish

import TextToSpeechManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.helphull.bistrenglish.databinding.ActivityMainBinding
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
import com.helphull.bistrenglish.text.familyWords
import com.helphull.bistrenglish.text.randomRuAdjectivesA1
import com.helphull.bistrenglish.text.randomRuAdjectivesA2
import com.helphull.bistrenglish.text.randomRuAdverbsA1
import com.helphull.bistrenglish.text.randomRuAdverbsA2
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

class MainActivity : AppCompatActivity(){
    private lateinit var ttsManager: TextToSpeechManager
    private lateinit var binding: ActivityMainBinding

    //Блок переменных по темам
    private var rusAppWords = russianVerbsA1
    private var enAppWords = englishVerbsA1
    private var randomRuAppWords = randomRussianVerbsA1
    

    fun btPaintGreen() {
        if (binding.btAnswer1.text == rusAppWords[wordNumber]) {
            binding.btAnswer1.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer2.text == rusAppWords[wordNumber]) {
            binding.btAnswer2.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer3.text == rusAppWords[wordNumber]) {
            binding.btAnswer3.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer4.text == rusAppWords[wordNumber]) {
            binding.btAnswer4.setBackgroundColor(Color.GREEN)
        } else if (binding.btAnswer5.text == rusAppWords[wordNumber]) {
            binding.btAnswer5.setBackgroundColor(Color.GREEN)
        }
    }

    fun addErrors() {
        errorEnWords.add(enAppWords[wordNumber])
        errorRuWords.add(rusAppWords[wordNumber])
    }

    fun unclickable() {
        binding.btAnswer1.isClickable = false
        binding.btAnswer2.isClickable = false
        binding.btAnswer3.isClickable = false
        binding.btAnswer4.isClickable = false
        binding.btAnswer5.isClickable = false
        binding.btIdk.isClickable = false
    }

    var wordNumber = -1

    fun nextWord() {
        if (wordNumber >= rusAppWords.size - 1) {
            // Показываем кнопку "В начало"
            binding.btRestart.visibility = View.VISIBLE
            return // Прекращаем обновление слов
        }

        wordNumber += 1

        // Обновляем actualWords каждый раз при вызове nextWord()
        val actualWords = mutableListOf(
            rusAppWords[wordNumber],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)]
        )

        actualWords.shuffle() // Раскомментируйте, если нужно перемешать слова

        // Обновляем текст кнопок
        binding.tvCurrentWord.text = enAppWords[wordNumber]
        binding.btAnswer1.text = actualWords[0]
        binding.btAnswer2.text = actualWords[1]
        binding.btAnswer3.text = actualWords[2]
        binding.btAnswer4.text = actualWords[3]
        binding.btAnswer5.text = actualWords[4]

        // Делаем кнопки кликабельными
        binding.btAnswer1.isClickable = true
        binding.btAnswer2.isClickable = true
        binding.btAnswer3.isClickable = true
        binding.btAnswer4.isClickable = true
        binding.btAnswer5.isClickable = true
        binding.btIdk.isClickable = true

        // Сбрасываем цвет кнопок
        binding.btAnswer1.setBackgroundColor(Color.GRAY)
        binding.btAnswer2.setBackgroundColor(Color.GRAY)
        binding.btAnswer3.setBackgroundColor(Color.GRAY)
        binding.btAnswer4.setBackgroundColor(Color.GRAY)
        binding.btAnswer5.setBackgroundColor(Color.GRAY)

        // TTS на запуск
        ttsManager.speak(enAppWords[wordNumber])
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ttsManager = TextToSpeechManager(this)

        // Распределение в зависимости от выбранной темы
        when (choosenLvl){
            1 -> {when (choosenTheme) {
                1 -> {
                    rusAppWords = russianVerbsA1
                    enAppWords = englishVerbsA1
                    randomRuAppWords = randomRussianVerbsA1
                }
                2 -> {
                    rusAppWords = russianNounsWordAroundA1
                    enAppWords = englishNounsWordAroundA1
                    randomRuAppWords = randomRussianNounsWorldA1
                }
                3 -> {
                    rusAppWords = ruNounsFamilyA1
                    enAppWords = enNounsFamilyA1
                    randomRuAppWords = familyWords
                }
                4 -> {
                    rusAppWords = ruNounsSocialA1
                    enAppWords = enNounsSocialA1
                    randomRuAppWords = randomRuNounsSocialA1
                }
                5 -> {
                    rusAppWords = ruNounsTouristA1
                    enAppWords = enNounsTouristA1
                    randomRuAppWords = randomRuNounsTouristA1
                }
                6 -> {
                    rusAppWords = ruAdjectivesA1
                    enAppWords = enAdjectivesA1
                    randomRuAppWords = randomRuAdjectivesA1
                }
                7 -> {
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                }
            }} //done
            2 -> {when (choosenTheme) {
                1 -> {
                    rusAppWords = ruVerbsA2
                    enAppWords = enVerbsA2
                    randomRuAppWords = randomRuVerbsA2
                }
                2 -> {
                    rusAppWords = ruNounsJobA2
                    enAppWords = enNounsJobA2
                    randomRuAppWords = randomRuNounsJobA2
                }
                3 -> {
                    rusAppWords = ruNounsRestA2
                    enAppWords = enNounsRestA2
                    randomRuAppWords = randomRuNounsRestA2
                }
                4 -> {
                    rusAppWords = ruNounsNatureA2
                    enAppWords = enNounsNatureA2
                    randomRuAppWords = randomRuNounsNatureA2
                }
                5 -> {
                    rusAppWords = ruAdjectivesA2
                    enAppWords = enAdjectivesA2
                    randomRuAppWords = randomRuAdjectivesA2
                }
                6 -> {
                    rusAppWords = ruAdverbsA2
                    enAppWords = enAdverbsA2
                    randomRuAppWords = randomRuAdverbsA2
                }
                7 -> {
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                }
            }} //done
            3 -> {when (choosenTheme) {
                1 -> {
                    rusAppWords = russianVerbsA1
                    enAppWords = englishVerbsA1
                    randomRuAppWords = randomRussianVerbsA1
                }
                2 -> {
                    rusAppWords = russianNounsWordAroundA1
                    enAppWords = englishNounsWordAroundA1
                    randomRuAppWords = randomRussianNounsWorldA1
                }
                3 -> {
                    rusAppWords = ruNounsFamilyA1
                    enAppWords = enNounsFamilyA1
                    randomRuAppWords = familyWords
                }
                4 -> {
                    rusAppWords = ruNounsSocialA1
                    enAppWords = enNounsSocialA1
                    randomRuAppWords = randomRuNounsSocialA1
                }
                5 -> {
                    rusAppWords = ruNounsTouristA1
                    enAppWords = enNounsTouristA1
                    randomRuAppWords = randomRuNounsTouristA1
                }
                6 -> {
                    rusAppWords = ruAdjectivesA1
                    enAppWords = enAdjectivesA1
                    randomRuAppWords = randomRuAdjectivesA1
                }
                7 -> {
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                }
            }}
            4 -> {when (choosenTheme) {
                1 -> {
                    rusAppWords = russianVerbsA1
                    enAppWords = englishVerbsA1
                    randomRuAppWords = randomRussianVerbsA1
                }
                2 -> {
                    rusAppWords = russianNounsWordAroundA1
                    enAppWords = englishNounsWordAroundA1
                    randomRuAppWords = randomRussianNounsWorldA1
                }
                3 -> {
                    rusAppWords = ruNounsFamilyA1
                    enAppWords = enNounsFamilyA1
                    randomRuAppWords = familyWords
                }
                4 -> {
                    rusAppWords = ruNounsSocialA1
                    enAppWords = enNounsSocialA1
                    randomRuAppWords = randomRuNounsSocialA1
                }
                5 -> {
                    rusAppWords = ruNounsTouristA1
                    enAppWords = enNounsTouristA1
                    randomRuAppWords = randomRuNounsTouristA1
                }
                6 -> {
                    rusAppWords = ruAdjectivesA1
                    enAppWords = enAdjectivesA1
                    randomRuAppWords = randomRuAdjectivesA1
                }
                7 -> {
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                }
            }}
            5 -> {when (choosenTheme) {
                1 -> {
                    rusAppWords = russianVerbsA1
                    enAppWords = englishVerbsA1
                    randomRuAppWords = randomRussianVerbsA1
                }
                2 -> {
                    rusAppWords = russianNounsWordAroundA1
                    enAppWords = englishNounsWordAroundA1
                    randomRuAppWords = randomRussianNounsWorldA1
                }
                3 -> {
                    rusAppWords = ruNounsFamilyA1
                    enAppWords = enNounsFamilyA1
                    randomRuAppWords = familyWords
                }
                4 -> {
                    rusAppWords = ruNounsSocialA1
                    enAppWords = enNounsSocialA1
                    randomRuAppWords = randomRuNounsSocialA1
                }
                5 -> {
                    rusAppWords = ruNounsTouristA1
                    enAppWords = enNounsTouristA1
                    randomRuAppWords = randomRuNounsTouristA1
                }
                6 -> {
                    rusAppWords = ruAdjectivesA1
                    enAppWords = enAdjectivesA1
                    randomRuAppWords = randomRuAdjectivesA1
                }
                7 -> {
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                }
            }}
            6 -> {when (choosenTheme) {
                1 -> {
                    rusAppWords = russianVerbsA1
                    enAppWords = englishVerbsA1
                    randomRuAppWords = randomRussianVerbsA1
                }
                2 -> {
                    rusAppWords = russianNounsWordAroundA1
                    enAppWords = englishNounsWordAroundA1
                    randomRuAppWords = randomRussianNounsWorldA1
                }
                3 -> {
                    rusAppWords = ruNounsFamilyA1
                    enAppWords = enNounsFamilyA1
                    randomRuAppWords = familyWords
                }
                4 -> {
                    rusAppWords = ruNounsSocialA1
                    enAppWords = enNounsSocialA1
                    randomRuAppWords = randomRuNounsSocialA1
                }
                5 -> {
                    rusAppWords = ruNounsTouristA1
                    enAppWords = enNounsTouristA1
                    randomRuAppWords = randomRuNounsTouristA1
                }
                6 -> {
                    rusAppWords = ruAdjectivesA1
                    enAppWords = enAdjectivesA1
                    randomRuAppWords = randomRuAdjectivesA1
                }
                7 -> {
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                }
            }}
        }



        // Первая инициализация
        CoroutineScope(Dispatchers.Main).launch {
            delay(500) // Задержка для инициализации TTS
            nextWord()}

        binding.btPlayText.setOnClickListener {
            ttsManager.speak(enAppWords[wordNumber]) }

        binding.btRestart.setOnClickListener {
            if (errorEnWords.size == 0) {

                Toast.makeText(this, "Ошибок нет!", Toast.LENGTH_SHORT).show()
                Thread.sleep(delayInApp)
                val intentChooseThemeActivity = Intent(this, ChooseThemeActivity::class.java)
                startActivity(intentChooseThemeActivity)
                finish()
            }
            else{
            val intent = Intent(this, ErrorWorkActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() }// Закрываем текущую Activity
        }



        binding.btIdk.setOnClickListener {
            addErrors()
            btPaintGreen()
            unclickable()
            CoroutineScope(Dispatchers.Main).launch {
                delay(delayInApp) // Задержка 1.2 секунды
                nextWord()
            }
        }


        binding.btAnswer1.setOnClickListener {
            if (binding.btAnswer1.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer1.setBackgroundColor(Color.GREEN)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer1.setBackgroundColor(Color.RED)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer2.setOnClickListener {
            if (binding.btAnswer2.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer2.setBackgroundColor(Color.GREEN)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer2.setBackgroundColor(Color.RED)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer3.setOnClickListener {

            if (binding.btAnswer3.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer3.setBackgroundColor(Color.GREEN)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer3.setBackgroundColor(Color.RED)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer4.setOnClickListener {


            if (binding.btAnswer4.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer4.setBackgroundColor(Color.GREEN)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer4.setBackgroundColor(Color.RED)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer5.setOnClickListener {
            if (binding.btAnswer5.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer5.setBackgroundColor(Color.GREEN)
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord()  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer5.setBackgroundColor(Color.RED)
                btPaintGreen()
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



