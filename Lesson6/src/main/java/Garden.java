import java.util.ArrayList;
import java.util.Objects;

public class Garden {
    private static class Cat implements Comparable {
        int age;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age &&
                    Objects.equals(name, cat.name);
        }

        @Override
        public int compareTo(Object o) {
            Cat cat = (Cat) o;
            return (this.age - cat.age);
        }

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("C(%s, %d)", name, age);
        }

    }

    private static class Tree {

        public int maxBranch = 0;
        public int minBranch = 0;

        @Override
        public String toString() {
            return String.format("Tree [%d/%d]", minBranch, maxBranch);
        }

        private static class TreeNode implements Comparable {

            private Cat c;
            public TreeNode left;
            public TreeNode right;

            public TreeNode(Cat c) {
                this.c = c;
            }

            public Cat getCat() {
                return c;
            }

            @Override
            public String toString() {
                return String.format("TN(%s)", c);
            }

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof TreeNode))
                    throw new ClassCastException("Not a TreeNode!");
                return getCat().age - ((TreeNode) o).getCat().age;
            }
        }

        TreeNode root;

        private void addNode(TreeNode current, TreeNode newNode, int branch) {
            TreeNode parent;
            parent = current;
            if (newNode.compareTo(current) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    if (branch > maxBranch) maxBranch = branch;
                    else if (branch < minBranch || minBranch == 0) minBranch = branch;
                    return;
                }
            } else if (newNode.compareTo(current) > 0) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    if (branch > maxBranch) maxBranch = branch;
                    else if (branch < minBranch || minBranch == 0) minBranch = branch;
                    return;
                }
            } else {
                return;
            }
            addNode(current, newNode, ++branch);
        }

        public void insert(Cat c) {
            TreeNode node = new TreeNode(c);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                addNode(current, node,0);
            }
        }

        public Cat find(int age) {
            TreeNode current = root;
            while (current.c.age != age) {
                current = (age < current.c.age) ? current.left : current.right;
                if (current == null) return null;
            }
            return current.c;
        }

        public void preOrderTraverse(TreeNode currentNode) {
            if (currentNode != null) {
                System.out.println(currentNode);
                preOrderTraverse(currentNode.left);
                preOrderTraverse(currentNode.right);
            }
        }

        public void preOrderTraverse(TreeNode currentNode, int branch) {
            if (currentNode != null) {
                System.out.printf("LVL %2d: %s%n", branch++, currentNode);
                preOrderTraverse(currentNode.left, branch);
                preOrderTraverse(currentNode.right, branch);
            }
        }


        // LeftRootRight
        // RightLeftRoot
        public void displayTree() {
            preOrderTraverse(root);
        }

        public Cat delete(int age) {
            TreeNode current = root;
            TreeNode parent = root;
            boolean isLeftChild = true;
            while (current.c.age != age) {
                parent = current;
                if (age < current.c.age) {
                    current = current.left;
                    isLeftChild = true;
                } else {
                    current = current.right;
                    isLeftChild = false;
                }
                if (current == null) {
                    return null;
                }
            }
            //leaf
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // one ancestor
            else if (current.right == null) {
                if (isLeftChild)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            } else if (current.left == null) {
                if (isLeftChild)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
            // two ancestors
            else {
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            return current.c;
        }

        private TreeNode getSuccessor(TreeNode node) {
            TreeNode current = node.right;
            TreeNode parent = node;
            TreeNode successor = node;
            while (current != null) {
                parent = successor;
                successor = current;
                current = current.left;
            }

            if (successor != node.right) {
                parent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }

    }


    public static void main(String[] args) {
        ArrayList<Tree> garden = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            garden.add(new Tree());
            for (int j = 0; j < 100; j++) {
                garden.get(i).insert(new Cat((int) (Math.random() * 200 - 99), "Duncan"));
            }
            System.out.printf("%s %2d%n", garden.get(i), i);
            garden.get(i).displayTree();
        }
    }
}
