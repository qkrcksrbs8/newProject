package codingTest.nodeinfo;
import java.util.*;

public class Solution {
	
	/**
	 * ID x, y 좌표를 위한 클레스
	 */
	class Node{
		
		/**
		 * Node 클레스를 id, x, y 좌표로 구현하기 위해서 만들어진 생성자 클레스
		 * @param id
		 * @param x
		 * @param y
		 */
		Node(int id, int x, int y){
			
			this.id = id;
			this.x = x;
			this.y = y;
		
		};
		
		int id;
		int x, y;
		
		Node left;
		Node right;
	
	};
	
	int idx;
	
	List<Node> Nodes = new ArrayList<Node>();//Node클레스를 이용해서 ArrayList 형태로 생성
	
	Comparator<Node> Comp = new Comparator<Node>() {
		
		public int compare(Node a, Node b) {
			
			//----------------------------------------
			//y값이 같으면 x가 작은 값이 앞에 옴
			//y값이 같지 않으면 x값이 큰게 앞에 옴
			//----------------------------------------
			if(a.y == b.y) {
				
				return a.x - b.x;
				
			};//if
			
			return b.y - a.y;
			
		};//compare
		
	};
	
	/**
	 * 새로 추가하고자 하는 노드
	 * @param parent
	 * @param child
	 */
	void addNode(Node parent, Node child) {
		
		//----------------------------------------
		//
		//----------------------------------------
		if(child.x < parent.x) {
			
			if(parent.left == null) {
				
				parent.left = child;
				
			} else {
				
				addNode(parent.left, child);
				
			};//else
			
		} else {
			
			if(parent.right == null) {
				
				parent.right = child;
				
			} else {
				
				addNode(parent.right, child);
				
			};//else
			
		};//else
		
	};//addNode
	
	void preorder(int[][] answer, Node node) {
		
		if(node == null) return;
		
		answer[0][idx++] = node.id;
		
		preorder(answer, node.left);
		preorder(answer, node.right);
		
	};//preorder
	
	void postorder(int[][] answer, Node node) {
		
		if(node == null) return;
		
		postorder(answer, node.left);
		postorder(answer, node.right);
		answer[1][idx++] = node.id;
		
	};//postorder
	
	/**
	 * 초기 노드생성 메서드
	 * @param nodeinfo
	 * @return
	 */
	public int[][] solution(int[][] nodeinfo){
		
		int size = nodeinfo.length;//전체 노드의 갯수
		
		//----------------------------------------		
		//사이즈 갯수만큼 NodeList 생성
		//----------------------------------------
		for(int i = 0; i < size; ++i) {
			
			Nodes.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));//정렬되지 않은 상태로 리스트 생성
			
		};//for
		
		Nodes.sort(Comp);// Node 조건 정렬
		
		Node root = Nodes.get(0);//NodeList의 첫 번째
		
		for(int i = 1; i < size; ++i) {//node 2진 트리에 하나씩 추가
			
			addNode(root, Nodes.get(i));//새로 추가하고자 하는 노드
			
		};//for
		
		int[][] answer = new int[2][size];
		preorder(answer, root);
		idx = 0;
		postorder(answer, root);
		
		return answer;
	
	};
	
}
