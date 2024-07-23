package cm.domeni.template.freemarker.experience.generate;

import cm.domeni.template.freemarker.experience.model.ClassSpecification;
import cm.domeni.template.freemarker.experience.model.FieldsSpecification;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GeneratedJavaClassForMustacheFileTest {
    private final GeneratedJavaClassForMustacheFile objetUnderTest = new GeneratedJavaClassForMustacheFile();
    @Test
    @SneakyThrows
    @DisplayName(
            """
                    Given the list of ClassSpecifications is not empty and the path is correct
                    When calling method generateJavaSourceFiles
                    Then should be generate Java classes at the path passed as parameter
                    """
    )
    void test_1(){
        final var fieldSpecification1 = new FieldsSpecification("testGeneratedFieldsPojo1", "String");
        final var fieldSpecification2 = new FieldsSpecification("testGeneratedFieldsPojo2", "String");
        final var fieldSpecification3 = new FieldsSpecification("testGeneratedFieldsPojo3", "String");
        final var fieldSpecification4 = new FieldsSpecification("testGeneratedFieldsPojo4", "String");

        final var testGeneratedClassPojo1 = new ClassSpecification("TestGeneratedClassPojo1", new ArrayList<>(List.of(fieldSpecification1, fieldSpecification2)));
        final var testGeneratedClassPojo2 = new ClassSpecification("TestGeneratedClassPojo2", new ArrayList<>(List.of(fieldSpecification3, fieldSpecification4)));

        final var outputDirectory = Files.createTempDirectory(Path.of("target"),"testGeneratedFieldsPojo");

        objetUnderTest.generated(
                new ArrayList<>(List.of(testGeneratedClassPojo1, testGeneratedClassPojo2)),
                outputDirectory.toFile()
        );

        assertThat(Files.readAllLines(outputDirectory.resolve("TestGeneratedClassPojo1.java")))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public class TestGeneratedClassPojo1 {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public TestGeneratedClassPojo1() {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("private String testGeneratedFieldsPojo1;"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("private String testGeneratedFieldsPojo2;"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public void setTestGeneratedFieldsPojo1(String testGeneratedFieldsPojo1) {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("this.testGeneratedFieldsPojo1 = testGeneratedFieldsPojo1;"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public String getTestGeneratedFieldsPojo1() {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("return testGeneratedFieldsPojo1;"));

        assertThat(Files.readAllLines(outputDirectory.resolve("TestGeneratedClassPojo2.java")))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public class TestGeneratedClassPojo2 {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public TestGeneratedClassPojo2() {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("private String testGeneratedFieldsPojo3;"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("private String testGeneratedFieldsPojo4;"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public void setTestGeneratedFieldsPojo3(String testGeneratedFieldsPojo3) {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("this.testGeneratedFieldsPojo3 = testGeneratedFieldsPojo3;"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("public String getTestGeneratedFieldsPojo3() {"))
                .anyMatch(new GeneratedJavaClassTest.AnEntryContainsText("return testGeneratedFieldsPojo3;"));

    }

}