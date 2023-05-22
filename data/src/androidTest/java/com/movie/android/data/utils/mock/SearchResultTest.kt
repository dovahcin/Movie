package com.movie.android.data.utils.mock

import com.movie.android.data.PAGE_TEST
import com.movie.android.data.TOTAL_RESULTS_TEST

class SearchResultTest(private val page: Int = PAGE_TEST, private val totalResults: Int = TOTAL_RESULTS_TEST) {

  fun mockResultTest() = "{\n" +
    "  \"page\": $page,\n" +
    "  \"results\": [\n" +
    "    {\n" +
    "      \"poster_path\": \"/cezWGskPY5x7GaglTTRN4Fugfb8.jpg\",\n" +
    "      \"adult\": false,\n" +
    "      \"overview\": \"When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!\",\n" +
    "      \"release_date\": \"2012-04-25\",\n" +
    "      \"genre_ids\": [\n" +
    "        878,\n" +
    "        28,\n" +
    "        12\n" +
    "      ],\n" +
    "      \"id\": 24428,\n" +
    "      \"original_title\": \"The Avengers\",\n" +
    "      \"original_language\": \"en\",\n" +
    "      \"title\": \"The Avengers\",\n" +
    "      \"backdrop_path\": \"/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg\",\n" +
    "      \"popularity\": 7.353212,\n" +
    "      \"vote_count\": 8503,\n" +
    "      \"video\": false,\n" +
    "      \"vote_average\": 7.33\n" +
    "    },\n" +
    "    {\n" +
    "      \"poster_path\": \"/pMdTc3kYCD1869UX6cdYUT8Xe49.jpg\",\n" +
    "      \"adult\": false,\n" +
    "      \"overview\": \"Feature-length documentary about the rise of Marvel Studios and their films leading up to, and including, The Avengers.\",\n" +
    "      \"release_date\": \"2012-09-25\",\n" +
    "      \"genre_ids\": [\n" +
    "        99\n" +
    "      ],\n" +
    "      \"id\": 161097,\n" +
    "      \"original_title\": \"Marvel Studios: Building a Cinematic Universe\",\n" +
    "      \"original_language\": \"en\",\n" +
    "      \"title\": \"Marvel Studios: Building a Cinematic Universe\",\n" +
    "      \"backdrop_path\": \"/yeKT2gNFxHGbTT3Htj5PE9IerGJ.jpg\",\n" +
    "      \"popularity\": 1.136598,\n" +
    "      \"vote_count\": 4,\n" +
    "      \"video\": false,\n" +
    "      \"vote_average\": 3.88\n" +
    "    }\n" +
    "  ],\n" +
    "  \"total_results\": $totalResults,\n" +
    "  \"total_pages\": 1\n" +
    "}"
}