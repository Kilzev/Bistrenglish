package com.helphull.bistrenglish

import TextToSpeechManager
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.helphull.bistrenglish.databinding.ActivityMainBinding
import com.helphull.bistrenglish.progress.Progress
import com.helphull.bistrenglish.progress.correctTheme
import com.helphull.bistrenglish.progress.readJsonFile
import com.helphull.bistrenglish.progress.updateJsonFile
import com.helphull.bistrenglish.text.enAdjectivesA1
import com.helphull.bistrenglish.text.enAdjectivesA2
import com.helphull.bistrenglish.text.enAdjectivesB1
import com.helphull.bistrenglish.text.enAdjectivesB2
import com.helphull.bistrenglish.text.enAdjectivesC1
import com.helphull.bistrenglish.text.enAdverbsA1
import com.helphull.bistrenglish.text.enAdverbsA2
import com.helphull.bistrenglish.text.enAdverbsB1
import com.helphull.bistrenglish.text.enAdverbsB2
import com.helphull.bistrenglish.text.enAdverbsC1
import com.helphull.bistrenglish.text.enIdioms
import com.helphull.bistrenglish.text.enJargon
import com.helphull.bistrenglish.text.enNounsEarthC1
import com.helphull.bistrenglish.text.enNounsFamilyA1
import com.helphull.bistrenglish.text.enNounsJobA2
import com.helphull.bistrenglish.text.enNounsModernEducationB2
import com.helphull.bistrenglish.text.enNounsModernLiteratureB2
import com.helphull.bistrenglish.text.enNounsNatureA2
import com.helphull.bistrenglish.text.enNounsPersonalityC1
import com.helphull.bistrenglish.text.enNounsPostCovidB1
import com.helphull.bistrenglish.text.enNounsRestA2
import com.helphull.bistrenglish.text.enNounsScienceC1
import com.helphull.bistrenglish.text.enNounsSocTrendsB2
import com.helphull.bistrenglish.text.enNounsSocialA1
import com.helphull.bistrenglish.text.enNounsSuccessB1
import com.helphull.bistrenglish.text.enNounsTeensB1
import com.helphull.bistrenglish.text.enNounsTouristA1
import com.helphull.bistrenglish.text.enPhrasalVerbs
import com.helphull.bistrenglish.text.enVerbsA2
import com.helphull.bistrenglish.text.enVerbsB1
import com.helphull.bistrenglish.text.enVerbsB2
import com.helphull.bistrenglish.text.enVerbsC1
import com.helphull.bistrenglish.text.englishNounsWordAroundA1

