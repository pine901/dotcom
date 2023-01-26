package com.dentist.referapplication.sidemenuscreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.dentist.referapplication.Activities.BaseActivity
import com.dentist.referapplication.R
import kotlinx.android.synthetic.main.activity_term_of_use.*
import kotlinx.android.synthetic.main.activity_view_contract.*
import kotlinx.android.synthetic.main.activity_view_contract.txtWelcome

class TermOfUseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_of_use)

        txtWelcome1.movementMethod = ScrollingMovementMethod()

    }
}