package `in`.ktechnos.smsapp.ui

import `in`.ktechnos.smsapp.R
import `in`.ktechnos.smsapp.adapters.MyRecyclerViewAdapter
import `in`.ktechnos.smsapp.data.local.NoteDatabase
import `in`.ktechnos.smsapp.data.msgDB.Subscriber
import `in`.ktechnos.smsapp.data.msgDB.SubscriberDatabase
import `in`.ktechnos.smsapp.data.msgDB.SubscriberRepository
import `in`.ktechnos.smsapp.viewmodel.MainViewModel
import `in`.ktechnos.smsapp.viewmodel.MyViewModelFactory
import `in`.ktechnos.smsapp.viewmodel.SubscriberViewModel
import `in`.ktechnos.smsapp.viewmodel.SubscriberViewModelFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_message.view.*


class MessageFragment : Fragment() {

    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var mView: View
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = SubscriberDatabase.getInstance(requireContext()).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_message, container, false)
        initRecyclerView()
        return mView
    }

    private fun initRecyclerView(){
        mView.subscriber_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyRecyclerViewAdapter({selectedItem: Subscriber ->listItemClicked(selectedItem)})
        mView.subscriber_recyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(requireActivity(), Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(requireContext(),"selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        //subscriberViewModel.initUpdateAndDelete(subscriber)
    }

}