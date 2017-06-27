package pondthaitay.roompersistencelibrary.example

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import pondthaitay.roompersistencelibrary.example.persistence.StudentEntity

class MainViewModelTest {
    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockStudentDataSource = mock<StudentDataSource> {}

    private val mainViewModel = MainViewModel(mockStudentDataSource)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    @Throws(Exception::class)
    fun insertStudent() {
        val studentTest = StudentEntity()
        studentTest.firstName = "test"
        val testSubscribe = mainViewModel.insertStudent(studentTest).test()
        testSubscribe.assertSubscribed()
        testSubscribe.assertNoErrors()
        testSubscribe.assertComplete()
    }

    @Test
    @Throws(Exception::class)
    fun updateStudent() {
        val studentTest = StudentEntity()
        val testSubscribe = mainViewModel.updateStudent(studentTest).test()
        testSubscribe.assertSubscribed()
        testSubscribe.assertNoErrors()
    }

    @Test
    @Throws(Exception::class)
    fun deleteStudent() {
        val studentTest = StudentEntity()
        val testSubscribe = mainViewModel.deleteStudent(studentTest).test()
        testSubscribe.assertSubscribed()
        testSubscribe.assertNoErrors()
        testSubscribe.assertComplete()
    }

    @Test
    @Throws(Exception::class)
    fun getStudent() {
        val studentTest = StudentEntity()
        studentTest.firstName = "test"
        whenever(mockStudentDataSource.getStudentAll())
                .thenReturn(Flowable.just(arrayListOf(studentTest)))
        val testSubscribe = mainViewModel.getStudentAll().test()
        testSubscribe.assertSubscribed()
        testSubscribe.assertNoErrors()
        testSubscribe.assertComplete()
        testSubscribe.assertValue {
            assertThat(it.size, `is`(1))
            return@assertValue it[0].firstName == "test"
        }
    }
}