package com.jargente.randomuser.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jargente.randomuser.R
import com.jargente.randomuser.ui.components.ContactData
import com.jargente.randomuser.ui.components.TopBar
import com.jargente.randomuser.ui.contacts.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    contactId: String,
    navController: NavHostController,
    contactViewModel: ContactViewModel = viewModel()
) {

    val contacts by contactViewModel.contacts.collectAsState()


    if (contacts.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val contact = contacts.find { it.id == contactId }

        if (contact != null) {

            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    painter = painterResource(R.drawable.rectangle),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                TopBar(
                    navController,
                    contact.name,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(8.dp),
                    TopAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 220.dp
                        )
                ) {

                    AsyncImage(
                        model = contact.picture,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ContactData(
                        painterResource(R.drawable.ic_user),
                        "Nombre y apellidos",
                        contact.name
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ContactData(
                        painterResource(R.drawable.ic_mail), "Email", contact.email
                    )
                }
            }
        }
    }
}