package com.dentist.referapplication.Activities

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.dentist.referapplication.R
import com.example.farmfooddeliveryapp.utils.common.Constant
import java.util.*

class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       /* val appLocale: LocaleListCompat =
            LocaleListCompat.forLanguageTags("iw")
// Call this on the main thread as it may require Activity.restart()

        AppCompatDelegate.setApplicationLocales(appLocale)*/




        Handler().postDelayed({

            if (sharePref.getString(Constant.TOKEN, "").equals("")) {
                passIntentWithFinish(this, WelcomeActivity::class.java, "")

            } else {
                passIntentWithFinish(this, MainActivity::class.java, "")

            }

        }, 2000)

    }




}