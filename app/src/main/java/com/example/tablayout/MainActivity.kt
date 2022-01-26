package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2: ViewPager2 = binding.viewPager
        viewPager2.adapter = OrdersPagerAdapter(this)

        val tabLayout: TabLayout = binding.tabLayout
        val tabLayoutMeditor: TabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2, object: TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when(position) {
                    0 -> {
                        tab.text = "Pending"
                        tab.setIcon(R.drawable.ic_pending)
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor = ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.setVisible(true)
                    }
                    1 -> {
                        tab.text = "Cofirmed"
                        tab.setIcon(R.drawable.ic_confirmed)
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor = ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.number = 8
                        badgeDrawable.setVisible(true)
                    }
                    2 -> {
                        tab.text = "Delivered"
                        tab.setIcon(R.drawable.ic_delivered)
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor = ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.setVisible(true)
                        badgeDrawable.number = 100
                        badgeDrawable.maxCharacterCount = 3
                    }
                }
            }
        })
        tabLayoutMeditor.attach()

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val badgeDrawable = tabLayout.getTabAt(position)?.orCreateBadge
                badgeDrawable?.setVisible(true)
            }
        })
    }
}