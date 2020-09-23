package com.idea.group.iplato.models.responce

import com.google.gson.annotations.SerializedName

data class RocketModel(@SerializedName("id")val id : Int,
                       @SerializedName("rocket_name")val name: String,
                       @SerializedName("country")val country : String,
                       @SerializedName("flickr_images")val images: List<String>)