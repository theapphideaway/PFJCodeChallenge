package com.pilotflyingj.codechallenge

import com.pilotflyingj.codechallenge.network.LocationService
import com.pilotflyingj.codechallenge.network.models.Constants
import com.pilotflyingj.codechallenge.utilities.createLocationService
import com.pilotflyingj.codechallenge.utilities.enqueueResponse
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetSiteTest {
    private lateinit var service: LocationService
    private val server = MockWebServer()

    @Before
    fun setup(){
        service = createLocationService(server.url(Constants.BaseUrl))
    }

    @After
    fun tearDown(){
        server.shutdown()
    }
    //The API layer makes one API call, and the main thing to test is making sure that there is a
    //response. Since there is no status code in the response, I am making sure that the response
    //is at least not null.
    @Test
    fun `when making an API for site locations returns positive`(){
        server.enqueueResponse("SiteList.json")
        runBlocking{
            val requestBody = service.getSitesAsync()
            assertNotNull(requestBody)
        }
    }
}