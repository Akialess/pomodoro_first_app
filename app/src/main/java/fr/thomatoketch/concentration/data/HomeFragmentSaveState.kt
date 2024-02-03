package fr.thomatoketch.concentration.data

data class HomeFragmentSaveState (
    var activityButton: Boolean,
    var taskActivity: String,
    var startButton: Boolean,
    var pauseButton: Boolean,
    var giveUpButton: Boolean,
    var restartButton: Boolean,
    var currentTask: Task?
)

