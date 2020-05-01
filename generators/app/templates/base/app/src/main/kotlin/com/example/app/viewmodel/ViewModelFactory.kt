package <%= appPackage %>.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


/**
 * @author Aldyaz Nugroho on 29/04/20.
 * https://github.com/aldyaz
 */
class ViewModelFactory<T : ViewModel> @Inject constructor(private val provider: Provider<T>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = provider.get() as T
}