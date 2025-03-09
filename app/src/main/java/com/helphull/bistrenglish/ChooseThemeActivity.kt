package com.helphull.bistrenglish

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.helphull.bistrenglish.databinding.ActivityCooseThemeBinding

class ChooseThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCooseThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCooseThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    binding.btTheme1.setOnClickListener {val intentMainActivity = Intent(this,MainActivity ::class.java)
    startActivity(intentMainActivity)
        choosenTheme = 1
    }
    binding.btTheme2.setOnClickListener{val intentMainActivity = Intent(this,MainActivity::class.java)
    startActivity(intentMainActivity)
        choosenTheme = 2
    }
    }
}