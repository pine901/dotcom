package com.dentist.referapplication.loginmodule

import android.os.Bundle
import com.dentist.referapplication.Activities.BaseActivity
import com.dentist.referapplication.R
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        setClicks()


    }

    private fun setClicks() {

        txtNoAccount.setOnClickListener {
            passIntentToNextActivity(this, SignupWithPhoneActivity::class.java, "")

        }
    }
}