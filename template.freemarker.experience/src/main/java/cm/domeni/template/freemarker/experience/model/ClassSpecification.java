package cm.domeni.template.freemarker.experience.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ClassSpecification {
    private String name;
    private List<FieldsSpecification> fieldsSpecifications;
}
