package <%= appPackage %>

import android.app.Application
import <%= appPackage %>.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initInjection()
        Stetho.initializeWithDefaults(this)
    }

    private fun initInjection() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}