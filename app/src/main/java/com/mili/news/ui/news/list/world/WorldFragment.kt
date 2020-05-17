package com.mili.news.ui.news.list.world

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
import com.mili.news.databinding.WorldFragmentBinding
import com.mili.news.ui.news.list.NewsAdapter
import com.mili.news.ui.news.list.NewsSharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [WorldFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldFragment : Fragment() {

    private lateinit var binding: WorldFragmentBinding
    private lateinit var viewModel: NewsSharedViewModel
    //private val adapter = NewsAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.world_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* InternetStatus(requireContext()).observe(viewLifecycleOwner, Observer { status ->
            viewModel.getNewsArticles(GlobalConstants.CATEGORY_BUSINESS, status).observe(
                viewLifecycleOwner, Observer {
                    when (it) {
                        is ViewState.Success -> {
                            adapter.submitList(it.data)
                        }
                        is ViewState.Loading -> {

                        }
                        is ViewState.Error -> {

                        }
                    }
                })
        })
*/
    }
}
