package com.example.booklibrary.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry

object NavAnimations {
    private const val DURATION = 400
    private val standardEasing = FastOutSlowInEasing

    private val animationSpec = tween<IntOffset>(
        DURATION,
        easing = standardEasing
    )
    private val fadeSpec = tween<Float>(DURATION)

    fun AnimatedContentTransitionScope<NavBackStackEntry>.horizontalEnter(): EnterTransition {
        return slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec
        ) + fadeIn(fadeSpec)
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.horizontalExit(): ExitTransition {
        return slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec
        ) + fadeOut(fadeSpec)
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.verticalEnter(): EnterTransition {
        return slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up,
            animationSpec
        ) + fadeIn(fadeSpec)
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.verticalExit(): ExitTransition {
        return slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            animationSpec
        ) + fadeOut(fadeSpec)
    }

    fun fadeEnter(): EnterTransition = fadeIn(fadeSpec)
    fun fadeExit(): ExitTransition = fadeOut(fadeSpec)
}
