package com.example.dementiaapp.di

import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.features.calendar.CalendarViewModel
import com.example.dementiaapp.features.diary.DiaryViewModel
import com.example.dementiaapp.features.home.HomeViewModel
import com.example.dementiaapp.features.medication.manage.ManageMedicationViewModel
import com.example.dementiaapp.features.medication.main.MedicationViewModel
import com.example.dementiaapp.features.myfamily.main.MyFamilyViewModel
import com.example.dementiaapp.features.myfamily.manage.ManageMyFamilyViewModel
import com.example.dementiaapp.features.profile.ProfileViewModel
import com.example.dementiaapp.features.reminders.main.RemindersViewModel
import com.example.dementiaapp.features.reminders.manage.ManageRemindersViewModel
import com.example.dementiaapp.repository.features.DiaryRepository
import com.example.dementiaapp.repository.features.DiaryRepositoryImpl
import com.example.dementiaapp.repository.features.MedicationRepository
import com.example.dementiaapp.repository.features.MedicationRepositoryImpl
import com.example.dementiaapp.repository.features.MyFamilyRepository
import com.example.dementiaapp.repository.features.MyFamilyRepositoryImpl
import com.example.dementiaapp.repository.features.RemindersRepository
import com.example.dementiaapp.repository.features.RemindersRepositoryImpl
import com.example.dementiaapp.repository.UserRepository
import com.example.dementiaapp.repository.UserRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    // Repositories
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
    singleOf(::DiaryRepositoryImpl).bind<DiaryRepository>()
    singleOf(::MedicationRepositoryImpl).bind<MedicationRepository>()
    singleOf(::MyFamilyRepositoryImpl).bind<MyFamilyRepository>()
    singleOf(::RemindersRepositoryImpl).bind<RemindersRepository>()

    /*
    single<DataStore<Preferences>> {
        androidContext().dataStore
    }*/
    single { DateStateHolder() }
    single { PermissionsManager(get()) }

    // ViewModels
    viewModelOf(::HomeViewModel)
    viewModelOf(::CalendarViewModel)
    viewModelOf(::MedicationViewModel)
    viewModelOf(::ManageMedicationViewModel)
    viewModelOf(::DiaryViewModel)
    viewModelOf(::MyFamilyViewModel)
    viewModelOf(::ManageMyFamilyViewModel)
    viewModelOf(::RemindersViewModel)
    viewModelOf(::ManageRemindersViewModel)
    viewModelOf(::ProfileViewModel)
}