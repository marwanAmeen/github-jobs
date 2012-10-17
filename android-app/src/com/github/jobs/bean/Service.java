package com.github.jobs.bean;

/**
 * @author cristian
 * @version 1.0
 */
public class Service {
    private long id;
    private String type;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (id != service.id) return false;
        if (data != null ? !data.equals(service.data) : service.data != null) return false;
        if (type != null ? !type.equals(service.type) : service.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
