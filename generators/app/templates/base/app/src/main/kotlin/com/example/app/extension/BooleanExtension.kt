package <%= appPackage %>.extension


inline fun <reified T> Boolean.validate(accept: () -> T, denied: () -> T): T = if (this) accept() else denied()