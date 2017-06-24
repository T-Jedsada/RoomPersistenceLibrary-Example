package pondthaitay.roompersistencelibrary.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase = AppDatabase.getAppDatabase(this)

        Flowable.fromCallable { appDatabase.studentDao().insertStudent(StudentEntity()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    appDatabase.studentDao().getStudentAll()
                            .subscribe({ it.forEach {
                                Log.e("POND", it.id.toString() + "\t" + it.firstName)
                            } }, { it.printStackTrace() })
                }, { it.printStackTrace() })
    }
}
