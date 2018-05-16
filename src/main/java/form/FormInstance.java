package form;

import java.util.Map;

public class FormInstance {

    private int id;

    private int formDefId;

    private Map<String, Object> formData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormDefId() {
        return formDefId;
    }

    public void setFormDefId(int formDefId) {
        this.formDefId = formDefId;
    }

    public Map<String, Object> getFormData() {
        return formData;
    }

    public void setFormData(Map<String, Object> formData) {
        this.formData = formData;
    }
}
