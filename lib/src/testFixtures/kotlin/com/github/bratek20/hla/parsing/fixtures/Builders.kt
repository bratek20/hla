// DO NOT EDIT! Autogenerated by HLA tool

package com.github.bratek20.hla.parsing.fixtures

import com.github.bratek20.hla.definitions.api.*
import com.github.bratek20.hla.definitions.fixtures.*
import com.github.bratek20.hla.directory.api.*
import com.github.bratek20.hla.directory.fixtures.*
import com.github.bratek20.hla.facade.api.*
import com.github.bratek20.hla.facade.fixtures.*

import com.github.bratek20.hla.parsing.api.*

data class ModuleGroupDef(
    var name: String = "someValue",
    var modules: List<(ModuleDefinitionDef.() -> Unit)> = emptyList(),
    var profile: (HlaProfileDef.() -> Unit) = {},
    var dependencies: List<(ModuleGroupDef.() -> Unit)> = emptyList(),
)
fun moduleGroup(init: ModuleGroupDef.() -> Unit = {}): ModuleGroup {
    val def = ModuleGroupDef().apply(init)
    return ModuleGroup.create(
        name = GroupName(def.name),
        modules = def.modules.map { it -> moduleDefinition(it) },
        profile = hlaProfile(def.profile),
        dependencies = def.dependencies.map { it -> moduleGroup(it) },
    )
}