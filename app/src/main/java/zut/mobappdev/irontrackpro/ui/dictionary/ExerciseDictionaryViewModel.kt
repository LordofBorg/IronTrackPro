package zut.mobappdev.irontrackpro.ui.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zut.mobappdev.irontrackpro.domain.model.Exercise
import javax.inject.Inject

data class ExerciseDictionaryUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val exercises: List<Exercise> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class ExerciseDictionaryViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ExerciseDictionaryUiState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    private val allMockExercises = listOf(
        Exercise(
            id = "0001",
            name = "Bench Press (Barbell)",
            targetMuscle = "Pectorals",
            equipment = "Barbell",
            gifUrl = "https://api.exercisedb.io/image/0001.gif"
        ),
        Exercise(
            id = "0002",
            name = "Incline Dumbbell Press",
            targetMuscle = "Upper Pectorals",
            equipment = "Dumbbell",
            gifUrl = "https://api.exercisedb.io/image/0002.gif"
        ),
        Exercise(
            id = "0003",
            name = "Cable Fly",
            targetMuscle = "Pectorals",
            equipment = "Cable",
            gifUrl = "https://api.exercisedb.io/image/0003.gif"
        ),
        Exercise(
            id = "0004",
            name = "Deadlift (Barbell)",
            targetMuscle = "Glutes",
            equipment = "Barbell",
            gifUrl = "https://api.exercisedb.io/image/0004.gif"
        ),
        Exercise(
            id = "0005",
            name = "Lat Pulldown",
            targetMuscle = "Lats",
            equipment = "Cable",
            gifUrl = "https://api.exercisedb.io/image/0005.gif"
        ),
        Exercise(
            id = "0006",
            name = "Barbell Bicep Curl",
            targetMuscle = "Biceps",
            equipment = "Barbell",
            gifUrl = "https://api.exercisedb.io/image/0006.gif"
        ),
        Exercise(
            id = "0007",
            name = "Triceps Pushdown",
            targetMuscle = "Triceps",
            equipment = "Cable",
            gifUrl = "https://api.exercisedb.io/image/0007.gif"
        ),
        Exercise(
            id = "0008",
            name = "Squat (Barbell)",
            targetMuscle = "Quadriceps",
            equipment = "Barbell",
            gifUrl = "https://api.exercisedb.io/image/0008.gif"
        ),
        Exercise(
            id = "0009",
            name = "Pull-ups",
            targetMuscle = "Lats",
            equipment = "Body weight",
            gifUrl = "https://api.exercisedb.io/image/0009.gif"
        ),
        Exercise(
            id = "0010",
            name = "Shoulder Press (Dumbbell)",
            targetMuscle = "Deltoids",
            equipment = "Dumbbell",
            gifUrl = "https://api.exercisedb.io/image/0010.gif"
        )
    )

    init {
        loadAllExercises()
    }

    private fun loadAllExercises() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                delay(1000)
                _state.update { it.copy(isLoading = false, exercises = allMockExercises) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }

    fun searchExercises(query: String) {
        _state.update { it.copy(query = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            delay(500) // Debounce
            try {
                val filtered = if (query.isBlank()) {
                    allMockExercises
                } else {
                    allMockExercises.filter { exercise ->
                        exercise.name.contains(query, ignoreCase = true) ||
                                exercise.targetMuscle.contains(query, ignoreCase = true) ||
                                exercise.equipment.contains(query, ignoreCase = true)
                    }
                }
                _state.update { it.copy(isLoading = false, exercises = filtered) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }
}