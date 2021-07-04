import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 고객 관리
 */
public class CustomerManage {

    private ArrayList<Customer> customers = new ArrayList<>();
    private String fileName = "customerList.txt";

    /**
     * 예약한 고객의 예약 정보 변경
     */
    public void reservationCustomer(int roomNumber, String name) {
        for(Customer customer : customers) {
            if(customer.getName().equals(name) && customer.getRoomNumber().equals(null)) {
                customer.setRoomNumber(String.valueOf(roomNumber));
                break;
            }
        }
    }

    /**
     * 예약 취소 한 고객의 예약 정보 변경
     */
    public void updateCustomer(int roomNumber, String name) {
        for(Customer customer : customers) {
            if(customer.getName().equals(name) && customer.getRoomNumber().equals(roomNumber)) {
                customer.setRoomNumber(null);
            }
        }
    }

    /**
     * 고객 등록
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("회원 가입이 완료되었습니다.");
        System.out.println("환영합니다.^^");
    }

    /**
     * 고객 삭제
     */
    public void removeCustomer(String name) {
        for(int i=0; i<customers.size(); i++) {
            if(customers.get(i).getName().equals(name)) {
                customers.remove(i);
                System.out.println("회원 탈퇴되었습니다.");
                return;
            }
        }
        // 이름이 검색되지 않는다면
        System.out.println("등록되지 않은 회원입니다.");
        return;
    }

    /**
     * 고객 전체 조회
     */
    public void showCustomer() {
        if(customers.size() == 0 ) System.out.println("등록된 회원이 없습니다.");
        for(int i=0; i<customers.size(); i++) {
            System.out.println(customers.get(i).getName() + " " + customers.get(i).getPhone() + " " + customers.get(i).getRoomNumber());
        }

    }

    /**
     * 고객 한 명만 조회
     */
    public boolean findCustomer(String name, String phone) {
        for(int i=0; i<customers.size(); i++) {
            // 이름이 일치하는 사람이 있다면
            if(customers.get(i).getName().equals(name) && customers.get(i).getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 고객 명단 파일로 저장
     */
    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Customer customer : customers) {
                bufferedWriter.write(customer.getName() + ",");
                bufferedWriter.write(customer.getPhone() + ",");
                bufferedWriter.write(customer.getRoomNumber());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 고객 명단 파일에서 불러오기
     */
    public void loadFile() {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                String name = stringTokenizer.nextToken();
                String tel = stringTokenizer.nextToken();
                String roomNumber = stringTokenizer.nextToken();
                customers.add(new Customer(name, tel, roomNumber));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
