package pondthaitay.roompersistencelibrary.example

import io.reactivex.Flowable
import pondthaitay.roompersistencelibrary.example.persistence.StudentEntity

interface StudentDataSource {
    fun insertStudent(studentEntity: StudentEntity)

    fun updateStudent(studentEntity: StudentEntity)

    fun deleteStudent(studentEntity: StudentEntity)

    fun getStudentAll(): Flowable<List<StudentEntity>>

    fun getStudentByCode(studentCode: Int): Flowable<List<StudentEntity>>

    fun getStudentByEmail(email: String): Flowable<StudentEntity>

    fun deleteTable()
}
