package com.jargente.randomuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jargente.randomuser.ui.contacts.ContactListScreen
import com.jargente.randomuser.ui.contacts.ContactViewModel
import com.jargente.randomuser.ui.details.DetailScreen
import com.jargente.randomuser.ui.theme.RandomUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val contactViewModel: ContactViewModel = viewModel()

            RandomUserTheme {
                NavHost(navController = navController, startDestination = "contacts") {

                    composable("contacts") {
                        ContactListScreen(
                            navController = navController,
                            contactViewModel = contactViewModel
                        )
                    }

                    composable(
                        "details/{contactId}",
                        arguments = listOf(navArgument("contactId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val contactId = backStackEntry.arguments?.getString("contactId") ?: ""
                        DetailScreen(
                            contactId = contactId,
                            navController = navController,
                            contactViewModel = contactViewModel
                        )
                    }
                }
            }
        }
    }
}