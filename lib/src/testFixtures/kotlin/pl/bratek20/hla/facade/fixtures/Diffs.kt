// DO NOT EDIT! Autogenerated by HLA tool

package pl.bratek20.hla.facade.fixtures

import pl.bratek20.hla.directory.api.*
import pl.bratek20.hla.directory.fixtures.*

import pl.bratek20.hla.facade.api.*

fun diffModuleName(given: ModuleName, expected: String, path: String = ""): String {
    if (given.value != expected) { return "${path}value ${given.value} != ${expected}" }
    return ""
}


fun diffProfileName(given: ProfileName, expected: String, path: String = ""): String {
    if (given.value != expected) { return "${path}value ${given.value} != ${expected}" }
    return ""
}

data class ExpectedHlaProfile(
    var name: String? = null,
    var language: ModuleLanguage? = null,
    var projectPath: String? = null,
    var mainPath: String? = null,
    var fixturesPath: String? = null,
    var testsPath: String? = null,
    var onlyParts: List<String>? = null,
    var generateWeb: Boolean? = null,
)
fun diffHlaProfile(given: HlaProfile, expectedInit: ExpectedHlaProfile.() -> Unit, path: String = ""): String {
    val expected = ExpectedHlaProfile().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.name?.let {
        if (diffProfileName(given.getName(), it) != "") { result.add(diffProfileName(given.getName(), it, "${path}name.")) }
    }

    expected.language?.let {
        if (given.language != it) { result.add("${path}language ${given.language} != ${it}") }
    }

    expected.projectPath?.let {
        if (diffPath(given.getProjectPath(), it) != "") { result.add(diffPath(given.getProjectPath(), it, "${path}projectPath.")) }
    }

    expected.mainPath?.let {
        if (diffPath(given.getMainPath(), it) != "") { result.add(diffPath(given.getMainPath(), it, "${path}mainPath.")) }
    }

    expected.fixturesPath?.let {
        if (diffPath(given.getFixturesPath(), it) != "") { result.add(diffPath(given.getFixturesPath(), it, "${path}fixturesPath.")) }
    }

    expected.testsPath?.let {
        if (diffPath(given.getTestsPath(), it) != "") { result.add(diffPath(given.getTestsPath(), it, "${path}testsPath.")) }
    }

    expected.onlyParts?.let {
        if (given.onlyParts.size != it.size) { result.add("${path}onlyParts size ${given.onlyParts.size} != ${it.size}") }
        given.onlyParts.forEachIndexed { idx, entry -> if (entry != it[idx]) { result.add("${path}onlyParts[${idx}] ${entry} != ${it[idx]}") } }
    }

    expected.generateWeb?.let {
        if (given.generateWeb != it) { result.add("${path}generateWeb ${given.generateWeb} != ${it}") }
    }

    return result.joinToString("\n")
}

data class ExpectedModuleOperationArgs(
    var hlaFolderPath: String? = null,
    var profileName: String? = null,
    var moduleName: String? = null,
)
fun diffModuleOperationArgs(given: ModuleOperationArgs, expectedInit: ExpectedModuleOperationArgs.() -> Unit, path: String = ""): String {
    val expected = ExpectedModuleOperationArgs().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.hlaFolderPath?.let {
        if (diffPath(given.hlaFolderPath, it) != "") { result.add(diffPath(given.hlaFolderPath, it, "${path}hlaFolderPath.")) }
    }

    expected.profileName?.let {
        if (diffProfileName(given.profileName, it) != "") { result.add(diffProfileName(given.profileName, it, "${path}profileName.")) }
    }

    expected.moduleName?.let {
        if (diffModuleName(given.moduleName, it) != "") { result.add(diffModuleName(given.moduleName, it, "${path}moduleName.")) }
    }

    return result.joinToString("\n")
}

data class ExpectedAllModulesOperationArgs(
    var hlaFolderPath: String? = null,
    var profileName: String? = null,
)
fun diffAllModulesOperationArgs(given: AllModulesOperationArgs, expectedInit: ExpectedAllModulesOperationArgs.() -> Unit, path: String = ""): String {
    val expected = ExpectedAllModulesOperationArgs().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.hlaFolderPath?.let {
        if (diffPath(given.hlaFolderPath, it) != "") { result.add(diffPath(given.hlaFolderPath, it, "${path}hlaFolderPath.")) }
    }

    expected.profileName?.let {
        if (diffProfileName(given.profileName, it) != "") { result.add(diffProfileName(given.profileName, it, "${path}profileName.")) }
    }

    return result.joinToString("\n")
}