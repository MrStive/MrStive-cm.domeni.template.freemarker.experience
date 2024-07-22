package cm.domeni.template.freemarker.experience.loarder;

import cm.domeni.template.freemarker.experience.model.ClassSpecification;
import cm.domeni.template.freemarker.experience.model.FieldsSpecification;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderTest {
    private final Loader objectUnderTest = new Loader();

    @Test
    @SneakyThrows
    @DisplayName("""
            Given the FileReader contains a path to a yaml file containing at least one object
            When call method  read
            Then should see a list of ClassSpecification corresponding to the objet present of the yaml file
            """)
    void shouldTransformYamlFilesIntoListsOfYamlClassSpecificationReader(){

        String POJO_SPECS_FILE = "src/openapi/openApi.yaml";

        final var result = objectUnderTest.read(new File(POJO_SPECS_FILE));
        assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .anySatisfy(
                        classSpecification -> {
                            assertThat(classSpecification)
                                    .returns("User", ClassSpecification::getName);
                            assertThat(classSpecification.getFieldsSpecifications())
                                    .hasSize(3)
                                    .anySatisfy(fieldSpecification -> assertThat(fieldSpecification)
                                            .returns("userName", FieldsSpecification::getName)
                                            .returns("string", FieldsSpecification::getType)
                                    ) .anySatisfy(fieldSpecification -> assertThat(fieldSpecification)
                                            .returns("password", FieldsSpecification::getName)
                                            .returns("string", FieldsSpecification::getType)
                                    ) .anySatisfy(fieldSpecification -> assertThat(fieldSpecification)
                                            .returns("role", FieldsSpecification::getName)
                                            .returns("Role", FieldsSpecification::getType)
                                    );
                        }
                ).anySatisfy(
                        classSpecification -> {
                            assertThat(classSpecification)
                                    .returns("Role", ClassSpecification::getName);
                            assertThat(classSpecification.getFieldsSpecifications())
                                    .hasSize(2)
                                    .anySatisfy(fieldSpecification -> assertThat(fieldSpecification)
                                            .returns("id", FieldsSpecification::getName)
                                            .returns("string", FieldsSpecification::getType)
                                    ) .anySatisfy(fieldSpecification -> assertThat(fieldSpecification)
                                            .returns("nameRole", FieldsSpecification::getName)
                                            .returns("string", FieldsSpecification::getType)
                                    );
                        }
                );

    }

}