package com.example.sembahyang.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.sembahyang.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val surah= "Al-Fatihah"
    private val tempatTurun = "mekah"
    private val jumlah_ayat = "7 Ayat"
    private val arti = "Pembukaan"

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)



    @Test
    fun loadSurah() {
        Espresso.onView(withId(R.id.cv_alquran)).perform(click())
        Espresso.onView(withId(R.id.rv_surah)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_surah)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                114
            )
        )
    }

    @Test
    fun searchData() {
        Espresso.onView(withId(R.id.cv_alquran)).perform(click())
        Espresso.onView(withId(R.id.search_view))
            .perform(click() ,typeText(surah) ,closeSoftKeyboard())
    }

    @Test
    fun loadAyah() {
        Espresso.onView(withId(R.id.cv_alquran)).perform(click())
        Espresso.onView(withId(R.id.rv_surah)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(withId(R.id.tv_surah)).check(matches(withText(surah)))
        Espresso.onView(withId(R.id.tv_arti)).check(matches(withText(arti)))
        Espresso.onView(withId(R.id.tv_turun)).check(matches(withText(tempatTurun)))
        Espresso.onView(withId(R.id.tv_arti)).check(matches(withText(arti)))

    }

    @Test
    fun loadJadwal() {
        Espresso.onView(withId(R.id.cv_jadwal_sholat)).perform(click())
        Espresso.onView(checkNotNull(withId(R.id.tv_date)))
        Espresso.onView(checkNotNull(withId(R.id.rl_spin)))
        Espresso.onView(checkNotNull(withId(R.id.tv_subuh)))
        Espresso.onView(checkNotNull(withId(R.id.tv_dzuhur)))
        Espresso.onView(checkNotNull(withId(R.id.tv_ashar)))
        Espresso.onView(checkNotNull(withId(R.id.tv_maghrib)))
        Espresso.onView(checkNotNull(withId(R.id.tv_isya)))
    }
}