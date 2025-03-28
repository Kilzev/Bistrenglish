package com.helphull.bistrenglish.progress
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.File

data class Progress(
    var a1T1: Int = 0,
    var a1T2: Int = 0,
    var a1T3: Int = 0,
    var a1T4: Int = 0,
    var a1T5: Int = 0,
    var a1T6: Int = 0,
    var a1T7: Int = 0,
    var a2T1: Int = 0,
    var a2T2: Int = 0,
    var a2T3: Int = 0,
    var a2T4: Int = 0,
    var a2T5: Int = 0,
    var a2T6: Int = 0,
    var b1T1: Int = 0,
    var b1T2: Int = 0,
    var b1T3: Int = 0,
    var b1T4: Int = 0,
    var b1T5: Int = 0,
    var b1T6: Int = 0,
    var b2T1: Int = 0,
    var b2T2: Int = 0,
    var b2T3: Int = 0,
    var b2T4: Int = 0,
    var b2T5: Int = 0,
    var b2T6: Int = 0,
    var c1T1: Int = 0,
    var c1T2: Int = 0,
    var c1T3: Int = 0,
    var c1T4: Int = 0,
    var c1T5: Int = 0,
    var c1T6: Int = 0,
    var c2T1: Int = 0,
    var c2T2: Int = 0,
    var c2T3: Int = 0,

    var a1T1condition: Int = 0,
    var a1T2condition: Int = 0,
    var a1T3condition: Int = 0,
    var a1T4condition: Int = 0,
    var a1T5condition: Int = 0,
    var a1T6condition: Int = 0,
    var a1T7condition: Int = 0,
    var a2T1condition: Int = 0,
    var a2T2condition: Int = 0,
    var a2T3condition: Int = 0,
    var a2T4condition: Int = 0,
    var a2T5condition: Int = 0,
    var a2T6condition: Int = 0,
    var b1T1condition: Int = 0,
    var b1T2condition: Int = 0,
    var b1T3condition: Int = 0,
    var b1T4condition: Int = 0,
    var b1T5condition: Int = 0,
    var b1T6condition: Int = 0,
    var b2T1condition: Int = 0,
    var b2T2condition: Int = 0,
    var b2T3condition: Int = 0,
    var b2T4condition: Int = 0,
    var b2T5condition: Int = 0,
    var b2T6condition: Int = 0,
    var c1T1condition: Int = 0,
    var c1T2condition: Int = 0,
    var c1T3condition: Int = 0,
    var c1T4condition: Int = 0,
    var c1T5condition: Int = 0,
    var c1T6condition: Int = 0,
    var c2T1condition: Int = 0,
    var c2T2condition: Int = 0,
    var c2T3condition: Int = 0,

    var a1T1errorArray: MutableList<Int> = mutableListOf(),
    var a1T2errorArray: MutableList<Int> = mutableListOf(),
    var a1T3errorArray: MutableList<Int> = mutableListOf(),
    var a1T4errorArray: MutableList<Int> = mutableListOf(),
    var a1T5errorArray: MutableList<Int> = mutableListOf(),
    var a1T6errorArray: MutableList<Int> = mutableListOf(),
    var a1T7errorArray: MutableList<Int> = mutableListOf(),
    var a2T1errorArray: MutableList<Int> = mutableListOf(),
    var a2T2errorArray: MutableList<Int> = mutableListOf(),
    var a2T3errorArray: MutableList<Int> = mutableListOf(),
    var a2T4errorArray: MutableList<Int> = mutableListOf(),
    var a2T5errorArray: MutableList<Int> = mutableListOf(),
    var a2T6errorArray: MutableList<Int> = mutableListOf(),
    var b1T1errorArray: MutableList<Int> = mutableListOf(),
    var b1T2errorArray: MutableList<Int> = mutableListOf(),
    var b1T3errorArray: MutableList<Int> = mutableListOf(),
    var b1T4errorArray: MutableList<Int> = mutableListOf(),
    var b1T5errorArray: MutableList<Int> = mutableListOf(),
    var b1T6errorArray: MutableList<Int> = mutableListOf(),
    var b2T1errorArray: MutableList<Int> = mutableListOf(),
    var b2T2errorArray: MutableList<Int> = mutableListOf(),
    var b2T3errorArray: MutableList<Int> = mutableListOf(),
    var b2T4errorArray: MutableList<Int> = mutableListOf(),
    var b2T5errorArray: MutableList<Int> = mutableListOf(),
    var b2T6errorArray: MutableList<Int> = mutableListOf(),
    var c1T1errorArray: MutableList<Int> = mutableListOf(),
    var c1T2errorArray: MutableList<Int> = mutableListOf(),
    var c1T3errorArray: MutableList<Int> = mutableListOf(),
    var c1T4errorArray: MutableList<Int> = mutableListOf(),
    var c1T5errorArray: MutableList<Int> = mutableListOf(),
    var c1T6errorArray: MutableList<Int> = mutableListOf(),
    var c2T1errorArray: MutableList<Int> = mutableListOf(),
    var c2T2errorArray: MutableList<Int> = mutableListOf(),
    var c2T3errorArray: MutableList<Int> = mutableListOf(),

)


var correctTheme = 0

fun createJsonFile(context: Context){
    val file = File(context.filesDir, "progress")
    if (!file.exists()){
        val json = Gson().toJson(Progress())
        file.writeText(json)
    }
    else{Log.v("Trouble", "Джейсон уже имеется")}
}

fun readJsonFile(context: Context): Progress? {
    val file = File(context.filesDir, "progress")
    return if (file.exists()) {
        val json = file.readText()
        Log.v("JSON","Прочитанный JSON: $json") // Логирование для отладки
        Gson().fromJson(json, Progress::class.java)
    } else {
        Log.v("JSON","Файл не существует") // Логирование для отладки
        null
    }
}
fun updateJsonFile(context: Context, progress: Progress) {
    val file = File(context.filesDir, "progress")
    val json = Gson().toJson(progress)
    file.writeText(json)
   Log.v("JSON","Файл обновлен: $json") // Логирование для отладки
}

/*fun deleteJsonFile(context: Context): Boolean {
    val file = File(context.filesDir, "progress")
    return if (file.exists()) {
        val isDeleted = file.delete()
        if (isDeleted) {
            Log.v("JSON", "Файл успешно удален")
        } else {
            Log.v("JSON", "Не удалось удалить файл")
        }
        isDeleted
    } else {
        Log.v("JSON", "Файл не существует")
        false
    }*/