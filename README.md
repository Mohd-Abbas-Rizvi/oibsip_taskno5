<<<<<<<------Explanation:---->>>>
XML Layout (activity_main.xml):
The XML layout defines the visual structure of the stopwatch app. It consists of a LinearLayout containing a TextView for displaying the timer and three buttons (Start, Hold, and Stop/Reset) arranged in a RelativeLayout. The layout attributes control the positioning, styling, and behavior of these UI elements.
### XML Layout (activity_main.xml):

1. **LinearLayout:**
   - Defines the main layout as a vertical `LinearLayout`.
   - Uses `@drawable/bg` as the background.
   - Aligns its content vertically in the center.

2. **TextView (timer):**
   - Displays the timer in the format "00:00:00".
   - Positioned at the center horizontally with a margin from the top.

3. **RelativeLayout:**
   - Contains three buttons: Start (`stbtn`), Hold (`hlbtn`), and Stop/Reset (`stpbtn`).

4. **Start Button (stbtn):**
   - Starts the stopwatch.
   - Initial width of 170dp, positioned at the start with a margin from the top.

5. **Hold Button (hlbtn):**
   - Pauses the stopwatch.
   - Initial width of 170dp, positioned at the end with a margin from the top.
   - Initially disabled.

6. **Stop/Reset Button (stpbtn):**
   - Stops or resets the stopwatch.
   - Takes the full width, placed below the Start button.
   - Initially disabled.

### Kotlin Code (MainActivity.kt):
Certainly! Let's go through the Kotlin code for the stopwatch app with corresponding functions explained:

### Kotlin Code (MainActivity.kt):
1. **Variables:**
   - `isRunning`: Tracks if the stopwatch is running.
   - `timerSeconds`: Tracks the elapsed time in seconds.
   - `handler`: Handles the timer updates on the main thread.
   - `runnable`: Runnable object updating the timer display.

```kotlin
private var isRunning = false
private var timerSeconds = 0
private val handler = Handler(Looper.getMainLooper())
private val runnable = object : Runnable {
    override fun run() {
        timerSeconds++
        val hours = timerSeconds / 3600
        val minutes = (timerSeconds % 3600) / 60
        val seconds = timerSeconds % 60
        val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        binding.timer.text = time
        handler.postDelayed(this, 1000)
    }
}
```

2. **onCreate Method:**
   - Initializes the layout using View Binding.
   - Sets click listeners for Start, Hold, and Stop/Reset buttons.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.stbtn.setOnClickListener {
        startTimer()
    }
    binding.hlbtn.setOnClickListener {
        holdTimer()
    }
    binding.stpbtn.setOnClickListener {
        stopTimer()
    }
}
```

3. **startTimer Function:**
   - Starts the timer if not already running.
   - Updates the timer display every second.
   - Disables the Start button and enables Hold and Stop/Reset buttons.

```kotlin
private fun startTimer() {
    if (!isRunning) {
        handler.postDelayed(runnable, 1000)
        isRunning = true
        binding.stbtn.isEnabled = false
        binding.hlbtn.isEnabled = true
        binding.stpbtn.isEnabled = true
    }
}
```

4. **holdTimer Function:**
   - Pauses the timer, updating UI accordingly.
   - Enables the Start button for resuming.

```kotlin
private fun holdTimer() {
    if (isRunning) {
        handler.removeCallbacks(runnable)
        isRunning = false
        binding.stbtn.isEnabled = true
        binding.stbtn.text = "Resume"
        binding.hlbtn.isEnabled = false
        binding.stpbtn.isEnabled = true
    }
}
```

5. **stopTimer Function:**
   - Calls `holdTimer` to pause the timer.
   - Resets the elapsed time to zero and updates the display.
   - Enables the Start button and disables the Stop/Reset button.

```kotlin
private fun stopTimer() {
    holdTimer()
    timerSeconds = 0
    binding.timer.text = "00:00:00"
    binding.stbtn.isEnabled = true
    binding.stpbtn.isEnabled = false
    binding.stbtn.text = "Start"
}
```

These functions collectively control the stopwatch functionality, managing the timer, and updating the UI based on user interactions.
