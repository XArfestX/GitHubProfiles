package com.arfest.githubprofiles.presentation.profile

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arfest.githubprofiles.domain.models.User
import com.arfest.githubprofiles.domain.models.UserProfile
import com.arfest.githubprofiles.domain.usecases.profile.GetUserFollowersUseCase
import com.arfest.githubprofiles.domain.usecases.profile.GetUserProfileUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UserProfileUiState {
    data object Loading : UserProfileUiState

    @Immutable
    data class SuccessBio(
        val userProfile: UserProfile
    ) : UserProfileUiState

    @Immutable
    data class SuccessFollowers (
        val userProfile: ImmutableList<User>
    ) : UserProfileUiState
}


class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserFollowersUseCase: GetUserFollowersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        initUserProfile()
    }



    private fun initUserProfile() {
        viewModelScope.launch {
            getUserProfileUseCase.execute().collect{ user ->
                val login = user.login

                _uiState.update { _ ->
                    UserProfileUiState.SuccessBio(
                        userProfile = user
                    )
                }

//                val followers = getUserFollowersUseCase.execute(login)
//                _uiState.update { _ ->
//                    UserProfileUiState.SuccessFollowers(
//                        userProfile = followers.toPersistentList()
//                    )
//                }

            }
        }
    }

    internal class Factory(
        private val getUserProfileUseCase: GetUserProfileUseCase,
        private val getUserFollowersUseCase: GetUserFollowersUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProfileViewModel(
                getUserProfileUseCase = getUserProfileUseCase,
                getUserFollowersUseCase = getUserFollowersUseCase
            ) as T
    }
}