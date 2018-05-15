package form.definition;

import java.util.ArrayList;
import java.util.List;

public class FormDefinition {

    private int id;

    private String name;

    private List<FieldDefinition> fieldDefList = new ArrayList<>();

    private String desc;

    public void addFieldDef(FieldDefinition fieldDef) {
        fieldDefList.add(fieldDef);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldDefinition> getFieldDefList() {
        return fieldDefList;
    }

    public void setFieldDefList(List<FieldDefinition> fieldDefList) {
        this.fieldDefList = fieldDefList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "FormDefinition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fieldDefList=" + fieldDefList +
                ", desc='" + desc + '\'' +
                '}';
    }
}
