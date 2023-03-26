package com.abhisheksamuely.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Sudoku {

	static final int ROW = 9;
	static final int COLUMN = 9;
	static int[][] sudoku = new int[ROW][COLUMN];
	static List<List<List<Integer>>> sudokuSolver = new ArrayList<>();

	public static void main(String[] args) {

		initilize(sudoku);
		print(sudoku);
		solve(sudoku);
	}

	private static void solve(int[][] sudoku) {
		populateSolver();
		printSolver();

	}

	private static void printSolver() {
		formatAsTable(sudokuSolver);
		for (List<List<Integer>> row : sudokuSolver) {
		    System.out.format("%15s%15s%15s%n", row);
		}
		/*for (List<List<Integer>> row : sudokuSolver) {
			for (List<Integer> column : row) {
				for (Integer set : column) {
					System.out.print(set + ",");
				}
				System.out.print("\t\t");
			}
			System.out.println();
		}*/
	}

	private static void populateSolver() {
		for (int i = 0; i < ROW; i++) {
			sudokuSolver.add(new ArrayList());
		}

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				sudokuSolver.get(i).add(new ArrayList());
			}
		}

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				if (sudoku[i][j] != 0) {
					sudokuSolver.get(i).get(j).add(sudoku[i][j]);
				} else {
					sudokuSolver.get(i).get(j).addAll(addMissingNumbers(i, j));
				}
			}
		}
	}

	private static Set<Integer> getExistingValues(int row, int column) {
		Set<Integer> improbableValues = new HashSet<>();
		for (int i = 0; i < COLUMN; i++) {
			improbableValues.add(sudoku[row][i]);
		}
		for (int i = 0; i < ROW; i++) {
			improbableValues.add(sudoku[i][column]);
		}
		improbableValues.remove(0);
		return improbableValues;
	}

	private static boolean checkSolved() {
		boolean isSolved = false;
		for (List<List<Integer>> row : sudokuSolver) {
			for (List<Integer> column : row) {
				if (column.size() > 1) {
					return false;
				} else {
					isSolved = true;
				}
			}
		}
		return isSolved;
	}

	private static List<Integer> addMissingNumbers(int i, int j) {
		Set<Integer> existingNumbers = getExistingValues(i, j);
		return arrayIntersection(existingNumbers.stream().collect(Collectors.toList()));
	}

	private static List<Integer> arrayIntersection(List<Integer> presentNumbers) {
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		List<Integer> intersection = new ArrayList<>();
		for (int number : numbers) {
			if (!presentNumbers.contains(number))
				intersection.add(number);
		}
		return intersection;
	}

	private static void print(int[][] sudoku) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				System.out.print(sudoku[i][j] + "  ");
			}
			System.out.println();
		}

	}

	private static void initilize(int[][] sudoku) {
		sudoku[0][0] = 8;
		sudoku[0][1] = 7;
		sudoku[0][2] = 6;
		sudoku[0][3] = 9;
		sudoku[1][1] = 1;
		sudoku[1][5] = 6;
		sudoku[2][1] = 4;
		sudoku[2][3] = 3;
		sudoku[2][5] = 5;
		sudoku[2][6] = 8;
		sudoku[3][0] = 4;
		sudoku[3][6] = 2;
		sudoku[3][7] = 1;
		sudoku[4][1] = 9;
		sudoku[4][3] = 5;
		sudoku[5][1] = 5;
		sudoku[5][4] = 4;
		sudoku[5][6] = 3;
		sudoku[5][8] = 6;
		sudoku[6][1] = 2;
		sudoku[6][2] = 9;
		sudoku[6][8] = 8;
		sudoku[7][2] = 4;
		sudoku[7][3] = 6;
		sudoku[7][4] = 9;
		sudoku[7][6] = 1;
		sudoku[7][7] = 7;
		sudoku[7][8] = 3;
		sudoku[8][5] = 1;
		sudoku[8][8] = 4;
	}
	
	public static String formatAsTable(List<List<List<Integer>>> sudokuSolver)
	{
	    int[] maxLengths = new int[8];

	    StringBuilder formatBuilder = new StringBuilder();
	    for (int maxLength : maxLengths)
	    {
	        formatBuilder.append("%-").append(maxLength + 2).append("s");
	    }
	    String format = formatBuilder.toString();

	    StringBuilder result = new StringBuilder();
	    for (List<Integer> row : sudokuSolver.get(0))
	    {
	        result.append(String.format(format, new String[] {"*"})).append("\n");
	    }
	    return result.toString();
	}

	private static String[] getArrayOfString(List<Integer> row2) {
		String[] stringArray = new String[8];
		for(int i = 0; i < row2.size(); i++) {
			stringArray[i] = String.valueOf(row2.get(i));
		}
		return stringArray;
	}
}
