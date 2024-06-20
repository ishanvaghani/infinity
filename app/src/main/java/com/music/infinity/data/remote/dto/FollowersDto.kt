package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.Followers

data class FollowersDto(
    val href: Any?,
    val total: Long,
){
    fun getFollower() : Followers{
        return Followers(
            href, total
        )
    }
}
