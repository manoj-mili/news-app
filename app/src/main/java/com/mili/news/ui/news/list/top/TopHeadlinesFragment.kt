package com.mili.news.ui.news.list.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mili.news.R
import com.mili.news.comman.GlobalConstants
import com.mili.news.comman.InternetStatus
import com.mili.news.data.ViewState
import com.mili.news.data.entities.NewsArticle
import com.mili.news.databinding.TopHeadlinesFragmentBinding
import com.mili.news.ui.news.list.NewsAdapter
import com.mili.news.ui.news.list.NewsClickInterface
import com.mili.news.ui.news.list.NewsSharedViewModel
import timber.log.Timber

class TopHeadlinesFragment(private val viewModel: NewsSharedViewModel) : Fragment() ,
    NewsClickInterface {

    private lateinit var binding: TopHeadlinesFragmentBinding
    private val adapter = NewsAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.top_headlines_fragment, container, false)
        binding.lifecycleOwner = this
        binding.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InternetStatus(requireContext()).observe(viewLifecycleOwner, Observer { status ->
            viewModel.getNewsArticles(GlobalConstants.CATEGORY_TOP_HEADLINES, status)
                .observe(viewLifecycleOwner, Observer { result ->
                    when (result) {
                        is ViewState.Success -> {
                            adapter.submitList(result.data)
                            binding.pgBar.visibility = GONE
                        }
                        is ViewState.Loading -> {
                            binding.pgBar.visibility = VISIBLE
                            if(result.data != null && result.data.isNotEmpty()) {
                                adapter.submitList(result.data)
                            }
                        }
                        is ViewState.Error -> {
                            Timber.d("Data is error %s",result.message)
                        }
                    }
                })
        })
    }

    override fun onNewsClick(news: NewsArticle) {
        viewModel.changeSelectedArticle(news)
    }

}
