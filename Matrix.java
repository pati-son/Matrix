import java.lang.Math;
public class Matrix{
	
	public static void main(String [] args){

		Matrix res = new Matrix(3);

		res.setElement(0,0,-1);
		res.setElement(0,1,1);
		res.setElement(0,2,1);

		res.setElement(1,0,2);
		res.setElement(1,1,1);
		res.setElement(1,2,-1);

		res.setElement(2,0,1);
		res.setElement(2,1,3);
		res.setElement(2,2,-1);

		System.out.println(res);
		int a = res.determinant();
		System.out.println(a);

	}

	private int curSize;
	private int[][] matrix;
	

	public Matrix(int size){
	
		curSize = size;
		matrix = new int[size][size];

		for(int i = 0; i < curSize; i++)
			for(int j = 0; j < curSize; j++)
	
			if(i ==	j) matrix[i][j] = 1;
			else matrix[i][j] = 0;
	}

	public Matrix sum(Matrix m){

		for(int i = 0; i < curSize; i++)
			for(int j = 0; j < curSize; j++){
				m.matrix[i][j] += m.matrix[i][j];
			}

		return m;
	}

	public Matrix product(Matrix m){

		Matrix matrix = new Matrix(curSize);
		for(int i = 0; i < curSize; i++)
			for(int j = 0; j < curSize; j++)
				matrix.setElement(i,j,0);
		
		for(int i = 0; i < curSize; i++){
			for(int j = 0; j < curSize; j++){
				for(int k = 0; k < curSize; k++){
					matrix.matrix[i][j] += this.matrix[i][k] * m.matrix[k][j];
				}
			}
		}
		return matrix;	

	}
	
	public void setElement(int row, int column, int value){

		matrix[row][column] = value;

	}

	public int getElement(int row, int column){

		return matrix[row][column];

	}

	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < curSize; i++){
			for(int j = 0; j < curSize; j++){
				sb.append(matrix[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		String text = sb.toString();

		return text;
	}
	
	public int determinant () {
		int det = 0;
		int det1 = 0;
		int [][]minor = new int [2][2];
		det1 = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[2][1] * matrix[1][2])
		     + matrix[0][1] * (matrix[1][2] * matrix[2][0] - matrix[2][2] * matrix[1][0]) 
		     + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[2][0] * matrix[1][1]); 
		for(int i = 0; i < 1; i++){	//будем вычеркивать первую строку и каждый столбец поочереди
			for(int j = 0; j < 3; j++){	//j-ый стобец 
				if(j == 0){  // первый столбик
					for(int k = 0;  k < 2; k++){
						for(int l = 0; l < 2; l++){
							minor[k][l] = matrix[k + 1][l + 1];
						}
					}
					det = det + matrix[0][0] * (minor[0][0] * minor[1][1] - minor[0][1] * minor[1][0]);
				}
				else{
					if(j == 1){	//[0][1] - ценnральныый столбец  (копирую массив)
						minor[0][0] = matrix[1][0];		//	|- M - |
						minor[1][1] = matrix[2][2];		//	|. - . |
						minor[0][1] = matrix[1][2];		//  	|. - . |
						minor[1][0] = matrix[2][0];
						det = det - matrix[0][1] * (minor[0][0] * minor[1][1] - minor[0][1] * minor[1][0]);
					}
					else{ //последний столбик
						for(int k = 0; k < 2; k++){
							for(int l = 0; l < 2; l++){
								minor[k][l] = matrix[k + 1][l];
							}
						}
						det = det + matrix[0][2] * (minor[0][0] * minor[1][1] - minor[0][1] * minor[1][0]);
					}
				}
			}
		}
		System.out.println(det1);
		return det;
	}

	}
// метод, который вычисляет определитель 3-го порядка матрицы	