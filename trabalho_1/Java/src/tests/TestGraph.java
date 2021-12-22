package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import main.analysis.GraphAnalysis;
import main.classes.AdjacentList;
import main.classes.AdjacentMatrix;
import java.io.FileWriter;
import java.io.IOException;
public class TestGraph {
    public static void main(String[] args) {
        testGraph();
    }

    @Test
    static void testGraph(){
        caseStudy_6();
    }

    static void caseStudy_1() {
        Scanner consoleScanner = new Scanner(System.in);
        for (int i = 1; i <=3; i++) {
            readAdjMatrix(i);
        }

        for (int i = 1; i <=3; i++) {
            readAdjList(i);
        }
    }

    static void caseStudy_2() {
        String outputFilePath = GraphAnalysis.CreateOutputFile("caseStudy_2_list");
        BFS1000List(outputFilePath);
        outputFilePath = GraphAnalysis.CreateOutputFile("caseStudy_2_Matrix");
        BFS1000Matrix(outputFilePath);
    }

    static void caseStudy_3() {
        String outputFilePath = GraphAnalysis.CreateOutputFile("caseStudy_3_list");
        DFS1000List(outputFilePath);
        outputFilePath = GraphAnalysis.CreateOutputFile("caseStudy_3_Matrix");
        DFS1000Matrix(outputFilePath);
    }

    static void caseStudy_4() {
        try {
            String outputFilePath = GraphAnalysis.CreateOutputFile("caseStudy_4");
            FileWriter fileWriter = new FileWriter(outputFilePath);
            for (int graphNumber = 1; graphNumber <= 3; graphNumber++) {
                String inputFile = "grafo_" + graphNumber + ".txt";
                AdjacentList graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
               
                for(int searchRoot = 1; searchRoot <= 3; searchRoot++) {
                    HashMap<String, int[]> BFSListData = GraphAnalysis.BFS(graphAdjList, null, searchRoot);
                    int[] nodeChilds = {10, 20, 30};
    
                    for(int node : nodeChilds) {
                        int parent = BFSListData.get("parents")[node];
                        fileWriter.write("Pai do nó " + node + " do grafo " + graphNumber + " na BFS iniciada pelo nó " + searchRoot + ": " + parent);
                        if(parent == 0) {
                            fileWriter.write(" (Vértice sem pai - parte de outra componente conexa)\n");
                        } else {
                            fileWriter.write("\n");
                        }
                        
                    }
                }
            }
            fileWriter.write("\n");
            for (int graphNumber = 1; graphNumber <= 3; graphNumber++) {
                String inputFile = "grafo_" + graphNumber + ".txt";
                AdjacentList graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
                  
                for(int searchRoot = 1; searchRoot <= 3; searchRoot++) {
                    HashMap<String, int[]> DFSListData = GraphAnalysis.DFS(graphAdjList, null, searchRoot);
                    int[] nodeChilds = {10, 20, 30};
    
                    for(int node : nodeChilds) {
                        int parent = DFSListData.get("parents")[node];
                        fileWriter.write("Pai do nó " + node + " do grafo " + graphNumber + " na DFS iniciada pelo nó " + searchRoot + ": " + parent);
                        if(parent == 0) {
                            fileWriter.write(" (Vértice sem pai - parte de outra componente conexa)\n");
                        } else {
                            fileWriter.write("\n");
                        }
                        
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro.");
            e.printStackTrace();
        }

        
    }   

    static void caseStudy_5() {
        try {
            String outputFilePath = GraphAnalysis.CreateOutputFile("caseStudy_5");
            FileWriter fileWriter = new FileWriter(outputFilePath);
            fileWriter.write("Obs: Distancias marcadas como 0 indicam que os vértices pertencem a diferentes componentes conexas do grafo.\n\n");
            for (int graphNumber = 1; graphNumber <= 3; graphNumber++) {
                String inputFile = "grafo_" + graphNumber + ".txt";
                AdjacentList graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
                HashMap<String, int[]> BFSListData1 = GraphAnalysis.BFS(graphAdjList, null, 10);
                HashMap<String, int[]> BFSListData2 = GraphAnalysis.BFS(graphAdjList, null, 20);
                
                fileWriter.write("Distância entre os vértices 10 e 20 no grafo: "+ graphNumber + ": " + BFSListData1.get("levels")[20]+ "\n");
                fileWriter.write("Distância entre os vértices 10 e 30 no grafo: "+ graphNumber + ": " + BFSListData1.get("levels")[30]+ "\n");
                fileWriter.write("Distância entre os vértices 20 e 30 no grafo: "+ graphNumber + ": " + BFSListData2.get("levels")[30]+ "\n");
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro.");
            e.printStackTrace();
        }
    }

    static void caseStudy_6() {
        for (int i = 1; i <= 3; i++) {
            String inputFileName = "grafo_" + i + ".txt";
            AdjacentList graph = new AdjacentList(GraphAnalysis.SetGraphData(inputFileName));

            GraphAnalysis.SaveConnectedComponentInfo(graph, null, "caseStudy_6_"+ i);
        }
    }

    static AdjacentList readAdjList(int number) {
        
        String inputFile = "grafo_" + number + ".txt";
        AdjacentList graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
        Runtime.getRuntime().gc();
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return graphAdjList;
    }

    static AdjacentMatrix readAdjMatrix(int number) {
        String inputFile = "grafo_" + number + ".txt";
        AdjacentMatrix graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
        Runtime.getRuntime().gc();
        System.out.println("Pausando");
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return graphAdjMatrix;
    }

    static void BFS1000List(String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);
            String inputFile = "grafo_1.txt";
            AdjacentList graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
            
            long pastTime = System.currentTimeMillis();
    
            for (int j = 1; j <= 100; j++){
                for (int k = 1; k <= 10; k++) {
                    GraphAnalysis.BFS(graphAdjList, null, j);
                }
            }
    
            long timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 1: " + timeElapsed + "ms\n");
    
            inputFile = "grafo_2.txt";
            graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();
    
            for (int i = 1; i <= 968; i++){
                GraphAnalysis.BFS(graphAdjList, null, i);
            }
    
            for (int i = 1; i <= 32; i++){
                GraphAnalysis.BFS(graphAdjList, null, i);
            }
            
            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 2: " + timeElapsed + "ms\n");
    
            
            inputFile = "grafo_3.txt";
            graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();
    
            for (int i = 1; i <= 1000; i++){
                GraphAnalysis.BFS(graphAdjList, null, i);
            }
    
            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 3: " + timeElapsed + "ms\n");
    
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro.");
            e.printStackTrace();
        }
    }

    static void BFS1000Matrix(String outputFilePath) {

        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);
            String inputFile = "grafo_1.txt";
            AdjacentMatrix graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
            
            long pastTime = System.currentTimeMillis();
    
            for (int j = 1; j <= 100; j++){
                for (int k = 1; k <= 10; k++) {
                    GraphAnalysis.BFS(null, graphAdjMatrix, j);
                }
            }
    
            long timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 1: " + timeElapsed + "ms\n");
    
            inputFile = "grafo_2.txt";
            graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();
    
            for (int i = 1; i <= 968; i++){
                GraphAnalysis.BFS(null, graphAdjMatrix, i);
            }
    
            for (int i = 1; i <= 32; i++){
                GraphAnalysis.BFS(null, graphAdjMatrix, i);
            }
            
            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 2: " + timeElapsed + "ms\n");
    
            
            inputFile = "grafo_3.txt";
            graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();
    
            for (int i = 1; i <= 1000; i++){
                GraphAnalysis.BFS(null, graphAdjMatrix, i);
            }
    
            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 3: " + timeElapsed + "ms\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro.");
            e.printStackTrace();
        }

    }

