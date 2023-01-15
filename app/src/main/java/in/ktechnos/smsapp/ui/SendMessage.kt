package `in`.ktechnos.smsapp.ui

import `in`.ktechnos.smsapp.R
import `in`.ktechnos.smsapp.data.msgDB.SubscriberDatabase
import `in`.ktechnos.smsapp.data.msgDB.SubscriberRepository
import `in`.ktechnos.smsapp.databinding.ActivitySendMessageBinding
import `in`.ktechnos.smsapp.viewmodel.SubscriberViewModel
import `in`.ktechnos.smsapp.viewmodel.SubscriberViewModelFactory
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Suppress("DEPRECATION")
class SendMessage : AppCompatActivity() {

    private var contactPhone : String? = null
    private var contactName : String? = null
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var binding: ActivitySendMessageBinding


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_send_message)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_send_message)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            contactPhone = bundle.getString(CONTACT_PHONE)
            contactName = bundle.getString(CONTACT_NAME)
            binding.idEdtPhone.text = contactPhone?.toEditable()

        }

        val i = kotlin.random.Random.nextInt(900000) + 100000

        binding.idEdtMessage.text = "Hi, Your OTP is: $i".toEditable()

        binding.btnSendMessage.setOnClickListener{
            sendSMS(binding.idEdtPhone.text.toString(),binding.idEdtMessage.text.toString())
        }

        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendSMS(phoneNumber:String, message:String){
        val uri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        startActivity(intent)

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)

        println("Current Date is: $formatted")

        binding.myViewModel?.saveOrUpdate(contactPhone?.toEditable().toString(),contactName?.toEditable().toString(),formatted,binding.idEdtMessage.text.toString())

    }

}