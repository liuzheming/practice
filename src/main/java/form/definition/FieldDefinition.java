package form.definition;

public class FieldDefinition {

    private int id;

    private FormDefinition formDef;

    private String name;    // 唯一标识符

    private String label;

    private String dataType;

    private String pattern;

    private int isStandard;     // 是否标准字段

    private String desc;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FormDefinition getFormDef() {
        return formDef;
    }

    public void setFormDef(FormDefinition formDef) {
        this.formDef = formDef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(int isStandard) {
        this.isStandard = isStandard;
    }

    @Override
    public String toString() {
        return "FieldDefinition{" +
                "id=" + id +
                ", formDef=" + formDef.getName() +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", dataType='" + dataType + '\'' +
                ", pattern='" + pattern + '\'' +
                ", isStandard=" + isStandard +
                ", desc='" + desc + '\'' +
                '}';
    }
}