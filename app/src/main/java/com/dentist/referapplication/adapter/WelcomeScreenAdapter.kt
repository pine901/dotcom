package com.dentist.referapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dentist.referapplication.R


class WelcomeScreenAdapter(context: Context) : PagerAdapter() {
    private val context: Context
    private var layoutInflater: LayoutInflater? = null


    private val images = arrayOf<Int>(
        R.drawable.welcome_scren_1,
        R.drawable.welcome_screen_2,
        R.drawable.welcom_screen_3
    )
    private val headings = arrayOf(  R.string.welcome_1,
        R.string.welcome_2,
        R.string.welcome_3 )




    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater?
        val view: View = layoutInflater!!.inflate(R.layout.welcome_adapter, null)
        val imgDR: ImageView = view.findViewById(R.id.imgDR)
        val txtWelcome: TextView = view.findViewById(R.id.txtWelcome)
        txtWelcome.setText(headings[position])
        imgDR.setImageResource(images[position])
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view: View = `object` as View
        viewPager.removeView(view)
    }

    init {
        this.context = context
    }
}