// DO NOT EDIT! Autogenerated by HLA tool

package com.some.pkg.simplemodule.fixtures

import org.assertj.core.api.Assertions.assertThat

import com.some.pkg.simplemodule.api.*

fun assertSimpleId(given: SimpleId, expected: String) {
    val diff = diffSimpleId(given, expected)
    assertThat(diff).withFailMessage(diff).isEqualTo("")
}