package cm.domeni.template.freemarker.experience.model;

public class FieldsSpecification {
private String name;
private String type;
private String capitalizedName;

    public FieldsSpecification(String name, String type) {
        this.name = name;
        this.type = type;
        this.capitalizedName = capitalizedName();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    public String capitalizedName() {
        if (this.name == null || this.name.isEmpty()) {
            return name;
        }
        return this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
    }
}
