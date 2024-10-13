package com.jargente.randomuser.ui.contacts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jargente.randomuser.models.Contact
import com.jargente.randomuser.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    private var currentPage = 1
    private var isLoading = false

    init {
        fetchContacts()
    }

    fun fetchContacts() {
        if (isLoading) return

        viewModelScope.launch {
            try {
                val newContacts = ApiService.getContacts(currentPage)
                val currentContacts = _contacts.value
                val updatedContacts = (currentContacts + newContacts).distinctBy { it.id }

                _contacts.value = updatedContacts
                currentPage++
            } catch (e: Exception) {
                Log.e("API_error", e.toString())
            } finally {
                isLoading = false
            }
        }
    }
}