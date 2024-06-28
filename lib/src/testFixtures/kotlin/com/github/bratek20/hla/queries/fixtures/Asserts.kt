// DO NOT EDIT! Autogenerated by HLA tool

package com.github.bratek20.hla.queries.fixtures

import org.assertj.core.api.Assertions.assertThat

import com.github.bratek20.hla.definitions.api.*
import com.github.bratek20.hla.definitions.fixtures.*
import com.github.bratek20.hla.parsing.api.*
import com.github.bratek20.hla.parsing.fixtures.*

import com.github.bratek20.hla.queries.api.*

fun assertModuleDependency(given: ModuleDependency, expectedInit: ExpectedModuleDependency.() -> Unit) {
    val diff = diffModuleDependency(given, expectedInit)
    assertThat(diff).withFailMessage(diff).isEqualTo("")
}