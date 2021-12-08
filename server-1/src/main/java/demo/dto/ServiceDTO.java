package demo.dto;

/**
 * Created by Junkie on 01/12/2021.
 **/
public class ServiceDTO extends AbstractDTO {
    private String serviceCode;
    private String name;
    private float priceUnit;
    private float price;
    private Long studentId;
    private Object studentObject;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(float priceUnit) {
        this.priceUnit = priceUnit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Object getStudentObject() {
        return studentObject;
    }

    public void setStudentObject(Object studentObject) {
        this.studentObject = studentObject;
    }
}
