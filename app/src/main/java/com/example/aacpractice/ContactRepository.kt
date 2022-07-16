package com.example.aacpractice

import android.app.Application
import androidx.lifecycle.LiveData

class ContactRepository(application: Application) {
    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    fun getAllContacts(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact) {
        try {
            Thread {
                contactDao.insert(contact)
            }.apply { start() }
        } catch (e: Exception) { }
    }

    fun delete(contact: Contact) {
        try {
            Thread {
                contactDao.delete(contact)
            }.apply { start() }
        } catch (e: Exception) { }
    }
}