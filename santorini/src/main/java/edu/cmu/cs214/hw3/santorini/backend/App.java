package edu.cmu.cs214.hw3.santorini.backend;

import java.io.IOException;
import edu.cmu.cs214.hw3.santorini.backend.core.GameFrameworkImpl;
import edu.cmu.cs214.hw3.santorini.backend.core.GameFramework;
import edu.cmu.cs214.hw3.santorini.backend.SantoriniPlugin // Ensure you have the correct path to your Santorini plugin
import fi.iki.elonen.NanoHTTPD;

public class App extends NanoHTTPD {

    private GameFrameworkImpl game;

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    /**
     * Start the server at :8080 port.
     * @throws IOException
     */
    public App() throws IOException {
        super(8080);

        // Initialize the game framework
        this.game = new GameFrameworkImpl();

        // Initialize and register the SantoriniPlugin directly
        SantoriniPlugin santoriniPlugin = new SantoriniPlugin();
        game.registerPlugin(santoriniPlugin);
        game.startNewGame(santoriniPlugin); // Automatically start the Santorini game

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        if (uri.equals("/play")) {
            // Handle play move requests
            Map<String, String> params = session.getParms();
            if (game.hasGame()) {
                game.playMove(Integer.parseInt(params.get("x")), Integer.parseInt(params.get("y")));
            }
        }

        // Extract the view-specific data from the game and apply it to the template.
        GameState gameplay = GameState.forGame(this.game);
        return newFixedLengthResponse(gameplay.toString());
    }
}
