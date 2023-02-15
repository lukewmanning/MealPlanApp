package com.example.mealplanapplication.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mealplanapplication.MealFragment
import com.example.mealplanapplication.WeightFragment


class TabAdapter (activity : AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(i: Int): Fragment {

        when (i) {
            0 -> return MealFragment()
            1 -> return WeightFragment()
        }
        return MealFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}