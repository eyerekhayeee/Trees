import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FamilyMember {
    String name;
    FamilyMember parent;
    List<FamilyMember> children;

    public FamilyMember(String name, FamilyMember parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
}

public class FamilyTree {
    private FamilyMember root;  // The root of the tree (ancestor or first generation)

    public FamilyTree(String rootName) {
        root = new FamilyMember(rootName, null);
    }

    // Add a family member under a specific parent
    public void addFamilyMember(String childName, String parentName) {
        FamilyMember parent = findMember(root, parentName);
        if (parent != null) {
            FamilyMember newMember = new FamilyMember(childName, parent);
            parent.children.add(newMember);
            System.out.println("Family member " + childName + " added under " + parentName);
        } else {
            System.out.println("Parent " + parentName + " not found.");
        }
    }

    // Find a family member by name (recursive search)
    public FamilyMember findMember(FamilyMember node, String name) {
        if (node == null) return null;
        if (node.name.equals(name)) return node;
        
        for (FamilyMember child : node.children) {
            FamilyMember found = findMember(child, name);
            if (found != null) return found;
        }
        return null;
    }

    // Display the family tree starting from the root
    public void displayTree(FamilyMember node, String indent) {
        if (node != null) {
            System.out.println(indent + node.name);
            for (FamilyMember child : node.children) {
                displayTree(child, indent + "  ");
            }
        }
    }

    // Search for children of a specific family member
    public void searchChildren(String parentName) {
        FamilyMember parent = findMember(root, parentName);
        if (parent != null) {
            if (parent.children.isEmpty()) {
                System.out.println(parentName + " has no children.");
            } else {
                System.out.println("Children of " + parentName + ":");
                for (FamilyMember child : parent.children) {
                    System.out.println(child.name);
                }
            }
        } else {
            System.out.println("Family member " + parentName + " not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FamilyTree tree = new FamilyTree("Eyerekhaye"); // Start with a root family member

        while (true) {
            System.out.println("\nFamily Tree Options:");
            System.out.println("1. Add Family Member");
            System.out.println("2. View Family Tree");
            System.out.println("3. Search for Children");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter child's name: ");
                    String childName = scanner.nextLine();
                    System.out.print("Enter parent's name: ");
                    String parentName = scanner.nextLine();
                    tree.addFamilyMember(childName, parentName);
                    break;
                case 2:
                    System.out.println("Displaying Family Tree:");
                    tree.displayTree(tree.root, "");
                    break;
                case 3:
                    System.out.print("Enter family member's name to find children: ");
                    String name = scanner.nextLine();
                    tree.searchChildren(name);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
