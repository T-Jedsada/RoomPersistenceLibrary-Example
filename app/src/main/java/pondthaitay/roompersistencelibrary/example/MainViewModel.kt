package pondthaitay.roompersistencelibrary.example

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableFromAction
import pondthaitay.roompersistencelibrary.example.persistence.StudentEntity

class MainViewModel(private val studentDataSource: StudentDataSource) : ViewModel() {

    fun insertStudent(studentEntity: StudentEntity) = CompletableFromAction(Action {
        studentDataSource.insertStudent(studentEntity)
    })

    fun updateStudent(studentEntity: StudentEntity) = CompletableFromAction(Action {
        studentDataSource.updateStudent(studentEntity)
    })

    fun deleteStudent(studentEntity: StudentEntity) = CompletableFromAction(Action {
        studentDataSource.deleteStudent(studentEntity)
    })

    fun getStudentAll() = studentDataSource.getStudentAll()

    fun getStudentAllLiveDta() = studentDataSource.getStudentAllLiveData()

    class MainViewModelFactory(val studentDataSource: StudentDataSource) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>?): T = MainViewModel(studentDataSource) as T
    }
}