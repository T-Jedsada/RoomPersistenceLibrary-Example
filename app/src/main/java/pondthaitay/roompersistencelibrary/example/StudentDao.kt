package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(studentEntity: StudentEntity)

    @Delete
    fun deleteStudent(studentEntity: StudentEntity)

    @Query("SELECT * FROM student")
    fun getStudentAll(): Flowable<MutableList<StudentEntity>>

    @Query("SELECT * FROM student WHERE student.student_code = :arg0")
    fun getEmailsForUser(studentCode: Int): Flowable<StudentEntity>
}
