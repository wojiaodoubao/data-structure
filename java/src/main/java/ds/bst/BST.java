package ds.bst;

import java.util.ArrayList;
import java.util.List;

public class BST <T extends Comparable>{
    private Node root;
    public BST(T value) throws Exception {
        if(value==null){
            throw new Exception("Couldn't handle null value!");
        }
        root = new Node(value);
    }
    private class Node{
        private Node left;
        private Node right;
        T value;
        public Node(T value) {
            this.value = value;
        }
    }

    public void add(T value) throws Exception {
        if(value==null){
            throw new Exception("Couldn't handle null value!");
        }
        add(this.root,value);
    }
    private Node add(Node root,T value){
        if (root == null) {
            return new Node(value);
        }
        else if(root.value.compareTo(value)>0){
            root.left = add(root.left,value);
        }
        else if(root.value.compareTo(value)<0){
            root.right = add(root.right,value);
        }
        return root;
    }
    public T find(T value){
        return find(root,value);
    }
    private T find(Node root,T value){
        if(root == null){
            return null;
        }
        else if(root.value.compareTo(value)>0){
            return find(root.left,value);
        }
        else if(root.value.compareTo(value)<0){
            return find(root.right,value);
        }
        else {
            return root.value;
        }
    }
    public void delete(T value){
        delete(root, value);
    }
    private Node delete(Node root, T value){
        if(root == null){
            return null;
        }
        else if(root.value.compareTo(value)<0){
            root.right = delete(root.right, value);
        }
        else if(root.value.compareTo(value)>0){
            root.left = delete(root.left, value);
        }
        else{
            if(root.right == null){
                return root.left;
            }
            else if(root.left==null){
                return root.right;
            }
            else{
                Node p = root.left;
                if(p.right == null){
                    root.left = p.left;
                    root.value = p.value;
                }
                else{
                    while(p.right.right !=null)p=p.right;
                    root.value = p.right.value;
                    p.right = p.right.left;
                }
            }
        }
        return root;
    }
    public List<T> serialize(){
        return serialize(root);
    }
    private List<T> serialize(Node root){
        List<T> list = new ArrayList<T>();
        if(root != null){
            list.add(root.value);
            list.addAll(serialize(root.left));
            list.addAll(serialize(root.right));
        }
        return list;
    }
    public void deserialize(List<T> list){
        root = deserialize(list,0,list.size()-1);
    }
    private Node deserialize(List<T> list,int left,int right){
        if(left>right){
            return null;
        }
        else{
            T mid = list.get(left);
            Node root = new Node(mid);
            int i = left+1;
            while(i<=right && list.get(i).compareTo(mid)<0)i++;
            root.left = deserialize(list,left+1,i-1);
            root.right = deserialize(list,i,right);
            return root;
        }
    }
    public List<T> inOrder(){
        return midRoot(root);
    }
    private List<T> midRoot(Node root){
        List<T> list = new ArrayList<T>();
        if(root != null){
            list.addAll(midRoot(root.left));
            list.add(root.value);
            list.addAll(midRoot(root.right));
        }
        return list;
    }
    public static void main(String args[]) throws Exception {
        BST tree = new BST(10);
        int[] seq = {10,9,15,13,14,17};
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<seq.length;i++) {
            tree.add(seq[i]);
        }
        System.out.println(tree.serialize());
        tree.deserialize(tree.serialize());
        System.out.println(tree.serialize());
        System.out.println(tree.inOrder());
    }
}



/*
List<Node> tree;
*/
