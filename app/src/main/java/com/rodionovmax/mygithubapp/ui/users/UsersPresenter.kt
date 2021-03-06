package com.rodionovmax.mygithubapp.ui.users

// Presenter implementation for MVP. Leaving it commented out here just for notes
/*
class UsersPresenter(private val usersRepo: UsersRepo) : UsersContract.Presenter {

    private var view: UsersContract.View? = null
    private var inProgress: Boolean = false
    private var usersList: List<UserEntity>? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        usersList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClicked(userEntity: UserEntity) {
        view?.openProfileScreen(userEntity)
    }

    private fun loadData() {
        view?.showProgress(true)
        inProgress = true
        usersRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                usersList = it
                inProgress = false
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProgress = false
            }
        )
    }
}
*/
