package InputInformation;

/**
 * Class that reads rank and returns a value
 * @author tarek
 *
 */
public class ReadRankingSystem {
	/**
	 * Read rank and return numerical value
	 * @param rank
	 */
	public static int GetRankNumber(RankingSystem rank) {
		if (rank == RankingSystem.Strong) {
			return 5;
		}
		else if (rank == RankingSystem.AboveAverage) {
			return 4;
		}
		else if (rank == RankingSystem.Average) {
			return 3;
		}
		else if (rank == RankingSystem.BelowAverage) {
			return 2;
		}
		return 1;
	}
}
