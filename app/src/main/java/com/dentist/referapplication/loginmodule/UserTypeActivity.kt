package com.dentist.referapplication.loginmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dentist.referapplication.Activities.BaseActivity
import com.dentist.referapplication.R
import com.dentist.referapplication.sidemenuscreens.ViewContractActivity
import kotlinx.android.synthetic.main.activity_user_type.*

class UserTypeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)

        setClicks()

    }

    private fun setClicks() {
        relativeBusiness.setOnClickListener {


            passIntentToNextActivity(this,SigninActivity::class.java,"")

        }
        relativeRecommend.setOnClickListener {


//            passIntentToNextActivity(this,SigninActivity::class.java,"")
            passIntentToNextActivity(this,ViewContractActivity::class.java,"")

        }
    }
}