package `in`.ktechnos.smsapp.viewmodel

import `in`.ktechnos.smsapp.data.msgDB.Subscriber
import `in`.ktechnos.smsapp.data.msgDB.SubscriberRepository
import `in`.ktechnos.smsapp.utils.Event
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(){

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    val inputPhone = MutableLiveData<String>()
    val inputMessage = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()


    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
    get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Send Message"

    }

    fun saveOrUpdate(pNumber:String,name:String,dateTime:String,uMessage:String){

        insert(Subscriber(0, name, pNumber,uMessage,dateTime))

    }


    private fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
           val newRowId = repository.insert(subscriber)
           withContext(Dispatchers.Main){
               if(newRowId > -1) {
                   statusMessage.value = Event("Subscriber Inserted Successfully! $newRowId")
               }else{
                   statusMessage.value = Event("Error Occurred!")
               }
           }
       }







}