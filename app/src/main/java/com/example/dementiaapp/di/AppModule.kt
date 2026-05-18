package com.example.dementiaapp.di

import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.state.AppStateHolder
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.domain.state.UserStateHolder
import com.example.dementiaapp.feature.calendar.CalendarViewModel
import com.example.dementiaapp.feature.diary.DiaryViewModel
import com.example.dementiaapp.feature.home.HomeViewModel
import com.example.dementiaapp.feature.medication.management.ManageMedicationViewModel
import com.example.dementiaapp.feature.medication.main.MedicationViewModel
import com.example.dementiaapp.feature.myfamily.main.MyFamilyViewModel
import com.example.dementiaapp.feature.myfamily.manage.ManageMyFamilyViewModel
import com.example.dementiaapp.feature.profile.main.ProfileViewModel
import com.example.dementiaapp.feature.reminders.shared.RemindersViewModel
import com.example.dementiaapp.feature.reminders.manage.ManageRemindersViewModel
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

    single {
        AppStateHolder(
            get(),
            get(),
            //get()
        )
    }
    single { UserStateHolder(get()) }
    //single { ThemeStateHolder(get()) }
    single { PermissionsManager() }
    single { DateStateHolder() }

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