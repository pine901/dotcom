package com.dentist.referapplication.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dentist.referapplication.R
import com.example.farmfooddeliveryapp.utils.common.Constant
import com.example.farmfooddeliveryapp.utils.common.SharedPref
import com.kare.support.retrofit.APIClient
import com.my.dronlineappointment.retrofit.ApiInterface
import com.kare.support.retrofit.ParseApiData
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

import com.sueep.Interfaces.CheckPermissionInterface

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


open class BaseActivity : AppCompatActivity(), CheckPermissionInterface {
    lateinit var currentPhotoPath: String


    var photoFile: File? = null
    val CAPTURE_IMAGE_REQUEST = 1
    var mCurrentPhotoPath: String? = null

    var imageFrom = ""

    var apiService: ApiInterface? = null
    var parseApiData: ParseApiData? = null
    lateinit var pDialog: ProgressDialog
    var resultUri: Uri? = null
    var sharePref = SharedPref
    lateinit var checkPermission: CheckPermissionInterface



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initViews()
        updateResources(this,"iw")

    }




    private fun initViews() {




        checkPermission = this
        APIClient.init(this)
        apiService = APIClient.client!!.create(ApiInterface::class.java)
        parseApiData = ParseApiData()
        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)
        pDialog.setMessage("Loading...")
        sharePref.init(this)

    }

    fun showProgress() {
        pDialog.show()
    }

    fun dismissProgress() {
        pDialog.dismiss()
    }


    fun loadImages(url: String, image_view: ImageView) {
        Log.e("profile_image", ">>>" + url + "?=" + System.currentTimeMillis())
        Glide.with(this)
            .load(url + "?=" + System.currentTimeMillis())
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            /*.placeholder(R.drawable.add_image_placeholder)
            .error(R.drawable.add_image_placeholder)*/
            .skipMemoryCache(true)
            .into(image_view)
    }

    fun ToastMsg(message: String) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target)
            .matches()
    }


    fun replaceFragment(
        fragment: Fragment, frame: Int, addtoStack: Boolean, allowAnim: Boolean,
        bundle: Bundle?
    ) {
        val ft = supportFragmentManager.beginTransaction()

        if (addtoStack) {
            ft.addToBackStack(fragment.javaClass.name)
        }
        if (allowAnim) {
            fragment.arguments = bundle
            ft.replace(frame, fragment)
            ft.commit()
        }
    }

    fun toolbarTitle(title: String) {
        sharePref.saveString(Constant.TITLE, title)
        sharePref.saveString(Constant.SUBTITLE, title)

    }

    fun toolbarDetailTitle(title: String) {
        sharePref.saveString(Constant.SUBTITLE, title)


    }

    fun toolbarBackTitle(title: String) {
    }


    fun requestLocationpermission() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                    if (report.areAllPermissionsGranted()) {
                        checkPermission.OnPermissionAccepted()


                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {

            }
            .onSameThread()
            .check()
    }

    fun requestCamerapermission() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                    if (report.areAllPermissionsGranted()) {
                        checkPermission.OnPermissionAccepted()
                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {

            }
            .onSameThread()
            .check()
    }

    fun requestImagespermission(from: String) {

        imageFrom = from

        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                    if (report.areAllPermissionsGranted()) {
                        checkPermission.OnPermissionAccepted()
/*
                        ImagePicker.with(this@BaseActivity)
                            .crop()
                            .compress(1024)
                            .maxResultSize(
                                1080,
                                1080
                            ).start()*/

                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {

            }
            .onSameThread()
            .check()
    }

    private fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
                openSettings()
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }


    fun getRealPathFromURI(contentURI: Uri): String? {

        val result: String?
        val cursor = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result

    }


    fun startActivites(string: String) {

    }


    fun commonAlertDialog(alertMsg: String, title: String, from: String) {

        val builder1 =
            AlertDialog.Builder(this)
        builder1.setTitle(title)
        builder1.setMessage(alertMsg)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "YES"
        ) { dialog, id ->

            if (from == "logout") {


            } else if (from == "") {

            }
            dialog.dismiss()


        }

        builder1.setNegativeButton(
            "NO"
        ) { dialog, id ->

            dialog.dismiss()


        }


        val alert11 = builder1.create()
        alert11.show()

    }

    fun alertDialog(alertMsg: String) {

        val builder1 =
            AlertDialog.Builder(this)
        builder1.setMessage(alertMsg)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Yes"
        ) { dialog, id ->

            sharePref.clear()
            passIntentWithFinish(this, SplashActivity::class.java, "")

            dialog.dismiss()


        }

        builder1.setNegativeButton(
            "No"
        ) { dialog, id ->

            dialog.dismiss()


        }


        val alert11 = builder1.create()
        alert11.show()

    }



    fun commonAlertDialog(alertMsg: String) {

        val builder1 =
            AlertDialog.Builder(this)
        builder1.setMessage(alertMsg)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id ->


            dialog.dismiss()

        }


        val alert11 = builder1.create()
        alert11.show()

    }


    fun getAddress(lat: Double, lng: Double): String {
        var add: String = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)!!
            if (addresses.isNotEmpty()) {

                val obj: Address = addresses[0]
                /*add = obj.getAddressLine(0)
               add = add + "\n" + obj.adminArea*/

                add = obj.locality + " , " + obj.adminArea
                Log.v("IGA", "Address$add")
                /* txtSearchLocation.setText(add)
                 edtZipBottom.setText(addresses[0].postalCode)
                 edtStateBottom.setText(addresses[0].adminArea)*/

            }

        } catch (e: IOException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return add
    }

    fun hideStatusBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }


    fun passIntentWithFinish(context: Context, className: Class<*>, extra: String) {
        val intent = Intent(context, className)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("extra", extra)
        startActivity(intent)
        finish()
    }

    fun passIntentToNextActivity(context: Context, className: Class<*>, extra: String) {
        val intent = Intent(context, className)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("extra", extra)
        startActivity(intent)
    }

    fun prepareFilePart(
        partName: String,
        fileUri: Uri
    ): MultipartBody.Part {
        val file =
            File(this.getRealPathFromURI(fileUri)!!)
        val requestFile =
            file.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

    open fun encryptCard(textPlain: String): String? {
        return try {
            val plaintext = textPlain.toByteArray()
            val cipher = Cipher.getInstance("AES")
            val myKey = "kindlebitPvtLtd1".toByteArray()
            /*byte[] keyStart = "kindlebitPvtLtd1".getBytes();
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                sr.setSeed(keyStart);
                kgen.init(128, sr); // 192 and 256 bits may not be available
                SecretKey skey = kgen.generateKey();
                byte[] myKey = skey.getEncoded();*/
            val keySpec =
                SecretKeySpec(myKey, "AES")
            cipher.init(Cipher.ENCRYPT_MODE, keySpec)
            val cipherText = cipher.doFinal(plaintext)
            //String s = new String(cipherText, StandardCharsets.UTF_8);
            Base64.encodeToString(cipherText, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("~~~!!~!~!@~!@~", " :: Exception :: " + e.message)
            "Exception"
        }
    }

    fun callIntent(phone: String) {

        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    }

    fun getCropUri(): Uri {
        return resultUri!!
    }

    override fun OnPermissionAccepted() {

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
/*
        val fragment = supportFragmentManager.findFragmentById(R.id.frameProfile)
        val bookingFragment = supportFragmentManager.findFragmentById(R.id.frameDoctorDetail)

        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!

            if (fragment is UpdateProfileFragment) {
                fragment.setCropperResult(uri)
            } else if (bookingFragment is BookAppointmentFragment) {
                bookingFragment.patientImages(uri)
            } else {

                if (imageFrom.equals("from_idProof")) {
                    (this as RegisterActivity).idProofImage(uri)

                } else {
                    (this as RegisterActivity).setCropperResult(uri)

                }


            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun updateResources(context: Context, language: String): Boolean {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.getResources()
        val configuration: Configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        return true
    }

}



