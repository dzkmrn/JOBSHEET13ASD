# JOBSHEET 13: TREE
## Nama : Muhammad Dzaka Murran Rusid
## Kelas/abs : 1F_D4-TI/18
#
1. Tujuan Praktikum

Setelah melakukan praktikum ini, mahasiswa mampu: 

1. memahami model Tree khususnya Binary Tree
2. membuat dan mendeklarasikan struktur algoritma Binary Tree.
3. menerapkan dan mengimplementasikan algoritma Binary Tree dalam kasus 
Binary Search Tree
<br>

### 2.1 Implementasi Binary Search Tree menggunakan Linked List

#### 2.1.1 Tahapan percobaan

Waktu Percobaan (45 menit)
Pada percobaan ini akan diimplementasikan Binary Search Tree dengan operasi dasar, 
dengan menggunakan array (praktikum 2) dan linked list (praktikum 1). Sebelumnya, 
akan dibuat class Node, dan Class BinaryTree
1. Buatlah class Node, BinaryTree dan BinaryTreeMain
2. Di dalam class Node, tambahkan atribut data, left dan right, serta konstruktor 
default dan berparameter.
3. Di dalam class BinaryTree, tambahkan atribut root.
4. Tambahkan konstruktor default dan method isEmpty() di dalam class BinaryTree
5. Tambahkan method add() di dalam class BinaryTree. Di bawah ini proses 
penambahan node tidak dilakukan secara rekursif, agar lebih mudah dilihat alur 
proses penambahan node dalam tree. Sebenarnya, jika dilakukan dengan proses
6. Tambahkan method find()
7. Tambahkan method traversePreOrder(), traverseInOrder() dan
traversePostOrder(). Method traverse digunakan untuk mengunjungi dan 
menampilkan node-node dalam tree, baik dalam mode pre-order, in-order 
maupun post-order.
8. Tambahkan method getSuccessor(). Method ini akan digunakan ketika proses 
penghapusan node yang memiliki 2 child.
9. Tambahkan method delete().
Di dalam method delete tambahkan pengecekan apakah tree kosong, dan jika tidak 
cari posisi node yang akan di hapus. Kemudian tambahkan proses penghapusan terhadap node current yang telah 
ditemukan.
10. Buka class BinaryTreeMain dan tambahkan method main().
11. Compile dan jalankan class BinaryTreeMain untuk mendapatkan simulasi jalannya 
program tree yang telah dibuat.
12. Amati hasil running tersebut.
```java
package praktikum;

public class Node{
    int data;
    Node left;
    Node right;


    public Node(){    
    }
    public Node(int data){
        this.left = null;
        this.data = data;
        this.right = null;
    }
}
```

```java
package praktikum;

public class BinaryTree {
    Node root;

    public BinaryTree(){
        root = null;
    }
    boolean isEmpty(){
        return root==null;
    }

    void add(int data){
        if(isEmpty()){
            root = new Node(data);
        }else{
            Node current = root;
            while(true){
                if(data<current.data){
                    if(current.left!=null){
                        current = current.left;
                    }else{
                        current.left = new Node(data);
                        break;
                    }
                }else if(data>current.data){
                    if(current.right!=null){
                        current = current.right;
                    }else{
                        current.right = new Node(data);
                        break;
                    }
                }else{
                    break;
                }
            }
        }
    }

    boolean find(int data){
        boolean hasil = false;
        Node current = root;
        while(current!=null){
            if(current.data==data){
                hasil = true;
                break;
            }else if(data<current.data){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return hasil;
    }

    void traversePreOrder(Node node){
        if(node != null){
            System.out.println(" " + node.data);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    void traversePostOrder(Node node){
        if(node != null){
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.println(" " + node.data);
        }
    }

    void traverseInOrder(Node node){
        if(node != null){
            traverseInOrder(node.left);
            System.out.println(" " + node.data);
            traverseInOrder(node.right);
        }
    }

    Node getSuccessor(Node del){
        Node successor = del.right;
        Node successorParent = del;
        while(successor.left!=null){
            successorParent = successor;
            successor = successor.left;
        }
        if(successor != del.right){
            successorParent.left = successor.right;
            successor.right = del.right;
        }
        return successor;
    }

    void delete(int data){
        if(isEmpty()){
            System.out.println("Tree is empty!");
            return;
        }

        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while(current != null){
            if(current!=null){
                if(current.data==data){
                    break;
                }else if(data<current.data){
                    parent = current;
                    current = current.left;
                    isLeftChild = true;
                }else if(data>current.data){
                    parent = current;
                    current = current.right;
                    isLeftChild = false;
                }
            }
        }

        if(current==null){
            System.out.println("Couldn't find data!");
            return;
        }else{
            if(current.left==null&&current.right==null){
                if(current==root){
                    root = null;
                }else{
                    if(isLeftChild){
                        parent.left = null;
                    }else{
                        parent.right = null;
                    }
                }
            }else if(current.left==null){
            if(current==root){
                root = current.right;
            }else{
                if(isLeftChild){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }
            }else if(current.right==null){
                if(current==root){
                    root=current.left;
                }else{
                    if(isLeftChild){
                        parent.left = current.left;
                    }else{
                        parent.right = current.left;
                    }
                }
            }else{
                Node successor = getSuccessor(current);
                if(current==root){
                    root = successor;
                }else{
                    if(isLeftChild){
                        parent.left = successor;
                    }else{
                        parent.right = successor;
                    }
                    successor.left = current.left;
                }
            }
        }
    }
}


```

