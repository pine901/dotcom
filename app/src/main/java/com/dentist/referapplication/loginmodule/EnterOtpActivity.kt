package com.dentist.referapplication.loginmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dentist.referapplication.Activities.BaseActivity
import com.dentist.referapplication.R
import kotlinx.android.synthetic.main.activity_enter_otp.*

class EnterOtpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_otp)
        setClicks()
    }

    private fun setClicks() {
        btnSend.setOnClickListener {

            passIntentToNextActivity(this,SignupNotificationActivity::class.java,"")
        }
    }
}