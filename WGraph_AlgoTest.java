package ex1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class WGraph_AlgoTest {

    @Test
    void init() {
        WGraph_DS g0 = new WGraph_DS();
        g0.addNode(0);
        g0.addNode(1);
        g0.addNode(2);
        g0.addNode(0);
        weighted_graph_algorithms g1 = new WGraph_Algo();
        g1.init(g0);
        assertEquals(g0, g1.getGraph());


    }


    @Test
    void isConnected() {
        weighted_graph g0 = WGraph_DSTest.graph_creator(0, 0, 1);
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(1, 0, 1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(2, 0, 1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertFalse(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(2, 1, 1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(10, 30, 1);
        ag0.init(g0);
        boolean b = ag0.isConnected();
        assertTrue(b);

        g0 = WGraph_DSTest.graph_creator(5, 10, 1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(10, 38, 1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(2, 1, 1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());


    }

    @Test
    void shortestPathDist() {
        WGraph_DS g0 = new WGraph_DS();
        g0.addNode(0);
        g0.addNode(1);
        g0.addNode(2);
        g0.addNode(3);
        g0.addNode(4);
        g0.addNode(3);
        g0.addNode(6);
        g0.addNode(7);
        g0.addNode(8);
        g0.addNode(5);
        g0.addNode(6);
        g0.addNode(2);
        g0.connect(0, 5, 3);
        g0.connect(0, 1, 3);
        g0.connect(1, 4, 7);
        g0.connect(1, 3, 6);
        g0.connect(4, 8, 6);
        g0.connect(5, 2, 9);
        g0.connect(2, 3, 3);
        g0.connect(2, 7, 5);
        g0.connect(4, 6, 6);
        g0.connect(4, 7, 8);
        g0.connect(5, 8, 15);
        g0.connect(3, 6, 6);
        WGraph_Algo ag0 = new WGraph_Algo();
        ag0.init(g0);
        double x = 16;
        double i = ag0.shortestPathDist(0, 8);
        Boolean flag = null;
        if (i == x) flag = true;
        assertEquals(true, flag);
    }

    @Test
    void shortestPath() {
        WGraph_DS g0 = new WGraph_DS();
        g0.addNode(0);
        g0.addNode(1);
        g0.addNode(2);
        g0.addNode(3);
        g0.addNode(4);
        g0.addNode(3);
        g0.addNode(6);
        g0.addNode(7);
        g0.addNode(8);
        g0.addNode(5);
        g0.addNode(6);
        g0.addNode(2);
        g0.connect(0, 5, 3);
        g0.connect(0, 1, 3);
        g0.connect(1, 4, 7);
        g0.connect(1, 3, 6);
        g0.connect(4, 8, 6);
        g0.connect(5, 2, 9);
        g0.connect(2, 3, 3);
        g0.connect(2, 7, 5);
        g0.connect(4, 6, 6);
        g0.connect(4, 7, 8);
        g0.connect(5, 8, 15);
        g0.connect(3, 6, 6);
        WGraph_Algo ag0 = new WGraph_Algo();
        ag0.init(g0);
        ArrayList<node_info> list = (ArrayList<node_info>) ag0.shortestPath(0, 8);
        assertEquals(list.get(0), g0.getNode(0));
        assertEquals(list.get(1), g0.getNode(1));
        assertEquals(list.get(2), g0.getNode(4));
        assertEquals(list.get(3), g0.getNode(8));


    }

    @Test
    void save_load() {
        WGraph_DS g0 = new WGraph_DS();
        g0.addNode(0);
        g0.addNode(1);
        g0.addNode(2);
        g0.addNode(3);
        g0.addNode(4);
        g0.addNode(3);
        g0.addNode(6);
        g0.addNode(7);
        g0.addNode(8);
        g0.addNode(5);
        g0.addNode(6);
        g0.addNode(2);
        g0.connect(0, 5, 3);
        g0.connect(0, 1, 3);
        g0.connect(1, 4, 7);
        g0.connect(1, 3, 6);
        g0.connect(4, 8, 6);
        g0.connect(5, 2, 9);
        g0.connect(2, 3, 3);
        g0.connect(2, 7, 5);
        g0.connect(4, 6, 6);
        g0.connect(4, 7, 8);
        g0.connect(5, 8, 6);
        g0.connect(3, 6, 6);
        WGraph_Algo ag0 = new WGraph_Algo();
        ag0.init(g0);
        ag0.save("st");
        WGraph_Algo k = new WGraph_Algo();
        k.load("st");
        assertEquals(ag0.Graph.edgeSize(), k.Graph.edgeSize());
        assertEquals(ag0.Graph.nodeSize(), k.Graph.nodeSize());

    }

}