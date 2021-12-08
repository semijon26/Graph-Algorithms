package Main;

import Exercise_1_Village_Streets.VillageCreator;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws IOException {

        Menu.chooseProgram();

        //Exercise1Creator.create();

    }


    public static void chooseProgram() throws IOException {
        System.out.println("Willkommen beim Graph Tool. Was möchten Sie machen?");
        System.out.println("1: Aus einem ungerichteten gewichteten Graphen den minimalen Spannbaum aufstellen");
        System.out.println("2: Aus bla bla bla .............");

        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        switch (number) {
            case 1:
                VillageCreator.create();
                break;
            case 2:
                System.out.println("nix");
                break;
            default:
                System.out.println("Ungültige Eingabe. Versuchen Sie es erneut.");
                chooseProgram();
                break;

        }

    }

}
