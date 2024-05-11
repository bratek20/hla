package pl.bratek20.hla.app;

import pl.bratek20.architecture.context.spring.SpringContextBuilder;
import pl.bratek20.hla.definitions.api.ModuleName;
import pl.bratek20.hla.directory.api.Path;
import pl.bratek20.hla.directory.impl.DirectoriesLogic;
import pl.bratek20.hla.directory.impl.DirectoryModule;
import pl.bratek20.hla.facade.api.GenerateModuleArgs;
import pl.bratek20.hla.facade.api.HlaFacade;
import pl.bratek20.hla.facade.impl.FacadeModule;
import pl.bratek20.hla.generation.api.ModuleLanguage;

public class Main {
    public static void main(String[] args) {
        var context = new SpringContextBuilder()
            .withModules(
                new FacadeModule(),
                new DirectoryModule()
            )
            .build();

        var directories = context.get(DirectoriesLogic.class);
        var facade = context.get(HlaFacade.class);

        var moduleName = new ModuleName(args[0]);
        var language = ModuleLanguage.valueOf(args[1]);
        var inPath = new Path(args[2]);

        var outPath = new Path("../tmp");
        directories.deleteDirectory(outPath);

        facade.generateModule(
            new GenerateModuleArgs(
                moduleName,
                language,
                inPath,
                outPath
            )
        );
    }
}