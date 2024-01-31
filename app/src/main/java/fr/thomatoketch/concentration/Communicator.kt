package fr.thomatoketch.concentration

import fr.thomatoketch.concentration.data.Task

interface Communicator {
    fun passData(task: Task)
}