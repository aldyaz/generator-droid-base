package <%= appPackage %>.extension


inline fun <reified T> T?.orThrow(throwable: Throwable): T {
    return this ?: throw throwable
}

inline infix fun <reified T> T?.orElse(default: T): T {
    return this ?: default
}

inline infix fun <reified T> T?.orElse(default: () -> T): T {
    return this ?: default.invoke()
}