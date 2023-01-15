package `in`.ktechnos.smsapp.ui

import `in`.ktechnos.smsapp.R
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contact_details.*



class ContactDetails : AppCompatActivity() {

    var currentCId: Long? = null
    var contactName: String? = null
    var contactPhone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            contactName = bundle.getString(CONTACT_NAME)
            contactPhone = bundle.getString(CONTACT_PHONE)

            uName.text = contactName
            uPhone.text = contactPhone

        }

        btnSend.setOnClickListener{
            sendMessage()
        }


    }

    fun generateOTP(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    fun getOTP() {
        val start = 1
        val end = 9

        for (i in 1..6) println(generateOTP(start, end))
    }

    private fun sendMessage(){
        val intent = Intent(this, SendMessage()::class.java)
        intent.putExtra(CONTACT_PHONE,contactPhone)
        startActivity(intent)
    }
}