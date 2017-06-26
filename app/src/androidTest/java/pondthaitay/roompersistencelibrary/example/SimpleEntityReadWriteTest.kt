package pondthaitay.roompersistencelibrary.example

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private var studentDao: StudentDao? = null
    private var database: AppDatabase? = null

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        studentDao = database!!.studentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeStudentAndReadInList() {
        val student = StudentEntity()
        student.firstName = "test"
        student.email = "test@test.com"
        studentDao!!.insertStudent(student)

        val testSubscribe = studentDao!!.getStudentByEmail(student.email!!).test()
        testSubscribe.assertSubscribed()
        testSubscribe.assertNoErrors()
        testSubscribe.assertValue {
            return@assertValue it.email == student.email
        }
    }
}