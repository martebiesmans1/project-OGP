package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * the class cube is similar to the class position, where position consists of doubles,
 * cube consists of integers.
 * 
 * @invar	x, y and z are always positive integers
 * 
 * @Value
 * 
 * @author Marte & Ellen
 *
 */
@Value
public class Cube {

	/**
	 * 
	 * @param x
	 *            the position of this new cube on the x-axis
	 * @param y
	 *            the position of this new cube on the y-axis
	 * @param z
	 *            the position of this new cube on the z-axis
	 * 
	 * @post The position on the x-, y- and z-axis of this new cube is equal to
	 *       the given x, y and z
	 * 
	 * @throws IllegalArgumentException
	 *             given x, y or z are not legal
	 */
	@Raw
	public Cube(int x, int y, int z) throws IllegalArgumentException {
		if (!isValidCubeCoordinate(x, y, z)) 
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * 
	 * @param x
	 *            the position of this new cube on the x-axis
	 * @param y
	 *            the position of this new cube on the y-axis
	 * @param z
	 *            the position of this new cube on the z-axis
	 * @param world
	 *            the world this cube is located in
	 * 
	 * @post The position on the x-, y- and z-axis of this new cube is equal to
	 *       the given x, y and z
	 * 
	 * @throws IllegalArgumentException
	 *             given x, y or z are not legal
	 */
	@Raw
	public Cube(int x, int y, int z, World world) throws IllegalArgumentException {
		if (!isValidCubeCoordinate(x, y, z, world))
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	private boolean isValidCubeCoordinate(int x, int y, int z) {
		if ((x < 0) || (y < 0) || (z < 0))
			return false;
		return true;
	}

	private boolean isValidCubeCoordinate(int x, int y, int z, World world) {
		if ((x < 0) || (x >= world.getNbCubesX()) || 
			(y < 0) || (y >= world.getNbCubesY()) || 
			(z < 0) || (z >= world.getNbCubesZ()))
			return false;
		return true;
	}
	
	public boolean isValidIn(World world) {
		return isValidCubeCoordinate(this.getX(), this.getY(), this.getZ(), world);
	}

	@Basic
	@Raw
	public int getX() {
		return this.x;
	}

	@Basic
	@Raw
	public int getY() {
		return this.y;
	}

	@Basic
	@Raw
	public int getZ() {
		return this.z;
	}

	/**
	 * Variables registering the position of this cube on the x-, y- and z-axis.
	 */
	private int x;
	private int y;
	private int z;

	/**
	 * constant registering the side length of one cube
	 */
	public static final double SIDE_LENGTH = 1;

	public Position getCenter() {
		return new Position(SIDE_LENGTH / 2, SIDE_LENGTH / 2, SIDE_LENGTH / 2, this);
	}

	public Cube min(Cube other) {
		return new Cube(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ());
	}
	
	public boolean isPassableIn(World world) {
		return world.getTerrainType(this).isPassable();
	}

	public boolean isSameOrAdjacentCube(Cube other) {
		int diffX = this.getX() - other.getX();
		int diffY = this.getY() - other.getY();
		int diffZ = this.getZ() - other.getZ();

		return (( (diffX == -1) || (diffX == 0) || (diffX == 1) ) && 
				( (diffY == -1) || (diffY == 0) || (diffY == 1) ) && 
				( (diffZ == -1) || (diffZ == 0) || (diffZ == 1) ) );
	}
	
	public boolean isSameOrDirectlyAdjacentCube(Cube other) {
		if (this.equals(other))
			return true;
		int diffX = this.getX() - other.getX();
		int diffY = this.getY() - other.getY();
		int diffZ = this.getZ() - other.getZ();
		
		if (( (diffX != -1) && (diffX != 0) && (diffX != 1) ) || 
			( (diffY != -1) && (diffY != 0) && (diffY != 1) ) || 
			( (diffZ != -1) && (diffZ != 0) && (diffZ != 1) ) ) {
			return false; }
		
		int nbDiff = 0;
		if (diffX!=0)
			nbDiff += 1;
		if (diffY!=0)
			nbDiff +=1;
		if (diffZ!=0)
			nbDiff +=1;
		
		return (nbDiff==1);
	}
	
	public Set<Cube> getAllAdjacentCubes(World world) {
		
		Set<Cube> result = new HashSet<Cube>();
		for (int x=-1; x<2; x++)
			for (int y=-1; y<2; y++)
				for (int z=-1; z<2; z++){
					if (! ( (x==0) && (y==0) && (z==0) ) )
						try {
							Cube possibleCube = new Cube(this.getX()+x, this.getY()+y, this.getZ()+z);
							if (possibleCube.isValidIn(world))
								result.add(possibleCube);
						} catch (IllegalArgumentException e) {}
		}
		
//		result.remove(this);
		
		return result;
	}
	
	public Set<Cube> getAllDirectlyAdjacentCubes(World world) {
		Set<Cube> result = new HashSet<Cube>();

//		Iterator<Cube> it = this.getAllNeighbouringCubes(world).iterator();
//		while (it.hasNext()) {
//			Cube possibleCube = it.next();
//			if (possibleCube.isSameOrAdjacentCube(this))
//				result.add(possibleCube);
//		}
		
		for (Cube cube : this.getAllAdjacentCubes(world)){
			if (cube.isSameOrDirectlyAdjacentCube(this))
				result.add(cube);
		}
		
		return result;
	}
	
	/**
	 * check wether this cube is located directly beside a border of the given world
	 * 
	 * @param	world
	 * 			the world the checked cube belongs to
	 * @return	true if the number of neighbouring cubes is not equal to 26
	 * @throws	IllegalArgumentException
	 * 			the given world equals null or this cubes is not within the boundaries of the given world
	 */
	boolean isDirectlyConnectedToBorder(World world) throws IllegalArgumentException {
		if ( (world==null) || (! this.isValidIn(world)) )
			throw new IllegalArgumentException();
		if (this.getAllAdjacentCubes(world).size() != 26)
			return true;		
		return false;
	}
	
	/**
	 * check wether this cube is solid and legally connected to the border of the game world
	 * 
	 * @param	world
	 * 			the world the checked cube belongs to
	 * @return	false if this cube is not solid
	 * @return	true if this cube is solid and directly connected to the border
	 * @return	true if this cube is solid and at least one adjacent cube 
	 * 			is solid and connected to the border
	 * @throws	IllegalArgumentException
	 * 			world equals null or this cube is not within the boundaries of the given world
	 */
	public boolean isSolidConnectedToBorder(World world) throws IllegalArgumentException {
		if ( (world==null) || (! this.isValidIn(world)) )
			throw new IllegalArgumentException();
//		TODO work in progress
		return false;
		
	}
	
	@Override
	public String toString() {
		return "(" + this.getX() + "," + this.getY() + "," + this.getZ() + ")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cube))
			return false;
		Cube other = (Cube) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
}
