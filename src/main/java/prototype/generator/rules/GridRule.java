package prototype.generator.rules;

import prototype.Config;
import prototype.generator.Density;
import prototype.generator.ProposedVertex;
import prototype.generator.Utils;
import prototype.graph.Vertex;

import java.util.List;

public class GridRule
{
	public void suggestVertices(ProposedVertex src, Vertex srcNewlyAdded, List<ProposedVertex> proposed)
	{
		double density = Density.getValue(src.getX(), src.getY());

		double angleOffset = density / Config.getDouble(Config.Key.ANGLE_VARIATION);

		double currentAngle = src.getDirectionAngle();
		double[] gridAngles = {-Math.PI / 2, 0, Math.PI};

		// left, forward, right
		for (int i = 0; i < 3; i++)
		{
			if (Utils.RANDOM.nextFloat() < Config.getDouble(Config.Key.ROAD_CHANCE))
			{
				double proposedAngle = gridAngles[i] + currentAngle + angleOffset;
				double proposedX = src.getX() + (Math.cos(proposedAngle) * Config.getDouble(Config.Key.ROAD_LENGTH));
				double proposedY = src.getY() + (Math.sin(proposedAngle) * Config.getDouble(Config.Key.ROAD_LENGTH));

				proposed.add(new ProposedVertex(proposedX, proposedY, srcNewlyAdded, src.getType()));
			}


		}
	}
}
