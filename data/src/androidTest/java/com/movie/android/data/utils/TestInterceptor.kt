package com.movie.android.data.utils

import com.movie.android.data.utils.mock.SearchResultTest
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class TestInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val url = chain.request().url.toString()

    return when {
      url.contains("search/movie") -> {
        Response.Builder()
          .code(200)
          .message("")
          .request(chain.request())
          .protocol(Protocol.HTTP_1_0)
          .body(
            SearchResultTest().mockResultTest().toResponseBody(
              "application/json"
                .toMediaTypeOrNull()
            )
          )
          .build()
      }
      else -> {
        Response.Builder()
          .code(404)
          .message("")
          .request(chain.request())
          .protocol(Protocol.HTTP_1_0)
          .body("".toResponseBody("application/json".toMediaTypeOrNull()))
          .build()
      }

    }
  }
}