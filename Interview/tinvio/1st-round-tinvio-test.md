import java.io.*;
import java.util.*; 

/* 
* To execute Java, please define "static void main" on a class 
* named Main. 
*
Given a Binary Tree, find the vertical sum of the nodes that are in the same vertical line.
 Print all sums through different vertical lines.

      1
    /   \
  2 (-1)      3 (1)
 / \    / \
4(-2)   5(0)  6(0)   7(2)


expected output is 4, 2, 12, 3 and 7 


* If you need more classes, simply define them inline. 
*/ 
class BNode{
    int data;
    BNode left;
    BNode right;

    public BNode(BNode left, BNode right, int data) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
class Main { 
	public static void main(String[] args) {
        BNode leafNode1 = new BNode(null, null, 4);
        BNode leafNode2 = new BNode(null, null, 5);
        BNode leafNode3 = new BNode(null, null, 6);
        BNode leafNode4 = new BNode(null, null, 7);

        BNode leftNode = new BNode(leafNode1, leafNode2, 2);
        BNode rightNode = new BNode(leafNode3, leafNode4, 3);

		BNode root = new BNode(leftNode, rightNode, 1);

        printVerticalSum(root);
	}

    public static void printVerticalSum(BNode node) {
        Map<Integer, Integer> output = new TreeMap<>();
        iterateBNodes(node, 0, output);

        for (Integer data : output.values()) {
            System.out.println(data + " ");
        }
    }

    public static void iterateBNodes(BNode node, int pos, Map<Integer, Integer> output) {
        if (node == null) {
            return;
        }

        output.put(pos, output.getOrDefault(pos, 0) + node.data);

        iterateBNodes(node.left, pos - 1, output);
        iterateBNodes(node.right, pos + 1, output);
    }
}


// Design:

// A file search application
// Basic requirements:
// - files can be searched in local system/ftp server/cloud services (s3, gdrive) --> read-heavy operation
// - files searches can be done on
// 	- size, eg: all files size > 5 MB.
// 	- type of file, eg: jpeg file
// 	- based on name.
// 	- all of them or any of them , .jpeg file with size > 1 MB

// Things to focus:

// - Focus on class design
// - Focus on data modeling
// - Structure of your program
// - Should be future proof

class FileInfo {
    private long size;
    private String type;
    private String nameFile;

    private String url;
}

interface SearchCritera {
    boolean isValid(FileInfo fileInfo);

    SearchCritera and(SearchCritera otherCritera);
}

abstract class Connection {
    void connect(String path);

    List<FileInfo> search(Criteria criteria);
}

class LocalConnection extends Connection {

}

class S3Connection extends Connection {
    
}

// ... connection like ftp

class ConnectionFactory {
    public static Connection createConnection(String path) {
        // path: (http|ftp)://domain-name/path
        // 1st: extract protocol.

        // 

    }
}

LocalFileSearch



class Lib {
    search("search criteria...", "path.."){
        //build the criteria
        
        // connect to source
        Connection connection = ConnectionFactory.createConnection(path);

        // 
    } 
}

main(){

    Lib 
    .search(
        "file search... criteria",
        "ftp://localtion...."
    )
}

