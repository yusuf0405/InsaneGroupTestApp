package com.joseph.insanegrouptestapp.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val title: String
    val route: String
    val routeWithArgs: String
}

object CreateLead : Destinations {

    override val title: String
        get() = "Create lead"

    override val route: String
        get() = "create_lead"

    override val routeWithArgs: String
        get() = route
}

object LeadProfile : Destinations {

    const val LEAD_ID = "leadId"

    override val title: String
        get() = "Lead profile"

    override val route: String
        get() = "lead_profile"

    override val routeWithArgs: String
        get() = "$route/{$LEAD_ID}"

    val arguments = listOf(
        navArgument(name = LEAD_ID) { type = NavType.IntType }
    )
}

val destinations = listOf(
    CreateLead.route,
    LeadProfile.routeWithArgs,
)
