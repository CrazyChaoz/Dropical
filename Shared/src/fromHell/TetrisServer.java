package fromHell;

public interface TetrisServer {

//	public abstract StartRequest start(StartRequest request)
//			throws FileNotFoundException, IOException;

	public abstract PollRequest pollGameState(PollRequest request)
			throws Exception;

//	public abstract ListRequest listGames(ListRequest request) throws Exception;
//
//	public abstract CreateRequest createGame(CreateRequest request)
//			throws Exception;
//
//	public abstract JoinRequest joinGame(JoinRequest request) throws Exception;
//
//	public abstract DetachRequest detachGame(DetachRequest request)
//			throws Exception;
//
}


