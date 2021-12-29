package xyz.zohre.presentation

import android.content.Context
import android.widget.Toast
import com.example.presentation.R
import xyz.zohre.domain.exeption.RemoteCallException

fun Throwable.parseErrorStringRes(): TextData {
  return when (this) {
    is RemoteCallException -> {
      TextData.TextStringRes(R.string.remote_error)
    }
    else -> {
      TextData.TextStringRes(R.string.unkown_error)
    }
  }
}

fun TextData.shortToast(requireContext: Context) {
  when (this) {
    is TextData.TextString -> Toast.makeText(requireContext, this.text, Toast.LENGTH_SHORT).show()
    is TextData.TextStringRes -> Toast.makeText(requireContext, this.resId, Toast.LENGTH_SHORT).show()
  }
}