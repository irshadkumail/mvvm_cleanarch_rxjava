package com.example.mvvm_cleanarchitecture_rxjava

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetAlbumsUseCase
import com.example.mvvm_cleanarchitecture_rxjava.presentation.HomeListUiStateItem
import com.example.mvvm_cleanarchitecture_rxjava.presentation.HomeScreen
import com.example.mvvm_cleanarchitecture_rxjava.presentation.MainActivity
import com.example.mvvm_cleanarchitecture_rxjava.presentation.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MainActivityTest {


    @MockK
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createComposeRule()


    @Test
    fun testProgressIndicator() {

        every { viewModel.handleError }.returns(null)
        every { viewModel.loadingState }.returns(true)


        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel) {

            }
        }

        composeTestRule.onNodeWithTag("Circular").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lazyColumn").assertDoesNotExist()
        composeTestRule.onNodeWithTag("errorDialog").assertDoesNotExist()
    }

    @Test
    fun testListDisplay() {

        every { viewModel.handleError }.returns(null)
        every { viewModel.loadingState }.returns(false)
        every { viewModel.uiList }.returns(
            mutableStateListOf(
                HomeListUiStateItem(
                    id = 123
                ),
                HomeListUiStateItem(
                    id = 456
                )
            )
        )


        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel) {

            }
        }

        composeTestRule.onNodeWithTag("Circular").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lazyColumn").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorDialog").assertDoesNotExist()

    }

      @Test
      fun testErrorDialogDisplay() {

          every { viewModel.handleError }.returns("Error")
          every { viewModel.loadingState }.returns(false)
          every { viewModel.uiList }.returns(
              mutableStateListOf()
          )

          composeTestRule.setContent {
              HomeScreen(viewModel = viewModel) {

              }
          }

          composeTestRule.onNodeWithTag("Circular").assertDoesNotExist()
          composeTestRule.onNodeWithTag("errorDialog").assertIsDisplayed()

      }


}