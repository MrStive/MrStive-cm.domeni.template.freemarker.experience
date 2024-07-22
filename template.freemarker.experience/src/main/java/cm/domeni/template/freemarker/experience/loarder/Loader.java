package cm.domeni.template.freemarker.experience.loarder;

import cm.domeni.template.freemarker.experience.model.ClassSpecification;
import cm.domeni.template.freemarker.experience.model.FieldsSpecification;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loader {
    public List<ClassSpecification> read(File file) throws FileNotFoundException {
        var yamlFile = new Yaml();
        Map<String, Map<String, String>> load = yamlFile.load(new FileReader(file));
        return load.
                entrySet()
                .stream()
                .map(
                        stringMapEntry -> {
                            final var fieldsSpecifications = stringMapEntry
                                    .getValue()
                                    .entrySet()
                                    .stream()
                                    .map(
                                            stringStringEntry -> new FieldsSpecification(
                                                    stringStringEntry.getKey(),
                                                    stringStringEntry.getValue()
                                            )
                                    ).toList();
                            return new ClassSpecification(
                                    stringMapEntry.getKey(),
                                    fieldsSpecifications
                            );
                        }
                ).collect(Collectors.toList());
    }
}
