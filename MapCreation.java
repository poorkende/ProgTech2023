package hu.nye.progTech;

import java.util.Random;

class MapCreation {
    private char[][] map;
    private int N;
    private int numBonusWalls;
    private int numPiles;

    public MapCreation(int N, int numBonusWalls, int numPiles) {
        if (N < 6 || N > 20) {
            throw new IllegalArgumentException("Nak 6 és 20 közötti egész számnak kell lennie.");
        }
        this.N = N;
        this.numBonusWalls = numBonusWalls;
        this.numPiles = numPiles;
        this.map = new char[N][N];
        initializeMap();
        fillBordersWithWalls();
        placePilesRandomly(numPiles);
        placeBonusWallsRandomly(numBonusWalls);
        placeMonsters();
        placeGold();
    }

    private void initializeMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = ' ';
            }
        }
    }

    private void fillBordersWithWalls() {
        for (int i = 0; i < N; i++) {
            map[0][i] = 'W';
            map[N - 1][i] = 'W';
            map[i][0] = 'W';
            map[i][N - 1] = 'W';
        }
    }

    private void placePilesRandomly(int numPiles) {
        Random random = new Random();
        int placedPiles = 0;

        while (placedPiles < numPiles) {
            int x = random.nextInt(N);
            int y = random.nextInt(N);

            if (map[y][x] == ' ') {
                map[y][x] = 'P';
                placedPiles++;
            }
        }
    }

    private void placeBonusWallsRandomly(int numBonusWalls) {
        Random random = new Random();
        int placedBonusWalls = 0;

        while (placedBonusWalls < numBonusWalls) {
            int x = random.nextInt(N);
            int y = random.nextInt(N);

            if (map[y][x] == ' ') {
                map[y][x] = 'W';
                placedBonusWalls++;
            }
        }
    }

    private void placeMonsters() {
        map[N / 2][N / 2] = 'M';
    }

    private void placeGold() {
        map[N / 2 - 1][N / 2 - 1] = 'G';
    }

    public char[][] getMap() {
        return map;
    }

    public void placeHero(Hero hero) {
        map[hero.getX()][hero.getY()] = 'H';
    }

    public void clearHero(Hero hero) {
        map[hero.getX()][hero.getY()] = ' ';
    }
}
