import java.io.Serializable;

public class Customer implements Serializable {

    private String name;
    private String phone;
    private String roomNumber;

    public Customer() {
    }


    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.roomNumber = "EMPTY";
    }

    public Customer(String name, String phone, String roomNumber) {
        this.name = name;
        this.phone = phone;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
