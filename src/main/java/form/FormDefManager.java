package form;

import form.definition.FormDefinition;

import java.util.ArrayList;
import java.util.List;


/**
 * 新增、删除、查询 FormDefinition 入口
 */
public class FormDefManager {

    private static List<FormDefinition> FORM_DEF_LIST = new ArrayList<>();

    public void addFormDef(FormDefinition formDef) {
        FORM_DEF_LIST.add(formDef);
    }

    public void deleteFormDef(Integer formDefId) {
        FORM_DEF_LIST.removeIf(formDefinition -> formDefinition.getId() == formDefId);
    }

    public List<FormDefinition> queryPage() {
        return this.FORM_DEF_LIST;
    }

    public FormDefinition getById(int id) {
        FormDefinition formDef = null;
        for (FormDefinition def : this.FORM_DEF_LIST) {
            if (def.getId() == id) formDef = def;
        }
        return formDef;
    }

}
