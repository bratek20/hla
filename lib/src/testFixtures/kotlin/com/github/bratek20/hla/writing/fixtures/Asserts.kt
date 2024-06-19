// DO NOT EDIT! Autogenerated by HLA tool

package com.github.bratek20.hla.writing.fixtures

import org.assertj.core.api.Assertions.assertThat

import com.github.bratek20.hla.directory.api.*
import com.github.bratek20.hla.directory.fixtures.*
import com.github.bratek20.hla.facade.api.*
import com.github.bratek20.hla.facade.fixtures.*
import com.github.bratek20.hla.generation.api.*
import com.github.bratek20.hla.generation.fixtures.*

import com.github.bratek20.hla.writing.api.*

fun assertWriteArgs(given: WriteArgs, expectedInit: ExpectedWriteArgs.() -> Unit) {
    val diff = diffWriteArgs(given, expectedInit)
    assertThat(diff).withFailMessage(diff).isEqualTo("")
}