import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class caesarcipher {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int option = getMenuOption(in);
		boolean exist1 = checkOption(option, in);
		
		
		
		while (exist1 == false) {
			System.out.println("Error: File does not exist");
			System.out.println("");
			option = getMenuOption(in);
			exist1 = checkOption(option, in);
		}

	}
	public static int getMenuOption(Scanner in) {
		int option;
		do {
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.println("3. Quit");
		System.out.print("What would you like to do? ");
		option = in.nextInt();
		if (option < 1 || option > 3) {
			System.out.println("Error: Invalid option chosen");
			System.out.println("");
		}
		} while ((option < 1 || option > 3));
		return option;
	}
	public static void encrypt(File inputFile, int key) throws Exception {
		Scanner in = new Scanner(new FileInputStream(inputFile));
		String ciphertext = "";
		while (in.hasNextLine()) {
			String line = in.nextLine();
			int length = line.length();
			for (int i = 0; i < length; i++) {
				char originalchar = line.charAt(i);
				if (Character.isWhitespace(originalchar)) {
					ciphertext = ciphertext + originalchar;
				}
				else {
					ciphertext = ciphertext + (char) ((char) originalchar + key);
				}
			}
			ciphertext = ciphertext + "\n";
	}
		String newfilename = inputFile.getName();
		newfilename = newfilename.substring(0, newfilename.lastIndexOf('.'));
		File newfile = new File(newfilename + ".enc");
		FileWriter FileWrite = new FileWriter(newfile);
		BufferedWriter BuffWriter = new BufferedWriter(FileWrite);
		BuffWriter.write(ciphertext);
		BuffWriter.close();
		FileWrite.close();
		in.close();
}
	public static void decrypt(File inputFile, int key) throws Exception {
		Scanner in = new Scanner(new FileInputStream(inputFile));
		String originaltext = "";
		while (in.hasNextLine()) {
			String line = in.nextLine();
			int length = line.length();
			for (int i = 0; i < length; i++) {
				char cipherchar = line.charAt(i);
				if (Character.isWhitespace(cipherchar)) {
					originaltext = originaltext + cipherchar;
				}
				else {
					originaltext = originaltext + (char) ((char) cipherchar - key);
				}
			}
			originaltext = originaltext + "\n";
	}
		String newfilename = inputFile.getName();
		newfilename = newfilename.substring(0, newfilename.lastIndexOf('.'));
		File newfile = new File(newfilename + ".txt");
		FileWriter FileWrite = new FileWriter(newfile);
		BufferedWriter BuffWriter = new BufferedWriter(FileWrite);
		BuffWriter.write(originaltext);
		BuffWriter.close();
		FileWrite.close();
		in.close();
}
	public static boolean checkOption(int option, Scanner in) {
		boolean exist = false;
		if (option == 1) {
			System.out.print("Input file name: ");
			String filename = in.next();
			
			File newfile = new File(filename + ".txt");
			
			if (newfile.exists()) {
				exist = true;
				System.out.print("Input key: ");
				int key = in.nextInt();
				try {
					encrypt(newfile, key);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		else if (option == 2) {
			System.out.print("Input file name: ");
			String filename = in.next();
			
			
			File newfile = new File(filename + ".enc");
			
			if (newfile.exists()) {
				exist = true;
				System.out.print("Input key: ");
				int key = in.nextInt();
				try {
					decrypt(newfile, key);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		else if (option == 3) {
			exist = true;
			System.exit(0);
		}
		return exist;
	}

}
