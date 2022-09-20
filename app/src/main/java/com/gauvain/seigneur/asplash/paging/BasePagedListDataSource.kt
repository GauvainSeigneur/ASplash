package com.gauvain.seigneur.asplash.paging

import android.util.Log
import androidx.annotation.CallSuper
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError

abstract class BasePagedListDataSource<T : Any> : PagingSource<Int, T>() {

    init {
        val l = this.handleFirstLoad()
    }

    abstract suspend fun getData(page: Int): Outcome<List<T>, RemoteRequestError>

    @CallSuper
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    @CallSuper
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val currentPageNumber = when (val key = params.key) {
            null -> 1
            else -> key
        }
        Log.d("weshalors", "currentPageNumber $currentPageNumber")
        return when (val response = getData(currentPageNumber)) {
            is Outcome.Success -> {
                LoadResult.Page(
                    data = response.data,
                    prevKey = null,
                    nextKey = currentPageNumber + 1
                )
            }
            is Outcome.Error -> LoadResult.Error(Throwable("error")) // todo rework! with a mapper ?
        }
    }

    private fun handleFirstLoad() {
    }
}