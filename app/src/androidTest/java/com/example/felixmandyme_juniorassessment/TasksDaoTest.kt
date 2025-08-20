package com.example.felixmandyme_juniorassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.felixmandyme_juniorassessment.data.data_source.TasksDao
import com.example.felixmandyme_juniorassessment.data.data_source.TasksDatabase
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TasksDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TasksDatabase
    private lateinit var dao: TasksDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TasksDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.tasksDao()
    }


    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertTask() = runTest {
        val task = Tasks(title = "Adding", description = "Adding to database", complete = false)
        dao.insert(task)

        val getTask = dao.getTask(1).first()
        assertThat(getTask.title).isEqualTo(task.title)
        assertThat(getTask.description).isEqualTo(task.description)
        assertThat(getTask.complete).isEqualTo(task.complete)
    }

    @Test
    fun deleteTask() = runTest {
        val task = Tasks(title = "Adding", description = "Adding to database", complete = false)
        dao.insert(task)
        val insertedTask =  dao.getTask(1).first()
        dao.delete(insertedTask)

        val getTask = dao.getTask(1).first()
        assertThat(getTask).isNull()
    }

    @Test
    fun updateTask() = runTest {
        val task = Tasks(title = "Adding", description = "Adding to database", complete = false)
        val taskUpdate = Tasks(id = 1, title = "Adding", description = "Updating database", complete = false)

        dao.insert(task)
        dao.update(taskUpdate)

        val getTask = dao.getTask(1).first()
        assertThat(getTask.description).isEqualTo(taskUpdate.description)
    }

    @Test
    fun getIncompleteTasks() = runTest {
        val task = Tasks(title = "Adding", description = "Adding to database", complete = false)
        val task2 = Tasks(title = "Adding again", description = "Adding again to database", complete = false)
        val task3 = Tasks(title = "Complete", description = "This task is complete", complete = true)

        dao.insert(task)
        dao.insert(task2)
        dao.insert(task3)

        val getTasks = dao.getAllTasksIncomplete().first()
        assertThat(getTasks).hasSize(2)
    }


    @Test
    fun getCompleteTasks() = runTest {
        val task = Tasks(title = "Adding", description = "Adding to database", complete = false)
        val task2 = Tasks(title = "Adding again", description = "Adding again to database", complete = false)
        val task3 = Tasks(title = "Complete", description = "This task is complete", complete = true)

        dao.insert(task)
        dao.insert(task2)
        dao.insert(task3)

        val getTasks = dao.getAllTasksComplete().first()
        assertThat(getTasks).hasSize(1)
    }
}