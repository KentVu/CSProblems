package net

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

/**
 * Automatically cancels this [CoroutineScope] when the specified [lifecycle] is destroyed.
 *
 * @return the same (this) [CoroutineScope].
 */
fun CoroutineScope.withLifecycle(lifecycle: Lifecycle): CoroutineScope {
  lifecycle.doOnDestroy(::cancel)

  return this
}
