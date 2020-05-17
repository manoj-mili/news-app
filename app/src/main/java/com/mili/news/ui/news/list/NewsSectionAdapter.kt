package com.mili.news.ui.news.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mili.news.ui.news.list.business.BusinessFragment
import com.mili.news.ui.news.list.entertainment.EntertainmentFragment
import com.mili.news.ui.news.list.health.HealthFragment
import com.mili.news.ui.news.list.science.ScienceFragment
import com.mili.news.ui.news.list.sports.SportsFragment
import com.mili.news.ui.news.list.technology.TechnologyFragment
import com.mili.news.ui.news.list.top.TopHeadlinesFragment

class NewsSectionAdapter(fa: FragmentActivity, private val viewModel: NewsSharedViewModel) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
            return when (position) {
            0 -> TopHeadlinesFragment(viewModel)
            1 -> BusinessFragment(viewModel)
            2 -> SportsFragment(viewModel)
            3 -> EntertainmentFragment(viewModel)
            4 -> HealthFragment(viewModel)
            5 -> ScienceFragment(viewModel)
            6 -> TechnologyFragment(viewModel)
            else -> TopHeadlinesFragment(viewModel)
        }
    }

}