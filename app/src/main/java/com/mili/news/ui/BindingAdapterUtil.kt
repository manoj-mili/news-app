package com.mili.news.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.request.LoadRequest
import com.google.android.material.appbar.AppBarLayout
import com.mili.news.R
import com.mili.news.comman.TimeUtils.getFormattedTime
import com.mili.news.data.entities.NewsArticle

@BindingAdapter("publishedTime")
fun publishedTime(view: TextView, time: String) {
    view.text =
        getFormattedTime(time)
}

@BindingAdapter("spannableText")
fun spannableText(view: TextView, data: NewsArticle?) {
    data?.let {

        val extraText: String = if(data.content.isNotEmpty()) {
            data.description.plus("\n \n").plus(data.content).plus("   Read More")
        } else
            data.description.plus("   Read More")

        extraText.contains("[+")

        val spannableText = SpannableString(extraText)
        val linkColor = ForegroundColorSpan(Color.parseColor("#00acee"))

        val clickAbleLink = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(data.url)
                widget.context.startActivity(intent)
            }
        }

        spannableText.setSpan(
            clickAbleLink,
            extraText.length - 9,
            extraText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableText.setSpan(
            linkColor,
            extraText.length - 9,
            extraText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.movementMethod = LinkMovementMethod.getInstance()
        view.setText(spannableText)
    }
}


@BindingAdapter("appBarVisibilityAnimation")
fun appBarVisibilityAnimation(appBar: AppBarLayout, news: NewsArticle?) {
    if(news != null) {
        appBar.visibility = VISIBLE
        appBar.setExpanded(true,true)
    } else {
        appBar.visibility = GONE
        appBar.setExpanded(false,true)
    }
}

@BindingAdapter("setVisibleAnimation")
fun setVisibleAnimation(view: CardView, data: Any?) {
    view.animation =
        android.view.animation.AnimationUtils.loadAnimation(view.context, R.anim.fade_scale)
}

@BindingAdapter("imageUrl", "imageProgress", requireAll = false)
fun imageUrl(imageView: ImageView, image: String?, pb: ProgressBar) {
    if (image != null)
        if (image.isNotEmpty()) {
            val request = LoadRequest.Builder(imageView.context)
                .data(image)
                .placeholder(R.drawable.ic_language)
                .target(
                    onStart = {
                        pb.visibility = VISIBLE
                    },
                    onSuccess = { result ->
                        // Handle the successful result.
                        imageView.setImageDrawable(result)
                        pb.visibility = GONE
                    },
                    onError = { error ->
                        // Handle the error drawable.
                        imageView.visibility = GONE
                        pb.visibility = GONE
                    }
                )
                .build()
            Coil.execute(request)
        }
}