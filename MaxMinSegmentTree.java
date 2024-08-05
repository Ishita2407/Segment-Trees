import javax.sound.midi.Synthesizer;

public class MaxMinSegmentTree{
    static int tree[];

    public static void init(int n){
        tree = new int[4*n];
    }
    // Time complexity - O(n)
    public static void buildTree(int i, int si, int sj, int arr[]){
        // Base case
        // Single element
        if(si == sj){
            tree[i] = arr[si];
            return;
        }
        int  mid = (si+sj)/2; // mid = si+(sj - si)/2;
        buildTree(2*i + 1, si, mid, arr);
        buildTree(2*i + 2, mid+1, sj, arr);
        tree[i] = Math.max(tree[2*i+1], tree[2*i+2]);
        // tree[i] = Math.min(tree[2*i+1], tree[2*i+2]);

    }
    // Query for finding maximum 
    
    public static int getMax(int arr[], int qi, int qj){
        int n = arr.length;
        return getMaxUtil(0, 0, n-1, qi, qj);
    }
    // Time complexity - O(log n)
    public static int getMaxUtil(int i, int si, int sj, int qi, int qj){
        // no overlap
        if(si > qj || sj < qi){
            return Integer.MIN_VALUE;
        } else if(si >= qi && sj <= qj){
            // complete overlap
            return tree[i];
        } else{
            // Partial overlap
            int mid = si +(sj-si)/2;
            int leftAns = getMaxUtil(2*i+1, si,mid,qi, qj);
            int rightAns = getMaxUtil(2*i+2,mid+1 ,sj,qi, qj);
            return Math.max(leftAns, rightAns);

        }
    }

    // update code - updation in array
    public static void update(int arr[], int idx, int newVal){
        arr[idx] = newVal;
        int n = arr.length;
        updateUtil(0, 0, n-1, idx,newVal);
    }
    // updation in segment tree
    // Time complexity - O(logn)
    public static void updateUtil(int i, int si, int sj, int idx,int newVal){
        if(idx < si || idx > sj){
            return;
        }
        tree[i] = Math.max(tree[i], newVal);
        if(si != sj){
            // non-leaf
            int mid = (si + sj)/2;
            updateUtil(2*i+1, si, mid, idx, newVal); // left
            updateUtil(2*i+2, mid+1, sj, idx, newVal); // right
        }
    }

    // CODE FOR MIN
    public static void buildTreeMin(int i, int si, int sj, int arr[]){
        // Base case
        // Single element
        if(si == sj){
            tree[i] = arr[si];
            return;
        }
        int  mid = (si+sj)/2; // mid = si+(sj - si)/2;
        buildTree(2*i + 1, si, mid, arr);
        buildTree(2*i + 2, mid+1, sj, arr);
        tree[i] = Math.max(tree[2*i+1], tree[2*i+2]);
        tree[i] = Math.min(tree[2*i+1], tree[2*i+2]);
    }
        
    public static int getMin(int arr[], int qi, int qj){
        int n = arr.length;
        return getMinUtil(0, 0, n-1, qi, qj);
    }
    // Time complexity - O(log n)
    public static int getMinUtil(int i, int si, int sj, int qi, int qj){
        // no overlap
        if(si > qj || sj < qi){
            return Integer.MAX_VALUE;
        } else if(si >= qi && sj <= qj){
            // complete overlap
            return tree[i];
        } else{
            // Partial overlap
            int mid = si +(sj-si)/2;
            int leftAns = getMinUtil(2*i+1, si,mid,qi, qj);
            int rightAns = getMinUtil(2*i+2,mid+1 ,sj,qi, qj);
            return Math.min(leftAns, rightAns);

        }
    }
    public static void updateMin(int arr[], int idx, int newVal){
        arr[idx] = newVal;
        int n = arr.length;
        updateUtil(0, 0, n-1, idx,newVal);
    }
    // updation in segment tree
    // Time complexity - O(logn)
    public static void updateUtilMin(int i, int si, int sj, int idx,int newVal){
        if(idx < si || idx > sj){
            return;
        }
        if(si == sj){
            // leaf node
            tree[i] = newVal;
        }
        if(si != sj){
            tree[i] = Math.min(tree[i], newVal);
            // non-leaf
            int mid = (si + sj)/2;
            updateUtilMin(2*i+1, si, mid, idx, newVal); // left
            updateUtilMin(2*i+2, mid+1, sj, idx, newVal); // right
        }
    }
    public static void main(String args[]){
        int arr[] = {6, 8, -1, 2, 17, 1, 3, 2, 4};
        int n = arr.length;
        init(n);
        buildTree(0, 0, n-1, arr);
        for(int i=0; i<tree.length; i++){
            System.out.print(tree[i] + " ");
        }
        System.out.println();
        int max = getMax(arr, 2, 5);
        System.out.println(max);
        update(arr, 2, 20);
        max = getMax(arr, 2, 5);
        System.out.println(max);

        buildTreeMin(0,0,n-1,arr);
        for(int i=0; i<tree.length; i++){
            System.out.print(tree[i] + " ");
        }
        System.out.println();
        int min = getMin(arr, 2, 5);
        System.out.println("Minimum value is: "+min);

        updateMin(arr, 2, 20);
        min = getMin(arr, 2, 5);
        System.out.println("Minimum value is: "+min);
        
    }
}