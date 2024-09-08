package swea_1953;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static int N, M, R, C, L;
    static int[][] map;
    static boolean[][] visited;
    static int count;

    // up, down, left, right
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] directionType = {
            {false, false, false, false},
            {true, true, true, true}, // up down left right
            {true, true, false, false}, // up down
            {false, false, true, true}, // left right
            {true, false, false, true}, // up right
            {false, true, false, true}, // down right
            {false, true, true, false}, // down left
            {true, false, true, false} // up left
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();

            map = new int[N][M];
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    map[n][m] = sc.nextInt();
                }
            }

            visited = new boolean[N][M];
            count = 0;
            dfs(0, R, C);

            for (boolean[] row : visited) {
                System.out.println(Arrays.toString(row));
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int depth, int r, int c) {
        if (depth == L) {
            return;
        }

        visited[r][c] = true;
        count++;

        int type = map[r][c];
        for (int d = 0; d < 4; d++) {
            if (!directionType[type][d]) {
                continue;
            }

            int nr = r + dr[d];
            int nc = c + dc[d];
            if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                System.out.print(printDashes(depth) + " depth  " + depth + " try [" + r + "," + c + "] type " + Arrays.toString(directionType[map[r][c]]) + " => [" + nr + "," + nc + "] type " + Arrays.toString(directionType[map[nr][nc]]));
                if (visited[nr][nc]) {
                    System.out.println(" but no, visited");
                } else if (!checkEntry(d, nr, nc)) {
                    System.out.println(" but no, entry not match");
                } else {
                    System.out.println(" yes!");
                    dfs(depth + 1, nr, nc);
                }
            }
        }
    }

    public static String printDashes(int n) {
        StringBuilder dashes = new StringBuilder();

        for (int i = 0; i < n; i++) {
            dashes.append("-");
        }

        return dashes.toString();
    }

    public static boolean checkEntry(int directionFrom, int arrivalR, int arrivalC) {
        int entrancePoint = directionFrom < 2 ? Math.abs(directionFrom - 1) : Math.abs(directionFrom - 5);
        return directionType[map[arrivalR][arrivalC]][entrancePoint];
    }
}
