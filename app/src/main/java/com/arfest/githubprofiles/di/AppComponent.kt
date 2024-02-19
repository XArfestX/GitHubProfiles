package com.arfest.githubprofiles.di

import android.content.Context
import com.arfest.githubprofiles.domain.usecases.profile.GetUserFollowersUseCase
import com.arfest.githubprofiles.domain.usecases.profile.GetUserProfileUseCase
import com.arfest.githubprofiles.domain.usecases.users.GetUsersUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
])
interface AppComponent {

    val getUsersUseCase: GetUsersUseCase
    val getUserProfileUseCase: GetUserProfileUseCase
    val getUserFollowersUseCase: GetUserFollowersUseCase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addContext(context: Context): Builder

        fun build(): AppComponent
    }
}