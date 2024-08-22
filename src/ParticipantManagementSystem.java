import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Participant {
    private static int counter = 1;
    private int code;
    private int role; // 1: Consultant, 2: Outsource, 3: Guest, 4: Staff
    private String name;
    private String age;
    private int gender; // 1: Male, 2: Female, 3: Other
    private String address;
    private float money;

    public Participant(int role, String name, String age, int gender, String address, float money) {
        this.code = counter++;
        this.role = role;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.money = money;
    }

    public int getCode() {
        return code;
    }

    public int getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public float getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "code=" + code +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", money=" + money +
                '}';
    }
}

class ParticipantManager {
    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void deleteParticipant(int code) {
        participants.removeIf(participant -> participant.getCode() == code);
    }

    public List<Participant> findParticipantsByNameAndRole(String name, int role) {
        return participants.stream()
                .filter(participant -> participant.getName().equalsIgnoreCase(name) && participant.getRole() == role)
                .collect(Collectors.toList());
    }

    public List<Participant> findParticipantsByCost(float minCost, float maxCost) {
        return participants.stream()
                .filter(participant -> participant.getMoney() >= minCost && participant.getMoney() <= maxCost)
                .collect(Collectors.toList());
    }

    public List<Participant> sortParticipantsByCost() {
        return participants.stream()
                .sorted(Comparator.comparing(Participant::getMoney))
                .collect(Collectors.toList());
    }

    public void displayAllParticipants() {
        participants.forEach(System.out::println);
    }
}

public class ParticipantManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParticipantManager manager = new ParticipantManager();

        while (true) {
            System.out.println("\nParticipant Management System");
            System.out.println("1. Add Participant");
            System.out.println("2. Delete Participant by Code");
            System.out.println("3. Find Participants by Name and Role");
            System.out.println("4. Sort Participants by Cost");
            System.out.println("5. Find Participants by Cost Range");
            System.out.println("6. Display All Participants");
            System.out.println("7. Exit");
            System.out.println("8. Insert example data");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Role (1: Consultant, 2: Outsource, 3: Guest, 4: Staff): ");
                    int role = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    String age = scanner.nextLine();
                    System.out.print("Enter Gender (1: Male, 2: Female, 3: Other): ");
                    int gender = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Money (1000 = 1000000 dong: ");
                    float money = scanner.nextFloat();
                    scanner.nextLine();

                    Participant participant = new Participant(role, name, age, gender, address, money);
                    manager.addParticipant(participant);
                    System.out.println("Participant added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Participant Code to Delete: ");
                    int code = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteParticipant(code);
                    System.out.println("Participant deleted successfully!");
                    break;

                case 3:
                    System.out.print("Enter Name: ");
                    String searchName = scanner.nextLine();
                    System.out.print("Enter Role (1: Consultant, 2: Outsource, 3: Guest, 4: Staff): ");
                    int searchRole = scanner.nextInt();
                    scanner.nextLine();
                    List<Participant> foundParticipants = manager.findParticipantsByNameAndRole(searchName, searchRole);
                    if (foundParticipants.isEmpty()) {
                        System.out.println("No participants found with the given name and role.");
                    } else {
                        foundParticipants.forEach(System.out::println);
                    }
                    break;

                case 4:
                    List<Participant> sortedParticipants = manager.sortParticipantsByCost();
                    sortedParticipants.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter Minimum Cost: ");
                    float minCost = scanner.nextFloat();
                    System.out.print("Enter Maximum Cost: ");
                    float maxCost = scanner.nextFloat();
                    scanner.nextLine();
                    List<Participant> participantsByCost = manager.findParticipantsByCost(minCost, maxCost);
                    if (participantsByCost.isEmpty()) {
                        System.out.println("No participants found within the given cost range.");
                    } else {
                        participantsByCost.forEach(System.out::println);
                    }
                    break;

                case 6:
                    manager.displayAllParticipants();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                case 8:
                    // Adding participants
                    manager.addParticipant(new Participant(4, "Nguyễn Hoàng Hiếu", "19", 1, "Ha Noi", 0.0f));
                    manager.addParticipant(new Participant(4, "Võ Minh Quang", "19", 1, "Ha Noi", 0.0f));
                    manager.addParticipant(new Participant(4, "Chu Hùng Công", "19", 1, "Ha Noi", 0.0f));
                    manager.addParticipant(new Participant(4, "Đỗ Hoàng Thái Duy", "19", 1, "Ha Noi", 0.0f));
                    manager.addParticipant(new Participant(3, "Nguyễn Văn Bùi", "19", 1, "Nam Dinh", 0.0f));
                    manager.addParticipant(new Participant(3, "Phạm Thị Hương", "45", 2, "Ha Noi", 0.0f));
                    manager.addParticipant(new Participant(2, "Nguyễn Thanh Long", "30", 1, "Hai Phong", 10000.0f));
                    manager.addParticipant(new Participant(2, "Hoàng Minh Cường", "40", 1, "Ha Noi", 12000.0f));
                    manager.addParticipant(new Participant(2, "Đặng Ngọc Anh", "30", 2, "Ha Noi", 15000.0f));
                    manager.addParticipant(new Participant(2, "Lê Minh Châu", "35", 1, "456 Avenue", 15000.0f));
                    manager.addParticipant(new Participant(1, "Đinh Thị Hồng", "37", 1, "Ha Noi", 15000.0f));
                    manager.addParticipant(new Participant(1, "Hoàng Thị Mai", "40", 1, "Thanh Hoa", 12000.0f));
                    manager.addParticipant(new Participant(1, "Bùi Minh Đức", "30", 1, "Nghe An", 12000.0f));
                    manager.addParticipant(new Participant(1, "Nguyễn Thanh Phong", "35", 1, "Ha Noi", 12000.0f));
                    manager.addParticipant(new Participant(1, "Trần Thu Hương", "45", 2, "Nam Dinh", 12000.0f));
                    manager.addParticipant(new Participant(1, "Lê Minh Tuấn", "32", 1, "Ha Noi", 12000.0f));
            }
        }
    }
}
