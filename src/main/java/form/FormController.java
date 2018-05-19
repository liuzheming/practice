package form;

public class FormController {

    public FormController(FormDefManager fdm) {
        this.formService = new GenericFormService(fdm);
    }

    private FormService formService;

    /**
     * 暂时默认被提交的form数据格式都是正确的，不要要验证
     *
     * @param formInst 表单实例
     */
    public void submit(FormInstance formInst) {

//        FormDefinition formDef = formDefController.getById(formInst.getFormDefId());


        formService.verifyForm(formInst);

        formService.saveForm(formInst);

        // 根据取formData
//        for (FieldDefinition fieldDef : formDef.getFieldDefList()) {
//            Object value = formInst.getFormData().get(fieldDef.getName());
////         标准化字段，直接填充
////         非标准化字段，拼接后填充
//        }


    }

}