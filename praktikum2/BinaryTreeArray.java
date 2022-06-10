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