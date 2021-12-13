package <%= appPackage %>.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.toList().find {
                modelClass.isAssignableFrom(it.first)
            }?.second
        return try {
            if (creator != null)
                creator.get() as T
            else
                throw RuntimeException("unknown $modelClass")
        } catch (e: Throwable) {
            throw RuntimeException(e)
        }
    }
}