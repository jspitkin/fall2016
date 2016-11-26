package assignment09;

/**
 * A representation of a matrix which holds an X,Y value and a data value inside of it.
 * 
 * @author - Lindsey Loveland (u0970740)
 * @author - Jacob Brown (u0583647)
 *
 * @param <T> Data type stored in matrix.
 */
public class Matrix<T> {

	private int xVal;
	private int yVal;
	private T data;
	
	/**
	 * Constructor which gives a X,Y position and data to a matrix.
	 * 
	 * @param x position
	 * @param y position
	 * @param data
	 */
	Matrix (int x, int y, T data) {
		
		setXVal(x);
		setYVal(y);
		
		this.setData(data);
		
	}

	/**
	 * Returns X position
	 * @return X position
	 */
	public int getXVal() {
		return xVal;
	}

	/**
	 * Set the X position of Matrix.
	 * @param xVal
	 */
	public void setXVal(int xVal) {
		this.xVal = xVal;
	}

	/**
	 * Returns Y position
	 * @return Y position
	 */
	public int getYVal() {
		return yVal;
	}

	/**
	 * Set the Y position of Matrix.
	 * @param yVal
	 */
	public void setYVal(int yVal) {
		this.yVal = yVal;
	}

	/**
	 * Get the data in the Matrix.
	 * @return data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * St the data in the Matrix.
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
}
