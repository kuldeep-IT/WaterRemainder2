package com.example.waterremainder.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
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

    }

}