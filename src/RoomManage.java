import java.util.*;

/**
 * 예약 관리
 */
public class RoomManage {

    private static CustomerManage customerManage = new CustomerManage();
    private HashMap<Integer, String> rooms = new HashMap<>();

    /**
     * 객실 초기화
     */
    public void roomSetting() {
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                int roomNumber = (i * 100) + j;
                rooms.put(roomNumber, "EMPTY");
            }
        }
    }

    /**
     * 예약 등록
     */
    public void addReservation(int roomNumber, String name) {
        if (!rooms.get(roomNumber).equals("EMPTY")) {
            System.out.println("예약이 불가능한 객실입니다.");
        } else {
            rooms.put(roomNumber, name);
            System.out.println("예약되었습니다.");
        }
    }

    /**
     * 예약된 객실 1개 찾기
     */
    public int findReservationRoom(String name) {
        int key = 0;
        Iterator<Integer> iterator = rooms.keySet().iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            if (rooms.get(key).equals(name)) {
                System.out.println(key + "호");
                return key;
            }
        }
        return -1;
    }

    /**
     * 객실 조회 (정렬 후 출력)
     */
    public void showRoom() {
        TreeMap<Integer, String> tm = new TreeMap<>(rooms);
        Iterator<Integer> keys = tm.keySet().iterator();

        while (keys.hasNext()) {
            int key = keys.next();
            System.out.println(key + "호 : " + tm.get(key));
        }

    }

    /**
     * 예약 삭제
     */
    public void deleteReservation(int roomNumber, String name) {
        rooms.put(roomNumber, "EMPTY");
        customerManage.updateCustomer(roomNumber, name);
        System.out.println("객실 " + roomNumber + "호 예약이 취소되었습니다.");
    }

    /**
     * 객실 등록
     */
    public void addRoom(int roomNumber) {
        Iterator<Integer> iterator = rooms.keySet().iterator();
        while(iterator.hasNext()) {
            int key = iterator.next();
            if(key == roomNumber) {
                System.out.println("이미 존재하는 객실입니다.");
                return;
            }
        }
        rooms.put(roomNumber, "EMPTY");
        System.out.println("객실이 등록되었습니다.");
    }

    /**
     * 객실 삭제
     */
    public void deleteRoom(int roomNumber) {
        Iterator<Integer> iterator = rooms.keySet().iterator();
        while(iterator.hasNext()) {
            int key = iterator.next();
            if(key == roomNumber) {
                rooms.remove(roomNumber);
                System.out.println("삭제되었습니다.");
                return;
            }
        }
        System.out.println("존재하지 않는 객실입니다.");

    }
}
