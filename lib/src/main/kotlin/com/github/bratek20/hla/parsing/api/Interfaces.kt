// DO NOT EDIT! Autogenerated by HLA tool

package com.github.bratek20.hla.parsing.api

import com.github.bratek20.hla.definitions.api.*
import com.github.bratek20.hla.directory.api.*
import com.github.bratek20.hla.facade.api.*

interface ModuleDefinitionsParser {
    @Throws(
        UnknownRootSectionException::class,
    )
    fun parse(hlaFolder: Path, profile: ProfileName): List<ModuleDefinition>
}