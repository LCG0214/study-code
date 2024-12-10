package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author : LCG
 * @description : 力扣算法题练习
 * @since : 2024-12-10  10:27
 */
public class PracticeTest {

    /** 2024-12-10
     * 删除有序数组中的重复项
     * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
     * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
     * 返回 k 。
     *
     * 思路：创建一个新数组，不相等的直接放入新数组，最后把新数组赋值给nums：执行效率不如官方提供的快慢指针解法
     */
    @Test
    public void practice3() {
        // 传入值：nums = [0,0,1,1,1,2,2,3,3,4]
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
//        int[] nums = {1,1,2};
//        int[] nums = {1,1,1};
        // 具体逻辑
        int index = 0;
        Integer value = null;
        int[] array = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (value == null || nums[i] != value){
                array[index++] = nums[i];
                value = nums[i];
            }
        }
//        nums = array; // 直接用 = 赋值力扣编辑器不能赋值成功，改用循环赋值
        for (int i = 0; i < array.length; i++) {
            nums[i] = array[i];
        }
        System.out.println(index+ ", nums = " + Arrays.toString(nums));
        // ============================官方提供的快慢指针解法====================================
        // 快慢指针都从1开始，快指针每次向前一步，当快慢指针的值不相等就把快指针的值赋值给慢指针的值，快慢各进一步
        // 使用前先把上方逻辑注释掉，直接用官方提供的解法
        int n = nums.length;
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        System.out.println(slow+ ", nums = " + Arrays.toString(nums));
    }

    /** 2024-12-10
     * 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
     * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
     * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
     * 返回 k。
     *
     *思路：遍历数组，找到目标值则把这个位置后的所有数据往前移动一个，覆盖，然后i--再次检验本位置
     */
    @Test
    public void practice2() {
        // 输入数据 nums = [3,2,2,3], val = 3
        int[] nums = {2,2,2,0,0}; int val = 0;
        // 具体逻辑
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[nums.length - 1] == val){
                count++;
                nums[nums.length - 1] = -1;
            }
            if (nums[i] == val){
                count++;
                for (int j = i; j < nums.length-1; j++) {
                    nums[j] = nums[j+1];
                }
                i--;
            }
        }
        System.out.println(nums.length-count + ", " + Arrays.toString(nums));
    }

    /** 2024-12-10
     * 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1
     * 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * 思路：创建一个新数组，并且创建两个角标(指针)依次递进两个数组角标，比较两个数组的元素大小，
     * 小一点的加入新数组 最后转到数组nums1
     */
    @Test
    public void practice1() {
        // 初始数据 nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        // 具体逻辑
        int[] array = new int[m + n];
        int n1=0,n2 = 0;
        for(int i = 0; i< (m+n); i++){
            if(n1 == m){
                array[i] = nums2[n2++];
                continue;
            }
            if(n2 == n){
                array[i] = nums1[n1++];
                continue;
            }
            if(nums1[n1] <= nums2[n2]){
                array[i] = nums1[n1++];
            }else {
                array[i] = nums2[n2++];
            }
        }
        for(int i = 0; i<(m+n);i++){
            nums1[i] = array[i];
        }
    }

}
