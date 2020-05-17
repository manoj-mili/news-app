package com.mili.news.ui.news.list.technology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.mili.news.R
import com.mili.news.comman.GlobalConstants
import com.mili.news.comman.InternetStatus
import com.mili.news.data.ViewState
import com.mili.news.data.entities.NewsArticle
import com.mili.news.databinding.TechnologyFragmentBinding
import com.mili.news.ui.news.list.NewsAdapter
import com.mili.news.ui.news.list.NewsClickInterface
import com.mili.news.ui.news.list.NewsSharedViewModel

class TechnologyFragment(private val viewModel: NewsSharedViewModel) : Fragment(), NewsClickInterface {
    private lateinit var binding: TechnologyFragmentBinding

    private val adapter = NewsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.technology_fragment, container, false)
        binding.lifecycleOwner = this
        binding.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        InternetStatus(requireContext()).observe(viewLifecycleOwner, Observer { status ->
            viewModel.getNewsArticles(GlobalConstants.CATEGORY_TECH, status).observe(
                viewLifecycleOwner, Observer { result ->
                    when (result) {
                        is ViewState.Success -> {
                            adapter.submitList(result.data)
                        }
                        is ViewState.Loading -> {
                            if(result.data != null && result.data.isNotEmpty()) {
                                adapter.submitList(result.data)
                            }
                        }
                        is ViewState.Error -> {

                        }
                    }
                })
        })
    }

    override fun onNewsClick(news: NewsArticle) {
        viewModel.changeSelectedArticle(news)
    }

}
