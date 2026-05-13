package zut.mobappdev.irontrackpro.ui.workout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import zut.mobappdev.irontrackpro.domain.model.WorkoutSet
import zut.mobappdev.irontrackpro.ui.theme.IronTrackProTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutLoggerScreen(
    modifier: Modifier = Modifier,
    onAddExtraExerciseClick: () -> Unit = {},
    onFinishWorkoutClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (state.workoutTitle.isNotBlank()) "Log: ${state.workoutTitle}" else "Log Workout",
                        fontWeight = FontWeight.Bold
                    )
                },
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
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.error ?: "Unknown error",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadWorkout() }) {
                            Text("Retry")
                        }
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item { Spacer(modifier = Modifier.height(0.dp)) }

                        itemsIndexed(state.exercises) { exerciseIndex, entry ->
                            ExerciseCard(
                                exerciseName = entry.exerciseName,
                                sets = entry.sets,
                                onAddSet = { viewModel.addSet(exerciseIndex) },
                                onRemoveExercise = { viewModel.removeExercise(exerciseIndex) },
                                onUpdateSet = { setIndex, weight, reps ->
                                    viewModel.updateSet(exerciseIndex, setIndex, weight, reps)
                                }
                            )
                        }

                        // Add Extra Exercise button
                        item {
                            TextButton(
                                onClick = onAddExtraExerciseClick,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add Extra")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Add Extra Exercise")
                            }
                        }

                        // Supplements section
                        item {
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Supplements", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = state.supplementsTaken.contains("Creatine (5g)"),
                                    onCheckedChange = { viewModel.toggleSupplement("Creatine (5g)") }
                                )
                                Text("Creatine (5g)")
                            }
                        }

                        // Finish Workout button
                        item {
                            Button(
                                onClick = onFinishWorkoutClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text("Finish Workout", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

// Helper component for exercise card
@Composable
fun ExerciseCard(
    exerciseName: String,
    sets: List<WorkoutSet>,
    onAddSet: () -> Unit = {},
    onRemoveExercise: () -> Unit = {},
    onUpdateSet: (setIndex: Int, weightKg: Double, reps: Int) -> Unit = { _, _, _ -> }
) {
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
                IconButton(onClick = onRemoveExercise, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Clear, contentDescription = "Remove")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Column headers
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Set", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                Text("kg", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Reps", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Render sets
            sets.forEachIndexed { index, set ->
                val weightText = if (set.weightKg == 0.0) "Bodyweight" else set.weightKg.toInt().toString()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${index + 1}", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                    OutlinedTextField(
                        value = weightText,
                        onValueChange = { newValue ->
                            val weight = newValue.toDoubleOrNull() ?: 0.0
                            onUpdateSet(index, weight, set.reps)
                        },
                        modifier = Modifier.weight(1f).padding(end = 8.dp).height(50.dp)
                    )
                    OutlinedTextField(
                        value = set.reps.toString(),
                        onValueChange = { newValue ->
                            val reps = newValue.toIntOrNull() ?: 0
                            onUpdateSet(index, set.weightKg, reps)
                        },
                        modifier = Modifier.weight(1f).height(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onAddSet) {
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