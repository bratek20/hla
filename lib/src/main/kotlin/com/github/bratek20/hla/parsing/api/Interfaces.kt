// DO NOT EDIT! Autogenerated by HLA tool

package com.github.bratek20.hla.parsing.api

import com.github.bratek20.hla.definitions.api.*
import com.github.bratek20.hla.directory.api.*

interface ModuleDefinitionsParser {
    fun parse(path: Path): List<ModuleDefinition>
}