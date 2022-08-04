package leetcode.array.seacrh_in_rotatted_sorted_array;
/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
 */

class Solution {
    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1, start;

        //가장 작은 수 이분탐색으로 찾기
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                //만약 중간이 오른쪽 작은 수들 보다 크다면 left 변경
                left = mid + 1;
            } else {
                //아니라면 right 변경
                right = mid;
            }
        }

        //start 지정 및 초기화
        start = left;
        left = 0;
        right = nums.length - 1;

        //타켓 범위 지정
        if (target >= nums[start] && target <= nums[right]) {
            //target 이 오른쪽 array(작은수들) 범위 안에 있는 경우
            left = start;
        } else { 
            //왼쪽 array(큰수들) 범위 안에 있는 경우
            right = start;
        }

        //숫자 탐색
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}

public class Main {


    public static void main(String[] args) {

        Solution s = new Solution();
        int search = s.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        System.out.println("search = " + search);


    }
}
