package zut.mobappdev.irontrackpro.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zut.mobappdev.irontrackpro.ui.theme.IronTrackProTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier,
                    onStartWorkoutClick: () -> Unit = {},
                    onNavigateToMap: () -> Unit = {},
                    onNavigateToProgress: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("IronTrack Pro", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onStartWorkoutClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Start Workout")
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { /* Вже тут */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Place, contentDescription = "Map") },
                    label = { Text("Map") },
                    selected = false,
                    onClick = onNavigateToMap
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Progress") },
                    label = { Text("Progress") },
                    selected = false,
                    onClick = onNavigateToProgress
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Recent Workouts (Offline Cached)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Фейковий список тренувань
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Chest & Triceps", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(text = "Yesterday • 1h 15m", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Bench Press: 80kg 4x8\nTriceps Pushdown: 25kg 3x12\nCreatine: 5g")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Back & Biceps", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(text = "April 4 • 1h 05m", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Deadlift: 120kg 3x5\nPull-ups: 3x8\nCreatine: 5g")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    IronTrackProTheme {
        DashboardScreen()
    }
}