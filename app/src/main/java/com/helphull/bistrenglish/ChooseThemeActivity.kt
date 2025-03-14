package com.helphull.bistrenglish

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.helphull.bistrenglish.databinding.ActivityCooseThemeBinding
import com.helphull.bistrenglish.progress.createJsonFile
import com.helphull.bistrenglish.progress.readJsonFile

class ChooseThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCooseThemeBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        createJsonFile(this)
        val progress = readJsonFile(this)
        binding = ActivityCooseThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun chooseLvlGone(){
            val allLayout = listOf(binding.btA1, binding.btA2,binding.btB1,binding.btB2,
                binding.btC1,binding.btC2, binding.tvChooseLvl)
            allLayout.forEach { it.visibility = View.GONE }
            val themeLayout = listOf(binding.btTheme1, binding.btTheme2, binding.btTheme3,
                binding.btTheme4, binding.btTheme5, binding.btTheme6, binding.btTheme7,
                binding.tvTheme, binding.btBackLvlChoose)
            themeLayout.forEach { it.visibility = View.VISIBLE }
            binding.btBackLvlChoose.setOnClickListener {
                val allLayout = listOf(binding.btA1, binding.btA2,binding.btB1,binding.btB2,
                    binding.btC1,binding.btC2, binding.tvChooseLvl)
                allLayout.forEach { it.visibility = View.VISIBLE }
                val themeLayout = listOf(binding.btTheme1, binding.btTheme2, binding.btTheme3,
                    binding.btTheme4, binding.btTheme5, binding.btTheme6, binding.btTheme7,
                    binding.tvTheme, binding.btBackLvlChoose)
                themeLayout.forEach { it.visibility = View.GONE }
            }
        }
        fun bindingMovingTheme() { //TODO: Дописать логику открытия основной части или работы над ошибками для всех
            // Надо сделать через when
            binding.btTheme1.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T1condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 1
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 1
                            }
                        }
                    }
                    2 -> {
                        when (progress!!.a2T1condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 1
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 1
                            }
                        }
                    }

                }

            }
            binding.btTheme2.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T2condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 2
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 2
                            }
                        }
                    }
                    2 -> {
                        when (progress!!.a2T2condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 2
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 2
                            }
                        }
                    }
                }
            }
            binding.btTheme3.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T3condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 3
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 3
                            }
                        }
                    }
                    2 -> {
                        when (progress!!.a2T3condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 2
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 2
                            }
                        }
                    }
                }
            }
            binding.btTheme4.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T4condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 4
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 4
                            }
                        }
                    }
                    2 -> {
                        when (progress!!.a2T4condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 4
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 4
                            }
                        }
                    }
                }
            }
            binding.btTheme5.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T5condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 5
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 5
                            }
                        }
                    }
                    2 -> {
                        when (progress!!.a2T5condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 5
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 5
                            }
                        }
                    }
                }
            }
            binding.btTheme6.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T6condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 6
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 6
                            }
                        }
                    }
                    2 -> {
                        when (progress!!.a2T6condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 6
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 6
                            }
                        }
                    }
                }
            }
            binding.btTheme7.setOnClickListener {
                when (choosenLvl) {
                    1 -> {
                        when (progress!!.a1T7condition) {
                            0 -> {
                                val intentMainActivity = Intent(this, MainActivity::class.java)
                                startActivity(intentMainActivity)
                                choosenTheme = 7
                            }

                            1 -> {
                                val intentErrorWorkActivity =
                                    Intent(this, ErrorWorkActivity::class.java)
                                startActivity(intentErrorWorkActivity)
                                choosenTheme = 7
                            }
                        }
                    }
                }
            }
        }

        binding.btA1.setOnClickListener {
            choosenLvl = 1
            chooseLvlGone()

            binding.btTheme1.text = "Глаголы A1"
            binding.btTheme2.text = "Вокруг света"
            binding.btTheme3.text = "О семье"
            binding.btTheme4.text = "Социальная коммуникация"
            binding.btTheme5.text = "База слов туриста"
            binding.btTheme6.text = "Прилагательные А1"
            binding.btTheme7.text = "Наречия А1"
            bindingMovingTheme()


        }
        binding.btA2.setOnClickListener {
            choosenLvl = 2
            chooseLvlGone()

            binding.btTheme1.text = "Базовые глаголы А2"
            binding.btTheme2.text = "Карьера и профессия"
            binding.btTheme3.text = "Развлечения и отдых"
            binding.btTheme4.text = "Климат и защита природы"
            binding.btTheme5.text = "Прилагательные А2"
            binding.btTheme6.text = "Наречия А2"
            binding.btTheme7.visibility = View.GONE
            bindingMovingTheme()
        }


    }
}