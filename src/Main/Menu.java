package Main;

import Exercise_1_Village_Streets.VillageGraphCreator;
import Exercise_2_Water.WaterSupplyGraphCreator;
import Exercise_3_Firework.FireworkGraphCreator;
import Exercise_4_Wedding.WeddingGraphCreator;
import Exercise_5_Postman.PostmanGraphCreator;
import Exercise_6_Traffic.TrafficGraphCreator;
import Exercise_7_Work.WorkGraphCreator;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        System.out.println("""
                Willkommen beim Graph-Berechnungstool!

                Es wurde für die "Graph"schaft Schilda entwickelt, da die Menschen dort vor kurzem viele komplizierte
                Probleme lösen mussten. Mit diesem Tool ist all das nun ohne Stift, Papier und Kopfschmerzen möglich!
                                
                Was möchtest du gerne berechnen?
                """);
        System.out.println();


        try {
            Menu.chooseProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void chooseProgram() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1:  Beispiel: Straßenbau für Schilda berechnen");
        System.out.println("2:  eigener Graph");
        System.out.println();
        System.out.println("3:  Beispiel: Wasserversorgung des Supermarktes in Schilda überprüfen");
        System.out.println("4:  eigener Graph");
        System.out.println();
        System.out.println("5:  Beispiel: Feuerwerks-Choreographie des Fundes in Schilda berechnen");
        System.out.println("6:  eigener Graph");
        System.out.println();
        System.out.println("7:  Beispiel: Hochzeitspaare der Singles in Schilda berechnen");
        System.out.println("8:  eigener Graph");
        System.out.println();
        System.out.println("9:  Beispiel: Postbotenroute für die Einladungsverteilung in Schilda berechnen");
        System.out.println("10: eigener Graph");
        System.out.println();
        System.out.println("11: Beispiel: Verkehrsplanung von der Autobahn zum Parkplatz in Schilda berechnen");
        System.out.println("12: eigener Graph");
        System.out.println();
        System.out.println("13: Beispiel: Arbeitsverteilung der anfallenden Aufgaben in Schilda berechnen");
        System.out.println("14: eigener Graph");


        int choice = scanner.nextInt();

        switch (choice) {
            case 2 -> {
                VillageGraphCreator villageGraphCreator = new VillageGraphCreator();
                villageGraphCreator.create();
            }
            case 1 -> {
                VillageGraphCreator villageGraphCreator1 = new VillageGraphCreator();
                villageGraphCreator1.runExample();
            }
            case 4 -> {
                WaterSupplyGraphCreator waterSupplyGraphCreator = new WaterSupplyGraphCreator();
                waterSupplyGraphCreator.create();
            }
            case 3 -> {
                WaterSupplyGraphCreator waterSupplyGraphCreator1 = new WaterSupplyGraphCreator();
                waterSupplyGraphCreator1.runExample();
            }
            case 6 -> {
                FireworkGraphCreator fireworkGraphCreator = new FireworkGraphCreator();
                fireworkGraphCreator.create();
            }
            case 5 -> {
                FireworkGraphCreator fireworkGraphCreator1 = new FireworkGraphCreator();
                fireworkGraphCreator1.runExample();
            }
            case 8 -> {
                WeddingGraphCreator weddingGraphCreator = new WeddingGraphCreator();
                weddingGraphCreator.create();
            }
            case 7 -> {
                WeddingGraphCreator weddingGraphCreator1 = new WeddingGraphCreator();
                weddingGraphCreator1.runExample();
            }
            case 10 -> {
                PostmanGraphCreator postmanGraphCreator = new PostmanGraphCreator();
                postmanGraphCreator.create();
            }
            case 9 -> {
                PostmanGraphCreator postmanGraphCreator1 = new PostmanGraphCreator();
                postmanGraphCreator1.runExample();
            }
            case 12 -> {
                TrafficGraphCreator trafficGraphCreator = new TrafficGraphCreator();
                trafficGraphCreator.create();
            }
            case 11 -> {
                TrafficGraphCreator trafficGraphCreator1 = new TrafficGraphCreator();
                trafficGraphCreator1.runExample();
            }
            case 14 -> {
                WorkGraphCreator workGraphCreator = new WorkGraphCreator();
                workGraphCreator.create();
            }
            case 13 -> {
                WorkGraphCreator workGraphCreator1 = new WorkGraphCreator();
                workGraphCreator1.runExample();
            }
            default -> {
                System.out.println("Ungültige Eingabe. Versuche es erneut.");
                chooseProgram();
            }
        }
    }
}
