package `in`.ktechnos.smsapp.ui

import `in`.ktechnos.smsapp.R
import `in`.ktechnos.smsapp.adapters.ContactsAdapter
import `in`.ktechnos.smsapp.data.local.NoteDatabase
import `in`.ktechnos.smsapp.viewmodel.MainViewModel
import `in`.ktechnos.smsapp.viewmodel.MyViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_contacts.view.*


class ContactsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mView: View
    private val adapter by lazy { ContactsAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //instance of database
        val noteDatabase = NoteDatabase.getInstance(requireContext())
        val myViewModelFactory = MyViewModelFactory(noteDatabase!!)
        mainViewModel = ViewModelProvider(requireActivity(),myViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_contacts, container, false)

        //submitting data to the adapter which the maps it to recyclerview
        
        mView.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        mainViewModel.notes.observe(requireActivity(), Observer { result ->
            adapter.submitList(result)
            mView.recyclerView.adapter = adapter
        })

        return mView
    }


}