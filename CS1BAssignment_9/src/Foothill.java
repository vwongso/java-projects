public class Foothill
{ 
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      FHsdTree<String> sceneTree = new FHsdTree<String>();
      FHsdTreeNode<String> tn;

      // create a scene in a room
      tn = sceneTree.addChild(null, "room");

      // add three objects to the scene tree
      sceneTree.addChild(tn, "Lily the canine");
      sceneTree.addChild(tn, "Miguel the human");
      sceneTree.addChild(tn, "table");
      // add some parts to Miguel
      tn = sceneTree.find("Miguel the human");

      // Miguel's left arm
      tn = sceneTree.addChild(tn, "torso");
      tn = sceneTree.addChild(tn, "left arm");
      tn =  sceneTree.addChild(tn, "left hand");
      sceneTree.addChild(tn, "thumb");
      sceneTree.addChild(tn, "index finger");
      sceneTree.addChild(tn, "middle finger");
      sceneTree.addChild(tn, "ring finger");
      sceneTree.addChild(tn, "pinky");

      // Miguel's right arm
      tn = sceneTree.find("Miguel the human");
      tn = sceneTree.find(tn, "torso", 0);
      tn = sceneTree.addChild(tn, "right arm");
      tn =  sceneTree.addChild(tn, "right hand");
      sceneTree.addChild(tn, "thumb");
      sceneTree.addChild(tn, "index finger");
      sceneTree.addChild(tn, "middle finger");
      sceneTree.addChild(tn, "ring finger");
      sceneTree.addChild(tn, "pinky");

      // add some parts to Lily
      tn = sceneTree.find("Lily the canine");
      tn = sceneTree.addChild(tn, "torso");
      sceneTree.addChild(tn, "right front paw");
      sceneTree.addChild(tn, "left front paw");
      sceneTree.addChild(tn, "right rear paw");
      sceneTree.addChild(tn, "left rear paw");
      sceneTree.addChild(tn, "spare mutant paw");
      sceneTree.addChild(tn, "wagging tail");

      // add some parts to table
      tn = sceneTree.find("table");
      sceneTree.addChild(tn, "north east leg");
      sceneTree.addChild(tn, "north west leg");
      sceneTree.addChild(tn, "south east leg");
      sceneTree.addChild(tn, "south west leg");

      System.out.println("Tree:");
      sceneTree.display();

      sceneTree.remove("spare mutant paw");
      sceneTree.remove("Miguel the human");
      sceneTree.remove("an imagined higgs boson");

      System.out.println("\nVirtual Tree:");
      sceneTree.display();
      System.out.println("\nPhysical Tree:");
      sceneTree.displayPhysical();

      System.out.println("\nSize Test:");
      System.out.println("Virtual size: " + sceneTree.size());
      System.out.println("Physical size: "
         + sceneTree.sizePhysical() + "\n");

      System.out.println("collectingGarbage:");
      System.out.println("Garbage removed: "
         + sceneTree.collectGarbage());
      System.out.println("Collect again immediately: "
         + sceneTree.collectGarbage() + "\n");
      
      // size should be the same
      System.out.println("Size Test after collectingGarbage:");
      System.out.println("Virtual size: " + sceneTree.size());
      System.out.println("Physical size: "
         + sceneTree.sizePhysical() + "\n");

      System.out.println("Physical Tree display after collectGarbage: ");
      sceneTree.displayPhysical();
      System.out.println("\nTree empty: " + sceneTree.empty());
      sceneTree.remove("room"); // removing root
      System.out.println("Tree empty after root removed: "
         + sceneTree.empty()); 
   }
}

//file FHsdTreeNode.java --------------------------------------

class FHsdTreeNode<E>
{
   // use protected access so the tree, in the same package, 
   // or derived classes can access members 
   protected FHsdTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHsdTreeNode<E> myRoot;  // needed to test for certain error
   protected boolean deleted;

