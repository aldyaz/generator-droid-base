package <%= appPackage %>.di

import <%= appPackage %>.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


/**
 * @author Aldyaz Nugroho on 29/04/20.
 * https://github.com/aldyaz
 */

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        MainModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun build(): AppComponent
    }

    fun inject(application: MainApplication)
}