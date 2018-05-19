package form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import form.definition.FieldDefinition;
import form.definition.FormDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericFormService implements FormService {


    private FormDefManager formDefMgr;

    private ObjectMapper objectMapper = new ObjectMapper();

    public GenericFormService(FormDefManager formDefMgr) {
        this.formDefMgr = formDefMgr;
    }

    @Override
    public boolean verifyForm(FormInstance formInst) throws RuntimeException {
        FormDefinition formDefinition =
                formDefMgr.getById(formInst.getFormDefId());

        List<FieldDefinition> list = formDefinition.getFieldDefList();

        // 只支持对部分String、Integer数据的格式验证
        for (FieldDefinition fd : list) {
            switch (fd.getDataType()) {
                case "String_":
                    String strVal = (String) formInst.getFormData().get(fd.getName());
                    // email
                    if (FormPattern.PATTERN_MAP.containsKey(fd.getPattern())) {
                        Pattern pattern = Pattern.compile(FormPattern.PATTERN_MAP.get(fd.getPattern()));
                        Matcher matcher = pattern.matcher(strVal);
                        System.out.println("是否符合格式: " + fd.getName() + " --- " + fd.getPattern() + " --- " + matcher.matches());
                        if (!matcher.matches()) throw new RuntimeException("参数不合规:[" + fd.getName() + "]");
                    }
                    System.out.println("String_类型参数" + fd.getName() + "=" + strVal);
                    break;
                case "int_":
                    int intVal = (int) formInst.getFormData().get(fd.getName());
                    System.out.println("int_类型参数" + fd.getName() + "=" + intVal);
                    break;
            }
        }
        return true;
    }

    @Override
    public void saveForm(FormInstance formInst) {

        FormDefinition formDefinition =
                formDefMgr.getById(formInst.getFormDefId());
//        PropertyDescriptor[] pds = null;

        GenericFormPo formPo = new GenericFormPo();
        Map<String, Object> other = new HashMap<>();    // 自定义字段
        for (FieldDefinition fd : formDefinition.getFieldDefList()) {

            // 访客信息字段
            switch (fd.getName()) {
                case "name":
                    formPo.setName((String) formInst.getFormData().get("name"));
                    break;
                case "phone":
                    formPo.setPhone((String) formInst.getFormData().get("phone"));
                    break;
                case "email":
                    formPo.setEmail((String) formInst.getFormData().get("email"));
                    break;
                case "pageTitle":
                    formPo.setPageTitle((String) formInst.getFormData().get("pageTitle"));
                    break;
                case "pageURL":
                    formPo.setPageURL((String) formInst.getFormData().get("pageURL"));
                    break;
                case "referrerURL":
                    formPo.setReferrerURL((String) formInst.getFormData().get("referrerURL"));
                    break;
                case "referrerDomain":
                    formPo.setReferrerDomain((String) formInst.getFormData().get("referrerDomain"));
                    break;
                case "campaignCode":
                    formPo.setCampaignCode((String) formInst.getFormData().get("campaignCode"));
                    break;
                case "userIP":
                    formPo.setUserIP((String) formInst.getFormData().get("userIP"));
                    break;
                case "deviceType":
                    formPo.setDeviceType((String) formInst.getFormData().get("deviceType"));
                    break;
                case "userAgent":
                    formPo.setUserAgent((String) formInst.getFormData().get("userAgent"));
                    break;
                case "isWillingToContact":
                    formPo.setIsWillingToContact((String) formInst.getFormData().get("isWillingToContact"));
                    break;
                case "jdCloudAccount":
                    formPo.setJdCloudAccount((String) formInst.getFormData().get("jdCloudAccount"));
                    break;
                default:
                    other.put(fd.getName(), formInst.getFormData().get(fd.getName()));
            }

//            for (Map.Entry<String, Object> entry :
//                    formInst.getFormData().entrySet()) {
//                Object value = entry.getKey();
//            }
        }

        try {
            formPo.setOthers(objectMapper.writeValueAsString(other));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        System.out.println("==================准备GenericFormPo结束==================");
        System.out.println(formPo);
        System.out.println("==================准备GenericFormPo结束==================");

        _FormTest.FORM_INST_LIST.add(formPo);

    }
}
