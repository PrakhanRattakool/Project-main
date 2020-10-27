package com.android.example.project.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.example.project.database.Contact
import com.android.example.project.database.DatabaseDAO
import com.android.example.project.databinding.FragmentAddBinding
import kotlinx.coroutines.*

class AddViewModel(
    private val database: DatabaseDAO,
    private val binding: FragmentAddBinding,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onContactAdd() {
        uiScope.launch {
            val newContact = Contact()
            newContact.nameS = binding.editTextTextPersonName.text.toString()
            newContact.phone = binding.editTextTextPersonPhone.text.toString()
            insert(newContact)
        }
    }

    private suspend fun insert(contact: Contact) {
        withContext(Dispatchers.IO) {
            database.insert(contact)
        }
    }
}