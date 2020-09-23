package com.idea.group.iplato.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idea.group.iplato.repositories.RocketRepo
import com.idea.group.iplato.models.responce.RocketModel
import com.idea.group.iplato.repositories.DatabaseRepository
import com.idea.group.iplato.repositories.models.RepoCallBack

class RocketsViewModel(
    private val rocketRepo: RocketRepo,
    private val database: DatabaseRepository
) : ViewModel() {

    private val callback: RepoCallBack<List<RocketModel>> =
        object : RepoCallBack<List<RocketModel>>() {
            override fun repoResult(data: List<RocketModel>?, error: String?) {
                loadingItems = false
                showLoading.postValue(loadingItems)

                error?.let {
                    showError.postValue(it)
                }
                data?.let {
                    if (it.isNotEmpty()) {
                        if (loadFreshList) {
                            shouldClearExistingItems.postValue(Unit)
                        }
                        rockets.postValue(data)
                    } else
                        canLoadMore = false
                }
                loadFreshList = false

            }
        }

    init {
        rocketRepo.repoCallback = callback
    }

    val rockets: MutableLiveData<List<RocketModel>> = MutableLiveData<List<RocketModel>>()
    val showLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val showError: MutableLiveData<String> = MutableLiveData<String>()
    val shouldClearExistingItems: MutableLiveData<Unit> = MutableLiveData<Unit>()

    private var loadingItems = false
    private var canLoadMore = true
    private var loadFreshList = false
    fun getItemsFromStart() {
        loadFreshList = true
        getItems(0)
    }

    fun getItems(offset: Int) {
        if (loadingItems || !canLoadMore)
            return

        loadingItems = true
        showLoading.postValue(loadingItems)
        rocketRepo.getRocketList(offset)
    }

}