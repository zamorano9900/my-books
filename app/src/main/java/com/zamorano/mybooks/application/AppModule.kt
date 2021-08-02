package com.zamorano.mybooks.application

import android.content.Context
import com.zamorano.mybooks.model.repositories.BookRepository
import com.zamorano.mybooks.model.repositories.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBookRepository(
        @ApplicationContext appContext: Context,
    ): BookRepository {
        return BookRepositoryImpl(appContext)
    }
}