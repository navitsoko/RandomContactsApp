package com.example.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//To create communication between the room database and the view we need viewModel
class ContactViewModel(private val repository: ContactRepository): ViewModel() {

   val allContacts: Flow<List<Contact>> = repository.allContacts

    fun fetchAndSaveRandomContact() {
        viewModelScope.launch {
            val contacts = repository.fetchAndSaveRandomContact()
            if (contacts != emptyList<Contact>().toMutableList()) {
                repository.deleteAll()
                repository.insert(contacts)
            }
        }
    }
}
//initializes the viewModel
//ensures that ContactViewModel is created with necessary repository parameter
class ContactViewModelFactory(private val repository: ContactRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
            @Suppress ("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        else{
            throw  IllegalArgumentException("Unknown ViewModel class")
        }
    }
}