package pondthaitay.roompersistencelibrary.example

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableFromAction
import pondthaitay.roompersistencelibrary.example.persistence.StudentDao
import pondthaitay.roompersistencelibrary.example.persistence.StudentEntity

class MainViewModel(private val studentDao: StudentDao) : ViewModel() {

    fun insertStudent(studentEntity: StudentEntity) = CompletableFromAction(Action {
        studentDao.insertStudent(studentEntity)
    })

    fun updateStudent(studentEntity: StudentEntity) = CompletableFromAction(Action {
        studentDao.updateStudent(studentEntity)
    })

    fun deleteStudent(studentEntity: StudentEntity) = CompletableFromAction(Action {
        studentDao.deleteStudent(studentEntity)
    })

    fun getStudentAll() = studentDao.getStudentAll()

    fun getStudentAllLiveDta() = studentDao.getStudentAllLiveData()

    fun deleteTableStudent() = studentDao.deleteTable()

    class MainViewModelFactory(private val studentDao: StudentDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>?): T = MainViewModel(studentDao) as T
    }
}