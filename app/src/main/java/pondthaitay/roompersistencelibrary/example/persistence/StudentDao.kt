package pondthaitay.roompersistencelibrary.example.persistence

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@android.arch.persistence.room.Dao
interface StudentDao {
    @Insert
    fun insertStudent(studentEntity: StudentEntity)

    @Update()
    fun updateStudent(studentEntity: StudentEntity)

    @Delete
    fun deleteStudent(studentEntity: StudentEntity)

//    @Query("SELECT * FROM student")
//    fun getStudentAll(): Flowable<List<StudentEntity>>

    @Query("SELECT * FROM student")
    fun getStudentAll(): Flowable<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE student.student_code = :arg0")
    fun getStudentByCode(studentCode: Int): Flowable<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE student.email LIKE :arg0")
    fun getStudentByEmail(email: String): Flowable<StudentEntity>

    @Query("DELETE FROM student")
    fun deleteTable()
}