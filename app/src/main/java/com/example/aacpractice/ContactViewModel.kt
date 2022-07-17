package com.example.aacpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ContactRepository(application)
    private val contacts = repository.getAllContacts()

    init {
        val item = Contact(1, "wonyoung", "010-6395-3275", 'k')
        insert(item)
    }

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}