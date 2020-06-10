package <%= appPackage %>.di

import androidx.lifecycle.ViewModelProvider
import <%= appPackage %>.base.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactor(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}