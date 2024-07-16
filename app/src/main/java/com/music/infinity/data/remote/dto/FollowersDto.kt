package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.Followers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowersDto(
    @SerialName("href")
    val href: String?,
    @SerialName("total")
    val total: Long,
){
    fun getFollower() : Followers{
        return Followers(
            href, total
        )
    }
}
