package at.reseau.graph.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.commons.collections15.Transformer;

import at.reseau.graph.model.Matrix;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphPanel extends JPanel 
{

	private static final long serialVersionUID = -2707712944901661771L;

	static Graph<Integer, Integer> graph;
	static VisualizationViewer<Integer, Integer> vv;
	static CircleLayout<Integer, Integer> layout;

	public GraphPanel(String title) 
        {
		if (title == null || title.isEmpty()) 
                {
			throw new IllegalArgumentException(
					"expected: title.length > 0, actual: " + title);
		}
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(title));

	}

	public void updateWith(Matrix matrix) 
        {
		removeAll();

		graph = new UndirectedSparseMultigraph<Integer, Integer>();

		for (int i = 1; i <= matrix.getSize(); i++) {
			graph.addVertex(i);
		}

		int i = 0;
		for (ArrayList<Integer> edges : matrix.selectEdge()) {
			graph.addEdge(i, edges);
			i++;
		}
		layout = new CircleLayout<Integer, Integer>(graph);
		layout.setSize(new Dimension(300, 260));
		vv = new VisualizationViewer<Integer, Integer>(layout);

		Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() 
            {
			@Override
			public Paint transform(Integer i) {
				return Color.YELLOW;
			}
		};

		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Integer, Integer>());
		vv.getRenderContext().setVertexDrawPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
		add(vv, BorderLayout.CENTER);
	}
}