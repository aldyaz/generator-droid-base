package <%= appPackage %>.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflateLayout(layoutId: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}