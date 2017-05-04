/*The binary search tree can handle duplicate keys*/

import java.util.*;
public class DuplicateBST{
    public class Node{
        Node left;
        Node right;
        Node parent;
        int key;
        List<Integer> data;

        public Node(int x){
            key = x;
            data = new LinkedList<>();
        }

//        public Node(int k, int d){
//            key = k ;
//            Data d = new Data(k,d);
//        }

        public void add(int d){
            data.add(d);
        }
    }

    private Node root;

    public DuplicateBST(){
        root = null;
    }

    public void insert(int x, int d){
        Node cur = root;
        if(root == null){
            root = new Node(x);
            return;
        }
        Node help = null;
        while(cur != null){
            help = cur;
            if(x>cur.key){
                cur = cur.right;
            }else if(x < cur.key){
                cur = cur.left;
            }else{
                break;
            }
        }
        Node child = new Node(x);
        if(x>help.key){
            help.right = child;
            help.right.add(d);
            child.parent= help;

        }else if(x<help.key){
            help.left = child;
            help.left.add(d);
            child.parent = help;
        }else{
            help.add(d);
        }
    }

    public void print(){
        print(root);
    }

    private void print(Node root){
        if(root == null){
            return;
        }
        print(root.left);
        System.out.print("key is: "+ root.key+" nums: "+root.data.size()+" ");
        print(root.right);
    }

    private Node maximum(Node n){
        Node cur = n;
        while(cur.right != null){
            cur = cur.right;
        }
        return cur;
    }

    private Node minimum (Node n){
        Node cur = n;
        while(cur.left != null){
            cur = cur.left;
        }
        return cur;
    }

    private Node successor(Node n){
        if(n.right != null){
            return minimum(n.right);
        }
        Node y = n.parent;
        while(y!= null && n == y.right){
            n=y;
            y = n.parent;
        }
        return y;
    }

    private void transplante(Node u, Node v){
        if(u.parent == null){
            root = v;
        }else if( u == u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        if(v != null){
            v.parent = u.parent;
        }
    }

    private void delete(int x){
        Node cur = root;
        while(cur != null){
            if(cur.key>x){
                cur = cur.left;
            }else if(cur.key<x){
                cur = cur.right;
            }else{
                delete(cur);
                break;
            }
        }
    }

    private void delete(Node cur){
        int size = cur.data.size();
        if(size>1){
            int length = cur.data.size();
            cur.data.remove(length-1);
        }else{
            if(cur.left == null){
                transplante(cur,cur.right);
            }else if(cur.right == null) {
                transplante(cur, cur.left);
            }else{
                Node y = minimum(cur.right);
                if(y.parent != cur){
                    transplante(y,y.right);
                    y.right = cur.right;
                    y.right.parent = y;
                }
                transplante(cur,y);
                y.left = cur.left;
                y.left.parent = y;

            }
        }
    }

    public static void main(String[] args){
        int[] nums = {7,8,9,0,1,1,1,2,3,4,5,6,7,89,2,45,76,3};
        int[] data = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        DuplicateBST solution = new DuplicateBST();
        for(int i=0;i<nums.length;i++){
            solution.insert(nums[i],data[i]);
        }
        solution.print();
        for(int i=0;i<nums.length;i++){
            solution.delete(nums[i]);
        }
        solution.print();
    }
}