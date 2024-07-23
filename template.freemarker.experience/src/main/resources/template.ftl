public class ${classSpecification.name} {

    <#list classSpecification.fieldsSpecifications as field>
        private ${field.type?cap_first} ${field.name};
    </#list>
        public ${classSpecification.name?cap_first} (){
        }
    <#list classSpecification.fieldsSpecifications as field>
        public ${field.type?cap_first} get${field.name?cap_first}() {
        return this.${field.name};
        }
        public set${field.name?cap_first} (${field.type?cap_first} ${field.name}) {
        return this.${field.name} = ${field.name};
        }

    </#list>
}