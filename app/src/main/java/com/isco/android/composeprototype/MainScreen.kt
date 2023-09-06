package com.isco.android.composeprototype

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.isco.android.composeprototype.ui.theme.destination.Destination

private val menuList = listOf(Destination.Ticket)


@Composable
fun MainScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        menuList.forEach {
            Button(
                onClick = { navHostController.navigate(it.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = it.name)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}