    static void DFS1000List(String outputFilePath) {

        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);
            String inputFile = "grafo_1.txt";
            AdjacentList graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
            
            long pastTime = System.currentTimeMillis();

            for (int j = 1; j <= 100; j++){
                for (int k = 1; k <= 10; k++) {
                    GraphAnalysis.DFS(graphAdjList, null, j);
                }
            }

            long timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 1: " + timeElapsed + "ms\n");

            inputFile = "grafo_2.txt";
            graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();

            for (int i = 1; i <= 968; i++){
                GraphAnalysis.DFS(graphAdjList, null, i);
            }

            for (int i = 1; i <= 32; i++){
                GraphAnalysis.DFS(graphAdjList, null, i);
            }
            
            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 2: " + timeElapsed + "ms\n");

            
            inputFile = "grafo_3.txt";
            graphAdjList = new AdjacentList(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();

            for (int i = 1; i <= 1000; i++){
                GraphAnalysis.DFS(graphAdjList, null, i);
            }

            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 3: " + timeElapsed + "ms\n");

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro.");
            e.printStackTrace();
        }

    }

    static void DFS1000Matrix(String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);
            String inputFile = "grafo_1.txt";
            AdjacentMatrix graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
            
            long pastTime = System.currentTimeMillis();

            for (int j = 1; j <= 100; j++){
                for (int k = 1; k <= 10; k++) {
                    GraphAnalysis.DFS(null, graphAdjMatrix, j);
                }
            }

            long timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 1: " + timeElapsed + "ms\n");

            inputFile = "grafo_2.txt";
            graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();

            for (int i = 1; i <= 968; i++){
                GraphAnalysis.DFS(null, graphAdjMatrix, i);
            }

            for (int i = 1; i <= 32; i++){
                GraphAnalysis.DFS(null, graphAdjMatrix, i);
            }
            
            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 2: " + timeElapsed + "ms\n");

            
            inputFile = "grafo_3.txt";
            graphAdjMatrix = new AdjacentMatrix(GraphAnalysis.SetGraphData(inputFile));
            Runtime.getRuntime().gc();
            pastTime = System.currentTimeMillis();

            for (int i = 1; i <= 1000; i++){
                GraphAnalysis.DFS(null, graphAdjMatrix, i);
            }

            timeElapsed = System.currentTimeMillis() - pastTime;
            fileWriter.write("1000 buscas no grafo 3: " + timeElapsed + "ms\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro.");
            e.printStackTrace();
        }

    }
}
