package <%= appPackage %>.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified T : ViewModel> FragmentActivity.getViewModel(
    viewModelFactory: ViewModelProvider.Factory
): T = ViewModelProvider(this, viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.getViewModel(
    viewModelFactory: ViewModelProvider.Factory
): T = ViewModelProvider(this, viewModelFactory)[T::class.java]