   public FHsdTreeNode( E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld,
      FHsdTreeNode<E> prv )
   {
      firstChild = chld; 
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
      deleted = false;
   }

   public FHsdTreeNode()
   {
      this(null, null, null, null);
   }

   public E getData() { return data; }

   // for use only by FHtree (default access)
   protected FHsdTreeNode( E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld, 
      FHsdTreeNode<E> prv, FHsdTreeNode<E> root )
   {
      this(d, sb, chld, prv);
      myRoot = root;
      deleted = false;
   }
}

//file FHsdTree.java
class FHsdTree<E> implements Cloneable
{
   protected int mSize;
   protected FHsdTreeNode<E> mRoot;

   public FHsdTree() { clear(); }
   public boolean emptyPhysical() { return (mSize == 0); } // old empty()
   public int sizePhysical() { return mSize; } // old size()
   public void clear() { mSize = 0; mRoot = null; }

   public FHsdTreeNode<E> find(E x) { return find(mRoot, x, 0); }
   public boolean remove(E x) { return remove(mRoot, x); }
   public void display()  { display(mRoot, 0); }
   public void displayPhysical() { displayPhysical(mRoot, 0); }

   public FHsdTreeNode<E> addChild( FHsdTreeNode<E> treeNode,  E x )
   {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0)
      {
         if (treeNode != null)
            return null; // error something's fishy.  treeNode can't be right

         mRoot = new FHsdTreeNode<E>(x, null, null, null);
         mRoot.myRoot = mRoot;
         mSize = 1;

         return mRoot;
      }

      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent

