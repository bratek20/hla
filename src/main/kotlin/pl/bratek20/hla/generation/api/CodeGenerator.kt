package pl.bratek20.hla.generation.api

import pl.bratek20.hla.directory.Directory
import pl.bratek20.hla.model.HlaModule

interface CodeGenerator {
    fun generateCode(module: HlaModule): Directory
}