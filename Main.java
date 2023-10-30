package hu.nye.progTech;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Adja meg a nevét: ");
            String playerName = scanner.nextLine();

            int N, numBonusWalls, numPiles;
            do {
                System.out.print("Adja meg a térkép méretét (6-20 közötti egész szám): ");
                N = scanner.nextInt();
            } while (N < 6 || N > 20);

            System.out.print("Adja meg a bónusz falak számát: ");
            numBonusWalls = scanner.nextInt();

            System.out.print("Adja meg a vermek számát: ");
            numPiles = scanner.nextInt();

            while (true) {
                MapCreation gameMap = new MapCreation(N, numBonusWalls, numPiles);
                char[][] map = gameMap.getMap();

                Hero hero = new Hero(N - 2, 1, numPiles, N);

                gameMap.placeHero(hero);

                System.out.println(playerName + ", a hősnek van egy nyila, amit a szörnyek kilövésére használhat.");
                System.out.println("Nyilak száma: " + hero.getArrows());

                printMap(map);

                while (true) {
                    System.out.print("Adja meg a mozgás irányát (fordulás/kilépés/lövés): ");
                    String input = scanner.next();

                    if (input.equals("kilépés")) {
                        break;
                    } else if (input.equals("fordulás")) {
                        System.out.print("Adja meg az új irányt (észak/kelet/dél/nyugat): ");
                        String newDirection = scanner.next();
                        hero.rotate(newDirection);
                    } else if (input.equals("lövés")) {
                        hero.shootMonster(map);
                        printMap(map);
                    } else {
                        gameMap.clearHero(hero);
                        hero.move(map);
                        gameMap.placeHero(hero);
                        printMap(map);
                    }

                    // Kezdo pont ellenorzes
                    if (hero.isAtStartingPoint()) {
                        System.out.println("Gratulálok, megnyerted a játékot!");
                        break;
                    }
                }

                // Ellenőrzés, hogy a játékos újra szeretné-e indítani a játékot
                System.out.print("Szeretnéd újra játszani? (igen/nem): ");
                String playAgain = scanner.next().toLowerCase();
                if (!playAgain.equals("igen")) {
                    break; // Ha a valasz nem akkor kilep
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Hibás bemenet. Az N értékének 6 és 20 közötti egész számnak kell lennie.");
        }
    }

    public static void printMap(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }
}
