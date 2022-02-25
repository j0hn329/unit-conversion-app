package uk.ac.stir.cs.unit_converter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

internal class pagerAdapter(
    fragmentManager: FragmentManager?, private val mNumOfTabs:
    Int
) : FragmentStatePagerAdapter(
    fragmentManager!!,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Page1Fragment()
            1 -> Page2Fragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}