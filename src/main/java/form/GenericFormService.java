package form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import form.definition.FieldDefinition;
import form.definition.FormDefinition;

import java.util.HashMap;
import java.util.Map;

public class GenericFormService implements FormService {


    private FormDefManager formDefMgr;

    private ObjectMapper objectMapper = new ObjectMapper();

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

    }
}
