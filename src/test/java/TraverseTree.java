import java.util.LinkedList;
import java.util.Stack;

public class TraverseTree {
    public static void main(String[] args) {
//              1
//             / \
//            2   5
//           / \    \
//          3   4    6
        TreeNode rootPreOrder = 
            new TreeNode("1",
                new TreeNode("2", 
                    new TreeNode("3", 
                        null, 
                        null
                    ),
                    new TreeNode("4", 
                        null, 
                        null
                    )
                ),
                new TreeNode("5", 
                    null, 
                    new TreeNode("6", null, null)
                )
            );

        System.out.println();
        System.out.println("Pre order - recursion:");
        preOrderRecursion(rootPreOrder);
        System.out.println();
        System.out.println("Pre order - no recursion:");
        preOrder(rootPreOrder);
        System.out.println();

//              4
//             / \
//            2   5
//           / \    \
//          1   3    6
        TreeNode rootInOrder = 
        new TreeNode("4",
            new TreeNode("2", 
                new TreeNode("1", 
                    null, 
                    null
                ),
                new TreeNode("3", 
                    null, 
                    null
                )
            ),
            new TreeNode("5", 
                null, 
                new TreeNode("6", null, null)
            )
        );
        System.out.println();
        System.out.println("In order - recursion:");
        inOrderRecursion(rootInOrder);
        System.out.println();
        System.out.println("In order - no recursion:");
        inOrder(rootInOrder);
        System.out.println();

//              1
//             / \
//            2   3
//           / \    \
//          4   5    6
TreeNode bfsTree = 
new TreeNode("1",
    new TreeNode("2", 
        new TreeNode("4", 
            null, 
            null
        ),
        new TreeNode("5", 
            null, 
            null
        )
    ),
    new TreeNode("3", 
        null, 
        new TreeNode("6", null, null)
    )
);
System.out.println();
System.out.println("Breadth first search:");
breadthFirstSearch(bfsTree);
System.out.println();
    
}

    public static void preOrderRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.printf("%s ", treeNode.value);
        preOrderRecursion(treeNode.left);
        preOrderRecursion(treeNode.right);
    }

    public static void preOrder(TreeNode treeNode) {
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(treeNode);

        while (!nodes.isEmpty()) {
            TreeNode current = nodes.pop();
            System.out.printf("%s ", current.value);
            
            TreeNode right = current.right;
            if (right != null) nodes.push(right);

            TreeNode left = current.left;
            if (left != null) nodes.push(left);
        }
    }

    public static void inOrderRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrderRecursion(treeNode.left);
        System.out.printf("%s ", treeNode.value);
        inOrderRecursion(treeNode.right);
    }

    public static void inOrder(TreeNode treeNode) {
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode current = treeNode;
        while (!nodes.isEmpty() || current != null) {
            if (current != null) {
                nodes.push(current);
                current = current.left;
            } else {
                TreeNode node = nodes.pop();
                System.out.printf("%s ", node.value);
                current = node.right;
            }
        }
    }

    public static void breadthFirstSearch(TreeNode treeNode) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(treeNode);

        while (!nodes.isEmpty()) {
            TreeNode current = nodes.removeFirst();
            System.out.printf("%s ", current.value);

            if (current.left != null) nodes.add(current.left);
            if (current.right != null) nodes.add(current.right);
        }
    }

}

class TreeNode {
    public String value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(String value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
