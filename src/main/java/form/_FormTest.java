package form;

import form.definition.FieldDefinition;
import form.definition.FormDefinition;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * 1、怎么解字段入库的问题  ，用了最笨的办法，硬编码
 * 2、怎么解决权限控制的问题    TODO
 * 3、怎么解决field格式验证的问题   TODO
 */
public class _FormTest {

    private FormDefManager formDefMgr = new FormDefManager();

    @Test
    public void prepareFormDef() {

        FormDefinition formDef = new FormDefinition();
        formDef.setName("弼马温入职表");
        formDef.setDesc("入职弼马温需签署的文件");
//        formDef.setId((int) (Math.random() * 100));
        formDef.setId(100);

        // 姓名
        FieldDefinition field_name = new FieldDefinition();
        field_name.setDesc("员工名称");
        field_name.setLabel("staff_name");
        field_name.setName("name");
        field_name.setDataType("String_");   // TODO 应该定义好类型字典
        field_name.setIsStandard(1);
        field_name.setFormDef(formDef);

        // 电话
        FieldDefinition field_phone = new FieldDefinition();
        field_phone.setDesc("员工电话");
        field_phone.setLabel("staff_phone");
        field_phone.setName("phone");
        field_phone.setDataType("String_");
        field_phone.setIsStandard(1);
        field_phone.setFormDef(formDef);

        // 邮箱
        FieldDefinition field_email = new FieldDefinition();
        field_email.setDesc("员工邮箱");
        field_email.setLabel("staff_email");
        field_email.setName("email");
        field_email.setDataType("String_");
        field_email.setIsStandard(1);
        field_email.setFormDef(formDef);

        // 年龄
        FieldDefinition field_age = new FieldDefinition();
        field_age.setDesc("员工年龄");
        field_age.setLabel("staff_age");
        field_age.setName("age");
        field_age.setDataType("int_");
        field_age.setIsStandard(1);
        field_age.setFormDef(formDef);

        formDef.addFieldDef(field_name);
        formDef.addFieldDef(field_phone);
        formDef.addFieldDef(field_email);
        formDef.addFieldDef(field_age);

        formDefMgr.addFormDef(formDef);

        System.out.println(formDefMgr.queryPage());
    }

    @After
    public void submitFormInst() {
        FormInstance formInst = new FormInstance();
        formInst.setFormDefId(100);
        Map<String, Object> formData = new HashMap<>();
        formData.put("name", "孙悟空");
        formData.put("phone", "110");
        formData.put("email", "sun_0006@126.com");
        formData.put("age", 800);
        formInst.setFormData(formData);
        FormController formController = new FormController(formDefMgr);
        formController.submit(formInst);
    }

    public void mainTest(String... args) {

        // 使用json数据注册FormDef


        // 表单提交

    }


}
