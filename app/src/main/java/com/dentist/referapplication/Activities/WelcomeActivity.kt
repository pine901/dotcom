package com.dentist.referapplication.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.dentist.referapplication.R
import com.dentist.referapplication.adapter.WelcomeScreenAdapter
import com.dentist.referapplication.loginmodule.UserTypeActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setViewpager()
    }

    private fun setViewpager() {
        welcome_viewpager.adapter = WelcomeScreenAdapter(this)
        tabView.setupWithViewPager(welcome_viewpager, true)


        handleViewPagerScroll()

        setClicks()
    }


    private fun handleViewPagerScroll() {
        welcome_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setButtons(position)
                Log.e("Rssb pos", position.toString())
            }
        })
    }

    private fun setButtons(position: Int) {


        if (position == 0) {
            txtNext.visibility = View.VISIBLE
            imgBack.visibility = View.GONE
            txtGetStart.visibility = View.GONE
        } else if (position == 1) {
            txtNext.visibility = View.VISIBLE
            imgBack.visibility = View.VISIBLE
            txtGetStart.visibility = View.GONE
        } else if (position == 2) {
            txtNext.visibility = View.GONE
            imgBack.visibility = View.GONE
            txtGetStart.visibility = View.VISIBLE
        }

    }

    private fun setClicks() {

        imgBack.setOnClickListener {
            welcome_viewpager.currentItem = welcome_viewpager.currentItem - 1


        }
        txtNext.setOnClickListener {
            welcome_viewpager.currentItem = welcome_viewpager.currentItem + 1


        }
        txtGetStart.setOnClickListener {

            passIntentWithFinish(this, UserTypeActivity::class.java, "")

        }


    }


}