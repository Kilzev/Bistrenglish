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

    private fun addErrors() {
        val progress = readJsonFile(this)
        errorEnWords.add(enAppWords[wordNumber])
        errorRuWords.add(rusAppWords[wordNumber])

        when (choosenLvl){
            1 -> {when (choosenTheme) {
                1 -> {
                    progress!!.a1T1errorArray.add(wordNumber)
                    updateJsonFile(this, progress)
                }
                2 -> {
                    progress!!.a1T2errorArray.add(wordNumber)
                    updateJsonFile(this, progress)
                }
                3 -> {
                    progress!!.a1T3errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                4 -> {
                    progress!!.a1T4errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                5 -> {
                    progress!!.a1T5errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                6 -> {
                    progress!!.a1T6errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                7 -> {
                    progress!!.a1T7errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
            }} //done
            2 -> {when (choosenTheme) {
                1 -> {
                    progress!!.a2T1errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                2 -> {
                    progress!!.a2T2errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                3 -> {
                    progress!!.a2T3errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                4 -> {
                    progress!!.a2T4errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                5 -> {
                    progress!!.a2T5errorArray.add(wordNumber)
                    updateJsonFile(this, progress)

                }
                6 -> {
                    progress!!.a2T6errorArray.add(wordNumber)
                    updateJsonFile(this, progress)
                }
            }}}
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
        when(correctTheme){
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
        //Инициализация файла сохранений
        val progress = readJsonFile(this)!!

        // Распределение в зависимости от выбранной темы
        when (choosenLvl){
            1 -> {when (choosenTheme) {
                1 -> {
                    correctTheme = 11
                    rusAppWords = russianVerbsA1
                    enAppWords = englishVerbsA1
                    randomRuAppWords = randomRussianVerbsA1
                    wordNumber = progress.a1T1-1
                    errorEnWords = progress.a1T1errorArray.map { englishVerbsA1[it]}.toMutableList()
                    errorRuWords = progress.a1T1errorArray.map { russianVerbsA1[it]}.toMutableList()
                }
                2 -> {
                    correctTheme = 12
                    rusAppWords = russianNounsWordAroundA1
                    enAppWords = englishNounsWordAroundA1
                    randomRuAppWords = randomRussianNounsWorldA1
                    wordNumber = progress.a1T2-1
                    errorEnWords = progress.a1T2errorArray.map { englishNounsWordAroundA1[it]}.toMutableList()
                    errorRuWords = progress.a1T2errorArray.map { russianNounsWordAroundA1[it]}.toMutableList()
                }
                3 -> {
                    correctTheme = 13
                    rusAppWords = ruNounsFamilyA1
                    enAppWords = enNounsFamilyA1
                    randomRuAppWords = randomRuNounsFamilyA1
                    wordNumber = progress.a1T3-1
                    errorEnWords = progress.a1T3errorArray.map { enNounsFamilyA1[it]}.toMutableList()
                    errorRuWords = progress.a1T3errorArray.map { ruNounsFamilyA1[it]}.toMutableList()
                }
                4 -> {
                    correctTheme = 14
                    rusAppWords = ruNounsSocialA1
                    enAppWords = enNounsSocialA1
                    randomRuAppWords = randomRuNounsSocialA1
                    wordNumber = progress.a1T4-1
                    errorEnWords = progress.a1T4errorArray.map { enNounsSocialA1[it]}.toMutableList()
                    errorRuWords = progress.a1T4errorArray.map { ruNounsSocialA1[it]}.toMutableList()
                }
                5 -> {
                    correctTheme = 15
                    rusAppWords = ruNounsTouristA1
                    enAppWords = enNounsTouristA1
                    randomRuAppWords = randomRuNounsTouristA1
                    wordNumber = progress.a1T5-1
                    errorEnWords = progress.a1T5errorArray.map { enNounsTouristA1[it]}.toMutableList()
                    errorRuWords = progress.a1T5errorArray.map { ruNounsTouristA1[it]}.toMutableList()
                }
                6 -> {
                    correctTheme = 16
                    rusAppWords = ruAdjectivesA1
                    enAppWords = enAdjectivesA1
                    randomRuAppWords = randomRuAdjectivesA1
                    wordNumber = progress.a1T6-1
                    errorEnWords = progress.a1T6errorArray.map { enAdjectivesA1[it]}.toMutableList()
                    errorRuWords = progress.a1T6errorArray.map { ruAdjectivesA1[it]}.toMutableList()
                }
                7 -> {
                    correctTheme = 17
                    rusAppWords = ruAdverbsA1
                    enAppWords = enAdverbsA1
                    randomRuAppWords = randomRuAdverbsA1
                    wordNumber = progress.a1T7-1
                    errorEnWords = progress.a1T7errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a1T7errorArray.map { ruAdverbsA1[it]}.toMutableList()
                }
            }} //done
            2 -> {when (choosenTheme) {
                1 -> {
                    correctTheme = 21
                    rusAppWords = ruVerbsA2
                    enAppWords = enVerbsA2
                    randomRuAppWords = randomRuVerbsA2
                    wordNumber = progress.a2T1-1
                    errorEnWords = progress.a2T1errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a2T1errorArray.map { ruAdverbsA1[it]}.toMutableList()
                }
                2 -> {
                    correctTheme = 22
                    rusAppWords = ruNounsJobA2
                    enAppWords = enNounsJobA2
                    randomRuAppWords = randomRuNounsJobA2
                    wordNumber = progress.a2T2-1
                    errorEnWords = progress.a2T2errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a2T2errorArray.map { ruAdverbsA1[it]}.toMutableList()
                }
                3 -> {
                    correctTheme = 23
                    rusAppWords = ruNounsRestA2
                    enAppWords = enNounsRestA2
                    randomRuAppWords = randomRuNounsRestA2
                    wordNumber = progress.a2T3-1
                    errorEnWords = progress.a2T3errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a2T3errorArray.map { ruAdverbsA1[it]}.toMutableList()
                }
                4 -> {
                    correctTheme = 24
                    rusAppWords = ruNounsNatureA2
                    enAppWords = enNounsNatureA2
                    randomRuAppWords = randomRuNounsNatureA2
                    wordNumber = progress.a2T4-1
                    errorEnWords = progress.a2T4errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a2T4errorArray.map { ruAdverbsA1[it]}.toMutableList()
                }
                5 -> {
                    correctTheme = 25
                    rusAppWords = ruAdjectivesA2
                    enAppWords = enAdjectivesA2
                    randomRuAppWords = randomRuAdjectivesA2
                    wordNumber = progress.a2T5-1
                    errorEnWords = progress.a2T5errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a2T5errorArray.map { ruAdverbsA1[it]}.toMutableList()
                }
                6 -> {
                    correctTheme = 26
                    rusAppWords = ruAdverbsA2
                    enAppWords = enAdverbsA2
                    randomRuAppWords = randomRuAdverbsA2
                    wordNumber = progress.a2T6-1
                    errorEnWords = progress.a2T6errorArray.map { enAdverbsA1[it]}.toMutableList()
                    errorRuWords = progress.a2T6errorArray.map { ruAdverbsA1[it]}.toMutableList()
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
                    randomRuAppWords = randomRuNounsFamilyA1
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
                    randomRuAppWords = randomRuNounsFamilyA1
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
                    randomRuAppWords = randomRuNounsFamilyA1
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
                    randomRuAppWords = randomRuNounsFamilyA1
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
        /*CoroutineScope(Dispatchers.Main).launch {
            delay(10)*/ // Задержка для инициализации TTS(если на трубке будут проблемы, раскомментировать)
            nextWord()//}
        binding.btPlayText.setOnClickListener {
            ttsManager.speak(enAppWords[wordNumber]) }

        binding.btGoToErrorActivity.setOnClickListener {
            val progress = readJsonFile(this)!!
            //TODO: Доделать обработчик решения без ошибок(A1 & A2 есть)
            if (errorEnWords.size == 0) {
                when(choosenLvl){ //Обнуление в случае решения без ошибок
                    1 -> {when(choosenTheme){
                        1 -> {progress.a1T1condition = 0
                        progress.a1T1 = 0
                        progress.a1T1errorArray.clear()
                        val updateJson = Gson().toJson(progress)
                        File(this.filesDir,"progress").writeText(updateJson)}

                        2 ->{progress.a1T2condition = 0
                            progress.a1T2 = 0
                            progress.a1T2errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        3 ->{progress.a1T3condition = 0
                            progress.a1T3 = 0
                            progress.a1T3errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        4 ->{progress.a1T4condition = 5
                            progress.a1T4 = 0
                            progress.a1T4errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        5 ->{progress.a1T5condition = 0
                            progress.a1T5 = 0
                            progress.a1T5errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        6 ->{progress.a1T6condition = 0
                            progress.a1T6 = 0
                            progress.a1T6errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        7 ->{progress.a1T7condition = 0
                            progress.a1T7 = 0
                            progress.a1T7errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}


                    }}
                    2 -> {when(choosenTheme){
                        1 -> {progress.a2T1condition = 0
                            progress.a2T1 = 0
                            progress.a2T1errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        2 ->{progress.a2T2condition = 0
                            progress.a2T2 = 0
                            progress.a2T2errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        3 ->{progress.a2T3condition = 0
                            progress.a2T3 = 0
                            progress.a2T3errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        4 ->{progress.a2T4condition = 5
                            progress.a2T4 = 0
                            progress.a2T4errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        5 ->{progress.a2T5condition = 0
                            progress.a2T5 = 0
                            progress.a2T5errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}

                        6 ->{progress.a2T6condition = 0
                            progress.a2T6 = 0
                            progress.a2T6errorArray.clear()
                            val updateJson = Gson().toJson(progress)
                            File(this.filesDir,"progress").writeText(updateJson)}}
                }}
                Toast.makeText(this, "Ошибок нет!", Toast.LENGTH_SHORT).show()
                Thread.sleep(delayInApp)

                val intentChooseThemeActivity = Intent(this, ChooseThemeActivity::class.java)
                startActivity(intentChooseThemeActivity)
                finish()
            }
            else{
                when(choosenLvl){
                    1 -> when(choosenTheme){
                        1 -> {progress.a1T1condition = 1}
                        2 -> {progress.a1T2condition = 1}
                        3 -> {progress.a1T3condition = 1}
                        4 -> {progress.a1T4condition = 1}
                        5 -> {progress.a1T5condition = 1}
                        6 -> {progress.a1T6condition = 1}
                        7 -> {progress.a1T7condition = 1}
                    }
                    2 -> when(choosenTheme){
                        1 -> {progress.a2T1condition = 1}
                        2 -> {progress.a2T2condition = 1}
                        3 -> {progress.a2T3condition = 1}
                        4 -> {progress.a2T4condition = 1}
                        5 -> {progress.a2T5condition = 1}
                        6 -> {progress.a2T6condition = 1}
                    }
                }
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



