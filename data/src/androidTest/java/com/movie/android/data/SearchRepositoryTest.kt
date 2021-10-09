package com.movie.android.data

import android.util.Log
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.movie.android.data.db.SearchHistoryDao
import com.movie.android.data.network.ApiServices
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.java.KoinJavaComponent.inject

@RunWith(AndroidJUnit4::class)
class SearchRepositoryTest {

  val searchDb : SearchHistoryDao by inject(SearchHistoryDao::class.java)
  lateinit var repository : SearchRepository

  val api : ApiServices by inject(ApiServices::class.java)

  @Before
  fun before() {
    loadKoinModules(testModule)
    repository = SearchRepository(api, searchDb)
  }

  @Test
  fun testGetDataForLists() = runBlocking {
    repository.getDataForLists("lord").collect {
      Log.i("tag" , "")
    }
  }

  fun testAddSearchHistory() {}

  fun testRemoveSearchHistory() {}
}