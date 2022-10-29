package com.mustafa.artbookhilttesting.view

import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.mustafa.artbookhilttesting.R
import com.mustafa.artbookhilttesting.fragments.ArtsFragment
import com.mustafa.artbookhilttesting.fragments.ArtsFragmentDirections
import com.mustafa.artbookhilttesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@HiltAndroidTest
@MediumTest
class ArtFragmentTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory


    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtToArtDetais(){
        val navController = Mockito.mock(NavController::class.java)
            launchFragmentInHiltContainer<ArtsFragment>(
                factory = fragmentFactory
            ){
                Navigation.setViewNavController(requireView(),navController)
            }

        Espresso.onView(ViewMatchers.withId(com.mustafa.artbookhilttesting.R.id.fab)).perform(click())
        Mockito.verify(navController).navigate(
            ArtsFragmentDirections.actionArtsFragmentToArtDetailsFragment()
        )
    }


}