package pondthaitay.roompersistencelibrary.example.persistence

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import pondthaitay.roompersistencelibrary.example.StudentDataSource

class LocalStudentDataSource(val studentDao: StudentDao) : StudentDataSource {
    override fun getStudentAllLiveData(): LiveData<List<StudentEntity>> =
            studentDao.getStudentAllLiveData()

    override fun insertStudent(studentEntity: StudentEntity) =
            studentDao.insertStudent(studentEntity)

    override fun updateStudent(studentEntity: StudentEntity) =
            studentDao.updateStudent(studentEntity)

    override fun deleteStudent(studentEntity: StudentEntity) =
            studentDao.deleteStudent(studentEntity)

    override fun getStudentAll(): Flowable<List<StudentEntity>> =
            studentDao.getStudentAll()

    override fun getStudentByCode(studentCode: Int): Flowable<List<StudentEntity>> =
            studentDao.getStudentByCode(studentCode)

    override fun getStudentByEmail(email: String): Flowable<StudentEntity> =
            studentDao.getStudentByEmail(email)

    override fun deleteTable() = studentDao.deleteTable()
}