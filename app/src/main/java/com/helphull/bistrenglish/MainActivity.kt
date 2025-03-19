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
import com.google.gson.Gson
import com.helphull.bistrenglish.databinding.ActivityMainBinding
import com.helphull.bistrenglish.progress.Progress
import com.helphull.bistrenglish.progress.correctTheme
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
import com.helphull.bistrenglish.text.randomRuNounsFamilyA1
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
import java.io.File
import kotlin.random.Random

class MainActivity : AppCompatActivity(){
    private lateinit var ttsManager: TextToSpeechManager
    private lateinit var binding: ActivityMainBinding

    //Блок переменных по темам
    private var rusAppWords = russianVerbsA1
    private var enAppWords = englishVerbsA1
    private var randomRuAppWords = randomRussianVerbsA1
    //val progress = readJsonFile(this)
    /*private fun record()
    {File(this.filesDir,"progress").writeText(Gson().toJson(progress))}*/


    private fun btPaintGreen() {
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

    private fun addErrors() { // TODO Добавить сюда
        val progress = readJsonFile(this)
        errorEnWords.add(enAppWords[wordNumber])
        errorRuWords.add(rusAppWords[wordNumber])

        val errorArrayMapping = mapOf(
            11 to progress!!.a1T1errorArray,
            12 to progress.a1T2errorArray,
            13 to progress.a1T3errorArray,
            14 to progress.a1T4errorArray,
            15 to progress.a1T5errorArray,
            16 to progress.a1T6errorArray,
            17 to progress.a1T7errorArray,
            21 to progress.a2T1errorArray,
            22 to progress.a2T2errorArray,
            23 to progress.a2T3errorArray,
            24 to progress.a2T4errorArray,
            25 to progress.a2T5errorArray,
            26 to progress.a2T6errorArray
        )

        errorArrayMapping[correctTheme]?.add(wordNumber)
        updateJsonFile(this, progress)
    }

    private fun unclickable() {
        binding.btAnswer1.isClickable = false
        binding.btAnswer2.isClickable = false
        binding.btAnswer3.isClickable = false
        binding.btAnswer4.isClickable = false
        binding.btAnswer5.isClickable = false
        binding.btIdk.isClickable = false
    }

    private var wordNumber = -1

    private fun nextWord() {
        val progress = readJsonFile(this)!!
        if (wordNumber >= rusAppWords.size - 1) {
            // Показываем кнопку "В начало"
            binding.btGoToErrorActivity.visibility = View.VISIBLE
            return // Прекращаем обновление слов
        }

        wordNumber += 1
        when(correctTheme){ //TODO Добавить сюда
            11-> {progress.a1T1 = wordNumber}
            12-> {progress.a1T2 = wordNumber}
            13-> {progress.a1T3 = wordNumber}
            14-> {progress.a1T4 = wordNumber}
            15-> {progress.a1T5 = wordNumber}
            16-> {progress.a1T6 = wordNumber}
            17-> {progress.a1T7 = wordNumber}
            21-> {progress.a2T1 = wordNumber}
            22-> {progress.a2T2 = wordNumber}
            23-> {progress.a2T3 = wordNumber}
            24-> {progress.a2T4 = wordNumber}
            25-> {progress.a2T5 = wordNumber}
            26-> {progress.a2T6 = wordNumber}
        }
        updateJsonFile(this, progress)
        // Обновляем actualWords каждый раз при вызове nextWord()
        val actualWords = mutableListOf(
            rusAppWords[wordNumber],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)],
            randomRuAppWords[Random.nextInt(randomRuAppWords.size)]
        )

        actualWords.shuffle() // Раскомментируйте, если нужно перемешать слова

        fun updateUI() {
            // Обновляем текст кнопок и другие элементы UI
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
        }

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
        //Инициализация файла сохранений
        val progress = readJsonFile(this)!!

        // Распределение в зависимости от выбранной темы
        val themeToWordsMap = mapOf( // TODO Добавить сюда
            11 to Triple(russianVerbsA1, englishVerbsA1, randomRussianVerbsA1),
            12 to Triple(russianNounsWordAroundA1, englishNounsWordAroundA1, randomRussianNounsWorldA1),
            13 to Triple(ruNounsFamilyA1, enNounsFamilyA1, randomRuNounsFamilyA1),
            14 to Triple(ruNounsSocialA1, enNounsSocialA1, randomRuNounsSocialA1),
            15 to Triple(ruNounsTouristA1, enNounsTouristA1, randomRuNounsTouristA1),
            16 to Triple(ruAdjectivesA1, enAdjectivesA1, randomRuAdjectivesA1),
            17 to Triple(ruAdverbsA1, enAdverbsA1, randomRuAdverbsA1),
            21 to Triple(ruVerbsA2, enVerbsA2, randomRuVerbsA2),
            22 to Triple(ruNounsJobA2, enNounsJobA2, randomRuNounsJobA2),
            23 to Triple(ruNounsRestA2, enNounsRestA2, randomRuNounsRestA2),
            24 to Triple(ruNounsNatureA2, enNounsNatureA2, randomRuNounsNatureA2),
            25 to Triple(ruAdjectivesA2, enAdjectivesA2, randomRuAdjectivesA2),
            26 to Triple(ruAdverbsA2, enAdverbsA2, randomRuAdverbsA2))

