package com.isco.android.composeprototype

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isco.android.composeprototype.ui.theme.destination.Destination
import com.isco.android.composeprototype.ui.theme.ticket.TicketsPagerScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable(Destination.Main.route) { MainScreen(navController) }
        composable(Destination.Ticket.route) { TicketsPagerScreen(navController) }
    }
}
