package com.example.contacts

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.contacts.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


const val LOG_TAG = "app_test"
class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding
    internal lateinit var viewModel: ContactViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LOG_TAG, "onCreate mainActivity")

        binding = ActivityMainBinding.inflate(layoutInflater) //fetch the layout
        setContentView(binding.root)

        goToFragment(ContactGridFragment())

        val db = initDB()

        val repository = ContactRepository(db.contactDao(), RetrofitClient.create())

        viewModel = viewModels<ContactViewModel> { ContactViewModelFactory(repository) }.value

        binding.progressBar.visibility = View.VISIBLE
        ifEmptyDatabaseFetchFromServer()        //if database is empty fetch random contact from server

    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_TAG,"onStart MainActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG,"onResume MainActivity")
    }

    private fun initDB(): ContactDatabase {
        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contact-database"
        ).build()
        return db
    }

    private fun goToFragment(fragment: Fragment) {
        fragmentManager = supportFragmentManager   // returns the fragment manager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()  // commit() - commits the changes through the transactions
    }

     private fun ifEmptyDatabaseFetchFromServer() {
        lifecycleScope.launch {
            val isEmpty = viewModel.allContacts.first().isEmpty()
            Log.d("MainActivityViewModel", "Room database is empty: $isEmpty")
            if(isEmpty){
                viewModel.fetchAndSaveRandomContact()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_TAG,"onResume MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LOG_TAG,"onStop MainActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LOG_TAG,"onRestart MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LOG_TAG,"onDestroy MainActivity")
    }
}