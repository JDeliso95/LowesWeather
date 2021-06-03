package com.example.lowesweather.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

fun Fragment.navigate(@IdRes action: Int) {
    Navigation.findNavController(requireView()).navigate(action)
}