package com.nikol412.poopysquish.utils

import android.os.Looper
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline initializer: (LayoutInflater) -> T
): FragmentViewBindingPropertyDelegate<T> =
    FragmentViewBindingPropertyDelegate(lifecycle) { initializer(layoutInflater) }

class FragmentViewBindingPropertyDelegate<T : ViewBinding>(
    lifecycle: Lifecycle,
    private val initializer: () -> T
) : LifecycleObserver {

    private var _value: T? = null

    init {
        val defaultLifecycleObserver = object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                _value = null
            }
        }
        lifecycle.addObserver(defaultLifecycleObserver)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>?): T {
        return _value ?: initializer().also {
            check(Looper.myLooper() == Looper.getMainLooper()) {
                "This cannot be called from other threads. It should be on the main thread only."
            }
            _value = it
        }
    }
}