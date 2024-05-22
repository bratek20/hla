package pl.bratek20.hla.generation.impl.languages.kotlin

import pl.bratek20.hla.directory.api.Path
import pl.bratek20.hla.generation.impl.core.ContentBuilderExtension
import pl.bratek20.hla.generation.impl.core.DomainContext
import pl.bratek20.hla.velocity.api.VelocityFileContentBuilder

fun srcPathToRootPackage(mainPath: Path): String {
    return mainPath.value
        .replace("src/main/kotlin/", "")
        .replace("src/main/java/", "")
        .replace("/", ".")
}

class PackageNameAndImportsExtension(
    private val c: DomainContext
) : ContentBuilderExtension {
    override fun extend(builder: VelocityFileContentBuilder) {
        val rootPackage = srcPathToRootPackage(c.profile.getMainPath());

        val imports = c.modules.getCurrentDependencies()
            .map { "$rootPackage.${it.value.lowercase()}" }


        builder
            .put("packageName", "$rootPackage.${c.module.name.value.lowercase()}")
            .put("imports", imports)
    }
}