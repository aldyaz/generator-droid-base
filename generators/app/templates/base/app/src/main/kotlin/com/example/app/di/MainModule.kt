package <%= appPackage %>.di

import android.content.Context
import <%= appPackage %>.MainApplication
import dagger.Module


/**
 * @author Aldyaz Nugroho on 29/04/20.
 * https://github.com/aldyaz
 */
@Module
abstract class MainModule {
    internal abstract fun bindApplicationContext(application: MainApplication): Context
}