        val mapCorrectWord = mapOf(
            11 to progress.a1T1-1,
            12 to progress.a1T2-1,
            13 to progress.a1T3-1,
            14 to progress.a1T4-1,
            15 to progress.a1T5-1,
            16 to progress.a1T6-1,
            17 to progress.a1T7-1,
            21 to progress.a2T1-1,
            22 to progress.a2T2-1,
            23 to progress.a2T3-1,
            24 to progress.a2T4-1,
            25 to progress.a2T5-1,
            26 to progress.a2T6-1,
        )
        val errorArrayMapping = mapOf(
            11 to progress.a1T1errorArray,
            12 to progress.a1T2errorArray,
            13 to progress.a1T3errorArray,
            14 to progress.a1T4errorArray,
            15 to progress.a1T5errorArray,
            16 to progress.a1T6errorArray,
            17 to progress.a1T7errorArray,
            21 to progress.a2T1errorArray,
            22 to progress.a2T2errorArray,
            23 to progress.a2T3errorArray,
            24 to progress.a2T4errorArray,
            25 to progress.a2T5errorArray,
            26 to progress.a2T6errorArray
        )

        val (rusWords, enWords, randomRuWords) = themeToWordsMap[correctTheme] ?: return
        rusAppWords = rusWords
        enAppWords = enWords
        randomRuAppWords = randomRuWords
        wordNumber = mapCorrectWord[correctTheme] ?: return
        errorEnWords = errorArrayMapping[correctTheme]!!.map { enAppWords[it] }.toMutableList()
        errorRuWords = errorArrayMapping[correctTheme]!!.map { rusAppWords[it] }.toMutableList()





        // Первая инициализация
        /*CoroutineScope(Dispatchers.Main).launch {
            delay(10)*/ // Задержка для инициализации TTS(если на трубке будут проблемы, раскомментировать)
            nextWord()//}
        binding.btPlayText.setOnClickListener {
            ttsManager.speak(enAppWords[wordNumber]) }

        binding.btGoToErrorActivity.setOnClickListener {//TODO Добавить сюда
            @Suppress("NAME_SHADOWING") val progress = readJsonFile(this)!!
            @Suppress("NAME_SHADOWING") val mapCorrectWord = mapOf(
                11 to Progress::a1T1,
                12 to Progress::a1T2,
                13 to Progress::a1T3,
                14 to Progress::a1T4,
                15 to Progress::a1T5,
                16 to Progress::a1T6,
                17 to Progress::a1T7,
                21 to Progress::a2T1,
                22 to Progress::a2T2,
                23 to Progress::a2T3,
                24 to Progress::a2T4,
                25 to Progress::a2T5,
                26 to Progress::a2T6
            )
            @Suppress("NAME_SHADOWING") val errorArrayMapping = mapOf(
                11 to Progress::a1T1errorArray,
                12 to Progress::a1T2errorArray,
                13 to Progress::a1T3errorArray,
                14 to Progress::a1T4errorArray,
                15 to Progress::a1T5errorArray,
                16 to Progress::a1T6errorArray,
                17 to Progress::a1T7errorArray,
                21 to Progress::a2T1errorArray,
                22 to Progress::a2T2errorArray,
                23 to Progress::a2T3errorArray,
                24 to Progress::a2T4errorArray,
                25 to Progress::a2T5errorArray,
                26 to Progress::a2T6errorArray
            )

            val conditionMap = mapOf(
                11 to Progress::a1T1condition,
                12 to Progress::a1T2condition,
                13 to Progress::a1T3condition,
                14 to Progress::a1T4condition,
                15 to Progress::a1T5condition,
                16 to Progress::a1T6condition,
                17 to Progress::a1T7condition,
                21 to Progress::a2T1condition,
                22 to Progress::a2T2condition,
                23 to Progress::a2T3condition,
                24 to Progress::a2T4condition,
                25 to Progress::a2T5condition,
                26 to Progress::a2T6condition,
            )

            //TODO: Доделать обработчик решения без ошибок(A1 & A2 есть)
            if (errorEnWords.size == 0) {
                conditionMap[correctTheme]?.set(progress, 0)
                mapCorrectWord[correctTheme]?.set(progress, 0)
                errorArrayMapping[correctTheme]?.get(progress)?.clear()
                val updateJson = Gson().toJson(progress)
                File(this.filesDir,"progress").writeText(updateJson)

                Toast.makeText(this, "Ошибок нет!", Toast.LENGTH_SHORT).show()
                Thread.sleep(delayInApp)

                val intentChooseThemeActivity = Intent(this, ChooseThemeActivity::class.java)
                startActivity(intentChooseThemeActivity)
                finish()
            }
            else{
                conditionMap[correctTheme]?.set(progress, 1)
                updateJsonFile(this, progress)
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



