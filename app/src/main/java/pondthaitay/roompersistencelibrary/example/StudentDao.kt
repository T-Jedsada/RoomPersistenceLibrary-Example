package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(studentEntity: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudentReplace(studentEntity: StudentEntity)

    @Update
    fun updateStudent(studentEntity: StudentEntity)

    @Delete
    fun deleteStudent(studentEntity: StudentEntity)

    @Query("SELECT * FROM student")
    fun getStudentAll(): Flowable<MutableList<StudentEntity>>

    @Query("SELECT * FROM student WHERE student.student_code = :arg0")
    fun getEmailsForUser(studentCode: Int): Flowable<StudentEntity>

    @Query("DELETE FROM student")
    fun deleteTable()
}