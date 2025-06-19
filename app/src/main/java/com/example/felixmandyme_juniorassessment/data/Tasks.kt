package com.example.felixmandyme_juniorassessment.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val complete: Boolean = false
)
