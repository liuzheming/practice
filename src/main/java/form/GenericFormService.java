package form;

import form.definition.FieldDefinition;
import form.definition.FormDefinition;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class GenericFormService implements FormService {


    private FormDefManager formDefMgr;

    public GenericFormService(FormDefManager formDefMgr) {
        this.formDefMgr = formDefMgr;
    }

    @Override
    public void verifyForm(FormInstance formInst) {

    }

    @Override
    public void saveForm(FormInstance formInst) {

        FormDefinition formDefinition =
                formDefMgr.getById(formInst.getFormDefId());
        PropertyDescriptor[] pds = null;
        Object formPo = null;
        try {
            pds = Introspector
                    .getBeanInfo(GenericFormPo.class).getPropertyDescriptors();
            Constructor constructor = GenericFormPo.class.getConstructor();
            formPo = constructor.newInstance();
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

//        GenericFormPo formPo = new GenericFormPo();
        for (FieldDefinition fd : formDefinition.getFieldDefList()) {
            for (PropertyDescriptor pd : pds) {
                if (pd.getName().equals(fd.getName())) {

                }
            }


//            for (Map.Entry<String, Object> entry :
//                    formInst.getFormData().entrySet()) {
//                Object value = entry.getKey();
//            }
        }

    }
}