      if (treeNode.myRoot != mRoot)
         return null;  // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      FHsdTreeNode<E> newNode = new FHsdTreeNode<E>(x, 
         treeNode.firstChild, null, treeNode, mRoot);  // sb, chld, prv, rt

      treeNode.firstChild = newNode;

      if (newNode.sib != null)
         newNode.sib.prev = newNode;

      ++mSize;

      return newNode;
   }

   public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level)
   {
      FHsdTreeNode<E> retval;

      if (mSize == 0 || root == null)
         return null;

      if (root.deleted == true)
         return null;

      if (root.data.equals(x))
         return root;

      // otherwise, recurse.  don't process sibs if this was the original call
      if ( level > 0 && (retval = find(root.sib, x, level)) != null )
         return retval;

      return find(root.firstChild, x, ++level);
   }

   public boolean remove(FHsdTreeNode<E> root, E x)
   {
      FHsdTreeNode<E> tn = null;

      if (mSize == 0 || root == null)
         return false;

      if ( (tn = find(root, x, 0)) != null )
      {
         tn.deleted = true;
         return true;
      }
      return false;
   }

   private void removeNode(FHsdTreeNode<E> nodeToDelete)
   {
      if (nodeToDelete == null || mRoot == null)
         return;

      if (nodeToDelete.myRoot != mRoot)
         return;  // silent error, node does not belong to this tree

      // remove all the children of this node
      // (but for decrementing mSize, this loop might be unnecessary)
      while (nodeToDelete.firstChild != null)
         removeNode(nodeToDelete.firstChild);

      if (nodeToDelete.prev == null)
         mRoot = null;  // last node in tree
      else if (nodeToDelete.prev.sib == nodeToDelete)
         nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
      else
         nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent

      // adjust the successor sib's prev pointer
      if (nodeToDelete.sib != null)
         nodeToDelete.sib.prev = nodeToDelete.prev;

      --mSize;
   }

   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      FHsdTree<E> newObject = (FHsdTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);

      return newObject;
   }

   private FHsdTreeNode<E> cloneSubtree(FHsdTreeNode<E> root)
   {
      FHsdTreeNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHsdTreeNode<E>
      (
         root.data, 
         cloneSubtree(root.sib), cloneSubtree(root.firstChild),
         null
         );

      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }

   // recursively sets all myRoots to mRoot
   private void setMyRoots(FHsdTreeNode<E> treeNode)
   {
      FHsdTreeNode<E> child;

      if (mRoot == null)
         return;

      treeNode.myRoot = mRoot;
      for (child = treeNode.firstChild; child != null; child = child.sib)
         setMyRoots(child);
   }

   // define this as a static member so recursive display() does not need
   // a local version
   final static String blankString = "                                    ";

   protected void display(FHsdTreeNode<E> treeNode, int level) 
   {
      FHsdTreeNode<E> child;
      String indent;

      // stop runaway indentation/recursion
      if  (level > (int)blankString.length() - 1)
      {
         System.out.println( blankString + " ... " );
         return;
      }

      indent = blankString.substring(0, level);

      if (mRoot == null || mRoot.deleted)
         return;

      // pre-order processing done here ("visit")
      System.out.println( indent + treeNode.data ) ;

      // recursive step done here
      for (child = treeNode.firstChild; child != null; child = child.sib)
      {
         if(child.deleted)
            continue;

         display(child, level+1);
      }
   }

   public < F extends TraverserG< ? super E > > 
   void traverse(F func)
   {
      traverse(func, mRoot);
   }

   protected <F extends TraverserG<? super E>> 
   //  public <F extends TraverserG<E>>  // also works but less flexibly
   void traverse(F func, FHsdTreeNode<E> treeNode)
   {
      FHsdTreeNode<E> child;
      if (treeNode == null)
         return;

      func.visit(treeNode.data);

      for (child = treeNode.firstChild; child != null; child = child.sib)
         traverse(func, child);
   }

   public boolean empty()
   {
      if(size() == 0)
         return true;

      return false;
   }

   public int size()
   {
      int counter = 0;

      if(mRoot == null)
         return counter;

      return size(mRoot, counter);
   }

   public int size(FHsdTreeNode<E> treeNode, int counter)
   {
      if(!treeNode.deleted)
         ++counter;

      if(treeNode.sib != null)
         counter = size(treeNode.sib, counter);

      if(treeNode.firstChild != null && !treeNode.deleted)
         counter = size(treeNode.firstChild, counter);

      return counter;
   }

   public void displayPhysical(FHsdTreeNode<E> treeNode, 
      int level)
   {
      FHsdTreeNode<E> child;
      String indent;

      if  (level > (int)blankString.length() - 1)
      {
         System.out.println( blankString + " ... " );
         return;
      }

      indent = blankString.substring(0, level);

      if (mRoot == null)
         return;

      System.out.println( indent + treeNode.data );

      for (child = treeNode.firstChild; child != null; child = child.sib)
         display(child, level+1);
   }

   public boolean collectGarbage()
   {
      boolean value = false;

      if (mRoot == null)
         return value;

      return collectGarbage(mRoot, value);
   }

   public boolean collectGarbage(FHsdTreeNode<E> treeNode,
      boolean value)
   {
      if(treeNode.sib != null)
         value = collectGarbage(treeNode.sib, value);

      if(treeNode.firstChild != null)
         value = collectGarbage(treeNode.firstChild,value);

      if(treeNode.deleted == true)
      {
         removeNode(treeNode);
         value = true;
      }

      return value;
   }
}

//file TraverserG.java

interface TraverserG<E>
{
   public void visit(E x);
}

/*----------------------------- sample run ------------------------------------
 * 
Tree:
room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw
   left rear paw
   right rear paw
   left front paw
   right front paw

Virtual Tree:
room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

Physical Tree:
room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

Size Test:
Virtual size: 13
Physical size: 30

collectingGarbage:
Garbage removed: true
Collect again immediately: false

Size Test after collectingGarbage:
Virtual size: 13
Physical size: 13

Physical Tree display after collectGarbage: 
room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

Tree empty: false
Tree empty after root removed: true

-----------------------------------------------------------------------------*/