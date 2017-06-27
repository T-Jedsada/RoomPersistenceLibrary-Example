package pondthaitay.roompersistencelibrary.example

import android.content.Context
import pondthaitay.roompersistencelibrary.example.persistence.AppDatabase
import pondthaitay.roompersistencelibrary.example.persistence.LocalStudentDataSource

class Injection {

    companion object {
        fun provideStudentDataSource(context: Context) =
                LocalStudentDataSource(AppDatabase.getAppDatabase(context).studentDao())

        fun provideMainViewModelFactory(context: Context) =
                MainViewModel.MainViewModelFactory(provideStudentDataSource(context))
    }
}
