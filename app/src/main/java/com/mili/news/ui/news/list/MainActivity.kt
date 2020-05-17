package com.mili.news.ui.news.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.mili.news.R
import com.mili.news.databinding.ActivityMainBinding
import com.mili.news.simpledi.NewsViewModelInjector

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: NewsSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel =
            ViewModelProvider(this, NewsViewModelInjector(application).provideViewModel()).get(
                NewsSharedViewModel::class.java
            )

        binding.pager.adapter = NewsSectionAdapter(this, viewModel)

        TabLayoutMediator(
            binding.tab,
            binding.pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "Top"
                    1 -> "Business"
                    2 -> "Sports"
                    3 -> "Entertainment"
                    4 -> "Health"
                    5 -> "Science"
                    6 -> "Tech"
                    else -> "Top"
                }
            }).attach()

        viewModel.showSelectedNews().observe(this, Observer { article ->
            article?.let {
                val dialog = DetailNewsDialog(article)
                dialog.show(supportFragmentManager,dialog.javaClass.simpleName)
            }
        })
    }
}
