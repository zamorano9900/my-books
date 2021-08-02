package com.zamorano.mybooks.modules.categoryBookCollection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Modules are classes that tell Hilt how to get the necessary components a class needs.
 * In this case the module is an Activity, that's why we use the ActivityComponent annotation.
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class CategoryBookModule {

    /**
     * The parameter you receive is the interface implementation of the return parameter.
     * In this case it need to return an object of type BookContract.Presentation, we specify that
     * this implementation is in the BooksPresenter class.
     */
    @Binds
    abstract fun provideBookPresenter(presenterCategory: CategoryBookPresenter): CategoryBookContract.Presentation

    /**
     * The parameter you receive is the interface implementation of the return parameter.
     * In this case it need to return an object of type BookContract.InteractorInput, we specify that
     * this implementation is in the BookInteractor class.
     */
    @Binds
    abstract fun provideInteractorPresenter(presenter: CategoryBookInteractor): CategoryBookContract.InteractorInput

    /**
     * The parameter you receive is the interface implementation of the return parameter.
     * In this case it need to return an object of type BookContract.Wireframe, we specify that
     * this implementation is in the BooksRouter class.
     */
    @Binds
    abstract fun provideRouterPresenter(routerCategory: CategoryBookRouter): CategoryBookContract.Wireframe
}