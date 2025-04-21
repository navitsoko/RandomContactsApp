package com.example.contacts

import android.util.Log
import kotlinx.coroutines.flow.Flow



//storing all the data
class ContactRepository(private val contactDao: ContactDao
, private val api: RandomContactApi
) {

    val allContacts: Flow<List<Contact>> = contactDao.getAllContacts()

    suspend fun insert(contacts: List<Contact>){
        contactDao.insertAll(contacts)
    }

    suspend fun deleteAll(){
        contactDao.deleteAll()
    }

    suspend fun fetchAndSaveRandomContact() : MutableList<Contact> {
        try {
            val response = api.getRandomContact()
            val contactResponse = response.body()

            if (contactResponse != null) {
                val contacts = contactResponse.results.map {  user ->
                    convertToContact(user)
                } .toMutableList()
                for (user in contactResponse.results){
                    Log.d("ContactRepository", " users : ${user.name}")
                }
                return contacts
            }
        }
        catch (e: Exception) {
            Log.e("ContactRepository", "Error fetching contact", e)
        }
        val emptyContact = emptyList<Contact>()
        return emptyContact.toMutableList()
    }


    private fun convertToContact(user : ApiContact) : Contact {
        val name = user.name.first + " " + user.name.last
        val email = user.email
        val birthday = user.dob.date
        val image = user.picture.medium
        val contact = Contact(
            image = image,
            name = name,
            email = email,
            birthday = birthday
            )
        return contact
   }

}
