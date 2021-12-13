package <%= appPackage %>.di

import <%= appPackage %>.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
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