package cm.domeni.template.freemarker.experience.model;

public class FieldsSpecification {
private String name;
private String type;

    public FieldsSpecification(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
