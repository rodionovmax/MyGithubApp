package com.rodionovmax.mygithubapp.ui.users

import com.rodionovmax.mygithubapp.domain.entity.UserEntity

interface UsersContract {

    interface View {
        fun showUsers(users: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
        fun openProfileScreen(userEntity: UserEntity)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
        fun onUserClicked(userEntity: UserEntity)
    }
}
