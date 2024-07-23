package cm.domeni.template.freemarker.experience.generate;

import cm.domeni.template.freemarker.experience.model.ClassSpecification;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GeneratedJavaClassForMustacheFile {

    public void generated(List<ClassSpecification> classSpecifications, File yamlPath) {


        classSpecifications.forEach(
                classSpecification -> {
                    try {
                        final var freemarkerDataModel = new HashMap<String, Object>();
                        freemarkerDataModel.put("classSpecification", classSpecification);
                        final var fileToBeGenerate = new File(yamlPath, classSpecification.getName() + ".java");
                        final var generatedFile = new FileWriter(fileToBeGenerate);

                        MustacheFactory mf = new DefaultMustacheFactory();
                        Mustache mustache = mf.compile("template.mustache");
                        mustache.execute(generatedFile, freemarkerDataModel).flush();
                    } catch (IOException e) {
                        System.out.println("can not fine file " + yamlPath.getPath() + " Because " + e.getMessage());
                    }
                }
        );
    }
}
