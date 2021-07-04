import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HotelManageSystemMain {

    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static CustomerManage customerManage = new CustomerManage();
    private static RoomManage roomManage = new RoomManage();

    private static int roomNumber = 0;
    private static String name = null;
    private static String tel = null;

    private static int userChoice;
    private static int userSubChoice;

    public static void main(String[] args) {



        do {

            System.out.println("============= Hotel =============");
            System.out.println("1. 회원관리");
            System.out.println("2. 예약관리");
            System.out.println("3. 객실관리");
            System.out.println("0. 종료");

            System.out.print("메뉴를 입력하세요 >> ");
            userChoice = sc.nextInt();

            switch (userChoice) {
                // 회원 관리
                case 1 -> {
                    do {
                        System.out.println("============= 회원관리 =============");
                        System.out.println("1. 회원 등록");
                        System.out.println("2. 회원 삭제");
                        System.out.println("3. 회원 목록 조회");
                        System.out.println("4. 파일에 회원 저장");
                        System.out.println("5. 파일에서 회원 불러오기");
                        System.out.println("0. 처음으로");

                        System.out.print("메뉴를 입력하세요 >> ");
                        userSubChoice = sc.nextInt();

                        switch (userSubChoice) {
                            case 1 -> addCustomer();
                            case 2 -> removeCustomer();
                            case 3 -> showCustomer();
                            case 4 -> customerSaveFile();
                            case 5 -> customerLoadFile();
                        }
                    } while(userSubChoice != 0);

                }
                // 예약 관리
                case 2 -> {
                    do {
                        System.out.println("============= 예약관리 =============");
                        System.out.println("1. 객실 조회");
                        System.out.println("2. 객실 예약 조회");
                        System.out.println("3. 객실 예약");
                        System.out.println("4. 객실 예약 취소");
                        System.out.println("0. 처음으로");

                        System.out.print("메뉴를 입력하세요 >> ");
                        userSubChoice = sc.nextInt();

                        switch (userSubChoice) {
                            case 1 -> showRoom();
                            case 2 -> showReservation();
                            case 3 -> addReservation();
                            case 4 -> cancelReservation();
                        }
                    } while(userSubChoice != 0);
                }
                // 객실 관리
                case 3 -> {
                    do {
                        System.out.println("============= 객실 =============");
                        System.out.println("1. 객실 조회");
                        System.out.println("2. 객실 등록");
                        System.out.println("3. 객실 삭제");
                        System.out.println("4. 객실 초기화");
                        System.out.println("0. 처음으로");

                        System.out.print("메뉴를 입력하세요 >> ");
                        userSubChoice = sc.nextInt();

                        switch (userSubChoice) {
                            case 1 -> showRoom();
                            case 2 -> addRoom();
                            case 3 -> deleteRoom();
                            case 4 -> roomSetting();
                        }
                    } while(userSubChoice != 0);
                }
            }

        } while(userChoice != 0);

    }

    /**
     * 예약 조회
     */
    private static void showReservation() {
        System.out.print("이름을 입력하세요 >> ");
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int roomNumber = roomManage.findReservationRoom(name);
        System.out.println("예약한 객실은 " + roomNumber +"호 입니다.");
    }

    /**
     * 파일에서 고객 명단 불러오기
     */
    private static void customerLoadFile() {
        customerManage.loadFile();
        System.out.println("고객의 명단을 모두 성공적으로 불러왔습니다.");
    }

    /**
     * 고객 명단 파일에 저장
     */
    private static void customerSaveFile() {
        customerManage.saveFile();
        System.out.println("고객의 명단이 파일에 저장되었습니다.");
    }

    /**
     * 객실 삭제
     */
    private static void deleteRoom() {

        System.out.print("삭제할 객실 번호를 입력하세요 >> ");
        try {
            roomNumber = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        roomManage.deleteRoom(roomNumber);
    }

    /**
     * 객실 등록
     */
    private static void addRoom() {
        System.out.print("등록할 객실 번호를 입력하세요 >> ");
        try {
            roomNumber = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        roomManage.addRoom(roomNumber);
    }

    /**
     * 객실 예약 취소
     */
    private static void cancelReservation() {
        System.out.print("이름을 입력하세요 >> ");
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("전화번호를 입력하세요 >> ");
        try {
            tel = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 회원인지 아닌지 확인
        if(customerManage.findCustomer(name, tel)) {
            // 예약 내역 보여주기
            System.out.println("===예약된 객실===");
            int roomNumber = roomManage.findReservationRoom(name);
            if(roomNumber == -1) {
                System.out.println("예약된 객실이 없습니다.");
                return;
            }
            // 예약 취소
            roomManage.deleteReservation(roomNumber, name);
        }
        // 회원이 아니면
        else {
            System.out.println("등록된 고객이 아닙니다.");
        }


    }

    /**
     * 객실 예약
     */
    private static void addReservation() {
        System.out.println("===객실 예약===");
        System.out.print("원하는 객실을 입력하세요 >> ");
        try {
            roomNumber = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("이름을 입력하세요 >> ");
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("전화번호를 입력하세요 >> ");
        try {
            tel = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 등록된 고객이면 예약 가능
        if(customerManage.findCustomer(name, tel)) {
            customerManage.reservationCustomer(roomNumber, name);
            roomManage.addReservation(roomNumber, name);
        }
        // 등록되지 않은 고객이면 예약 불가능
        else {
            System.out.println("등록된 고객이 아닙니다.");
        }


    }

    /**
     * 객실 조회
     */
    private static void showRoom() {
        System.out.println("===객실 조회===");
        roomManage.showRoom();
    }

    /**
     * 객실 초기화
     */
    private static void roomSetting() {
        System.out.println("===객실 초기화===");
        roomManage.roomSetting();
    }

    /**
     * 회원 삭제
     */
    private static void removeCustomer() {
        System.out.print("삭제 할 이름을 입력하세요 >> ");
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerManage.removeCustomer(name);

    }

    /**
     * 회원 등록
     */
    public static void addCustomer() {
        System.out.print("이름을 입력하세요 >> ");
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("전화번호를 입력하세요 >> ");
        try {
            tel = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerManage.addCustomer(new Customer(name, tel));
    }

    /**
     * 회원 목록 조회
     */
    public static void showCustomer() {
        System.out.println("===회원 목록===");
        customerManage.showCustomer();
    }

}
