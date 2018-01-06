package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum_Force(int[] nums, int target) {//Force Search  60ms
        int[] ans = new int[2];
        for(int i = 0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target == nums[i]+nums[j]){
                    ans[0]=i;
                    ans[1]=j;
                    return ans;
                }
            }
        }
        return null;
    }
    public int[] twoSum_Hash_TwoPass(int[] nums, int target) {//Using hashmap to reduce the time in searching form O(n2) to O(n)   12ms
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return null;
    }

    public int[] twoSum_Hash_OnePass(int[] nums, int target) {   //9ms
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i){
                return new int[]{i,map.get(complement)};
            }
            map.put(nums[i],i);
        }
        return null;
    }


}

public class Twosum {


        public static int[] stringToIntegerArray(String input) {
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            if (input.length() == 0) {
                return new int[0];
            }

            String[] parts = input.split(",");
            int[] output = new int[parts.length];
            for(int index = 0; index < parts.length; index++) {
                String part = parts[index].trim();
                output[index] = Integer.parseInt(part);
            }
            return output;
        }

        public static String integerArrayToString(int[] nums, int length) {
            if (length == 0) {
                return "[]";
            }

            String result = "";
            for(int index = 0; index < length; index++) {
                int number = nums[index];
                result += Integer.toString(number) + ", ";
            }
            return "[" + result.substring(0, result.length() - 2) + "]";
        }

        public static String integerArrayToString(int[] nums) {
            return integerArrayToString(nums, nums.length);
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                int[] nums = stringToIntegerArray(line);
                line = in.readLine();
                int target = Integer.parseInt(line);

                int[] ret = new Solution().twoSum_Hash_TwoPass(nums,target);
                String out = integerArrayToString(ret);

                System.out.print(out);
            }
        }

}
