public class updateOnST{
    static int tree[];

    public static void initialize(int n){
        tree = new int[4 * n];
    }

    // building segment tree
    public static int buildST(int arr[], int i, int start, int end){
        if(start == end){
            tree[i] = arr[start]; // single element 
            return arr[start];
        }
        int mid = (start + end)/2;
        // left subtree
        buildST(arr, 2*i+1, start, mid);  // 2*i + 1
        // right subtree 
        buildST(arr, 2*i+2, mid+1, end);  // 2*i + 2
        // parent node - left child + right child
        tree[i]  = tree[2*i+1] + tree[2*i+2];
        return tree[i];
    }
    //  Time complexity for single update operation is O(logn)
    public static void updateUtil(int i, int si, int sj, int idx, int diff){
        // here i is segment tree index
        // check for non- overlapping condn
        if(idx > sj || idx < si){
            return;
        } 
        tree[i] += diff;
        // non-leaf condition
        if(si != sj){
            int mid = (si + sj)/2;
            updateUtil(2*i + 1, si, mid, idx, diff);  // left
            updateUtil(2*i+2, mid+1, sj, idx, diff); // right
        }
        
    }
    
    public static void update(int arr[],int idx, int newVal){
        // Changes in array
        // Time complexity of this block is O(1)
        int n = arr.length;
        int diff = newVal - arr[idx];
        arr[idx] = newVal;

        updateUtil(0, 0, n-1, idx, diff);  // segment tree updation
    }
    public static void main(String args[]){
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        initialize(n);
        buildST(arr,0, 0,n-1);

        for(int i=0; i<tree.length; i++){
            System.out.print(tree[i] + " ");
        }
        System.out.println();
        update(arr, 2, 2);
       // call jaeygi getsum();
    }
}