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
import com.google.gson.Gson
import com.helphull.bistrenglish.databinding.ActivityErrorWorkBinding
import com.helphull.bistrenglish.progress.Progress
import com.helphull.bistrenglish.progress.correctTheme
import com.helphull.bistrenglish.progress.readJsonFile
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
import com.helphull.bistrenglish.text.randomRuNounsFamilyA1
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

class ErrorWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityErrorWorkBinding
    private lateinit var ttsManager: TextToSpeechManager
    private fun unclickable() {
        binding.btAnswer1Error.isClickable = false
        binding.btAnswer2Error.isClickable = false
        binding.btAnswer3Error.isClickable = false
        binding.btAnswer4Error.isClickable = false
        binding.btAnswer5Error.isClickable = false
        binding.btIdkError.isClickable = false
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


    private fun nextWord(delayInNextWord : Long) {
        wordNumber += 1


        if (wordNumber == errorRuWords.size) {
            // Показываем кнопку "В начало"
            binding.btIdkError.visibility = View.GONE
            binding.btRestartError.visibility = View.VISIBLE
            return // Прекращаем обновление слов
        }
        // TTS на запуск
        CoroutineScope(Dispatchers.Main).launch {
            delay(delayInNextWord)
            ttsManager.speak(errorEnWords[wordNumber])}

        // Обновляем actualWords каждый раз при вызове nextWord()
        val actualWords = mutableListOf(
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
        binding.btIdkError.isClickable = true

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
        ttsManager = TextToSpeechManager(this)
        val progress = readJsonFile(this)!!

// Мапы для хранения данных // TODO Добавить сюда
        val enWordsMap = mapOf(
            11 to englishVerbsA1,
            12 to englishNounsWordAroundA1,
            13 to enNounsFamilyA1,
            14 to enNounsSocialA1,
            15 to enNounsTouristA1,
            16 to enAdjectivesA1,
            17 to enAdverbsA1,
            21 to enVerbsA2,
            22 to enNounsJobA2,
            23 to enNounsRestA2,
            24 to enNounsNatureA2,
            25 to enAdjectivesA2,
            26 to enAdverbsA2,
            31 to enVerbsB1,
            32 to enNounsTeensB1,
            33 to enNounsPostCovidB1,
            34 to enNounsSuccessB1,
            35 to enAdjectivesB1,
            36 to enAdverbsB1,
            41 to enVerbsB2,
            42 to enNounsModernEducationB2,
            43 to enNounsSocTrendsB2,
            44 to enNounsModernLiteratureB2,
            45 to enAdjectivesB2,
            46 to enAdverbsB2,
            51 to enVerbsC1,
            52 to enNounsScienceC1,
            53 to enNounsPersonalityC1,
            54 to enNounsEarthC1,
            55 to enAdjectivesC1,
            56 to enAdverbsC1,
            61 to enIdioms,
            62 to enJargon,
            63 to enPhrasalVerbs,
        )

        val ruWordsMap = mapOf(
            11 to russianVerbsA1,
            12 to russianNounsWordAroundA1,
            13 to ruNounsFamilyA1,
            14 to ruNounsSocialA1,
            15 to ruNounsTouristA1,
            16 to ruAdjectivesA1,
            17 to ruAdverbsA1,
            21 to ruVerbsA2,
            22 to ruNounsJobA2,
            23 to ruNounsRestA2,
            24 to ruNounsNatureA2,
            25 to ruAdjectivesA2,
            26 to ruAdverbsA2,
            31 to ruVerbsB1,
            32 to ruNounsTeensB1,
            33 to ruNounsPostCovidB1,
            34 to ruNounsSuccessB1,
            35 to ruAdjectivesB1,
            36 to ruAdverbsB1,
            41 to ruVerbsB2,
            42 to ruNounsModernEducationB2,
            43 to ruNounsSocTrendsB2,
            44 to ruNounsModernLiteratureB2,
            45 to ruAdjectivesB2,
            46 to ruAdverbsB2,
            51 to ruVerbsC1,
            52 to ruNounsScienceC1,
            53 to ruNounsPersonalityC1,
            54 to ruNounsEarthC1,
            55 to ruAdjectivesC1,
            56 to ruAdverbsC1,
            61 to ruIdioms,
            62 to ruJargon,
            63 to ruPhrasalVerbs,
        )

        val randomRuWordsMap = mapOf(
            11 to randomRussianVerbsA1,
            12 to randomRussianNounsWorldA1,
            13 to randomRuNounsFamilyA1,
            14 to randomRuNounsSocialA1,
            15 to randomRuNounsTouristA1,
            16 to randomRuAdjectivesA1,
            17 to randomRuAdverbsA1,
            21 to randomRuVerbsA2,
            22 to randomRuNounsJobA2,
            23 to randomRuNounsRestA2,
            24 to randomRuNounsNatureA2,
            25 to randomRuAdjectivesA2,
            26 to randomRuAdverbsA2,
            31 to randomRuVerbsB1,
            32 to randomRuNounsTeensB1,
            33 to randomRuNounsPostCovidB1,
            34 to randomRuNounsSuccessB1,
            35 to randomRuAdjectivesB1,
            36 to randomRuAdverbsB1,
            41 to randomRuVerbsB2,
            42 to randomRuNounsModernEducationB2,
            43 to randomRuNounsSocTrendsB2,
            44 to randomRuNounsModernLiteratureB2,
            45 to randomRuAdjectivesB2,
            46 to randomRuAdverbsB2,
            51 to randomRuVerbsC1,
            52 to randomRuNounsScienceC1,
            53 to randomRuNounsPersonalityC1,
            54 to randomRuNounsEarthC1,
            55 to randomRuAdjectivesC1,
            56 to randomRuAdverbsC1,
            61 to randomRuIdioms,
            62 to randomRuJargon,
            63 to randomRuPhrasalVerbs,
        )

        val errorArrayMap = mapOf(
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


// Получаем данные по ключу
        val errorArray = errorArrayMap[correctTheme] ?: emptyList()
        val enWords = enWordsMap[correctTheme] ?: emptyList()
        val ruWords = ruWordsMap[correctTheme] ?: emptyList()
        val randomRuWords = randomRuWordsMap[correctTheme] ?: emptyList()

// Заполняем errorEnWords и errorRuWords
        errorEnWords = errorArray.map { enWords[it] }.toMutableList()
        errorRuWords = errorArray.map { ruWords[it] }.toMutableList()
        randomRuErrorWords = randomRuWords
        nextWord(800)
        binding.btPlayTextError.setOnClickListener { ttsManager.speak(errorEnWords[wordNumber]) }
        binding.btRestartError.setOnClickListener {
            errorEnWords.removeAll(enWordsToRemove)
            errorRuWords.removeAll(ruWordsToRemove)

           /* when(choosenLvl){ //TODO: Решиить вопрос с удалением прорешенных слов
                1 -> {when(choosenTheme){
                    1 -> {
                        indexOfSolvedErrors.sortedDescending().forEach{ index ->
                        progress.a1T1errorArray.removeAt(index)}
                        updateJsonFile(this, progress)
                    }}
                }}*/
            val mapCorrectWord = mapOf( // TODO Добавлять сюда
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
            val errorArrayMapping = mapOf(
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
            if (errorEnWords.size == 0) {
                conditionMap[correctTheme]?.set(progress, 0)
                mapCorrectWord[correctTheme]?.set(progress, 0)
                errorArrayMapping[correctTheme]?.get(progress)?.clear()
                val updateJson = Gson().toJson(progress)
                File(this.filesDir,"progress").writeText(updateJson)

                Toast.makeText(this, "Ошибки прорешаны!", Toast.LENGTH_SHORT).show()
                Thread.sleep(delayInApp)
                val intentChooseThemeActivity = Intent(this, ChooseThemeActivity::class.java)
                startActivity(intentChooseThemeActivity)
                finish()
            }
            recreate() // Закрываем текущую Activity
        }
        binding.btIdkError.setOnClickListener {
            paintGreen()
            unclickable()
            CoroutineScope(Dispatchers.Main).launch {
                delay(delayInApp) // Задержка 1.2 секунды
                nextWord(50)
            }}
        binding.btAnswer1Error.setOnClickListener {
            if (binding.btAnswer1Error.text == errorRuWords[wordNumber]) {
                unclickable()
                binding.btAnswer1Error.setBackgroundColor(Color.GREEN)
                removeWord()
                Log.d("Correct", "$enWordsToRemove")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer1Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
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
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer2Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
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
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer3Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
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
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer4Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
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
                    nextWord(50)  // Переход к следующему слову
                }
            } else {
                unclickable()
                binding.btAnswer5Error.setBackgroundColor(Color.RED)
                paintGreen()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(delayInApp) // Задержка 1.2 секунды
                    nextWord(50)  // Переход к следующему слову
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        ttsManager.shutdown()
    }
}