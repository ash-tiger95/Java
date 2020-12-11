package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
    static int M, A;
    static int moveA[];
    static int moveB[];
    static ArrayList<BCTemp> BC[][];
    static int dy[] = { 0, -1, 0, 1, 0 };
    static int dx[] = { 0, 0, 1, 0, -1 };
 
    static class BCTemp implements Comparable<BCTemp> {
        int id;
        int p;
 
        public BCTemp(int id, int p) {
            this.id = id;
            this.p = p;
        }
 
        @Override
        public int compareTo(BCTemp o) {
            return o.p - this.p;
        }
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            BC = new ArrayList[11][11];
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    BC[i][j] = new ArrayList<BCTemp>();
                }
            }
            int ans = 0;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            moveA = new int[M];
            moveB = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                moveA[m] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                moveB[m] = Integer.parseInt(st.nextToken());
            }
            for (int a = 0; a < A; a++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                Proccess(a, x, y, c, p);
            }
          pirnt();
            Sorted();
 
            ans = Solve();
 
            sb.append('#').append(test).append(' ').append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }
 
    private static void pirnt() {
        for(int i=1;i<=10;i++) {
            for(int j=1;j<=10;j++) {
                System.out.print(BC[i][j].size());
            }
            System.out.println();
        }
         System.out.println("------");
    }
 
    private static void Sorted() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Collections.sort(BC[i][j]);
            }
        }
 
    }
 
    private static int Solve() {
        int sum = 0;
        int ay = 1;
        int ax = 1;
        int by = 10;
        int bx = 10;
        sum += calculate(ay, ax, by, bx);
        for (int m = 0; m < M; m++) {
            ay = ay + dy[moveA[m]];
            ax = ax + dx[moveA[m]];
            by = by + dy[moveB[m]];
            bx = bx + dx[moveB[m]];
            sum += calculate(ay, ax, by, bx);
        }
 
        return sum;
    }
 
    private static int calculate(int ay, int ax, int by, int bx) {
        if (BC[ay][ax].size() != 0 && BC[by][bx].size() == 0) {
            return BC[ay][ax].get(0).p;
        } else if (BC[ay][ax].size() == 0 && BC[by][bx].size() != 0) {
            return BC[by][bx].get(0).p;
        } else if (BC[ay][ax].size() != 0 && BC[by][bx].size() != 0) {
            if (BC[ay][ax].size() == 1 && BC[by][bx].size() == 1) {
                if (BC[ay][ax].get(0).id == BC[by][bx].get(0).id) {
                    return BC[ay][ax].get(0).p;
                } else {
                    return BC[ay][ax].get(0).p + BC[by][bx].get(0).p;
                }
            } else {
                if (BC[ay][ax].get(0).id == BC[by][bx].get(0).id) {
                    if (BC[ay][ax].size() == 1) {
                        return BC[ay][ax].get(0).p + BC[by][bx].get(1).p;
 
                    } else if (BC[by][bx].size() == 1) {
                        return BC[ay][ax].get(1).p + BC[by][bx].get(0).p;
                    } else {
                        int AA = BC[ay][ax].get(0).p + BC[by][bx].get(1).p;
                        int BB = BC[ay][ax].get(1).p + BC[by][bx].get(0).p;
                        if (AA > BB) {
                            return AA;
                        } else {
                            return BB;
                        }
 
                    }
                } else {
                    return BC[ay][ax].get(0).p + BC[by][bx].get(0).p;
                }
            }
        }
        return 0;
    }
 
    static class Pair {
        int y;
        int x;
 
        public Pair(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }
    }
 
    private static void Proccess(int id, int x, int y, int c, int p) {
        BC[y][x].add(new BCTemp(id, p));
        boolean check[][] = new boolean[11][11];
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(y, x));
        check[y][x] = true;
        int time = 0;
        while (time != c) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();
 
                for (int d = 1; d <= 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];
                    if (ny < 1 || nx < 1 || ny > 10 || nx > 10)
                        continue;
                    if (check[ny][nx])
                        continue;
                    check[ny][nx] = true;
                    BC[ny][nx].add(new BCTemp(id, p));
                    q.add(new Pair(ny, nx));
                }
            }
            time++;
        }
 
    }
}
