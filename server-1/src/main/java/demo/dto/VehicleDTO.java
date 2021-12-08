package demo.dto;

/**
 * Created by Junkie on 08/12/2021.
 **/
public class VehicleDTO extends AbstractDTO {
    private String numberPlate;
    private boolean hasTicket;
    private Long studentId;
    private Object studentObject;

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public boolean isHasTicket() {
        return hasTicket;
    }

    public void setHasTicket(boolean hasTicket) {
        this.hasTicket = hasTicket;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Object getStudentObject() {
        return studentObject;
    }

    public void setStudentObject(Object studentObject) {
        this.studentObject = studentObject;
    }
}
