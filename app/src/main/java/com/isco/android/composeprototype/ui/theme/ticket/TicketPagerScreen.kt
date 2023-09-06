package com.isco.android.composeprototype.ui.theme.ticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.isco.android.composeprototype.R
import com.isco.android.composeprototype.randomColor
import com.isco.android.composeprototype.ui.theme.ComposePrototypeTheme
import kotlin.math.absoluteValue

private val ticketsList = listOf(
    Ticket(
        eventName = "Artisans Alley",
        ticketNUmber = "E12",
        date = "04 Sep 5:30 PM",
        eventType = "Exhibition",
    ),
    Ticket(
        eventName = "Wellness Retreat",
        ticketNUmber = "G45",
        date = "09 Sep 8:30 AM",
        eventType = "Seminar",
    ),
    Ticket(
        eventName = "Cooking Chefs",
        ticketNUmber = "C34",
        date = "12 Sep 11:00 AM",
        eventType = "Workshop",
    ),
    Ticket(
        eventName = "Page Turner",
        ticketNUmber = "D12",
        date = "18 Sep 4:00 PM",
        eventType = "Book Fair",
    ),
    Ticket(
        eventName = "Dance Fusion",
        ticketNUmber = "B07",
        date = "21 Sep 12:00 PM",
        eventType = "Competition",
    ),
    Ticket(
        eventName = "Home Hub",
        ticketNUmber = "D12",
        date = "21 Sep 12:00 PM",
        eventType = "Expo",
    )
)

private val tickets = mutableListOf<Ticket>().apply {
    addAll(ticketsList)
    addAll(ticketsList)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TicketsPagerScreen(navHostController: NavHostController) {

    var endOff by remember { mutableFloatStateOf(0f) }
    val state = rememberPagerState { tickets.size }
    var color by remember { mutableStateOf(tickets[0].color) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ) {

        Box(modifier = Modifier.weight(1f)) {

            HorizontalPager(state = state, Modifier) {
                val currentPageOffset = (state.currentPage - it) + state.currentPageOffsetFraction
                if (currentPageOffset == 0f) {
                    color = tickets[it].color
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            val endOffset = state.endOffsetForPage(it)
                            endOff = state.endOffsetForPage(it)
                            val origin = Offset(x = size.width, y = 0f)
                            val progress = 1f - endOffset.absoluteValue
                            val center = Offset(x = ((origin.x) * (1 - progress)), y = 0f)
                            val size = Size(width = ((origin.x) * (progress)), height = size.height)

                            drawRect(
                                color = tickets[it].color,
                                size = size,
                                topLeft = center
                            )
                        }
                        .padding(top = 112.dp, bottom = 112.dp)
                ) {

                    Ticket(
                        ticket = tickets[it], modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(8))
                            .background(color)
                    )
                }
            }

            HeaderSection { navHostController.popBackStack() }

            IndicationSection(state.currentPage)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeaderSection(onClick: () -> Unit) {
    Column {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onSurface)
                            .padding(4.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
            ),
        )
        Text(
            text = "Tickets", modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun Ticket(ticket: Ticket, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(bottomStartPercent = 8, bottomEndPercent = 8))
                    .background(MaterialTheme.colorScheme.onSurface)
            ) {

                Text(
                    text = ticket.eventName,
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )

                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(24.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Text(
                            text = ticket.ticketNUmber,
                            color = MaterialTheme.colorScheme.surface,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 64.sp
                            ),
                            modifier = Modifier
                        )
                        Text(
                            text = ticket.date.uppercase(),
                            color = MaterialTheme.colorScheme.surface,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Text(
                        text = ticket.eventType.uppercase(),
                        color = MaterialTheme.colorScheme.surface.copy(.5f),
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(16.dp)
                            .rotate(-90f)
                            .layout90Rotated()
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.bar_code),
                contentDescription = "Bar code",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
private fun BoxScope.IndicationSection(currentPage: Int) {
    Row(
        Modifier
            .fillMaxWidth()
            .align(Alignment.BottomStart)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 84.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(tickets.size) { iteration ->
            val dotColor =
                if (currentPage == iteration) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(dotColor)
                    .size(8.dp)
            )
        }
    }
}

// Reference https://stackoverflow.com/questions/70455757/align-composables-on-all-the-edges-of-the-screen-while-rotated
private fun Modifier.layout90Rotated() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(-placeable.height, (placeable.width - placeable.height) / 2)
        }
    }

@OptIn(ExperimentalFoundationApi::class)
private fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}

@OptIn(ExperimentalFoundationApi::class)
private fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TicketPrev() {
    ComposePrototypeTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            Ticket(
                ticket = Ticket(
                    eventName = "Artisans Alley",
                    ticketNUmber = "E12",
                    date = "04 Sep 5:30 pm",
                    eventType = "Exhibition",
                )
            )
        }
    }
}

data class Ticket(
    val eventName: String,
    val ticketNUmber: String,
    val date: String,
    val eventType: String,
    val color: Color = randomColor(200)
)