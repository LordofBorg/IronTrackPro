# Iron Track Pro

## Description
Iron Track Pro is a Cloud-Synced Fitness Assistant. It allows users to log workouts, track progress with interactive charts, discover exercises via the ExerciseDB API, and find nearby gyms using Google Maps. The app supports offline caching and syncs data to Firebase when the connection is restored.

## Complexity Level
**Advanced** (Integrates Firebase, Google Maps SDK, Room, Retrofit, and Hilt).

## Build Steps
To build and run this project locally, you need to provide your own ExerciseDB API key.
1. Get an API key from [ExerciseDB on RapidAPI](https://rapidapi.com/justin-WFnsXH_t6/api/exercisedb).
2. Create a file named `local.properties` in the root directory of the project.
3. Add the following line to the file (replace with your actual key, no quotes):
   `EXERCISE_DB_API_KEY=your_actual_api_key_here`
4. Sync the project with Gradle files and run the app.

## Screenshots
*(Screenshots will be added in future updates)*