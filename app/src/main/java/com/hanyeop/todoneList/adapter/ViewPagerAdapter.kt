package com.hanyeop.todoneList.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hanyeop.todoneList.ui.fragment.CalendarFragment
import com.hanyeop.todoneList.ui.fragment.DoneListFragment
import com.hanyeop.todoneList.ui.fragment.TodoListFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TodoListFragment()
            1 -> CalendarFragment()
            else -> DoneListFragment()
        }
    }
}