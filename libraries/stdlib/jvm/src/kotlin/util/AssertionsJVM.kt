/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("PreconditionsKt")
package kotlin

@PublishedApi
internal object _Assertions {
    @JvmField
    @PublishedApi
    internal val ENABLED: Boolean = javaClass.desiredAssertionStatus()
}

/**
 * Throws an [AssertionError] if the [value] is false
 * and runtime assertions have been enabled on the JVM using the *-ea* JVM option.
 */
@kotlin.internal.InlineOnly
public inline fun assert(value: Boolean) {
    assert(value) { "Assertion failed" }
}

/**
 * Throws an [AssertionError] calculated by [lazyMessage] if the [value] is false
 * and runtime assertions have been enabled on the JVM using the *-ea* JVM option.
 */
@kotlin.internal.InlineOnly
public inline fun assert(value: Boolean, lazyMessage: () -> Any) {
    if (_Assertions.ENABLED) {
        @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
        alwaysEnabledAssert(value, lazyMessage)
    }
}

@kotlin.internal.InlineOnly
internal inline fun alwaysEnabledAssert(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        val message = lazyMessage()
        throw AssertionError(message)
    }
}

@kotlin.internal.InlineOnly
internal inline fun alwaysEnabledAssert(value: Boolean) {
    alwaysEnabledAssert(value) { "Assertion failed" }
}
