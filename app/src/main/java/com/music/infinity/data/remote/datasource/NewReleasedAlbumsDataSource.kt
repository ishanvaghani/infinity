package com.music.infinity.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.dto.AlbumDto

class NewReleasedAlbumsDataSource(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : PagingSource<Int, AlbumDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumDto> {
        return try {
            val position = params.key ?: 0
            val response = networkManager.executeWithAuthentication {
                spotifyApi.getNewReleasesAlbums(position)
            }
            if (response.isRight()) {
                LoadResult.Page(
                    data = response.getOrNull()?.albums ?: listOf(),
                    prevKey = if (position == 0) null else position - 1,
                    nextKey = if (position == response.getOrNull()?.total) null else position + 1
                )
            } else {
                LoadResult.Error(response.leftOrNull() ?: Exception())
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AlbumDto>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}