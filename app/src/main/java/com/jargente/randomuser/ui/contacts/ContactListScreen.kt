package com.jargente.randomuser.ui.contacts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jargente.randomuser.R
import com.jargente.randomuser.ui.components.ContactCard
import com.jargente.randomuser.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    navController: NavController,
    contactViewModel: ContactViewModel = viewModel()
) {
    val contacts by contactViewModel.contacts.collectAsState()

    Scaffold(topBar = {
        TopBar(
            navController,
            stringResource(id = R.string.top_bar_text),
            colors = TopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black
            )
        )
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
        {
            items(contacts.size) { index ->
                val contact = contacts[index]
                ContactCard(contact = contact, onClick = {
                    navController.navigate("details/${contact.id}")
                })

                if (index >= contacts.size - 3) {
                    contactViewModel.fetchContacts()
                }
            }
        }
    }
}