import com.helphull.bistrenglish.text.englishVerbsA1
import com.helphull.bistrenglish.text.errorEnWords
import com.helphull.bistrenglish.text.errorRuWords
import com.helphull.bistrenglish.text.randomRuNounsFamilyA1
import com.helphull.bistrenglish.text.randomRuAdjectivesA1
import com.helphull.bistrenglish.text.randomRuAdjectivesA2
import com.helphull.bistrenglish.text.randomRuAdjectivesB1
import com.helphull.bistrenglish.text.randomRuAdjectivesB2
import com.helphull.bistrenglish.text.randomRuAdjectivesC1
import com.helphull.bistrenglish.text.randomRuAdverbsA1
import com.helphull.bistrenglish.text.randomRuAdverbsA2
import com.helphull.bistrenglish.text.randomRuAdverbsB1
import com.helphull.bistrenglish.text.randomRuAdverbsB2
import com.helphull.bistrenglish.text.randomRuAdverbsC1
import com.helphull.bistrenglish.text.randomRuIdioms
import com.helphull.bistrenglish.text.randomRuJargon
import com.helphull.bistrenglish.text.randomRuNounsEarthC1
import com.helphull.bistrenglish.text.randomRuNounsJobA2
import com.helphull.bistrenglish.text.randomRuNounsModernEducationB2
import com.helphull.bistrenglish.text.randomRuNounsModernLiteratureB2
import com.helphull.bistrenglish.text.randomRuNounsNatureA2
import com.helphull.bistrenglish.text.randomRuNounsPersonalityC1
import com.helphull.bistrenglish.text.randomRuNounsPostCovidB1
import com.helphull.bistrenglish.text.randomRuNounsRestA2
import com.helphull.bistrenglish.text.randomRuNounsScienceC1
import com.helphull.bistrenglish.text.randomRuNounsSocTrendsB2
import com.helphull.bistrenglish.text.randomRuNounsSocialA1
import com.helphull.bistrenglish.text.randomRuNounsSuccessB1
import com.helphull.bistrenglish.text.randomRuNounsTeensB1
import com.helphull.bistrenglish.text.randomRuNounsTouristA1
import com.helphull.bistrenglish.text.randomRuPhrasalVerbs
import com.helphull.bistrenglish.text.randomRuVerbsA2
import com.helphull.bistrenglish.text.randomRuVerbsB1
import com.helphull.bistrenglish.text.randomRuVerbsB2
import com.helphull.bistrenglish.text.randomRuVerbsC1
import com.helphull.bistrenglish.text.randomRussianNounsWorldA1
import com.helphull.bistrenglish.text.randomRussianVerbsA1
import com.helphull.bistrenglish.text.ruAdjectivesA1
import com.helphull.bistrenglish.text.ruAdjectivesA2
import com.helphull.bistrenglish.text.ruAdjectivesB1
import com.helphull.bistrenglish.text.ruAdjectivesB2
import com.helphull.bistrenglish.text.ruAdjectivesC1
import com.helphull.bistrenglish.text.ruAdverbsA1
import com.helphull.bistrenglish.text.ruAdverbsA2
import com.helphull.bistrenglish.text.ruAdverbsB1
import com.helphull.bistrenglish.text.ruAdverbsB2
import com.helphull.bistrenglish.text.ruAdverbsC1
import com.helphull.bistrenglish.text.ruIdioms
import com.helphull.bistrenglish.text.ruJargon
import com.helphull.bistrenglish.text.ruNounsEarthC1
import com.helphull.bistrenglish.text.ruNounsFamilyA1
import com.helphull.bistrenglish.text.ruNounsJobA2
import com.helphull.bistrenglish.text.ruNounsModernEducationB2
import com.helphull.bistrenglish.text.ruNounsModernLiteratureB2
import com.helphull.bistrenglish.text.ruNounsNatureA2
import com.helphull.bistrenglish.text.ruNounsPersonalityC1
import com.helphull.bistrenglish.text.ruNounsPostCovidB1
import com.helphull.bistrenglish.text.ruNounsRestA2
import com.helphull.bistrenglish.text.ruNounsScienceC1
import com.helphull.bistrenglish.text.ruNounsSocTrendsB2
import com.helphull.bistrenglish.text.ruNounsSocialA1
import com.helphull.bistrenglish.text.ruNounsSuccessB1
import com.helphull.bistrenglish.text.ruNounsTeensB1
import com.helphull.bistrenglish.text.ruNounsTouristA1
import com.helphull.bistrenglish.text.ruPhrasalVerbs
import com.helphull.bistrenglish.text.ruVerbsA2
import com.helphull.bistrenglish.text.ruVerbsB1
import com.helphull.bistrenglish.text.ruVerbsB2
import com.helphull.bistrenglish.text.ruVerbsC1
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


    private fun btPaintGreen() {
        val correctColor = ContextCompat.getColor(this, R.color.light_backgraund_bt_green)

        listOf(
            binding.btAnswer1,
            binding.btAnswer2,
            binding.btAnswer3,
            binding.btAnswer4,
            binding.btAnswer5
        ).firstOrNull { it.text == rusAppWords[wordNumber] }
            ?.setBackgroundColor(correctColor)
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
            26 to progress.a2T6errorArray,
            31 to progress.b1T1errorArray,
            32 to progress.b1T2errorArray,
            33 to progress.b1T3errorArray,
            34 to progress.b1T4errorArray,
            35 to progress.b1T5errorArray,
            36 to progress.b1T6errorArray,
            41 to progress.b2T1errorArray,
            42 to progress.b2T2errorArray,
            43 to progress.b2T3errorArray,
            44 to progress.b2T4errorArray,
            45 to progress.b2T5errorArray,
            46 to progress.b2T6errorArray,
            51 to progress.c1T1errorArray,
            52 to progress.c1T2errorArray,
            53 to progress.c1T3errorArray,
            54 to progress.c1T4errorArray,
            55 to progress.c1T5errorArray,
            56 to progress.c1T6errorArray,
            61 to progress.c2T1errorArray,
            62 to progress.c2T2errorArray,
            63 to progress.c2T3errorArray,

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

    @SuppressLint("SuspiciousIndentation")
    private fun nextWord(delayInNextWord : Long) {
        val progress = readJsonFile(this)!!
        if (wordNumber >= rusAppWords.size - 1) {
            // Показываем кнопку "В начало"
            binding.btGoToErrorActivity.visibility = View.VISIBLE
            binding.btIdk.visibility = View.GONE
            return // Прекращаем обновление слов
        }

        wordNumber += 1
        // TTS на запуск
        CoroutineScope(Dispatchers.Main).launch {
            delay(delayInNextWord)
            ttsManager.speak(enAppWords[wordNumber])}

        when (correctTheme) { //TODO Добавить сюда
            11 -> {
                progress.a1T1 = wordNumber
            }

            12 -> {
                progress.a1T2 = wordNumber
            }

            13 -> {
                progress.a1T3 = wordNumber
            }

            14 -> {
                progress.a1T4 = wordNumber
            }

            15 -> {
                progress.a1T5 = wordNumber
            }

            16 -> {
                progress.a1T6 = wordNumber
            }

            17 -> {
                progress.a1T7 = wordNumber
            }

            21 -> {
                progress.a2T1 = wordNumber
            }

            22 -> {
                progress.a2T2 = wordNumber
            }

            23 -> {
                progress.a2T3 = wordNumber
            }

            24 -> {
                progress.a2T4 = wordNumber
            }

            25 -> {
                progress.a2T5 = wordNumber
            }

            26 -> {
                progress.a2T6 = wordNumber
            }
            31 -> {
                progress.b1T1 = wordNumber
            }

            32 -> {
                progress.b1T2 = wordNumber
            }

            33 -> {
                progress.b1T3 = wordNumber
            }

            34 -> {
                progress.b1T4 = wordNumber
            }

            35 -> {
                progress.b1T5 = wordNumber
            }

            36 -> {
                progress.b1T6 = wordNumber
            }
            41 -> {
                progress.b2T1 = wordNumber
            }

            42 -> {
                progress.b2T2 = wordNumber
            }

            43 -> {
                progress.b2T3 = wordNumber
            }
            44 -> {
                progress.b2T4 = wordNumber
            }

            45 -> {
                progress.b2T5 = wordNumber
            }

            46 -> {
                progress.b2T6 = wordNumber
            }
            51 -> {
                progress.c1T1 = wordNumber
            }

            52 -> {
                progress.c1T2 = wordNumber
            }

            53 -> {
                progress.c1T3 = wordNumber
            }

            54 -> {
                progress.c1T4 = wordNumber
            }

            55 -> {
                progress.c1T5 = wordNumber
            }

            56 -> {
                progress.c1T6 = wordNumber
            }
            61 -> {
                progress.c2T1 = wordNumber
            }

            62 -> {
                progress.c2T2 = wordNumber
            }

            63 -> {
                progress.c2T3 = wordNumber
            }
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
        val greyColor = ContextCompat.getColor(this, R.color.light_backgraund_bt_white)

            binding.btAnswer1.setBackgroundColor(greyColor)
            binding.btAnswer2.setBackgroundColor(greyColor)
            binding.btAnswer3.setBackgroundColor(greyColor)
            binding.btAnswer4.setBackgroundColor(greyColor)
            binding.btAnswer5.setBackgroundColor(greyColor)
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
            26 to Triple(ruAdverbsA2, enAdverbsA2, randomRuAdverbsA2),
            31 to Triple(ruVerbsB1, enVerbsB1, randomRuVerbsB1),
            32 to Triple(ruNounsTeensB1, enNounsTeensB1, randomRuNounsTeensB1),
            33 to Triple(ruNounsPostCovidB1, enNounsPostCovidB1, randomRuNounsPostCovidB1),
            34 to Triple(ruNounsSuccessB1, enNounsSuccessB1, randomRuNounsSuccessB1),
            35 to Triple(ruAdjectivesB1, enAdjectivesB1, randomRuAdjectivesB1),
            36 to Triple(ruAdverbsB1, enAdverbsB1, randomRuAdverbsB1),
            41 to Triple(ruVerbsB2, enVerbsB2, randomRuVerbsB2),
            42 to Triple(ruNounsModernEducationB2, enNounsModernEducationB2, randomRuNounsModernEducationB2),
            43 to Triple(ruNounsSocTrendsB2, enNounsSocTrendsB2, randomRuNounsSocTrendsB2),
            44 to Triple(ruNounsModernLiteratureB2, enNounsModernLiteratureB2, randomRuNounsModernLiteratureB2),
            45 to Triple(ruAdjectivesB2, enAdjectivesB2, randomRuAdjectivesB2),
            46 to Triple(ruAdverbsB2, enAdverbsB2, randomRuAdverbsB2),
            51 to Triple(ruVerbsC1, enVerbsC1, randomRuVerbsC1),
            52 to Triple(ruNounsScienceC1, enNounsScienceC1, randomRuNounsScienceC1),
            53 to Triple(ruNounsPersonalityC1, enNounsPersonalityC1, randomRuNounsPersonalityC1),
            54 to Triple(ruNounsEarthC1, enNounsEarthC1, randomRuNounsEarthC1),
            55 to Triple(ruAdjectivesC1, enAdjectivesC1, randomRuAdjectivesC1),
            56 to Triple(ruAdverbsC1, enAdverbsC1, randomRuAdverbsC1),
            61 to Triple(ruIdioms, enIdioms, randomRuIdioms),
            62 to Triple(ruJargon, enJargon, randomRuJargon),
            63 to Triple(ruPhrasalVerbs, enPhrasalVerbs, randomRuPhrasalVerbs),

        )

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
            31 to progress.b1T1-1,
            32 to progress.b1T2-1,
            33 to progress.b1T3-1,
            34 to progress.b1T4-1,
            35 to progress.b1T5-1,
            36 to progress.b1T6-1,
            41 to progress.b2T1-1,
            42 to progress.b2T2-1,
            43 to progress.b2T3-1,
            44 to progress.b2T4-1,
            45 to progress.b2T5-1,
            46 to progress.b2T6-1,
            51 to progress.c1T1-1,
            52 to progress.c1T2-1,
            53 to progress.c1T3-1,
            54 to progress.c1T4-1,
            55 to progress.c1T5-1,
            56 to progress.c1T6-1,
            61 to progress.c2T1-1,
            62 to progress.c2T2-1,
            63 to progress.c2T3-1,
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
            26 to progress.a2T6errorArray,
            31 to progress.b1T1errorArray,
            32 to progress.b1T2errorArray,
            33 to progress.b1T3errorArray,
            34 to progress.b1T4errorArray,
            35 to progress.b1T5errorArray,
            36 to progress.b1T6errorArray,
            41 to progress.b2T1errorArray,
            42 to progress.b2T2errorArray,
            43 to progress.b2T3errorArray,
            44 to progress.b2T4errorArray,
            45 to progress.b2T5errorArray,
            46 to progress.b2T6errorArray,
            51 to progress.c1T1errorArray,
            52 to progress.c1T2errorArray,
            53 to progress.c1T3errorArray,
            54 to progress.c1T4errorArray,
            55 to progress.c1T5errorArray,
            56 to progress.c1T6errorArray,
            61 to progress.c2T1errorArray,
            62 to progress.c2T2errorArray,
            63 to progress.c2T3errorArray,
        )

        val (rusWords, enWords, randomRuWords) = themeToWordsMap[correctTheme] ?: return
        rusAppWords = rusWords
        enAppWords = enWords
        randomRuAppWords = randomRuWords
        wordNumber = mapCorrectWord[correctTheme] ?: return
        errorEnWords = errorArrayMapping[correctTheme]!!.map { enAppWords[it] }.toMutableList()
        errorRuWords = errorArrayMapping[correctTheme]!!.map { rusAppWords[it] }.toMutableList()

        // Первая инициализация
        nextWord(800)
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
                26 to Progress::a2T6,
                31 to Progress::b1T1,
                32 to Progress::b1T2,
                33 to Progress::b1T3,
                34 to Progress::b1T4,
                35 to Progress::b1T5,
                36 to Progress::b1T6,
                41 to Progress::c1T1,
                42 to Progress::c1T2,
                43 to Progress::c1T3,
                44 to Progress::c1T4,
                45 to Progress::c1T5,
                46 to Progress::c1T6,
                51 to Progress::c1T1,
                52 to Progress::c1T2,
                53 to Progress::c1T3,
                54 to Progress::c1T4,
                55 to Progress::c1T5,
                56 to Progress::c1T6,
                61 to Progress::c2T1,
                62 to Progress::c2T2,
                63 to Progress::c2T3,
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
                26 to Progress::a2T6errorArray,
                31 to Progress::b1T1errorArray,
                32 to Progress::b1T2errorArray,
                33 to Progress::b1T3errorArray,
                34 to Progress::b1T4errorArray,
                35 to Progress::b1T5errorArray,
                36 to Progress::b1T6errorArray,
                41 to Progress::b2T1errorArray,
                42 to Progress::b2T2errorArray,
                43 to Progress::b2T3errorArray,
                44 to Progress::b2T4errorArray,
                45 to Progress::b2T5errorArray,
                46 to Progress::b2T6errorArray,
                51 to Progress::c1T1errorArray,
                52 to Progress::c1T2errorArray,
                53 to Progress::c1T3errorArray,
                54 to Progress::c1T4errorArray,
                55 to Progress::c1T5errorArray,
                56 to Progress::c1T6errorArray,
                61 to Progress::c2T1errorArray,
                62 to Progress::c2T2errorArray,
                63 to Progress::c2T3errorArray,
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
                31 to Progress::b1T1condition,
                32 to Progress::b1T2condition,
                33 to Progress::b1T3condition,
                34 to Progress::b1T4condition,
                35 to Progress::b1T5condition,
                36 to Progress::b1T6condition,
                41 to Progress::b2T1condition,
                42 to Progress::b2T2condition,
                43 to Progress::b2T3condition,
                44 to Progress::b2T4condition,
                45 to Progress::b2T5condition,
                46 to Progress::b2T6condition,
                51 to Progress::c1T1condition,
                52 to Progress::c1T2condition,
                53 to Progress::c1T3condition,
                54 to Progress::c1T4condition,
                55 to Progress::c1T5condition,
                56 to Progress::c1T6condition,
                61 to Progress::c2T1condition,
                62 to Progress::c2T2condition,
                63 to Progress::c2T3condition,
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
                nextWord(50)
            }
        }


        binding.btAnswer1.setOnClickListener {
            if (binding.btAnswer1.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer1.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_green))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer1.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_red))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer2.setOnClickListener {
            if (binding.btAnswer2.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer2.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_green))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer2.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_red))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer3.setOnClickListener {

            if (binding.btAnswer3.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer3.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_green))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer3.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_red))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer4.setOnClickListener {


            if (binding.btAnswer4.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer4.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_green))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer4.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_red))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            }
        }
        binding.btAnswer5.setOnClickListener {
            if (binding.btAnswer5.text == rusAppWords[wordNumber]) {
                unclickable()
                binding.btAnswer5.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_green))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                addErrors()
                unclickable()
                binding.btAnswer5.setBackgroundColor(ContextCompat.getColor(this, R.color.light_backgraund_bt_red))
                btPaintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            }
        }
        binding.btBackLvlChoose.setOnClickListener { finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
       ttsManager.shutdown()
    }

}



