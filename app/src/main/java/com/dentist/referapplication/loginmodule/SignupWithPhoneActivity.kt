package com.dentist.referapplication.loginmodule

import android.os.Bundle
import com.dentist.referapplication.Activities.BaseActivity
import com.dentist.referapplication.R
import kotlinx.android.synthetic.main.activity_signup_with_phone.*

class SignupWithPhoneActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_with_phone)
        setClicks()
    }

    private fun setClicks() {
        btnSend.setOnClickListener {

            passIntentToNextActivity(this,EnterOtpActivity::class.java,"")
        }
    }
}