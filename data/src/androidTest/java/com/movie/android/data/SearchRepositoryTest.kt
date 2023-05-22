package com.movie.android.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.movie.android.data.db.MovieDatabase
import com.movie.android.data.db.SearchHistoryDao
import com.movie.android.data.di.testModule
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.SearchHistory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.java.KoinJavaComponent.inject
import java.io.IOException

const val TOTAL_RESULTS_TEST = 2
const val PAGE_TEST = 1

@RunWith(AndroidJUnit4::class)
class SearchRepositoryTest {

  private val db: MovieDatabase by inject(MovieDatabase::class.java)
  private lateinit var repository: SearchRepository
  private val api: ApiServices by inject(ApiServices::class.java)

  @Before
  fun before() {
    loadKoinModules(testModule)
    repository = SearchRepository(api, db.searchHistory())
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  fun test1_getDataFromServer() = runBlocking {
    val query = "test"
    repository.getDataForLists(query).collect {
      assertThat(it.histories).isEmpty()
      assertThat(it.movies.page).isEqualTo(PAGE_TEST)
      assertThat(it.movies.total_results).isEqualTo(TOTAL_RESULTS_TEST)
      assertThat(it.movies.results).hasSize(TOTAL_RESULTS_TEST)
    }
  }

  @Test
  fun test2_addSearchHistory() = runBlocking {
    val history = SearchHistory("test", 1)
    repository.addSearchHistory(history).collect {
      assertThat(it).isNotEmpty()
      assertThat(it[0]).isEqualTo(history)
    }
  }

  @Test
  fun test3_removeSearchHistory() = runBlocking {
    val history = SearchHistory("test", 1)
    repository.removeSearchHistory(history.id!!).collect {
      assertThat(it).isEmpty()
    }
  }

}