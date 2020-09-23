package com.idea.group.iplato.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idea.group.iplato.repositories.RocketRepo
import com.idea.group.iplato.repositories.DatabaseRepository
import com.idea.group.iplato.viewModels.RocketsViewModel

class RocketsModelFactory(
    private val githubUserRepo: RocketRepo,
    private val database: DatabaseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RocketsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RocketsViewModel(githubUserRepo, database) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }

}