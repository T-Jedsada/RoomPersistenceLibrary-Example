package pondthaitay.roompersistencelibrary.example

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import pondthaitay.roompersistencelibrary.example.persistence.StudentEntity

@RunWith(JUnit4::class)
class MainViewModelTest {
    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockStudentDataSource = mock<StudentDataSource> {}

    private val mainViewModel = MainViewModel(mockStudentDataSource)

    private val mockObserver = mock<Observer<List<StudentEntity>>> {}

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
        testSubscribe.assertComplete()
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
    fun getStudentAll() {
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

    @Test
    fun getStudentAllLiveData() {
        val studentTest = StudentEntity()
        studentTest.firstName = "test"
        val listStudent = MutableLiveData<List<StudentEntity>>()
        listStudent.value = arrayListOf(studentTest)
        whenever(mockStudentDataSource.getStudentAllLiveData()).thenReturn(listStudent)
        mainViewModel.getStudentAllLiveDta().observeForever(mockObserver)
        verify(mockObserver, times(1)).onChanged(listStudent.value)
    }
}