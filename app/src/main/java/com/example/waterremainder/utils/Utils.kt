package com.example.waterremainder.utils

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {

    companion object {
        fun showSnackBar(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()

        fun convertTime(time: Long): String{
            val formatTime = SimpleDateFormat("hh:mm aa")
            val time = formatTime.format(time)
            return time.toString()
        }

        fun convertDate(time: Long): String{
            val formatTime = SimpleDateFormat("dd-MMM-yyyy")
            val time = formatTime.format(time)
            return time.toString()
        }

        fun currentDate(): String{
            return SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
                .format(Date())
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun currentDateString(): String {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.BASIC_ISO_DATE
            val formatted = current.format(formatter)

            return formatted
        }

    }

}