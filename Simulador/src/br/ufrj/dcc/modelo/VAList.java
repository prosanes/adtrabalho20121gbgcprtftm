package br.ufrj.dcc.modelo;

import java.util.ArrayList;


public class VAList<Type>
{
	ArrayList<ArrayList<Type>> array;
 
	public VAList()
	{
		array = new ArrayList<ArrayList<Type>>();
	}
	
	 
	/**
	 * ensures a minimum capacity of num rows. Note that this does not guarantee
	 * that there are that many rows.
	 * 
	 * @param num
	 */
	public void ensureCapacity(int num)
	{
		array.ensureCapacity(num);
	}
 
	/**
	 * Ensures that the given row has at least the given capacity. Note that
	 * this method will also ensure that getNumRows() >= row
	 * 
	 * @param row
	 * @param num
	 */
	public void ensureCapacity(int row, int num)
	{
		ensureCapacity(row);
		while (row < getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).ensureCapacity(num);
	}
 
	/**
	 * Adds an item at the end of the specified row. This will guarantee that at least row rows exist.
	 */
	public void Add(Type data, int row)
	{
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).add(data);
	}
	
	public void Add(ArrayList<Type> listData, int row)
	{
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).addAll(listData);
	}
 
	public Type get(int row, int col)
	{
		return array.get(row).get(col);
	}
 
	public void set(int row, int col, Type data)
	{
		array.get(row).set(col,data);
	}
 
	public void remove(int row, int col)
	{
		array.get(row).remove(col);
	}
 
	public boolean contains(Type data)
	{
		for (int i = 0; i < array.size(); i++)
		{
			if (array.get(i).contains(data))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Type> getArrayListRow(int row)
	{
		return array.get(row);
	}
 
	public int getNumRows()
	{
		return array.size();
	}
 
	public int getNumCols(int row)
	{
		return array.get(row).size();
	}
}