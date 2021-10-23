package com.tes.balabali.data.ext

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton
import com.afollestad.materialdialogs.actions.getActionButton
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.tes.balabali.AppController
import com.tes.balabali.domain.model.OwnerModel
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

fun AppCompatActivity.showMessage(
    message: String,
    title: String = "Peringatan",
    positiveButtonText: String = "OK",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "",
    negativeAction: () -> Unit = {}
) {
    MaterialDialog(this)
        .cancelable(false)
        .lifecycleOwner(this)
        .show {
            title(text = title)
            message(text = message)
            positiveButton(text = positiveButtonText) {
                positiveAction.invoke()
            }
            if (negativeButtonText.isNotEmpty()) {
                negativeButton(text = negativeButtonText) {
                    negativeAction.invoke()
                }
            }
            getActionButton(WhichButton.POSITIVE).updateTextColor(Color.BLACK)
            getActionButton(WhichButton.NEGATIVE).updateTextColor(Color.RED)
        }
}

fun Fragment.showMessage(
    message: String,
    title: String = "Peringatan",
    positiveButtonText: String = "OK",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "",
    negativeAction: () -> Unit = {}
) {
    activity?.let {
        MaterialDialog(it)
            .cancelable(false)
            .lifecycleOwner(viewLifecycleOwner)
            .show {
                title(text = title)
                message(text = message)
                positiveButton(text = positiveButtonText) {
                    positiveAction.invoke()
                }
                if (negativeButtonText.isNotEmpty()) {
                    negativeButton(text = negativeButtonText) {
                        negativeAction.invoke()
                    }
                }
                getActionButton(WhichButton.POSITIVE).updateTextColor(Color.BLACK)
                getActionButton(WhichButton.NEGATIVE).updateTextColor(Color.RED)
            }
    }
}

fun String.toHtml(flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, flag)
    } else {
        Html.fromHtml(this)
    }
}


fun formatToRupiah(number: String): String? {
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
    val decimalFormatSymbols = (formatRupiah as DecimalFormat).decimalFormatSymbols
    decimalFormatSymbols.currencySymbol = ""
    formatRupiah.decimalFormatSymbols = decimalFormatSymbols

    formatRupiah.maximumFractionDigits = 0
    return formatRupiah.format(number.toDouble())
}

fun getProcessName(pid: Int): String? {
    var reader: BufferedReader? = null
    try {
        reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
        var processName = reader.readLine()
        if (!TextUtils.isEmpty(processName)) {
            processName = processName.trim { it <= ' ' }
        }
        return processName
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
    } finally {
        try {
            reader?.close()
        } catch (exception: IOException) {
            exception.printStackTrace()
        }

    }
    return null
}

fun showToastShort(message: String) {
    Toast.makeText(
        AppController.instance.applicationContext,
        message, Toast.LENGTH_SHORT
    )
        .show()
}

fun showToastLong(message: String) {
    Toast.makeText(
        AppController.instance.applicationContext,
        message, Toast.LENGTH_LONG
    )
        .show()
}

fun isNetworkAvailable(context: Context): Boolean {
    val manager = context.applicationContext.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager ?: return false
    val info = manager.activeNetworkInfo
    return null != info && info.isAvailable
}
