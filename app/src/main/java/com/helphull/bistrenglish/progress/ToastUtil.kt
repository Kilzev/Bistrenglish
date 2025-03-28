@file:Suppress("DEPRECATION")

package com.helphull.bistrenglish.progress

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.helphull.bistrenglish.R

object ToastUtils {

    // Базовый метод для показа кастомного Toast
    @SuppressLint("MissingInflatedId")
    fun showCustomToast(
        context: Context,
        message: String,
        @DrawableRes iconRes: Int = R.drawable.congrats,
        duration: Int = Toast.LENGTH_LONG,
        gravity: Int = Gravity.BOTTOM,
        xOffset: Int = 0,
        yOffset: Int = 0
    ) {
        // Проверяем, что контекст - это Activity
        val activity = context as? Activity ?: return

        // Создаем и настраиваем Toast
        Toast(activity).apply {
            // Загружаем кастомный макет
            val layout = LayoutInflater.from(activity).inflate(
                R.layout.custom_toast,
                activity.findViewById(R.id.custom_toast_layout),
                false
            )

            // Настраиваем содержимое
            layout.findViewById<ImageView>(R.id.toast_icon).setImageResource(iconRes)
            layout.findViewById<TextView>(R.id.toast_text).text = message

            // Настраиваем Toast
            this.duration = duration
            this.view = layout
            setGravity(gravity, xOffset, yOffset)

            show()
        }
    }}