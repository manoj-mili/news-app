package com.mili.news.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.mili.news.R
import com.mili.news.data.entities.NewsArticle
import com.mili.news.databinding.DetailNewsDialogBinding

class DetailNewsDialog(private val news: NewsArticle) : DialogFragment() {

    private lateinit var binding: DetailNewsDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_news_dialog, container,false)
        binding.news = news
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}