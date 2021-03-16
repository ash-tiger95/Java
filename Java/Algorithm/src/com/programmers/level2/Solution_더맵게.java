package com.programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_더맵게 {
	// 내 풀이: Collection.sort 때문에 효율성을 통과하지 못한다.
	public int solution1(int[] scoville, int K) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<scoville.length;i++ ){
            list.add(scoville[i]);
        }
        
        while(true){
            Collections.sort(list);
            
            if(list.get(0) < K && list.size() == 1){
                answer = -1;
                break;
            }
            
            if(list.get(0) < K){
                    list.add(list.get(0) + list.get(1)*2);
                    list.remove(0);
                    list.remove(0);
                    answer++;
                
            }else{
                break;
            }
        }
        
        return answer;
    }
	
	// 정답: PQ를 써서 효율성을 높인다. (PQ는 heap)
	public int solution2(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue();

        for (int aScoville : scoville) {
            heap.offer(aScoville);
        }

        while (heap.peek() <= K) {
            if (heap.size() == 1) {
                return -1;
            }
            int a = heap.poll();
            int b = heap.poll();


            int result = a + (b * 2);

            heap.offer(result);
            answer ++;
        }
        return answer;
    }
}
