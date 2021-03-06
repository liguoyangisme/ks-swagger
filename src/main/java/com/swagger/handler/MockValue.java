package com.swagger.handler;

import java.util.Random;

/**
 * 生成模拟数据
 * @author liguoyang
 * @create 2018-06-14 下午5:13
 **/
public class MockValue {

    /**
     * 词库
     */
    private static String[] mockString = new String[]{
            "Data","Structures","基本数据结构","Dictionaries","字典","Priority","Queues","堆","Graph","Data","Structures","图","Set","Data","Structures","集合","Kd-Trees","线段树","Numerical","Problems","数值问题","Solving","Linear","Equations","线性方程组","Bandwidth","Reduction","带宽压缩","Matrix","Multiplication","矩阵乘法","Determinants","and","Permanents","行列式","Constrained","and","UnconstrainedOptimization","最值问题","Linear","Programming","线性规划","Random","Number","Generation","随机数生成","Factoring","and","Primality","Testing","因子分解/质数判定","Arbitrary","Precision","Arithmetic","高精度计算","Knapsack","Problem","背包问题","Discrete","Fourier","Transform","离散Fourier变换","Combinatorial","Problems","组合问题","Sorting","排序","Searching","查找","Median","and","Selection","中位数","Generating","Permutations","排列生成","Generating","Subsets","子集生成","Generating","Partitions","划分生成","Generating","Graphs","图的生成","Calendrical","Calculations","日期","Job","Scheduling","工程安排","Satisfiability","可满足性","Graph","Problems","--","polynomial","图论-多项式算法","Connected","Components","连通分支","Topological","Sorting","拓扑排序","Minimum","Spanning","Tree","最小生成树","Shortest","Path","最短路径","Transitive","Closure","and","Reduction","传递闭包","Matching","匹配","Eulerian","Cycle","/","Chinese","PostmanEuler回路/中国邮路","Edge","and","Vertex","Connectivity","割边/割点","Network","Flow","网络流","Drawing","Graphs","Nicely","图的描绘","Drawing","Trees","树的描绘","Planarity","Detection","and","Embedding","平面性检测和嵌入","Graph","Problems","--","hard","图论-NP问题","Clique","最大团","Independent","Set","独立集","Vertex","Cover","点覆盖","Traveling","Salesman","Problem","旅行商问题","Hamiltonian","Cycle","Hamilton回路","Graph","Partition","图的划分","Vertex","Coloring","点染色","Edge","Coloring","边染色","Graph","Isomorphism","同构","Steiner","Tree","Steiner树","Feedback","Edge/Vertex","Set","最大无环子图","Computational","Geometry","计算几何","Convex","Hull","凸包","Triangulation","三角剖分","Voronoi","Diagrams","Voronoi图","Nearest","Neighbor","Search","最近点对查询","Range","Search","范围查询","Point","Location","位置查询","Intersection","Detection","碰撞测试","Bin","Packing","装箱问题","Medial-Axis","Transformation","中轴变换","Polygon","Partitioning","多边形分割","Simplifying","Polygons","多边形化简","Shape","Similarity","相似多边形","Motion","Planning","运动规划","Maintaining","Line","Arrangements","平面分割","Minkowski","Sum","Minkowski和","Set","and","String","Problems","集合与串的问题","Set","Cover","集合覆盖","Set","Packing","集合配置","String","Matching","模式匹配","Approximate","String","Matching","模糊匹配","Text","Compression","压缩","Cryptography","密码","Finite","State","Machine","Minimization","有穷自动机简化","Longest","Common","Substring","最长公共子串","Shortest","Common","Superstring","最短公共父串","DP——Dynamic","Programming——动态规划","recursion","递归","编程词汇","A2A","integration","A2A整合","abstract","抽象的","software","软件","source","code","源代码"
    };

    /**
     * 随机数
     */
    private static Random random = new Random();


    /**
     * 生成模拟字符串
     */
    public static String mockString(){
        return mockString[random.nextInt(mockString.length-1)];
    }

    /**
     * 生成模拟数字
     */
    public static Integer mockInt(){
        return random.nextInt(1000);
    }

    /**
     * 生成模拟小数
     */
    public static Float mockFloat(){
        return random.nextInt(1000) + random.nextFloat();
    }
}
