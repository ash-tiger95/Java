package com.theory.bst;

public class BinarySearchTree {

	public class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.setData(data);
			setLeft(null);
			setRight(null);
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
	}

	public Node root; // BinarySearchTree의 루트 노드

	public BinarySearchTree() { // BinarySearchTree 생성자
		this.root = null;
	}

	/* 탐색 연산 */
	public boolean find(int key) {
		Node currentNode = root;
		while (currentNode != null) { // 현재 노드와 찾는 값이 같으면
			if (currentNode.getData() == key) {
				return true;
			} else if (currentNode.getData() > key) { // 찾는 값이 현재 노드보다 작으면
				currentNode = currentNode.getLeft();
			} else {// 찾는 값이 현재 노드보다 크면
				currentNode = currentNode.getRight();
			}
		}
		return false;
	} 
	/* 삽입 연산 */
	public void insert(int data) {
		Node newNode = new Node(data); // 왼쪽, 오른쪽 자식 노드가 null이며 data의 값이 저장된 새 노드 생성
		
		if (root == null) {// 루트 노드가 없을때, 즉 트리가 비어있는 상태일 때,
			root = newNode;
			return;
		}
		
		Node currentNode = root;
		Node parentNode = null;
		
		while (true) {
			parentNode = currentNode;
			if (data < currentNode.getData()) { // 해당 노드보다 클 때.
				currentNode = currentNode.getLeft();
				if (currentNode == null) {
					parentNode.setLeft(newNode);
					return;
				}
			} else { // 해당 노드보다 작을 때.
				currentNode = currentNode.getRight();
				if (currentNode == null) {
					parentNode.setRight(newNode);
					return;
				}
			}
		}
	} 
	/* 삭제 연산 */
	public boolean delete(int data) {
		Node parentNode = root;
		Node currentNode = root;
		boolean isLeftChild = false;
		while (currentNode.getData() != data) {
			parentNode = currentNode;
			if (currentNode.getData() > data) {
				isLeftChild = true;
				currentNode = currentNode.getLeft();
			} else {
				isLeftChild = false;
				currentNode = currentNode.getRight();
			}
			if (currentNode == null) {
				return false;
			}
		}
		if (currentNode.getLeft() == null && currentNode.getRight() == null) { // 1. 자식 노드가 없는 경우
			if (currentNode == root) {
				root = null; // 해당 노드가 root node일 경우
			}
			if (isLeftChild) {
				parentNode.setLeft(null); // 삭제할 노드가 부모 노드의 왼쪽 자식일 경우
			} else {
				parentNode.setRight(null); // 삭제할 노드가 부모 노드의 오른쪽 자식일 경우
			}
		} else if (currentNode.getRight() == null) { // 2-1. 자식 노드가 하나인 경우(오른쪽 자식이 없을 때)
			parentNode.setLeft(currentNode.getLeft());
		} else if (currentNode.getLeft() == null) { // 2-2. 자식 노드가 하나인 경우(왼쪽 자식이 없을 떄)
			parentNode.setRight(currentNode.getRight());
		} else { // 3. 자식이 둘인 경우
			Node minimum = getMinumun(currentNode);
			if (currentNode == root) {
				root = minimum;
			} else if (isLeftChild) {
				parentNode.setLeft(minimum);
			} else {
				parentNode.setLeft(currentNode.getLeft());
			}
			minimum.setLeft(currentNode.getLeft());
		}
		return false;
	}

	Node getMinumun(Node deleteNode) {
		Node minimum = null;
		Node minimumParent = null;
		Node currentNode = deleteNode.getRight();
		while (currentNode != null) {
			minimumParent = minimum;
			minimum = minimumParent;
			currentNode = currentNode.getLeft();
		}
		if (minimum != deleteNode.getRight()) {
			minimumParent.setLeft(minimum.getRight());
			minimum.setRight(deleteNode.getRight());
		}
		return minimum;
	}

	public void display(Node root) {
		if (root != null) {
			display(root.getLeft());
			System.out.print(" " + root.getData());
			display(root.getRight());
		}
	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();

		bst.insert(3);
		bst.insert(8);
		bst.insert(1);
		bst.insert(4);
		bst.insert(6);
		bst.insert(2);
		bst.insert(10);
		bst.insert(9);
		bst.insert(20);
		bst.insert(25);
		bst.insert(15);
		bst.insert(16);
		System.out.println("트리 삽입 결과");
		bst.display(bst.root);
	}

}
