import java.util.Scanner;
import java.util.Stack;

public class Hanoi {
	public static int height; //탑의 높이
	public static Stack<Integer> leftTower = new Stack<>(), midTower = new Stack<>(), rightStackower = new Stack<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("탑 높이 입력(3 이상) :");
		height = sc.nextInt();
		int left = 1, mid = 2, right = 3; //왼쪽, 가운데, 오른쪽 기둥 번호
		
		initStack(leftTower, height);
		
		printAllTower();
		
		hanoi(height, left, mid, right);
		
		sc.close();
	}
	
	/**
	 * <p> 하노이탑 이동 </p>
	 * 탑 전체(n)를 맨 아래(1)와 나머지(n-1) 것들로 나눠 분할정복.
	 * @param n 탑의 높이(height)
	 * @param left 왼쪽 탑 번호
	 * @param mid 가운데 탑 번호
	 * @param right 오른쪽 탑 번호
	 */
	private static void hanoi(int n, int left, int mid, int right){
		if(n==1){ //맨 아래 1개 이동시키는 경우
			pushStack(left, right); 
			printAllTower();
		}
		else{ //위에서부터 n-1개 부분 이동시키는 경우
			/* 왼쪽 탑의 맨 위에 것을, 오른쪽 탑을 경유지로 삼아, 가운데 칸으로 옮김 */
			hanoi(n-1, left, right, mid);
			
			pushStack(left, right);
			printAllTower();
			
			/* 가운데 탑의 맨 위에 것을, 왼쪽 탑을 경유지로 삼아, 오른쪽 탑으로 옮김 */
			hanoi(n-1, mid, left, right);
		}
	}
	
	/**
	 * left -> right로 맨 위의 탑을 이동시키는 메서드
	 * @param left 이동시킬 탑의 번호
	 * @param right 도착할 탑의 번호
	 */
	private static void pushStack(int left, int right){
		System.out.printf("이동: %d번탑 -> %d번탑\n", left, right);
		
		//출발탑, 도착탑
		Stack<Integer> leftStack = null, rightStack = null;
		
		switch(left){
		case 1:
			leftStack = leftTower;
			break;
		case 2:
			leftStack = midTower;
			break;
		case 3:
			leftStack = rightStackower;
			break;
		}
		
		switch(right){
		case 1:
			rightStack = leftTower;
			break;
		case 2:
			rightStack = midTower;
			break;
		case 3:
			rightStack = rightStackower;
			break;
		}
		
		rightStack.push(leftStack.pop());
	}
	
	/**
	 * 처음 시작 시 왼쪽 탑에 다 채우는 메서드
	 * @param stack 왼쪽 탑
	 * @param n 탑의 높이
	 */
	private static void initStack(Stack<Integer> stack, int n){
		System.out.println("탑 생성");
		for(int i=n;i>0;i--) stack.push(i);
	}
	
	/**
	 * 왼쪽 탑 -> 가운데 탑 -> 오른쪽 탑 순서로 출력
	 */
	private static void printAllTower(){
		int i, j, size; //size = i번째 위치에 있는 탑의 길이
		StringBuilder res = new StringBuilder();
		
		for(i=height;i>0;i--){
			if(leftTower.size()>=i){
				size = leftTower.get(i-1);
				for(j=0;j<size;j++) res.append("*");
			}
			res.append("\t\t");
			
			if(midTower.size()>=i){
				size = midTower.get(i-1);
				for(j=0;j<size;j++) res.append("*");
			}
			res.append("\t\t");
			
			if(rightStackower.size()>=i){
				size = rightStackower.get(i-1);
				for(j=0;j<size;j++) res.append("*");
			}
			res.append("\t\t\n");
		}
		res.append("1번탑\t\t2번탑\t\t3번탑\n\n");
		res.append("--------------------------------------------\n");
		System.out.println(res);
	}
}