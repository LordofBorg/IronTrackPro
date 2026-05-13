package zut.mobappdev.irontrackpro.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zut.mobappdev.irontrackpro.domain.model.WorkoutSet
import javax.inject.Inject

data class WorkoutExerciseEntry(
    val exerciseName: String,
    val sets: List<WorkoutSet>
)

data class WorkoutUiState(
    val isLoading: Boolean = true,
    val workoutTitle: String = "",
    val exercises: List<WorkoutExerciseEntry> = emptyList(),
    val supplementsTaken: List<String> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class WorkoutViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(WorkoutUiState())
    val state = _state.asStateFlow()

    init {
        loadWorkout()
    }

    fun loadWorkout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                delay(1000)
                val mockExercises = listOf(
                    WorkoutExerciseEntry(
                        exerciseName = "Pull-ups",
                        sets = listOf(
                            WorkoutSet(exerciseName = "Pull-ups", weightKg = 0.0, reps = 8),
                            WorkoutSet(exerciseName = "Pull-ups", weightKg = 0.0, reps = 7)
                        )
                    ),
                    WorkoutExerciseEntry(
                        exerciseName = "Barbell Bicep Curls",
                        sets = listOf(
                            WorkoutSet(exerciseName = "Barbell Bicep Curls", weightKg = 30.0, reps = 10),
                            WorkoutSet(exerciseName = "Barbell Bicep Curls", weightKg = 30.0, reps = 8)
                        )
                    )
                )
                _state.update {
                    it.copy(
                        isLoading = false,
                        workoutTitle = "Back & Biceps",
                        exercises = mockExercises,
                        supplementsTaken = listOf("Creatine (5g)")
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }

    fun addSet(exerciseIndex: Int) {
        _state.update { current ->
            val exercises = current.exercises.toMutableList()
            if (exerciseIndex in exercises.indices) {
                val entry = exercises[exerciseIndex]
                val lastSet = entry.sets.lastOrNull()
                val newSet = WorkoutSet(
                    exerciseName = entry.exerciseName,
                    weightKg = lastSet?.weightKg ?: 0.0,
                    reps = lastSet?.reps ?: 0
                )
                exercises[exerciseIndex] = entry.copy(sets = entry.sets + newSet)
            }
            current.copy(exercises = exercises)
        }
    }

    fun removeExercise(exerciseIndex: Int) {
        _state.update { current ->
            val exercises = current.exercises.toMutableList()
            if (exerciseIndex in exercises.indices) {
                exercises.removeAt(exerciseIndex)
            }
            current.copy(exercises = exercises)
        }
    }

    fun updateSet(exerciseIndex: Int, setIndex: Int, weightKg: Double, reps: Int) {
        _state.update { current ->
            val exercises = current.exercises.toMutableList()
            if (exerciseIndex in exercises.indices) {
                val entry = exercises[exerciseIndex]
                val sets = entry.sets.toMutableList()
                if (setIndex in sets.indices) {
                    sets[setIndex] = sets[setIndex].copy(weightKg = weightKg, reps = reps)
                    exercises[exerciseIndex] = entry.copy(sets = sets)
                }
            }
            current.copy(exercises = exercises)
        }
    }

    fun toggleSupplement(supplement: String) {
        _state.update { current ->
            val supplements = current.supplementsTaken.toMutableList()
            if (supplements.contains(supplement)) {
                supplements.remove(supplement)
            } else {
                supplements.add(supplement)
            }
            current.copy(supplementsTaken = supplements)
        }
    }
}