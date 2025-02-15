class AVLNode extends Node {
    int height;

    public AVLNode(int item) {
        super(item);
        height = 1;
    }
}

class AVLTree extends BinarySearchTree {
    // Get height of node
    public int height(AVLNode node) { // Change 'private' to 'public'
        if (node == null) return 0;
        return node.height;
    }
    // Get balance factor
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height((AVLNode)node.left) - height((AVLNode)node.right);
    }
    // Update height
    private void updateHeight(AVLNode node) {
        if (node == null) return;
        node.height = Math.max(height((AVLNode)node.left), height((AVLNode)node.right)) + 1;
    }
    // Rotate right
    private AVLNode rotateRight(AVLNode y) {
        if (y == null || y.left == null) return y;

        AVLNode x = (AVLNode) y.left;
        AVLNode T2 = (AVLNode) x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Rotate left
    private AVLNode rotateLeft(AVLNode x) {
        if (x == null || x.right == null) return x;

        AVLNode y = (AVLNode) x.right;
        AVLNode T2 = (AVLNode) y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }
    // Insert
    @Override
    public void insert(int key) {
        root = insertRec((AVLNode)root, key);
    }
    private AVLNode insertRec(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.key)
            node.left = insertRec((AVLNode) node.left, key);
        else if (key > node.key)
            node.right = insertRec((AVLNode) node.right, key);
        else
            return node; // Ignore duplicate keys

        updateHeight(node);
        int balanceFactor = getBalance(node);

        // **Check null before accessing `key`**
        if (balanceFactor > 1 && node.left != null && key < node.left.key)
            return rotateRight(node);

        if (balanceFactor < -1 && node.right != null && key > node.right.key)
            return rotateLeft(node);

        if (balanceFactor > 1 && node.left != null && key > node.left.key) {
            node.left = rotateLeft((AVLNode) node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && node.right != null && key < node.right.key) {
            node.right = rotateRight((AVLNode) node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Delete
    @Override
    public void delete(int key) {
        root = deleteRec((AVLNode)root, key);
    }
    private AVLNode deleteRec(AVLNode node, int key) {
        // TODO: Implement AVL delete with balancing
        return null;
    }
}