package pondthaitay.roompersistencelibrary.example

import android.content.Context
import pondthaitay.roompersistencelibrary.example.persistence.AppDatabase

class Injection {

    companion object {
        fun provideStudentDao(context: Context) = AppDatabase.getAppDatabase(context).studentDao()

        fun provideMainViewModelFactory(context: Context) =
                MainViewModel.MainViewModelFactory(provideStudentDao(context))
    }
}
