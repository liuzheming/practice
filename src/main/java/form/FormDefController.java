package form;

import form.definition.FormDefinition;

import java.util.ArrayList;
import java.util.List;


/**
 * 新增、删除、查询 FormDefinition 入口
 */
public class FormDefController {


    private List<FormDefinition> formDefs = new ArrayList<>();

    public void addFormDef(FormDefinition formDef) {
        formDefs.add(formDef);
    }

    public void deleteFormDef(Integer formDefId) {
        formDefs.removeIf(formDefinition -> formDefinition.getId() == formDefId);
    }

    public List<FormDefinition> queryPage() {
        return this.formDefs;
    }

    public FormDefinition getById(int id) {
        FormDefinition formDef = null;
        for (FormDefinition def : this.formDefs) {
            if (def.getId() == id) formDef = def;
        }
        return formDef;
    }

}
