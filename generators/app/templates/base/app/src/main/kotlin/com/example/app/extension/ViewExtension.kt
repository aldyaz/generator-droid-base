package <%= appPackage %>.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @author Aldyaz Nugroho on 29/04/20.
 * https://github.com/aldyaz
 */

fun ViewGroup.inflateLayout(layoutId: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}