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

// Мапы для хранения данных // TODO Добавить сюда
        val enWordsMap = mapOf(
            11 to englishVerbsA1, 12 to englishNounsWordAroundA1, 13 to enNounsFamilyA1,
            14 to enNounsSocialA1, 15 to enNounsTouristA1, 16 to enAdjectivesA1, 17 to enAdverbsA1,
            21 to enVerbsA2, 22 to enNounsJobA2, 23 to enNounsRestA2, 24 to enNounsNatureA2,
            25 to enAdjectivesA2, 26 to enAdverbsA2
        )

        val ruWordsMap = mapOf(
            11 to russianVerbsA1, 12 to russianNounsWordAroundA1, 13 to ruNounsFamilyA1,
            14 to ruNounsSocialA1, 15 to ruNounsTouristA1, 16 to ruAdjectivesA1, 17 to ruAdverbsA1,
            21 to ruVerbsA2, 22 to ruNounsJobA2, 23 to ruNounsRestA2, 24 to ruNounsNatureA2,
            25 to ruAdjectivesA2, 26 to ruAdverbsA2
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
            26 to randomRuAdverbsA2
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
            26 to progress.a2T6errorArray
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
        nextWord()
        ttsManager = TextToSpeechManager(this)
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
            val mapCorrectWord = mapOf(
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