package com.joseph.insanegrouptestapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.CreateLeadViewModel
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.CreateLeadScreen
import com.joseph.insanegrouptestapp.presentation.lead_profile.LeadProfileScreen
import com.joseph.insanegrouptestapp.presentation.lead_profile.LeadProfileScreenViewModel
import com.joseph.insanegrouptestapp.presentation.leads_screen.LeadsScreen
import com.joseph.insanegrouptestapp.presentation.leads_screen.LeadsScreenViewModel
import com.joseph.insanegrouptestapp.presentation.navigation.BottomNavItem
import com.joseph.insanegrouptestapp.presentation.navigation.CustomBottomNavigation
import com.joseph.insanegrouptestapp.presentation.navigation.CreateLead
import com.joseph.insanegrouptestapp.presentation.navigation.LeadProfile
import com.joseph.insanegrouptestapp.presentation.navigation.LeadProfile.LEAD_ID
import com.joseph.insanegrouptestapp.presentation.navigation.bottomNavDestinationsRoutes
import com.joseph.insanegrouptestapp.presentation.navigation.destinations

import com.joseph.insanegrouptestapp.presentation.theme.InsaneGroupTestAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsaneGroupTestAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenView()
                }
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()
    val backStackEntry by navController.currentBackStackEntryAsState()

    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.onSecondary,
        darkIcons = !isSystemDark
    )
    val allDestinations = mutableListOf<String>()
    allDestinations.addAll(bottomNavDestinationsRoutes)
    allDestinations.addAll(destinations)

    val currentScreenRoute = allDestinations.find { route ->
        backStackEntry?.destination?.route == route || backStackEntry?.destination?.route == route
    } ?: BottomNavItem.Home.screen_route

    if (currentScreenRoute == BottomNavItem.Leads.screen_route) {
        bottomBarState.value = true
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                CustomBottomNavigation(
                    navController = navController
                )
            }

        },
    ) { innerPaddings ->
        NavHost(
            navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = BottomNavItem.Home.screen_route
        ) {
            composable(BottomNavItem.Home.screen_route) {
                HomeScreen()
            }
            composable(BottomNavItem.Organizer.screen_route) {
                OrganizerScreen()
            }
            composable(BottomNavItem.Calls.screen_route) {
                CallsScreen()
            }
            composable(BottomNavItem.Chats.screen_route) {
                ChatsScreen()
            }
            composable(CreateLead.routeWithArgs) {
                val createLeadViewModel: CreateLeadViewModel = koinViewModel()
                CreateLeadScreen(
                    uiState = createLeadViewModel.uiState,
                    onNavigateBack = navController::navigateUp,
                    onSaveButtonClick = createLeadViewModel::onSaveButtonClick,
                    onSelectedLeadIntentionType = createLeadViewModel::setSelectLeadIntentionTypes,
                    onSelectedCountry = createLeadViewModel::setSelectCountry,
                    onSelectedCity = createLeadViewModel::setSelectCity,
                    onSelectedLanguage = createLeadViewModel::setSelectLanguage,
                    onSelectedLeadSources = createLeadViewModel::setLeadSources,
                    onSearchCountry = createLeadViewModel::onSearchCountry,
                    onSearchLanguage = createLeadViewModel::onSearchLanguage,
                    onDismissDialog = createLeadViewModel::onDismissDialog
                )

            }
            composable(LeadProfile.routeWithArgs) {
                val leadId = it.arguments?.getString(LEAD_ID) ?: "0"
                val viewModel: LeadProfileScreenViewModel = koinViewModel(
                    parameters = { parametersOf(leadId.toInt()) }
                )
                LeadProfileScreen(
                    uiState = viewModel.uiState,
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(BottomNavItem.Leads.screen_route) {
                val viewModel: LeadsScreenViewModel = koinViewModel()
                LeadsScreen(
                    uiState = viewModel.uiState,
                    onNavigateCreateLeadScreen = {
                        bottomBarState.value = false
                        navController.navigate(CreateLead.route)
                    },
                    onNavigateLeadProfile = { id ->
                        bottomBarState.value = false
                        navController.navigate("${LeadProfile.route}/${id}")
                    }
                )
            }
        }
    }
}