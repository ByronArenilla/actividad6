package Friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class CRUD {
    private File file;

    public CRUD() {
        file = new File("friendsContact.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addFriend(String name, long number) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        boolean found = false;
        String nameNumberString;
        String existingName;
        long existingNumber;

        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");
            existingName = lineSplit[0];
            existingNumber = Long.parseLong(lineSplit[1]);

            if (existingName.equals(name) || existingNumber == number) {
                found = true;
                break;
            }
        }

        if (!found) {
            raf.writeBytes(name + "!" + number);
            raf.writeBytes(System.lineSeparator());
            System.out.println("Friend added.");
        } else {
            System.out.println("Friend already exists.");
        }
        raf.close();
    }

    public void displayFriends() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String nameNumberString;

        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");
            String name = lineSplit[0];
            long number = Long.parseLong(lineSplit[1]);
            System.out.println("Friend Name: " + name + ", Contact Number: " + number);
        }

        raf.close();
    }

    public void updateFriend(String name, long newNumber) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
        boolean found = false;

        while (raf.getFilePointer() < raf.length()) {
            String nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");
            String existingName = lineSplit[0];
            long existingNumber = Long.parseLong(lineSplit[1]);

            if (existingName.equals(name)) {
                nameNumberString = name + "!" + newNumber;
                found = true;
            }

            tmpraf.writeBytes(nameNumberString);
            tmpraf.writeBytes(System.lineSeparator());
        }

        raf.seek(0);
        tmpraf.seek(0);

        while (tmpraf.getFilePointer() < tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine());
            raf.writeBytes(System.lineSeparator());
        }

        raf.setLength(tmpraf.length());
        tmpraf.close();
        raf.close();
        tmpFile.delete();

        if (found) {
            System.out.println("Friend updated.");
        } else {
            System.out.println("Friend not found.");
        }
    }

    public void deleteFriend(String name) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
        boolean found = false;

        while (raf.getFilePointer() < raf.length()) {
            String nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");
            String existingName = lineSplit[0];

            if (!existingName.equals(name)) {
                tmpraf.writeBytes(nameNumberString);
                tmpraf.writeBytes(System.lineSeparator());
            } else {
                found = true;
            }
        }

        raf.seek(0);
        tmpraf.seek(0);

        while (tmpraf.getFilePointer() < tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine());
            raf.writeBytes(System.lineSeparator());
        }

        raf.setLength(tmpraf.length());
        tmpraf.close();
        raf.close();
        tmpFile.delete();

        if (found) {
            System.out.println("Friend deleted.");
        } else {
            System.out.println("Friend not found.");
        }
    }
}
