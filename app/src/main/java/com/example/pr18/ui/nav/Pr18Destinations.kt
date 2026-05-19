package com.example.pr18.ui.nav

sealed class Pr18Destinations(val route: String) {
    data object MainMenu : Pr18Destinations("main_menu")
    data object SimpleService : Pr18Destinations("simple_service")
    data object StopSelf : Pr18Destinations("stop_self")
    data object StickyModes : Pr18Destinations("sticky_modes")
    data object PendingIntent : Pr18Destinations("pending_intent")
}

