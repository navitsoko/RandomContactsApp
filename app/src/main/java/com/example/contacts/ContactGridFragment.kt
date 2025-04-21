package com.example.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContactGridFragment : Fragment() {
    private lateinit var viewModel: ContactViewModel
    private lateinit var adapter: ContactCardRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.i(LOG_TAG, "onCreateView gridFragment")
        viewModel = (activity as MainActivity).viewModel // casting (Necessary) viewModel is a member of MainActivity.
        val view = inflater.inflate(R.layout.contact_grid_fragment, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        if (savedInstanceState == null) {
            recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {    // lifecycle method in Android Fragments
        super.onViewCreated(view, savedInstanceState)
        Log.i(LOG_TAG, "onViewCreated gridFragment")
        adapter = ContactCardRecyclerViewAdapter(               //Creating the Adapter
            onContactClick = { selectedContact ->
                navigateToContactDetails(selectedContact)
            }
        )

        recyclerView.adapter = adapter
        // Observe the allContacts flow and update the adapter
        lifecycleScope.launch(Dispatchers.Main) {   //emitting a new list of contacts whenever the contact data changes.
            viewModel.allContacts.collect { contacts ->
                adapter.updateContacts(contacts)
            }
        }
        view.findViewById<Button>(R.id.refreshButton).setOnClickListener {
                viewModel.fetchAndSaveRandomContact()
             }
    }

    private fun navigateToContactDetails(contact: Contact) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        // Create a Bundle to pass the contact data to details fragment
        val bundle = Bundle()
        bundle.putParcelable("contact", contact)

        val contactDetailFragment = ContactDetailFragment()
        contactDetailFragment.arguments = bundle
        transaction.replace(R.id.fragmentContainer, contactDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}