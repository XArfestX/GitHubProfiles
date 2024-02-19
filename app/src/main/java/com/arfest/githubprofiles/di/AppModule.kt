package com.arfest.githubprofiles.di

import com.arfest.githubprofiles.data.network.api.GitApi
import com.arfest.githubprofiles.data.network.api.GitApiNetwork
import com.arfest.githubprofiles.data.repository.UserRepositoryImpl
import com.arfest.githubprofiles.data.storage.api.ProfileStorage
import com.arfest.githubprofiles.data.storage.memory.ProfileMemoryStorage
import com.arfest.githubprofiles.domain.repository.UserRepository
import com.arfest.githubprofiles.domain.usecases.profile.GetUserFollowersUseCase
import com.arfest.githubprofiles.domain.usecases.profile.GetUserProfileUseCase
import com.arfest.githubprofiles.domain.usecases.users.GetUsersUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
class AppModule {
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideGitApi(
        profileStorage: ProfileStorage,
        @ApiUsersUrl apiUsersUrl: String,
        moshi: Moshi,
        dispatcher: CoroutineDispatcher
    ): GitApi {
        return GitApiNetwork(
            apiUsersUrl = apiUsersUrl,
            moshi = moshi,
            dispatcher = dispatcher,
            profileStorage = profileStorage
        )
    }

    @Provides
    fun provideUserRepository(
        profileStorage: ProfileStorage,
        gitApi: GitApi,
        dispatcher: CoroutineDispatcher
    ) : UserRepository {
        return UserRepositoryImpl(
            gitApi = gitApi,
            dispatcher = dispatcher,
            profileStorage = profileStorage
        )
    }

    @Provides
    fun provideProfileStorage(): ProfileStorage {
        return ProfileMemoryStorage()
    }

    @Provides
    fun provideGetUsersUseCase(
        userRepository: UserRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(
            repository = userRepository
        )
    }

    @Provides
    fun provideGetUserProfileUseCase(
        userRepository: UserRepository
    ): GetUserProfileUseCase {
        return GetUserProfileUseCase(
            repository = userRepository
        )
    }

    @Provides
    fun provideGetUserFollowersUseCase(
        userRepository: UserRepository
    ): GetUserFollowersUseCase {
        return GetUserFollowersUseCase(
            repository = userRepository
        )
    }

    @Provides
    @ApiUsersUrl
    fun provideUsersUrl(): String {
        return "https://api.github.com/users"
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideListMoshi(
        moshi: Moshi
    ): List<Moshi> {
        return listOf(moshi)
    }

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiUsersUrl
