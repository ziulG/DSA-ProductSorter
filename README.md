
# DSA-ProductSorter

## 🇧🇷 Português

### Descrição
Uma ferramenta baseada em Java para ordenar dados de produtos utilizando múltiplos algoritmos de ordenação como ShellSort, QuickSort, HeapSort e Insertion Sort.

### Estrutura do Projeto
O projeto está organizado nos seguintes pacotes:
- `br.com.productSorter.algorithms`: Contém implementações dos algoritmos de ordenação
- `br.com.productSorter.datastructures`: Estruturas de dados como lista encadeada
- `br.com.productSorter.model`: Definição da classe Product
- `br.com.productSorter.util`: Utilitários como gerador de produtos

### Algoritmos Implementados
- **ShellSort Modificado**: Ordena produtos por NOME utilizando SelectionSort para sublistas h-espaçadas (h>1) e InsertionSort para h=1
- **QuickSort**: Ordena produtos por PREÇO usando mediana de três elementos e particionamento com cursor único (Lomuto)
- **HeapSort Iterativo**: Ordena produtos por DATA DE VALIDADE com implementação não recursiva do HeapSort
- **InsertionSort em Lista Encadeada**: Ordena produtos por FABRICANTE utilizando lista encadeada

### Como Executar
1. Clone o repositório
2. Compile o projeto: `javac src/main/java/br/com/productSorter/MainApp.java`
3. Execute a aplicação: `java src.main.java.br.com.productSorter.MainApp`

Para gerar arquivos de teste:
```
java src.main.java.br.com.productSorter.util.ProductGenerator
```

## 🇬🇧 English

### Description
A Java-based tool to sort product data using multiple sorting algorithms like ShellSort, QuickSort, HeapSort, and Insertion Sort.

### Project Structure
The project is organized in the following packages:
- `br.com.productSorter.algorithms`: Contains sorting algorithm implementations
- `br.com.productSorter.datastructures`: Data structures like linked list
- `br.com.productSorter.model`: Product class definition
- `br.com.productSorter.util`: Utilities like product generator

### Implemented Algorithms
- **Modified ShellSort**: Sorts products by NAME using SelectionSort for h-spaced sublists (h>1) and InsertionSort for h=1
- **QuickSort**: Sorts products by PRICE using median of three elements and single cursor partitioning (Lomuto)
- **Iterative HeapSort**: Sorts products by EXPIRY DATE with non-recursive implementation of HeapSort
- **Insertion Sort on Linked List**: Sorts products by MANUFACTURER using linked list

### How to Run
1. Clone the repository
2. Compile the project: `javac src/main/java/br/com/productSorter/MainApp.java`
3. Run the application: `java src.main.java.br.com.productSorter.MainApp`

To generate test files:
```
java src.main.java.br.com.productSorter.util.ProductGenerator
```
