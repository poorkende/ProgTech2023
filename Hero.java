package hu.nye.progTech;

import java.util.Random;

public class Hero {
    private int x;
    private int y;
    private int arrows;
    private String direction;
    private int mapSize;
    private int goldCount;

    public Hero(int x, int y, int arrows, int mapSize) {
        this.x = x;
        this.y = y;
        this.arrows = arrows;
        this.direction = "észak";
        this.mapSize = mapSize;
        this.goldCount = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getArrows() {
        return arrows;
    }

    public void move(char[][] map) {
        int newX = x;
        int newY = y;

        if (direction.equals("észak")) {
            newX = x - 1;
        } else if (direction.equals("dél")) {
            newX = x + 1;
        } else if (direction.equals("nyugat")) {
            newY = y - 1;
        } else if (direction.equals("kelet")) {
            newY = y + 1;
        }

        if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length && map[newX][newY] != 'W') {
            x = newX;
            y = newY;

            // Ellenőrzés, hogy megszerezte-e az aranyat
            if (map[x][y] == 'G') {
                goldCount++;
                System.out.println("Gratulálok, megszerezted az aranyat!");
                map[x][y] = ' '; // Az arany eltávolítása a térképről
            }
        }

        checkForGold(map);
    }

    public void shootMonster(char[][] map) {
        if (arrows > 0) {
            int shotX = x;
            int shotY = y;

            if (direction.equals("észak")) {
                for (int i = shotX - 1; i >= 0; i--) {
                    if (map[i][shotY] == 'M') {
                        map[i][shotY] = ' ';
                        arrows--;
                        break;
                    }
                }
            } else if (direction.equals("dél")) {
                for (int i = shotX + 1; i < mapSize; i++) {
                    if (map[i][shotY] == 'M') {
                        map[i][shotY] = ' ';
                        arrows--;
                        break;
                    }
                }
            } else if (direction.equals("nyugat")) {
                for (int i = shotY - 1; i >= 0; i--) {
                    if (map[shotX][i] == 'M') {
                        map[shotX][i] = ' ';
                        arrows--;
                        break;
                    }
                }
            } else if (direction.equals("kelet")) {
                for (int i = shotY + 1; i < mapSize; i++) {
                    if (map[shotX][i] == 'M') {
                        map[shotX][i] = ' ';
                        arrows--;
                        break;
                    }
                }
            }
        }
    }

    public void rotate(String newDirection) {
        this.direction = newDirection;
    }

    private void checkForGold(char[][] map) {
        int northX = x - 1;
        int southX = x + 1;
        int westY = y - 1;
        int eastY = y + 1;

        if (northX >= 0 && map[northX][y] == 'G') {
            goldCount++;
            System.out.println("Gratulálok, megszerezted az aranyat!");
            map[northX][y] = ' ';
        }
        if (southX < mapSize && map[southX][y] == 'G') {
            goldCount++;
            System.out.println("Gratulálok, megszerezted az aranyat!");
            map[southX][y] = ' ';
        }
        if (westY >= 0 && map[x][westY] == 'G') {
            goldCount++;
            System.out.println("Gratulálok, megszerezted az aranyat!");
            map[x][westY] = ' ';
        }

        if (eastY < mapSize && map[x][eastY] == 'G') {
            goldCount++;
            System.out.println("Gratulálok, megszerezted az aranyat!");
            map[x][eastY] = ' ';
        }
    }

    public boolean isAtStartingPoint() {
        return (x == mapSize - 2) && (y == 1);
    }
}
