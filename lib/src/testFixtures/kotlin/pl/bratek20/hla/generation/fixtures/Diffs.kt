// DO NOT EDIT! Autogenerated by HLA tool

package pl.bratek20.hla.generation.fixtures

import pl.bratek20.hla.definitions.api.*
import pl.bratek20.hla.definitions.fixtures.*
import pl.bratek20.hla.directory.api.*
import pl.bratek20.hla.directory.fixtures.*
import pl.bratek20.hla.facade.api.*
import pl.bratek20.hla.facade.fixtures.*

import pl.bratek20.hla.generation.api.*

data class ExpectedGenerateArgs(
    var moduleName: String? = null,
    var modules: List<(ExpectedModuleDefinition.() -> Unit)>? = null,
    var onlyUpdate: Boolean? = null,
    var profile: (ExpectedHlaProfile.() -> Unit)? = null,
)
fun diffGenerateArgs(given: GenerateArgs, expectedInit: ExpectedGenerateArgs.() -> Unit, path: String = ""): String {
    val expected = ExpectedGenerateArgs().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.moduleName?.let {
        if (diffModuleName(given.moduleName, it) != "") { result.add(diffModuleName(given.moduleName, it, "${path}moduleName.")) }
    }

    expected.modules?.let {
        if (given.modules.size != it.size) { result.add("${path}modules size ${given.modules.size} != ${it.size}") }
        given.modules.forEachIndexed { idx, entry -> if (diffModuleDefinition(entry, it[idx]) != "") { result.add(diffModuleDefinition(entry, it[idx], "${path}modules[${idx}].")) } }
    }

    expected.onlyUpdate?.let {
        if (given.onlyUpdate != it) { result.add("${path}onlyUpdate ${given.onlyUpdate} != ${it}") }
    }

    expected.profile?.let {
        if (diffHlaProfile(given.profile, it) != "") { result.add(diffHlaProfile(given.profile, it, "${path}profile.")) }
    }

    return result.joinToString("\n")
}

data class ExpectedGenerateResult(
    var main: (ExpectedDirectory.() -> Unit)? = null,
    var fixtures: (ExpectedDirectory.() -> Unit)? = null,
    var tests: (ExpectedDirectory.() -> Unit)? = null,
)
fun diffGenerateResult(given: GenerateResult, expectedInit: ExpectedGenerateResult.() -> Unit, path: String = ""): String {
    val expected = ExpectedGenerateResult().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.main?.let {
        if (diffDirectory(given.main, it) != "") { result.add(diffDirectory(given.main, it, "${path}main.")) }
    }

    expected.fixtures?.let {
        if (diffDirectory(given.fixtures, it) != "") { result.add(diffDirectory(given.fixtures, it, "${path}fixtures.")) }
    }

    expected.tests?.let {
        if (diffDirectory(given.tests!!, it) != "") { result.add(diffDirectory(given.tests!!, it, "${path}tests.")) }
    }

    return result.joinToString("\n")
}