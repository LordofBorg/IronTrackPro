package zut.mobappdev.irontrackpro.ui.workout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
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
fun WorkoutLoggerScreen(modifier: Modifier = Modifier,
                        onAddExtraExerciseClick: () -> Unit = {},
                        onFinishWorkoutClick: () -> Unit = {},
                        onBackClick: () -> Unit = {}
                        ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Log: Back & Biceps", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onFinishWorkoutClick) {
                        Icon(Icons.Default.Check, contentDescription = "Save")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Вправа 1 зі спліту
            ExerciseCard(
                exerciseName = "Pull-ups",
                sets = listOf(Pair("Bodyweight", "8"), Pair("Bodyweight", "7"))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Вправа 2 зі спліту
            ExerciseCard(
                exerciseName = "Barbell Bicep Curls",
                sets = listOf(Pair("30", "10"), Pair("30", "8"))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка для позапланових вправ
            TextButton(
                onClick = onAddExtraExerciseClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Extra")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Add Extra Exercise")
            }

            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // Секція добавок
            Text("Supplements", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = true, onCheckedChange = {})
                Text("Creatine (5g)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка завершення
            Button(
                onClick = onFinishWorkoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Finish Workout", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Допоміжний компонент для картки вправи
@Composable
fun ExerciseCard(exerciseName: String, sets: List<Pair<String, String>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = exerciseName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                IconButton(onClick = { /* Видалити вправу */ }, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Clear, contentDescription = "Remove")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Заголовки колонок
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Set", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                Text("kg", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Reps", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Рендер підходів
            sets.forEachIndexed { index, set ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${index + 1}", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                    OutlinedTextField(
                        value = set.first,
                        onValueChange = {},
                        modifier = Modifier.weight(1f).padding(end = 8.dp).height(50.dp)
                    )
                    OutlinedTextField(
                        value = set.second,
                        onValueChange = {},
                        modifier = Modifier.weight(1f).height(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { /* Додати підхід */ }) {
                Text("+ Add Set")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WorkoutLoggerScreenPreview() {
    IronTrackProTheme {
        WorkoutLoggerScreen()
    }
}