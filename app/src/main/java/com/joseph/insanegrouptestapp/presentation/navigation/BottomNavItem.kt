package com.joseph.insanegrouptestapp.presentation.navigation

import com.joseph.insanegrouptestapp.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var screen_route: String
) {

    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Organizer : BottomNavItem("Organizer", R.drawable.ic_organizer, "organizer")
    object Calls : BottomNavItem("Calls", R.drawable.ic_calls, "calls")
    object Chats : BottomNavItem("Chats", R.drawable.ic_chats, "chats")
    object Leads : BottomNavItem("Leads", R.drawable.ic_leads, "leads")
}

val bottomNavDestinationsRoutes = listOf(
    BottomNavItem.Home.screen_route,
    BottomNavItem.Organizer.screen_route,
    BottomNavItem.Calls.screen_route,
    BottomNavItem.Chats.screen_route,
    BottomNavItem.Leads.screen_route,
)