```java
package praktikum;

public class BinaryTreeMain {
    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
        bt.add(10);
        bt.add(15);

        bt.traversePreOrder(bt.root);
        System.out.println("");
        bt.traverseInOrder(bt.root);
        System.out.println("");
        bt.traversePostOrder(bt.root);
        System.out.println("");
        System.out.println("Find " +bt.find(5));
        bt.delete(8);
        bt.traversePreOrder(bt.root);
        System.out.println("");
    }
}

```

<img src = "prak1.png">


#### 2.1.2 Pertanyaan Percobaan
1. Mengapa dalam binary search tree proses pencarian data bisa lebih efektif 
dilakukan dibanding binary tree biasa?

<br>

**Jawaban: Karena pada Binary Search Tree node disusun sudah secara berurutan (Pohon Biner Terurut) yang mana penempatan data berdasarkan Left Child akan selalu lebih kecil dari node induk, dan right child akan lebih besar dari node induk.**

2. Untuk apakah di class Node, kegunaan dari atribut left dan right?

<br>

**Jawaban: Pada class node atribut left berfungsi untuk menyimpan "left child" atau nilai yang lebih kecil dari root (node induk) dan atribut right berfungsi untuk menyimpan "right child" atau nilai yang lebih besar dari root (node induk)**

3. a. Untuk apakah kegunaan dari atribut root di dalam class BinaryTree?
b. Ketika objek tree pertama kali dibuat, apakah nilai dari root?

**Jawaban:** 

**a. untuk menyimpan data yang berada pada bagian paling atas tree**

**b. Ketika objek tree pertama kali dibuat, apakah nilai dari root?**


4. Ketika tree masih kosong, dan akan ditambahkan sebuah node baru, proses apa 
yang akan terjadi?

**Jawaban: **
5. Perhatikan method add(), di dalamnya terdapat baris program seperti di bawah 
ini. Jelaskan secara detil untuk apa baris program tersebut?
```java
if(data<current.data){
    if(current.left!=null){
    current = current.left;
}else{
    current.left = new Node(data);
    break;
}
}
```

**Jawaban: **

### 2.2 Implementasi binary tree dengan array
Waktu percobaan: 45 menit

#### 2.2.1 Tahapan Percobaan
1. Di dalam percobaan implementasi binary tree dengan array ini, data tree 
disimpan dalam array dan langsung dimasukan dari method main(), dan 
selanjutnya akan disimulasikan proses traversal secara inOrder.
2. Buatlah class BinaryTreeArray dan BinaryTreeArrayMain
3. Buat atribut data dan idxLast di dalam class BinaryTreeArray. Buat juga method 
populateData() dan traverseInOrder(). 
4. Kemudian dalam class BinaryTreeArrayMain buat method main() seperti gambar 
berikut ini.
5. Jalankan class BinaryTreeArrayMain dan amati hasilnya!


```java
package praktikum2;

public class BinaryTreeArray {
        int[] data;
        int idxlast;
    
        public BinaryTreeArray(){
            data = new int[10];
        }
    
        void populateData(int data[], int idxlast){
            this.data = data;
            this.idxlast = idxlast;
        }
    
        void traverseinOrder(int idxStart){
            if(idxStart <= idxlast){
                traverseinOrder(2*idxStart+1);
                System.out.println(data[idxStart]+" ");
                traverseinOrder(2*idxStart+1);
            }
        }
    }
```

```java
package praktikum2;

public class BinaryTreeArrayMain{

    public static void main(String[] args) {
            BinaryTreeArray bta = new BinaryTreeArray();
            int[] data = {6,4,8,3,5,7,9,0,0,0};
            int idxLast = 6;
            bta.populateData(data, idxLast);
            bta.traverseinOrder(0);
        
    }
}   
```
<img src = prak2.png>

