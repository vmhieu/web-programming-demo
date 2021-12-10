package demo.dto;

import java.util.Date;

/**
 * Created by Junkie on 09/12/2021.
 **/
public class BillDTO {
    private String type;
    private Date createdDate;
    private Object object;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
