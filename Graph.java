import java.util. * ;

public class Graph {
  public static class Edge {
    int nbr;
    int wt;

    Edge(int nbr, int wt) {
      this.nbr = nbr;
      this.wt = wt;
    }
  }

  public static class solPair {
    int lightW = (int) 1e7;
    int heavyW = 0;
    int ceil = (int) 1e7;
    int floor = 0;
  }

  public static int N = 7;;
  public static ArrayList < Edge > [] graph = new ArrayList[N];

  public static void addEdge(int u, int v, int wt) {
    graph[u].add(new Edge(v, wt));
    graph[v].add(new Edge(u, wt));
  }

  public static int findEdge(int u, int v) {
    for (int i = 0; i < graph[u].size(); i++) {
      Edge e = graph[u].get(i);
      if (e.nbr == v) {
        return i;
      }
    }

    return - 1;
  }

  public static void removeEdge(int v, int u) {
    int i1 = findEdge(u, v);
    int i2 = findEdge(v, u);

    graph[u].remove(i1);
    graph[v].remove(i2);
  }

  public static void removeVtx(int vtx) {
    while (graph[vtx].size() != 0) {
      removeEdge(vtx, graph[vtx].get(0).nbr);
    }
  }

  //leetcode 130 
  public void solve(char[][] board) {
    if (board.length == 0 || board[0].length == 0) return;
    if (board.length < 2 || board[0].length < 2) return;
    int m = board.length,
    n = board[0].length;
    //Any 'O' connected to a boundary can't be turned to 'X', so ...
    //Start from first and last column, turn 'O' to '*'.
    for (int i = 0; i < m; i++) {
      if (board[i][0] == 'O') boundaryDFS(board, i, 0);
      if (board[i][n - 1] == 'O') boundaryDFS(board, i, n - 1);
    }
    //Start from first and last row, turn '0' to '*'
    for (int j = 0; j < n; j++) {
      if (board[0][j] == 'O') boundaryDFS(board, 0, j);
      if (board[m - 1][j] == 'O') boundaryDFS(board, m - 1, j);
    }
    //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') board[i][j] = 'X';
        else if (board[i][j] == '*') board[i][j] = 'O';
      }
    }
  }
  //Use DFS algo to turn internal however boundary-connected 'O' to '*';
  private void boundaryDFS(char[][] board, int i, int j) {
    if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) return;
    if (board[i][j] == 'O') board[i][j] = '*';
    if (i > 1 && board[i - 1][j] == 'O') boundaryDFS(board, i - 1, j);
    if (i < board.length - 2 && board[i + 1][j] == 'O') boundaryDFS(board, i + 1, j);
    if (j > 1 && board[i][j - 1] == 'O') boundaryDFS(board, i, j - 1);
    if (j < board[i].length - 2 && board[i][j + 1] == 'O') boundaryDFS(board, i, j + 1);
  }

  //leetcode 1267
  public int countServers(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int numRows = grid.length;
    int numCols = grid[0].length;
    int rowCount[] = new int[numRows];
    int colCount[] = new int[numCols];
    int totalServers = 0;
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
            if (grid[row][col] == 1) {
                rowCount[row]++;
                colCount[col]++;
                totalServers++;
            }
        }
    }
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
            if (grid[row][col] == 1) {
                if (rowCount[row] == 1 && colCount[col] == 1) {
                    totalServers--;
                }
            }
        }
    }
    return totalServers;
}

//LEETCODE 200
public int numIslands(char[][] grid) {
    int count = 0;
    n = grid.length;
    if (n == 0) return 0;
    m = grid[0].length;
    for (int i = 0; i < n; i++){
        for (int j = 0; j < m; j++)
            if (grid[i][j] == '1') {
                DFSMarking(grid, i, j);
                ++count;
            }
    }    
    return count;
}

private void DFSMarking(char[][] grid, int i, int j) {
    if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
    grid[i][j] = '0';
    DFSMarking(grid, i + 1, j);
    DFSMarking(grid, i - 1, j);
    DFSMarking(grid, i, j + 1);
    DFSMarking(grid, i, j - 1);
}

//LEETCODE 695
 public int maxAreaOfIsland(int[][] grid) {
        int max_area = 0;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] == 1)max_area = Math.max(max_area, AreaOfIsland(grid, i, j));
        return max_area;
    }
    
    public int AreaOfIsland(int[][] grid, int i, int j){
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;
            return 1 + AreaOfIsland(grid, i+1, j) + AreaOfIsland(grid, i-1, j) + AreaOfIsland(grid, i, j-1) + AreaOfIsland(grid, i, j+1);
        }
        return 0;
    }

  public static void display(ArrayList < Edge > [] graph) {
    for (int i = 0; i < graph.length; i++) {
      System.out.print(i + "--> ");
      for (int j = 0; j < graph[i].size(); j++) {
        Edge e = graph[i].get(j);
        System.out.print(e.nbr + "@" + e.wt + ", ");
      }

      System.out.println();
    }
  }

//LEETCODE 463
public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }

  public static void main(String[] args) {
    for (int i = 0; i < graph.length; i++) {
      graph[i] = new ArrayList < >();
    }

    addEdge(0, 1, 20);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 3);
    addEdge(5, 6, 8);
    removeVtx(3);
    display(graph);
  }
}