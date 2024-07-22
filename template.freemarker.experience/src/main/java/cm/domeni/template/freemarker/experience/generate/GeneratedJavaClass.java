package cm.domeni.template.freemarker.experience.generate;

import cm.domeni.template.freemarker.experience.model.ClassSpecification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class GeneratedJavaClass {
    private final Configuration configuration;

    public GeneratedJavaClass() {
        configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "");

        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
    }

    public void generated(List<ClassSpecification> classSpecifications, File yamlPath) {
        classSpecifications.forEach(
                classSpecification -> {
                    try {
                        final var freemarkerDataModel = new HashMap<String, Object>();
                        freemarkerDataModel.put("classSpecification", classSpecification);
                        final var template = configuration.getTemplate("template.ftl");
                        final var fileToBeGenerate = new File(yamlPath, classSpecification.getName() + ".java");
                        final var generatedFile = new FileWriter(fileToBeGenerate);
                        template.process(freemarkerDataModel, generatedFile);
                    } catch (IOException e) {
                        System.out.println("can not fine file "+yamlPath.getPath() + " Because " + e.getMessage());
                    } catch (TemplateException e) {
                        System.out.print(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
