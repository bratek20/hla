package pl.bratek20.hla.utils

fun pascalToCamelCase(name: String): String {
    return name[0].lowercase() + name.substring(1)
}