package com.example.dicodingmovie.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.dicodingmovie.R
import com.example.dicodingmovie.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        delay(2)
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        delay(4)
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay(2)
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovie[0].release_date)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie[0].overview)))
    }

    @Test
    fun loadTvShows() {
        delay(2)
        onView(withText("Tv Show")).perform(click())
        delay(2)
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShows() {
        delay(2)
        onView(withText("Tv Show")).perform(click())
        delay(2)
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay(2)
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShow[0].name)))
        onView(withId(R.id.tv_first_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date)).check(matches(withText(dummyTvShow[0].first_air_date)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShow[0].overview)))
    }

    private fun delay(second:Long) {
        try {
            Thread.sleep(second*1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
