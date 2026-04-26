package zut.mobappdev.irontrackpro.ui.progress

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zut.mobappdev.irontrackpro.ui.theme.IronTrackProTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressChartsScreen(modifier: Modifier = Modifier,
                         onNavigateToHome: () -> Unit = {},
                         onNavigateToMap: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analytics", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = onNavigateToHome
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
                    selected = true,
                    onClick = { /* Вже тут */ }
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
            // Фільтри
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = "Bench Press",
                    onValueChange = {},
                    label = { Text("Exercise") },
                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop") },
                    modifier = Modifier.weight(1f),
                    readOnly = true
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = "1 Month",
                    onValueChange = {},
                    label = { Text("Period") },
                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop") },
                    modifier = Modifier.weight(0.7f),
                    readOnly = true
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Strength Trend",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Повністю переписана картка з графіком (тепер не вилазить)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Сітка графіка (фонові лінії)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 20.dp), // Відступ для тексту "W1", "W2" знизу
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
                        HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
                        HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
                        HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f), thickness = 2.dp)
                    }

                    // Самі стовпчики (стоять рівно на жирній лінії)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ChartBar(value = "65", height = 60)
                        ChartBar(value = "70", height = 80)
                        ChartBar(value = "72.5", height = 100)
                        ChartBar(value = "80", height = 130) // Висоту скориговано, тепер точно влазить
                    }

                    // Підписи тижнів
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("W1", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("W2", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("W3", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("W4", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Оновлена статистика
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("1RM Est.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("92 kg", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
                Card(
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Bodyweight", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondaryContainer)
                        Text("70.5 kg", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondaryContainer)
                    }
                }
            }
        }
    }
}

// Оновлений компонент для стовпчиків (без вбудованого тексту тижня знизу)
@Composable
fun ChartBar(value: String, height: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            modifier = Modifier
                .width(36.dp)
                .height(height.dp),
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
        ) {}
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProgressChartsScreenPreview() {
    IronTrackProTheme {
        ProgressChartsScreen()
    }
}