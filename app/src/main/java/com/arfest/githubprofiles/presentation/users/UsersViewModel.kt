package com.arfest.githubprofiles.presentation.users

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arfest.githubprofiles.domain.models.User
import com.arfest.githubprofiles.domain.usecases.profile.GetUserProfileUseCase
import com.arfest.githubprofiles.domain.usecases.users.GetUsersUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed interface UserUiState {
    data object Loading : UserUiState

    @Immutable
    data class Success (
        val users: ImmutableList<User>
    ) : UserUiState
}


class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        initUsers()
    }

    private fun initUsers() {
        viewModelScope.launch {
            val user = getUsersUseCase.execute()

            _uiState.update {_ ->
                UserUiState.Success(
                    users = user.toPersistentList()
                )
            }
        }
    }

    suspend fun openToDetails(login: String) {
        getUserProfileUseCase.execute(login)
    }

    internal class Factory(
        private val getUsersUseCase: GetUsersUseCase,
        private val getUserProfileUseCase: GetUserProfileUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            UsersViewModel(
                getUsersUseCase = getUsersUseCase,
                getUserProfileUseCase = getUserProfileUseCase
            ) as T
    }
}