#### 13.2.1 Pertanyaan Percobaan
1. Apakah kegunaan dari atribut data dan idxLast yang ada di class 
BinaryTreeArray?

**Jawaban: Atribut data berfungsi untuk menyimpan data array, sedangkan idxLast berfungsi untuk menyimpan batas index**

2. Apakah kegunaan dari method populateData()?

**Jawaban: Untuk menunjukkan data pada idxLast**

3. Apakah kegunaan dari method traverseInOrder()?

**Jawaban: Untuk menelusuri tree dengan metode in order dengan prinsip(left visit right)**

4. Jika suatu node binary tree disimpan dalam array indeks 2, maka di indeks berapakah posisi left child dan rigth child masin-masing?

**Jawaban: Left child pada indeks ke 5, dan right child pada indeks ke 6**

5. Apa kegunaan statement int idxLast = 6 pada praktikum 2 percobaan nomor 4?

**Jawaban: Untuk menunjukkan idxLast atau batas indeks arraynya adalah 6**

### 13.3 Tugas Praktikum
Waktu pengerjaan: 90 menit
1. Buat method di dalam class BinaryTree yang akan menambahkan node 
dengan cara rekursif.

```java
    void addNodeR(int key){ //Nomor 1
        root = addNodeR(root, key);
    }
    public Node addNodeR(Node current, int data){
        if (current == null){
            return new Node(data);
        }
        if (data < current.data){
            current.left = addNodeR(current.left, data);
        }else if(data > current.data){
            current.right = addNodeR(current.right, data);
        }else{
            return current;
        }
        return current;
    }
```

2. Buat method di dalam class BinaryTree untuk menampilkan nilai paling kecil 
dan yang paling besar yang ada di dalam tree.

```java
 void maksimal(){    //Nomor 2
        Node current = root;
        while(current.right != null){
            current = current.right;
        }
        System.out.println(current.data);
    }
    void minimal(){
        Node current = root;
        while(current.left != null){
            current = current.left;
        }
        System.out.println(current.data);
    }
```

3. Buat method di dalam class BinaryTree untuk menampilkan data yang ada 
di leaf.

```java
 void printLeaf(Node root){  //Nomor 3
        if(root == null){
        return;            
        }
        if(root.left == null && root.right == null){
            System.out.print(" "+ root.data);
            return;
        }if(root.left != null){
            printLeaf(root.left);
        }if(root.right != null){
            printLeaf(root.right);
        }
    }
```

4. Buat method di dalam class BinaryTree untuk menampilkan berapa jumlah 
leaf yang ada di dalam tree.

```java
       int jumlahLeaf(){   //Nomor 4
        return jumlahLeaf(root);
    }
    int jumlahLeaf(Node node){
        if(node == null){
            return 0;
        }
        if(node.left == null && node.right == null){
            return 1;
        }else{
            return jumlahLeaf(node.left)+jumlahLeaf(node.right);
        }
    }
```

<img src = tgsa.png>

5. Modifikasi class BinaryTreeArray, dan tambahkan : 
• method add(int data) untuk memasukan data ke dalam tree 
• method traversePreOrder() dan traversePostOrder()
```java
package Tugas;

public class BinaryTreeArray {
    int[] data;
    int idxlast;
    
    public BinaryTreeArray(){
        data = new int[10];
    }
    
    void populateData(int data[], int idxlast){
        this.data = data;
        this.idxlast = idxlast;
    }
    
    void traverseinOrder(int idxStart){
        if(idxStart <= idxlast){
            traverseinOrder(2*idxStart+1);
            System.out.println(data[idxStart]+" ");
            traverseinOrder(2*idxStart+2);
        }
    }
    
        void add(int data){
        if(idxlast == this.data.length -1){
            System.out.println("Tree Array sudah Penuh");
        }else{
            this.data[++idxlast] = data;
        }
    }
    void traversePreOrder(int idxStart){
        if(idxStart <= idxlast){
            System.out.print(" "+data[idxStart]);
            traversePreOrder(2 * idxStart + 1);
            traversePreOrder(2 * idxStart + 2);
        }
    }
    void traversePostOrder(int idxStart){
        if(idxStart <= idxlast){
            traversePostOrder(2 * idxStart + 1);
            traversePostOrder(2 * idxStart + 2);
            System.out.print(" "+data[idxStart]);
        }
    }
}
```

<img src = tgsb.png>