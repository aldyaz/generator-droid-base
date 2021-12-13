package <%= appPackage %>.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


inline fun FragmentManager.replaceSingle(
    tag: String,
    layout: Int,
    func: () -> Fragment,
    isFirstFragment: Boolean = false
) {
    if (isFirstFragment) {
        popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    beginTransaction().apply {
        replace(layout, func(), tag)
        if (!isFirstFragment) {
            addToBackStack(null)
        }
    }.commit()
}