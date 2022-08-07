package desafio2;

public class Constellations {
	private int rows, cols;
	private int[] elements;

	Constellations(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.elements = new int[rows * cols];
		for (int i = 0; i < rows; i++) {
			for (int j = i * cols; j < (i * cols) + cols; j++) {
				if (j - i * cols < i) { // colunas menores do que a linha
					this.elements[j] = this.elements[i + ((j - i * cols) * cols)];

				} else if (j - i * cols == i) { // coluna igual a linha observada
					this.elements[j] = 0;

				} else { // colunas maiores do que a linha
					this.elements[j] = (int) Math.floor(Math.random() * 2); //atribui aleatóriamente 0 ou 1 para o elemento da posição j
				}

			}

		}
	}

	// Getters
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int[] getElements() {
		return elements;
	}

	// Setters
	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setElements(int[] elements) {
		this.elements = elements;
	}
}
