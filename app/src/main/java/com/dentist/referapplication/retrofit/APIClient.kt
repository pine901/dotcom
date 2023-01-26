package com.kare.support.retrofit


import android.annotation.SuppressLint
import android.content.Context
import com.example.farmfooddeliveryapp.utils.common.Constant
import com.example.farmfooddeliveryapp.utils.common.SharedPref
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@SuppressLint("StaticFieldLeak")
object APIClient {
    var context: Context? = null
    var sharePref = SharedPref
    private var retrofit: Retrofit? = null

    fun init(context: Context) {
        this.context=context
    }

    val client: Retrofit?
        get() {

            sharePref.init(context!!)



            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .addNetworkInterceptor(header!!)
                .connectTimeout(50, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(50, TimeUnit.MINUTES) // write timeout
                .readTimeout(50, TimeUnit.MINUTES)

                .build()

            val gson = GsonBuilder().setLenient().create()


            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASEURL)

                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)

                .build()
            return retrofit
        }


    private var header: Interceptor? = Interceptor { chain ->

        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${sharePref.getString(Constant.TOKEN, "").toString()}")
            .build()


        /*      val versionName: String = BuildConfig.VERSION_NAME


              val builder: Request.Builder = chain.request().newBuilder()
              builder.addHeader("Accept", "application/json")
              builder.addHeader("Content-Type", "application/json")
              builder.addHeader("device_type", "Android")
              builder.addHeader("appVersion", versionName)
              chain.proceed(builder.build())*/
        chain.proceed(newRequest)

    }


}