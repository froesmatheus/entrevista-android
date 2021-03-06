package com.matheusfroes.swapi.di

import com.matheusfroes.swapi.di.module.*
import com.matheusfroes.swapi.ui.bookmarks.BookmarkedPeopleActivity
import com.matheusfroes.swapi.ui.peoplelist.PeopleListActivity
import com.matheusfroes.swapi.ui.persondetail.PersonDetailActivity
import com.matheusfroes.swapi.ui.searchpeople.SearchPeopleActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, DataModule::class, NetworkModule::class,
    PeopleModule::class, ApiaryModule::class])
interface Injector {
    fun inject(personDetailActivity: PersonDetailActivity)
    fun inject(personDetailActivity: PeopleListActivity)
    fun inject(bookmarkedPeopleActivity: BookmarkedPeopleActivity)
    fun inject(searchPeopleActivity: SearchPeopleActivity)
}