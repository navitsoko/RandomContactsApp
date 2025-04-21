package com.example.contacts

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.contacts.databinding.ContactDetailFragmentBinding
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ContactDetailFragment : Fragment() {
    private lateinit var binding: ContactDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.i(LOG_TAG, "onCreateView DetailFragment")
        binding = ContactDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(     // lifecycle method in Android Fragments
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(LOG_TAG, "onViewCreated DetailFragment")
        initView()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        val contact = arguments?.getParcelable<Contact>("contact")?: return
        val contactNameTextView: TextView = binding.contactName
        val contactBirthdayTextView: TextView = binding.birthdayCountdown

        val name = contact.name
        val birthday = contact.birthday   // Format : "1992-03-08T15:13:16.688Z"
        val image = contact.image

        val daysUntilBirthday = calcDaysUntilBirthday(birthday).toString()

        contactNameTextView.text = name
        contactBirthdayTextView.text = "$daysUntilBirthday Days Until Birthday"
        Glide
            .with(requireContext())
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.ic_emotions)
            .into(binding.contactDetailImage)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calcDaysUntilBirthday(birthday: String?) : Int {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val birthdayDateTime = OffsetDateTime.parse(birthday, formatter)

        val birthdayDate = LocalDate.of(birthdayDateTime.year, birthdayDateTime.month, birthdayDateTime.dayOfMonth)
        var nextBirthday = LocalDate.of(today.year, birthdayDate.month, birthdayDate.dayOfMonth)

        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1)
        }
        val daysUntilBirthday = ChronoUnit.DAYS.between(today, nextBirthday).toInt()
        return daysUntilBirthday
    }

}