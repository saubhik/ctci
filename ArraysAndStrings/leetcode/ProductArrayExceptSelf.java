public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        /* ans contains the right products.
         * ans[i+1] is the right product for i.
         */
        int[] ans = new int[nums.length];
        ans[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; --i) {
            ans[i] = nums[i] * ans[i + 1];
        }
        
        int leftProduct = 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            ans[i] = ans[i + 1] * leftProduct;
            leftProduct *= nums[i];
        }
        ans[nums.length - 1] = leftProduct;
        
        return ans;
    }
}