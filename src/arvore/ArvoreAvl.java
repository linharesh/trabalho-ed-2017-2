package arvore;

import java.lang.StringBuilder;
import main.Fluxo;

class ArvoreAvl {

	public NoArvoreAvl raiz;

	public int countInsertions;
	public int countSingleRotations;
	public int countDoubleRotations;

	public ArvoreAvl() {
		raiz = null;
		countInsertions = 0;
		countSingleRotations = 0;
		countDoubleRotations = 0;
	}

	public int altura(NoArvoreAvl no) {
		return no == null ? -1 : no.altura;
	}

	public boolean insert(Fluxo fluxo) {
		try {
			raiz = insert(fluxo, raiz);
			countInsertions++;
			return true;
		} catch (Exception e) { // TODO: catch a DuplicateValueException instead!
			return false;
		}
	}

	protected NoArvoreAvl insert(Fluxo fluxo, NoArvoreAvl no) throws Exception {
		if (no == null)
			no = new NoArvoreAvl(fluxo.getFluxo());
		else if (fluxo.getFluxo() < no.fluxo) {
			no.filhoEsquerda = insert(fluxo, no.filhoEsquerda);
			if (altura(no.filhoEsquerda) - altura(no.filhoDireita) == 2) {
				if (fluxo.getFluxo() < no.filhoEsquerda.fluxo) {
					no = rotateWithLeftChild(no);
					countSingleRotations++;
				} else {
					no = doubleWithLeftChild(no);
					countDoubleRotations++;
				}
			}
		} else if (fluxo.compareTo(no.element) > 0) {
			no.right = insert(fluxo, no.right);

			if (altura(no.right) - altura(no.left) == 2)
				if (fluxo.compareTo(no.right.element) > 0) {
					no = rotateWithRightChild(no);
					countSingleRotations++;
				} else {
					no = doubleWithRightChild(no);
					countDoubleRotations++;
				}
		} else {
			throw new Exception("Attempting to insert duplicate value");
		}

		no.height = max(altura(no.left), altura(no.right)) + 1;
		return no;
	}

	protected NoArvoreAvl rotateWithLeftChild(NoArvoreAvl k2) {
		NoArvoreAvl k1 = k2.filhoEsquerda;

		k2.filhoEsquerda = k1.filhoDireita;
		k1.filhoDireita = k2;

		k2.altura = max(altura(k2.filhoEsquerda), altura(k2.filhoDireita)) + 1;
		k1.altura = max(altura(k1.filhoEsquerda), k2.altura) + 1;

		return (k1);
	}

	protected AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	protected AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
		AvlNode<T> k2 = k1.right;

		k1.right = k2.left;
		k2.left = k1;

		k1.height = max(altura(k1.left), altura(k1.right)) + 1;
		k2.height = max(altura(k2.right), k1.height) + 1;

