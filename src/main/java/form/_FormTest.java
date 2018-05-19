package form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import form.definition.FieldDefinition;
import form.definition.FormDefinition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * 1、怎么解字段入库的问题  ，用了最笨的办法，硬编码
 * 2、怎么解决权限控制的问题    在formDef增加status字段
 * 3、怎么解决field格式验证的问题   TODO 只支持String类型
 */
public class _FormTest {

    private FormDefManager formDefMgr = new FormDefManager();
    private FormController formController = new FormController(formDefMgr);
    public static List<GenericFormPo> FORM_INST_LIST = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
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
        field_phone.setPattern("phone");
        field_phone.setFormDef(formDef);

        // 邮箱
        FieldDefinition field_email = new FieldDefinition();
        field_email.setDesc("员工邮箱");
        field_email.setLabel("staff_email");
        field_email.setName("email");
        field_email.setDataType("String_");
        field_email.setIsStandard(1);
        field_email.setPattern("email");
        field_email.setFormDef(formDef);

        // 年龄
        FieldDefinition field_age = new FieldDefinition();
        field_age.setDesc("员工年龄");
        field_age.setLabel("staff_age");
        field_age.setName("age");
        field_age.setDataType("int_");
        field_age.setIsStandard(1);
        field_age.setFormDef(formDef);

        // 公司
        FieldDefinition field_company = new FieldDefinition();
        field_company.setDesc("公司名称");
        field_company.setLabel("company");
        field_company.setName("company");
        field_company.setDataType("String_");
//        field_age.setIsStandard(1);
        field_company.setFormDef(formDef);


        // 年龄
        FieldDefinition field_married = new FieldDefinition();
        field_married.setDesc("员工年龄");
        field_married.setLabel("married");
        field_married.setName("married");
        field_married.setDataType("String_");
        field_married.setIsStandard(1);
        field_married.setFormDef(formDef);


        // 年龄
        FieldDefinition field_address = new FieldDefinition();
        field_address.setDesc("地址");
        field_address.setLabel("address");
        field_address.setName("address");
        field_address.setDataType("String_");
        field_address.setIsStandard(1);
        field_address.setFormDef(formDef);


        formDef.addFieldDef(field_name);
        formDef.addFieldDef(field_phone);
        formDef.addFieldDef(field_email);
        formDef.addFieldDef(field_age);
        formDef.addFieldDef(field_company);
        formDef.addFieldDef(field_married);
        formDef.addFieldDef(field_address);


        formDefMgr.addFormDef(formDef);

        System.out.println(formDefMgr.queryPage());
    }

    @Test
    public void submitFormInst() {
        FormInstance formInst = new FormInstance();
        formInst.setFormDefId(100);
        Map<String, Object> formData = new HashMap<>();
        formData.put("name", "孙悟空");
        formData.put("phone", "18533336666");
        formData.put("email", "sun_0006@126.com");
        formData.put("age", 800);
        formData.put("company", "oracle");
        formData.put("married", "yes");
        formData.put("address", "朝阳区，北辰世纪中心A座");
        formInst.setFormData(formData);
//        FormController formController = new FormController(formDefMgr);


        try {
            System.out.println("打印JSON参数：");
            System.out.println(objectMapper.writeValueAsString(formInst));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("submit结束:" + formController.submit(formInst));
    }

    @After
    public void printForm() {

        //
        List<Map<String, Object>> list = new ArrayList<>();

        for (GenericFormPo po : FORM_INST_LIST) {
            try {
                BeanInfo info = Introspector.getBeanInfo(GenericFormPo.class);
                PropertyDescriptor[] pds = info.getPropertyDescriptors();
                Map<String, Object> bean = new LinkedHashMap<>();
                for (PropertyDescriptor pd : pds) {
                    System.out.println("调用getter方法: " + pd.getName() + "=" + pd.getReadMethod().invoke(po));
                    if ("others".equals(pd.getName())) {
                        String jsonStr = (String) pd.getReadMethod().invoke(po);
                        Map other = objectMapper.readValue(jsonStr, Map.class);
                        bean.putAll(other);
                    } else bean.put(pd.getName(), pd.getReadMethod().invoke(po));
                }
                list.add(bean);
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

        }


        System.out.println("打印已保存的表单：");
        System.out.println(list);

    }

    public void mainTest(String... args) {

        // 使用json数据注册FormDef


        // 表单提交

    }


}
