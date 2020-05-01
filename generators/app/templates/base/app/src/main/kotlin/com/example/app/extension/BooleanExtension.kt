package <%= appPackage %>.extension


/**
 * @author Aldyaz Nugroho on 29/04/20.
 * https://github.com/aldyaz
 */

inline fun <reified T> Boolean.validate(accept: () -> T, denied: () -> T): T = if (this) accept() else denied()