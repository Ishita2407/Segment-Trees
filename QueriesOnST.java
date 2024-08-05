public class QueriesOnST{
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
    public static int getSumUtil(int i,int si, int sj, int qi, int qj){
        // i is segment tree index
        // case 1
        if(qj <= si || qi >= sj){
            // non - overlapping case
            return 0;
        } else if(si >= qi && sj <= qj){
            // complete overlap
            return tree[i];
        } else{
            // Partial overlap
            int mid = (si + sj)/2;
            int left = getSumUtil(2*i +1, si, mid, qi, qj);
            int right = getSumUtil(2*i+2, mid+1, sj, qi, qj);
            return left + right;
        }
    }
    public static int getSum(int arr[], int qi, int qj){
        int n = arr.length;
        return getSumUtil(0,0,n-1,qi,qj);
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
        System.out.println(getSum(arr, 2, 5));

    }
}