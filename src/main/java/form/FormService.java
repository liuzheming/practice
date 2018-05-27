package form;

public interface FormService {

    boolean verifyForm(FormInstance formInst) throws RuntimeException;

    void saveForm(FormInstance formInst);

}
