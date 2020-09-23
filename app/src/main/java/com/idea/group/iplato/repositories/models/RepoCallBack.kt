package com.idea.group.iplato.repositories.models

abstract class RepoCallBack<T> {
    abstract fun repoResult(data: T?, error: String?)
}


