package com.programmers.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_Level3_가장먼노드 {
static ArrayList<Node>[] list;
    
    static class Node{
        int end;
        
        public Node(int end){
            super();
            this.end = end;
        }
        
        @Override
        public String toString(){
        return end+" ";
        }
        
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        list = new ArrayList[n];
        for(int i=0;i<n;i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0;i<edge.length;i++){
            list[edge[i][0]-1].add(new Node(edge[i][1]-1));
            list[edge[i][1]-1].add(new Node(edge[i][0]-1));
        }
        
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        
        Queue<Node> q = new LinkedList<>();
        q.addAll(list[0]);
        visited[0] = true;
        distance[0] = 0;
        int dist = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            dist++;
            
            for(int s=0;s<size;s++){
                
                
                Node cn = q.poll();

                if(visited[cn.end]){
                    continue;
                }

                visited[cn.end] = true;
                distance[cn.end] = dist;
                q.addAll(list[cn.end]);
            }
            
            
        }
        
        for(int i=0;i<n;i++){
            if(dist-1 == distance[i]){
                answer++;
            }
        }
        
        return answer;
    }
}	
