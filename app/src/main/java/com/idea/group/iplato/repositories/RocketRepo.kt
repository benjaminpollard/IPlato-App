package com.idea.group.iplato.repositories

import com.idea.group.iplato.models.responce.RocketModel
import com.idea.group.iplato.repositories.models.RepoCallBack
import com.idea.group.iplato.services.BaseNetworkService
import com.idea.group.iplato.services.RocketService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ROCKET_LIMIT = 1

open class RocketRepo(
    private val serviceCon: BaseNetworkService
) {

    var repoCallback: RepoCallBack<List<RocketModel>>? = null

    fun getRocketList(offset: Int) {
        val reposService: RocketService =
            serviceCon.serviceConstructor(RocketService::class.java) as RocketService

        reposService.getRockets(ROCKET_LIMIT,offset).enqueue(object : Callback<List<RocketModel>> {
            override fun onFailure(call: Call<List<RocketModel>>, t: Throwable) {
                repoCallback?.repoResult(null, t.localizedMessage)
            }

            override fun onResponse(
                call: Call<List<RocketModel>>,
                response: Response<List<RocketModel>>
            ) {
                if (response.isSuccessful)
                    repoCallback?.repoResult(response.body(), null)
                else
                    repoCallback?.repoResult(null, response.message())
            }

        })
    }
}