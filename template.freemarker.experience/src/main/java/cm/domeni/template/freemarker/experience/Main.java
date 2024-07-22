package cm.domeni.template.freemarker.experience;

import cm.domeni.template.freemarker.experience.generate.GeneratedJavaClass;
import cm.domeni.template.freemarker.experience.loarder.Loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            throw new IllegalArgumentException("2 Arguments are expected, 1. YAML file URL, 2. out put dire");
        }
        final String yamlFile = Objects.requireNonNull(args[0], "Yaml file is missing");
        final String outputDir = Objects.requireNonNull(args[1], "output dir is missing");

        final var loader = new Loader();
        final var classSpecifications = loader.read(new File(yamlFile));
        final var generatedJavaClass = new GeneratedJavaClass();
        generatedJavaClass.generated(classSpecifications, new File(outputDir));
    }
}