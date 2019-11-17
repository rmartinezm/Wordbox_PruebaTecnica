package io.arkstud.wordbox_roberto.model.util.glide

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions

fun getDefaultLoaderOptions(context: Context) = RequestOptions()
    .placeholder(CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 30f
        start()
    })
    .fitCenter()