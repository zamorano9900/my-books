package com.zamorano.mybooks.modules.categoryBookDetail

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
abstract class CategoryBookDetailModule {

    /**
     * The parameter you receive is the interface implementation of the return parameter.
     * In this case it need to return an object of type BookDetailContract.Presentation, we specify that
     * this implementation is in the BookDetailsPresenter class.
     */
    @Binds
    abstract fun provideBookDetailPresenter(presenterCategory: CategoryBookDetailPresenter): CategoryBookDetailContract.Presentation

    /**
     * The parameter you receive is the interface implementation of the return parameter.
     * In this case it need to return an object of type BookDetailContract.InteractorInput, we specify that
     * this implementation is in the BookDetailInteractor class.
     */
    @Binds
    abstract fun provideInteractorPresenter(presenter: CategoryBookDetailInteractor): CategoryBookDetailContract.InteractorInput

    /**
     * The parameter you receive is the interface implementation of the return parameter.
     * In this case it need to return an object of type BookDetailContract.Wireframe, we specify that
     * this implementation is in the BookDetailsRouter class.
     */
    @Binds
    abstract fun ProvideRouterPresenter(routerCategory: CategoryBookDetailRouter): CategoryBookDetailContract.Wireframe
}