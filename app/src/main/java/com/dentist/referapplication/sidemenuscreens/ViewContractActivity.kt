package com.dentist.referapplication.sidemenuscreens

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.dentist.referapplication.Activities.BaseActivity
import com.dentist.referapplication.R
import kotlinx.android.synthetic.main.activity_view_contract.*


class ViewContractActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contract)

        txtWelcome.movementMethod = ScrollingMovementMethod()

        setClicks()
    }

    private fun setClicks() {
        btnSend.setOnClickListener {
            passIntentToNextActivity(this, TermOfUseActivity::class.java, "")

        }
    }
}