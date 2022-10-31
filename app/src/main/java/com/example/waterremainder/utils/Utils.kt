package com.example.waterremainder.utils

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.view.View
import com.google.android.material.snackbar.Snackbar

class Utils {

    companion object {
        fun showSnackBar(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

}