package com.codexdroid.theguru.controllers.data_models.local

data class Events(val time: Long, val place: String) {
    fun requestTime(eventTime:Long = time): String  {
        val seconds = time / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7

        val remainingDays = weeks % 7
        val remainingHours = hours % 24
        val remainingMinutes = minutes % 60
        val remainingSeconds = seconds % 60

        return if(remainingDays > 0L) "$remainingDays Days"
        else if(remainingHours > 0L) "$remainingHours Hour(s)"
        else if(remainingMinutes > 0L) "$remainingMinutes Minutes"
        else if(remainingSeconds > 0L) "$remainingSeconds Seconds"
        else "00:00:00"

        //return requestTimeInHourMinSec(eventTime)

    }
}