		return (k2);
	}

	protected AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	public String serializeInfix() {
		StringBuilder str = new StringBuilder();
		serializeInfix(raiz, str, " ");
		return str.toString();
	}

	protected void serializeInfix(AvlNode<T> t, StringBuilder str, String sep) {
		if (t != null) {
			serializeInfix(t.left, str, sep);
			str.append(t.element.toString());
			str.append(sep);
			serializeInfix(t.right, str, sep);
		}
	}

	public String serializePrefix() {
		StringBuilder str = new StringBuilder();
		serializePrefix(raiz, str, " ");
		return str.toString();
	}

	private void serializePrefix(AvlNode<T> t, StringBuilder str, String sep) {
		if (t != null) {
			str.append(t.element.toString());
			str.append(sep);
			serializePrefix(t.left, str, sep);
			serializePrefix(t.right, str, sep);
		}
	}

	public void makeEmpty() {
		raiz = null;
	}

	public boolean isEmpty() {
		return (raiz == null);
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public T findMin() {
		if (isEmpty())
			return null;

		return findMin(raiz).element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public T findMax() {
		if (isEmpty())
			return null;
		return findMax(raiz).element;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private AvlNode<T> findMin(AvlNode<T> t) {
		if (t == null)
			return t;

		while (t.left != null)
			t = t.left;
		return t;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private AvlNode<T> findMax(AvlNode<T> t) {
		if (t == null)
			return t;

		while (t.right != null)
			t = t.right;
		return t;
	}

	public void remove(T x) {
		raiz = remove(x, raiz);
	}

	public AvlNode<T> remove(T x, AvlNode<T> t) {
		if (t == null) {
			System.out.println("Sorry but you're mistaken, " + t + " doesn't exist in this tree :)\n");
			return null;
		}
		System.out.println("Remove starts... " + t.element + " and " + x);

		if (x.compareTo(t.element) < 0) {
			t.left = remove(x, t.left);
			int l = t.left != null ? t.left.height : 0;

			if ((t.right != null) && (t.right.height - l >= 2)) {
				int rightHeight = t.right.right != null ? t.right.right.height : 0;
				int leftHeight = t.right.left != null ? t.right.left.height : 0;

				if (rightHeight >= leftHeight)
					t = rotateWithLeftChild(t);
				else
					t = doubleWithRightChild(t);
			}
		} else if (x.compareTo(t.element) > 0) {
			t.right = remove(x, t.right);
			int r = t.right != null ? t.right.height : 0;
			if ((t.left != null) && (t.left.height - r >= 2)) {
				int leftHeight = t.left.left != null ? t.left.left.height : 0;
				int rightHeight = t.left.right != null ? t.left.right.height : 0;
				if (leftHeight >= rightHeight)
					t = rotateWithRightChild(t);
				else
					t = doubleWithLeftChild(t);
			}
		}
		/*
		 * Here, we have ended up when we are node which shall be removed. Check if
		 * there is a left-hand node, if so pick out the largest element out, and move
		 * down to the root.
		 */
		else if (t.left != null) {
			t.element = findMax(t.left).element;
			remove(t.element, t.left);

			if ((t.right != null) && (t.right.height - t.left.height >= 2)) {
				int rightHeight = t.right.right != null ? t.right.right.height : 0;
				int leftHeight = t.right.left != null ? t.right.left.height : 0;

				if (rightHeight >= leftHeight)
					t = rotateWithLeftChild(t);
				else
					t = doubleWithRightChild(t);
			}
		}

		else
			t = (t.left != null) ? t.left : t.right;

		if (t != null) {
			int leftHeight = t.left != null ? t.left.height : 0;
			int rightHeight = t.right != null ? t.right.height : 0;
			t.height = Math.max(leftHeight, rightHeight) + 1;
		}
		return t;
	} // End of remove...

	/**
	 * Search for an element within the tree.
	 *
	 * @param x
	 *            Element to find
	 * @param t
	 *            Root of the tree
	 * @return True if the element is found, false otherwise
	 */
	public boolean contains(T x) {
		return contains(x, raiz);
	}

	/**
	 * Internal find method; search for an element starting at the given node.
	 *
	 * @param x
	 *            Element to find
	 * @param t
	 *            Root of the tree
	 * @return True if the element is found, false otherwise
	 */
	protected boolean contains(T x, AvlNode<T> t) {
		if (t == null) {
			return false; // The node was not found

		} else if (x.compareTo(t.element) < 0) {
			return contains(x, t.left);
		} else if (x.compareTo(t.element) > 0) {
			return contains(x, t.right);
		}

		return true; // Can only reach here if node was found
	}

	/***********************************************************************/
	// Diagnostic functions for the tree
	public boolean checkBalanceOfTree(AvlTree.AvlNode<Integer> current) {

		boolean balancedRight = true, balancedLeft = true;
		int leftHeight = 0, rightHeight = 0;

		if (current.right != null) {
			balancedRight = checkBalanceOfTree(current.right);
			rightHeight = getDepth(current.right);
		}

		if (current.left != null) {
			balancedLeft = checkBalanceOfTree(current.left);
			leftHeight = getDepth(current.left);
		}

		return balancedLeft && balancedRight && Math.abs(leftHeight - rightHeight) < 2;
	}

	public int getDepth(AvlTree.AvlNode<Integer> n) {
		int leftHeight = 0, rightHeight = 0;

		if (n.right != null)
			rightHeight = getDepth(n.right);
		if (n.left != null)
			leftHeight = getDepth(n.left);

		return Math.max(rightHeight, leftHeight) + 1;
	}

	public boolean checkOrderingOfTree(AvlTree.AvlNode<Integer> current) {
		if (current.left != null) {
			if (current.left.element.compareTo(current.element) > 0)
				return false;
			else
				return checkOrderingOfTree(current.left);
		} else if (current.right != null) {
			if (current.right.element.compareTo(current.element) < 0)
				return false;
			else
				return checkOrderingOfTree(current.right);
		} else if (current.left == null && current.right == null)
			return true;

		return true;
	}

}