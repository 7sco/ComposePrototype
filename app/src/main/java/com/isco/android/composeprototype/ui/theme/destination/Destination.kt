package com.isco.android.composeprototype.ui.theme.destination

sealed class Destination(val route: String, val name : String) {

    object  Main:Destination("main", "Main")
    object  Ticket:Destination("ticket", "Tickets viewer")

}