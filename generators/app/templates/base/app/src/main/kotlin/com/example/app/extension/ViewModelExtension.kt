package <%= appPackage %>.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * @author Aldyaz Nugroho on 29/04/20.
 * https://github.com/aldyaz
 */

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(
    viewModelFactory: ViewModelProvider.Factory
): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}