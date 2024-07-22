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
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratedJavaClassTest {
    private final GeneratedJavaClass objetUnderTest = new GeneratedJavaClass();

    @Test
    @SneakyThrows
    @DisplayName(
            """
                    Given the list of ClassSpecifications is not empty and the path is correct
                    When calling method generateJavaSourceFiles
                    Then should be generate Java classes at the path passed as parameter
                    """
    )
    void generateJavaSourceFilesTest() {
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
                .anyMatch(new AnEntryContainsText("public class TestGeneratedClassPojo1 {"))
                .anyMatch(new AnEntryContainsText("public TestGeneratedClassPojo1() {"))
                .anyMatch(new AnEntryContainsText("private String testGeneratedFieldsPojo1;"))
                .anyMatch(new AnEntryContainsText("private String testGeneratedFieldsPojo2;"))
                .anyMatch(new AnEntryContainsText("public void setTestGeneratedFieldsPojo1(String testGeneratedFieldsPojo1) {"))
                .anyMatch(new AnEntryContainsText("this.testGeneratedFieldsPojo1 = testGeneratedFieldsPojo1;"))
                .anyMatch(new AnEntryContainsText("public String getTestGeneratedFieldsPojo1() {"))
                .anyMatch(new AnEntryContainsText("return testGeneratedFieldsPojo1;"));

        assertThat(Files.readAllLines(outputDirectory.resolve("TestGeneratedClassPojo2.java")))
                .anyMatch(new AnEntryContainsText("public class TestGeneratedClassPojo2 {"))
                .anyMatch(new AnEntryContainsText("public TestGeneratedClassPojo2() {"))
                .anyMatch(new AnEntryContainsText("private String testGeneratedFieldsPojo3;"))
                .anyMatch(new AnEntryContainsText("private String testGeneratedFieldsPojo4;"))
                .anyMatch(new AnEntryContainsText("public void setTestGeneratedFieldsPojo3(String testGeneratedFieldsPojo3) {"))
                .anyMatch(new AnEntryContainsText("this.testGeneratedFieldsPojo3 = testGeneratedFieldsPojo3;"))
                .anyMatch(new AnEntryContainsText("public String getTestGeneratedFieldsPojo3() {"))
                .anyMatch(new AnEntryContainsText("return testGeneratedFieldsPojo3;"));
    }

    record AnEntryContainsText(String expectedText) implements Predicate<String> {
        @Override
        public boolean test(String s) {
            return expectedText.contains(s);
        }
    }
}