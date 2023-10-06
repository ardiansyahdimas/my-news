package com.idstar.test.core.utils

import android.app.Activity
import androidx.core.view.isVisible
import com.idstar.test.core.R
import com.idstar.test.core.databinding.ViewErrorBinding
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun handleProgressAndError(activity: Activity,state:Boolean){
        val errorView = activity.layoutInflater.inflate(R.layout.view_error, null)
        val binding  = ViewErrorBinding.bind(errorView)
        binding.progressBar.isVisible = state
        binding.containerError.isVisible = !state
    }

    fun convertDate(dateString: String): String {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

            val date = inputFormat.parse(dateString)
            return outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}