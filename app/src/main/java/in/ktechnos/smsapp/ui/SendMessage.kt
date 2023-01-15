package `in`.ktechnos.smsapp.ui

import `in`.ktechnos.smsapp.R
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.activity_send_message.*
import kotlin.random.Random as Random1


@Suppress("DEPRECATION")
class SendMessage : AppCompatActivity() {

    private var contactPhone : String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            contactPhone = bundle.getString(CONTACT_PHONE)
            idEdtPhone.text = contactPhone?.toEditable()
        }

        val i = kotlin.random.Random.nextInt(900000) + 100000

        idEdtMessage.text = "Hi, Your OTP is: $i".toEditable()

        btnSendMessage.setOnClickListener{
            sendSMS(idEdtPhone.text.toString(),idEdtMessage.text.toString())
        }

        //println("Random No: $i")
    }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun sendSMS(phoneNumber:String,message:String){
        val uri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        startActivity(intent)

    }
}